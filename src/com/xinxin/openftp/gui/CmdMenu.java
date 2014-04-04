package com.xinxin.openftp.gui;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
public class CmdMenu {
	private JMenu cmdMenu; // ����

	private JMenuItem putFile;
	private JMenuItem getFile;
	
	private JMenuItem renameFile;
	private JMenuItem deleteFile;
	
	private JMenuItem renameDir;
	private JMenuItem makeDir;
	private JMenuItem removeDir;
	
	private JMenuItem mputFile;
	private JMenuItem mgetFile;
	private JMenuItem mdeleteFile;
	private JMenuItem openShell;
	public JMenu initCmdMenu(){
		cmdMenu=new JMenu("����(C)");
		cmdMenu.setMnemonic('C');
		
		putFile=new JMenuItem("�ϴ������ļ�(U)",new ImageIcon("img/putFile.png"));
		putFile.setMnemonic('U');
		putFile.setActionCommand("putFile");
		getFile=new JMenuItem("����Զ���ļ�(G)",new ImageIcon("img/getFile.png"));
		getFile.setMnemonic('G');
		getFile.setActionCommand("getFile");
		renameFile=new JMenuItem("������Զ���ļ�(R)",new ImageIcon("img/renameFile.png"));
		renameFile.setMnemonic('R');
		renameFile.setActionCommand("renameFile");
		deleteFile=new JMenuItem("ɾ��Զ���ļ�(D)",new ImageIcon("img/deleteFile.png"));
		deleteFile.setMnemonic('D');
		deleteFile.setActionCommand("deleteFile");
		renameDir=new JMenuItem("����Զ��Ŀ¼����(C)",new ImageIcon("img/renameDir.png"));
		renameDir.setMnemonic('C');
		renameDir.setActionCommand("renameDir");
		makeDir=new JMenuItem("�½�Զ��Ŀ¼(N)",new ImageIcon("img/makeDir.png"));
		makeDir.setMnemonic('N');
		makeDir.setActionCommand("makeDir");
		removeDir=new JMenuItem("ɾ��Զ��Ŀ¼(E)",new ImageIcon("img/removeDir.png"));
		removeDir.setMnemonic('E');
		removeDir.setActionCommand("removeDir");
		mputFile=new JMenuItem("�����ϴ������ļ�(W)");
		mputFile.setMnemonic('W');
		mputFile.setActionCommand("mputFile");
		mgetFile=new JMenuItem("��������Զ���ļ�(M)");
		mgetFile.setMnemonic('M');
		mgetFile.setActionCommand("mgetFile");
		mdeleteFile=new JMenuItem("����ɾ��Զ���ļ�(X)");
		mdeleteFile.setMnemonic('X');
		mdeleteFile.setActionCommand("mdeleteFile");
		openShell=new JMenuItem("FTP�ͻ��������й���(S)",new ImageIcon("img/openShell.png"));
		openShell.setMnemonic('S');
		openShell.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_DOWN_MASK));
		openShell.setActionCommand("openShell");
	
		cmdMenu.add(putFile);
		cmdMenu.add(getFile);
		cmdMenu.addSeparator();
		cmdMenu.add(renameFile);
		cmdMenu.add(deleteFile);
		cmdMenu.addSeparator();
		cmdMenu.add(renameDir);
		cmdMenu.add(makeDir);
		cmdMenu.add(removeDir);
		cmdMenu.addSeparator();
		cmdMenu.add(mputFile);
		cmdMenu.add(mgetFile);
		cmdMenu.add(mdeleteFile);
		cmdMenu.addSeparator();
		cmdMenu.add(openShell);
		return cmdMenu;
	}
	public JMenuItem getPutFile() {
		return putFile;
	}
	public JMenuItem getGetFile() {
		return getFile;
	}
	public JMenuItem getRenameFile() {
		return renameFile;
	}
	public JMenuItem getDeleteFile() {
		return deleteFile;
	}
	public JMenuItem getRenameDir() {
		return renameDir;
	}
	public JMenuItem getMakeDir() {
		return makeDir;
	}
	public JMenuItem getRemoveDir() {
		return removeDir;
	}
	public JMenuItem getMputFile() {
		return mputFile;
	}
	public JMenuItem getMgetFile() {
		return mgetFile;
	}
	public JMenuItem getMdeleteFile() {
		return mdeleteFile;
	}

	public JMenuItem getOpenShell() {
		return openShell;
	}
	
}
