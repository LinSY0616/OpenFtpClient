package com.xinxin.openftp.gui;

import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class LoginInfoToolBar implements ItemListener {
	private JToolBar loginInfoToolBar; // ��¼��Ϣ������
	// ��¼��Ϣ������������Ŀ
	private JTextField name = new JTextField(15); // �û���
	private JPasswordField pass = new JPasswordField(15); // ����
	private JComboBox addressList = new JComboBox(); // ��������ַ
	private JTextField port = new JTextField("21", 4); // �������˿�
	private JCheckBox anony = new JCheckBox(); // ������¼
	private JButton connectButton;
	public JToolBar initLoginInfoToolBar(){
		// ��¼��Ϣ������
		JLabel username = new JLabel("�û���:");
		JLabel password = new JLabel("����:");
		JLabel remoteport = new JLabel("�˿�:");
		JLabel anonyLogin = new JLabel("������¼");
		JLabel addressLabel = new JLabel("��ַ:");
		
		anony.addItemListener(this);
		pass.setEchoChar('*');
		connectButton = new JButton(new ImageIcon("img/connect.png"));
		connectButton.setActionCommand("connectButton");
		connectButton.setToolTipText("���ӷ�����");

		addressList.setEditable(true);
		addressList.configureEditor(addressList.getEditor(), "");
		addressList.addItem(new String("jinxinxin-pc"));
		addressList.addItem(new String("192.168.254.130"));
		addressList.addItem(new String("ftp.sjtu.edu.cn"));
		loginInfoToolBar = new JToolBar("�û���¼��Ϣ������");
		loginInfoToolBar.setOrientation(JToolBar.HORIZONTAL);
		loginInfoToolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		loginInfoToolBar.add(username);
		loginInfoToolBar.add(name);
		loginInfoToolBar.add(password);
		loginInfoToolBar.add(pass);
		loginInfoToolBar.add(remoteport);
		loginInfoToolBar.add(port);
		loginInfoToolBar.add(anony);
		loginInfoToolBar.add(anonyLogin);
		loginInfoToolBar.addSeparator();
		loginInfoToolBar.add(addressLabel);
		loginInfoToolBar.add(addressList);
		loginInfoToolBar.add(connectButton);
		return loginInfoToolBar;
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == anony) {
			if (anony.isSelected()) {
				this.name.setEnabled(false);
				this.pass.setEnabled(false);
			} else if (!anony.isSelected()) {
				this.name.setEnabled(true);
				this.pass.setEnabled(true);
			}
		} 
	}
	public JButton getConnectButton() {
		return connectButton;
	}
	public JTextField getName() {
		return name;
	}
	public JPasswordField getPass() {
		return pass;
	}
	public JComboBox getAddressList() {
		return addressList;
	}
	public JTextField getPort() {
		return port;
	}
	public JCheckBox getAnony() {
		return anony;
	}
	
}
