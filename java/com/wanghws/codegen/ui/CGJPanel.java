package com.wanghws.codegen.ui;
import com.wanghws.codegen.db.DataBase;
import com.wanghws.codegen.main.CodeGenerate;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPasswordField;

import javax.swing.JTextField;
import javax.swing.ListModel;

public class CGJPanel extends javax.swing.JPanel {
	private JTextField userName;
	private JList tableLIst;
	private JButton jButton1;
	private JLabel jLabel5;
	private JComboBox jComboBox2;
	private JLabel jLabel7;
	private JTextField jTextField2;
	private JButton dbButton;
	private JOptionPane jOptionPane1;
	private JTextField jTextField1;
	private JLabel jLabel6;
	private JPasswordField passWord;
	private JLabel jLabel4;
	private JComboBox jComboBox1;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JComboBox dbList;



	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		final JFrame frame = new JFrame();
		frame.getContentPane().add(new CGJPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);   
		frame.setTitle("代码生成器@wanghws");
		frame.addWindowListener(new WindowAdapter() {   
            public void windowClosing(WindowEvent e) {   
                int flag = JOptionPane.showConfirmDialog(frame.getContentPane(),"确定要退出吗",   
                        "exit", JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);   
                if(JOptionPane.YES_OPTION == flag){   
                	if(null!=CodeGenerate.getDataBase()){
                		CodeGenerate.getDataBase().close();
                	}
                    System.exit(0);   
                }else{   
                    return;   
                }   
            }   
        }); 
		frame.pack();
		frame.setVisible(true);
	}
	
	public CGJPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(636, 425));
			
			{
				dbButton = new JButton();
				this.add(dbButton, new AnchorConstraint(236, 703, 304, 552, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				dbButton.setText("\u8fde\u63a5");
				dbButton.setPreferredSize(new java.awt.Dimension(96, 29));
				dbButton.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent actionEvent) {
						 String obj = (String)dbList.getSelectedItem();
						 
						 try{
							 String user = userName.getText();
							 String pass = passWord.getText();
							 if(obj.equals("MySql")){
								
								 DataBase db = CodeGenerate.getMySQL(user,pass);
								 List<String> list = db.getDBList();
								 ComboBoxModel jComboBox1Model = new DefaultComboBoxModel(list.toArray());
								 jComboBox1.setModel(jComboBox1Model);
							 }else if(obj.equals("SqlServer")){
								 DataBase db = CodeGenerate.getSqlServer(user, pass);
								 List<String> list = db.getDBList();
								 ComboBoxModel jComboBox1Model = new DefaultComboBoxModel(list.toArray());
								 jComboBox1.setModel(jComboBox1Model);
							 }else{
								 jOptionPane1.showMessageDialog(null,"错误的数据库","囧",JOptionPane.ERROR_MESSAGE);
							 }
							 
						 }catch(Exception e){
							 jOptionPane1.showMessageDialog(null,"连接失败:"+e.getMessage(),"囧",JOptionPane.ERROR_MESSAGE);
						 }
						 
						 
						 
		             }
				});
			}
			{
				jOptionPane1 = new JOptionPane();
				jOptionPane1.setVisible(false);
				this.add(jOptionPane1, new AnchorConstraint(290, 675, 502, 263, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			}
			{
				passWord = new JPasswordField();
				passWord.setText("123456");
				this.add(passWord, new AnchorConstraint(238, 543, 304, 280, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				passWord.setPreferredSize(new java.awt.Dimension(167, 28));
			}
			{
				jLabel2 = new JLabel();
				this.add(jLabel2, new AnchorConstraint(60, 233, 107, 89, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel2.setText("\u6570\u636e\u5e93\u7c7b\u578b");
				jLabel2.setPreferredSize(new java.awt.Dimension(72, 17));
			}
			{
				jLabel1 = new JLabel();
				this.add(jLabel1, new AnchorConstraint(149, 147, 196, 87, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel1.setText("\u8d26\u53f7");
				jLabel1.setPreferredSize(new java.awt.Dimension(38, 20));
			}
			
			{
				
				ListModel tableLIstModel = 
					new DefaultComboBoxModel(
							new String[]{});
				tableLIst = new JList();
				JScrollPane jScrollPane1 = new JScrollPane(tableLIst);
				jScrollPane1.setAutoscrolls(true);
				this.add(jScrollPane1, new AnchorConstraint(417, 865, 677, 281, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				tableLIst.setModel(tableLIstModel);
				jScrollPane1.setPreferredSize(new java.awt.Dimension(338, 108));
				
				
			}
			{
				userName = new JTextField();
				userName.setText("root");
				this.add(userName, new AnchorConstraint(142, 543, 210, 280, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				userName.setPreferredSize(new java.awt.Dimension(167, 29));
			}
			{
				jButton1 = new JButton();
				this.add(jButton1, new AnchorConstraint(876, 565, 942, 444, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton1.setText("OK");
				jButton1.setPreferredSize(new java.awt.Dimension(77, 28));
				jButton1.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent actionEvent) {
						 try{
							 Object [] objs = tableLIst.getSelectedValues();
							 if(null==objs||objs.length<=0){
								 throw new Exception("请选择表");
							 }
							 String dbName = (String)jComboBox1.getSelectedItem();
							 CodeGenerate.make(dbName,jTextField1.getText(), jTextField2.getText(), objs,jComboBox2.getSelectedItem().toString());
							 jOptionPane1.showMessageDialog(null,"操作成功","success",JOptionPane.INFORMATION_MESSAGE);
						 }catch(Exception e){
							 e.printStackTrace();
							 jOptionPane1.showMessageDialog(null,"操作失败:"+e.getMessage(),"囧",JOptionPane.ERROR_MESSAGE);
						 }
						 
		             }
				});
			}
			{
				ComboBoxModel dbListModel = 
					new DefaultComboBoxModel(
							new String[] { "MySql","SqlServer"  });
				dbList = new JComboBox();
				this.add(dbList, new AnchorConstraint(48, 543, 115, 281, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				dbList.setModel(dbListModel);
				dbList.setPreferredSize(new java.awt.Dimension(131, 24));
			}
			{
				jLabel3 = new JLabel();
				this.add(jLabel3, new AnchorConstraint(328, 235, 377, 88, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel3.setPreferredSize(new java.awt.Dimension(93, 21));
				jLabel3.setText("\u8bf7\u9009\u62e9\u6570\u636e\u5e93");
			}
			{
				ComboBoxModel jComboBox1Model = 
					new DefaultComboBoxModel(
							new String[] { "请选择", });
				jComboBox1 = new JComboBox();
				this.add(jComboBox1, new AnchorConstraint(318, 543, 384, 280, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jComboBox1.setModel(jComboBox1Model);
				jComboBox1.setPreferredSize(new java.awt.Dimension(167, 28));
				jComboBox1.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent actionEvent) {
						 if("comboBoxChanged".equals(actionEvent.getActionCommand())){
							 String dbName = (String)jComboBox1.getSelectedItem();
							 if("请选择".equals(dbName))return;
							 List<String> list = CodeGenerate.getDataBase().getTableList(dbName);
							 ListModel tableLIstModel = new DefaultComboBoxModel(list.toArray());
							 tableLIst.setModel(tableLIstModel);
						 }
						 //jOptionPane1.showMessageDialog(null,"操作成功","Information",JOptionPane.INFORMATION_MESSAGE);
		             }
				});
			}
			{
				jLabel4 = new JLabel();
				this.add(jLabel4, new AnchorConstraint(245, 147, 295, 88, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel4.setText("\u5bc6\u7801");
				jLabel4.setPreferredSize(new java.awt.Dimension(37, 21));
			}
			{
				jLabel5 = new JLabel();
				this.add(jLabel5, new AnchorConstraint(417, 171, 465, 89, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel5.setText("\u9009\u62e9\u8868");
				jLabel5.setPreferredSize(new java.awt.Dimension(41, 17));
			}
			{
				jLabel6 = new JLabel();
				this.add(jLabel6, new AnchorConstraint(780, 170, 827, 87, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel6.setText("\u8f93\u51fa\u8def\u5f84");
				jLabel6.setPreferredSize(new java.awt.Dimension(53, 20));
			}
			{
				jTextField1 = new JTextField();
				this.add(jTextField1, new AnchorConstraint(780, 543, 848, 280, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jTextField1.setPreferredSize(new java.awt.Dimension(167, 29));
				jTextField1.setText("D:\\");
			}
			{
				jTextField2 = new JTextField();
				this.add(jTextField2, new AnchorConstraint(700, 543, 768, 280, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jTextField2.setText("com.comb.project");
				jTextField2.setPreferredSize(new java.awt.Dimension(167, 29));
			}
			{
				jLabel7 = new JLabel();
				this.add(jLabel7, new AnchorConstraint(707, 170, 754, 87, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel7.setText("\u5305\u8def\u5f84");
				jLabel7.setPreferredSize(new java.awt.Dimension(53, 20));
			}
			{
				ComboBoxModel jComboBox2Model = 
					new DefaultComboBoxModel(
							new String[] { "SSH","DBHelper" , "WF"});
				jComboBox2 = new JComboBox();
				this.add(jComboBox2, new AnchorConstraint(697, 703, 763, 562, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jComboBox2.setModel(jComboBox2Model);
				jComboBox2.setPreferredSize(new java.awt.Dimension(90, 28));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public JTextField getUserName() {
		return userName;
	}
	
	public JList getTableLIst() {
		return tableLIst;
	}

}
