package com.Report;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import com.Admin.SessionBean;
import com.Admin.SuggestText;
import com.toedter.calendar.JDateChooser;
import com.Admin.Dbconnection;

public class SetupReport extends JPanel
{
	private static final long serialVersionUID = 1L;
	JPanel panelNorth = new JPanel();
	JPanel panelCenter = new JPanel();
	JPanel panelProductReport = new JPanel();
	JPanel panelSupplierReport = new JPanel();
	JPanel panelClientReport = new JPanel();
	JPanel panelStockReport = new JPanel();
	JPanel panelProductReportCenter = new JPanel();
	JPanel panelProductReportSouth = new JPanel();
	JPanel panelSupplierReportCenter = new JPanel();
	JPanel panelSupplierReportSouth = new JPanel();
	JPanel panelClientReportCenter = new JPanel();
	JPanel panelClientReportSouth = new JPanel();
	JPanel panelStockReportCenter = new JPanel();
	JPanel panelStockReportSouth = new JPanel();
	SuggestText cmbProductReport = new SuggestText();
	SuggestText cmbSupplierReport = new SuggestText();
	SuggestText cmbClientReport = new SuggestText();
	JLabel lblReportType = new JLabel("Report Type");
	JLabel lblCategory = new JLabel("Category");
	JLabel lblSubCategory = new JLabel("Sub Category");
	JLabel lblProduct = new JLabel("Product");
	JLabel lblTransactionNo = new JLabel("Transaction No.");
	JLabel lblFromDate = new JLabel("From Date");
	JLabel lblToDate = new JLabel("To Date");
	SuggestText cmbReportType = new SuggestText();
	JComboBox<String> cmbCategory = new JComboBox<>();
	JComboBox<String> cmbSubCategory = new JComboBox<>();
	SuggestText cmbProduct = new SuggestText();
	JComboBox<String> cmbTransactionNo = new JComboBox<>();
	JDateChooser jdcFromDate = new JDateChooser();
	JDateChooser jdcToDate = new JDateChooser();
	JCheckBox chkProductReport=new JCheckBox("All");
	JCheckBox chkSupplierReport=new JCheckBox("All");
	JCheckBox chkClientReport=new JCheckBox("All");
	JCheckBox chkCategory = new JCheckBox("All");
	JCheckBox chkSubCategory = new JCheckBox("All");
	JCheckBox chkProduct = new JCheckBox("All");
	JCheckBox chkTransaction = new JCheckBox("All");
	JButton btnPreviewProductReport=new JButton("Preview",new ImageIcon("images/preview.png"));
	JButton btnPreviewSupplierReport=new JButton("Preview",new ImageIcon("images/preview.png"));
	JButton btnPreviewClientReport=new JButton("Preview",new ImageIcon("images/preview.png"));
	JButton btnPreviewStockReport=new JButton("Preview",new ImageIcon("images/preview.png"));
	JButton btnPrintProductReport=new JButton("Print",new ImageIcon("images/print.png"));
	JButton btnPrintSupplierReport=new JButton("Print",new ImageIcon("images/print.png"));
	JButton btnPrintClientReport=new JButton("Print",new ImageIcon("images/print.png"));
	JButton btnResetStockReport = new JButton("Reset",new ImageIcon("images/Refresh.png"));
	Font font=new Font("Cambria",Font.BOLD,17);
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	SessionBean sessionBean;
	public SetupReport(SessionBean bean) 
	{
		init();
		cmp();
		setEventAction();
		stockReportTypeInit(true);
		this.sessionBean=bean;
	}
	private void stockReportTypeInit(boolean i)
	{
		lblReportType.setVisible(i);
		cmbReportType.txtSuggest.setVisible(i);
		lblTransactionNo.setVisible(!i);
		cmbTransactionNo.setVisible(!i);
		chkTransaction.setVisible(!i);
		lblCategory.setVisible(!i);
		cmbCategory.setVisible(!i);
		chkCategory.setVisible(!i);
		lblSubCategory.setVisible(!i);
		cmbSubCategory.setVisible(!i);
		chkSubCategory.setVisible(!i);
		lblProduct.setVisible(!i);
		cmbProduct.cmbSuggest.setVisible(!i);
		chkProduct.setVisible(!i);
		lblFromDate.setVisible(!i);
		jdcFromDate.setVisible(!i);
		lblToDate.setVisible(!i);
		jdcToDate.setVisible(!i);
	}
	private void refreshStock()
	{
		cmbReportType.txtSuggest.setText("");
		cmbProduct.txtSuggest.setText("");
		cmbProduct.txtSuggest.setEnabled(true);
		chkProduct.setSelected(false);
		cmbCategory.setSelectedItem("");
		cmbCategory.removeAllItems();
		cmbCategory.setEnabled(true);
		chkCategory.setSelected(false);
		cmbSubCategory.setSelectedItem("");
		cmbSubCategory.removeAllItems();
		cmbSubCategory.setEnabled(true);
		chkSubCategory.setSelected(false);
		cmbTransactionNo.setSelectedItem("");
		cmbTransactionNo.setEnabled(true);
		chkTransaction.setSelected(false);
		jdcToDate.setDate(new Date());
	}
	private void setEventAction() 
	{
		cmbProduct.cmbSuggest.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!cmbProduct.txtSuggest.getText().trim().isEmpty())
				{
					StringTokenizer token = new StringTokenizer(cmbProduct.txtSuggest.getText().trim(), "~");
					productWiseLoad(token.nextToken());
				}
			}
		});
		chkProductReport.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(chkProductReport.isSelected())
				{
					cmbProductReport.txtSuggest.setEnabled(false);
					cmbProductReport.txtSuggest.setText("");
				}
				else
				{
					cmbProductReport.txtSuggest.setEnabled(true);
				}
			}
		});
		chkSupplierReport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(chkSupplierReport.isSelected())
				{
					cmbSupplierReport.txtSuggest.setEnabled(false);
					cmbSupplierReport.txtSuggest.setText("");
				}
				else
				{
					cmbSupplierReport.txtSuggest.setEnabled(true);
				}
			}
		});
		chkClientReport.addActionListener(new ActionListener() 
		{

			public void actionPerformed(ActionEvent arg0) 
			{

				if(chkClientReport.isSelected())
				{
					cmbClientReport.txtSuggest.setEnabled(false);
					cmbClientReport.txtSuggest.setText("");
				}
				else
				{
					cmbClientReport.txtSuggest.setEnabled(true);
				}
			}
		});
		chkProduct.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(chkProduct.isSelected())
				{
					cmbProduct.txtSuggest.setEnabled(false);
					cmbProduct.cmbSuggest.setSelectedItem("");
					chkCategory.setSelected(true);
					chkSubCategory.setSelected(true);
					cmbCategory.setSelectedItem("");
					cmbSubCategory.setSelectedItem("");
					cmbCategory.setEnabled(false);
					cmbSubCategory.setEnabled(false);
				}
				else
				{
					cmbProduct.txtSuggest.setEnabled(true);
					chkCategory.setSelected(false);
					chkSubCategory.setSelected(false);
					cmbCategory.setEnabled(true);
					cmbSubCategory.setEnabled(true);
				}
			}
		});
		chkTransaction.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(chkTransaction.isSelected())
				{
					cmbTransactionNo.setEnabled(false);
					cmbTransactionNo.setSelectedIndex(0);
				}
				else
				{
					cmbTransactionNo.setEnabled(true);
				}
			}
		});
		cmbReportType.cmbSuggest.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!cmbReportType.txtSuggest.getText().trim().isEmpty())
				{
					reportTypeWork();
				}
				else
				{
					refreshStock();
					stockReportTypeInit(true);
				}
			}
		});
		btnPreviewProductReport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!cmbProductReport.txtSuggest.getText().trim().isEmpty() || chkProductReport.isSelected())
				{
					productPreviewAction();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please select any product or Check all product!","warning",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnPreviewSupplierReport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!cmbSupplierReport.txtSuggest.getText().trim().isEmpty() || chkSupplierReport.isSelected())
				{
					supplierPreviewAction();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Select any supplier or Check all supplier!","warning",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnPreviewClientReport.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!cmbClientReport.txtSuggest.getText().trim().isEmpty() || chkClientReport.isSelected())
				{
					clientPreviewAction();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Select Any Client or Check All Client!","warning",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnPreviewStockReport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!cmbReportType.txtSuggest.getText().trim().isEmpty())
				{
					stockPreviewAction();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please select any Report Type!","warning",JOptionPane.WARNING_MESSAGE);
				}
			}


		});
		btnResetStockReport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				refreshStock();
				stockReportTypeInit(true);
				cmbReportType.txtSuggest.setText("");
			}
		});
	}
	private void productPreviewAction()
	{
		String product = "%";
		if(!cmbProductReport.txtSuggest.getText().trim().isEmpty())
		{
			StringTokenizer token = new StringTokenizer(cmbProductReport.txtSuggest.getText().trim(), "~");
			product = token.nextToken();
		}
		String sql = "Select catName,subCatName,supplierName,productName,unit,productDesc,dealerPrice,tradePrice from tbProductinfo "
				+ "where productid like '"+product+"' order by catName,productName";
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("CompanyName", sessionBean.getCompanyName());
		hMap.put("Address", sessionBean.getCompanyAddress());
		hMap.put("DeveloperAddress", sessionBean.getDeveloperAddress());
		reportShowProduct("D:/javaspace/Electronics Store Management/ReportFile/ProductReport.jrxml",sql,hMap);
	}
	private void reportShowProduct(String jrxml, String query, HashMap<String, String>map) 
	{
		try
		{
			Dbconnection.connection();
			JasperDesign jd = JRXmlLoader.load(jrxml);
			JRDesignQuery newQuery = new JRDesignQuery();
			newQuery.setText(query);
			jd.setQuery(newQuery);
			JasperReport jr = JasperCompileManager.compileReport(jd);
			JasperPrint jp = JasperFillManager.fillReport(jr,map,Dbconnection.con);
			JasperViewer.viewReport(jp, false);
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"reportShowProduct SetupReport","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void supplierPreviewAction() 
	{
		String supplier = "%";
		if(!cmbSupplierReport.txtSuggest.getText().trim().isEmpty())
		{
			StringTokenizer token = new StringTokenizer(cmbSupplierReport.txtSuggest.getText().trim(), "~");
			supplier = token.nextToken();
		}
		String sql = "Select supplierName,address,phoneNumber,mailAddress from tbSupplierinfo "
				+ "where supplierid like '"+supplier+"' order by supplierName";
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("CompanyName", sessionBean.getCompanyName());
		hMap.put("Address", sessionBean.getCompanyAddress());
		hMap.put("DeveloperAddress", sessionBean.getDeveloperAddress());
		reportShowSupplier("D:/javaspace/Electronics Store Management/ReportFile/SupplierReport.jrxml", sql, hMap);
	}
	private void reportShowSupplier(String jrxml, String query, HashMap<String, String> map) 
	{
		try
		{
			Dbconnection.connection();
			JasperDesign jd = JRXmlLoader.load(jrxml);
			JRDesignQuery newQuery = new JRDesignQuery();
			newQuery.setText(query);
			jd.setQuery(newQuery);
			JasperReport jr = JasperCompileManager.compileReport(jd);
			JasperPrint jp = JasperFillManager.fillReport(jr, map, Dbconnection.con);
			JasperViewer.viewReport(jp, false);
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"reportShowSupplier SetupReport","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void clientPreviewAction() 
	{
		String client = "%";
		if(!cmbClientReport.txtSuggest.getText().trim().isEmpty())
		{
			StringTokenizer token=new StringTokenizer(cmbClientReport.txtSuggest.getText().trim(),"~");
			client=token.nextToken();
		}
		String sql = "select clientName,address,mobileNo,email,nid,refByid,refByName "
				+ "from tbclientinfo where clientid like '"+client+"' order by clientName";
		HashMap<String, String> hMap = new HashMap<String,String>();
		hMap.put("CompanyName", sessionBean.getCompanyName());
		hMap.put("Address", sessionBean.getCompanyAddress());
		hMap.put("DeveloperAddress", sessionBean.getDeveloperAddress());
		reportShowClient("D:/javaspace/Electronics Store Management/ReportFile/ClientReport.jrxml",sql,hMap);
	}
	public void reportShowClient(String jrxml, String query,HashMap<String, String> map) 
	{
		try
		{
			Dbconnection.connection();
			JasperDesign jd = JRXmlLoader.load(jrxml);
			JRDesignQuery newQuery = new JRDesignQuery();
			newQuery.setText(query);
			jd.setQuery(newQuery);
			JasperReport jr = JasperCompileManager.compileReport(jd);
			JasperPrint jp = JasperFillManager.fillReport(jr,map,Dbconnection.con);
			JasperViewer.viewReport(jp, false);
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"reportShowClient SetupReport","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void productLoad()
	{
		try
		{
			cmbProductReport.v.clear();
			cmbProductReport.v.add("");
			String sql = "Select productid,productName from tbProductinfo order by productName";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbProductReport.v.add(rs.getString("productid")+"~"+rs.getString("productName"));
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from productLoad SetupRetport","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void supplierLaod()
	{
		try
		{
			cmbSupplierReport.v.clear();
			cmbSupplierReport.v.add("");
			String sql = "Select supplierid,supplierName from tbSupplierinfo order by supplierName";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbSupplierReport.v.add(rs.getString("supplierid")+"~"+rs.getString("supplierName"));
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from supplierLoad SetupReport","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void clientLoad() 
	{
		try
		{
			cmbClientReport.v.clear();
			cmbClientReport.v.add("");
			Dbconnection.connection();
			String sql="select clientId,clientName from tbClientINfo order by clientName";
			ResultSet rs=Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbClientReport.v.add(rs.getString("clientId")+"~"+rs.getString("clientName"));
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from clientLoad SetupReport","error",JOptionPane.ERROR_MESSAGE);
		}
	}

	////////////////////////Stock Report
	public void productLoadStock()
	{
		cmbProduct.v.clear();
		cmbProduct.v.add("");
		try
		{
			String sql = "select productid, productName from tbProductinfo order by productName";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbProduct.v.add(rs.getString("productid")+"~"+rs.getString("productName"));
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from productLoad SetupReport");
		}
	}
	public void productWiseLoad(String product)
	{
		try
		{
			String sql = "Select catid,catName,subCatid,subCatName from tbOpeningStock where productid  like '"+product+"'";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{
				cmbCategory.removeAllItems();
				cmbCategory.addItem("");
				cmbCategory.addItem(rs.getString("catid")+"~"+rs.getString("catName"));
				cmbCategory.setSelectedItem(rs.getString("catid")+"~"+rs.getString("catName"));
				String subCat = rs.getString("subCatid")+rs.getString("subCatName");
				if(!subCat.trim().isEmpty())
				{
					cmbSubCategory.removeAllItems();
					cmbSubCategory.addItem("");
					cmbSubCategory.addItem(rs.getString("subCatid")+"~"+rs.getString("subCatName"));
					cmbSubCategory.setSelectedItem(rs.getString("subCatid")+"~"+rs.getString("subCatName"));
				}
				else
				{
					cmbSubCategory.setSelectedIndex(0);
				}
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"productLoadStock SetupReport","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void stockReportTrueFalse(boolean b)
	{
		lblTransactionNo.setVisible(b);
		cmbTransactionNo.setVisible(b);
		chkTransaction.setVisible(b);
		lblCategory.setVisible(!b);
		cmbCategory.setVisible(!b);
		chkCategory.setVisible(!b);
		lblSubCategory.setVisible(!b);
		cmbSubCategory.setVisible(!b);
		chkSubCategory.setVisible(!b);
		lblProduct.setVisible(!b);
		cmbProduct.cmbSuggest.setVisible(!b);
		chkProduct.setVisible(!b);
		lblFromDate.setVisible(!b);
		jdcFromDate.setVisible(!b);
		lblToDate.setVisible(!b);
		jdcToDate.setVisible(!b);
	}
	private void reportTypeWork()
	{
		if(cmbReportType.txtSuggest.getText().trim().equalsIgnoreCase("Profit Statement"))
		{
			stockReportTrueFalse(false);
		}
		else if(cmbReportType.txtSuggest.getText().trim().equalsIgnoreCase("Opening Stock"))
		{
			stockReportTrueFalse(false);
			lblFromDate.setVisible(false);
			jdcFromDate.setVisible(false);
			lblToDate.setVisible(false);
			jdcToDate.setVisible(false);
		}
		else
		{
			stockReportTrueFalse(true);
			if(cmbReportType.txtSuggest.getText().trim().equalsIgnoreCase("Purchase Receipt"))
			{
				reportTypeWiseTransactionLoad("Purchase Receipt");
			}
			else if(cmbReportType.txtSuggest.getText().trim().equalsIgnoreCase("Sales Invoice"))
			{
				reportTypeWiseTransactionLoad("Sales Invoice");
			}
			else if(cmbReportType.txtSuggest.getText().trim().equalsIgnoreCase("Purchase Return"))
			{
				reportTypeWiseTransactionLoad("Purchase Return");
			}
			else if(cmbReportType.txtSuggest.getText().trim().equalsIgnoreCase("Return Invoice"))
			{
				reportTypeWiseTransactionLoad("Return Invoice");
			}
		}
	}
	private void reportTypeWiseTransactionLoad(String transaction) 
	{
		String sql = "";
		cmbTransactionNo.removeAllItems();
		cmbTransactionNo.addItem("");
		if(transaction.equalsIgnoreCase("Purchase Receipt"))
		{
			sql = "select invoiceNo transactionNo from tbPurchaseInfo";
		}
		else if(transaction.equalsIgnoreCase("Sales Invoice"))
		{
			sql = "select salesNo transactionNo from tbSalesEntryinfo";
		}
		else if(transaction.equalsIgnoreCase("Purchase Return"))
		{
			sql = "select returnNo transactionNo from tbPurchaseReturninfo";
		}
		else if(transaction.equalsIgnoreCase("Return Invoice"))
		{
			sql = "select salesReturnNo transactionNo from tbSalesReturninfo";
		}
		Dbconnection.connection();
		try
		{
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbTransactionNo.addItem(rs.getString("transactionNo"));
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"reportTypeWiseTransactionLoad SetupReport");
		}
	}
	private void stockPreviewAction() 
	{
		String catid = "%",subCatid = "%",productid = "%",transactionNo = "%",sql = "";
		if(cmbReportType.txtSuggest.getText().trim().equalsIgnoreCase("Opening Stock") ||
				cmbReportType.txtSuggest.getText().trim().equalsIgnoreCase("Profit Statement"))
		{
			if(cmbCategory.getSelectedIndex()!=0 && cmbCategory.getSelectedItem()!=null)
			{
				StringTokenizer token = new StringTokenizer(cmbCategory.getSelectedItem().toString(), "~");
				catid = token.nextToken();
			}
			if(cmbSubCategory.getSelectedIndex()!=0 && cmbSubCategory.getSelectedItem()!=null)
			{
				StringTokenizer token = new StringTokenizer(cmbSubCategory.getSelectedItem().toString(), "~");
				subCatid = token.nextToken();
			}
			if(!cmbProduct.txtSuggest.getText().trim().isEmpty())
			{
				StringTokenizer token = new StringTokenizer(cmbProduct.txtSuggest.getText().trim(), "~");
				productid = token.nextToken();
			}
		}
		else
		{
			transactionNo = cmbTransactionNo.getSelectedItem().toString();
		}
		HashMap<String, String> hasMap = new HashMap<String, String>();
		hasMap.put("CompanyName", sessionBean.getCompanyName());
		hasMap.put("Address", sessionBean.getCompanyAddress());
		hasMap.put("Phone","");
		if(cmbReportType.txtSuggest.getText().trim().equalsIgnoreCase("Opening Stock"))
		{
			if(!cmbProduct.txtSuggest.getText().trim().isEmpty() || chkProduct.isSelected())
			{
				if(chkProduct.isSelected())
				{
					sql = "select catId,catName,subcatId,subCatName,productId,productName,unit,"
							+ "stockQty,dealerPrice,amount from tbOpeningStock where "
							+ "catId like '%' and subcatId like '%'"
							+ " and productId like '%' order by catName,subCatName,productName";
					reportShowProduct("D:/javaspace/Electronics Store Management/ReportFile/OpeningStock.jrxml", sql, hasMap);
				}
				else
				{
					sql = "select catId,catName,subcatId,subCatName,productId,productName,unit,"
							+ "stockQty,dealerPrice,amount from tbOpeningStock where "
							+ "catId = '"+catid+"' and subcatId = '"+subCatid+"'"
							+ " and productId = '"+productid+"' order by catName,subCatName,productName";
					reportShowProduct("D:/javaspace/Electronics Store Management/ReportFile/OpeningStock.jrxml", sql, hasMap);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please Select any product or Check all product!","warning",JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(cmbReportType.txtSuggest.getText().trim().equalsIgnoreCase("Profit Statement"))
		{
			if(!cmbProduct.txtSuggest.getText().trim().isEmpty() && chkProduct.isSelected() || jdcFromDate.getDate()!=null )
			{
				if(chkProduct.isSelected())
				{
					sql="select c.catid,c.catName,c.subcatid,c.subcatname,b.productid,b.productName,"
							+ "b.unit,b.dealerPrice,b.tradePrice,ifnull(sum(b.salesQty),0)salesQty "
							+ "from tbSalesEntryinfo a inner join tbSalesEntryDetails b on a.salesNo = b.salesNo "
							+ "inner join tbproductinfo c on b.productId = c.productid where a.curDate "
							+ "between '"+dateFormat.format(jdcFromDate.getDate())+"' and '"+dateFormat.format(jdcToDate.getDate())+"' "
							+ "and c.catid like '%' and c.subcatid like '%' "
							+ "and b.productid like '%' group by b.productid,b.productName,"
							+ "b.unit,b.dealerPrice,b.tradePrice order by c.catName,c.subCatName,b.productName ";
					reportShowProduct("D:/javaspace/Electronics Store Management/ReportFile/ProfitSummary.jrxml",sql,hasMap);
				}
				else
				{
					sql="select c.catid,c.catName,c.subcatid,c.subcatname,b.productid,b.productName,"
							+ "b.unit,b.dealerPrice,b.tradePrice,ifnull(sum(b.salesQty),0)salesQty "
							+ "from tbSalesEntryinfo a inner join tbSalesEntryDetails b on a.salesNo = b.salesNo "
							+ "inner join tbproductinfo c on b.productId = c.productid where a.curDate "
							+ "between '"+dateFormat.format(jdcFromDate.getDate())+"' and '"+dateFormat.format(jdcToDate.getDate())+"' "
							+ "and c.catid like '"+catid+"' and c.subcatid like '"+subCatid+"' "
							+ "and b.productid like '"+productid+"' group by b.productid,b.productName,"
							+ "b.unit,b.dealerPrice,b.tradePrice order by c.catName,c.subCatName,b.productName ";
					reportShowProduct("D:/javaspace/Electronics Store Management/ReportFile/ProfitSummary.jrxml",sql,hasMap);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please Select All Info!","warning",JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(cmbReportType.txtSuggest.getText().trim().equalsIgnoreCase("Purchase Receipt"))
		{
			if(cmbTransactionNo.getSelectedIndex()!=0 && cmbTransactionNo.getSelectedItem()!=null || chkTransaction.isSelected())
			{
				if(chkTransaction.isSelected())
				{
					sql="select b.supplierName,b.InvoiceNo,a.invoiceDate,b.productName,"+
							"b.InvoiceQty,b.ReceiveQty,b.ShortQty,b.dealerPrice,a.totalAmount,"+
							"b.remark from tbpurchaseinfo a inner join tbpurchasedetails b "+
							"on a.InvoiceNo=b.InvoiceNo where a.InvoiceNo like '%'";
					reportShowProduct("D:/javaspace/Electronics Store Management/ReportFile/PurchaseReceipt.jrxml",sql,hasMap);
				}
				else
				{
					sql="select b.supplierName,b.InvoiceNo,a.invoiceDate,b.productName,"+
							"b.InvoiceQty,b.ReceiveQty,b.ShortQty,b.dealerPrice,a.totalAmount,"+
							"b.remark from tbpurchaseinfo a inner join tbpurchasedetails b "+
							"on a.InvoiceNo=b.InvoiceNo where a.InvoiceNo = '"+transactionNo+"'";
					reportShowProduct("D:/javaspace/Electronics Store Management/ReportFile/PurchaseReceipt.jrxml",sql,hasMap);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please select any transaction no. or check all transaction!","warning",JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(cmbReportType.txtSuggest.getText().trim().equalsIgnoreCase("Purchase Return"))
		{
			if(cmbTransactionNo.getSelectedIndex()!=0 && cmbTransactionNo.getSelectedItem()!=null || chkTransaction.isSelected())
			{
				if(chkTransaction.isSelected())
				{
					sql="select a.returnNo,a.returnDate,b.supplierName,"
							+ "b.productName,b.returnQty,b.amount,b.remark,b.dealerprice from  "
							+ "tbPurchaseReturninfo a inner join tbPurchaseReturndetails b on a.returnNo=b.returnNo "
							+ "where a.returnNo like '%'";
					reportShowProduct("D:/javaspace/Electronics Store Management/ReportFile/PurchaseReturn.jrxml",sql,hasMap);
				}
				else
				{
					sql="select a.returnNo,a.returnDate,b.supplierName,"
							+ "b.productName,b.returnQty,b.amount,b.remark,b.dealerprice from  "
							+ "tbPurchaseReturninfo a inner join tbPurchaseReturndetails b on a.returnNo=b.returnNo "
							+ "where a.returnNo = '"+transactionNo+"'";
					reportShowProduct("D:/javaspace/Electronics Store Management/ReportFile/PurchaseReturn.jrxml",sql,hasMap);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please select any transaction no. or check all transaction!","warning",JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(cmbReportType.txtSuggest.getText().trim().equalsIgnoreCase("Sales Invoice"))
		{
			if(cmbTransactionNo.getSelectedIndex()!=0 && cmbTransactionNo.getSelectedItem()!=null || chkTransaction.isSelected())
			{
				if(chkTransaction.isSelected())
				{
					sql="select a.salesNo,a.clientType,a.clientName,a.curdate,a.payProtocol,"
							+ "a.reference,a.totalAmount,a.discount,a.paidAmount,a.due,b.productId,"
							+ "b.productName, b.unit,b.salesQty,b.tradeprice,b.amount,b.remark "
							+ "from tbsalesentryinfo a inner join tbsalesentrydetails b "
							+ "on a.salesNo=b.salesNo "
							+ "where a.salesNo like '%'";
					reportShowProduct("D:/javaspace/Electronics Store Management/ReportFile/SalesInvoice.jrxml",sql,hasMap);
				}
				else
				{
					sql="select a.salesNo,a.clientType,a.clientName,a.curdate,a.payProtocol,"
							+ "a.reference,a.totalAmount,a.discount,a.paidAmount,a.due,b.productId,"
							+ "b.productName, b.unit,b.salesQty,b.tradeprice,b.amount,b.remark "
							+ "from tbsalesentryinfo a inner join tbsalesentrydetails b "
							+ "on a.salesNo=b.salesNo "
							+ "where a.salesNo = '"+transactionNo+"'";
					reportShowProduct("D:/javaspace/Electronics Store Management/ReportFile/SalesInvoice.jrxml",sql,hasMap);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please any transaction no. or check all transaction!","warning",JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(cmbReportType.txtSuggest.getText().trim().equalsIgnoreCase("Return Invoice"))
		{
			if(cmbTransactionNo.getSelectedIndex()!=0 && cmbTransactionNo.getSelectedItem()!=null || chkTransaction.isSelected())
			{
				if(chkTransaction.isSelected())
				{
					sql="select (select clientName from tbSalesEntryinfo where salesNo=a.salesNo)clientName,"
							+ "a.salesReturnNo,a.returnDate,b.productName,b.receiveQty,"
							+ "b.tradeprice,b.amount,b.remark from tbSalesReturninfo a inner join "
							+ "tbSalesReturndetails b on a.salesReturnNo=b.salesReturnNo "
							+ "where a.salesReturnNo like '%'";
					reportShowProduct("D:/javaspace/Electronics Store Management/ReportFile/ReturnInvoice.jrxml",sql,hasMap);
				}
				else
				{
					sql="select (select clientName from tbSalesEntryinfo where salesNo=a.salesNo)clientName,"
							+ "a.salesReturnNo,a.returnDate,b.productName,b.receiveQty,"
							+ "b.tradeprice,b.amount,b.remark from tbSalesReturninfo a inner join "
							+ "tbSalesReturndetails b on a.salesReturnNo=b.salesReturnNo "
							+ "where a.salesReturnNo = '"+transactionNo+"'";
					reportShowProduct("D:/javaspace/Electronics Store Management/ReportFile/ReturnInvoice.jrxml",sql,hasMap);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please select any transaction no. or check all transaction!","warning",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	private void cmp() 
	{
		add(panelNorth,BorderLayout.NORTH);
		panelNorth();
		add(panelCenter,BorderLayout.CENTER);
		panelCenter();
	}
	public void panelProductReportCenter()
	{
		panelProductReportCenter.setBackground(Color.white);
		GridBagLayout grid = new GridBagLayout();
		panelProductReportCenter.setLayout(grid);
		GridBagConstraints cn=new GridBagConstraints();
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=0;
		cmbProductReport.cmbSuggest.setPreferredSize(new Dimension(250,35));
		panelProductReportCenter.add(cmbProductReport.cmbSuggest,cn);
		cmbProductReport.cmbSuggest.setBackground(Color.white);
		cn.gridx=1;
		cn.gridy=0;
		panelProductReportCenter.add(chkProductReport,cn);
		chkProductReport.setFont(font);
		chkProductReport.setBackground(Color.white);
	}
	public void panelProductReportSouth()
	{
		panelProductReportSouth.setBackground(Color.white);
		panelProductReportSouth.setPreferredSize(new Dimension(0,100));
		FlowLayout flow=new FlowLayout();
		flow.setHgap(10);
		panelProductReportSouth.setLayout(flow);
		btnPreviewProductReport.setPreferredSize(new Dimension(120,45));
		panelProductReportSouth.add(btnPreviewProductReport);
		btnPreviewProductReport.setForeground(Color.black);
		btnPreviewProductReport.setBackground(new Color(0,134,139));
		btnPreviewProductReport.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));

		btnPrintProductReport.setPreferredSize(new Dimension(120,45));
		panelProductReportSouth.add(btnPrintProductReport);
		btnPrintProductReport.setForeground(Color.black);
		btnPrintProductReport.setBackground(new Color(0,134,139));
		btnPrintProductReport.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
	}
	@SuppressWarnings("static-access")
	public void panelProductReport()
	{
		panelProductReport.setPreferredSize(new Dimension(570,1));
		panelProductReport.setBackground(new Color(2, 191, 185));
		TitledBorder productreportTitle=new TitledBorder("Product Report");
		productreportTitle.setTitleFont(new Font("Agency FB",Font.BOLD,20));
		productreportTitle.setTitleColor(Color.black);
		productreportTitle.setTitleJustification(TitledBorder.CENTER);
		productreportTitle.setTitlePosition(productreportTitle.TOP);
		panelProductReport.setBorder(productreportTitle);
		BorderLayout border=new BorderLayout();
		panelProductReport.setLayout(border);
		panelProductReport.add(panelProductReportCenter,BorderLayout.CENTER);
		panelProductReportCenter();
		panelProductReport.add(panelProductReportSouth,BorderLayout.SOUTH);
		panelProductReportSouth();
	}
	public void panelSupplierReportCenter()
	{
		panelSupplierReportCenter.setBackground(Color.white);
		GridBagLayout grid=new GridBagLayout();
		panelSupplierReportCenter.setLayout(grid);
		GridBagConstraints cn=new GridBagConstraints();
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=0;
		cmbSupplierReport.cmbSuggest.setPreferredSize(new Dimension(250,35));
		cmbSupplierReport.cmbSuggest.setBackground(Color.white);
		panelSupplierReportCenter.add(cmbSupplierReport.cmbSuggest,cn);
		cn.gridx=1;
		cn.gridy=0;
		panelSupplierReportCenter.add(chkSupplierReport,cn);
		chkSupplierReport.setFont(font);
		chkSupplierReport.setBackground(Color.white);
	}
	public void panelSupplierReportSouth()
	{
		panelSupplierReportSouth.setBackground(Color.white);
		panelSupplierReportSouth.setPreferredSize(new Dimension(0,100));
		FlowLayout flow=new FlowLayout();
		flow.setHgap(10);
		panelSupplierReportSouth.setLayout(flow);
		btnPreviewSupplierReport.setPreferredSize(new Dimension(120,45));
		panelSupplierReportSouth.add(btnPreviewSupplierReport);
		btnPreviewSupplierReport.setForeground(Color.black);
		btnPreviewSupplierReport.setBackground(new Color(0,134,139));
		btnPreviewSupplierReport.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));

		btnPrintSupplierReport.setPreferredSize(new Dimension(120,45));
		panelSupplierReportSouth.add(btnPrintSupplierReport);
		btnPrintSupplierReport.setForeground(Color.black);
		btnPrintSupplierReport.setBackground(new Color(0,134,139));
		btnPrintSupplierReport.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
	}
	@SuppressWarnings("static-access")
	public void panelSupplierReport()
	{
		panelSupplierReport.setPreferredSize(new Dimension(570,1));
		panelSupplierReport.setBackground(new Color(2, 191, 185));
		TitledBorder supplierreportTitle=new TitledBorder("Supplier Report");
		supplierreportTitle.setTitleFont(new Font("Agency FB",Font.BOLD,20));
		supplierreportTitle.setTitleColor(Color.black);
		supplierreportTitle.setTitleJustification(TitledBorder.CENTER);
		supplierreportTitle.setTitlePosition(supplierreportTitle.TOP);
		panelSupplierReport.setBorder(supplierreportTitle);
		BorderLayout border=new BorderLayout();
		panelSupplierReport.setLayout(border);
		panelSupplierReport.add(panelSupplierReportCenter,BorderLayout.CENTER);
		panelSupplierReportCenter();
		panelSupplierReport.add(panelSupplierReportSouth,BorderLayout.SOUTH);
		panelSupplierReportSouth();
	}
	public void panelNorth()
	{
		panelNorth.setPreferredSize(new Dimension(1140,365));
		BorderLayout border=new BorderLayout();
		border.setHgap(0);
		panelNorth.setLayout(border);
		panelNorth.add(panelProductReport,BorderLayout.WEST);
		panelProductReport();
		panelNorth.add(panelSupplierReport,BorderLayout.CENTER);
		panelSupplierReport();

	}
	@SuppressWarnings("static-access")
	public void panelClientReport()
	{
		panelClientReport.setPreferredSize(new Dimension(570,1));
		panelClientReport.setBackground(new Color(2, 191, 185));
		TitledBorder clientreportTitle=new TitledBorder("Client Report");
		clientreportTitle.setTitleFont(new Font("Agency FB",Font.BOLD,20));
		clientreportTitle.setTitleColor(Color.black);
		clientreportTitle.setTitleJustification(TitledBorder.CENTER);
		clientreportTitle.setTitlePosition(clientreportTitle.TOP);
		panelClientReport.setBorder(clientreportTitle);
		BorderLayout border=new BorderLayout();
		panelClientReport.setLayout(border);
		panelClientReport.add(panelClientReportCenter,BorderLayout.CENTER);
		panelClientReportCenter();
		panelClientReport.add(panelClientReportSouth,BorderLayout.SOUTH);
		panelClientReportSouth();
	}
	public void panelClientReportCenter()
	{
		panelClientReportCenter.setBackground(Color.white);
		GridBagLayout grid=new GridBagLayout();
		panelClientReportCenter.setLayout(grid);
		GridBagConstraints cn=new GridBagConstraints();
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=0;
		cmbClientReport.cmbSuggest.setPreferredSize(new Dimension(250,35));
		panelClientReportCenter.add(cmbClientReport.cmbSuggest,cn);
		cmbClientReport.cmbSuggest.setBackground(Color.white);
		cn.gridx=1;
		cn.gridy=0;
		panelClientReportCenter.add(chkClientReport,cn);
		chkClientReport.setFont(font);
		chkClientReport.setBackground(Color.white);
	}
	public void panelClientReportSouth()
	{
		panelClientReportSouth.setBackground(Color.white);
		panelClientReportSouth.setPreferredSize(new Dimension(0,100));
		FlowLayout flow=new FlowLayout();
		flow.setHgap(10);
		panelClientReportSouth.setLayout(flow);
		btnPreviewClientReport.setPreferredSize(new Dimension(120,45));
		panelClientReportSouth.add(btnPreviewClientReport);
		btnPreviewClientReport.setForeground(Color.black);
		btnPreviewClientReport.setBackground(new Color(0,134,139));
		btnPreviewClientReport.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		btnPrintClientReport.setPreferredSize(new Dimension(120,45));
		panelClientReportSouth.add(btnPrintClientReport);
		btnPrintClientReport.setForeground(Color.black);
		btnPrintClientReport.setBackground(new Color(0,134,139));
		btnPrintClientReport.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
	}
	@SuppressWarnings("static-access")
	public void panelStockReport()
	{
		panelStockReport.setPreferredSize(new Dimension(570,1));
		panelStockReport.setBackground(new Color(2, 191, 185));
		TitledBorder stockreportTitle=new TitledBorder("Stock Report");
		stockreportTitle.setTitleFont(new Font("Agency FB",Font.BOLD,20));
		stockreportTitle.setTitleColor(Color.black);
		stockreportTitle.setTitleJustification(TitledBorder.CENTER);
		stockreportTitle.setTitlePosition(stockreportTitle.TOP);
		panelStockReport.setBorder(stockreportTitle);
		BorderLayout border=new BorderLayout();
		panelStockReport.setLayout(border);
		panelStockReport.add(panelStockReportCenter,BorderLayout.CENTER);
		panelStockReportCenter();
		panelStockReport.add(panelStockReportSouth,BorderLayout.SOUTH);
		panelStockReportSouth();
	}
	public void panelStockReportCenter()
	{
		panelStockReportCenter.setBackground(Color.white);
		GridBagLayout grid=new GridBagLayout();
		panelStockReportCenter.setLayout(grid);
		GridBagConstraints cn=new GridBagConstraints();
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(2, 1, 2, 5);
		cn.gridx=0;
		cn.gridy=0;
		lblReportType.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		panelStockReportCenter.add(lblReportType,cn);
		cn.gridx=1;
		cn.gridy=0;
		panelStockReportCenter.add(cmbReportType.cmbSuggest,cn);
		cmbReportType.cmbSuggest.setPreferredSize(new Dimension(250,35));
		cmbReportType.cmbSuggest.setFont(new Font("Arial Narrow", Font.CENTER_BASELINE, 14));
		cmbReportType.cmbSuggest.setBackground(Color.white);
		cmbReportType.v.add("");
		cmbReportType.v.add("Opening Stock");
		cmbReportType.v.add("Purchase Receipt");
		cmbReportType.v.add("Purchase Return");
		cmbReportType.v.add("Sales Invoice");
		cmbReportType.v.add("Invoice Bill");
		cmbReportType.v.add("Return Invoice");
		cmbReportType.v.add("Profit Statement");
		cmbReportType.v.add("Wastage Or Broken");

		cn.gridx=0;
		cn.gridy=1;
		lblProduct.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		panelStockReportCenter.add(lblProduct,cn);
		cn.gridx=1;
		cn.gridy=1;
		panelStockReportCenter.add(cmbProduct.cmbSuggest,cn);
		cmbProduct.cmbSuggest.setPreferredSize(new Dimension(250,30));
		cmbProduct.cmbSuggest.setBackground(Color.white);
		cn.gridx=2;
		cn.gridy=1;
		panelStockReportCenter.add(chkProduct,cn);
		chkProduct.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD,14));
		cn.gridx=0;
		cn.gridy=2;
		lblCategory.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD,14));
		panelStockReportCenter.add(lblCategory,cn);
		cn.gridx=1;
		cn.gridy=2;
		panelStockReportCenter.add(cmbCategory,cn);
		cmbCategory.setPreferredSize(new Dimension(250,30));
		cmbCategory.setBackground(Color.white);
		cn.gridx=2;
		cn.gridy=2;
		panelStockReportCenter.add(chkCategory,cn);
		chkCategory.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD,14));
		cn.gridx=0;
		cn.gridy=3;
		lblSubCategory.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD,14));
		panelStockReportCenter.add(lblSubCategory,cn);
		cn.gridx=1;
		cn.gridy=3;
		panelStockReportCenter.add(cmbSubCategory,cn);
		cmbSubCategory.setPreferredSize(new Dimension(250,30));
		cmbSubCategory.setBackground(Color.white);
		cn.gridx=2;
		cn.gridy=3;
		panelStockReportCenter.add(chkSubCategory,cn);
		chkSubCategory.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD,14));
		cn.gridx=0;
		cn.gridy=4;
		lblTransactionNo.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD,14));
		panelStockReportCenter.add(lblTransactionNo,cn);
		cn.gridx=1;
		cn.gridy=4;
		panelStockReportCenter.add(cmbTransactionNo,cn);
		cmbTransactionNo.setPreferredSize(new Dimension(250,30));
		cmbTransactionNo.setBackground(Color.white);
		cn.gridx=2;
		cn.gridy=4;
		panelStockReportCenter.add(chkTransaction,cn);
		chkTransaction.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.gridx=0;
		cn.gridy=5;
		lblFromDate.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		panelStockReportCenter.add(lblFromDate,cn);
		cn.gridx=1;
		cn.gridy=5;
		panelStockReportCenter.add(jdcFromDate,cn);
		jdcFromDate.setDateFormatString("yyyy-MM-dd");
		jdcFromDate.setPreferredSize(new Dimension(250,30));
		jdcFromDate.setBackground(Color.white);
		cn.gridx=0;
		cn.gridy=6;
		lblToDate.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		panelStockReportCenter.add(lblToDate,cn);
		cn.gridx=1;
		cn.gridy=6;
		panelStockReportCenter.add(jdcToDate,cn);
		jdcToDate.setDateFormatString("yyyy-MM-dd");
		jdcToDate.setPreferredSize(new Dimension(250,30));
		jdcToDate.setDate(new Date());
		jdcToDate.setBackground(Color.white);
	}
	public void panelStockReportSouth()
	{
		panelStockReportSouth.setBackground(Color.white);
		panelStockReportSouth.setPreferredSize(new Dimension(0,60));
		FlowLayout flow=new FlowLayout();
		flow.setHgap(10);
		panelStockReportSouth.setLayout(flow);
		btnPreviewStockReport.setPreferredSize(new Dimension(120,45));
		panelStockReportSouth.add(btnPreviewStockReport);
		btnPreviewStockReport.setForeground(Color.black);
		btnPreviewStockReport.setBackground(new Color(0,134,139));
		btnPreviewStockReport.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));

		btnResetStockReport.setPreferredSize(new Dimension(120,45));
		panelStockReportSouth.add(btnResetStockReport);
		btnResetStockReport.setForeground(Color.black);
		btnResetStockReport.setBackground(new Color(0,134,139));
		btnResetStockReport.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
	}
	public void panelCenter()
	{
		panelCenter.setPreferredSize(new Dimension(1140,365));
		BorderLayout border=new BorderLayout();
		panelCenter.setLayout(border);
		border.setHgap(0);
		panelCenter.setLayout(border);
		panelCenter.add(panelClientReport,BorderLayout.WEST);
		panelClientReport();
		panelCenter.add(panelStockReport,BorderLayout.CENTER);
		panelStockReport();
	}
	public void init()
	{
		{
			setPreferredSize(new Dimension(1140,740));
			BorderLayout border=new BorderLayout();
			border.setVgap(0);
			setLayout(border);
		}
	}
}
