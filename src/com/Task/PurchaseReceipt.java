package com.Task;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.Admin.Dbconnection;
import com.Admin.SessionBean;
import com.Admin.SuggestText;
import com.toedter.calendar.JDateChooser;

public class PurchaseReceipt extends JPanel
{
	private static final long serialVersionUID = 1L;
	SessionBean sessionBean;

	JPanel PurchaseReciptNorth=new JPanel();
	JPanel PurchaseReciptCenter=new JPanel();
	JPanel PurchaseReciptSouth=new JPanel();
	JPanel PurchaseReciptSouthCenter=new JPanel();
	JPanel PurchaseReciptSouthSouth=new JPanel();
	JPanel PurchaseReciptCenterEast=new JPanel();
	JPanel PurchaseReciptCenterCenter=new JPanel();
	JPanel PurchaseReciptCenterCenterCenter=new JPanel();
	JPanel PurchaseReciptCenterCenterCenterEast = new JPanel();
	JPanel PurchaseReciptCenterCenterCenterCenter = new JPanel();
	JPanel PurchaseReciptCenterCenterSouth=new JPanel();
	JPanel PurchaseReciptCenterEastNorth=new JPanel();
	JPanel PurchaseReciptCenterEastCenter=new JPanel();

	JLabel lblInvoiceNo = new JLabel("Invoice No.");
	JLabel lblUserName = new JLabel("User Name");
	JLabel lblDate = new JLabel("Current Date");
	JLabel lblInvoiceDate = new JLabel("Invoice Date");
	JLabel lblCategory = new JLabel("Category");
	JLabel lblSubCategory=new JLabel("Sub Category");
	JLabel lblProduct=new JLabel("Product");
	JLabel lblUnit=new JLabel("Unit");
	JLabel lblStockQty=new JLabel("Stock Qty");
	JLabel lblDealerPrice=new JLabel("Dealer Price");
	JLabel lblInvoiceQuantity=new JLabel("Invoice Qty");
	JLabel lblReciveQuantity=new JLabel("Receive Qty");
	JLabel lblAmount = new JLabel("Amount");
	JLabel lblShortOverQuantity=new JLabel("Short/Over Qty");
	JLabel lblPresentStock=new JLabel("Present Stock");
	JLabel lblSupplier=new JLabel("Supplier");
	JLabel lblRemarks=new JLabel("Remarks");
	JLabel lblFromDate=new JLabel("From Date");
	JLabel lblToDate=new JLabel("To Date");
	JLabel lblTotalAmount=new JLabel("Total Amount");
	JTextField txtInvoiceNo=new JTextField(15);
	JTextField txtUserName=new JTextField(15);
	JTextField txtUnit=new JTextField(15);
	JTextField txtStockQty=new JTextField(15);
	JTextField txtDealerPrice=new JTextField(15);
	JTextField txtInvoiceQuantity=new JTextField(15);
	JTextField txtReciveQuantity=new JTextField(15);
	JTextField txtAmount = new JTextField(15);
	JTextField txtShortOverQuantity=new JTextField(15);
	JTextField txtPresentStock=new JTextField(15);
	JTextField txtTotalAmount=new JTextField(15);
	JTextArea txtRemarks=new JTextArea(3,1);
	JScrollPane scrollRemarks=new JScrollPane(txtRemarks,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	JComboBox<String> cmbCategory=new JComboBox<>();
	JComboBox<String> cmbSubCategory=new JComboBox<>();
	SuggestText cmbProduct = new SuggestText();
	JComboBox<String> cmbSupplier=new JComboBox<String>();
	JDateChooser jdcFromDate=new JDateChooser();
	JDateChooser jdcToDate=new JDateChooser();
	JDateChooser jdcDate=new JDateChooser();
	JDateChooser jdcInvoiceDate=new JDateChooser();
	JButton btnSubmit=new JButton("Submit",new ImageIcon("images/btnSubmit.png"));
	JButton btnEdit=new JButton("Edit     ",new ImageIcon("images/btnEdit.png"));
	JButton btnRefresh=new JButton("Refresh",new ImageIcon("images/btnRefresh.png"));
	JButton btnConfirm=new JButton("Confirm",new ImageIcon("images/confirm.png"));
	JButton btnClear=new JButton("Clear   ",new ImageIcon("images/btnRefresh.png"));
	JButton btnfind=new JButton(" Find ");

	String[] column={"Product ID","Product Name","Unit","Dealer Price","Stock Qty","Invoice Qty","Receive Qty","Short Qty","Present stock","Supplier id","Supplier Name","Amount","Remarks"};
	Object[][]row={};
	DefaultTableModel modelInsert=new DefaultTableModel(row,column)
	{
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int column)
		{
			return false;
		}
	};
	JTable tableInsert=new JTable(modelInsert);
	JScrollPane scrollMain=new JScrollPane(tableInsert,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	String[] column2={"Invoice No.","Total Amount","Invoice Date"};
	Object[][] row2={};
	DefaultTableModel modelFind=new DefaultTableModel(row2,column2)
	{
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row2, int column2)
		{
			return false;
		}
	};
	JTable tableFind=new JTable(modelFind);
	JScrollPane scrollFind=new JScrollPane(tableFind,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	GridBagConstraints c=new GridBagConstraints();
	Font font = new Font("Baskerville O",Font.BOLD,12);
	Font font2 = new Font("Baskerville O",Font.BOLD,17);
	BufferedImage image;
	DecimalFormat df = new DecimalFormat("#0.00");
	DecimalFormat dfQty = new DecimalFormat("#0");
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	boolean isFind = false;
	boolean isEdit = false;
	boolean isSearch = true;

	public PurchaseReceipt(SessionBean bean)
	{
		init();
		cmp();
		btnAction();
		btnIni(true);
		defaultEditable(false);
		this.sessionBean = bean;
		txtUserName.setText(sessionBean.getUserName());
	}
	private void btnIni(boolean b)
	{
		btnSubmit.setEnabled(b);
		btnEdit.setEnabled(!b);
	}
	private void defaultEditable(boolean e)
	{
		txtInvoiceNo.setEditable(e);
		txtUserName.setEditable(e);
		cmbCategory.setEnabled(e);
		cmbSubCategory.setEnabled(e);
		cmbSupplier.setEnabled(e);
		txtUnit.setEditable(e);
		txtDealerPrice.setEditable(e);
		txtStockQty.setEditable(e);
		txtShortOverQuantity.setEditable(e);
		txtPresentStock.setEditable(e);
		txtAmount.setEditable(e);
		txtTotalAmount.setEditable(e);
	}
	private void isSearchEditable(boolean s)
	{
		cmbProduct.txtSuggest.setEnabled(s);
		txtInvoiceQuantity.setEditable(s);
		txtReciveQuantity.setEditable(s);
		txtRemarks.setEditable(s);
	}
	private void refreshProductWise()
	{
		cmbCategory.setSelectedItem("");
		cmbSubCategory.setSelectedItem("");
		cmbSupplier.setSelectedItem("");
		txtUnit.setText("");
		txtDealerPrice.setText("");
		txtStockQty.setText("");
	}
	private void refreshWork()
	{
		cmbProduct.txtSuggest.setText("");
		cmbProduct.combowork();
		refreshProductWise();
		txtInvoiceQuantity.setText("");
		txtReciveQuantity.setText("");
		txtShortOverQuantity.setText("");
		txtPresentStock.setText("");
		txtAmount.setText("");
		txtRemarks.setText("");
	}
	private void clearWork()
	{
		refreshWork();
		autoid();
		tableFindDataLoad();
		removeSubmitTableData();
		jdcDate.setDate(new Date());
		jdcInvoiceDate.setDate(new Date());
		jdcFromDate.setDate(new Date());
		jdcToDate.setDate(new Date());
		txtTotalAmount.setText("");
		btnSubmit.setEnabled(true);
		btnConfirm.setEnabled(true);
		btnRefresh.setEnabled(true);
		isSearchEditable(true);
		btnIni(true);
		isFind = false;
		isEdit = false;
		isSearch = true;
	}
	private void calcPresentStock()
	{
		double stock, receiveQty, presentStock;
		stock = Double.parseDouble(txtStockQty.getText().toString().trim().isEmpty()?"0":txtStockQty.getText().toString());
		receiveQty = Double.parseDouble(txtReciveQuantity.getText().toString().trim().isEmpty()?"0":txtReciveQuantity.getText().toString());
		presentStock = stock+receiveQty;
		txtPresentStock.setText(dfQty.format(presentStock));
	}
	private void calcShortOverQty()
	{
		double shortOverQty, invoiceQty, receiveQty;
		invoiceQty = Double.parseDouble(txtInvoiceQuantity.getText().toString().trim().isEmpty()?"0":txtInvoiceQuantity.getText().toString());
		receiveQty = Double.parseDouble(txtReciveQuantity.getText().toString().trim().isEmpty()?"0":txtReciveQuantity.getText().toString());
		shortOverQty = invoiceQty-receiveQty;
		txtShortOverQuantity.setText(dfQty.format(shortOverQty));
	}
	private void calcAmount()
	{
		double rate, receiveQty, amount;
		rate = Double.parseDouble(txtDealerPrice.getText().toString().trim().isEmpty()?"0":txtDealerPrice.getText().toString());
		receiveQty = Double.parseDouble(txtReciveQuantity.getText().toString().trim().isEmpty()?"0":txtReciveQuantity.getText().toString());
		amount = rate*receiveQty;
		txtAmount.setText(df.format(amount));
	}
	private void calcTotalAmount()
	{
		double total = 0;
		for(int a=0; a<tableInsert.getRowCount(); a++)
		{
			total = total+Double.parseDouble(tableInsert.getValueAt(a, 11).toString());
		}
		txtTotalAmount.setText(df.format(total));
	}
	private boolean checkValidation()
	{
		if(!cmbProduct.txtSuggest.getText().trim().isEmpty())
		{
			if(!txtInvoiceQuantity.getText().trim().isEmpty())
			{
				if(!txtReciveQuantity.getText().trim().isEmpty())
				{
					return true;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Enter Receive Quantity!","Warning..",JOptionPane.WARNING_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please Enter Invoice Quantity!","Warning..",JOptionPane.WARNING_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please Select a Product id or Name!","Warning..",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	private boolean checkDoubleEntry()
	{
		StringTokenizer token = new StringTokenizer(cmbProduct.txtSuggest.getText().trim(), "~");
		String productid = token.nextToken();

		for(int x=0; x<modelInsert.getRowCount(); x++)
		{
			String proid = modelInsert.getValueAt(x, 0).toString();
			if(productid.equalsIgnoreCase(proid))
			{
				JOptionPane.showMessageDialog(null, "Doble Entry!\nPlease don't entry already exist data.","warning",JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
		return true;
	}
	private void tableSubmitDataLoad()
	{
		String product = cmbProduct.txtSuggest.getText().trim();
		StringTokenizer token = new StringTokenizer(product, "~");
		String productid = token.nextToken();
		String productName = token.nextToken();

		String supplier = cmbSupplier.getSelectedItem().toString();
		StringTokenizer token1 = new StringTokenizer(supplier, "~");
		String supid = token1.nextToken();
		String supName = token1.nextToken();

		modelInsert.addRow(new Object[]{productid,productName,txtUnit.getText().trim(),txtDealerPrice.getText().trim(),
				txtStockQty.getText().trim(),txtInvoiceQuantity.getText().trim(),txtReciveQuantity.getText().trim(),
				txtShortOverQuantity.getText().trim(),txtPresentStock.getText().trim(),supid,supName,
				txtAmount.getText().trim(),txtRemarks.getText().trim()});
	}
	private boolean checkValidationConfirm()
	{
		if(tableInsert.getRowCount()>=1)
		{
			return true;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please provide all data!","warning",JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}
	private boolean checkConfirmation(String confirm)
	{
		int a = JOptionPane.showConfirmDialog(null, confirm,"confirmation",JOptionPane.YES_NO_OPTION);
		if(a==JOptionPane.YES_OPTION)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	private boolean insertData()
	{
		try
		{
			Dbconnection.connection();
			Dbconnection.con.setAutoCommit(false);
			String sqlinfo = "insert into tbPurchaseinfo(invoiceNo,invoiceDate,curDate,totalAmount,userName,userip,entryTime)"
					+ "values ('"+txtInvoiceNo.getText().trim()+"','"+dateFormat.format(jdcInvoiceDate.getDate())+"',"
					+ "'"+dateFormat.format(jdcDate.getDate())+"','"+txtTotalAmount.getText().trim()+"','"+txtUserName.getText().trim()+"','',now())";
			Dbconnection.sta.executeUpdate(sqlinfo);

			for(int a=0; a<tableInsert.getRowCount(); a++)
			{
				String sqlDetails = "insert into tbPurchaseDetails(invoiceNo,productid,productName,"
						+ "unit,dealerPrice,stockQty,invoiceQty,receiveQty,shortQty,supplierid,supplierName,amount,remark)"
						+ "values ('"+txtInvoiceNo.getText().trim()+"',"
						+ "'"+modelInsert.getValueAt(a, 0)+"','"+modelInsert.getValueAt(a, 1)+"',"
						+ "'"+modelInsert.getValueAt(a, 2)+"','"+modelInsert.getValueAt(a, 3)+"',"
						+ "'"+modelInsert.getValueAt(a, 4)+"','"+modelInsert.getValueAt(a, 5)+"',"
						+ "'"+modelInsert.getValueAt(a, 6)+"','"+modelInsert.getValueAt(a, 7)+"',"
						+ "'"+modelInsert.getValueAt(a, 9)+"','"+modelInsert.getValueAt(a, 10)+"',"
						+ "'"+modelInsert.getValueAt(a, 11)+"','"+modelInsert.getValueAt(a, 12)+"')";
				Dbconnection.sta.executeUpdate(sqlDetails);
			}
			Dbconnection.con.commit();
			Dbconnection.con.close();
			return true;
		}
		catch(Exception e)
		{
			try
			{
				Dbconnection.con.rollback();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Error from PurchaseReceipt rollback()","error",JOptionPane.ERROR_MESSAGE);
			}
			JOptionPane.showMessageDialog(null, "Error from insertData PurchaseReceipt","error",JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	private boolean deleteData()
	{
		try
		{
			String sqlinfo = "delete from tbPurchaseinfo where invoiceNo = '"+txtInvoiceNo.getText()+"'";
			String sqlDetails = "delete from tbPurchaseDetails where invoiceNo = '"+txtInvoiceNo.getText()+"'";
			Dbconnection.connection();
			Dbconnection.sta.executeUpdate(sqlinfo);
			Dbconnection.sta.executeUpdate(sqlDetails);
			Dbconnection.con.close();
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		return false;
	}
	public void tableFindDataLoad()
	{
		for(int x=modelFind.getRowCount()-1; x>=0; x--)
		{
			modelFind.removeRow(x);
		}
		try
		{
			String sql = "Select invoiceNo,totalAmount,invoiceDate from tbPurchaseInfo order by cast(substring(invoiceNo,locate('-',invoiceNo)+1,length(invoiceNo)" +
					"-locate('-',invoiceNo))as UNSIGNED) DESC";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				modelFind.addRow(new Object[]{rs.getString("invoiceNo"),rs.getString("totalAmount"),rs.getString("invoiceDate")});
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"Error from tableFindDataLoad PurchaseReceipt","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void removeSubmitTableData()
	{
		for(int a=modelInsert.getRowCount()-1; a>=0; a--)
		{
			modelInsert.removeRow(a);
		}
	}
	private void reEditDataSubmitTableWise()
	{
		int i = tableInsert.getSelectedRow();
		cmbProduct.cmbSuggest.setSelectedItem(modelInsert.getValueAt(i, 0)+"~"+modelInsert.getValueAt(i, 1));
		cmbProduct.txtSuggest.setText(modelInsert.getValueAt(i, 0)+"~"+modelInsert.getValueAt(i, 1));
		txtUnit.setText(modelInsert.getValueAt(i, 2).toString());
		txtDealerPrice.setText(modelInsert.getValueAt(i, 3).toString());
		txtStockQty.setText(modelInsert.getValueAt(i, 4).toString());
		txtInvoiceQuantity.setText(modelInsert.getValueAt(i, 5).toString());
		txtReciveQuantity.setText(modelInsert.getValueAt(i, 6).toString());
		cmbSupplier.addItem(modelInsert.getValueAt(i, 9)+"~"+modelInsert.getValueAt(i, 10));
		cmbSupplier.setSelectedItem(modelInsert.getValueAt(i, 9)+"~"+modelInsert.getValueAt(i, 10));
		txtRemarks.setText(modelInsert.getValueAt(i, 12).toString());
		calcAmount();
		calcPresentStock();
		calcShortOverQty();
		modelInsert.removeRow(i);
		calcTotalAmount();
		isSearchEditable(false);
		btnRefresh.setEnabled(false);
		isEdit = false;
		btnConfirm.setEnabled(false);
	}
	private void searchDataFindTableWise(String invoice)
	{
		String sql = "select a.invoiceNo,a.userName,a.curDate,a.invoiceDate,b.productid,b.productName,b.unit,"
				+ "0 stockQty,b.dealerPrice,b.invoiceQty,b.ReceiveQty,b.shortQty,"
				+ "0 presentStock,b.supplierId,b.supplierName,b.remark,b.amount from "
				+ "tbPurchaseinfo a inner join tbPurchaseDetails b on a.invoiceNo=b.invoiceNo "
				+ "where a.invoiceNo ='"+invoice+"'";
		for(int a=modelInsert.getRowCount()-1; a>=0; a--)
		{
			modelInsert.removeRow(a);
		}
		try
		{
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			int a=0;
			while(rs.next())
			{
				if(a==0)
				{
					txtInvoiceNo.setText(rs.getString("invoiceNo"));
					txtUserName.setText(rs.getString("userName"));
					jdcDate.setDate(rs.getDate("curDate"));
					jdcInvoiceDate.setDate(rs.getDate("invoiceDate"));
				}
				modelInsert.addRow(new Object[]
						{
								rs.getString("productid"),rs.getString("productName"),rs.getString("unit"),
								rs.getString("dealerPrice"),rs.getString("stockQty"),rs.getString("invoiceQty"),
								rs.getString("receiveQty"),rs.getString("shortQty"),rs.getString("presentStock"),
								rs.getString("supplierid"),rs.getString("supplierName"),rs.getString("amount"),
								rs.getString("remark")
						});
				a++;
			}
			Dbconnection.con.close();
			calcTotalAmount();
			refreshWork();
			btnRefresh.setEnabled(true);
			btnConfirm.setEnabled(false);
			isEdit = false;
			btnClear.setEnabled(true);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from SearchDataFindTableWise PurchaseReceipt","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void dateWiseFind()
	{
		try
		{
			for(int a=modelFind.getRowCount()-1; a>=0; a--)
			{
				modelFind.removeRow(a);
			}
			String sql = "Select invoiceNo,totalAmount,invoiceDate from tbPurchaseInfo where invoiceDate "
					+ "BETWEEN '"+dateFormat.format(jdcFromDate.getDate())+"' and '"+dateFormat.format(jdcToDate.getDate())+"'";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{
				modelFind.addRow(new Object[]{rs.getString("invoiceNo"),rs.getString("totalAmount"),rs.getString("invoiceDate")});
				while(rs.next())
				{
					modelFind.addRow(new Object[]{rs.getString("invoiceNo"),rs.getString("totalAmount"),rs.getString("invoiceDate")});
				}
				Dbconnection.con.close();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Sorry!\nWe are not find any invoice at this moment!","warning",JOptionPane.WARNING_MESSAGE);
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"DateWiseFind PurchaseReceipt","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void btnAction()
	{
		cmbProduct.cmbSuggest.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!cmbProduct.txtSuggest.getText().trim().isEmpty())
				{
					String product = cmbProduct.txtSuggest.getText().trim();
					StringTokenizer token = new StringTokenizer(product, "~");
					productWiseDataLoad(token.nextToken());
				}
				else
				{
					refreshProductWise();
				}
			}
		});
		txtStockQty.addKeyListener(new KeyListener() 
		{
			public void keyTyped(KeyEvent evt){}
			public void keyReleased(KeyEvent arg0) 
			{
				calcPresentStock();
			}
			public void keyPressed(KeyEvent arg0) {}
		});
		txtDealerPrice.addKeyListener(new KeyListener() 
		{
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) 
			{
				calcAmount();
			}
			public void keyPressed(KeyEvent arg0) {}
		});
		txtReciveQuantity.addKeyListener(new KeyListener() 
		{
			public void keyTyped(KeyEvent evt)
			{
				char ch = evt.getKeyChar();
				if(!((Character.isDigit(ch) || ch==KeyEvent.VK_BACK_SPACE || ch==KeyEvent.VK_DELETE)))
				{
					getToolkit().beep();
					evt.consume();
				}
			}
			public void keyReleased(KeyEvent arg0) 
			{
				calcPresentStock();
				calcShortOverQty();
				calcAmount();
			}
			public void keyPressed(KeyEvent arg0) {}
		});
		txtInvoiceQuantity.addKeyListener(new KeyListener() 
		{
			public void keyTyped(KeyEvent evt) 
			{
				char ch = evt.getKeyChar();
				if(!((Character.isDigit(ch) || ch==KeyEvent.VK_BACK_SPACE || ch==KeyEvent.VK_DELETE)))
				{
					getToolkit().beep();
					evt.consume();
				}
			}
			public void keyReleased(KeyEvent arg0) 
			{
				calcShortOverQty();
			}
			public void keyPressed(KeyEvent arg0) {}
		});
		tableInsert.addMouseListener(new MouseListener() 
		{
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) 
			{
				btnIni(false);
				if(!isEdit)
				{
					reEditDataSubmitTableWise();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please submit previous info!","warning",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		tableFind.addMouseListener(new MouseListener() 
		{
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent arg0) 
			{
				if(isSearch)
				{
					isFind = true;
					searchDataFindTableWise(modelFind.getValueAt(tableFind.getSelectedRow(), 0).toString());
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Confirm Previous Invoice!","warning",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		tableFind.addKeyListener(new KeyListener() 
		{
			public void keyTyped(KeyEvent arg0){}
			public void keyReleased(KeyEvent arg0)
			{
				if(isSearch)
				{
					isFind = true;
					searchDataFindTableWise(modelFind.getValueAt(tableFind.getSelectedRow(), 0).toString());
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Confirm Previous Invoice!","warning",JOptionPane.WARNING_MESSAGE);
				}
			}
			public void keyPressed(KeyEvent arg0){}
		});
		btnSubmit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(checkValidation())
				{
					if(checkDoubleEntry())
					{
						tableSubmitDataLoad();
						calcTotalAmount();
						refreshWork();
						btnConfirm.setEnabled(true);
						isEdit = false;
						isSearch = false;
					}
				}
			}
		});
		btnConfirm.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{	
				if(checkValidationConfirm())
				{
					if(isFind)
					{
						if(checkConfirmation("Sure To Update?"))
						{
							if(deleteData())
							{
								if(insertData())
								{
									JOptionPane.showMessageDialog(null, "All information Updated Successfully!",
											"Information",JOptionPane.INFORMATION_MESSAGE);
									clearWork();
									btnClear.setEnabled(true);
									isSearch = true;
								}
							}
						}
						isFind = false;
					}
					else
					{
						if(checkConfirmation("Sure To Insert?"))
						{
							if(insertData())
							{
								JOptionPane.showMessageDialog(null, "All information Save Successfully!",
										"information",JOptionPane.INFORMATION_MESSAGE);
								clearWork();
							}
						}
					}
				}
			}
		});
		btnRefresh.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				refreshWork();
			}
		});
		btnEdit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				btnIni(true);
				isSearchEditable(true);
				btnRefresh.setEnabled(false);
			}
		});
		btnClear.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				clearWork();
				btnConfirm.setEnabled(true);
			}
		});
		btnfind.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				dateWiseFind();
			}
		});
	}
	public void autoid()
	{
		try
		{
			String sql = "select ifnull(max(cast(subString(invoiceNo,locate('-',invoiceNo)+1,length(invoiceNo)-locate('-',invoiceNo))as UNSIGNED)),0)+1 id from tbpurchaseinfo";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{
				String id = rs.getString("id").trim();
				txtInvoiceNo.setText("invoice-"+id);
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public void productLoad()
	{
		try
		{
			cmbProduct.v.clear();
			cmbProduct.v.add("");
			String sql = "select productid,productName from tbproductinfo ORDER by productName";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				String id = rs.getString("productid");
				String name = rs.getString("productName");
				String idName = id+"~"+name;
				cmbProduct.v.add(idName);
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(from productLoad purchaseReceipt)","Error..",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void productWiseDataLoad(String product)
	{
		try
		{
			String sql = "select catid,catName,subCatid,subCatName,supplierid,supplierName,"
					+ "(select productStock('"+product+"'))stockQty," +
					"unit,dealerPrice from tbproductinfo where productid ='"+product+"'";
			Dbconnection.connection();
			ResultSet rs=Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{
				cmbCategory.removeAllItems();
				cmbCategory.addItem("");
				cmbCategory.addItem(rs.getString("catid")+"~"+rs.getString("catName"));
				cmbCategory.setSelectedItem(rs.getString("catid")+"~"+rs.getString("catname"));
				String subcat = rs.getString("subCatid")+rs.getString("subCatName");
				if(!subcat.trim().isEmpty())
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
				cmbSupplier.removeAllItems();
				cmbSupplier.addItem("");
				cmbSupplier.addItem(rs.getString("supplierid")+"~"+rs.getString("supplierName"));
				cmbSupplier.setSelectedItem (rs.getString("supplierid")+"~"+rs.getString("supplierName"));
				txtStockQty.setText(rs.getString("stockQty"));
				txtUnit.setText(rs.getString("unit"));
				txtDealerPrice.setText(rs.getString("dealerPrice"));
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(productWiseDataLoad purchaseReceipt)","Error..",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void cmp()
	{
		add(PurchaseReciptNorth,BorderLayout.NORTH);
		PurchaseReciptNorth();
		add(PurchaseReciptCenter,BorderLayout.CENTER);
		PurchaseReciptCenter();
		add(PurchaseReciptSouth,BorderLayout.SOUTH);
		PurchaseReciptSouth();
	}
	@SuppressWarnings("static-access")
	public void PurchaseReciptNorth()
	{
		PurchaseReciptNorth.setBackground(new Color(2, 191, 185));
		PurchaseReciptNorth.setPreferredSize(new Dimension(0,100));
		TitledBorder PurchaseTitle=new TitledBorder("Purchase Receipt");
		PurchaseTitle.setTitleFont(new Font("Agency FB",Font.BOLD,22));
		PurchaseTitle.setTitleJustification(TitledBorder.CENTER);
		PurchaseTitle.setTitlePosition(PurchaseTitle.TOP);
		PurchaseReciptNorth.setBorder(PurchaseTitle);
		PurchaseReciptNorth.setLayout(new BorderLayout());
		PurchaseReciptNorth.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=0;
		c.gridy=0;
		PurchaseReciptNorth.add(lblInvoiceNo,c);
		lblInvoiceNo.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=1;
		c.gridy=0;
		PurchaseReciptNorth.add(txtInvoiceNo,c);//
		txtInvoiceNo.requestFocusInWindow();
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=2;
		c.gridy=0;
		PurchaseReciptNorth.add(lblUserName,c);
		lblUserName.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.insets=new Insets(5,5, 5, 90);
		c.gridx=3;
		c.gridy=0;
		PurchaseReciptNorth.add(txtUserName,c);//
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=4;
		c.gridy=0;
		PurchaseReciptNorth.add(lblDate,c);
		lblDate.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=5;
		c.gridy=0;
		jdcDate.setDateFormatString("dd-MM-yyyy");
		PurchaseReciptNorth.add(jdcDate,c);//
		jdcDate.setPreferredSize(new Dimension(122,20));
		jdcDate.setDate(new Date());
		jdcDate.setBackground(new Color(2, 191, 185));
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=6;
		c.gridy=0;
		PurchaseReciptNorth.add(lblInvoiceDate,c);
		lblInvoiceDate.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=7;
		c.gridy=0;
		jdcInvoiceDate.setDateFormatString("dd-MM-yyyy");
		PurchaseReciptNorth.add(jdcInvoiceDate,c);//
		jdcInvoiceDate.setPreferredSize(new Dimension(122,20));
		jdcInvoiceDate.setDate(new Date());
		jdcInvoiceDate.setBackground(new Color(2, 191, 185));
	}
	public void PurchaseReciptCenter()
	{
		BorderLayout border=new BorderLayout();
		border.setHgap(0);
		PurchaseReciptCenter.setLayout(border);
		PurchaseReciptCenter.add(PurchaseReciptCenterEast,BorderLayout.EAST);
		PurchaseReciptCenterEast();
		PurchaseReciptCenter.add(PurchaseReciptCenterCenter,BorderLayout.CENTER);
		PurchaseReciptCenterCenter();
	}
	@SuppressWarnings("static-access")
	public void PurchaseReciptCenterEast()
	{
		PurchaseReciptCenterEast.setBackground(new Color(2, 191, 185));
		PurchaseReciptCenterEast.setPreferredSize(new Dimension(500,0));
		TitledBorder titlePurchaseReciptCenterEast = new TitledBorder("Existing Purchase Invoice");
		titlePurchaseReciptCenterEast.setTitleFont(new Font("Agency FB", Font.PLAIN+Font.BOLD, 20));
		titlePurchaseReciptCenterEast.setTitleJustification(TitledBorder.CENTER);
		titlePurchaseReciptCenterEast.setTitlePosition(titlePurchaseReciptCenterEast.TOP);
		titlePurchaseReciptCenterEast.setTitleColor(Color.white);
		PurchaseReciptCenterEast.setBorder(titlePurchaseReciptCenterEast);
		PurchaseReciptCenterEast.setLayout(new BorderLayout());
		PurchaseReciptCenterEast.add(PurchaseReciptCenterEastNorth,BorderLayout.NORTH);
		PurchaseReciptCenterEastNorth();
		PurchaseReciptCenterEast.add(PurchaseReciptCenterEastCenter,BorderLayout.CENTER);
		PurchaseReciptCenterEastCenter();

	}
	public void PurchaseReciptCenterEastNorth()
	{
		PurchaseReciptCenterEastNorth.setBackground(Color.white);
		PurchaseReciptCenterEastNorth.setPreferredSize(new Dimension(0,70));
		PurchaseReciptCenterEastNorth.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5, 8, 5, 8);
		c.gridx=0;
		c.gridy=0;
		lblFromDate.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 12));
		PurchaseReciptCenterEastNorth.add(lblFromDate,c);
		c.gridx=1;
		c.gridy=0;
		jdcFromDate.setDateFormatString("dd-MM-yy");
		PurchaseReciptCenterEastNorth.add(jdcFromDate,c);//
		jdcFromDate.setDate(new Date());
		jdcFromDate.setBackground(Color.white);
		c.gridx=2;
		c.gridy=0;
		lblToDate.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 12));
		PurchaseReciptCenterEastNorth.add(lblToDate,c);
		c.gridx=3;
		c.gridy=0;
		jdcToDate.setDateFormatString("dd-MM-yy");
		PurchaseReciptCenterEastNorth.add(jdcToDate,c);//
		jdcToDate.setDate(new Date());
		jdcToDate.setBackground(Color.white);
		c.gridx=4;
		c.gridy=0;
		PurchaseReciptCenterEastNorth.add(btnfind,c);//
		btnfind.setBackground(new Color(0, 134, 139));
		btnfind.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 15));
	}
	public void PurchaseReciptCenterEastCenter()
	{
		PurchaseReciptCenterEastCenter.setBackground(Color.white);
		scrollFind.setPreferredSize(new Dimension(465,222));
		PurchaseReciptCenterEastCenter.add(scrollFind);
		scrollFind.getViewport().setBackground(Color.white);
		tableFind.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableFind.setOpaque(false);
		scrollFind.setOpaque(false);
		tableFind.setShowGrid(true);
		tableFind.setRowHeight(tableFind.getRowHeight()+2);
	}
	public void PurchaseReciptCenterCenter()
	{
		BorderLayout border = new BorderLayout();
		border.setVgap(0);
		PurchaseReciptCenterCenter.setLayout(border);
		PurchaseReciptCenterCenter.setBackground(new Color(2, 191, 185));
		PurchaseReciptCenterCenter.setLayout(new BorderLayout());
		PurchaseReciptCenterCenter.add(PurchaseReciptCenterCenterCenter,BorderLayout.CENTER);
		PurchaseReciptCenterCenterCenter();
		PurchaseReciptCenterCenter.add(PurchaseReciptCenterCenterSouth,BorderLayout.SOUTH);
		PurchaseReciptCenterCenterSouth();

	}
	public void PurchaseReciptCenterCenterCenter()
	{
		PurchaseReciptCenterCenterCenter.setBackground(new Color(2, 191, 185));
		PurchaseReciptCenterCenterCenter.setLayout(new BorderLayout());
		PurchaseReciptCenterCenterCenter.add(PurchaseReciptCenterCenterCenterCenter,BorderLayout.CENTER);
		PurchaseReciptCenterCenterCenterCenter();
		PurchaseReciptCenterCenterCenter.add(PurchaseReciptCenterCenterCenterEast, BorderLayout.EAST);
		PurchaseReciptCenterCenterCenterEast();	
	}
	private void PurchaseReciptCenterCenterCenterCenter()
	{
		PurchaseReciptCenterCenterCenterCenter.setBackground(Color.white);
		PurchaseReciptCenterCenterCenterCenter.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5, 20, 5, 5);
		c.gridx=0;
		c.gridy=0;
		lblProduct.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		PurchaseReciptCenterCenterCenterCenter.add(lblProduct,c);
		c.gridx=1;
		c.gridy=0;
		PurchaseReciptCenterCenterCenterCenter.add(cmbProduct.cmbSuggest,c);//
		cmbProduct.cmbSuggest.setBackground(Color.white);
		c.gridx=0;
		c.gridy=1;
		lblCategory.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		PurchaseReciptCenterCenterCenterCenter.add(lblCategory,c);
		c.gridx=1;
		c.gridy=1;
		PurchaseReciptCenterCenterCenterCenter.add(cmbCategory,c);//
		cmbCategory.setBackground(Color.white);
		c.gridx=0;
		c.gridy=2;
		lblSubCategory.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		PurchaseReciptCenterCenterCenterCenter.add(lblSubCategory,c);
		c.gridx=1;
		c.gridy=2;
		PurchaseReciptCenterCenterCenterCenter.add(cmbSubCategory,c);//
		cmbSubCategory.setBackground(Color.white);
		c.gridx=0;
		c.gridy=3;
		lblSupplier.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		PurchaseReciptCenterCenterCenterCenter.add(lblSupplier,c);
		c.gridx=1;
		c.gridy=3;
		PurchaseReciptCenterCenterCenterCenter.add(cmbSupplier,c);//
		cmbSupplier.setBackground(Color.white);
		c.gridx=0;
		c.gridy=4;
		lblUnit.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		PurchaseReciptCenterCenterCenterCenter.add(lblUnit,c);
		c.gridx=1;
		c.gridy=4;
		PurchaseReciptCenterCenterCenterCenter.add(txtUnit,c);//
		c.gridx=0;
		c.gridy=5;
		lblDealerPrice.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		PurchaseReciptCenterCenterCenterCenter.add(lblDealerPrice,c);
		c.gridx=1;
		c.gridy=5;
		PurchaseReciptCenterCenterCenterCenter.add(txtDealerPrice,c);//
		c.gridx=0;
		c.gridy=6;
		lblStockQty.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		PurchaseReciptCenterCenterCenterCenter.add(lblStockQty,c);
		c.gridx=1;
		c.gridy=6;
		PurchaseReciptCenterCenterCenterCenter.add(txtStockQty,c);//
	}
	private void PurchaseReciptCenterCenterCenterEast() 
	{
		PurchaseReciptCenterCenterCenterEast.setBackground(Color.white);
		PurchaseReciptCenterCenterCenterEast.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5, 2, 5, 20);
		c.gridx=0;
		c.gridy=0;
		lblInvoiceQuantity.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		PurchaseReciptCenterCenterCenterEast.add(lblInvoiceQuantity,c);
		c.gridx=1;
		c.gridy=0;
		PurchaseReciptCenterCenterCenterEast.add(txtInvoiceQuantity,c);//
		c.gridx=0;
		c.gridy=1;
		lblReciveQuantity.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		PurchaseReciptCenterCenterCenterEast.add(lblReciveQuantity,c);
		c.gridx=1;
		c.gridy=1;
		PurchaseReciptCenterCenterCenterEast.add(txtReciveQuantity,c);////
		c.gridx=0;
		c.gridy=2;
		lblShortOverQuantity.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		PurchaseReciptCenterCenterCenterEast.add(lblShortOverQuantity,c);
		c.gridx=1;
		c.gridy=2;
		PurchaseReciptCenterCenterCenterEast.add(txtShortOverQuantity,c);////
		c.gridx=0;
		c.gridy=3;
		lblPresentStock.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		PurchaseReciptCenterCenterCenterEast.add(lblPresentStock,c);
		c.gridx=1;
		c.gridy=3;
		PurchaseReciptCenterCenterCenterEast.add(txtPresentStock,c);////
		c.gridx=0;
		c.gridy=4;
		lblAmount.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		PurchaseReciptCenterCenterCenterEast.add(lblAmount,c);
		c.gridx=1;
		c.gridy=4;
		PurchaseReciptCenterCenterCenterEast.add(txtAmount,c);////
		c.gridx=0;
		c.gridy=5;
		lblRemarks.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		PurchaseReciptCenterCenterCenterEast.add(lblRemarks,c);
		c.gridx=1;
		c.gridy=5;
		PurchaseReciptCenterCenterCenterEast.add(scrollRemarks,c);//
	}
	public void PurchaseReciptCenterCenterSouth()
	{
		PurchaseReciptCenterCenterSouth.setBackground(Color.white);
		PurchaseReciptCenterCenterSouth.setPreferredSize(new Dimension(0,40));
		PurchaseReciptCenterCenterSouth.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5, 10, 12, 5);
		c.gridx=0;
		c.gridy=0;
		PurchaseReciptCenterCenterSouth.add(btnSubmit,c);
		btnSubmit.setBackground(new Color(0, 134, 139));
		btnSubmit.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 13));
		c.gridx=1;
		c.gridy=0;
		PurchaseReciptCenterCenterSouth.add(btnEdit,c);
		btnEdit.setForeground(Color.black);
		btnEdit.setBackground(new Color(0,134,139));
		btnEdit.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		c.gridx=2;
		c.gridy=0;
		PurchaseReciptCenterCenterSouth.add(btnRefresh,c);
		btnRefresh.setForeground(Color.black);
		btnRefresh.setBackground(new Color(0,134,139));
		btnRefresh.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
	}
	public void PurchaseReciptSouth()
	{
		PurchaseReciptSouth.setPreferredSize(new Dimension(0,300));
		PurchaseReciptSouth.setLayout(new BorderLayout());
		PurchaseReciptSouth.add(PurchaseReciptSouthCenter,BorderLayout.CENTER);
		PurchaseReciptSouthCenter();
		PurchaseReciptSouth.add(PurchaseReciptSouthSouth,BorderLayout.SOUTH);
		PurchaseReciptSouthSouth();
	}
	@SuppressWarnings("static-access")
	public void PurchaseReciptSouthCenter()
	{
		tableInsert.setAutoResizeMode(tableInsert.AUTO_RESIZE_OFF);
		tableInsert.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableInsert.getColumnModel().getColumn(1).setPreferredWidth(180);
		tableInsert.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableInsert.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableInsert.getColumnModel().getColumn(4).setPreferredWidth(100);
		tableInsert.getColumnModel().getColumn(5).setPreferredWidth(100);
		tableInsert.getColumnModel().getColumn(6).setPreferredWidth(100);
		tableInsert.getColumnModel().getColumn(7).setPreferredWidth(100);
		tableInsert.getColumnModel().getColumn(8).setPreferredWidth(100);
		tableInsert.getColumnModel().getColumn(9).setPreferredWidth(100);
		tableInsert.getColumnModel().getColumn(10).setPreferredWidth(180);
		tableInsert.getColumnModel().getColumn(11).setPreferredWidth(180);
		tableInsert.getColumnModel().getColumn(12).setPreferredWidth(180);
		tableInsert.getTableHeader().setReorderingAllowed(false);
		scrollMain.setPreferredSize(new Dimension(1125,220));
		PurchaseReciptSouthCenter.add(scrollMain);
		PurchaseReciptSouthCenter.setBackground(Color.white);
		scrollMain.getViewport().setBackground(Color.white);
		tableInsert.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableInsert.setOpaque(false);
		scrollMain.setOpaque(false);
		tableInsert.setShowGrid(true);
		tableInsert.setRowHeight(tableInsert.getRowHeight()+2);
	}
	public void PurchaseReciptSouthSouth()
	{
		PurchaseReciptSouthSouth.setBackground(Color.white);
		PurchaseReciptSouthSouth.setPreferredSize(new Dimension(0,70));
		//PurchaseReciptSouthSouth.setBorder(BorderFactory.createRaisedBevelBorder());
		PurchaseReciptSouthSouth.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5, 10, 10, 10);
		c.gridx=0;
		c.gridy=0;
		PurchaseReciptSouthSouth.add(btnConfirm,c);
		btnConfirm.setForeground(Color.black);
		btnConfirm.setBackground(new Color(0,134,139));
		btnConfirm.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,15));
		c.gridx=1;
		c.gridy=0;
		PurchaseReciptSouthSouth.add(btnClear,c);//
		btnClear.setForeground(Color.black);
		btnClear.setBackground(new Color(0,134,139));
		btnClear.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,15));
		c.insets=new Insets(5, 300, 10, 5);
		c.gridx=2;
		c.gridy=0;
		PurchaseReciptSouthSouth.add(lblTotalAmount,c);
		lblTotalAmount.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 20));
		c.insets=new Insets(5, 5, 10, 5);
		c.gridx=3;
		c.gridy=0;
		PurchaseReciptSouthSouth.add(txtTotalAmount,c);//
		txtTotalAmount.setFont(font2);
		txtTotalAmount.setForeground(Color.BLUE);
	}
	public void init()
	{
		setPreferredSize(new Dimension(1140,740));
		setLayout(new BorderLayout());
	}
}




