package com.xinxin.openftp.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.JOptionPane;
public class HelpMenu implements ActionListener{
	private JMenu helpMenu;						//����
	//��������˵�������Ӳ˵�
	private JMenuItem helpContent;				//�����ĵ�
	private JMenuItem officialSite;				//�ٷ���ҳ
	private JMenuItem techSupport;				//����֧��
	private JMenuItem aboutSoftware;			//���ڱ����
	public JMenu initHelpMenu(){
		helpMenu=new JMenu("����(H)");
		helpMenu.setMnemonic('H');
		helpContent=new JMenuItem("����(C)",new ImageIcon("img/help.png"));
		helpContent.setAccelerator(KeyStroke.getKeyStroke("F1"));
		helpContent.setMnemonic('C');
		helpContent.addActionListener(this);
		helpContent.setActionCommand("helpContent");
		helpMenu.add(helpContent);
		helpMenu.addSeparator();
		officialSite=new JMenuItem("�ٷ���վ(P)",new ImageIcon("img/official.png"));
		officialSite.setMnemonic('P');
		officialSite.addActionListener(this);
		officialSite.setActionCommand("officialSite");
		helpMenu.add(officialSite);

		techSupport=new JMenuItem("����֧��(S)",new ImageIcon("img/support.png"));
		techSupport.setMnemonic('S');
		techSupport.addActionListener(this);
		techSupport.setActionCommand("techSupport");
		helpMenu.add(techSupport);
		helpMenu.addSeparator();
		aboutSoftware=new JMenuItem("����(A)",new ImageIcon("img/about.png"));
		aboutSoftware.setMnemonic('A');
		aboutSoftware.addActionListener(this);
		aboutSoftware.setActionCommand("aboutSoftware");
		helpMenu.add(aboutSoftware);
		return helpMenu;
	}

	@Override
	public void actionPerformed(ActionEvent event) {		
		if(event.getActionCommand().equalsIgnoreCase("helpContent") || event.getActionCommand().equalsIgnoreCase("helpToolButton")){
			String  helpfileurl="help.chm";   
			 try {
				Runtime.getRuntime().exec("hh"+" "+helpfileurl);
			}catch (IOException e) {
				return;
			}   
		}else if(event.getActionCommand().equalsIgnoreCase("officialSite")){
			String uriStr="http://hi.csdn.net/space-3820011.html";
			if(!Desktop.isDesktopSupported()){
				JOptionPane.showMessageDialog(null, "��֧�ִ����������,��ֱ�ӷ���http://hi.csdn.net/space-3820011.html");
				return;
			}
			Desktop desktop=Desktop.getDesktop();
			try{
				URI uri=new URI(uriStr);
				desktop.browse(uri);
			}catch(URISyntaxException e){
				return;
			}catch(IOException e){
				return;
			}
		}else if(event.getActionCommand().equalsIgnoreCase("techSupport")){
			String uriStr="mailto://xinxinli1234@hotmail.com";
			if(!Desktop.isDesktopSupported()){
				JOptionPane.showMessageDialog(null, "��֧�ִ�Ĭ���ʼ��ͻ���,�뷢�ʼ���:xinxinli1234@hotmail.com");
				return;
			}
			Desktop desktop=Desktop.getDesktop();
			try{
				URI uri=new URI(uriStr);
				desktop.browse(uri);
			}catch(URISyntaxException e){
				return;
			}catch(IOException e){
				return;
			}
		}else if(event.getActionCommand().equalsIgnoreCase("aboutSoftware")){
			new About();
		}
	}
}
class About extends JDialog implements ActionListener{
	private static final long serialVersionUID = 4745639077829122095L;
	private JTabbedPane tabbedPane;
	private JPanel softPane;
	private JPanel infoPane;
	private JPanel rightPane;
	private JPanel yescancelPanel;
	private JButton yesButton;
	private JButton cancelButton;
	private JLabel softInfoLabel;
	private JTextArea rightArea;
	private JTextArea infoArea;
	private JScrollPane infoScrollPane;
	private String copyright;
	private JScrollPane rightScrollPane;
	public void initPanel(){
		softPane=new JPanel();
		softPane.setLayout(new BorderLayout());
		ImageIcon logo=new ImageIcon("img/logo.png");
		softInfoLabel=new JLabel(logo);	 
		softPane.add(softInfoLabel,BorderLayout.CENTER);

		infoPane=new JPanel();
		infoPane.setLayout(new BorderLayout());
		infoArea=new JTextArea();
		infoArea.setEditable(false);
		infoScrollPane=new JScrollPane(infoArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		infoArea.append("�û���Ϣ:\n"+
				"�û���Ŀ¼:"+System.getProperty("user.home")+"\n"+
				"�û���:"	+System.getProperty("user.name")+"\n\n"+
				"Java�������Ϣ:\n"+
				"Java��װĿ¼:"+System.getProperty("java.home")+"\n"+
				"Java�汾��:"+System.getProperty("java.version")+"\n"+
				"Java���������:"+System.getProperty("java.vm.name")+"\n\n"+
				"ϵͳ��Ϣ:\n"+
				"ϵͳ��ϵ�ṹ:"+System.getProperty("os.arch")+"\n"+
				"ϵͳ����:"+System.getProperty("os.name")+"\n"+
				"ϵͳ�汾��:"+System.getProperty("os.version")
		);
		infoPane.add(infoScrollPane,BorderLayout.CENTER);
		rightPane=new JPanel();
		rightPane.setLayout(new BorderLayout());
		rightArea=new JTextArea();
		rightArea.setAutoscrolls(true);
		rightArea.setEditable(false);
		rightScrollPane=new JScrollPane(rightArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		rightArea.setText(copyright);
		rightPane.add(rightScrollPane,BorderLayout.CENTER);
		yescancelPanel=new JPanel();
	}
	public About(){
		this.setTitle("���� OpenFTP");
		this.setLayout(new BorderLayout());
		copyright="���Э��:\r\n������������������ѭGPL��Ȩ�����������\r\n��װĿ¼�����Licence.txt�ļ���\r\n\r\n" +
		"��Ȩ����:\r\n�������ѭ GPL Э�鷢�����κεĸ��ƣ��޸ģ�\r\n��������ѭGPLЭ�顣��������İ�װĿ¼����\r\n��Licence.txt�ļ���\r\n\r\n" +
		"�̱�ע��:\r\nOpenFTPΪ������ע����̱�,��������Ȩ����"
		;
		initPanel();
		yesButton=new JButton("ȷ��(O)");
		yesButton.setMnemonic('O');
		yesButton.addActionListener(this);
		cancelButton=new JButton("ȡ��(N)");
		cancelButton.setMnemonic('N');
		cancelButton.addActionListener(this);
		yescancelPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		yescancelPanel.add(yesButton);
		yescancelPanel.add(cancelButton);

		tabbedPane=new JTabbedPane();
		tabbedPane.addTab("����", softPane);
		tabbedPane.addTab("��Ϣ", infoPane);
		tabbedPane.addTab("��Ȩ", rightPane);
		this.getContentPane().add(tabbedPane,BorderLayout.CENTER);
		this.getContentPane().add(yescancelPanel,BorderLayout.SOUTH);
		this.setIconImage(new ImageIcon("img/about.png").getImage());
		this.setModal(true);
		this.setSize(320,400);
		Common.getPos(320,400);
		this.setLocation(Common.posX,Common.posY);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		this.dispose();
	}
}