package com.xinxin.openftp.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import com.xinxin.openftp.ftpclient.FTPClientImpl;

public class RemoteWorkSpaceTab extends JPanel{
	private static final long serialVersionUID = -252595178607307278L;
	// Զ��Ŀ�����
	private JButton connButton;
	private JButton closeButton;
	private JButton remoteLastDir;
	private JButton remoteRefresh;
	private JComboBox remoteDirSelect;
	private JTable remoteFileTable;
	private String[][] remoteFileNames;
	private JTextArea returnInfoArea;
	private JPopupMenu popMenu;
	private FTPClientImpl ftpClient;
	public RemoteWorkSpaceTab(){
		this.setLayout(new BorderLayout());
		JPanel dirNorth = new JPanel(new BorderLayout());
		JToolBar northToolBar = new JToolBar();
		northToolBar.setFloatable(false);
		connButton = new JButton(new ImageIcon("img/conn.png"));
		connButton.setToolTipText("���ӷ�����");
		connButton.setActionCommand("connButton");
		closeButton=new JButton(new ImageIcon("img/close.png"));
		closeButton.setToolTipText("�Ͽ�����");
		closeButton.setActionCommand("closeButton");
		remoteLastDir = new JButton(new ImageIcon("img/lastdir.png"));
		remoteLastDir.setToolTipText("��һ��");
		remoteLastDir.setActionCommand("lastDir");
		remoteRefresh = new JButton(new ImageIcon("img/reload.png"));
		remoteRefresh.setToolTipText("ˢ��");
		remoteRefresh.setActionCommand("remoteRefresh");
		JLabel label = new JLabel(" Զ��·��: ");
		remoteDirSelect = new JComboBox();
		remoteDirSelect.addItem("");
		remoteDirSelect.setEditable(true);
		northToolBar.add(connButton);
		northToolBar.add(closeButton);
		northToolBar.add(remoteLastDir);
		northToolBar.add(remoteRefresh);
		northToolBar.addSeparator();
		northToolBar.add(label);
		northToolBar.add(remoteDirSelect);
		dirNorth.add(northToolBar);

		JPanel dirCenter = new JPanel(new BorderLayout());
		
		String [] tableHeaders=new String[]{"","����","�޸�����","����","Ȩ��"};
		remoteFileNames=new String[][]{};
		remoteFileTable=new JTable(remoteFileNames,tableHeaders);
		remoteFileTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		remoteFileTable.setAutoscrolls(true);
		JScrollPane scrollTable=new JScrollPane(remoteFileTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		dirCenter.add(scrollTable);
		
		JPanel dirSouth = new JPanel(new BorderLayout());
		returnInfoArea = new JTextArea(10, 10);
		popMenu=new JPopupMenu();
		JMenuItem copyText=new JMenuItem("����(C)",new ImageIcon("img/copy.png"));
		copyText.setMnemonic('C');
		copyText.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_DOWN_MASK));
		copyText.setActionCommand("copyText");
		copyText.addActionListener(new TextAreaAction());
		JMenuItem pasteText=new JMenuItem("ճ��(P)",new ImageIcon("img/paste.png"));
		pasteText.setMnemonic('P');
		pasteText.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_DOWN_MASK));
		pasteText.setActionCommand("pasteText");
		pasteText.addActionListener(new TextAreaAction());
		JMenuItem clearText=new JMenuItem("���(E)",new ImageIcon("img/clearText.png"));
		clearText.setMnemonic('E');
		clearText.setActionCommand("clearText");
		clearText.addActionListener(new TextAreaAction());
		popMenu.add(copyText);
		popMenu.add(pasteText);
		popMenu.add(clearText);
		returnInfoArea.add(popMenu);
		//returnInfoArea.setEditable(false);
		returnInfoArea.addMouseListener(new TextAreaMouseEvent());
		 
		JScrollPane scrollPane = new JScrollPane(returnInfoArea,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		dirSouth.add(scrollPane);
		this.add(dirNorth, BorderLayout.NORTH);
		this.add(dirCenter, BorderLayout.CENTER);
		this.add(dirSouth,BorderLayout.SOUTH);
		
		ftpClient=new FTPClientImpl();//ÿ������Ӧһ��FTP�ͻ���ʵ��
		
	}
	public JButton getConnButton() {
		return connButton;
	}
	public JButton getRemoteLastDir() {
		return remoteLastDir;
	} 
	public JButton getRemoteRefresh() {
		return remoteRefresh;
	}
	public JComboBox getRemoteDirSelect() {
		return remoteDirSelect;
	}
	public JTextArea getReturnInfoArea() {
		return returnInfoArea;
	}
	
	class TextAreaMouseEvent extends MouseAdapter{
		public void mousePressed( MouseEvent event ) {  //������
			triggerEvent(event);  //����triggerEvent���������¼�
		}
		public void mouseReleased( MouseEvent event ) { //�ͷ����
			triggerEvent(event); 
		}
		private void triggerEvent(MouseEvent event) { //�����¼�
			if (event.isPopupTrigger()) //����ǵ����˵��¼�(����ƽ̨��ͬ���ܲ�ͬ)
				popMenu.show(event.getComponent(),event.getX(),event.getY());  //��ʾ�˵�
		}
	 }
	class TextAreaAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			 if(e.getActionCommand()=="copyText"){
				 returnInfoArea.copy();
			 }else if(e.getActionCommand()=="pasteText"){
				 returnInfoArea.paste();
			 }else if(e.getActionCommand()=="clearText"){
				 returnInfoArea.setText("");
			 }
		}
		
	}
	public FTPClientImpl getFtpClient() {
		return ftpClient;
	}
	public JButton getCloseButton() {
		return closeButton;
	}
	public JTable getRemoteFileTable() {
		return remoteFileTable;
	}
}
