package com.xinxin.openftp.ftpclient;

import javax.swing.JTextArea;

public class FTPClientHelp {
	private static JTextArea t;
	
	public static void setT(JTextArea t) {
		FTPClientHelp.t = t;
	}
	public static void helpList(){
		t.append("�����������д�ġ�  ����Ϊ\n!\t\t?\t\tappend\t\tascii\t\tbell\n"
				+"binary\t\tbye\t\tcd\t\tclose\t\tdelete\n"
				+"debug\t\tdir\t\tdisconnect\t\tget\t\tglob\n"
				+"hash\t\thelp\t\tlcd\t\tliteral\t\tls\n"
				+"mdelete\t\tmdir\t\tmget\t\tmkdir\t\tmls\n"
				+"mput\t\topen\t\tprompt\t\tput\t\tpwd\n"
				+"quit\t\tquote\t\trecv\t\tremotehelp\t\trename\n"
				+"rmdir\r\n");
	}
	public static void print(String str){
		t.append(str+"\r\n");
	}
	public static void noHelp(){
		print("!\t\tת�嵽 shell");
	}
	public static void questionHelp(){
		print("?\t\t��ӡ���ذ�����Ϣ");
	}
	public static void appendHelp(){
		print("append\t\t�������ļ�");
	}
	public static void appendUse(){
		print("�÷�: append �����ļ� Զ���ļ�");
	}
	public static void asciiHelp(){
		print("ascii\t\t���� ASCII ��������");
	}
	public static void bellHelp(){
		print("bell\t\t�������ʱ��������");
	}
	public static void binaryHelp(){
		print("binary\t\t���ö����ƴ�������");
	}
	public static void byeHelp(){
		print("bye\t\t��ֹ ftp �Ự���˳�");
	}
	public static void cdHelp(){
		print("cd\t\t����Զ�̹���Ŀ¼");
	}
	public static void cdUse(){
		print("�÷�: cd Զ��Ŀ¼");
	}
	public static void closeHelp(){
		print("close\t\t��ֹ ftp �Ự");
	}
	public static void deleteUse(){
		print("�÷�:delete Զ���ļ���");
	}
	public static void deleteHelp(){
		print("delete\t\tɾ��Զ���ļ�");
	}
	public static void debugHelp(){
		print("debug\t\t�л�����ģʽ");
	}
	public static void dirHelp(){
		print("dir\t\t�г�Զ��Ŀ¼������");
	}
	public static void dirUse(){
		print("�÷�: dir Զ��Ŀ¼ �����ļ�");
	}
	public static void disconnectHelp(){
		print("disconnect\t\t��ֹ ftp �Ự");
	}
	public static void getHelp(){
		print("get\t\t�����ļ�");
	}
	public static void getUse(){
		print("get Զ���ļ� [�����ļ�]");
	}
	public static void globHelp(){
		print("glob\t\t�л������ļ�����Ԫ�ַ���չ");
	}
	public static void hashHelp(){
		print("hash\t\tΪÿ�������������л���ӡ��#��");
	}
	public static void helpHelp(){
		print("help\t\t��ӡ���ذ�����Ϣ");
	}
	public static void lcdHelp(){
		print("lcd\t\t���ı��ع���Ŀ¼");
	}
	public static void lcdUse(){
		print("�÷�: lcd [����Ŀ¼]");
	}
	public static void literalHelp(){
		print("literal\t\t�������� ftp ����");
	}
	public static void lsHelp(){
		print("ls\t\t�г�Զ��Ŀ¼������");
	}
	public static void lsUse(){
		print("�÷�: ls [Զ��Ŀ¼] [�����ļ�]");
	}
	public static void mdeleteHelp(){
		print("mdelete\t\tɾ������ļ�");
	}
	public static void mdeleteUse(){
		print("�÷�: mdelete [�ļ��б�]");
	}
	public static void mdirHelp(){
		print("mdir\t\t�г����Զ��Ŀ¼������");
	}
	public static void mdirUse(){
		print("�÷�: mdir Զ���ļ��б� �����ļ�");
	}
	public static void mgetHelp(){
		print("mget\t\t��ȡ����ļ�");
	}
	public static void mgetUse(){
		print("mget Զ���ļ���");
	}
	public static void mkdirHelp(){
		print("mkdir\t\t��Զ�̼�����ϴ���Ŀ¼");
	}
	public static void mkdirUse(){
		print("�÷�: mkdir Ŀ¼��");
	}
	public static void mlsHelp(){
		print("mls\t\t�г����Զ��Ŀ¼������");
	}
	public static void mlsUse(){
		print("�÷�: mls [�ļ��б�]");
	}
	public static void mputHelp(){
		print("mput\t\t���Ͷ���ļ�");
	}
	public static void mputUse(){
		print("�÷�:mput �����ļ�");
	}
	public static void openHelp(){
		print("open\t\t���ӵ�Զ�� FTP");
	}
	public static void openUse(){
		print("�÷�: OPEN ������ [�˿�]");
	}
	public static void promptHelp(){
		print("prompt\t\t�ڶ��������ǿ�ƽ�����ʾ");
	}
	public static void putHelp(){
		print("put\t\t����һ���ļ�");
	}
	public static void putUse(){
		print("�÷�: put �����ļ� [Զ���ļ�]");
	}
	public static void pwdHelp(){
		print("pwd\t\t��Զ�̼�����ϴ�ӡ����Ŀ¼");
	}
	public static void quitHelp(){
		print("quit\t\t��ֹ ftp �Ự���˳�");
	}
	public static void quoteHelp(){
		print("quote\t\t�������� ftp ����");
	}
	public static void recvHelp(){
		print("recv\t\t�����ļ�");
	}
	public static void recvUse(){
		print("�÷�: recv Զ���ļ� �����ļ�");
	}
	public static void remotehelpHelp(){
		print("remotehelp\t\t��Զ�̷�������ȡ����");
	}
	public static void remotehelpUse() {
		print("�÷�: remotehelp [��������]");
	}
	public static void renameHelp(){
		print("rename\t\t�������ļ�");
	}
	public static void renameUse(){
		print("�÷�: rename Զ���ļ��� ���ļ���");
	}
	public static void rmdirHelp(){
		print("rmdir\t\t��Զ�̼������ɾ��Ŀ¼");
	}
	public static void rmdirUse(){
		print("�÷�: rmdir Զ��Ŀ¼��");
	}
	public static void sendHelp(){
		print("send\t\t����һ���ļ�");
	}
	public static void sendUse(){
		print("�÷�: send �����ļ� [Զ���ļ�]");
	}
	public static void sizeHelp(){
		print("size\t\t����Զ���ļ�����С");
	}
	public static void sizeUse() {
		print("�÷�: size Զ���ļ���");
	}
	public static void statusHelp(){
		print("status\t\t��ʾ��ǰ״̬");
	}
	public static void traceHelp(){
		print("trace\t\t�л����ݰ�����");
	}
	public static void typeHelp(){
		print("type\t\t�����ļ���������");
	}
	public static void userHelp(){
		print("user\t\t�������û���Ϣ");
	}
	public static void userUse(){
		print("�÷�: user �û��� [����] [�ʺ�]");
	}
	public static void verboseHelp(){
		print("verbose\t\t�л���ϸģʽ");
	}
	
