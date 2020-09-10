package com.Report;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

public class TaskReport extends JPanel
{
	private static final long serialVersionUID = 1L;
	JPanel panelCenter=new JPanel();
	JPanel panelSouth=new JPanel();
	JPanel panelItemReport=new JPanel();
	JPanel panelSalesReport=new JPanel();
	JPanel panelProductReport=new JPanel();
	JPanel panelStockReport=new JPanel();
	JPanel panelItemReportNorth=new JPanel();
	JPanel panelItemReportCenter=new JPanel();
	JPanel panelItemReportSouth=new JPanel();
	JPanel panelItemReportCenterWest=new JPanel();
	JPanel panelItemReportCenterCenter=new JPanel();
	JPanel panelSalseReportNorth=new JPanel();
	JPanel panelSalseReportCenter=new JPanel();
	JPanel panelSalseReportSouth=new JPanel();
	JPanel panelSalseReportCenterWest=new JPanel();
	JPanel panelSalseReportCenterCenter=new JPanel();
	JPanel panelProductReportNorth=new JPanel();
	JPanel panelProductReportCenter=new JPanel();
	JPanel panelProductReportSouth=new JPanel();
	JPanel panelProductReportCenterWest=new JPanel();
	JPanel panelProductReportCenterCenter=new JPanel();
	JPanel panelStockReportNorth=new JPanel();
	JPanel panelStockReportCenter=new JPanel();
	JPanel panelStockReportSouth=new JPanel();
	JPanel panelStockReportCenterWest=new JPanel();
	JPanel panelStockReportCenterCenter=new JPanel();

	JLabel lblReportTypeItemReport=new JLabel("Report Type:");
	JLabel lblReportTypeSalesReport=new JLabel("Report Type:");
	JLabel lblReportTypeProductReport=new JLabel("Report Type:");
	JLabel lblReportTypeStockReport=new JLabel("Report Type:");
	JLabel lblSupplierName=new JLabel("Supplier Name");

	JLabel lblProductNameItemReport=new JLabel("Product Name");
	JLabel lblProductNameSalesReport=new JLabel("Product Name");
	JLabel lblProductNameProductReport=new JLabel("Product Name");
	JLabel lblProductNameStockReport=new JLabel("Product Name");

	JLabel lblFromDateItemReport=new JLabel("From Date");
	JLabel lblFromDateSalesReport=new JLabel("From Date");
	JLabel lblFromDateProductReport=new JLabel("From Date");
	JLabel lblFromDateStockReport=new JLabel("From Date");

	JLabel lblToDateItemReport=new JLabel("To Date");
	JLabel lblToDateSalesReport=new JLabel("To Date");
	JLabel lblToDateProductReport=new JLabel("To Date");
	JLabel lblToDateStockReport=new JLabel("To Date");

	JLabel lblInvoicNoItemReport=new JLabel("Invoice No");
	JLabel lblInvoicNoSalesReport=new JLabel("Invoice No");

	JLabel lblCatagoryNameProductReport=new JLabel("Category Name");
	JLabel lblCatagoryNameStockReport=new JLabel("Category Name");

	JLabel lblSubcatagoryNameProductReport=new JLabel("Subcategory Name");
	JLabel lblSubcatagoryNameStockReport=new JLabel("Subcategory Name");

	JButton btnPreviewItemReport=new JButton("Preview",new ImageIcon("images/preview.png"));
	JButton btnRefreshItemReport=new JButton("Refresh",new ImageIcon("images/refresh.png"));
	JButton btnPreviewSalesReport=new JButton("Preview",new ImageIcon("images/preview.png"));
	JButton btnRefreshSalesReport=new JButton("Refresh",new ImageIcon("images/refresh.png"));
	JButton btnPreviewProductReport=new JButton("Preview",new ImageIcon("images/preview.png"));
	JButton btnRefreshProductReport=new JButton("Refresh",new ImageIcon("images/refresh.png"));
	JButton btnPreviewStockReport=new JButton("Preview",new ImageIcon("images/preview.png"));
	JButton btnRefreshStockReport=new JButton("Refresh",new ImageIcon("images/refresh.png"));

