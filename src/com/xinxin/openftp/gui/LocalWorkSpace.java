package com.xinxin.openftp.gui;

import java.awt.BorderLayout;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
public class LocalWorkSpace {
	// ����Ŀ¼���
	private JPanel localDirPane;
	private JTabbedPane localDirTabbedPane;
	private JFileChooser fileChooser;
	// ��������Ŀ¼���
	private JPanel createLocalDirTab() {
		JPanel dir = new JPanel(new BorderLayout()); // Ŀ¼���
		fileChooser=new JFileChooser();
		fileChooser.setControlButtonsAreShown(false);
		fileChooser.setDragEnabled(true);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setMultiSelectionEnabled(true);
		dir.add(fileChooser, BorderLayout.CENTER);
		return dir;
	}
	public JPanel initLocalDirPane() {
		localDirPane = new JPanel();
		localDirPane.setLayout(new BorderLayout());
		JPanel dir = this.createLocalDirTab();
		localDirTabbedPane = new JTabbedPane();
		localDirTabbedPane.addTab("����", dir);
		this.localDirTabbedPane.setName("local");
		localDirPane.add(localDirTabbedPane, BorderLayout.CENTER);
		return localDirPane;
	}
	public JFileChooser getFileChooser() {
		return fileChooser;
	}
}
