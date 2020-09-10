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

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

public class SalesEntry extends JPanel
{
	private static final long serialVersionUID = 1L;
	SessionBean sessionBean;
	JPanel SalesEntryNorth=new JPanel();
	JPanel SalesEntryCenter=new JPanel();
	JPanel SalesEntrySouth=new JPanel();
	JPanel SalesEntrySouthCenter=new JPanel();
	JPanel SalesEntrySouthSouth=new JPanel();
	JPanel SalesEntrySouthSouthCenter = new JPanel();
	JPanel SalesEntrySouthSouthEast = new JPanel();
	JPanel SalesEntryCenterEast=new JPanel();
	JPanel SalesEntryCenterCenter=new JPanel();
	JPanel SalesEntryCenterCenterCenter=new JPanel();
	JPanel SalesEntryCenterCenterCenterCenter = new JPanel();
	JPanel SalesEntryCenterCenterCenterEast = new JPanel();
	JPanel SalesEntryCenterCenterSouth=new JPanel();
	JPanel SalesEntryCenterEastNorth=new JPanel();
	JPanel SalesEntryCenterEastCenter=new JPanel();

	JLabel lblClient = new JLabel("Client");
	JLabel lblUserName=new JLabel("User Name");
	JLabel lblSalesNo=new JLabel("Sales No.");
	JLabel lblDate=new JLabel("Current Date");
	JLabel lblCategory=new JLabel("Category");
	JLabel lblSubCategory=new JLabel("Sub Category");
	JLabel lblProduct=new JLabel("Product");
	JLabel lblUnit=new JLabel("Unit");
	JLabel lblStockQty = new JLabel("Stock Qty");
	JLabel lblSalesQty = new JLabel("Sales Qty");
	JLabel lblDealerPrice = new JLabel("Dealer Price");
	JLabel lblTradePrice = new JLabel("Trade Price");
	JLabel lblAmount = new JLabel("Amount");
	JLabel lblRemarks=new JLabel("Remarks");
	JLabel lblPaymentProtocol=new JLabel("Payment Protocol");
	JLabel lblReference = new JLabel("Reference");
	JLabel lblTotalAmount=new JLabel("Total Amount");
	JLabel lblDiscount = new JLabel("Discount");
	JLabel lblPaidAmount = new JLabel("Paid Amount");
	JLabel lblDue=new JLabel("Due Amount");
	JLabel lblFromDate=new JLabel("From Date");
	JLabel lblToDate=new JLabel("To Date");
	JComboBox<String> cmbClient=new JComboBox<String>();
	JTextField txtSalesNo=new JTextField(15);
	JTextField txtUserName=new JTextField(15);
	JDateChooser jdcDate=new JDateChooser();
	JComboBox<String> cmbCategory=new JComboBox<>();
	JComboBox<String> cmbSubCategory=new JComboBox<>();
	SuggestText cmbProduct=new SuggestText();
	JTextField txtUnit = new JTextField(15);
	JTextField txtStockQty = new JTextField(15);
	JTextField txtSalesQty = new JTextField(15);
	JTextField txtDealerPrice = new JTextField(15);
	JTextField txtTradePrice = new JTextField(15);
	JTextField txtAmount = new JTextField(15);
	JTextArea txtRemarks=new JTextArea(3,1);
	JScrollPane scrollRemarks=new JScrollPane(txtRemarks,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	String ara[] = {"","Cash","Check","Devit Card","Credit Card","bKash","Sure Cash","Rocket","UCash"};
	JComboBox<String> cmbPaymentProtocol=  new JComboBox<>(ara);
	JTextField txtReference = new JTextField(15);
	JTextField txtTotalAmount = new JTextField(15);
	JTextField txtDiscount = new JTextField(15);
	JTextField txtPaidAmount = new JTextField(15);
	JTextField txtDue = new JTextField(15);
	JDateChooser jdcFromDate=new JDateChooser();
	JDateChooser jdcToDate=new JDateChooser();
	JButton btnSubmit=new JButton("Submit ",new ImageIcon("images/btnSubmit.png"));
	JButton btnEdit=new JButton("Edit      ",new ImageIcon("images/btnEdit.png"));
	JButton btnRefresh=new JButton("Refresh",new ImageIcon("images/btnRefresh.png"));
	JButton btnConfirm=new JButton("Confirm",new ImageIcon("images/confirm.png"));
	JButton btnClear =new JButton("Clear  ",new ImageIcon("images/btnRefresh.png"));
	JButton btnfind=new JButton(" Find ");
	JCheckBox chkRegisterClient=new JCheckBox("Registered Client");
	JCheckBox chkUnregisterClient=new JCheckBox("Unregistered Client");
	String[] column={"Product ID","Product Name","Unit","Dealer Price","Trade Price","Stock Qty","Sales Qty","Amount","Remarks"};
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

	String[] column2={"Sales No.","Total Amount","Due Amount","Current Date"};
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
	JScrollPane scrollExistingInvoice=new JScrollPane(tableFind,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	GridBagConstraints c=new GridBagConstraints();

	Font font=new Font("Baskerville O",Font.BOLD,12);
	BufferedImage image;
	Color bgColor = new Color(2, 191, 185);
	Color btnColor = new Color(0, 134, 139);
	ButtonGroup btnGroup = new ButtonGroup();
	DecimalFormat df = new DecimalFormat("#0.00");
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	boolean isFind = false;
	boolean isEdit = false;
	boolean isSearch = true;

	public SalesEntry(SessionBean bean)
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
		chkUnregisterClient.setSelected(!e);
		cmbClient.setEnabled(e);
		txtDue.setEnabled(e);
		txtSalesNo.setEditable(e);
		txtUserName.setEditable(e);
		txtTotalAmount.setEditable(e);
		cmbCategory.setEnabled(e);
		cmbSubCategory.setEnabled(e);
		txtUnit.setEditable(e);
		txtDealerPrice.setEditable(e);
		txtTradePrice.setEditable(e);
		txtStockQty.setEditable(e);
		txtAmount.setEditable(e);
	}
	private void isSearchEditable(boolean s)
	{
		cmbProduct.txtSuggest.setEnabled(s);
		txtSalesQty.setEditable(s);
		txtRemarks.setEditable(s);
	}
	private void refreshProductWise()
	{
		cmbCategory.setSelectedItem("");
		cmbSubCategory.setSelectedItem("");
		txtUnit.setText("");
		txtDealerPrice.setText("");
		txtTradePrice.setText("");
		txtStockQty.setText("");
	}
	private void refreshWork()
	{
		cmbProduct.txtSuggest.setText("");
		cmbProduct.combowork();
		refreshProductWise();
		txtSalesQty.setText("");
		txtAmount.setText("");
		txtRemarks.setText("");
	}
	private void clearWork()
	{
		refreshWork();
		autoSalseNo();
		tableFindDataLoad();
		removeSubmitTableData();
		chkUnregisterClient.setSelected(true);
		cmbClient.setSelectedIndex(0);
		cmbClient.setEnabled(false);
		jdcDate.setDate(new Date());
		jdcFromDate.setDate(new Date());
		jdcToDate.setDate(new Date());
		cmbPaymentProtocol.setSelectedIndex(0);
		txtReference.setText("");
		txtTotalAmount.setText("");
		txtDiscount.setText("");
		txtPaidAmount.setText("");
		txtDue.setText("");
		txtDue.setEnabled(false);
		btnSubmit.setEnabled(true);
		btnRefresh.setEnabled(true);
		isSearchEditable(true);
		btnIni(true);
		isFind = false;
	}
	private void calcAmount()
	{
		double salesQty, tradePrice, amount;
		salesQty = Double.parseDouble(txtSalesQty.getText().trim().isEmpty()?"0":txtSalesQty.getText().trim());
		tradePrice = Double.parseDouble(txtTradePrice.getText().trim().isEmpty()?"0":txtTradePrice.getText().trim());
		amount = salesQty*tradePrice;
		txtAmount.setText(df.format(amount));
	}
	private void calcTotalAmount()
	{
		double total = 0;
		for(int x=0; x<tableInsert.getRowCount(); x++)
		{
			total = total+Double.parseDouble(tableInsert.getValueAt(x, 7).toString());
		}
		txtTotalAmount.setText(df.format(total));
		txtPaidAmount.setText(df.format(total));
	}
	private void calcPaidAmount()
	{
		double totalAmount,discount,paidAmount;
		totalAmount = Double.parseDouble(txtTotalAmount.getText().trim().isEmpty()?"0":txtTotalAmount.getText().trim());
		discount = Double.parseDouble(txtDiscount.getText().trim().isEmpty()?"0":txtDiscount.getText().trim());
		paidAmount = totalAmount-discount;
		txtPaidAmount.setText(df.format(paidAmount));
	}
	private void calcDiscount()
	{
		double totalAmount, paidAmount, discount;
		totalAmount = Double.parseDouble(txtTotalAmount.getText().trim().isEmpty()?"0":txtTotalAmount.getText().trim());
		paidAmount = Double.parseDouble(txtPaidAmount.getText().trim().isEmpty()?"0":txtPaidAmount.getText().trim());
		discount = totalAmount-paidAmount;
		txtDiscount.setText(df.format(discount));
	}
	private void calcDue()
	{
		double totalAmount, discount, paidAmount, due;
		totalAmount = Double.parseDouble(txtTotalAmount.getText().trim().isEmpty()?"0":txtTotalAmount.getText().trim());
		discount = Double.parseDouble(txtDiscount.getText().trim().isEmpty()?"0":txtDiscount.getText().trim());
		paidAmount = Double.parseDouble(txtPaidAmount.getText().trim().isEmpty()?"0":txtPaidAmount.getText().trim());
		due = totalAmount-(discount+paidAmount);
		txtDue.setText(df.format(due));
	}
	private boolean checkValidation()
	{
		if(!cmbProduct.txtSuggest.getText().trim().isEmpty())
		{
			if(!txtSalesQty.getText().trim().isEmpty())
			{
				return true;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please Enter Sales Qty!","warning",JOptionPane.WARNING_MESSAGE);
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
		for(int x=0; x<modelInsert.getRowCount(); x++)
		{
			String product = modelInsert.getValueAt(x, 0).toString();
			if(productid.equalsIgnoreCase(product))
			{
				JOptionPane.showMessageDialog(null, "Double Entry!\nPlease don't entry already exist data.","warning",JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
		return true;
	}
	private void tableSubmitDataLoad()
	{
		try
		{
			StringTokenizer token = new StringTokenizer(cmbProduct.txtSuggest.getText().trim(), "~");
			String productid = token.nextToken();
			String productName = token.nextToken();
			modelInsert.addRow(new Object[]
					{
							productid,productName,txtUnit.getText().trim(),txtDealerPrice.getText().trim(),
							txtTradePrice.getText().trim(),txtStockQty.getText().trim(),txtSalesQty.getText().trim(),
							txtAmount.getText().trim(),txtRemarks.getText().trim()
					});
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from tableSubmit Data SalesEntry","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	private boolean checkValidationConfirm()
	{
		boolean client = true;
		if(chkRegisterClient.isSelected())
		{
			if(cmbClient.getSelectedIndex()!=0 && cmbClient.getSelectedItem()!=null)
			{
				client = true;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please select client!","warning",JOptionPane.WARNING_MESSAGE);
				client = false;
			}
		}
		if(client)
		{
			if(cmbPaymentProtocol.getSelectedIndex()!=0 && cmbPaymentProtocol.getSelectedItem()!=null)
			{
				if(cmbPaymentProtocol.getSelectedIndex()==1)
				{
					if(!txtDiscount.getText().trim().isEmpty())
					{
						if(!txtPaidAmount.getText().trim().isEmpty())
						{
							if(checkSubmitTableData())
							{
								return true;
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Please Enter Paid Amount!","warning",JOptionPane.WARNING_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please Enter Discount!","warning",JOptionPane.WARNING_MESSAGE);
					}
				}
				else
				{
					if(!txtReference.getText().trim().isEmpty())
					{
						if(!txtDiscount.getText().trim().isEmpty())
						{
							if(!txtPaidAmount.getText().trim().isEmpty())
							{
								if(checkSubmitTableData())
								{
									return true;
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Please Enter Paid Amount!","warning",JOptionPane.WARNING_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Please Enter Discount!","warning",JOptionPane.WARNING_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please Enter Reference!","warning",JOptionPane.WARNING_MESSAGE);
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please Select Any Payment Way!","warning",JOptionPane.WARNING_MESSAGE);
			}
		}
		return false;
	}
	private boolean checkSubmitTableData()
	{
		if(modelInsert.getRowCount()>=1)
		{
			return true;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please check submited data!","warning",JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}
	private boolean checkConfirmation(String confirm)
	{
		int a=JOptionPane.showConfirmDialog(null, confirm,"confirm",JOptionPane.YES_NO_OPTION);
		if(a==JOptionPane.YES_OPTION)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	private boolean deleteData()
	{
		try
		{
			String sqlinfo = "delete from tbSalesEntryinfo where salesNo = '"+txtSalesNo.getText().trim()+"'";
			String sqlDetails = "delete from tbSalesEntryDetails where salesNo = '"+txtSalesNo.getText().trim()+"'";
			Dbconnection.connection();
			Dbconnection.sta.executeUpdate(sqlinfo);
			Dbconnection.sta.executeUpdate(sqlDetails);
			Dbconnection.con.close();
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from deleteData SalesEntry","error",JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	private boolean insertData()
	{
		String clientid = "";
		String clientName = "";
		String due = "0";
		String clientType = "Unregistered Client";
		try
		{
			if(chkRegisterClient.isSelected())
			{
				StringTokenizer token = new StringTokenizer(cmbClient.getSelectedItem().toString(), "~");
				clientid = token.nextToken();
				clientName = token.nextToken();
				due = txtDue.getText().trim();
				clientType = "Registered Client";
			}
			Dbconnection.connection();
			Dbconnection.con.setAutoCommit(false);
			String sqlinfo = "insert into tbSalesEntryinfo(salesNo,clientType,clientid,clientName,curdate," +
					"payProtocol,reference,totalAmount,discount,paidAmount,Due,userName,userip,entrytime) values"
					+ "('"+txtSalesNo.getText().trim()+"','"+clientType+"','"+clientid+"','"+clientName+"',"
					+ "'"+dateFormat.format(jdcDate.getDate())+"','"+cmbPaymentProtocol.getSelectedItem().toString()+"',"
					+ "'"+txtReference.getText().trim()+"','"+txtTotalAmount.getText().trim()+"','"+txtDiscount.getText().trim()+"',"
					+ "'"+txtPaidAmount.getText().trim()+"','"+due+"','"+txtUserName.getText().trim()+"','',now())";
			Dbconnection.sta.executeUpdate(sqlinfo);
			for(int i=0; i<modelInsert.getRowCount(); i++)
			{
				String sqlDetails = "insert into tbSalesEntryDetails(salesNo,productid,productName,unit,"
						+ "dealerPrice,tradePrice,stockQty,salesQty,amount,remark) values"
						+ "('"+txtSalesNo.getText().trim()+"',"
						+ "'"+modelInsert.getValueAt(i, 0)+"','"+modelInsert.getValueAt(i, 1)+"',"
						+ "'"+modelInsert.getValueAt(i, 2)+"','"+modelInsert.getValueAt(i, 3)+"',"
						+ "'"+modelInsert.getValueAt(i, 4)+"','"+modelInsert.getValueAt(i, 5)+"',"
						+ "'"+modelInsert.getValueAt(i, 6)+"','"+modelInsert.getValueAt(i, 7)+"',"
						+ "'"+modelInsert.getValueAt(i, 8)+"')";
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
				JOptionPane.showMessageDialog(null, ex+"from rollback() SalesEntry");
			}
			JOptionPane.showMessageDialog(null, e+"from insertData SalesEntry","error",JOptionPane.ERROR_MESSAGE);
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
			String sql = "select salesNo,totalAmount,due,curDate from tbSalesEntryInfo "
					+ "order by cast(substring(salesNo,locate('-',salesNo)+1,"
					+ "length(salesNo)-locate('-',salesNo))as UNSIGNED) DESC";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				modelFind.addRow(new Object[]
						{
								rs.getString("salesNo"),rs.getString("totalAmount"),
								rs.getString("due"),rs.getString("curDate")
						});
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from tableFindDataLoad() SalesEntry","error",JOptionPane.ERROR_MESSAGE);
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
		txtTradePrice.setText(modelInsert.getValueAt(i, 4).toString());
		txtStockQty.setText(modelInsert.getValueAt(i, 5).toString());
		txtSalesQty.setText(modelInsert.getValueAt(i, 6).toString());
		txtRemarks.setText(modelInsert.getValueAt(i, 8).toString());
		calcAmount();
		modelInsert.removeRow(i);
		calcTotalAmount();
		isSearchEditable(false);
		isEdit = false;
		btnRefresh.setEnabled(false);
	}
	private void searchDataFindTableWise(String Sales)
	{
		for(int a=modelInsert.getRowCount()-1; a>=0; a--)
		{
			modelInsert.removeRow(a);
		}
		try
		{
			String sql = "select a.salesNo,a.clientType,a.clientId,a.clientName,a.curdate,a.payprotocol,"
					+ "a.reference,a.totalAmount,a.discount,a.paidAmount,a.due,a.userName,b.salesNo,"
					+ "b.productId,b.productName, b.unit,b.dealerPrice,b.tradePrice,b.stockQty,b.salesQty,"
					+ "b.amount,b.remark from tbSalesEntryinfo a inner join tbSalesEntrydetails b "
					+ "on a.salesNo=b.salesNo where a.salesNo = '"+Sales+"'";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			int i = 0;
			while(rs.next())
			{
				if(i==0)
				{
					txtSalesNo.setText(rs.getString("salesNo"));
					if(rs.getString("clientType").equals("Registered Client"))
					{
						chkRegisterClient.setSelected(true);
						cmbClient.setEnabled(true);
						txtDue.setEnabled(true);
						cmbClient.setSelectedItem(rs.getString("clientid")+"~"+rs.getString("clientName"));
						cmbClient.addItem(rs.getString("clientid")+"~"+rs.getString("clientName"));
						txtDue.setText(rs.getString("due"));
					}
					else
					{
						chkUnregisterClient.setSelected(true);
						cmbClient.setSelectedIndex(0);
						cmbClient.setEnabled(false);
						txtDue.setText("");
						txtDue.setEnabled(false);
					}
					txtUserName.setText(rs.getString("userName"));
					jdcDate.setDate(rs.getDate("curDate"));
					cmbPaymentProtocol.setSelectedItem(rs.getString("payProtocol"));
					txtReference.setText(rs.getString("reference"));
					txtTotalAmount.setText(rs.getString("totalAmount"));
					txtDiscount.setText(rs.getString("discount"));
					txtPaidAmount.setText(rs.getString("paidAmount"));
				}
				modelInsert.addRow(new Object[]
						{
								rs.getString("productid"),rs.getString("productName"),
								rs.getString("unit"),rs.getString("dealerPrice"),
								rs.getString("tradePrice"),rs.getString("stockQty"),
								rs.getString("salesQty"),rs.getString("amount"),rs.getString("remark")
						});
				i++;
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
			JOptionPane.showMessageDialog(null, e+"From searchDataFindTableWise SalesEntry","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void dateWiseFind()
	{
		try
		{
			for(int i = modelFind.getRowCount()-1; i>=0; i--)
			{
				modelFind.removeRow(i);
			}
			String sql = "Select salesNo,totalAmount,due,curDate from tbSalesEntryinfo where curDate "
					+ "Between '"+dateFormat.format(jdcFromDate.getDate())+"' and '"+dateFormat.format(jdcToDate.getDate())+"'";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{
				modelFind.addRow(new Object[]
						{
							rs.getString("salesNo"),rs.getString("totalAmount"),rs.getString("due"),rs.getString("curDate")
						});
				while(rs.next())
				{
					modelFind.addRow(new Object[]
							{
								rs.getString("salesNo"),rs.getString("totalAmount"),rs.getString("due"),rs.getString("curDate")
							});
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Sorry!\nWe are not find any sales invoice at this moment.");
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from dateWiseFind SalesEntry","error",JOptionPane.ERROR_MESSAGE);
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
		chkRegisterClient.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(chkRegisterClient.isSelected())
				{
					cmbClient.setEnabled(true);
					txtDue.setEnabled(true);
				}
			}
		});
		chkUnregisterClient.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(chkUnregisterClient.isSelected())
				{
					cmbClient.setSelectedIndex(0);
					cmbClient.setEnabled(false);
					txtDue.setText("");
					txtDue.setEnabled(false);
				}
			}
		});
		txtSalesQty.addKeyListener(new KeyListener() 
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
		txtDiscount.addKeyListener(new KeyListener() 
		{
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) 
			{
				calcPaidAmount();
				calcDue();
			}
			public void keyPressed(KeyEvent arg0) {}
		});
		txtPaidAmount.addKeyListener(new KeyListener() 
		{
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) 
			{
				if(chkUnregisterClient.isSelected())
				{
					calcDiscount();
				}
				else
				{
					calcDue();
				}
			}
			public void keyPressed(KeyEvent arg0) {}
		});
		txtDue.addKeyListener(new KeyListener() 
		{
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) 
			{
				calcPaidAmount();
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
					JOptionPane.showMessageDialog(null, "Please Submit Previous Info!","warning",JOptionPane.WARNING_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "Please confirm previous sales invoice!","warning",JOptionPane.WARNING_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "Please confirm previous sales invoice!","warning",JOptionPane.WARNING_MESSAGE);
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
											"inform",JOptionPane.INFORMATION_MESSAGE);
									clearWork();
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
										"inform",JOptionPane.INFORMATION_MESSAGE);
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
	public void autoSalseNo()
	{
		try
		{
			String sql = "Select ifnull(max(cast(substring(salesNo,Locate('-',salesNo)+1,"
					+ "Length(salesNo)-Locate('-',salesNo))as Unsigned)),0)+1 as id from tbSalesEntryinfo";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				txtSalesNo.setText("SalesNo-"+rs.getString("id"));
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from autoSalesNo Load SalesEntry","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void clientLoad()
	{
		cmbClient.removeAllItems();
		cmbClient.addItem("");
		try
		{
			String sql = "Select clientid,clientName from tbClientinfo order by clientName";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbClient.addItem(rs.getString("clientid")+"~"+rs.getString("clientName"));
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from clientLoad SalesEntry","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void productLoad()
	{
		cmbProduct.v.clear();
		cmbProduct.v.add("");
		try
		{
			String sql = "Select productid,productName from tbProductinfo order by productName";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				String id = rs.getString("productid");
				String name = rs.getString("productName");
				cmbProduct.v.add(id+"~"+name);
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from ProductLoad SalesEntry","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void productWiseLoad(String product)
	{
		try
		{
			String sql = "Select catid,catName,subCatid,subCatName,unit,dealerPrice,tradePrice,"
					+ "(select productStock('"+product+"'))StockQty from tbProductinfo where productid = '"+product+"'";
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
				txtUnit.setText(rs.getString("unit"));
				txtDealerPrice.setText(rs.getString("dealerPrice"));
				txtTradePrice.setText(rs.getString("tradePrice"));
				txtStockQty.setText(rs.getString("stockQty"));
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from productWiseLoad SalesEntry","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void cmp()
	{
		add(SalesEntryNorth,BorderLayout.NORTH);
		SalesEntryNorth();
		add(SalesEntryCenter,BorderLayout.CENTER);
		SalesEntryCenter();
		add(SalesEntrySouth,BorderLayout.SOUTH);
		SalesEntrySouth();
	}
	@SuppressWarnings("static-access")
	public void SalesEntryNorth()
	{
		SalesEntryNorth.setBackground(bgColor);
		SalesEntryNorth.setPreferredSize(new Dimension(0,120));
		TitledBorder SalesEntryTitle=new TitledBorder("Sales Entry");
		SalesEntryTitle.setTitleFont(new Font("Agency FB",Font.BOLD,22));
		SalesEntryTitle.setTitleJustification(TitledBorder.CENTER);
		SalesEntryTitle.setTitlePosition(SalesEntryTitle.TOP);
		SalesEntryNorth.setBorder(SalesEntryTitle);
		SalesEntryNorth.setLayout(new BorderLayout());
		SalesEntryNorth.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=0;
		c.gridy=0;
		SalesEntryNorth.add(chkRegisterClient,c);
		chkRegisterClient.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=0;
		c.gridy=1;
		SalesEntryNorth.add(chkUnregisterClient,c);//
		chkUnregisterClient.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		btnGroup.add(chkRegisterClient);
		btnGroup.add(chkUnregisterClient);
		c.insets=new Insets(5, 100, 5, 5);
		c.gridx=1;
		c.gridy=0;
		SalesEntryNorth.add(lblClient,c);
		lblClient.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=2;
		c.gridy=0;
		SalesEntryNorth.add(cmbClient,c);//
		cmbClient.setBackground(Color.white);
		c.insets=new Insets(5, 100, 5, 5);
		c.gridx=1;
		c.gridy=1;
		SalesEntryNorth.add(lblUserName,c);
		lblUserName.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=2;
		c.gridy=1;
		SalesEntryNorth.add(txtUserName,c);//
		txtUserName.setPreferredSize(new Dimension(1,25));
		c.insets=new Insets(5, 100, 5, 5);
		c.gridx=3;
		c.gridy=0;
		SalesEntryNorth.add(lblSalesNo,c);
		lblSalesNo.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=4;
		c.gridy=0;
		SalesEntryNorth.add(txtSalesNo,c);//
		txtSalesNo.setPreferredSize(new Dimension(1,25));
		c.insets=new Insets(5, 100, 5, 5);
		c.gridx=3;
		c.gridy=1;
		SalesEntryNorth.add(lblDate,c);
		lblDate.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=4;
		c.gridy=1;
		SalesEntryNorth.add(jdcDate,c);//
		jdcDate.setDateFormatString("dd-MM-yyyy");
		jdcDate.setDate(new Date());
		jdcDate.setPreferredSize(new Dimension(1,25));
		jdcDate.setBackground(bgColor);
	}
	public void SalesEntryCenter()
	{
		BorderLayout border = new BorderLayout();
		border.setHgap(0);
		SalesEntryCenter.setLayout(border);
		SalesEntryCenter.add(SalesEntryCenterEast,BorderLayout.EAST);
		SalesEntryCenterEast();
		SalesEntryCenter.add(SalesEntryCenterCenter,BorderLayout.CENTER);
		SalesEntryCenterCenter();
	}
	@SuppressWarnings("static-access")
	public void SalesEntryCenterEast()
	{
		SalesEntryCenterEast.setBackground(bgColor);
		SalesEntryCenterEast.setPreferredSize(new Dimension(500,0));
		TitledBorder titleSalesEntryCenterEast=new TitledBorder("Existing Sales Invoice");
		titleSalesEntryCenterEast.setTitleFont(new Font("Agency FB", Font.PLAIN+Font.BOLD, 20));
		titleSalesEntryCenterEast.setTitleJustification(TitledBorder.CENTER);
		titleSalesEntryCenterEast.setTitlePosition(titleSalesEntryCenterEast.TOP);
		titleSalesEntryCenterEast.setTitleColor(Color.white);
		SalesEntryCenterEast.setBorder(titleSalesEntryCenterEast);
		SalesEntryCenterEast.setLayout(new BorderLayout());
		SalesEntryCenterEast.add(SalesEntryCenterEastNorth,BorderLayout.NORTH);
		SalesEntryCenterEastNorth();
		SalesEntryCenterEast.add(SalesEntryCenterEastCenter,BorderLayout.CENTER);
		SalesEntryCenterEastCenter();
	}
	public void SalesEntryCenterEastNorth()
	{
		SalesEntryCenterEastNorth.setBackground(Color.white);
		SalesEntryCenterEastNorth.setPreferredSize(new Dimension(0,70));
		SalesEntryCenterEastNorth.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=0;
		c.gridy=0;
		SalesEntryCenterEastNorth.add(lblFromDate,c);
		lblFromDate.setFont(font);
		c.gridx=1;
		c.gridy=0;
		jdcFromDate.setPreferredSize(new Dimension(110,20));
		SalesEntryCenterEastNorth.add(jdcFromDate,c);//
		jdcFromDate.setDateFormatString("dd-MM-yy");
		jdcFromDate.setDate(new Date());
		jdcFromDate.setBackground(Color.white);
		c.gridx=2;
		c.gridy=0;
		SalesEntryCenterEastNorth.add(lblToDate,c);
		lblToDate.setFont(font);
		c.gridx=3;
		c.gridy=0;
		SalesEntryCenterEastNorth.add(jdcToDate,c);//
		jdcToDate.setDateFormatString("dd-MM-yy");
		jdcToDate.setDate(new Date());
		jdcToDate.setBackground(Color.white);
		c.gridx=4;
		c.gridy=0;
		SalesEntryCenterEastNorth.add(btnfind,c);//
		btnfind.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 15));
		btnfind.setBackground(btnColor);
	}
	public void SalesEntryCenterEastCenter()
	{
		SalesEntryCenterEastCenter.setBackground(Color.white);
		scrollExistingInvoice.setPreferredSize(new Dimension(465,192));
		SalesEntryCenterEastCenter.add(scrollExistingInvoice);
		scrollExistingInvoice.getViewport().setBackground(Color.white);
		tableFind.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollExistingInvoice.setOpaque(false);
		tableFind.setOpaque(false);
		tableFind.setShowGrid(true);
		tableFind.setRowHeight(tableFind.getRowHeight()+2);
	}
	public void SalesEntryCenterCenter()
	{
		SalesEntryCenterCenter.setBackground(bgColor);
		SalesEntryCenterCenter.setLayout(new BorderLayout());
		SalesEntryCenterCenter.add(SalesEntryCenterCenterCenter,BorderLayout.CENTER);
		SalesEntryCenterCenterCenter();
		SalesEntryCenterCenter.add(SalesEntryCenterCenterSouth,BorderLayout.SOUTH);
		SalesEntryCenterCenterSouth();
	}
	public void SalesEntryCenterCenterCenter()
	{
		SalesEntryCenterCenterCenter.setLayout(new BorderLayout());
		SalesEntryCenterCenterCenter.add(SalesEntryCenterCenterCenterCenter,BorderLayout.CENTER);
		SalesEntryCenterCenterCenterCenter();
		SalesEntryCenterCenterCenter.add(SalesEntryCenterCenterCenterEast, BorderLayout.EAST);
		SalesEntryCenterCenterCenterEast();

	}
	private void SalesEntryCenterCenterCenterCenter() 
	{
		SalesEntryCenterCenterCenterCenter.setBackground(Color.white);
		SalesEntryCenterCenterCenterCenter.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5, 25, 5, 5);
		c.gridx=0;
		c.gridy=0;
		SalesEntryCenterCenterCenterCenter.add(lblProduct,c);
		lblProduct.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=0;
		SalesEntryCenterCenterCenterCenter.add(cmbProduct.cmbSuggest,c);//
		cmbProduct.cmbSuggest.setPreferredSize(new Dimension(1,28));
		cmbProduct.cmbSuggest.setBackground(Color.white);
		c.gridx=0;
		c.gridy=1;
		SalesEntryCenterCenterCenterCenter.add(lblCategory,c);
		lblCategory.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=1;
		SalesEntryCenterCenterCenterCenter.add(cmbCategory,c);//
		cmbCategory.setPreferredSize(new Dimension(1,28));
		cmbCategory.setBackground(Color.white);
		c.gridx=0;
		c.gridy=2;
		SalesEntryCenterCenterCenterCenter.add(lblSubCategory,c);
		lblSubCategory.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=2;
		SalesEntryCenterCenterCenterCenter.add(cmbSubCategory,c);//
		cmbSubCategory.setPreferredSize(new Dimension(1,28));
		cmbSubCategory.setBackground(Color.white);
		c.gridx=0;
		c.gridy=3;
		SalesEntryCenterCenterCenterCenter.add(lblUnit,c);
		lblUnit.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=3;
		txtUnit.setPreferredSize(new Dimension(1,28));
		SalesEntryCenterCenterCenterCenter.add(txtUnit,c);//
		c.gridx=0;
		c.gridy=4;
		SalesEntryCenterCenterCenterCenter.add(lblDealerPrice,c);
		lblDealerPrice.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=4;
		txtDealerPrice.setPreferredSize(new Dimension(1,28));
		SalesEntryCenterCenterCenterCenter.add(txtDealerPrice,c);//
		c.gridx=0;
		c.gridy=5;
		SalesEntryCenterCenterCenterCenter.add(lblTradePrice,c);
		lblTradePrice.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=5;
		txtTradePrice.setPreferredSize(new Dimension(1,28));
		SalesEntryCenterCenterCenterCenter.add(txtTradePrice,c);//
	}
	private void SalesEntryCenterCenterCenterEast() 
	{
		SalesEntryCenterCenterCenterEast.setBackground(Color.white);
		SalesEntryCenterCenterCenterEast.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5, 5, 5, 30);
		c.gridx=0;
		c.gridy=0;
		SalesEntryCenterCenterCenterEast.add(lblStockQty,c);
		lblStockQty.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=0;
		SalesEntryCenterCenterCenterEast.add(txtStockQty,c);//
		txtStockQty.setPreferredSize(new Dimension(1,28));
		c.gridx=0;
		c.gridy=1;
		SalesEntryCenterCenterCenterEast.add(lblSalesQty,c);
		lblSalesQty.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=1;
		SalesEntryCenterCenterCenterEast.add(txtSalesQty,c);////
		txtSalesQty.setPreferredSize(new Dimension(1,28));
		c.gridx=0;
		c.gridy=2;
		SalesEntryCenterCenterCenterEast.add(lblAmount,c);
		lblAmount.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=2;
		SalesEntryCenterCenterCenterEast.add(txtAmount,c);////
		txtAmount.setPreferredSize(new Dimension(1,28));
		c.gridx=0;
		c.gridy=3;
		SalesEntryCenterCenterCenterEast.add(lblRemarks,c);
		lblRemarks.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=3;
		SalesEntryCenterCenterCenterEast.add(scrollRemarks,c);//
	}
	public void SalesEntryCenterCenterSouth()
	{
		SalesEntryCenterCenterSouth.setBackground(Color.white);
		SalesEntryCenterCenterSouth.setPreferredSize(new Dimension(0,60));
		SalesEntryCenterCenterSouth.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5, 10, 5, 5);
		c.gridx=0;
		c.gridy=0;
		SalesEntryCenterCenterSouth.add(btnSubmit,c);
		btnSubmit.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 13));
		btnSubmit.setBackground(btnColor);
		c.gridx=1;
		c.gridy=0;
		SalesEntryCenterCenterSouth.add(btnEdit,c);
		btnEdit.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 13));
		btnEdit.setBackground(btnColor);
		c.gridx=2;
		c.gridy=0;
		SalesEntryCenterCenterSouth.add(btnRefresh,c);
		btnRefresh.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 13));
		btnRefresh.setBackground(btnColor);
	}
	public void SalesEntrySouth()
	{
		SalesEntrySouth.setPreferredSize(new Dimension(0,310));
		SalesEntrySouth.setLayout(new BorderLayout());
		SalesEntrySouth.add(SalesEntrySouthCenter,BorderLayout.CENTER);
		SalesEntrySouthCenter();
		SalesEntrySouth.add(SalesEntrySouthSouth,BorderLayout.SOUTH);
		SalesEntrySouthSouth();
	}
	public void SalesEntrySouthCenter()
	{
		tableInsert.getTableHeader().setReorderingAllowed(false);
		scrollMain.setPreferredSize(new Dimension(1125,190));
		SalesEntrySouthCenter.add(scrollMain);
		SalesEntrySouthCenter.setBackground(Color.white);
		scrollMain.getViewport().setBackground(Color.white);
		tableInsert.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollMain.setOpaque(false);
		tableInsert.setOpaque(false);
		tableInsert.setShowGrid(true);
		tableInsert.setRowHeight(tableInsert.getRowHeight()+2);
	}
	public void SalesEntrySouthSouth()
	{
		SalesEntrySouthSouth.setLayout(new BorderLayout());
		SalesEntrySouthSouth.setBackground(Color.white);
		SalesEntrySouthSouth.add(SalesEntrySouthSouthCenter,BorderLayout.CENTER);
		SalesEntrySouthSouthCenter();
		SalesEntrySouthSouth.add(SalesEntrySouthSouthEast, BorderLayout.EAST);
		SalesEntrySouthSouthEast();
	}
	private void SalesEntrySouthSouthCenter() 
	{
		SalesEntrySouthSouthCenter.setBackground(Color.white);
		SalesEntrySouthSouthCenter.setPreferredSize(new Dimension(1, 90));
		SalesEntrySouthSouthCenter.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(1, 1, 5, 5);
		c.gridx=0;
		c.gridy=0;
		SalesEntrySouthSouthCenter.add(lblPaymentProtocol,c);
		lblPaymentProtocol.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 15));
		c.insets=new Insets(1, 5, 5, 5);
		c.gridx=1;
		c.gridy=0;
		SalesEntrySouthSouthCenter.add(cmbPaymentProtocol,c);//
		cmbPaymentProtocol.setPreferredSize(new Dimension(1, 35));
		cmbPaymentProtocol.setBackground(Color.white);
		c.gridx=2;
		c.gridy=0;
		SalesEntrySouthSouthCenter.add(lblTotalAmount,c);
		lblTotalAmount.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 15));
		c.gridx=3;
		c.gridy=0;
		SalesEntrySouthSouthCenter.add(txtTotalAmount,c);//
		txtTotalAmount.setPreferredSize(new Dimension(1, 35));
		txtTotalAmount.setForeground(Color.BLUE);
		txtTotalAmount.setFont(font);
		c.gridx=4;
		c.gridy=0;
		SalesEntrySouthSouthCenter.add(lblPaidAmount,c);
		lblPaidAmount.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 15));
		c.gridx=5;
		c.gridy=0;
		SalesEntrySouthSouthCenter.add(txtPaidAmount,c);//
		txtPaidAmount.setPreferredSize(new Dimension(1, 35));
		txtPaidAmount.setForeground(Color.BLUE);
		txtPaidAmount.setFont(font);
		c.insets = new Insets(5, 1, 5, 5);
		c.gridx=0;
		c.gridy=1;
		SalesEntrySouthSouthCenter.add(lblReference,c);
		lblReference.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 15));
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=1;
		c.gridy=1;
		SalesEntrySouthSouthCenter.add(txtReference,c);//
		txtReference.setPreferredSize(new Dimension(1, 35));
		txtReference.setForeground(Color.BLUE);
		txtReference.setFont(font);
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=2;
		c.gridy=1;
		SalesEntrySouthSouthCenter.add(lblDiscount,c);
		lblDiscount.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 15));
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=3;
		c.gridy=1;
		SalesEntrySouthSouthCenter.add(txtDiscount,c);//
		txtDiscount.setPreferredSize(new Dimension(1, 35));
		txtDiscount.setForeground(Color.BLUE);
		txtDiscount.setFont(font);
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=4;
		c.gridy=1;
		SalesEntrySouthSouthCenter.add(lblDue,c);
		lblDue.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 15));
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=5;
		c.gridy=1;
		SalesEntrySouthSouthCenter.add(txtDue,c);//
		txtDue.setPreferredSize(new Dimension(1, 35));
		txtDue.setForeground(Color.BLUE);
		txtDue.setFont(font);
	}
	private void SalesEntrySouthSouthEast() 
	{
		SalesEntrySouthSouthEast.setBackground(Color.white);
		SalesEntrySouthSouthEast.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(1, 5, 5, 5);
		c.gridx=0;
		c.gridy=0;
		SalesEntrySouthSouthEast.add(btnConfirm,c);
		btnConfirm.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 15));
		btnConfirm.setBackground(btnColor);
		c.insets=new Insets(1, 5, 5, 8);
		c.gridx=1;
		c.gridy=0;
		SalesEntrySouthSouthEast.add(btnClear,c);//
		btnClear.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 15));
		btnClear.setBackground(btnColor);
	}
	public void init()
	{
		setPreferredSize(new Dimension(1140,740));
		setLayout(new BorderLayout());
	}
}