	JRadioButton rdoItemReport=new JRadioButton("Item Report");
	JRadioButton rdoItemReportReturn=new JRadioButton("Item Return Report");
	JRadioButton rdoSales=new JRadioButton("Sales Report");
	JRadioButton rdosalesReturn=new JRadioButton("Sales Return Report");
	JRadioButton rdoOpeningStock=new JRadioButton("Opening Stock");
	JRadioButton rdoWastageBroken=new JRadioButton("Wastage Broken");
	JRadioButton rdoStockSummery=new JRadioButton("Stock Summery");
	JRadioButton rdoStockDetails=new JRadioButton("Stock Details");

	JCheckBox chkAllSupplierName=new JCheckBox("All");
	JCheckBox chkAllProductNameItemReport=new JCheckBox("All");
	JCheckBox chkAllProductNameSalesReport=new JCheckBox("All");
	JCheckBox chkAllChatagoryNameProductReport=new JCheckBox("All");
	JCheckBox chkAllSubcatagoryNameProductReport=new JCheckBox("All");
	JCheckBox chkAllProductNameProductReport=new JCheckBox("All");
	JCheckBox chkAllproductNameProductReport=new JCheckBox("All");
	JCheckBox chkAllCatagoryNameStockReport=new JCheckBox("All");
	JCheckBox chkAllSubCatagoryNameStockReport=new JCheckBox("All");
	JCheckBox chkAllProductNameStockReport=new JCheckBox("All");

	JCheckBox chkSupplierWise=new JCheckBox("Supplier Wise");
	JCheckBox chkProductWiseItemReport=new JCheckBox("Product Wise");
	JCheckBox chkDateWiseItemReport=new JCheckBox("Date Wise");
	JCheckBox chkInvoiceNowise=new JCheckBox("Invoice No");
	JCheckBox chkProductWiseSalesReport=new JCheckBox("Product Wise");
	JCheckBox chkDateWiseSalesReport=new JCheckBox("Date Wise");
	JCheckBox chkSalesReturnNoWise=new JCheckBox("Sales/Return No");
	JCheckBox chkCatagoryWise=new JCheckBox("Category");
	JCheckBox chkDateWiseStockReport=new JCheckBox("Date Wise");

