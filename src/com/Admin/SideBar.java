package com.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SideBar extends JPanel
{
	private static final long serialVersionUID = 1L;
	JPanel panelMain=new JPanel();
	JPanel panelNorth=new JPanel();
	JPanel panelCenter=new JPanel();
	
	JButton btnSetup=new JButton("Setup");
	JButton btnSecurity=new JButton("Security");
	JButton btnTask=new JButton("Task");
	JButton btnReport=new JButton("Report");
	JButton btnOthers=new JButton("Others");
	
	JPanel panelSetup=new JPanel();
	JPanel panelSecurity=new JPanel();
	JPanel panelTask=new JPanel();
	JPanel panelReport=new JPanel();
	JPanel panelOthers=new JPanel();	
	
	JSplitPane splitSetup=new JSplitPane(JSplitPane.VERTICAL_SPLIT,btnSetup,null);
	JSplitPane splitSecurity=new JSplitPane(JSplitPane.VERTICAL_SPLIT,btnSecurity,null);
	JSplitPane splitTask=new JSplitPane(JSplitPane.VERTICAL_SPLIT,btnTask,null);
	JSplitPane splitReport=new JSplitPane(JSplitPane.VERTICAL_SPLIT,btnReport,null);
	JSplitPane splitOthers=new JSplitPane(JSplitPane.VERTICAL_SPLIT,btnOthers,panelOthers);

	JLabel lbl_category=new JLabel("Category");
	JLabel lbl_supplierInfo=new JLabel("Supplier");
	JLabel lbl_productInfo=new JLabel("Product Info");
	JLabel lbl_clientInfo=new JLabel("Client Info");

	JButton btnCategory=new JButton(new ImageIcon("images/category.png"));
	JButton btnSupplier=new JButton(new ImageIcon("images/supplier.png"));
	JButton btnProduct=new JButton(new ImageIcon("images/product.png"));
	JButton btnClient=new JButton(new ImageIcon("images/client.png"));
	
	JLabel lbl_newUser=new JLabel("New User");
	JLabel lbl_changePass=new JLabel("Change Password");
	JButton btnNewUser=new JButton(new ImageIcon("images/newuser.png"));
	JButton btnChangePassword=new JButton(new ImageIcon("images/changepassword.png"));
	
	JLabel lblOpeningStock=new JLabel("Opening Stock");
	JLabel lblPurchaseReceipt=new JLabel("Purchase Receipt");
	JLabel lblReturnToSupplier=new JLabel("Return To Supplier");
	JLabel lblSalesEntry=new JLabel("Sales Entry");
	JLabel lblSalesReturn=new JLabel("Sales Return");
	JLabel lblWastageOrBroken=new JLabel("Wastage or Broken");
	
	JButton btnOpeningStock =new JButton(new ImageIcon("images/stock.png"));
	JButton btnPurchaseReceipt=new JButton(new ImageIcon("images/autoship48.png"));
	JButton btnReturnToSupplier=new JButton(new ImageIcon("images/returnToSupplier.png"));
	JButton btnSalesEntry=new JButton(new ImageIcon("images/salesEntry.png"));
	JButton btnSalesReturn=new JButton(new ImageIcon("images/salerReturn.png"));
	JButton btnWastageOrBroken=new JButton(new ImageIcon("images/wastageOrBroken.png"));
	
	JLabel lblSetUpR = new JLabel("Setup Report");
	JLabel lblTask = new JLabel("Task Report");
	JButton btnSetUpR = new JButton(new ImageIcon("images/Setup.png"));
	JButton btnTaskR = new JButton(new ImageIcon("images/Note48.png"));
	
	JLabel lbl_update=new JLabel("Mail");
	JLabel lbl_logoff=new JLabel("Log off");
	JLabel lbl_exit=new JLabel("Exit");
	JButton btnUpdate=new JButton(new ImageIcon("images/update1.png"));
	JButton btnLogOff=new JButton(new ImageIcon("images/logout-button-hi.png"));
	JButton btnExit=new JButton(new ImageIcon("images/exit.png"));
	
	GridBagConstraints c=new GridBagConstraints();
	
	public SideBar()
	{
		init();
		cmp();
		action();
	}
	public void action()
	{
		btnSetup.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				splitSetup.setBottomComponent(panelSetup);
				splitSecurity.remove(panelSecurity);
				splitTask.remove(panelTask);
				splitReport.remove(panelReport);
				splitOthers.remove(panelOthers);
				revalidate();
			}
		});
		btnSecurity.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				splitSetup.remove(panelSetup);
				splitSecurity.setBottomComponent(panelSecurity);
				splitTask.remove(panelTask);
				splitReport.remove(panelReport);
				splitOthers.remove(panelOthers);
				revalidate();
			}
		});
		btnTask.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				splitSetup.remove(panelSetup);
				splitSecurity.remove(panelSecurity);
				splitTask.setBottomComponent(panelTask);
				splitReport.remove(panelReport);
				splitOthers.remove(panelOthers);
				revalidate();
			}
		});
		btnReport.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				splitSetup.remove(panelSetup);
				splitSecurity.remove(panelSecurity);
				splitTask.remove(panelTask);
				splitReport.setBottomComponent(panelReport);
				splitOthers.remove(panelOthers);
				revalidate();
			}
		});
		btnOthers.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				splitSetup.remove(panelSetup);
				splitSecurity.remove(panelSecurity);
				splitTask.remove(panelTask);
				splitReport.remove(panelReport);
				splitOthers.setBottomComponent(panelOthers);
				revalidate();
			}
		});
		btnUpdate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
			}
		});
	}
	private void panelOthersWork() 
	{
		panelOthers.setLayout(new GridBagLayout());
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(2, 2, 2, 2);
		c.fill=GridBagConstraints.BOTH;
		panelOthers.add(btnUpdate,c);
		c.gridx=0;
		c.gridy=1;
		c.insets=new Insets(2, 35, 2, 2);
		panelOthers.add(lbl_update,c);
		c.gridx=0;
		c.gridy=2;
		c.insets=new Insets(2, 2, 2, 2);
		panelOthers.add(btnLogOff,c);
		c.gridx=0;
		c.gridy=3;
		c.insets=new Insets(2, 35, 2, 2);
		panelOthers.add(lbl_logoff,c);
		c.gridx=0;
		c.gridy=4;
		c.insets=new Insets(2, 2, 2, 2);
		panelOthers.add(btnExit,c);
		c.gridx=0;
		c.gridy=5;
		c.insets=new Insets(2, 45, 2, 2);
		panelOthers.add(lbl_exit,c);
	}
	private void panelReportWork() 
	{
		panelReport.setLayout(new GridBagLayout());
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(2, 2, 2, 2);
		c.fill=GridBagConstraints.BOTH;
		panelReport.add(btnSetUpR,c);
		c.gridx=0;
		c.gridy=1;
		c.insets=new Insets(2, 25, 2, 2);
		panelReport.add(lblSetUpR,c);
	}
	private void panelTaskWork() 
	{
		panelTask.setLayout(new GridBagLayout());
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(2, 2, 2, 2);
		c.fill=GridBagConstraints.BOTH;
		panelTask.add(btnPurchaseReceipt,c);
		c.gridx=0;
		c.gridy=1;
		c.insets=new Insets(2, 15, 2, 2);
		panelTask.add(lblPurchaseReceipt,c);
		c.gridx=0;
		c.gridy=2;
		c.insets=new Insets(2, 2, 2, 2);
		panelTask.add(btnReturnToSupplier,c);
		c.gridx=0;
		c.gridy=3;
		c.insets=new Insets(2, 5, 2, 2);
		panelTask.add(lblReturnToSupplier,c);
		c.gridx=0;
		c.gridy=4;
		c.insets=new Insets(2, 2, 2, 2);
		panelTask.add(btnSalesEntry,c);
		c.gridx=0;
		c.gridy=5;
		c.insets=new Insets(2, 42, 2, 2);
		panelTask.add(lblSalesEntry,c);
		c.gridx=0;
		c.gridy=6;
		c.insets=new Insets(2, 2, 2, 2);
		panelTask.add(btnSalesReturn,c);
		c.gridx=0;
		c.gridy=7;
		c.insets=new Insets(2, 25, 2, 2);
		panelTask.add(lblSalesReturn,c);
		c.gridx=0;
		c.gridy=8;
		c.insets=new Insets(2, 2, 2, 2);
		panelTask.add(btnWastageOrBroken,c);
		c.gridx=0;
		c.gridy=9;
		c.insets=new Insets(2, 10, 2, 2);
		panelTask.add(lblWastageOrBroken,c);
		
	}
	private void panelSecurityWork() 
	{
		panelSecurity.setLayout(new GridBagLayout());
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(2, 2, 2, 2);
		c.fill=GridBagConstraints.BOTH;
		panelSecurity.add(btnChangePassword,c);
		c.gridx=0;
		c.gridy=1;
		c.insets=new Insets(2, 2, 2, 2);
		panelSecurity.add(lbl_changePass,c);
		c.gridx=0;
		c.gridy=2;
		c.insets=new Insets(2, 2, 2, 2);
		panelSecurity.add(btnNewUser,c);
		c.gridx=0;
		c.gridy=3;
		c.insets=new Insets(2, 30, 2, 2);
		panelSecurity.add(lbl_newUser,c);
	}
	private void panelSetupWork() 
	{
		panelSetup.setLayout(new GridBagLayout());
		c.gridx=0;
		c.gridy=0;
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(2, 2, 2, 2);
		panelSetup.add(btnCategory,c);
		c.gridx=0;
		c.gridy=1;
		c.insets=new Insets(2, 30, 2, 2);
		panelSetup.add(lbl_category,c);
		
		c.gridx=0;
		c.gridy=2;
		c.insets=new Insets(2, 2, 2, 2);
		panelSetup.add(btnSupplier,c);
		c.gridx=0;
		c.gridy=3;
		c.insets=new Insets(2, 2, 2, 2);
		panelSetup.add(lbl_supplierInfo,c);
		
		c.gridx=0;
		c.gridy=4;
		c.insets=new Insets(2, 2, 2, 2);
		panelSetup.add(btnClient,c);
		c.gridx=0;
		c.gridy=5;
		c.insets=new Insets(2, 2, 2, 2);
		panelSetup.add(lbl_clientInfo,c);
		
		c.gridx=0;
		c.gridy=6;
		c.insets=new Insets(2, 2, 2, 2);
		panelSetup.add(btnProduct,c);
		c.gridx=0;
		c.gridy=7;
		c.insets=new Insets(2, 2, 2, 2);
		panelSetup.add(lbl_productInfo,c);
		c.gridx=0;
		c.gridy=8;
		c.insets=new Insets(2, 2, 2, 2);
		panelSetup.add(btnOpeningStock,c);		
		c.gridx=0;
		c.gridy=9;
		c.insets=new Insets(2, 30, 2, 2);
		panelSetup.add(lblOpeningStock,c);
	}
	public void cmp()
	{
		add(panelMain);
		panelMain.setLayout(new BorderLayout());
		Box box=Box.createVerticalBox();
		panelMain.add(box,BorderLayout.NORTH);
		box.add(splitSetup);
		splitSetup.setDividerSize(0);
		box.add(splitTask);
		splitTask.setDividerSize(0);
		box.add(splitSecurity);
		splitSecurity.setDividerSize(0);
		box.add(splitReport);
		splitReport.setDividerSize(0);
		box.add(splitOthers);
		splitOthers.setDividerSize(0);
		panelSetupWork();
		panelSecurityWork();
		panelTaskWork();
		panelReportWork();
		panelOthersWork();
		btnClient.setRolloverIcon(new ImageIcon("images/client.png"));
		btnProduct.setRolloverIcon(new ImageIcon("images/product.png"));
		btnOpeningStock.setRolloverIcon(new ImageIcon("images/stock.png"));
		btnSupplier.setRolloverIcon(new ImageIcon("images/supplier.png"));
		btnCategory.setRolloverIcon(new ImageIcon("images/category.png"));
		btnChangePassword.setRolloverIcon(new ImageIcon("images/changepassword.png"));
		btnNewUser.setRolloverIcon(new ImageIcon("images/newuser.png"));
		btnPurchaseReceipt.setRolloverIcon(new ImageIcon("images/autoship48.png"));
		btnReturnToSupplier.setRolloverIcon(new ImageIcon("images/due1.png"));
		btnSalesEntry.setRolloverIcon(new ImageIcon("images/vendor1.png"));
		btnSalesReturn.setRolloverIcon(new ImageIcon("images/invoice1.png"));
		btnWastageOrBroken.setRolloverIcon(new ImageIcon("images/wastageOrBroken2.png"));
		btnSetUpR.setRolloverIcon(new ImageIcon("images/report1.png"));
		btnUpdate.setRolloverIcon(new ImageIcon("images/update1.png"));
		btnLogOff.setRolloverIcon(new ImageIcon("images/logoff1.png"));
		btnExit.setRolloverIcon(new ImageIcon("images/exit1.png"));
	}
	public void init()
	{
		setBackground(Color.gray);
		setLayout(new BorderLayout());
	}
}
