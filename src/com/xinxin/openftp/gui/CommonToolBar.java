package com.xinxin.openftp.gui;
 
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.border.MatteBorder;
 
public class CommonToolBar {
	private JToolBar toolsToolBar; // ��ݹ��߹�����
	private JButton addTabToolButton;
	private JButton lcdToolButton;
	private JButton copyFileToolButton;
	private JButton pasteFileToolButton;
	private JButton openShellToolButton;
	private JButton showClientInfoToolButton;
	private JButton showServerInfoToolButton;
	private JButton helpToolButton;
	private JButton snaker;
	public JToolBar initCommonToolBar(){
		// ���й�����
		toolsToolBar = new JToolBar("���湤����");
		toolsToolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolsToolBar.setOrientation(JToolBar.HORIZONTAL);
		addTabToolButton = new JButton(new ImageIcon("img/addtab.png"));
		 
		lcdToolButton=new JButton(new ImageIcon("img/curdir.png"));
		helpToolButton = new JButton(new ImageIcon("img/help.png"));
		
		addTabToolButton.setToolTipText("����±�ǩ");
		addTabToolButton.setActionCommand("addTabToolButton");
		addTabToolButton.setActionCommand("addTabToolButton");
	 
		lcdToolButton.setToolTipText("����Ϊ��ǰ·��");
		lcdToolButton.setActionCommand("lcdToolButton");
		copyFileToolButton=new JButton(new ImageIcon("img/copy.png"));
		copyFileToolButton.setActionCommand("copyFileToolButton");
		copyFileToolButton.setToolTipText("�����ļ�");
		pasteFileToolButton=new JButton(new ImageIcon("img/paste.png"));
		pasteFileToolButton.setActionCommand("pasteFileToolButton");
		pasteFileToolButton.setToolTipText("ճ���ļ�");
		openShellToolButton=new JButton(new ImageIcon("img/openShell.png"));
		openShellToolButton.setToolTipText("FTP�ͻ��������й���");
		openShellToolButton.setActionCommand("openShellToolButton");
		showClientInfoToolButton=new JButton(new ImageIcon("img/client.png"));
		showClientInfoToolButton.setToolTipText("��ʾ�ͻ�����Ϣ");
		showClientInfoToolButton.setActionCommand("showClientInfoToolButton");
		showServerInfoToolButton=new JButton(new ImageIcon("img/server.png"));
		showServerInfoToolButton.setToolTipText("��ʾ��������Ϣ");
		showServerInfoToolButton.setActionCommand("showServerInfoToolButton");
		helpToolButton.setToolTipText("����");
		helpToolButton.setActionCommand("helpToolButton");
		toolsToolBar.add(addTabToolButton);
		toolsToolBar.add(lcdToolButton);
		toolsToolBar.add(copyFileToolButton);
		toolsToolBar.add(pasteFileToolButton);
		toolsToolBar.add(openShellToolButton);
		toolsToolBar.add(showClientInfoToolButton);
		toolsToolBar.add(showServerInfoToolButton);
		toolsToolBar.add(helpToolButton);
		snaker=new JButton(new ImageIcon("img/snaker.png"));
		snaker.setToolTipText("������һ����...һ��ΰ���̫��������...");
		snaker.addMouseListener(new Funny());
		toolsToolBar.add(snaker);
		return toolsToolBar;
	}
	
	class Funny extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			 new Snake();
		}
	}
	class Snake {
		private JFrame f;
		private JLabel label;
		public Snake(){
			MatteBorder b=new MatteBorder(60,200,60,200,new ImageIcon("img/bg.png"));
			label=new JLabel(new ImageIcon("img/sun.png"));
			label.setBorder(b);
			f=new JFrame();
			f.getContentPane().add(label);
			f.addMouseListener(new go());
			f.setExtendedState(JFrame.MAXIMIZED_BOTH);
			f.setUndecorated(true);
			f.setAlwaysOnTop(true);
			f.setVisible(true);
		}
		class go extends MouseAdapter{
			public void mouseClicked(MouseEvent e){
				f.dispose();
			}
		}
	}
	public JButton getCopyFileToolButton() {
		return copyFileToolButton;
	}
	public JButton getPasteFileToolButton() {
		return pasteFileToolButton;
	}
	public JButton getOpenShellToolButton() {
		return openShellToolButton;
	}
	public JButton getShowClientInfoToolButton() {
		return showClientInfoToolButton;
	}
	public JButton getShowServerInfoToolButton() {
		return showServerInfoToolButton;
	}
	public JToolBar getToolsToolBar() {
		return toolsToolBar;
	}
	public JButton getAddTabToolButton() {
		return addTabToolButton;
	}
	public JButton getLcdToolButton() {
		return lcdToolButton;
	}
	public JButton getHelpToolButton() {
		return helpToolButton;
	}
	
}