	JComboBox<String> cmbSupplierNameItemReport=new JComboBox<>();
	JComboBox<String> cmbProductNameItemReport=new JComboBox<>();
	JComboBox<String> cmbInvoiceNoItemReport=new JComboBox<>();
	JComboBox<String> cmbProductNameSalesReport=new JComboBox<>();
	JComboBox<String> cmbInvoiceNoSalesReport=new JComboBox<>();
	JComboBox<String> cmbCatagoryNameProductReport=new JComboBox<>();
	JComboBox<String> cmbSubcatagoryNameProductReport=new JComboBox<>();
	JComboBox<String> cmbProductNameProductReport=new JComboBox<>();
	JComboBox<String> cmbCatagoryNameStockReport=new JComboBox<>();
	JComboBox<String> cmbSubCatagoryNameStockReport=new JComboBox<>();
	JComboBox<String> cmbProductNameStockReport=new JComboBox<>();
	JDateChooser dateFromDateItemReport=new JDateChooser();
	JDateChooser dateToDateItemReport=new JDateChooser();
	JDateChooser dateFromDateSalesReport=new JDateChooser();
	JDateChooser dateToDateSalesReport=new JDateChooser();
	JDateChooser dateFromDateProductReport=new JDateChooser();
	JDateChooser dateToDateProductReport=new JDateChooser();
	JDateChooser dateFromDateStockReport=new JDateChooser();
	JDateChooser dateToDateStockReport=new JDateChooser();
	Font font=new Font("Baskerville O",Font.BOLD,17);
	Font font1=new Font("Baskerville O",Font.BOLD,15);
	JPanel panel=new JPanel();
	public TaskReport()
	{
		init();
		cmp();
	}
	public void cmp()
	{
		BorderLayout border = new BorderLayout();
		border.setHgap(10);
		setLayout(border);
		add(panelCenter,BorderLayout.CENTER);
		panelCenter();
		add(panelSouth,BorderLayout.SOUTH);
		panelSouth();
	}
	public void panelCenter()
	{
		BorderLayout border=new BorderLayout();
		panelCenter.setLayout(border);
		border.setHgap(0);
		panelCenter.add(panelItemReport,BorderLayout.WEST);
		panelItemReport();
		panelCenter.add(panelSalesReport,BorderLayout.CENTER);
		panelSalesReport();
	}
	public void panelItemReport()
	{
		panelItemReport.setBackground(new Color(2, 191, 185));
		panelItemReport.setPreferredSize(new Dimension(570,1));
		TitledBorder panelItemReportTitle=new TitledBorder("Item Report");
		panelItemReportTitle.setTitleFont(new Font("Agency FB",Font.BOLD,20));
		panelItemReportTitle.setTitleColor(Color.WHITE);
		panelItemReportTitle.setTitleJustification(TitledBorder.CENTER);
		panelItemReportTitle.setTitlePosition(TitledBorder.TOP);
		panelItemReport.setBorder(panelItemReportTitle);
		BorderLayout border=new BorderLayout();
		panelItemReport.setLayout(border);
		panelItemReport.add(panelItemReportNorth,BorderLayout.NORTH);
		panelItemReportNorth();
		panelItemReport.add(panelItemReportCenter,BorderLayout.CENTER);
		panelItemReportCenter();
		panelItemReport.add(panelItemReportSouth,BorderLayout.SOUTH);
		panelItemReportSouth();
	}
	public void panelItemReportNorth()
	{
		panelItemReportNorth.setOpaque(false);
		//panelItemReportNorth.setBorder(BorderFactory.createRaisedBevelBorder());
		panelItemReportNorth.setPreferredSize(new Dimension(1,50));
		FlowLayout flow=new FlowLayout();
		flow.setVgap(10);
		flow.setHgap(15);
		panelItemReportNorth.setLayout(flow);
		lblReportTypeItemReport.setFont(font);
		panelItemReportNorth.add(lblReportTypeItemReport);
		rdoItemReport.setFont(font);
		panelItemReportNorth.add(rdoItemReport);
		rdoItemReportReturn.setFont(font);
		panelItemReportNorth.add(rdoItemReportReturn);
	}
	public void panelItemReportCenter()
	{
		//panelItemReportCenter.setOpaque(false);
		panelItemReportCenter.setLayout(new BorderLayout());
		panelItemReportCenter.add(panelItemReportCenterWest,BorderLayout.WEST);
		panelItemReportCenterWest();
		panelItemReportCenter.add(panelItemReportCenterCenter,BorderLayout.CENTER);
		panelItemReportCenterCenter();
	}
	public void panelItemReportCenterWest()
	{
		panelItemReportCenterWest.setBackground(Color.white);
		//panelItemReportCenterWest.setOpaque(false);
		panelItemReportCenterWest.setPreferredSize(new Dimension(160,1));
		GridBagLayout grid=new GridBagLayout();
		panelItemReportCenterWest.setLayout(grid);
		GridBagConstraints cn=new GridBagConstraints();
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=0;
		chkSupplierWise.setFont(font1);
		panelItemReportCenterWest.add(chkSupplierWise,cn);
		cn.gridx=0;
		cn.gridy=1;
		chkProductWiseItemReport.setFont(font1);
		panelItemReportCenterWest.add(chkProductWiseItemReport,cn);
		cn.gridx=0;
		cn.gridy=2;
		chkDateWiseItemReport.setFont(font1);
		panelItemReportCenterWest.add(chkDateWiseItemReport,cn);
		cn.gridx=0;
		cn.gridy=3;
		chkInvoiceNowise.setFont(font1);
		panelItemReportCenterWest.add(chkInvoiceNowise,cn);
	}
	public void panelItemReportCenterCenter()
	{
		//panelItemReportCenterCenter.setOpaque(false);
		panelItemReportCenterCenter.setBackground(Color.white);
		GridBagLayout grid=new GridBagLayout();
		panelItemReportCenterCenter.setLayout(grid);
		GridBagConstraints cn=new GridBagConstraints();
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=0;
		cn.gridy=0;
		lblSupplierName.setFont(font1);
		panelItemReportCenterCenter.add(lblSupplierName,cn);
		cn.gridx=1;
		cn.gridy=0;
		cmbSupplierNameItemReport.setPreferredSize(new Dimension(170,25));
		panelItemReportCenterCenter.add(cmbSupplierNameItemReport,cn);
		cmbSupplierNameItemReport.setBackground(Color.white);
		cn.gridx=2;
		cn.gridy=0;
		chkAllSupplierName.setFont(font1);
		panelItemReportCenterCenter.add(chkAllSupplierName,cn);
		cn.gridx=0;
		cn.gridy=1;
		lblProductNameItemReport.setFont(font1);
		panelItemReportCenterCenter.add(lblProductNameItemReport,cn);
		cn.gridx=1;
		cn.gridy=1;
		cmbProductNameItemReport.setPreferredSize(new Dimension(170,25));
		panelItemReportCenterCenter.add(cmbProductNameItemReport,cn);
		cmbProductNameItemReport.setBackground(Color.white);
		cn.gridx=2;
		cn.gridy=1;
		chkAllProductNameItemReport.setFont(font1);
		panelItemReportCenterCenter.add(chkAllProductNameItemReport,cn);
		cn.gridx=0;
		cn.gridy=2;
		lblFromDateItemReport.setFont(font1);
		panelItemReportCenterCenter.add(lblFromDateItemReport,cn);
		cn.gridx=1;
		cn.gridy=2;
		panelItemReportCenterCenter.add(dateFromDateItemReport,cn);
		dateFromDateItemReport.setBackground(Color.white);
		cn.gridx=0;
		cn.gridy=3;
		lblToDateItemReport.setFont(font1);
		panelItemReportCenterCenter.add(lblToDateItemReport,cn);
		cn.gridx=1;
		cn.gridy=3;
		panelItemReportCenterCenter.add(dateToDateItemReport,cn);
		dateToDateItemReport.setBackground(Color.white);
		cn.gridx=0;
		cn.gridy=4;
		lblInvoicNoItemReport.setFont(font1);
		panelItemReportCenterCenter.add(lblInvoicNoItemReport,cn);
		cn.gridx=1;
		cn.gridy=4;
		panelItemReportCenterCenter.add(cmbInvoiceNoItemReport,cn);
		cmbInvoiceNoItemReport.setBackground(Color.white);
	}
	public void panelItemReportSouth()
	{
		panelItemReportSouth.setBackground(Color.white);
		//panelItemReportSouth.setOpaque(false);
		panelItemReportSouth.setPreferredSize(new Dimension(1,60));
		FlowLayout flow=new FlowLayout();
		flow.setHgap(10);
		panelItemReportSouth.setLayout(flow);
		btnRefreshItemReport.setForeground(Color.black);
		btnRefreshItemReport.setBackground(new Color(0,134,139));
		btnRefreshItemReport.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		btnPreviewItemReport.setForeground(Color.black);
		btnPreviewItemReport.setBackground(new Color(0,134,139));
		btnPreviewItemReport.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		panelItemReportSouth.add(btnRefreshItemReport);
		panelItemReportSouth.add(btnPreviewItemReport);
	}
	////.......................................................
	public void panelSalesReport()
	{
		panelSalesReport.setBackground(new Color(2, 191, 185));
		panelSalesReport.setPreferredSize(new Dimension(570,1));
		TitledBorder panelSalesReportTitle=new TitledBorder("Sales Report");
		panelSalesReportTitle.setTitleFont(new Font("Agency FB",Font.BOLD,20));
		panelSalesReportTitle.setTitleColor(Color.WHITE);
		panelSalesReportTitle.setTitleJustification(TitledBorder.CENTER);
		panelSalesReportTitle.setTitlePosition(TitledBorder.TOP);
		panelSalesReport.setBorder(panelSalesReportTitle);
		BorderLayout border=new BorderLayout();
		panelSalesReport.setLayout(border);
		panelSalesReport.add(panelSalseReportNorth,BorderLayout.NORTH);
		panelSalseReportNorth();
		panelSalesReport.add(panelSalseReportCenter,BorderLayout.CENTER);
		panelSalseReportCenter();
		panelSalesReport.add(panelSalseReportSouth,BorderLayout.SOUTH);
		panelSalseReportSouth();
	}
	public void panelSalseReportNorth()
	{
		panelSalseReportNorth.setOpaque(false);
		//panelSalseReportNorth.setBorder(BorderFactory.createLoweredBevelBorder());
		panelSalseReportNorth.setPreferredSize(new Dimension(1,50));
		FlowLayout flow=new FlowLayout();
		flow.setVgap(10);
		flow.setHgap(15);
		panelSalseReportNorth.setLayout(flow);
		lblReportTypeSalesReport.setFont(font);
		panelSalseReportNorth.add(lblReportTypeSalesReport);
		rdoSales.setFont(font);
		panelSalseReportNorth.add(rdoSales);
		rdosalesReturn.setFont(font);
		panelSalseReportNorth.add(rdosalesReturn);
	}
	public void panelSalseReportCenter()
	{
		//panelItemReportCenter.setOpaque(false);
		panelSalseReportCenter.setLayout(new BorderLayout());
		panelSalseReportCenter.add(panelSalseReportCenterWest,BorderLayout.WEST);
		panelSalseReportCenterWest();
		panelSalseReportCenter.add(panelSalseReportCenterCenter,BorderLayout.CENTER);
		panelSalseReportCenterCenter();
	}
	public void panelSalseReportCenterWest()
	{
		panelSalseReportCenterWest.setBackground(Color.white);
		//panelItemReportCenterWest.setOpaque(false);
		panelSalseReportCenterWest.setPreferredSize(new Dimension(170,1));
		GridBagLayout grid=new GridBagLayout();
		panelSalseReportCenterWest.setLayout(grid);
		GridBagConstraints cn=new GridBagConstraints();
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=0;
		chkProductWiseSalesReport.setFont(font1);
		panelSalseReportCenterWest.add(chkProductWiseSalesReport,cn);
		cn.gridx=0;
		cn.gridy=1;
		chkDateWiseSalesReport.setFont(font1);
		panelSalseReportCenterWest.add(chkDateWiseSalesReport,cn);
		cn.gridx=0;
		cn.gridy=2;
		chkSalesReturnNoWise.setFont(font1);
		panelSalseReportCenterWest.add(chkSalesReturnNoWise,cn);
	}
	public void panelSalseReportCenterCenter()
	{
		panelSalseReportCenterCenter.setBackground(Color.white);
		//panelItemReportCenterCenter.setOpaque(false);
		GridBagLayout grid=new GridBagLayout();
		panelSalseReportCenterCenter.setLayout(grid);
		GridBagConstraints cn=new GridBagConstraints();
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=0;
		cn.gridy=0;
		lblProductNameSalesReport.setFont(font1);
		panelSalseReportCenterCenter.add(lblProductNameSalesReport,cn);
		cn.gridx=1;
		cn.gridy=0;
		cmbProductNameSalesReport.setPreferredSize(new Dimension(170,25));
		panelSalseReportCenterCenter.add(cmbProductNameSalesReport,cn);
		cmbProductNameSalesReport.setBackground(Color.white);
		cn.gridx=2;
		cn.gridy=0;
		chkAllProductNameSalesReport.setFont(font1);
		panelSalseReportCenterCenter.add(chkAllProductNameSalesReport,cn);
		cn.gridx=0;
		cn.gridy=1;
		lblFromDateSalesReport.setFont(font1);
		panelSalseReportCenterCenter.add(lblFromDateSalesReport,cn);
		cn.gridx=1;
		cn.gridy=1;
		panelSalseReportCenterCenter.add(dateFromDateSalesReport,cn);
		dateFromDateSalesReport.setBackground(Color.white);
		cn.gridx=0;
		cn.gridy=2;
		lblToDateSalesReport.setFont(font1);
		panelSalseReportCenterCenter.add(lblToDateSalesReport,cn);
		cn.gridx=1;
		cn.gridy=2;
		panelSalseReportCenterCenter.add(dateToDateSalesReport,cn);
		dateToDateSalesReport.setBackground(Color.white);
		cn.gridx=0;
		cn.gridy=3;
		lblInvoicNoSalesReport.setFont(font1);
		panelSalseReportCenterCenter.add(lblInvoicNoSalesReport,cn);
		cn.gridx=1;
		cn.gridy=3;
		panelSalseReportCenterCenter.add(cmbInvoiceNoSalesReport,cn);
		cmbInvoiceNoSalesReport.setBackground(Color.white);
	}
	public void panelSalseReportSouth()
	{
		panelSalseReportSouth.setBackground(Color.white);
		//panelItemReportSouth.setOpaque(false);
		panelSalseReportSouth.setPreferredSize(new Dimension(1,60));
		FlowLayout flow=new FlowLayout();
		flow.setHgap(10);
		panelSalseReportSouth.setLayout(flow);
		btnRefreshSalesReport.setForeground(Color.black);
		btnRefreshSalesReport.setBackground(new Color(0,134,139));
		btnRefreshSalesReport.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		btnPreviewSalesReport.setForeground(Color.black);
		btnPreviewSalesReport.setBackground(new Color(0,134,139));
		btnPreviewSalesReport.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		panelSalseReportSouth.add(btnRefreshSalesReport);
		panelSalseReportSouth.add(btnPreviewSalesReport);
	}
	////..............................................................
	public void panelSouth()
	{
		panelSouth.setPreferredSize(new Dimension(1140,365));
		BorderLayout border=new BorderLayout();
		panelSouth.setLayout(border);
		border.setHgap(0);
		panelSouth.add(panelProductReport,BorderLayout.WEST);
		panelProductReport();
		panelSouth.add(panelStockReport,BorderLayout.CENTER);
		panelStockReport();
	}
	////...............................................................
	public void panelProductReport()
	{
		panelProductReport.setBackground(new Color(2, 191, 185));
		panelProductReport.setPreferredSize(new Dimension(570,1));
		TitledBorder panelproductReportTitle=new TitledBorder("Product Report");
		panelproductReportTitle.setTitleFont(new Font("Agency FB",Font.BOLD,20));
		panelproductReportTitle.setTitleColor(Color.WHITE);
		panelproductReportTitle.setTitleJustification(TitledBorder.CENTER);
		panelproductReportTitle.setTitlePosition(TitledBorder.TOP);
		panelProductReport.setBorder(panelproductReportTitle);
		BorderLayout border=new BorderLayout();
		panelProductReport.setLayout(border);
		panelProductReport.add(panelProductReportNorth,BorderLayout.NORTH);
		panelProductReportNorth();
		panelProductReport.add(panelProductReportCenter,BorderLayout.CENTER);
		panelProductReportCenter();
		panelProductReport.add(panelProductReportSouth,BorderLayout.SOUTH);
		panelProductReportSouth();
	}
	////....................................................................
	public void panelProductReportNorth()
	{
		panelProductReportNorth.setOpaque(false);
		//panelProductReportNorth.setBorder(BorderFactory.createLoweredBevelBorder());
		panelProductReportNorth.setPreferredSize(new Dimension(1,50));
		FlowLayout flow=new FlowLayout();
		flow.setVgap(10);
		flow.setHgap(15);
		panelProductReportNorth.setLayout(flow);
		lblReportTypeProductReport.setFont(font);
		panelProductReportNorth.add(lblReportTypeProductReport);
		rdoOpeningStock.setFont(font);
		panelProductReportNorth.add(rdoOpeningStock);
		rdoWastageBroken.setFont(font);
		panelProductReportNorth.add(rdoWastageBroken);
	}
	public void panelProductReportCenter()
	{
		panelProductReportCenter.setBackground(Color.white);
		GridBagLayout grid=new GridBagLayout();
		panelProductReportCenter.setLayout(grid);
		GridBagConstraints cn=new GridBagConstraints();
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=0;
		cn.gridy=0;
		lblCatagoryNameProductReport.setFont(font1);
		panelProductReportCenter.add(lblCatagoryNameProductReport,cn);
		cn.gridx=1;
		cn.gridy=0;
		cmbCatagoryNameProductReport.setPreferredSize(new Dimension(250,25));
		panelProductReportCenter.add(cmbCatagoryNameProductReport,cn);
		cmbCatagoryNameProductReport.setBackground(Color.white);
		cn.gridx=2;
		cn.gridy=0;
		chkAllChatagoryNameProductReport.setFont(font1);
		panelProductReportCenter.add(chkAllChatagoryNameProductReport,cn);//
		cn.gridx=0;
		cn.gridy=1;
		lblSubcatagoryNameProductReport.setFont(font1);
		panelProductReportCenter.add(lblSubcatagoryNameProductReport,cn);
		cn.gridx=1;
		cn.gridy=1;
		cmbSubcatagoryNameProductReport.setPreferredSize(new Dimension(250,25));
		panelProductReportCenter.add(cmbSubcatagoryNameProductReport,cn);
		cmbSubcatagoryNameProductReport.setBackground(Color.white);
		cn.gridx=2;
		cn.gridy=1;
		chkAllSubcatagoryNameProductReport.setFont(font1);
		panelProductReportCenter.add(chkAllSubcatagoryNameProductReport,cn);//
		cn.gridx=0;
		cn.gridy=2;
		lblProductNameProductReport.setFont(font1);
		panelProductReportCenter.add(lblProductNameProductReport,cn);
		cn.gridx=1;
		cn.gridy=2;
		cmbProductNameProductReport.setPreferredSize(new Dimension(250,25));
		panelProductReportCenter.add(cmbProductNameProductReport,cn);
		cmbProductNameProductReport.setBackground(Color.white);
		cn.gridx=2;
		cn.gridy=2;
		chkAllProductNameProductReport.setFont(font1);
		panelProductReportCenter.add(chkAllProductNameProductReport,cn);//
		cn.gridx=0;
		cn.gridy=3;
		lblFromDateProductReport.setFont(font1);
		panelProductReportCenter.add(lblFromDateProductReport,cn);
		cn.gridx=1;
		cn.gridy=3;
		panelProductReportCenter.add(dateFromDateProductReport,cn);
		dateFromDateProductReport.setBackground(Color.white);
		cn.gridx=0;
		cn.gridy=4;
		lblToDateProductReport.setFont(font1);
		panelProductReportCenter.add(lblToDateProductReport,cn);
		cn.gridx=1;
		cn.gridy=4;
		panelProductReportCenter.add(dateToDateProductReport,cn);
		dateToDateProductReport.setBackground(Color.white);
	}
	////...............................................................................
	public void panelProductReportSouth()
	{
		panelProductReportSouth.setBackground(Color.white);
		panelProductReportSouth.setPreferredSize(new Dimension(1,60));
		FlowLayout flow=new FlowLayout();
		flow.setHgap(10);
		panelProductReportSouth.setLayout(flow);
		btnRefreshProductReport.setForeground(Color.black);
		btnRefreshProductReport.setBackground(new Color(0,134,139));
		btnRefreshProductReport.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		btnPreviewProductReport.setForeground(Color.black);
		btnPreviewProductReport.setBackground(new Color(0,134,139));
		btnPreviewProductReport.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		panelProductReportSouth.add(btnRefreshProductReport);
		panelProductReportSouth.add(btnPreviewProductReport);
	}
	////...............................................................................
	public void panelStockReport()
	{
		panelStockReport.setBackground(new Color(2, 191, 185));
		panelStockReport.setPreferredSize(new Dimension(570,1));
		TitledBorder panelStockReportTitle=new TitledBorder("Stock Report");
		panelStockReportTitle.setTitleFont(new Font("Agency FB",Font.BOLD,20));
		panelStockReportTitle.setTitleColor(Color.WHITE);
		panelStockReportTitle.setTitleJustification(TitledBorder.CENTER);
		panelStockReportTitle.setTitlePosition(TitledBorder.TOP);
		panelStockReport.setBorder(panelStockReportTitle);
		BorderLayout border=new BorderLayout();
		panelStockReport.setLayout(border);
		panelStockReport.add(panelStockReportNorth,BorderLayout.NORTH);
		panelStockReportNorth();
		panelStockReport.add(panelStockReportCenter,BorderLayout.CENTER);
		panelStockReportCenter();
		panelStockReport.add(panelStockReportSouth,BorderLayout.SOUTH);
		panelStockReportSouth();
	}
	public void panelStockReportNorth()
	{
		panelStockReportNorth.setOpaque(false);
		//panelStockReportNorth.setBorder(BorderFactory.createLoweredBevelBorder());
		panelStockReportNorth.setPreferredSize(new Dimension(1,50));
		FlowLayout flow=new FlowLayout();
		flow.setVgap(10);
		flow.setHgap(15);
		panelStockReportNorth.setLayout(flow);
		lblReportTypeStockReport.setFont(font);
		panelStockReportNorth.add(lblReportTypeStockReport);
		rdoStockSummery.setFont(font);
		panelStockReportNorth.add(rdoStockSummery);
		rdoStockDetails.setFont(font);
		panelStockReportNorth.add(rdoStockDetails);
	}
	public void panelStockReportCenter()
	{
		panelStockReportCenter.setLayout(new BorderLayout());
		panelStockReportCenter.add(panelStockReportCenterWest,BorderLayout.WEST);
		panelStockReportCenterWest();
		panelStockReportCenter.add(panelStockReportCenterCenter,BorderLayout.CENTER);
		panelStockReportCenterCenter();
	}
	public void panelStockReportCenterWest()
	{
		panelStockReportCenterWest.setBackground(Color.white);
		panelStockReportCenterWest.setPreferredSize(new Dimension(130,1));
		GridBagLayout grid=new GridBagLayout();
		panelStockReportCenterWest.setLayout(grid);
		GridBagConstraints cn=new GridBagConstraints();
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=0;
		chkCatagoryWise.setFont(font1);
		panelStockReportCenterWest.add(chkCatagoryWise,cn);
		cn.gridx=0;
		cn.gridy=1;
		chkDateWiseStockReport.setFont(font1);
		panelStockReportCenterWest.add(chkDateWiseStockReport,cn);
		
	}
	public void panelStockReportCenterCenter()
	{
		panelStockReportCenterCenter.setBackground(Color.white);
		GridBagLayout grid=new GridBagLayout();
		panelStockReportCenterCenter.setLayout(grid);
		GridBagConstraints cn=new GridBagConstraints();
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5,0,5,0);
		cn.gridx=0;
		cn.gridy=0;
		lblCatagoryNameStockReport.setFont(font1);
		panelStockReportCenterCenter.add(lblCatagoryNameStockReport,cn);
		cn.gridx=1;
		cn.gridy=0;
		cmbCatagoryNameStockReport.setPreferredSize(new Dimension(170,25));
		panelStockReportCenterCenter.add(cmbCatagoryNameStockReport,cn);
		cmbCatagoryNameStockReport.setBackground(Color.white);
		cn.gridx=2;
		cn.gridy=0;
		chkAllCatagoryNameStockReport.setFont(font1);
		panelStockReportCenterCenter.add(chkAllCatagoryNameStockReport,cn);//
		cn.gridx=0;
		cn.gridy=1;
		lblSubcatagoryNameStockReport.setFont(font1);
		panelStockReportCenterCenter.add(lblSubcatagoryNameStockReport,cn);
		cn.gridx=1;
		cn.gridy=1;
		cmbSubCatagoryNameStockReport.setPreferredSize(new Dimension(170,25));
		panelStockReportCenterCenter.add(cmbSubCatagoryNameStockReport,cn);
		cmbSubCatagoryNameStockReport.setBackground(Color.white);
		cn.gridx=2;
		cn.gridy=1;
		chkAllSubCatagoryNameStockReport.setFont(font1);
		panelStockReportCenterCenter.add(chkAllSubCatagoryNameStockReport,cn);//
		cn.gridx=0;
		cn.gridy=2;
		lblProductNameStockReport.setFont(font1);
		panelStockReportCenterCenter.add(lblProductNameStockReport,cn);
		cn.gridx=1;
		cn.gridy=2;
		cmbProductNameStockReport.setPreferredSize(new Dimension(170,25));
		panelStockReportCenterCenter.add(cmbProductNameStockReport,cn);
		cmbProductNameStockReport.setBackground(Color.white);
		cn.gridx=2;
		cn.gridy=2;
		chkAllProductNameStockReport.setFont(font1);
		panelStockReportCenterCenter.add(chkAllProductNameStockReport,cn);//
		cn.gridx=0;
		cn.gridy=3;
		lblFromDateStockReport.setFont(font1);
		panelStockReportCenterCenter.add(lblFromDateStockReport,cn);
		cn.gridx=1;
		cn.gridy=3;
		panelStockReportCenterCenter.add(dateFromDateStockReport,cn);
		dateFromDateStockReport.setBackground(Color.white);
		cn.gridx=0;
		cn.gridy=4;
		lblToDateStockReport.setFont(font1);
		panelStockReportCenterCenter.add(lblToDateStockReport,cn);
		cn.gridx=1;
		cn.gridy=4;
		panelStockReportCenterCenter.add(dateToDateStockReport,cn);
		dateToDateStockReport.setBackground(Color.white);
	}
	////.................................................................................
	public void panelStockReportSouth()
	{
		panelStockReportSouth.setBackground(Color.white);
		panelStockReportSouth.setPreferredSize(new Dimension(1,60));
		FlowLayout flow=new FlowLayout();
		flow.setHgap(10);
		panelStockReportSouth.setLayout(flow);
		panelStockReportSouth.add(btnRefreshStockReport);
		panelStockReportSouth.add(btnPreviewStockReport);
		btnRefreshStockReport.setForeground(Color.black);
		btnRefreshStockReport.setBackground(new Color(0,134,139));
		btnRefreshStockReport.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		btnPreviewStockReport.setForeground(Color.black);
		btnPreviewStockReport.setBackground(new Color(0,134,139));
		btnPreviewStockReport.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
	}
	////..................................................................................
	public void init()
	{
		setPreferredSize(new Dimension(1140,740));
		BorderLayout border=new BorderLayout();
		border.setVgap(25);
		setLayout(border);
	}
}
