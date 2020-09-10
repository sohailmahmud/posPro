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

public class PurchaseReturn extends JPanel 
{
	private static final long serialVersionUID = 1L;
	SessionBean sessionBean;
	JPanel mainPanel=new JPanel();
	JPanel PurchaseReturnNorth=new JPanel();
	JPanel PurchaseReturnCenter=new JPanel();
	JPanel PurchaseReturnSouth=new JPanel();
	JPanel PurchaseReturnSouthCenter=new JPanel();
	JPanel PurchaseReturnSouthSouth=new JPanel();
	JPanel PurchaseReturnCenterEast=new JPanel();
	JPanel PurchaseReturnCenterCenter=new JPanel();
	JPanel PurchaseReturnCenterCenterCenter=new JPanel();
	JPanel PurchaseReturnCenterCenterCenterCenter = new JPanel();
	JPanel PurchaseReturnCenterCenterCenterEast = new JPanel();
	JPanel PurchaseReturnCenterCenterSouth=new JPanel();
	JPanel PurchaseReturnCenterEastNorth=new JPanel();
	JPanel PurchaseReturnCenterEastCenter=new JPanel();
	JLabel lblReturnNo=new JLabel("Return No.");
	JLabel lblUserName=new JLabel("User Name");
	JLabel lblDate=new JLabel("Current Date");
	JLabel lblReturnDate=new JLabel("Return Date");
	JLabel lblProduct = new JLabel("Product");
	JLabel lblCategory=new JLabel("Category");
	JLabel lblSubCategory=new JLabel("Sub Category");
	JLabel lblSupplier = new JLabel("Supplier");
	JLabel lblUnit = new JLabel("Unit");
	JLabel lblDealerPrice = new JLabel("Dealer Price");
	JLabel lblStockQty=new JLabel("Stock Qty");
	JLabel lblReturnQuantity=new JLabel(" Return Qty");
	JLabel lblAmount = new JLabel(" Amount");
	JLabel lblRemarks=new JLabel(" Remarks");
	JLabel lblFromDate=new JLabel("From Date");
	JLabel lblToDate=new JLabel("To Date");
	JLabel lblTotalAmount=new JLabel("Total Amount");
	JTextField txtReturnNo=new JTextField(15);
	JTextField txtUserName=new JTextField(15);
	JTextField txtReturnQuantity=new JTextField(15);
	JTextField txtStockQty=new JTextField(15);
	JTextField txtTotalAmount=new JTextField(15);
	JTextField txtUnit = new JTextField(15);
	JTextField txtDealerPrice = new JTextField(15);
	JTextField txtAmount = new JTextField(15);
	JTextArea txtRemarks=new JTextArea(3,1);
	JScrollPane scrollRemarks=new JScrollPane(txtRemarks,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	JComboBox<String> cmbCategory=new JComboBox<>();
	JComboBox<String> cmbSubCategory=new JComboBox<>();
	SuggestText cmbProduct=new SuggestText();
	JComboBox<String> cmbSupplier=new JComboBox<>();
	JDateChooser jdcFromDate=new JDateChooser();
	JDateChooser jdcToDate=new JDateChooser();
	JDateChooser jdcDate=new JDateChooser();
	JDateChooser jdcReturnDate=new JDateChooser();
	JButton btnSubmit=new JButton("Submit ",new ImageIcon("images/btnSubmit.png"));
	JButton btnEdit=new JButton("Edit      ",new ImageIcon("images/btnEdit.png"));
	JButton btnRefresh=new JButton("Refresh",new ImageIcon("images/btnRefresh.png"));
	JButton btnConfirm=new JButton("Confirm",new ImageIcon("images/confirm.png"));
	JButton btnClear=new JButton("Clear    ",new ImageIcon("images/btnRefresh.png"));
	JButton btnfind=new JButton(" Find ");
	String[] column={"Product ID","Product Name","Supplier ID","Supplier Name","Unit","Dealer Price","Stock Qty","Return Qty","Amount","Remarks"};
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
	String[] column2={"Return No.","Total Amount","Return Date"};
	Object[][] row2={};
	DefaultTableModel modelFind=new DefaultTableModel(row2,column2)
	{
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row2,int column2)
		{
			return false;
		}
	};
	JTable tableFind=new JTable(modelFind);
	JScrollPane scrollExistingInvoice=new JScrollPane(tableFind,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	GridBagConstraints c=new GridBagConstraints();
	Font font=new Font("Baskerville O",Font.BOLD,12);
	Font font2=new Font("Baskerville O",Font.BOLD,17);
	BufferedImage image;
	DecimalFormat df = new DecimalFormat("#0.00");
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Color bgColor = new Color(2, 191, 185);
	Color btnColor = new Color(0, 134, 139);
	boolean isFind = false;
	boolean isEdit = false;
	boolean isSearch = true;

	public PurchaseReturn(SessionBean bean)
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
		txtReturnNo.setEditable(e);
		txtUserName.setEditable(e);
		cmbCategory.setEnabled(e);
		cmbSubCategory.setEnabled(e);
		cmbSupplier.setEnabled(e);
		txtUnit.setEditable(e);
		txtDealerPrice.setEditable(e);
		txtStockQty.setEditable(e);
		txtAmount.setEditable(e);
		txtTotalAmount.setEditable(e);
	}
	private void isSearchEditable(boolean s)
	{
		cmbProduct.txtSuggest.setEnabled(s);
		txtReturnQuantity.setEditable(s);
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
		txtReturnQuantity.setText("");
		txtAmount.setText("");
		txtRemarks.setText("");
	}
	private void clearWork()
	{
		refreshWork();
		autoReturnNo();
		tableFindDataLoad();
		removeSubmitTableData();
		jdcDate.setDate(new Date());
		jdcReturnDate.setDate(new Date());
		jdcFromDate.setDate(new Date());
		jdcToDate.setDate(new Date());
		txtTotalAmount.setText("");
		btnSubmit.setEnabled(true);
		btnRefresh.setEnabled(true);
		isSearchEditable(true);
		btnIni(true);
		isFind = false;
		isEdit = false;
		isSearch = true;
	}
	private void calcAmount()
	{
		double dealerPrice, returnQty, amount;
		dealerPrice = Double.parseDouble(txtDealerPrice.getText().trim().isEmpty()?"0":txtDealerPrice.getText().trim());
		returnQty = Double.parseDouble(txtReturnQuantity.getText().trim().isEmpty()?"0":txtReturnQuantity.getText().trim());
		amount = returnQty*dealerPrice;
		txtAmount.setText(df.format(amount));
	}
	private void calcTotalAmount()
	{
		double total = 0;
		for(int x=0; x<tableInsert.getRowCount(); x++)
		{
			total = Double.parseDouble(tableInsert.getValueAt(x, 8).toString());
		}
		txtTotalAmount.setText(df.format(total));
	}
	private boolean checkValidation()
	{
		if(!cmbProduct.txtSuggest.getText().trim().isEmpty())
		{
			if(!txtReturnQuantity.getText().trim().isEmpty())
			{
				return true;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please Enter Return Qty!","warning",JOptionPane.WARNING_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please select a product!","warning",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	private boolean checkDoubleEntry()
	{
		StringTokenizer token = new StringTokenizer(cmbProduct.txtSuggest.getText().trim(), "~");
		String productid = token.nextToken();
		for(int i=0; i<modelInsert.getRowCount(); i++)
		{
			String product = modelInsert.getValueAt(i, 0).toString();
			if(productid.equalsIgnoreCase(product))
			{
				JOptionPane.showMessageDialog(null, "Double Entry!\nPlease don't entry already exist data!","warning",JOptionPane.WARNING_MESSAGE);
			}
		}
		return true;
	}
	private void tableSubmitDataLoad()
	{
		StringTokenizer token = new StringTokenizer(cmbProduct.txtSuggest.getText().trim(), "~");
		String productid = token.nextToken();
		String productName = token.nextToken();
		StringTokenizer token1 = new StringTokenizer(cmbSupplier.getSelectedItem().toString(), "~");
		String supplierid = token1.nextToken();
		String supplierName = token1.nextToken();
		modelInsert.addRow(new Object[]
				{
						productid,productName,supplierid,supplierName,txtUnit.getText().trim(),
						txtDealerPrice.getText().trim(),txtStockQty.getText().trim(),txtReturnQuantity.getText().trim(),
						txtAmount.getText().trim(),txtRemarks.getText().trim()
				});
	}
	private boolean checkValidationConfirm()
	{
		if(tableInsert.getRowCount()>=1)
		{
			return true;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please Provide All Data!","warning",JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}
	private boolean checkConfirmation(String confirm)
	{
		int a = JOptionPane.showConfirmDialog(null, confirm,"Confirm",JOptionPane.YES_NO_OPTION);
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
			String sqlinfo = "insert into tbPurchaseReturninfo(returnNo,curDate,returnDate,totalAmount,userName,userip,entryTime)"
					+ "values ('"+txtReturnNo.getText().trim()+"','"+dateFormat.format(jdcDate.getDate())+"',"
					+ "'"+dateFormat.format(jdcReturnDate.getDate())+"','"+txtTotalAmount.getText().trim()+"','"+txtUserName.getText().trim()+"','',now())";
			Dbconnection.sta.executeUpdate(sqlinfo);
			for(int a=0; a<tableInsert.getRowCount(); a++)
			{
				String sqlDetails = "insert into tbPurchaseReturnDetails(returnNo,productid,productName,supplierid,supplierName,unit,dealerPrice,stockQty,returnQty,amount,remark)"
						+ "values ('"+txtReturnNo.getText().trim()+"','"+modelInsert.getValueAt(a, 0)+"',"
						+ "'"+modelInsert.getValueAt(a, 1)+"','"+modelInsert.getValueAt(a, 2)+"',"
						+ "'"+modelInsert.getValueAt(a, 3)+"','"+modelInsert.getValueAt(a, 4)+"',"
						+ "'"+modelInsert.getValueAt(a, 5)+"','"+modelInsert.getValueAt(a, 6)+"',"
						+ "'"+modelInsert.getValueAt(a, 7)+"','"+modelInsert.getValueAt(a, 8)+"',"
						+ "'"+modelInsert.getValueAt(a, 9)+"')";
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
				JOptionPane.showMessageDialog(null, ex+"from inserdata rollback PurchaseReturn");
			}
			JOptionPane.showMessageDialog(null, e+"from insertData PurchaseReturn","error",JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	private boolean deleteData()
	{
		try
		{
			String sqlinfo = "delete from tbPurchaseReturninfo where returnNo = '"+txtReturnNo.getText().trim()+"'";
			String sqlDetails = "delete from tbPurchaseReturnDetails where returnNo = '"+txtReturnNo.getText().trim()+"'";
			Dbconnection.connection();
			Dbconnection.sta.executeUpdate(sqlinfo);
			Dbconnection.sta.executeUpdate(sqlDetails);
			Dbconnection.con.close();
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from deleteData PurchaseReturn");
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
			String sql = "Select returnNo,totalAmount,returnDate from tbPurchaseReturninfo Order by "
					+ "cast(substring(returnNo,locate('-',returnNo)+1,length(returnNo)" +
					"-locate('-',returnNo))as UNSIGNED) DESC";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				modelFind.addRow(new Object[]
						{
							rs.getString("returnNo"),rs.getString("totalAmount"),rs.getString("returnDate")
						});
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from tableFindDataLoad PurchaseReturn","error",JOptionPane.ERROR_MESSAGE);
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
		refreshWork();
		int i = tableInsert.getSelectedRow();
		cmbProduct.txtSuggest.setText(modelInsert.getValueAt(i, 0).toString()+"~"+modelInsert.getValueAt(i, 1));
		cmbProduct.cmbSuggest.setSelectedItem(modelInsert.getValueAt(i, 0).toString()+"~"+modelInsert.getValueAt(i, 1));
		txtUnit.setText(modelInsert.getValueAt(i, 4).toString());
		txtDealerPrice.setText(modelInsert.getValueAt(i, 5).toString());
		txtStockQty.setText(modelInsert.getValueAt(i, 6).toString());
		txtReturnQuantity.setText(modelInsert.getValueAt(i, 7).toString());
		txtAmount.setText(modelInsert.getValueAt(i, 8).toString());
		txtRemarks.setText(modelInsert.getValueAt(i, 9).toString());
		calcAmount();
		modelInsert.removeRow(i);
		calcTotalAmount();
		isSearchEditable(false);
		btnRefresh.setEnabled(false);
		isEdit = false;
		btnConfirm.setEnabled(false);
	}
	private  void searchDataFindTableWise(String returnNo)
	{
		String sql = "select a.returnNo,a.curDate,a.returnDate,a.totalAmount,a.username ,b.returnNo,b.productid,"
				+ "b.productName,b.supplierid,b.supplierName,b.unit,b.dealerPrice,b.stockQty,b.returnQty,"
				+ "b.amount,b.remark from tbPurchaseReturninfo a inner join tbPurchaseReturndetails b "
				+ "on a.returnNo = b.returnNo where a.returnNo = '"+returnNo+"'";
		for(int a=modelInsert.getRowCount()-1; a>=0; a--)
		{
			modelInsert.removeRow(a);
		}
		try
		{
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			int x=0;
			while(rs.next())
			{
				if(x==0)
				{
					txtReturnNo.setText(rs.getString("returnNo"));
					txtUserName.setText(rs.getString("userName"));
					jdcDate.setDate(rs.getDate("curDate"));
					jdcReturnDate.setDate(rs.getDate("returnDate"));
				}
				modelInsert.addRow(new Object[]
						{
							rs.getString("productid"),rs.getString("productName"),rs.getString("supplierid"),
							rs.getString("supplierName"),rs.getString("unit"),rs.getString("dealerPrice"),
							rs.getString("stockQty"),rs.getString("returnQty"),rs.getString("amount"),
							rs.getString("remark")
						});
				x++;
			}
			calcTotalAmount();
			Dbconnection.con.close();
			refreshWork();
			btnRefresh.setEnabled(true);
			btnConfirm.setEnabled(false);
			isEdit = false;
			btnClear.setEnabled(true);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "searchDataFindTableWise PurchaseReturn","error",JOptionPane.ERROR_MESSAGE);
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
			String sql = "Select returnNo,totalAmount,returnDate from tbPurchaseReturninfo where returnDate "
					+ "Between '"+dateFormat.format(jdcFromDate.getDate())+"' and '"+dateFormat.format(jdcToDate.getDate())+"'";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{
				modelFind.addRow(new Object[]
						{
							rs.getString("returnNo"),rs.getString("totalAmount"),rs.getString("returnDate")
						});
				while(rs.next())
				{
					modelFind.addRow(new Object[]
							{
								rs.getString("returnNo"),rs.getString("totalAmount"),rs.getString("returnDate")
							});
				}
				Dbconnection.con.close();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Sorry!\nWe are not find any return invoice at this moment.","warning",JOptionPane.WARNING_MESSAGE);
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from dateWiseFind PurchaseReturn","error",JOptionPane.ERROR_MESSAGE);
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
					StringTokenizer token = new StringTokenizer(cmbProduct.txtSuggest.getText().trim(), "~");
					productWiseLoad(token.nextToken());
				}
				else
				{
					refreshProductWise();
				}
			}
		});
		txtReturnQuantity.addKeyListener(new KeyListener() 
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
				calcAmount();
			}
			public void keyPressed(KeyEvent arg0) {}
		});
		tableInsert.addMouseListener(new MouseListener() 
		{
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent arg0) 
			{
				btnIni(false);
				if(!isEdit)
				{
					reEditDataSubmitTableWise();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Submit Previous info!","warining",JOptionPane.WARNING_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "Please Confirm Previous Return Invoice!","warning",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		tableFind.addKeyListener(new KeyListener()
		{
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) 
			{
				if(isSearch)
				{
					isFind = true;
					searchDataFindTableWise(modelFind.getValueAt(tableFind.getSelectedRow(), 0).toString());
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Confirm Previous Return Invoice!","warning",JOptionPane.WARNING_MESSAGE);
				}
			}
			public void keyPressed(KeyEvent arg0) {}
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
									JOptionPane.showMessageDialog(null, "All information updated successfully!",
											"information",JOptionPane.INFORMATION_MESSAGE);
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
								JOptionPane.showMessageDialog(null, "All information save successfully!",
										"information",JOptionPane.INFORMATION_MESSAGE);
								clearWork();
							}
						}
					}
				}
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
		btnRefresh.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				refreshWork();
			}
		});
		btnClear.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
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
	public void autoReturnNo()
	{
		try
		{
			String sql = "select ifnull(max(cast(substring(returnNo,locate('-',returnNo)+1,"
					+ "LENGTH(returnNo)-locate('-',returnNo))as UNSIGNED)),0)+1 id from tbPurchaseReturninfo";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				txtReturnNo.setText("ReturnNo-"+rs.getString("id"));
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from autoReturnNo PurchaseReturn","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void productLoad()
	{
		try
		{
			cmbProduct.v.clear();
			cmbProduct.v.add("");
			String sql = "select productid,productName from tbProductinfo order by productName";
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
			JOptionPane.showMessageDialog(null, e+"from productLoad PurchaseReturn","error",JOptionPane.WARNING_MESSAGE);
		}
	}
	private void productWiseLoad(String product)
	{
		try
		{
			String sql = "Select catid,catName,subCatid,subCatName,supplierid,supplierName,unit,dealerPrice,"
					+ "(select productStock('"+product+"'))stockQty from tbProductinfo where productid = '"+product+"'";
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
				cmbSupplier.removeAllItems();
				cmbSupplier.addItem("");
				cmbSupplier.addItem(rs.getString("supplierid")+"~"+rs.getString("supplierName"));
				cmbSupplier.setSelectedItem(rs.getString("supplierid")+"~"+rs.getString("supplierName"));
				txtUnit.setText(rs.getString("unit"));
				txtDealerPrice.setText(rs.getString("dealerPrice"));
				txtStockQty.setText(rs.getString("stockQty"));
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from ProductWiseLoad PurchaseReturn","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void cmp()
	{
		add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(PurchaseReturnNorth,BorderLayout.NORTH);
		PurchaseReturnNorth();
		mainPanel.add(PurchaseReturnCenter,BorderLayout.CENTER);
		PurchaseReturnCenter();
		mainPanel.add(PurchaseReturnSouth,BorderLayout.SOUTH);
		PurchaseReturnSouth();
	}
	@SuppressWarnings("static-access")
	public void PurchaseReturnNorth()
	{
		PurchaseReturnNorth.setBackground(bgColor);
		PurchaseReturnNorth.setPreferredSize(new Dimension(0,100));
		TitledBorder RSupplierTitle=new TitledBorder("Purchase Return");
		RSupplierTitle.setTitleFont(new Font("Agency FB",Font.BOLD,22));
		RSupplierTitle.setTitleJustification(TitledBorder.CENTER);
		RSupplierTitle.setTitlePosition(RSupplierTitle.TOP);
		PurchaseReturnNorth.setBorder(RSupplierTitle);
		PurchaseReturnNorth.setLayout(new BorderLayout());
		PurchaseReturnNorth.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=0;
		c.gridy=0;
		PurchaseReturnNorth.add(lblReturnNo,c);
		lblReturnNo.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=1;
		c.gridy=0;
		PurchaseReturnNorth.add(txtReturnNo,c);//
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=2;
		c.gridy=0;
		PurchaseReturnNorth.add(lblUserName,c);
		lblUserName.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.insets=new Insets(5,5, 5, 100);
		c.gridx=3;
		c.gridy=0;
		PurchaseReturnNorth.add(txtUserName,c);//
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=4;
		c.gridy=0;
		PurchaseReturnNorth.add(lblDate,c);
		lblDate.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=5;
		c.gridy=0;
		jdcDate.setDateFormatString("dd-MM-yyyy");
		PurchaseReturnNorth.add(jdcDate,c);//
		jdcDate.setPreferredSize(new Dimension(122,20));
		jdcDate.setDate(new Date());
		jdcDate.setBackground(bgColor);
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=6;
		c.gridy=0;
		PurchaseReturnNorth.add(lblReturnDate,c);
		lblReturnDate.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.insets=new Insets(5, 10, 5, 5);
		c.gridx=7;
		c.gridy=0;
		jdcReturnDate.setDateFormatString("dd-MM-yyyy");
		PurchaseReturnNorth.add(jdcReturnDate,c);//
		jdcReturnDate.setPreferredSize(new Dimension(122,20));
		jdcReturnDate.setDate(new Date());
		jdcReturnDate.setBackground(bgColor);
	}
	public void PurchaseReturnCenter()
	{
		BorderLayout border = new BorderLayout();
		border.setHgap(0);
		PurchaseReturnCenter.setLayout(border);
		PurchaseReturnCenter.add(PurchaseReturnCenterEast,BorderLayout.EAST);
		PurchaseReturnCenterEast();
		PurchaseReturnCenter.add(PurchaseReturnCenterCenter,BorderLayout.CENTER);
		PurchaseReturnCenterCenter();
	}
	@SuppressWarnings("static-access")
	public void  PurchaseReturnCenterEast()
	{
		PurchaseReturnCenterEast.setBackground(bgColor);
		PurchaseReturnCenterEast.setPreferredSize(new Dimension(500,0));
		TitledBorder titlePurchaseReturnCenterEast=new TitledBorder("Existing Purchase Return Invoice");
		titlePurchaseReturnCenterEast.setTitleFont(new Font("Agency FB", Font.PLAIN+Font.BOLD, 20));
		titlePurchaseReturnCenterEast.setTitleJustification(TitledBorder.CENTER);
		titlePurchaseReturnCenterEast.setTitlePosition(titlePurchaseReturnCenterEast.TOP);
		titlePurchaseReturnCenterEast.setTitleColor(Color.white);
		PurchaseReturnCenterEast.setBorder(titlePurchaseReturnCenterEast);
		PurchaseReturnCenterEast.setLayout(new BorderLayout());
		PurchaseReturnCenterEast.add(PurchaseReturnCenterEastNorth,BorderLayout.NORTH);
		PurchaseReturnCenterEastNorth();
		PurchaseReturnCenterEast.add(PurchaseReturnCenterEastCenter,BorderLayout.CENTER);
		PurchaseReturnCenterEastCenter();
	}
	public void PurchaseReturnCenterEastNorth()
	{
		PurchaseReturnCenterEastNorth.setBackground(Color.white);
		PurchaseReturnCenterEastNorth.setPreferredSize(new Dimension(0,70));
		PurchaseReturnCenterEastNorth.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5, 8, 5, 8);
		c.gridx=0;
		c.gridy=0;
		PurchaseReturnCenterEastNorth.add(lblFromDate,c);
		lblFromDate.setFont(font);
		c.gridx=1;
		c.gridy=0;
		jdcFromDate.setDateFormatString("dd-MM-yy");
		PurchaseReturnCenterEastNorth.add(jdcFromDate,c);//
		jdcFromDate.setDate(new Date());
		jdcFromDate.setBackground(Color.white);
		c.gridx=2;
		c.gridy=0;
		PurchaseReturnCenterEastNorth.add(lblToDate,c);
		lblToDate.setFont(font);
		c.gridx=3;
		c.gridy=0;
		jdcToDate.setDateFormatString("dd-MM-yy");
		PurchaseReturnCenterEastNorth.add(jdcToDate,c);//
		jdcToDate.setDate(new Date());
		jdcToDate.setBackground(Color.white);
		c.gridx=4;
		c.gridy=0;
		PurchaseReturnCenterEastNorth.add(btnfind,c);//
		btnfind.setBackground(btnColor);
		btnfind.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 15));
	}
	public void PurchaseReturnCenterEastCenter()
	{
		scrollExistingInvoice.setPreferredSize(new Dimension(465,202));
		PurchaseReturnCenterEastCenter.add(scrollExistingInvoice);
		PurchaseReturnCenterEastCenter.setBackground(Color.white);
		scrollExistingInvoice.getViewport().setBackground(Color.white);
		tableFind.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollExistingInvoice.setOpaque(false);
		tableFind.setOpaque(false);
		tableFind.setShowGrid(true);
		tableFind.setRowHeight(tableFind.getRowHeight()+2);
	}
	public void  PurchaseReturnCenterCenter()
	{
		PurchaseReturnCenterCenter.setLayout(new BorderLayout());
		PurchaseReturnCenterCenter.add(PurchaseReturnCenterCenterCenter,BorderLayout.CENTER);
		PurchaseReturnCenterCenterCenter();
		PurchaseReturnCenterCenter.add(PurchaseReturnCenterCenterSouth,BorderLayout.SOUTH);
		PurchaseReturnCenterCenterSouth();
	}
	public void PurchaseReturnCenterCenterCenter()
	{
		PurchaseReturnCenterCenterCenter.setLayout(new BorderLayout());
		PurchaseReturnCenterCenterCenter.add(PurchaseReturnCenterCenterCenterCenter,BorderLayout.CENTER);
		PurchaseReturnCenterCenterCenterCenter();
		PurchaseReturnCenterCenterCenter.add(PurchaseReturnCenterCenterCenterEast,BorderLayout.EAST);
		PurchaseReturnCenterCenterCenterEast();		
	}
	private void PurchaseReturnCenterCenterCenterCenter() 
	{
		PurchaseReturnCenterCenterCenterCenter.setBackground(Color.white);
		PurchaseReturnCenterCenterCenterCenter.setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5, 25, 5, 5);
		c.gridx=0;
		c.gridy=0;
		PurchaseReturnCenterCenterCenterCenter.add(lblProduct,c);
		lblProduct.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=0;
		PurchaseReturnCenterCenterCenterCenter.add(cmbProduct.cmbSuggest,c);//
		cmbProduct.cmbSuggest.setPreferredSize(new Dimension(1,28));
		cmbProduct.cmbSuggest.setBackground(Color.white);
		c.gridx=0;
		c.gridy=1;
		PurchaseReturnCenterCenterCenterCenter.add(lblCategory,c);
		lblCategory.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=1;
		PurchaseReturnCenterCenterCenterCenter.add(cmbCategory,c);//
		cmbCategory.setPreferredSize(new Dimension(1,28));
		cmbCategory.setBackground(Color.white);
		c.gridx=0;
		c.gridy=2;
		PurchaseReturnCenterCenterCenterCenter.add(lblSubCategory,c);
		lblSubCategory.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=2;
		PurchaseReturnCenterCenterCenterCenter.add(cmbSubCategory,c);//
		cmbSubCategory.setPreferredSize(new Dimension(1,28));
		cmbSubCategory.setBackground(Color.white);
		c.gridx=0;
		c.gridy=3;
		PurchaseReturnCenterCenterCenterCenter.add(lblSupplier,c);
		lblSupplier.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=3;
		PurchaseReturnCenterCenterCenterCenter.add(cmbSupplier,c);//
		cmbSupplier.setPreferredSize(new Dimension(1,28));
		cmbSupplier.setBackground(Color.white);
		c.gridx=0;
		c.gridy=4;
		PurchaseReturnCenterCenterCenterCenter.add(lblUnit,c);
		lblUnit.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=4;
		txtUnit.setPreferredSize(new Dimension(1,28));
		PurchaseReturnCenterCenterCenterCenter.add(txtUnit,c);//
		c.gridx=0;
		c.gridy=5;
		PurchaseReturnCenterCenterCenterCenter.add(lblDealerPrice,c);
		lblDealerPrice.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=5;
		txtDealerPrice.setPreferredSize(new Dimension(1,28));
		PurchaseReturnCenterCenterCenterCenter.add(txtDealerPrice,c);//
	}
	private void PurchaseReturnCenterCenterCenterEast() 
	{
		PurchaseReturnCenterCenterCenterEast.setBackground(Color.white);
		PurchaseReturnCenterCenterCenterEast.setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=0;
		c.gridy=0;
		PurchaseReturnCenterCenterCenterEast.add(lblStockQty,c);
		lblStockQty.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.insets=new Insets(5, 2, 5, 25);
		c.gridx=1;
		c.gridy=0;
		txtStockQty.setPreferredSize(new Dimension(1,28));
		PurchaseReturnCenterCenterCenterEast.add(txtStockQty,c);//
		c.gridx=0;
		c.gridy=1;
		PurchaseReturnCenterCenterCenterEast.add(lblReturnQuantity,c);
		lblReturnQuantity.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=1;
		txtReturnQuantity.setPreferredSize(new Dimension(1,28));
		PurchaseReturnCenterCenterCenterEast.add(txtReturnQuantity,c);//
		c.gridx=0;
		c.gridy=2;
		PurchaseReturnCenterCenterCenterEast.add(lblAmount,c);
		lblAmount.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=2;
		txtAmount.setPreferredSize(new Dimension(1,28));
		PurchaseReturnCenterCenterCenterEast.add(txtAmount,c);//
		c.gridx=0;
		c.gridy=3;
		PurchaseReturnCenterCenterCenterEast.add(lblRemarks,c);
		lblRemarks.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=3;
		PurchaseReturnCenterCenterCenterEast.add(scrollRemarks,c);//
	}
	public void PurchaseReturnCenterCenterSouth()
	{
		PurchaseReturnCenterCenterSouth.setBackground(Color.white);
		PurchaseReturnCenterCenterSouth.setPreferredSize(new Dimension(0,60));
		//PurchaseReciptCenterCenterSouth.setBorder(BorderFactory.createRaisedBevelBorder());
		PurchaseReturnCenterCenterSouth.setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5, 10, 15, 5);
		c.gridx=0;
		c.gridy=0;
		PurchaseReturnCenterCenterSouth.add(btnSubmit,c);
		btnSubmit.setBackground(btnColor);
		btnSubmit.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 13));
		c.gridx=1;
		c.gridy=0;
		PurchaseReturnCenterCenterSouth.add(btnEdit,c);
		btnEdit.setBackground(btnColor);
		btnEdit.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 13));
		c.gridx=2;
		c.gridy=0;
		PurchaseReturnCenterCenterSouth.add(btnRefresh,c);
		btnRefresh.setBackground(btnColor);
		btnRefresh.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 13));
	}

	public void PurchaseReturnSouth()
	{
		PurchaseReturnSouth.setPreferredSize(new Dimension(0,320));
		PurchaseReturnSouth.setLayout(new BorderLayout());
		PurchaseReturnSouth.add(PurchaseReturnSouthCenter,BorderLayout.CENTER);
		PurchaseReturnSouthCenter();
		PurchaseReturnSouth.add(PurchaseReturnSouthSouth,BorderLayout.SOUTH);
		PurchaseReturnSouthSouth();
	}
	public void PurchaseReturnSouthCenter()
	{
		tableInsert.getTableHeader().setReorderingAllowed(false);
		scrollMain.setPreferredSize(new Dimension(1125,230));
		PurchaseReturnSouthCenter.add(scrollMain);
		PurchaseReturnSouthCenter.setBackground(Color.white);
		scrollMain.getViewport().setBackground(Color.white);
		tableInsert.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableInsert.setOpaque(false);
		scrollMain.setOpaque(false);
		tableInsert.setShowGrid(true);
		tableInsert.setRowHeight(tableInsert.getRowHeight()+2);
	}
	public void PurchaseReturnSouthSouth()
	{
		PurchaseReturnSouthSouth.setBackground(Color.white);
		PurchaseReturnSouthSouth.setPreferredSize(new Dimension(0,80));
		PurchaseReturnSouthSouth.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5, 10, 15, 10);
		c.gridx=0;
		c.gridy=0;
		PurchaseReturnSouthSouth.add(btnConfirm,c);
		btnConfirm.setBackground(btnColor);
		btnConfirm.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 15));
		c.gridx=1;
		c.gridy=0;
		PurchaseReturnSouthSouth.add(btnClear,c);//
		btnClear.setBackground(btnColor);
		btnClear.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 15));
		c.insets=new Insets(5, 300, 15, 5);
		c.gridx=2;
		c.gridy=0;
		PurchaseReturnSouthSouth.add(lblTotalAmount,c);
		lblTotalAmount.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 20));
		c.insets=new Insets(5, 5, 15, 5);
		c.gridx=3;
		c.gridy=0;
		PurchaseReturnSouthSouth.add(txtTotalAmount,c);//
		txtTotalAmount.setFont(font2);
		txtTotalAmount.setForeground(Color.BLUE);
	}

	public void init()
	{
		setPreferredSize(new Dimension(1140,740));
		setLayout(new BorderLayout());
	}
}
