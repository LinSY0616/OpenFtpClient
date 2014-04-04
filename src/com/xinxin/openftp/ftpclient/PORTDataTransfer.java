package com.xinxin.openftp.ftpclient;

import java.net.*;
import java.io.*;
/**
 * ����ʵ��PORT(����ģʽ)�����ݴ���
 * �ڷ���PORT����֮�󣬿ͻ��˿������ݶ˿ڼ������ȴ�Զ�̷�����
 * �����ӡ��ڽ��յ�����֮��ֹͣ�������������ݽ��ա�
 * */
public class PORTDataTransfer {
	private ServerSocket localDataHandleSocket;			//���ؼ���Զ�������������ӵ��׽���
	private Socket dataSocket;							//���ؽ�������ͨ�ŵ��׽���

	private int localDataPort;							//�������ݼ����˿�
	private InetAddress localIPAddress;					//����IP��ַ

	private InputStream inputStream;					//����������
	private OutputStream outputStream;					//���������
	private DataInputStream input;
	private DataOutputStream output;

	public PORTDataTransfer(InetAddress ipAddress, int port) {
		this.localIPAddress = ipAddress;
		this.localDataPort = port;
		this.listeningPort();
	}
	private void listeningPort() {
		try {
			localDataHandleSocket = new ServerSocket(localDataPort,0,localIPAddress);
			try {
				dataSocket = localDataHandleSocket.accept();
			} catch (IOException e) {
				System.out.println("PORTģʽ:�������ݶ˿�ʧ��!");
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public DataInputStream getInputDataStream() {
		try{
			this.inputStream=this.dataSocket.getInputStream();
		}catch(IOException e){
			System.out.println("PORTģʽ:��ʼ������������ʧ��!");
			return null;
		}
		this.input=new DataInputStream(this.inputStream);
		return this.input;
	}
	public DataOutputStream getOutputDataStream() {
		try{
			this.outputStream=dataSocket.getOutputStream();
		}catch(IOException e){
			System.out.println("PORTģʽ:��ʼ�����������ʧ��!");
			return null;
		}
		return output;
	}
	public void closeConnection() {
		try {
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