	public static void help(String args[]){
		for(int i=0;i<args.length;i++){
			if(args[i].equalsIgnoreCase("!")){
				noHelp();
			}else if(args[i].equalsIgnoreCase("?")){
				questionHelp();
			}else if(args[i].toLowerCase().startsWith("a")){
				if(args[i].equalsIgnoreCase("a")){
					ambiHelpCmd(args[i]);
				}
				if(args[i].equalsIgnoreCase("append") || args[i].equalsIgnoreCase("ap") || 
						args[i].equalsIgnoreCase("app") ||args[i].equalsIgnoreCase("appe")
						||args[i].equalsIgnoreCase("appen")){
					appendHelp();
				}else if(args[i].equalsIgnoreCase("ascii") || args[i].equalsIgnoreCase("as") ||
						args[i].equalsIgnoreCase("asc") || args[i].equalsIgnoreCase("asci")){
					asciiHelp();
				}else{
					wrongHelpCmd(args[i]);
				}
			}else if(args[i].toLowerCase().startsWith("b")){
				if(args[i].equalsIgnoreCase("b")){
					ambiHelpCmd(args[i]);
				}else if(args[i].equalsIgnoreCase("bell") || args[i].equalsIgnoreCase("be") || args[i].equalsIgnoreCase("bel")){
					bellHelp();
				}else if(args[i].equalsIgnoreCase("binary") || args[i].equalsIgnoreCase("bi") || args[i].equalsIgnoreCase("bin")
						||args[i].equalsIgnoreCase("bina") || args[i].equalsIgnoreCase("binar") || args[i].equalsIgnoreCase("binary")){
					binaryHelp();
				}else if(args[i].equalsIgnoreCase("bye") || args[i].equalsIgnoreCase("by")){
					byeHelp();
				}else{
					wrongHelpCmd(args[i]);
				}	
			}else if(args[i].toLowerCase().startsWith("c")){
				if(args[i].equalsIgnoreCase("c")){
					ambiHelpCmd(args[i]);
				}else if(args[i].equalsIgnoreCase("cd")){
					cdHelp();
				}else if(args[i].equalsIgnoreCase("close") || args[i].equalsIgnoreCase("cl") || args[i].equalsIgnoreCase("clo")
						||args[i].equalsIgnoreCase("clos") || args[i].equalsIgnoreCase("close")){
					closeHelp();
				}else{
					wrongHelpCmd(args[i]);
				}
			}else if(args[i].toLowerCase().startsWith("d")){
				if(args[i].equalsIgnoreCase("d") || args[i].equalsIgnoreCase("de") || args[i].equalsIgnoreCase("di")){
					ambiHelpCmd(args[i]);
				}else if(args[i].equalsIgnoreCase("delete") || args[i].equalsIgnoreCase("del") || args[i].equalsIgnoreCase("dele") 
						|| args[i].equalsIgnoreCase("delet")){
					deleteHelp();
				}else if(args[i].equalsIgnoreCase("debug") || args[i].equalsIgnoreCase("deb") || args[i].equalsIgnoreCase("debu")){
					debugHelp();
				}else if(args[i].equalsIgnoreCase("dir")){
					dirHelp();
				}else if(args[i].equalsIgnoreCase("dis") || args[i].equalsIgnoreCase("disconnect") ||args[i].equalsIgnoreCase("disc")
						||args[i].equalsIgnoreCase("disco") || args[i].equalsIgnoreCase("discon") || args[i].equalsIgnoreCase("disconn")
						||args[i].equalsIgnoreCase("disconne") ||args[i].equalsIgnoreCase("disconnec")){
					disconnectHelp();
				}else{
					wrongHelpCmd(args[i]);
				}
			}else if(args[i].toLowerCase().startsWith("g")){
				if(args[i].equalsIgnoreCase("g")){
					ambiHelpCmd(args[i]);
				}else if(args[i].equalsIgnoreCase("get") || args[i].equalsIgnoreCase("ge")){
					getHelp();
				}else if(args[i].equalsIgnoreCase("glob") || args[i].equalsIgnoreCase("gl") || args[i].equalsIgnoreCase("glo")){
					globHelp();
				}
			}else if(args[i].toLowerCase().startsWith("h")){
				if(args[i].equalsIgnoreCase("h")){
					ambiHelpCmd(args[i]);
				}else if(args[i].equalsIgnoreCase("hash") || args[i].equalsIgnoreCase("ha") || args[i].equalsIgnoreCase("has")){
					hashHelp();
				}else if(args[i].equalsIgnoreCase("help") ||args[i].equalsIgnoreCase("he")||args[i].equalsIgnoreCase("hel")){
					helpHelp();
				}else{
					wrongHelpCmd(args[i]);
				}
			}else if(args[i].toLowerCase().startsWith("l")){
				if(args[i].equalsIgnoreCase("l")){
					ambiHelpCmd(args[i]);
				}else if(args[i].equalsIgnoreCase("lcd") || args[i].equalsIgnoreCase("lc")){
					lcdHelp();
				}else if(args[i].equalsIgnoreCase("literal") ||args[i].equalsIgnoreCase("li") ||args[i].equalsIgnoreCase("lit")
						||args[i].equalsIgnoreCase("lite") ||args[i].equalsIgnoreCase("liter")||args[i].equalsIgnoreCase("litera")){
					literalHelp();
				}else if(args[i].equalsIgnoreCase("ls")){
					lsHelp();
				}else{
					wrongHelpCmd(args[i]);
				}
			}else if(args[i].toLowerCase().startsWith("m")){
				if(args[i].equalsIgnoreCase("m") || args[i].equalsIgnoreCase("md")){
					ambiHelpCmd(args[i]);
				}else if(args[i].equalsIgnoreCase("mdelete") || args[i].equalsIgnoreCase("mde") ||args[i].equalsIgnoreCase("mdel")
						||args[i].equalsIgnoreCase("mdele") ||args[i].equalsIgnoreCase("mdelet")){
					mdeleteHelp();
				}else if(args[i].equalsIgnoreCase("mdir") ||args[i].equalsIgnoreCase("mdi")){
					mdirHelp();
				}else if(args[i].equalsIgnoreCase("mget") ||args[i].equalsIgnoreCase("mg") ||args[i].equalsIgnoreCase("mge")){
					mgetHelp();
				}else if(args[i].equalsIgnoreCase("mkdir") ||args[i].equalsIgnoreCase("mk") ||args[i].equalsIgnoreCase("mkd") 
						||args[i].equalsIgnoreCase("mkdi")){
					mkdirHelp();
				}else if(args[i].equalsIgnoreCase("mls") || args[i].equalsIgnoreCase("ml")){
					mlsHelp();
				}else if(args[i].equalsIgnoreCase("mput") ||args[i].equalsIgnoreCase("mp") ||args[i].equalsIgnoreCase("mpu")){
					mputHelp();
				}else{
					wrongHelpCmd(args[i]);
				}
			}else if(args[i].toLowerCase().startsWith("o")){
				if(args[i].equalsIgnoreCase("open") ||args[i].equalsIgnoreCase("o") ||args[i].equalsIgnoreCase("op")
						||args[i].equalsIgnoreCase("ope")){
					openHelp();
				}else{
					wrongHelpCmd(args[i]);
				}
			}else if(args[i].toLowerCase().startsWith("p")){
				if(args[i].equalsIgnoreCase("p")){
					ambiHelpCmd(args[i]);
				}else if(args[i].equalsIgnoreCase("prompt") ||args[i].equalsIgnoreCase("pr")||args[i].equalsIgnoreCase("pro")
						||args[i].equalsIgnoreCase("prom") ||args[i].equalsIgnoreCase("promp")){
					promptHelp();
				}else if(args[i].equalsIgnoreCase("put") ||args[i].equalsIgnoreCase("pu")){
					putHelp();
				}else if(args[i].equalsIgnoreCase("pwd") ||args[i].equalsIgnoreCase("pw")){
					pwdHelp();
				}else{
					wrongHelpCmd(args[i]);
				}
			}else if(args[i].toLowerCase().startsWith("q")){
				if(args[i].equalsIgnoreCase("qu") || args[i].equalsIgnoreCase("q")){
					ambiHelpCmd(args[i]);
				}else if(args[i].equalsIgnoreCase("quit") ||args[i].equalsIgnoreCase("qui")){
					quitHelp();
				}else if(args[i].equalsIgnoreCase("quote") ||args[i].equalsIgnoreCase("quo") ||args[i].equalsIgnoreCase("quot")){
					quoteHelp();
				}else{
					wrongHelpCmd(args[i]);
				}
			}else if(args[i].toLowerCase().startsWith("r")){
				if(args[i].equalsIgnoreCase("re") ||args[i].equalsIgnoreCase("r")){
					ambiHelpCmd(args[i]);
				}else if(args[i].equalsIgnoreCase("recv") ||args[i].equalsIgnoreCase("rec")){
					recvHelp();
				}else if(args[i].equalsIgnoreCase("remotehelp") ||args[i].equalsIgnoreCase("rem") ||args[i].equalsIgnoreCase("remo")
						||args[i].equalsIgnoreCase("remot") ||args[i].equalsIgnoreCase("remote") ||args[i].equalsIgnoreCase("remoteh")
						||args[i].equalsIgnoreCase("remotehe")|| args[i].equalsIgnoreCase("remotehel")){
					remotehelpHelp();
				}else if(args[i].equalsIgnoreCase("rename") ||args[i].equalsIgnoreCase("ren")||args[i].equalsIgnoreCase("rena")
						||args[i].equalsIgnoreCase("renam")){
					renameHelp();
				}else if(args[i].equalsIgnoreCase("rmdir") ||args[i].equalsIgnoreCase("rm") ||args[i].equalsIgnoreCase("rmd")
						||args[i].equalsIgnoreCase("rmdi")){
					rmdirHelp();
				}else{
					wrongHelpCmd(args[i]);
				}
			}else if(args[i].toLowerCase().startsWith("s")){
				if(args[i].equalsIgnoreCase("s")){
					ambiHelpCmd(args[i]);
				}else if(args[i].equalsIgnoreCase("send") ||args[i].equalsIgnoreCase("se") ||args[i].equalsIgnoreCase("sen")){
					sendHelp();
				}else if(args[i].equalsIgnoreCase("status") ||args[i].equalsIgnoreCase("st") ||args[i].equalsIgnoreCase("sta")
						||args[i].equalsIgnoreCase("stat") ||args[i].equalsIgnoreCase("statu")){
					statusHelp();
				}else{
					wrongHelpCmd(args[i]);
				}
			}else if(args[i].toLowerCase().startsWith("t")){
				if(args[i].equalsIgnoreCase("t")){
					ambiHelpCmd(args[i]);
				}else if(args[i].equalsIgnoreCase("trace") ||args[i].equalsIgnoreCase("tr") ||args[i].equalsIgnoreCase("tra")
						||args[i].equalsIgnoreCase("trac")){
					traceHelp();
				}else if(args[i].equalsIgnoreCase("type")||args[i].equalsIgnoreCase("ty") ||args[i].equalsIgnoreCase("typ")){
					typeHelp();
				}else{
					wrongHelpCmd(args[i]);
				}
			}else if(args[i].toLowerCase().startsWith("u")){
				if(args[i].equalsIgnoreCase("u")){
					ambiHelpCmd(args[i]);
				}else if(args[i].equalsIgnoreCase("user") ||args[i].equalsIgnoreCase("us") ||args[i].equalsIgnoreCase("use")){
					userHelp();
				}else{
					wrongHelpCmd(args[i]);
				}
			}else if(args[i].toLowerCase().startsWith("v")){
				if(args[i].equalsIgnoreCase("verbose") ||args[i].equalsIgnoreCase("v") ||args[i].equalsIgnoreCase("ve")
						||args[i].equalsIgnoreCase("ver") ||args[i].equalsIgnoreCase("verb") ||args[i].equalsIgnoreCase("verbo")
						||args[i].equalsIgnoreCase("verbos")){
					verboseHelp();
				}else{
					wrongHelpCmd(args[i]);
				}
			}else{
				wrongHelpCmd(args[i]);
			}
		}
	}
	public static void ambiHelpCmd(String cmd){
		print("�����԰�������"+cmd);
	}
	public static void wrongHelpCmd(String cmd){
		print("��Ч��������"+cmd);
	}
	
	
}

