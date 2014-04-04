package com.xinxin.openftp.gui;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
public class EditMenu {
	private JMenu editMenu; // �༭
	// ����༭�˵�������Ӳ˵�
	private JMenuItem copyFile; // �����ļ�(C) CTRL+C
	private JMenuItem pasteFile; // ճ���ļ�(V) CTRL+V
	private JMenuItem searchFile; // �����ļ�(F) CTRL+F
	private JMenuItem selectAll; // ȫ��ѡ��(A) CTRL+A
	private JMenuItem reverseSelect; // ����ѡ��(I) CTRL+I
	private JMenuItem paramsConfig; // ��������(R) F8
	private ConfigMenuItem configMenuItem;
	public JMenu initEditMenu() {
		editMenu = new JMenu("�༭(E)");
		editMenu.setMnemonic('E');
		copyFile = new JMenuItem("����(C)", new ImageIcon("img/copy.png"));
		copyFile
				.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_DOWN_MASK|InputEvent.SHIFT_DOWN_MASK));
		copyFile.setMnemonic('C');
		copyFile.setActionCommand("copyFile");
		editMenu.add(copyFile);
		pasteFile = new JMenuItem("ճ��(P)", new ImageIcon("img/paste.png"));
		pasteFile.setAccelerator(KeyStroke
				.getKeyStroke('V', InputEvent.CTRL_DOWN_MASK|InputEvent.SHIFT_DOWN_MASK));
		pasteFile.setMnemonic('P');
		pasteFile.setActionCommand("pasteFile");
		editMenu.add(pasteFile);
		editMenu.addSeparator();
		searchFile = new JMenuItem("����(F)", new ImageIcon("img/find.png"));
		searchFile.setAccelerator(KeyStroke.getKeyStroke('F',InputEvent.CTRL_DOWN_MASK));
		searchFile.setMnemonic('F');
		searchFile.setActionCommand("searchFile");
		editMenu.add(searchFile);
	 
		editMenu.addSeparator();
		selectAll = new JMenuItem("ȫ��ѡ��(A)");
		selectAll.setAccelerator(KeyStroke
				.getKeyStroke('A', InputEvent.CTRL_DOWN_MASK));
		selectAll.setMnemonic('A');
		selectAll.setActionCommand("selectAll");
		editMenu.add(selectAll);
		reverseSelect = new JMenuItem("����ѡ��(I)");
		reverseSelect.setAccelerator(KeyStroke.getKeyStroke('I',
				InputEvent.CTRL_DOWN_MASK));
		reverseSelect.setMnemonic('I');
		reverseSelect.setActionCommand("reverseSelect");
		editMenu.add(reverseSelect);
		editMenu.addSeparator();
		configMenuItem=new ConfigMenuItem();
		paramsConfig = new JMenuItem("��������(R)", new ImageIcon("img/config.png"));
		paramsConfig.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2,InputEvent.CTRL_DOWN_MASK));
		paramsConfig.setMnemonic('R');
		paramsConfig.setActionCommand("paramsConfig");
		paramsConfig.addActionListener(configMenuItem);
		editMenu.add(paramsConfig);
		return editMenu;
	}
	public JMenuItem getCopyFile() {
		return copyFile;
	}
	public JMenuItem getPasteFile() {
		return pasteFile;
	}
	public JMenuItem getSearchFile() {
		return searchFile;
	}
	public JMenuItem getSelectAll() {
		return selectAll;
	}
	public JMenuItem getReverseSelect() {
		return reverseSelect;
	}
	public ConfigMenuItem getConfigMenuItem() {
		return configMenuItem;
	}
	
}
