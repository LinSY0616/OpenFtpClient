package com.xinxin.openftp.ftpclient;

import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JTextArea;

public class FTPClientImpl implements FTPClientInterface{
	//�����ó�Ա����
	public enum TRANSFERTYPE{PORT,PASV};
	private	TRANSFERTYPE transferType;		//����ģʽ PORT PASV
	public enum DATATYPE {ASCII,BINARY};		
	private DATATYPE dataTransferType;		//���ݴ���ģʽ ASCII BINARY
	private String homeDir;					//����ԭ������Ŀ¼

	//�����Ա���� 
	private int remoteServerPort; 				//����������˿ں� ͨ��Ϊ21
	private InetAddress remoteServerIPAddress; 	//������IP��ַ(�Ϸ�IP��ַ) һ��Ϊ����IP��ַ

	private InputStream inputStream; 		//���������� ��21����˿�ͨ�ŵ�������
	private OutputStream outputStream; 		//��������� ��21����˿�ͨ�ŵ������
	private DataOutputStream output;		//��21����˿�ͨ�ŵİ�װ�����
	private DataInputStream input;			//��21����˿�ͨ�ŵİ�װ������ 

	private Socket clientSocket; 			//�ͻ����׽��� ����21�˿ڵĿͻ����׽���
	private int localConnectionPort;		//���س�ʼ����21�Ŷ˿ڵĶ˿�

	private String localWorkDir;			//���ص�ǰ����Ŀ¼ 
	//PORTģʽ����
	private InetAddress portLocalIPAddress;	//����IP��ַ
	private int portLocalDataPort;			//����PORTģʽ���ݶ˿�
	private PORTDataTransfer portHandle;	//PORTģʽ�����ݽ��վ��
	//PASVģʽ����
	private InetAddress pasvRemoteIPAddress;//Զ��IP��ַ
	private int pasvRemoteDataPort;			//Զ��PASVģʽ���ݶ˿�
	private PASVDataTransfer pasvHandle;	//PASVģʽ�����ݽ��վ��
	//���ÿͻ���ģʽ
	private boolean bellModeOn;				//����ģʽ
	//������� 
	private String remoteServerName;			//Զ��FTP��������
	private String localHostName;			//������������
	private String serverReturnInfo;		//������������Ϣ 
	private JTextArea showInfoTextArea;
	private boolean isLoggedIn;
	private String cmdResultInfo;
	private final int BUFFER_SIZE=4096;
	private boolean isCmdLine;
	private int tabIndex;
	private JTable dirTable;
	
