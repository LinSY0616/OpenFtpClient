package com.xinxin.openftp.gui;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class FileMenu {
	private JMenu fileMenu; // �ļ�
	// �����ļ��˵�������Ӳ˵�
	private JMenuItem newTab; // �½���ǩ(T) CTRL+T
	private JMenuItem connect; // ����(C)
	private JMenuItem disconnect; // �Ͽ�����(D) CTRL+D
	private JMenuItem close; // �ر�(L)

	/* ��ʼ���˵���Ŀ */
	public JMenu initFileMenu() {
		fileMenu = new JMenu("�ļ�(F)");
		fileMenu.setMnemonic('F');
		newTab = new JMenuItem("�½���ǩ(T)", new ImageIcon("img/addtab.png"));
		newTab.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,InputEvent.CTRL_DOWN_MASK));
		newTab.setMnemonic('T');
		newTab.setActionCommand("addNewTab");
		fileMenu.add(newTab);
		fileMenu.addSeparator();

		connect = new JMenuItem("����(C)", new ImageIcon("img/connect.png"));
		connect.setMnemonic('C');
		connect.setActionCommand("connect");
		fileMenu.add(connect);
 

		disconnect = new JMenuItem("�Ͽ�����(D)", new ImageIcon("img/discon.png"));
		disconnect.setAccelerator(KeyStroke.getKeyStroke('D',InputEvent.CTRL_DOWN_MASK));
		disconnect.setMnemonic('D');
		disconnect.setActionCommand("disconnect");
		fileMenu.add(disconnect);
		fileMenu.addSeparator();

		close = new JMenuItem("�ر�(Z)", new ImageIcon("img/exit.png"));
		close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
		close.setMnemonic('Z');
		close.setActionCommand("close");
		fileMenu.add(close);
		return fileMenu;
	}

	public JMenuItem getNewTab() {
		return newTab;
	}

	public JMenuItem getConnect() {
		return connect;
	}
 
	public JMenuItem getDisconnect() {
		return disconnect;
	}

	public JMenuItem getClose() {
		return close;
	}
	
}
