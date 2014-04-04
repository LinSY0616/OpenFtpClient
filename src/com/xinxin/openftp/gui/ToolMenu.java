package com.xinxin.openftp.gui;
import java.awt.event.InputEvent;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;

public class ToolMenu{
	private JMenu toolMenu; // ����
	private JMenu transferType;					//����ģʽ
	private JRadioButton pasvType;
	private JRadioButton portType;
	private JMenu dataTransferType;				//����ģʽ
	private JRadioButton asciiDataType;			//ASCII����ģʽ
	private JRadioButton binaryDataType;		//BINARY����ģʽ
	
	private JMenu lookAndFeel;					//�������� 
	private JRadioButton metal;
	private JRadioButton apple;
	private JRadioButton windows;
	private JRadioButton motif;
	private JRadioButton liquid;
	private JMenuItem showClientInfo;
	private JMenuItem showServerInfo;
 
	public JMenu initToolMenu(){
		toolMenu=new JMenu("����(T)");
		toolMenu.setMnemonic('T');	
		transferType=new JMenu("����ģʽ(M)");
		pasvType=new JRadioButton("PASV ����ģʽ");
		pasvType.setActionCommand("pasvType");
		portType=new JRadioButton("PORT ����ģʽ");
		portType.setActionCommand("portType");
		ButtonGroup mbg=new ButtonGroup();
		mbg.add(pasvType);
		mbg.add(portType);
		transferType.add(pasvType);
		transferType.add(portType);
		toolMenu.add(transferType);
		dataTransferType=new JMenu("����ģʽ(D)");
		dataTransferType.setMnemonic('D');
	 
		asciiDataType=new JRadioButton("�ı�ģʽ");
		asciiDataType.setActionCommand("asciiDataType");
		binaryDataType=new JRadioButton("������ģʽ");
		binaryDataType.setActionCommand("binaryDataType");
		ButtonGroup dataBg=new ButtonGroup();
	  
		dataBg.add(asciiDataType);
		dataBg.add(binaryDataType);
		 
		dataTransferType.add(asciiDataType);
		dataTransferType.add(binaryDataType);
		toolMenu.add(dataTransferType);

		toolMenu.addSeparator();
		metal=new JRadioButton("Java ���");
		windows=new JRadioButton("Windows ���");
		motif=new JRadioButton("Solaris ���");
		liquid=new JRadioButton("Liquid ���");
		apple=new JRadioButton("QuaQua Mac ���");
		ButtonGroup bg=new ButtonGroup();
		bg.add(metal);
		bg.add(windows);
		bg.add(motif);
		bg.add(liquid);
		bg.add(apple);
		metal.setActionCommand("metal");
		liquid.setActionCommand("liquid");
		motif.setActionCommand("motif");
		windows.setActionCommand("windows");
		apple.setActionCommand("apple");
		lookAndFeel=new JMenu("���Ƥ��(S)");
		lookAndFeel.setMnemonic('S');
		lookAndFeel.add(metal);
		lookAndFeel.add(liquid);
		lookAndFeel.add(motif);
		lookAndFeel.add(windows);
		lookAndFeel.add(apple);
		toolMenu.add(lookAndFeel);
		toolMenu.addSeparator();
		
		showClientInfo=new JMenuItem("��ʾ�ͻ�����Ϣ(C)",new ImageIcon("img/client.png"));
		showClientInfo.setMnemonic('C');
		showClientInfo.setActionCommand("showClientInfo");
		toolMenu.add(showClientInfo);
		showServerInfo=new JMenuItem("��ʾ��������Ϣ(I)",new ImageIcon("img/server.png"));
		showServerInfo.setMnemonic('I');
		showServerInfo.setAccelerator(KeyStroke.getKeyStroke('I',InputEvent.CTRL_MASK));
		showServerInfo.setActionCommand("showServerInfo");
		toolMenu.add(showServerInfo);
		return toolMenu;
	}

	public JRadioButton getPasvType() {
		return pasvType;
	}

	public JRadioButton getPortType() {
		return portType;
	}

	public JRadioButton getAsciiDataType() {
		return asciiDataType;
	}

	public JRadioButton getBinaryDataType() {
		return binaryDataType;
	}

	public JRadioButton getMetal() {
		return metal;
	}

	public JRadioButton getApple() {
		return apple;
	}

	public JRadioButton getWindows() {
		return windows;
	}

	public JRadioButton getMotif() {
		return motif;
	}

	public JRadioButton getLiquid() {
		return liquid;
	}

	public JMenuItem getShowClientInfo() {
		return showClientInfo;
	}

	public JMenuItem getShowServerInfo() {
		return showServerInfo;
	}
}
