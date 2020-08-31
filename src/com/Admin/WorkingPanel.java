package com.Admin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import com.Others.Exit;
import com.Others.LogOff;
import com.Others.Mail;
import com.Report.SetupReport;
import com.Report.TaskReport;
import com.Security.ChangePassword;
import com.Security.NewUser;
import com.Setup.Category;
import com.Setup.Clientinfo;
import com.Setup.Productinfo;
import com.Setup.Supplierinfo;
import com.Task.PurchaseReceipt;
import com.Task.PurchaseReturn;
import com.Task.SalesEntry;
import com.Task.SalesReturn;
import com.Task.OpeningStock;
import com.Task.WastageOrBroken;

public class WorkingPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	SessionBean sessionBean=new SessionBean();
	JFrame frm = new JFrame(); 
	JPanel panelWest = new JPanel();
	JPanel panelCenter = new JPanel();
	JPanel panelWestNorth = new JPanel();
	JPanel panelWestCenter = new JPanel();
	Category category;
	Supplierinfo supplier;
	Productinfo product;
	Clientinfo client;
	NewUser NewUser;
	ChangePassword ChangePassword = new ChangePassword();
	PurchaseReceipt purchase;
	PurchaseReturn purchaseReturn;
	SalesEntry salesEntry;
	SalesReturn salesReturn;
	OpeningStock openingStock;
	WastageOrBroken wastage;
	SetupReport SetupReport;
	TaskReport TaskReport = new TaskReport();
	Mail mail = new Mail();
	Exit exit = new Exit();
	LogOff logOff = new LogOff();
	JLabel lblCenter = new JLabel("Electronics Store Management");
	JLabel lblDeveloper = new JLabel("Developed By Sohail Mahmoud");
	JButton btnSetup = new JButton("Setup");
	JButton btnSecurity = new JButton("Security");
	JButton btnTask = new JButton("Task");
	JButton btnReport = new JButton("Report");
	JButton btnOthers = new JButton("Others");
	JPanel panelWestSetup = new JPanel();
	JPanel panelWestSecurity = new JPanel();
	JPanel panelWestTask = new JPanel();
	JPanel panelWestReport = new JPanel();
	JPanel panelWestOthers = new JPanel();
	JButton btnCategory = new JButton(new ImageIcon("images/category.png"));
	JButton btnSupplier = new JButton(new ImageIcon("images/supplier.png"));
	JButton btnProduct = new JButton(new ImageIcon("images/product.png"));
	JButton btnClient = new JButton(new ImageIcon("images/client.png"));
	JLabel lblCat = new JLabel("Category");
	JLabel lblSup = new JLabel("Supplier");
	JLabel lblPro = new JLabel("Product Info");
	JLabel lblCli = new JLabel("Client Info");
	JButton btnNewUser = new JButton(new ImageIcon("images/newuser.png"));
	JButton btnChangePassword = new JButton(new ImageIcon("images/changepassword.png"));
	JLabel lblnew = new JLabel("New User");
	JLabel lblCp = new JLabel("Change Password");
	JButton btnOpeningStock = new JButton(" Opening Stock   ",new ImageIcon("images/OpeningStock.png"));
	JButton btnPurchaseReceipt = new JButton(" Purchase Receipt  ",new ImageIcon("images/PurchaseReceipt.png"));
	JButton btnPurchaseReturn = new JButton(" Purchse Return  ",new ImageIcon("images/PurchaseReturn.png"));
	JButton btnSalesEntry = new JButton("   Sales Entry         ",new ImageIcon("images/SalesEntry.png"));
	JButton btnSalesReturn = new JButton("   Seles Return       ",new ImageIcon ("images/SalesReturn.png"));
	JButton btnWastageOrBroken = new JButton("Wastage Or Broken", new ImageIcon("images/Wastage.png"));
	JButton btnSetUpR = new JButton(new ImageIcon("images/SetupReport1.png"));
	JButton btnTaskR = new JButton(new ImageIcon("images/TaskReport.png"));
	JLabel lblSetUpR = new JLabel("Setup Report");
	JLabel lblTaskR = new JLabel("Task Report");
	JButton btnMail = new JButton(new ImageIcon("images/mail.png"));
	JButton btnLogOff = new JButton(new ImageIcon("images/logOff.png"));
	JButton btnExit = new JButton(new ImageIcon("images/exit.png"));
	JLabel lblMail = new JLabel("Mail");
	JLabel lblLogOff = new JLabel("LogOff");
	JLabel lblExit = new JLabel("Exit");
	JLabel lblHome = new JLabel(new ImageIcon("images/lblHome.png"));
	JLabel lblSetup = new JLabel(new ImageIcon("images/lblSetup.png"));
	JLabel lblSecurity = new JLabel(new ImageIcon("images/lblSecurity.png"));
	JLabel lblTask = new JLabel(new ImageIcon("images/lblTask.png"));
	JLabel lblReport = new JLabel(new ImageIcon("images/lblReport.png"));
	JLabel lblOthers = new JLabel(new ImageIcon("images/lblOthers.png"));
	
	BufferedImage image;

	public WorkingPanel(SessionBean bean, JFrame frame)
	{
		this.sessionBean = bean;
		this.frm = frame;
		category = new Category(sessionBean);
		supplier = new Supplierinfo(sessionBean);
		product = new Productinfo(sessionBean);
		client = new Clientinfo(sessionBean);
		NewUser = new NewUser(sessionBean);
		purchase = new PurchaseReceipt(sessionBean);
		purchaseReturn = new PurchaseReturn(sessionBean);
		salesEntry = new SalesEntry(sessionBean);
		salesReturn = new SalesReturn(sessionBean);
		openingStock = new OpeningStock(sessionBean);
		wastage = new WastageOrBroken(sessionBean);
		SetupReport = new SetupReport(sessionBean);
		cmp();
		btnAction();
	}
	private void lblBackgroundVisibleFalse()
	{
		lblHome.setVisible(false);
		lblSetup.setVisible(false);
		lblSecurity.setVisible(false);
		lblTask.setVisible(false);
		lblReport.setVisible(false);
		lblOthers.setVisible(false);
	}
	private void btnAction() 
	{
		btnSetup.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				panelWestCenterTrueFalse();
				lblBackgroundVisibleFalse();
				lblSetup.setVisible(true);
				panelWestSetup.setVisible(true);
				panelWestCenter.setVisible(true);
			}
		});
		btnSecurity.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				panelWestCenterTrueFalse();
				lblBackgroundVisibleFalse();
				lblSecurity.setVisible(true);
				panelWestSecurity.setVisible(true);
				panelWestCenter.setVisible(true);
			}
		});
		btnTask.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				panelWestCenterTrueFalse();
				lblBackgroundVisibleFalse();
				lblTask.setVisible(true);
				panelWestTask.setVisible(true);
				panelWestCenter.setVisible(true);

			}
		});
		btnReport.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				panelWestCenterTrueFalse();
				lblBackgroundVisibleFalse();
				lblReport.setVisible(true);
				panelWestReport.setVisible(true);
				panelWestCenter.setVisible(true);
			}
		});
		btnOthers.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				panelWestCenterTrueFalse();
				lblBackgroundVisibleFalse();
				lblOthers.setVisible(true);
				panelWestOthers.setVisible(true);
				panelWestCenter.setVisible(true);
			}
		});
		btnCategory.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				panelCenterTrueFalse();
				lblBackgroundVisibleFalse();
				category.setVisible(true);
				category.autoid();
				category.tableDataLoadCategory();
				category.cmbDataLoadCategory();
				category.autoidsub();
				category.cmbDataLoadSubCategory();
				category.tableDataLoadSubCategory();
			}
		});
		btnSupplier.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//panelWestCenterTrueFalse();
				panelCenterTrueFalse();
				lblBackgroundVisibleFalse();
				supplier.setVisible(true);
				supplier.autoidsup();
				supplier.tableDataLoadSupplier();
				supplier.cmbDataLoadSupplier();
			}
		});
		btnProduct.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				//panelWestCenterTrueFalse();
				panelCenterTrueFalse();
				lblBackgroundVisibleFalse();
				product.setVisible(true);
				product.autoidpro();
				product.categoryLoad();
				product.subCategoryLoad("%");
				product.supplierLoad();
				product.tableDataLoadProduct();
				product.cmbDataLoadProduct();
			}
		});
		btnClient.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				//panelWestCenterTrueFalse();
				panelCenterTrueFalse();
				lblBackgroundVisibleFalse();
				client.setVisible(true);
				client.autoidcli();
				client.tableDataLoad();
				client.cmbDataLoad();
				client.cmbReferenceDataLoad();
			}
		});
		btnNewUser.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				panelCenterTrueFalse();
				lblBackgroundVisibleFalse();
				NewUser.setVisible(true);
				NewUser.autoidnu();
				NewUser.tableDataLoadNewUser();
				NewUser.cmbDataLoadNewUser();
			}
		});
		btnChangePassword.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//panelWestCenterTrueFalse();
				panelCenterTrueFalse();
				lblBackgroundVisibleFalse();
				ChangePassword.setVisible(true);
				ChangePassword.cmbDataLoad();
			}
		});
		btnOpeningStock.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				//panelWestCenterTrueFalse();
				panelCenterTrueFalse();
				lblBackgroundVisibleFalse();
				openingStock.setVisible(true);
				openingStock.autoid();
				openingStock.productLoad();
				openingStock.tableDataLoad();
				openingStock.cmbDataLoad();
			}
		});
		btnPurchaseReceipt.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//panelWestCenterTrueFalse();
				panelCenterTrueFalse();
				lblBackgroundVisibleFalse();
				purchase.setVisible(true);
				purchase.autoid();
				purchase.productLoad();
				purchase.tableFindDataLoad();
			}
		});
		btnPurchaseReturn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//panelWestCenterTrueFalse();
				panelCenterTrueFalse();
				lblBackgroundVisibleFalse();
				purchaseReturn.setVisible(true);
				purchaseReturn.autoReturnNo();
				purchaseReturn.productLoad();
				purchaseReturn.tableFindDataLoad();
			}
		});
		btnSalesEntry.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//panelWestCenterTrueFalse();
				panelCenterTrueFalse();
				lblBackgroundVisibleFalse();
				salesEntry.setVisible(true);
				salesEntry.autoSalseNo();
				salesEntry.clientLoad();
				salesEntry.productLoad();
				salesEntry.tableFindDataLoad();
			}
		});
		btnSalesReturn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				panelCenterTrueFalse();
				lblBackgroundVisibleFalse();
				salesReturn.setVisible(true);
				salesReturn.autoSalesReturnNo();
				salesReturn.salesNoLoad();
				salesReturn.salesWiseProductLoad("%");
				salesReturn.tableFindDataLoad();
			}
		});
		btnWastageOrBroken.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				panelCenterTrueFalse();
				lblBackgroundVisibleFalse();
				wastage.setVisible(true);
				wastage.autoid();
				wastage.productLoad();
				wastage.tableDataLoad();
				wastage.cmbDataLoad();
			}
		});
		btnSetUpR.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				//panelWestCenterTrueFalse();
				panelCenterTrueFalse();
				lblBackgroundVisibleFalse();
				SetupReport.setVisible(true);
				SetupReport.productLoad();
				SetupReport.supplierLaod();
				SetupReport.clientLoad();
				SetupReport.productLoadStock();
			}
		});
		btnTaskR.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				//panelWestCenterTrueFalse();
				panelCenterTrueFalse();
				lblBackgroundVisibleFalse();
				TaskReport.setVisible(true);
			}
		});
		btnMail.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				//panelWestCenterTrueFalse();
				panelCenterTrueFalse();
				lblBackgroundVisibleFalse();
				mail.setVisible(true);
			}
		});
		btnLogOff.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				frm.setVisible(false);
				Login lg = new Login();
				ImageIcon icon = new ImageIcon("images/logopro1.png");
			    lg.setIconImage(icon.getImage());
			}
		});
		btnExit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				int confirmed = JOptionPane.showConfirmDialog(null, "Do you want to exit the whole system?","Exit......",JOptionPane.YES_NO_OPTION);
				if(confirmed == JOptionPane.YES_OPTION)
				{
					frm.dispose();
				}
				else
					frm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		});
	}
	private void cmp() 
	{
		setLayout(new BorderLayout());
		add(panelWest,BorderLayout.WEST);
		panelWestWork();
		add(panelCenter,BorderLayout.CENTER);
		panelCenterWork();
	}

	private void panelCenterWork()
	{
		panelCenter.setBackground(new Color(2, 191, 185));
		panelCenter.setBorder(BorderFactory.createLineBorder(new Color(2, 191, 185), 2));
		FlowLayout flow=new FlowLayout();
		panelCenter.setLayout(flow);
		flow.setVgap(0);
		panelCenter.add(lblHome);
		panelCenter.add(lblSetup);
		panelCenter.add(lblSecurity);
		panelCenter.add(lblTask);
		panelCenter.add(lblReport);
		panelCenter.add(lblOthers);
		/*panelCenter.add(DefaultPanel);
		DefaultPanel.add(Security);
		DefaultPanel.add(Setup);
		DefaultPanel.add(Task);
		DefaultPanel.add(Report);
		DefaultPanel.add(Others);
		DefaultPanel.add(lblImage);
		Setup.setVisible(false);
		Security.setVisible(false);
		Setup.setVisible(false);
		Task.setVisible(false);
		Report.setVisible(false);
		Others.setVisible(false);
		Setup.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0));
		Security.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));
		Task.setBorder(BorderFactory.createEmptyBorder(60, 0, 0, 0));
		Report.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		Others.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));*/
		panelCenter.add(category);
		panelCenter.add(supplier);
		panelCenter.add(product);
		panelCenter.add(client);
		panelCenter.add(mail);
		panelCenter.add(exit);
		panelCenter.add(logOff);
		panelCenter.add(purchase);
		panelCenter.add(purchaseReturn);
		panelCenter.add(salesEntry);
		panelCenter.add(salesReturn);
		panelCenter.add(openingStock);
		panelCenter.add(wastage);
		panelCenter.add(SetupReport);
		panelCenter.add(TaskReport);
		panelCenter.add(ChangePassword);
		panelCenter.add(NewUser);
		panelCenterTrueFalse();
	}
	private void panelCenterTrueFalse() 
	{
		category.setVisible(false);
		supplier.setVisible(false);
		product.setVisible(false);
		client.setVisible(false);
		mail.setVisible(false);
		exit.setVisible(false);
		logOff.setVisible(false);
		purchase.setVisible(false);
		purchaseReturn.setVisible(false);
		salesEntry.setVisible(false);
		salesReturn.setVisible(false);
		openingStock.setVisible(false);
		wastage.setVisible(false);
		ChangePassword.setVisible(false);
		NewUser.setVisible(false);
		SetupReport.setVisible(false);
		TaskReport.setVisible(false);
	}
	private void panelWestWork()
	{
		panelWest.setPreferredSize(new Dimension(220,0));
		panelWest.setBorder(BorderFactory.createRaisedBevelBorder());
		panelWest.setLayout(new BorderLayout());
		panelWest.add(panelWestNorth,BorderLayout.NORTH);
		panelWestNorthWork();
		panelWest.add(panelWestCenter, BorderLayout.CENTER);
		panelWestCenterWork();
	}
	private void panelWestCenterWork()
	{
		panelWestCenter.setBackground(Color.white);
		FlowLayout flow = new FlowLayout();
		flow.setVgap(0);
		panelWestCenter.setLayout(flow);
		panelWestCenter.add(panelWestSetup);
		panelWestSetup.setBackground(Color.white);
		panelWestSetupWorks();
		panelWestCenter.add(panelWestSecurity);
		panelWestSecurity.setBackground(Color.white);
		panelWestSecurityWorks();
		panelWestCenter.add(panelWestTask);
		panelWestTask.setBackground(Color.white);
		panelWestTaskWorks();
		panelWestCenter.add(panelWestReport);
		panelWestReport.setBackground(Color.white);
		panelWestReportWorks();
		panelWestCenter.add(panelWestOthers);
		panelWestOthers.setBackground(Color.white);
		panelWestOthersWorks();
		panelWestCenterTrueFalse();
	}
	private void panelWestCenterTrueFalse() 
	{
		panelWestSetup.setVisible(false);
		panelWestSecurity.setVisible(false);
		panelWestTask.setVisible(false);
		panelWestReport.setVisible(false);
		panelWestOthers.setVisible(false);
	}
	private void panelWestSetupWorks() 
	{
		panelWestSetup.setPreferredSize(new Dimension(220,565));
		GridBagConstraints c=new GridBagConstraints();
		panelWestSetup.setLayout(new GridBagLayout());
		c.insets=new Insets(5,5,5,5);
		c.fill=GridBagConstraints.BOTH;
		c.gridx=0;
		c.gridy=0;
		panelWestSetup.add(btnCategory,c);
		btnCategory.setBackground(new Color(0,134,139));
		btnCategory.setPreferredSize(new Dimension(70,70));
		c.gridx=0;
		c.gridy=1;
		c.insets=new Insets(5,22,5,5);
		panelWestSetup.add(lblCat, c);
		lblCat.setFont(new Font("Comic Sans MS",Font.PLAIN+Font.BOLD,16));
		lblCat.setCursor(new Cursor(Cursor.HAND_CURSOR));
		c.gridx=0;
		c.gridy=2;
		c.insets=new Insets(5,5,5,5);
		panelWestSetup.add(btnSupplier, c);
		btnSupplier.setBackground(new Color(0,134,139));
		btnSupplier.setPreferredSize(new Dimension(70,70));
		c.gridx=0;
		c.gridy=3;
		c.insets=new Insets(5,25,5,5);
		panelWestSetup.add(lblSup, c);
		lblSup.setFont(new Font("Comic Sans MS",Font.PLAIN+Font.BOLD,16));
		lblSup.setCursor(new Cursor(Cursor.HAND_CURSOR));
		c.gridx=0;
		c.gridy=4;
		c.insets=new Insets(5,5,5,5);
		panelWestSetup.add(btnProduct, c);
		btnProduct.setBackground(new Color(0,134,139));
		btnProduct.setPreferredSize(new Dimension(70,70));
		c.gridx=0;
		c.gridy=5;
		c.insets=new Insets(5,10,5,5);
		panelWestSetup.add(lblPro, c);
		lblPro.setFont(new Font("Comic Sans MS",Font.PLAIN+Font.BOLD,16));
		lblPro.setCursor(new Cursor(Cursor.HAND_CURSOR));
		c.gridx=0;
		c.gridy=6;
		c.insets=new Insets(5,5,5,5);
		panelWestSetup.add(btnClient, c);
		btnClient.setPreferredSize(new Dimension(70,70));
		btnClient.setBackground(new Color(0,134,139));
		c.gridx=0;
		c.gridy=7;
		c.insets=new Insets(5,15,5,5);
		panelWestSetup.add(lblCli, c);
		lblCli.setFont(new Font("Comic Sans MS",Font.PLAIN+Font.BOLD,16));
		lblCli.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	private void panelWestSecurityWorks() 
	{
		panelWestSecurity.setPreferredSize(new Dimension(220,565));
		GridBagConstraints c=new GridBagConstraints();
		panelWestSecurity.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5,5,5,5);
		c.gridx=0;
		c.gridy=0;
		panelWestSecurity.add(btnNewUser, c);
		btnNewUser.setBackground(new Color(0,134,139));
		btnNewUser.setPreferredSize(new Dimension(70,70));
		c.gridx=0;
		c.gridy=1;
		c.insets=new Insets(5,30,5,5);
		panelWestSecurity.add(lblnew, c);
		lblnew.setFont(new Font("Comic Sans MS",Font.PLAIN+Font.BOLD,16));
		lblnew.setCursor(new Cursor(Cursor.HAND_CURSOR));
		c.gridx=0;
		c.gridy=2;
		c.insets=new Insets(5,5,5,5);
		panelWestSecurity.add(btnChangePassword, c);
		btnChangePassword.setBackground(new Color(0,134,139));
		btnChangePassword.setPreferredSize(new Dimension(70,70));
		c.gridx=0;
		c.gridy=3;
		panelWestSecurity.add(lblCp, c);
		lblCp.setFont(new Font("Comic Sans MS",Font.PLAIN+Font.BOLD,16));
		lblCp.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	private void panelWestTaskWorks() 
	{
		panelWestTask.setPreferredSize(new Dimension(220,565));
		GridBagConstraints c=new GridBagConstraints();
		panelWestTask.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5,5,5,5);
		c.gridx=0;
		c.gridy=0;
		panelWestTask.add(btnOpeningStock, c);
		btnOpeningStock.setBackground(new Color(0,134,139));
		btnOpeningStock.setFont(new Font("Cambria",Font.PLAIN+Font.BOLD,15));
		c.gridx=0;
		c.gridy=1;
		c.insets=new Insets(5,5,5,5);
		panelWestTask.add(btnPurchaseReceipt, c);
		btnPurchaseReceipt.setBackground(new Color(0,134,139));
		btnPurchaseReceipt.setFont(new Font("Cambria",Font.PLAIN+Font.BOLD,15));
		c.gridx=0;
		c.gridy=2;
		c.insets=new Insets(5,5,5,5);
		panelWestTask.add(btnPurchaseReturn, c);
		btnPurchaseReturn.setBackground(new Color(0,134,139));
		btnPurchaseReturn.setFont(new Font("Cambria",Font.PLAIN+Font.BOLD,15));
		c.gridx=0;
		c.gridy=3;
		c.insets=new Insets(5,5,5,5);
		panelWestTask.add(btnSalesEntry, c);
		btnSalesEntry.setBackground(new Color(0,134,139));
		btnSalesEntry.setFont(new Font("Cambria",Font.PLAIN+Font.BOLD,15));
		c.gridx=0;
		c.gridy=4;
		c.insets=new Insets(5,5,5,5);
		panelWestTask.add(btnSalesReturn, c);
		btnSalesReturn.setBackground(new Color(0,134,139));
		btnSalesReturn.setFont(new Font("Cambria",Font.PLAIN+Font.BOLD,15));
		c.gridx=0;
		c.gridy=5;
		c.insets=new Insets(5,5,5,5);
		panelWestTask.add(btnWastageOrBroken, c);
		btnWastageOrBroken.setBackground(new Color(0,134,139));
		btnWastageOrBroken.setFont(new Font("Cambria",Font.PLAIN+Font.BOLD,15));
	}

	private void panelWestReportWorks() 
	{
		panelWestReport.setPreferredSize(new Dimension(220,565));
		GridBagConstraints c=new GridBagConstraints();
		panelWestReport.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5,5,5,5);
		c.gridx=0;
		c.gridy=0;
		panelWestReport.add(btnSetUpR, c);
		btnSetUpR.setBackground(new Color(0,134,139));
		c.gridx=0;
		c.gridy=1;
		c.insets=new Insets(5,10,5,5);
		panelWestReport.add(lblSetUpR, c);
		lblSetUpR.setFont(new Font("Comic Sans MS",Font.PLAIN+Font.BOLD,16));
		lblSetUpR.setCursor(new Cursor(Cursor.HAND_CURSOR));
		c.gridx=0;
		c.gridy=2;
		c.insets=new Insets(5,5,5,5);
		panelWestReport.add(btnTaskR, c);
		btnTaskR.setBackground(new Color(0,134,139));
		c.gridx=0;
		c.gridy=3;
		c.insets=new Insets(5,10,5,5);
		panelWestReport.add(lblTaskR, c);
		lblTaskR.setFont(new Font("Comic Sans MS",Font.PLAIN+Font.BOLD,16));
		lblTaskR.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	private void panelWestOthersWorks() 
	{
		panelWestOthers.setPreferredSize(new Dimension(220,565));
		GridBagConstraints c=new GridBagConstraints();
		panelWestOthers.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5,5,5,5);
		c.gridx=0;
		c.gridy=1;
		panelWestOthers.add(btnMail, c);
		btnMail.setBackground(new Color(0,134,139));
		c.gridx=0;
		c.gridy=2;
		c.insets=new Insets(5,35,5,5);
		panelWestOthers.add(lblMail, c);
		lblMail.setFont(new Font("Comic Sans MS",Font.PLAIN+Font.BOLD,16));
		lblMail.setCursor(new Cursor(Cursor.HAND_CURSOR));
		c.gridx=0;
		c.gridy=3;
		c.insets=new Insets(5,5,5,5);
		panelWestOthers.add(btnLogOff, c);
		btnLogOff.setBackground(new Color(0,134,139));
		c.gridx=0;
		c.gridy=4;
		c.insets=new Insets(5,25,5,5);
		panelWestOthers.add(lblLogOff, c);
		lblLogOff.setFont(new Font("Comic Sans MS",Font.PLAIN+Font.BOLD,16));
		lblLogOff.setCursor(new Cursor(Cursor.HAND_CURSOR));
      /*c.gridx=0;
		c.gridy=5;
		c.insets=new Insets(5,5,5,5);
		panelWestOthers.add(btnExit, c);
		btnExit.setBackground(new Color(0,134,139));

		c.gridx=0;
		c.gridy=6;
		c.insets=new Insets(5,45,5,5);
		panelWestOthers.add(lblExit, c);
		lblExit.setFont(new Font("Comic Sans MS",Font.PLAIN+Font.BOLD,16));
		lblExit.setCursor(new Cursor(Cursor.HAND_CURSOR));*/


	}

	private void panelWestNorthWork() 
	{
		panelWestNorth.setBackground(Color.WHITE);
		panelWestNorth.setPreferredSize(new Dimension(1,190));
		panelWestNorth.setBorder(BorderFactory.createRaisedBevelBorder());
		panelWestNorth.setLayout(new GridLayout(5,1,1,1));
		panelWestNorth.add(btnSetup);
		panelWestNorth.add(btnSecurity);
		panelWestNorth.add(btnTask);
		panelWestNorth.add(btnReport);
		panelWestNorth.add(btnOthers);
		btnSetup.setBackground(new Color(0,134,139));
		btnSetup.setFont(new Font("Agency FB",Font.PLAIN+Font.BOLD, 20));
		btnSecurity.setBackground(new Color(0,134,139));
		btnSecurity.setFont(new Font("Agency FB",Font.PLAIN+Font.BOLD, 20));
		btnTask.setBackground(new Color(0,134,139));
		btnTask.setFont(new Font("Agency FB",Font.PLAIN+Font.BOLD, 20));
		btnReport.setBackground(new Color(0,134,139));
		btnReport.setFont(new Font("Agency FB",Font.PLAIN+Font.BOLD, 20));
		btnOthers.setBackground(new Color(0,134,139));
		btnOthers.setFont(new Font("Agency FB",Font.PLAIN+Font.BOLD, 20));
	}
}
