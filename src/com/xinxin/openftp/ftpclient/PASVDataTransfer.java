package com.xinxin.openftp.ftpclient;

import java.net.*;
import java.io.*;
/**
 * ����ʵ��FTP��PASV(����ģʽ)�����ݴ���
 * */
public class PASVDataTransfer {
	private InetAddress remoteIPAddress;			//Զ��FTP����IP��ַ
	private int remoteDataPort;						//Զ��FTP���������ݶ˿�
	
	private InputStream inputStream;				//����������
	private OutputStream outputStream;				//���������
	private DataInputStream input;
	private DataOutputStream output;
	
	private Socket dataSocket;						//�����׽���
	
	public PASVDataTransfer(InetAddress ipAddress,int port){
		this.remoteIPAddress=ipAddress;
		this.remoteDataPort=port;
	}
	public boolean connectDataServer(){
		try{
			this.dataSocket=new Socket(this.remoteIPAddress,this.remoteDataPort);
			return true;
		}catch(IOException e){
			System.out.println("PASVģʽ:����FTP���ݶ˿�ʧ��!");
			return false;
		}
	}
    public boolean multiConnectServer(){
    	int times=1;
		while(times<=10){
			System.out.println("���е�"+times+"������!");
			if(this.connectDataServer()){
				return true;
			}
			times++;
		}
		return false;
    }
	public DataInputStream getInputDataStream(){
		try {
			this.inputStream=this.dataSocket.getInputStream();
		} catch (IOException e) {
			System.out.println("PASVģʽ:��ʼ������������ʧ��!");
			return null;
		}
		this.input=new DataInputStream(this.inputStream);
		return this.input;
	}
	public DataOutputStream getOutputDataStream(){
		try {
			this.outputStream=this.dataSocket.getOutputStream();
		} catch (IOException e) {
			System.out.println("PASVģʽ:��ʼ�����������ʧ��!");
			return null;
		} 
		this.output=new DataOutputStream(this.outputStream);
		return this.output;
	}
	public void closeConnection(){
		try{
			this.dataSocket.close();
			if(this.input!=null){
				this.input.close();
			}
			if(this.output!=null){
				this.output.close();
			}
			if(this.inputStream!=null){
				this.inputStream.close();
			}
			if(this.outputStream!=null){
				this.outputStream.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}