	private String remoteFile;
	private Vector <String> remoteFiles;
	//��ʼ���ͻ���
	public void init(){
		this.homeDir=System.getProperty("user.home");
		this.localWorkDir=this.homeDir;
		this.isLoggedIn=false;
		this.cmdResultInfo="";
		this.isCmdLine=false;
	}
	public FTPClientImpl(){
		this.init();
	}
	public FTPClientImpl(String remoteServerName,int remoteServerPort){
		this.init();
		this.remoteServerName=remoteServerName;
		this.remoteServerPort=remoteServerPort;
	}
	public void setShowInfoTextArea(JTextArea showInfoTextArea) {
		this.showInfoTextArea = showInfoTextArea;
	}
	public void setTransferType(TRANSFERTYPE transferType) {
		this.transferType = transferType;
	}
	public void setDataTransferType(DATATYPE dataTransferType) {
		this.dataTransferType = dataTransferType;
	}
	public void setCmdLine(boolean isCmdLine) {
		this.isCmdLine = isCmdLine;
	}
	public int getTabIndex() {
		return tabIndex;
	}
	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}
	public void setDirTableMouseListener(MouseListener ml){
		this.dirTable.addMouseListener(ml);
	}
	public JTable getDirTable() {
		return dirTable;
	}
	public void setDirTable(JTable dirTable) {
		this.dirTable = dirTable;
	}
	public String getRemoteFile() {
		return remoteFile;
	}
	public void setRemoteFile(String remoteFile) {
		this.remoteFile = remoteFile;
	}
	
	public void setRemoteFiles(Vector<String> remoteFiles) {
		this.remoteFiles = remoteFiles;
	}
	public Vector<String> getRemoteFiles() {
		return remoteFiles;
	}
	//������
	public String getRemoteHostName() {
		return remoteServerName;
	}
	public String getLocalHostName() {
		return localHostName;
	}
	public String getServerReturnInfo() {
		return serverReturnInfo;
	}
	public void setLocalWorkDir(String localWorkDir) {
		this.localWorkDir = localWorkDir;
	}
	public String getLocalWorkDir() {
		return localWorkDir;
	}
	public DATATYPE getDataTransferType() {
		return dataTransferType;
	}
	public TRANSFERTYPE getTransferType() {
		return transferType;
	}
	public String getCmdResultInfo() {
		return cmdResultInfo;
	}
	private void appInfoToShowArea(String info){
		Calendar c=Calendar.getInstance();
		String time="["+c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND)+"]";
		String text=time+"  "+info+"\r\n";
		this.showInfoTextArea.append(text);
		this.showInfoTextArea.setCaretPosition(this.showInfoTextArea.getDocument().getLength());
	}
	//����˽�з���
	private boolean acceptFileData(DataInputStream netDataInput,String localFileName,boolean isAppend){
		ByteArrayOutputStream swapStream=new ByteArrayOutputStream();
		DataOutputStream storeOutput=null;
		byte recvDataInfo[]=new byte[BUFFER_SIZE];
		int result=0;
		try {
			storeOutput = new DataOutputStream(new FileOutputStream(new File(this.localWorkDir,localFileName),isAppend));
			while((result=netDataInput.read(recvDataInfo))>0){
				swapStream.write(recvDataInfo,0,result);
				storeOutput.write(swapStream.toByteArray());
				swapStream.reset();
			}
		}catch (FileNotFoundException e) {
			System.out.println("�������������ļ�ʧ��!");
			return false;
		}catch (IOException e){
			remoteServerClosed();
			return false;
		}finally{
			try {
				netDataInput.close();
				swapStream.close();
				storeOutput.close();
			} catch (IOException e) {
				System.out.println("�ر��ļ������ʧ��!");
			}
		}
		return true;
	}
	private boolean showCmdData(DataInputStream dataInput){
		byte recvDataInfo[]=new byte[BUFFER_SIZE];
		this.cmdResultInfo="";
		try{
			while(dataInput.read(recvDataInfo)>0){
				this.cmdResultInfo+=new String(recvDataInfo,"utf8");
			}
		}catch(IOException e){
			remoteServerClosed();
			return false;
		}
		try {
			dataInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    Set <String> strSet=new HashSet <String>();
	    String dirParts[]=this.cmdResultInfo.trim().split("\r\n");
	    this.cmdResultInfo="";
	    for(String dir:dirParts){
	    	strSet.add(dir);
	    }
	    String str[]=new String[strSet.size()];
	    strSet.toArray(str);
	    for(int i=0;i<str.length-1;i++){
	    	this.cmdResultInfo+=str[i]+"\r\n";
	    }
	    this.cmdResultInfo+=str[str.length-1];
	    if(this.isCmdLine){
			this.appInfoToShowArea("\r\n"+this.cmdResultInfo);
	    }
		return true;
	}
	private boolean storeCmdData(DataInputStream netDataInput,String localFileName,boolean isAppend){
		return this.acceptFileData(netDataInput,localFileName,isAppend);
	}
	//����Զ�̷�����
	private boolean buildCommandConnection() {
		try {
			//����FTP������
			this.clientSocket = new Socket(this.remoteServerIPAddress, this.remoteServerPort);
			this.appInfoToShowArea("���ӵ�:"+this.remoteServerName);
			//��������������
			this.inputStream=this.clientSocket.getInputStream();
			this.outputStream=this.clientSocket.getOutputStream();
			//��װ������
			this.input=new DataInputStream(this.inputStream);
			this.output=new DataOutputStream(this.outputStream);
			//����һЩ���Ӳ���
			this.localHostName=this.clientSocket.getLocalAddress().getHostName();
			this.localConnectionPort=this.clientSocket.getLocalPort();
			this.portLocalDataPort=this.localConnectionPort+1;
			this.portLocalIPAddress=this.clientSocket.getLocalAddress();
			//���ܷ��������صĻ�ӭ��Ϣ
			this.recvServerReturnInfo();
			return true;
		} catch (IOException e) {
			this.appInfoToShowArea("����FTP������ʧ��!");
			return false;
		}
	}
	private boolean buildDataConnection(){
		if(this.transferType==TRANSFERTYPE.PORT){
			portHandle=new PORTDataTransfer(this.portLocalIPAddress,this.portLocalDataPort);
			return true;
		}else if(this.transferType==TRANSFERTYPE.PASV){
			pasvHandle=new PASVDataTransfer(this.pasvRemoteIPAddress,this.pasvRemoteDataPort);
			if(pasvHandle.multiConnectServer()){
				return true;
			}
		}
		return false;
	}
	private void closeConnection() {
		try {
			this.clientSocket.close();
			this.clientSocket=null;
		} catch (IOException e) {
			this.appInfoToShowArea("�ر�����ʧ��!");
		}
	}
	private void commandRequest(String cmdName){
		byte cmd[]=null; 
		cmd=new String(cmdName+"\r\n").getBytes();
		this.appInfoToShowArea(cmdName);
		this.sendInfoToServer(cmd);
	}
	private void commandRequest(String cmdName,String cmdParam){
		byte cmd[]=null;
		cmd=new String(cmdName+" "+cmdParam+"\r\n").getBytes();
		this.appInfoToShowArea(cmdName+" "+cmdParam);
		this.sendInfoToServer(cmd);
	}
	private void recvServerReturnInfo(){
		byte buf[] = new byte[BUFFER_SIZE];
		try {
			input.read(buf);
		} catch (IOException e) {
			this.appInfoToShowArea("��ȡFTP������������Ϣʧ��!");
			return;
		}
		String info = new String(buf).trim();
		this.serverReturnInfo=info;
		this.appInfoToShowArea(info);
	}
	private void remoteServerClosed(){
		this.appInfoToShowArea("Զ�������ر�����!");
		if(this.isConnected()){
			this.closeConnection();
		}
	}
	private void sendInfoToServer(byte info[]){
		//������������
		try{
			this.output.write(info);
		}catch(IOException e){
			this.remoteServerClosed();
		}
	}
	private boolean sendFileData(DataOutputStream dataOutput,String fileName){
		byte localInfo[]=new byte[BUFFER_SIZE];
		ByteArrayOutputStream swapStream=new ByteArrayOutputStream();
		DataInputStream inputData=null;
		int result=0;
		try {
			inputData=new DataInputStream(new FileInputStream(fileName));
			while((result=inputData.read(localInfo))>0){
				swapStream.write(localInfo, 0, result);
				dataOutput.write(swapStream.toByteArray());
				swapStream.reset();
			}
		} catch (FileNotFoundException e) {
			this.appInfoToShowArea("��ȡ������ʧ��!");
			return false;
		} catch(IOException e){
			remoteServerClosed();
			return false;
		}

		try {
			dataOutput.close();	
			inputData.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	private void setBellMode(){
		if(bellModeOn){
			bellModeOn=false;
			this.appInfoToShowArea("����ģʽ:��!");
		}else{
			bellModeOn=true;
			this.appInfoToShowArea("����ģʽ:��!");
		}
	}
	private String setPORTCmd(InetAddress ipAddress,int dataPort){
		String portCmd=null;
		String strIP=ipAddress.getHostAddress();
		strIP=strIP.replace('.', ',');
		//���ݶ˿ڸ�ʽת��
		String strPort=Integer.toHexString(dataPort);
		int len=strPort.length();
		String lowPart=strPort.substring(len-2);
		String highPart=strPort.substring(0,len-2);
		//��������ת��Ϊʮ���Ƶ��������з���
		int low=Integer.decode("0x"+lowPart);
		int high=Integer.decode("0x"+highPart);
		portCmd=new String("PORT"+" "+strIP+","+high+","+low);
		return portCmd;
	}
	private boolean sendPASVRequest(){
		this.commandRequest("PASV");
		this.recvServerReturnInfo();
		if(this.getServerReturnInfo().startsWith("530")){
			return false;
		}
		this.getPASVInfo(this.getServerReturnInfo());//��ȡPASV��Ӧ��Ϣ ����Զ������IP��ַ�����ݶ˿�
		return true;
	} 
	private boolean sendPORTRequest(){
		String portCmd=null;
		portCmd=this.setPORTCmd(portLocalIPAddress, portLocalDataPort);
		this.commandRequest(portCmd);
		this.recvServerReturnInfo();
		//530 Not logged in.
		if(this.serverReturnInfo.startsWith("530")){
			return false;
		}
		return true;
	}
	private void endPORTTransfer(){
		this.portHandle.closeConnection();
		this.recvServerReturnInfo();
		this.portLocalDataPort+=1;
		if(bellModeOn){
			Toolkit.getDefaultToolkit().beep();
		}
	}
	private void endPASVTransfer(){ 
		this.pasvHandle.closeConnection();
		this.recvServerReturnInfo();
		if(bellModeOn){
			Toolkit.getDefaultToolkit().beep();
		}
	}
	private void getPASVInfo(String pasvInfo){
		int begin=pasvInfo.indexOf("(");
		int end=pasvInfo.indexOf(")");
		String ipPortStr[]=pasvInfo.substring(begin+1,end).split(",");
		byte ipByte[]=new byte[4];
		ipByte[0]=(byte)Integer.parseInt(ipPortStr[0]);
		ipByte[1]=(byte)Integer.parseInt(ipPortStr[1]);
		ipByte[2]=(byte)Integer.parseInt(ipPortStr[2]);
		ipByte[3]=(byte)Integer.parseInt(ipPortStr[3]);
		String portHigh=Integer.toHexString(Integer.parseInt(ipPortStr[4]));
		String portLow=Integer.toHexString(Integer.parseInt(ipPortStr[5]));
		this.pasvRemoteDataPort=Integer.decode(("0x"+portHigh+portLow));
		try {
			this.pasvRemoteIPAddress=InetAddress.getByAddress(ipByte);
		} catch (UnknownHostException e) {
			this.appInfoToShowArea("��ȡԶ������IP��ַʧ��!");
		}
	}
	private boolean isRemoteFileExists(){
		if(this.getServerReturnInfo().startsWith("550")){
			return false;
		}
		return true;
	}
	private boolean isLocalFileExists(String fileName){
		File file=new File(fileName);
		if(file.exists() && file.isFile()){
			return true;
		}else{
			return false;
		}
	}
	public String getCmdReplyInfo(){
		int strStart=this.getServerReturnInfo().indexOf('\"');
		int strEnd=this.getServerReturnInfo().indexOf('\"', strStart+1);
		String str=this.getServerReturnInfo().substring(strStart+1, strEnd);
		return str;
	}
	public boolean resolveAddress(String remoteServerName){
		try {
			this.remoteServerIPAddress=InetAddress.getByName(remoteServerName);
			this.appInfoToShowArea("������ַ "+remoteServerName+" Ϊ:"+this.remoteServerIPAddress.getHostAddress());
			this.remoteServerName=remoteServerName;
		} catch (UnknownHostException e) {
			this.appInfoToShowArea("δ֪������ַ");
			return false;
		}
		return true;
	}
	public boolean isConnected(){
		if(clientSocket==null){
			return false;
		}
		return true;
	}
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	@Override
	public void append(String localFile, String remoteFile) {
		if(this.transferType==TRANSFERTYPE.PORT){
			if(this.isLocalFileExists(localFile)){
				if(this.sendPORTRequest()){
					this.commandRequest("APPE", remoteFile);
					this.buildDataConnection();
					this.recvServerReturnInfo();
					this.sendFileData(portHandle.getOutputDataStream(), localFile);
					this.endPORTTransfer();
				}
			}else{
				this.appInfoToShowArea(localFile+" :�����ļ�������!");
			}
		}else if(this.transferType==TRANSFERTYPE.PASV){
			if(this.isLocalFileExists(localFile)){
				if(this.sendPASVRequest()){
					this.commandRequest("APPE", remoteFile);
					this.buildDataConnection();
					this.recvServerReturnInfo();
					this.sendFileData(pasvHandle.getOutputDataStream(), localFile);
					this.endPASVTransfer();
				}
			}else{
				this.appInfoToShowArea(localFile+" :�����ļ�������!");
			}
		}
	}

	@Override
	public void ascii() {
		this.dataTransferType=DATATYPE.ASCII;
		this.commandRequest("TYPE","A");
		this.recvServerReturnInfo();
	}

	@Override
	public void bell() {
		this.setBellMode();
	}

	@Override
	public void binary() {
		this.dataTransferType=DATATYPE.BINARY;
		this.commandRequest("TYPE","I");
		this.recvServerReturnInfo();
	}

	@Override
	public void bye() {
		this.quit();
	}

	@Override
	public void cd(String remoteDir) {
		this.commandRequest("CWD",remoteDir);
		this.recvServerReturnInfo();
	}

	@Override
	public void cdup(){
		this.commandRequest("CDUP");
		this.recvServerReturnInfo();
	}

	@Override
	public void close() {
		this.disconnect();
	}

	@Override
	public void debug(int debugValue) {


	}

	@Override
	public void delete(String remoteFile) {
		this.commandRequest("DELE",remoteFile);
		this.recvServerReturnInfo();
	}

	@Override
	public void dir() {
		if(this.transferType==TRANSFERTYPE.PORT){
			if(this.sendPORTRequest()){
				this.commandRequest("LIST");
				if(this.buildDataConnection()){
					this.recvServerReturnInfo();
					this.showCmdData(portHandle.getInputDataStream());
					this.endPORTTransfer();
				}
			}
		}else if(this.transferType==TRANSFERTYPE.PASV){
			if(this.sendPASVRequest()){
				this.commandRequest("LIST");
				if(this.buildDataConnection()){
					this.recvServerReturnInfo();
					this.showCmdData(pasvHandle.getInputDataStream());
					this.endPASVTransfer();
				}
			}
		}
	}
	@Override
	public void dir(String remoteDir) {
		if(this.transferType==TRANSFERTYPE.PORT){
			if(this.sendPORTRequest()){
				this.commandRequest("LIST", remoteDir);
				if(this.buildDataConnection()){
					this.recvServerReturnInfo();
					this.showCmdData(portHandle.getInputDataStream());
					this.endPORTTransfer();
				}
			}
		}else if(this.transferType==TRANSFERTYPE.PASV){
			if(this.sendPASVRequest()){
				this.commandRequest("LIST",remoteDir);
				if(this.buildDataConnection()){
					this.recvServerReturnInfo();
					this.showCmdData(pasvHandle.getInputDataStream());
					this.endPASVTransfer();
				}
			}
		}
	}
	@Override
	public void dir(String remoteDir, String localFile) {
		if(this.transferType==TRANSFERTYPE.PORT){
			if(this.sendPORTRequest()){
				this.commandRequest("LIST", remoteDir);
				if(this.buildDataConnection()){
					this.recvServerReturnInfo();
					this.storeCmdData(portHandle.getInputDataStream(), localFile, false);
					this.endPORTTransfer();
				}
			}
		}else if(this.transferType==TRANSFERTYPE.PASV){
			if(this.sendPASVRequest()){
				this.commandRequest("LIST",remoteDir);
				if(this.buildDataConnection()){
					this.recvServerReturnInfo();
					this.storeCmdData(pasvHandle.getInputDataStream(), localFile,false);
					this.endPASVTransfer();
				}
			}
		}
	}

	@Override
	public void disconnect() {
		this.commandRequest("QUIT");
		this.recvServerReturnInfo();
		if(isConnected()){
			this.closeConnection();
			this.clientSocket=null;
		}
	}

	@Override
	public void exit(){
		this.quit();
	}

	@Override
	public void get(String remoteFile, String localFile) {
		if(this.transferType==TRANSFERTYPE.PORT){
			if(this.sendPORTRequest()){
				this.commandRequest("RETR", remoteFile);
				this.buildDataConnection();
				this.recvServerReturnInfo();
				if(this.isRemoteFileExists()){
					this.acceptFileData(portHandle.getInputDataStream(), localFile, false);
					this.endPORTTransfer();
				}
			}
		}else if(this.transferType==TRANSFERTYPE.PASV){
			if(this.sendPASVRequest()){
				this.commandRequest("RETR",remoteFile);
				this.buildDataConnection();
				this.recvServerReturnInfo();
				if(this.isRemoteFileExists()){
					this.acceptFileData(pasvHandle.getInputDataStream(), localFile,false);
					this.endPASVTransfer();
				}
			}
		}
	}

	@Override
	public void glob() {


	}

	@Override
	public void hash() {


	}
	@Override
	public void help(){
		FTPClientHelp.helpList();
	}
	@Override
	public void help(String cmds[]) {
		FTPClientHelp.help(cmds);

	}
	public void lcd(){
		this.appInfoToShowArea("Ŀǰ�ı���Ŀ¼Ϊ:"+this.getLocalWorkDir());
	}
	@Override
	public void lcd(String dir) {
		File f=new File(dir);
		if(f.isDirectory()){
			this.setLocalWorkDir(dir);
			this.appInfoToShowArea("Ŀǰ�ı���Ŀ¼Ϊ:"+this.getLocalWorkDir());
		}else{
			this.appInfoToShowArea("�Ƿ�Ŀ¼!");
		}
	}

	@Override
	public void literal() {


	}

	@Override
	public void ls() {
		if(this.transferType==TRANSFERTYPE.PORT){
			if(this.sendPORTRequest()){
				this.commandRequest("NLST");
				this.buildDataConnection();
				this.recvServerReturnInfo();
				this.showCmdData(portHandle.getInputDataStream());
				this.endPORTTransfer();
			}
		}else if(this.transferType==TRANSFERTYPE.PASV){
			if(this.sendPASVRequest()){
				this.commandRequest("NLST");
				this.buildDataConnection();
				this.recvServerReturnInfo();
				this.showCmdData(pasvHandle.getInputDataStream());
				this.endPASVTransfer();
			}
		}

	}
	@Override
	public void ls(String remoteDir) {
		if(this.transferType==TRANSFERTYPE.PORT){
			if(this.sendPORTRequest()){
				this.commandRequest("NLST",remoteDir);
				this.buildDataConnection();
				this.recvServerReturnInfo();
				if(this.isRemoteFileExists()){
					this.showCmdData(portHandle.getInputDataStream());
					this.endPORTTransfer();
				}
			}
		}else if(this.transferType==TRANSFERTYPE.PASV){
			if(this.sendPASVRequest()){
				this.commandRequest("NLST",remoteDir);
				this.buildDataConnection();
				this.recvServerReturnInfo();
				if(this.isRemoteFileExists()){
					this.showCmdData(pasvHandle.getInputDataStream());
					this.endPASVTransfer();
				}
			}
		}

	}
	@Override
	public void ls(String remoteDir, String localFile) {
		if(this.transferType==TRANSFERTYPE.PORT){
			if(this.sendPORTRequest()){
				this.commandRequest("NLST",remoteDir);
				this.buildDataConnection();
				this.recvServerReturnInfo();
				if(this.isRemoteFileExists()){
					this.storeCmdData(portHandle.getInputDataStream(), localFile, false);
					this.endPORTTransfer();
				}
			}
		}else if(this.transferType==TRANSFERTYPE.PASV){
			if(this.sendPASVRequest()){
				this.commandRequest("NLST",remoteDir);
				this.buildDataConnection();
				this.recvServerReturnInfo();
				if(this.isRemoteFileExists()){	
					this.storeCmdData(pasvHandle.getInputDataStream(), localFile,false);
					this.endPASVTransfer();
				}
			}
		}
	}

	@Override
	public void mdelete(String[] remoteFiles) {
		if(this.transferType==TRANSFERTYPE.PORT){
			for(String file:remoteFiles){
				this.delete(file);
			}
		}else if(this.transferType==TRANSFERTYPE.PASV){
			for(String file:remoteFiles){
				this.delete(file);
			}
		}
	}

	@Override
	public void mdir(String[] remoteFiles, String localFile) {
		if(this.transferType==TRANSFERTYPE.PORT){
			int times=1;
			for(String d:remoteFiles){
				if(times>1){
					if(this.sendPORTRequest()){
						this.commandRequest("LIST",d);
						this.buildDataConnection();
						this.recvServerReturnInfo();
						this.storeCmdData(portHandle.getInputDataStream(),localFile,true);
						this.endPORTTransfer();
					}
				}else{
					this.dir(d,localFile);
				}
				times++;
			}
		}else if(this.transferType==TRANSFERTYPE.PASV){
			int times=1;
			for(String d:remoteFiles){
				if(times>1){
					if(this.sendPASVRequest()){
						this.commandRequest("LIST",d);
						this.buildDataConnection();
						this.recvServerReturnInfo();
						this.storeCmdData(pasvHandle.getInputDataStream(),localFile,true);
						this.endPASVTransfer();
					}
				}else{
					this.dir(d, localFile);
				}
				times++;
			}
		}
	}

	@Override
	public void mget(String[] remoteFiles) {
		if(this.transferType==TRANSFERTYPE.PORT){
			for(String file:remoteFiles){
				this.get(file, file);
			}
		}else if(this.transferType==TRANSFERTYPE.PASV){
			for(String file:remoteFiles){
				this.get(file, file);
			}
		}
	}

	@Override
	public void mkdir(String dirName) {
		this.commandRequest("MKD",dirName);
		this.recvServerReturnInfo();
	}

	@Override
	public void mls(String[] remoteFiles, String localFile) {
		if(this.transferType==TRANSFERTYPE.PORT){
			int times=1;
			for(String d:remoteFiles){
				if(times>1){
					if(this.sendPORTRequest()){
						this.commandRequest("NLST",d);
						this.buildDataConnection();
						this.recvServerReturnInfo();
						this.storeCmdData(portHandle.getInputDataStream(),localFile,true);
						this.endPORTTransfer();
					}
				}else{
					this.ls(d, localFile);
				}
				times++;
			}
		}else if(this.transferType==TRANSFERTYPE.PASV){
			int times=1;
			for(String d:remoteFiles){
				if(times>1){
					if(this.sendPASVRequest()){
						this.commandRequest("NLST",d);
						this.buildDataConnection();
						this.recvServerReturnInfo();
						this.storeCmdData(pasvHandle.getInputDataStream(),localFile,true);
						this.endPASVTransfer();
					}
				}else{
					this.ls(d, localFile);
				}
				times++;
			}
		}
	}


	@Override
	public void mput(String[] localFiles) {
		if(this.transferType==TRANSFERTYPE.PORT){
			for(String file:localFiles){
				this.put(file, file);
			}
		}else if(this.transferType==TRANSFERTYPE.PASV){
			for(String file:localFiles){
				this.put(file, file);
			}
		}
	}

	@Override
	public boolean open(String remoteServerName, int remoteHostPort) {
		if(this.resolveAddress(remoteServerName)){
			this.remoteServerPort=remoteHostPort;
			if(this.buildCommandConnection()){//������������
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean pass(String userPassword) {
		this.commandRequest("PASS", userPassword);
		this.recvServerReturnInfo();
		if(this.getServerReturnInfo().startsWith("230")){
			this.isLoggedIn=true;
			return true;
		}
		return false; 
	}

	@Override
	public void prompt() {


	}

	@Override
	public void put(String localFile, String remoteFile) {
		if(this.transferType==TRANSFERTYPE.PORT){
			if(this.isLocalFileExists(localFile)){
				if(this.sendPORTRequest()){
					this.commandRequest("STOR", remoteFile);
					this.recvServerReturnInfo();
					this.buildDataConnection();
					this.sendFileData(portHandle.getOutputDataStream(), localFile);
					this.endPORTTransfer();
				}
			}else{
				this.appInfoToShowArea(localFile+" :�����ļ�������!");
			}
		}else if(this.transferType==TRANSFERTYPE.PASV){
			if(this.isLocalFileExists(localFile)){
				if(this.sendPASVRequest()){
					this.commandRequest("STOR", remoteFile);
					this.recvServerReturnInfo();
					this.buildDataConnection();
					this.sendFileData(pasvHandle.getOutputDataStream(), localFile);
					this.endPASVTransfer();
				}
			}else{
				this.appInfoToShowArea(localFile+" :�����ļ�������!");
			}
		}
	}

	@Override
	public void pwd() {
		this.commandRequest("PWD");
		this.recvServerReturnInfo();
	}

	@Override
	public void quit() {
		if(this.isConnected()){
			this.disconnect();
		}
		System.exit(0);
	}

	@Override
	public void quote(String[] args) {


	}

	@Override
	public void recv(String remoteFile, String localFile) {
		this.get(remoteFile, localFile);
	}
	@Override
	public void remotehelp(){
		this.commandRequest("HELP");
		this.recvServerReturnInfo();
	}
	@Override
	public void remotehelp(String cmd) {
		this.commandRequest("HELP",cmd);
		this.recvServerReturnInfo();
	}

	@Override
	public void rename(String fromFileName, String toFileName) {
		this.commandRequest("RNFR",fromFileName);
		this.recvServerReturnInfo();
		if(this.getServerReturnInfo().startsWith("530") || this.getServerReturnInfo().startsWith("550")){
			return;
		}
		this.commandRequest("RNTO",toFileName);
		this.recvServerReturnInfo();
	}

	@Override
	public void rmdir(String remoteDir) {
		this.commandRequest("RMD",remoteDir);
		this.recvServerReturnInfo();
	}

	@Override
	public void send(String localFile, String remoteFile) {
		this.put(localFile, remoteFile);
	}

	@Override
	public void size(String remoteFile) {
		this.commandRequest("SIZE",remoteFile);
		this.recvServerReturnInfo();
	}
	@Override
	public void status() {
		if(!this.isConnected()){
			System.out.print("δ����!");
		}else{
			System.out.print("���ӵ� "+this.getRemoteHostName());
		}
		if(this.dataTransferType==DATATYPE.ASCII){
			System.out.print("����:ASCII;");
		}else if(this.dataTransferType==DATATYPE.BINARY){
			System.out.print("����:BINARY;");
		}
		if(this.bellModeOn){
			System.out.print("����:��;");
		}else{
			System.out.print("����:��;");
		}
	}

	@Override
	public void system() {
		this.commandRequest("SYST");
		this.recvServerReturnInfo();
	}
	@Override
	public void trace() {


	}
	@Override 
	public void type(){
		if(this.dataTransferType==DATATYPE.ASCII){
			this.appInfoToShowArea("ʹ�� ASCII ģʽ�����ļ�");
		}else if(this.dataTransferType==DATATYPE.BINARY){
			this.appInfoToShowArea("ʹ�� BINARY ģʽ�����ļ�");
		}
	}
	@Override
	public void type(String typeName) {
		DATATYPE t=null;
		if(typeName.equalsIgnoreCase("ASCII")){
			t=DATATYPE.ASCII;
		}else  if(typeName.equalsIgnoreCase("BINARY")){
			t=DATATYPE.BINARY;
		}
		if(t==DATATYPE.ASCII ){
			this.ascii();
		}else if(t==DATATYPE.BINARY){
			this.binary();
		}else{
			this.appInfoToShowArea(t.toString()+":δ֪ģʽ");
		}
	}

	@Override
	public boolean user(String userName) {
		this.commandRequest("USER", userName);
		this.recvServerReturnInfo();
		if(this.getServerReturnInfo().startsWith("530")){
			return false;
		}
		return true;
	}

	@Override
	public void user(String userName, String userPassword, String userAccount) {


	}

	@Override
	public void verbose() {


	}

}
