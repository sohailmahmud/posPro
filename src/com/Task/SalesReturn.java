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

public class SalesReturn extends JPanel
{
	private static final long serialVersionUID = 1L;
	SessionBean sessionBean;

	JPanel SalesReturnNorth=new JPanel();
	JPanel SalesReturnCenter=new JPanel();
	JPanel SalesReturnSouth=new JPanel();
	JPanel SalesReturnSouthCenter=new JPanel();
	JPanel SalesReturnSouthSouth=new JPanel();
	JPanel SalesReturnCenterEast=new JPanel();
	JPanel SalesReturnCenterCenter=new JPanel();
	JPanel SalesReturnCenterCenterCenter=new JPanel();
	JPanel SalesReturnCenterCenterCenterCenter = new JPanel();
	JPanel SalesReturnCenterCenterCenterEast = new JPanel();
	JPanel SalesReturnCenterCenterSouth=new JPanel();
	JPanel SalesReturnCenterEastNorth=new JPanel();
	JPanel SalesReturnCenterEastCenter=new JPanel();

	JLabel lblSalesReturnNo=new JLabel("Sales Return No.");
	JLabel lblUserName = new JLabel("User Name");
	JLabel lblDate = new JLabel("Current Date");
	JLabel lblReturnDate = new JLabel("Return Date");
	JLabel lblSalesNo = new JLabel("Sales No.");
	JLabel lblProduct = new JLabel("Product");
	JLabel lblCategory = new JLabel("Category");
	JLabel lblSubCategory = new JLabel("Sub Category");
	JLabel lblUnit = new JLabel("Unit");
	JLabel lblTradePrice = new JLabel("Trade Price");
	JLabel lblSalesQty=new JLabel("Sales Qty");
	JLabel lblReciveQuantity=new JLabel("Receive Qty");
	JLabel lblAlreadyReceipt = new JLabel("Already Receipt");
	JLabel lblAmount = new JLabel("Amount");
	JLabel lblRemarks=new JLabel("Remarks");
	JLabel lblFromDate=new JLabel("From Date");
	JLabel lblToDate=new JLabel("To Date");
	JLabel lblTotalAmount=new JLabel("Total Amount");
	JTextField txtSalesReturnNo= new JTextField(15);
	JTextField txtUserName=new JTextField(15);
	JDateChooser jdcDate=new JDateChooser();
	JDateChooser jdcReturnDate = new JDateChooser();
	SuggestText cmbSalesNo = new SuggestText();
	JComboBox<String> cmbProduct = new JComboBox<String>();
	JComboBox<String> cmbCategory = new JComboBox<>();
	JComboBox<String> cmbSubCategory = new JComboBox<>();
	JTextField txtUnit = new JTextField(15);
	JTextField txtTradePrice = new JTextField(15);
	JTextField txtSalesQty = new JTextField(15);
	JTextField txtReciveQty = new JTextField(15);
	JTextField txtAlreadyReceipt = new JTextField(15);
	JTextField txtAmount = new JTextField(15);
	JTextField txtTotalAmount=new JTextField(15);
	JTextArea txtRemarks = new JTextArea(3,1);
	JScrollPane scrollRemarks=new JScrollPane(txtRemarks,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	JDateChooser jdcFromDate=new JDateChooser();
	JDateChooser jdcToDate=new JDateChooser();
	JButton btnSubmit=new JButton("Submit ",new ImageIcon("images/btnSubmit.png"));
	JButton btnEdit=new JButton("Edit      ",new ImageIcon("images/btnEdit.png"));
	JButton btnRefresh=new JButton("Refresh",new ImageIcon("images/btnRefresh.png"));
	JButton btnConfirm=new JButton("Confirm",new ImageIcon("images/confirm.png"));
	JButton btnClear =new JButton("Clear   ",new ImageIcon("images/btnRefresh.png"));
	JButton btnfind=new JButton(" Find ");

	String[] column={"Product ID","Product Name","Unit","Trade Price","Sales Qty","Receive Qty","Already Receipt","Amount","Remarks"};
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

	String[] column2={"SalesReturn No.","Sales No.","Total Amount","Current Date"};
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
	JScrollPane scrollExistingInvoice=new JScrollPane(tableFind,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	GridBagConstraints c=new GridBagConstraints();
	Font font=new Font("Baskerville O",Font.BOLD,13);
	Font font2=new Font("Baskerville O",Font.BOLD,17);
	BufferedImage image;
	Color bgColor = new Color(2, 191, 185);
	Color btnColor = new Color(0, 134, 139);
	DecimalFormat df = new DecimalFormat("#0.00");
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	boolean isFind = false;
	boolean isEdit = false;
	boolean isSearch =true;

	public SalesReturn(SessionBean bean)
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
		txtSalesReturnNo.setEditable(e);
		txtUserName.setEditable(e);
		cmbCategory.setEnabled(e);
		cmbSubCategory.setEnabled(e);
		txtUnit.setEditable(e);
		txtTradePrice.setEditable(e);
		txtSalesQty.setEditable(e);
		txtAlreadyReceipt.setEditable(e);
		txtAmount.setEditable(e);
		txtTotalAmount.setEditable(e);
	}
	private void isSearchEditable(boolean s)
	{
		cmbProduct.setEnabled(s);
		txtReciveQty.setEditable(s);
		txtAlreadyReceipt.setEditable(s);
		txtRemarks.setEditable(s);
	}
	private void refreshProductWise()
	{
		cmbCategory.setSelectedItem("");
		cmbSubCategory.setSelectedItem("");
		txtUnit.setText("");
		txtTradePrice.setText("");
		txtSalesQty.setText("");
		txtAlreadyReceipt.setText("");
	}
	private void refreshWork()
	{
		cmbProduct.removeAllItems();
		cmbProduct.addItem("");
		cmbProduct.setEnabled(true);
		refreshProductWise();
		txtReciveQty.setText("");
		txtAmount.setText("");
		txtRemarks.setText("");
	}
	private void clearWork()
	{
		refreshWork();
		autoSalesReturnNo();
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
		double receiveQty, tradePrice, amount;
		receiveQty = Double.parseDouble(txtReciveQty.getText().trim().isEmpty()?"0":txtReciveQty.getText().trim());
		tradePrice = Double.parseDouble(txtTradePrice.getText().trim().isEmpty()?"0":txtTradePrice.getText().trim());
		amount = receiveQty*tradePrice;
		txtAmount.setText(df.format(amount));
	}
	private void calcTotalAmount()
	{
		double total=0;
		for(int i=0; i<modelInsert.getRowCount(); i++)
		{
			total = total+Double.parseDouble(modelInsert.getValueAt(i, 7).toString());
		}
		txtTotalAmount.setText(df.format(total));
	}
	private boolean checkValidation()
	{
		if(!cmbSalesNo.txtSuggest.getText().trim().isEmpty())
		{
			if(cmbProduct.getSelectedIndex()!=0 && cmbProduct.getSelectedItem()!=null)
			{
				if(!txtReciveQty.getText().trim().isEmpty())
				{
					return true;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Enter Receive Qty!","warning",JOptionPane.WARNING_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please Select product!","warning",JOptionPane.WARNING_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please Select Sales Number!","warning",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	private boolean checkDoubleEntry()
	{
		StringTokenizer token = new StringTokenizer(cmbProduct.getSelectedItem().toString(), "~");
		String productid = token.nextToken();
		for(int i=0; i<modelInsert.getRowCount(); i++)
		{
			String product = modelInsert.getValueAt(i, 0).toString();
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
		StringTokenizer token = new StringTokenizer(cmbProduct.getSelectedItem().toString(), "~");
		String productid = token.nextToken();
		String productName = token.nextToken();
		modelInsert.addRow(new Object[]
				{
						productid,productName,txtUnit.getText().trim(),txtTradePrice.getText().trim(),
						txtSalesQty.getText().trim(),txtReciveQty.getText().trim(),txtAlreadyReceipt.getText().trim(),
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
			String sqlinfo = "delete from tbSalesReturninfo where salesReturnNo = '"+txtSalesReturnNo.getText().trim()+"'";
			String sqlDetails = "delete from tbSalesReturndetails where salesReturnNo = '"+txtSalesReturnNo.getText().trim()+"'";
			Dbconnection.connection();
			Dbconnection.sta.executeUpdate(sqlinfo);
			Dbconnection.sta.executeUpdate(sqlDetails);
			Dbconnection.con.close();
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from deleteData SalesReturn","error",JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	private boolean insertData()
	{
		try
		{
			Dbconnection.connection();
			Dbconnection.con.setAutoCommit(false);
			String sqlinfo = "insert into tbSalesReturninfo(salesReturnNo,salesNo,curDate,returnDate,"
					+ "totalAmount,userName,userip,entryTime) values "
					+ "('"+txtSalesReturnNo.getText().trim()+"','"+cmbSalesNo.txtSuggest.getText().trim()+"',"
					+ "'"+dateFormat.format(jdcDate.getDate())+"','"+dateFormat.format(jdcReturnDate.getDate())+"',"
					+ "'"+txtTotalAmount.getText().trim()+"','"+txtUserName.getText().trim()+"','',now())";
			Dbconnection.sta.executeUpdate(sqlinfo);
			for(int a=0; a<tableInsert.getRowCount(); a++)
			{
				String sqlDetails = "insert into tbSalesReturnDetails(salesReturnNo,productid,productName,unit,"
						+ "tradePrice,salesQty,receiveQty,alreadyReceipt,amount,remark) values"
						+ "('"+txtSalesReturnNo.getText().trim()+"','"+modelInsert.getValueAt(a, 0)+"',"
						+ "'"+modelInsert.getValueAt(a, 1)+"','"+modelInsert.getValueAt(a, 2)+"',"
						+ "'"+modelInsert.getValueAt(a, 3)+"','"+modelInsert.getValueAt(a, 4)+"',"
						+ "'"+modelInsert.getValueAt(a, 5)+"','"+modelInsert.getValueAt(a, 6)+"',"
						+ "'"+modelInsert.getValueAt(a, 7)+"','"+modelInsert.getValueAt(a, 8)+"')";
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
				JOptionPane.showMessageDialog(null, ex+"from rollback InsertData SalesReturn");
			}
			JOptionPane.showMessageDialog(null, e+"from insertData SalesReturn","error",JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	public void tableFindDataLoad()
	{
		for(int i=modelFind.getRowCount()-1; i>=0; i--)
		{
			modelFind.removeRow(i);
		}
		try
		{
			String sql = "Select salesReturnNo,salesNo,totalAmount,returnDate from tbSalesReturninfo Order by "
					+ "cast(substring(salesReturnNo,locate('-',salesReturnNo)+1,length(salesReturnNo)" +
					"-locate('-',salesReturnNo))as UNSIGNED) DESC";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				modelFind.addRow(new Object[]
						{
								rs.getString("salesReturnNo"),rs.getString("salesNo"),rs.getString("totalAmount"),
								rs.getString("returnDate")
						});
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from tableFindDataLoad SalesReturn","error",JOptionPane.ERROR_MESSAGE);
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
		cmbProduct.addItem(modelInsert.getValueAt(i, 0)+"~"+modelInsert.getValueAt(i, 1));
		cmbProduct.setSelectedItem(modelInsert.getValueAt(i, 0)+"~"+modelInsert.getValueAt(i, 1));
		txtUnit.setText(modelInsert.getValueAt(i, 2).toString());
		txtTradePrice.setText(modelInsert.getValueAt(i, 3).toString());
		txtSalesQty.setText(modelInsert.getValueAt(i, 4).toString());
		txtReciveQty.setText(modelInsert.getValueAt(i, 5).toString());
		txtAlreadyReceipt.setText(modelInsert.getValueAt(i, 6).toString());
		txtAmount.setText(modelInsert.getValueAt(i, 7).toString());
		txtRemarks.setText(modelInsert.getValueAt(i, 8).toString());
		calcAmount();
		modelInsert.removeRow(i);
		calcTotalAmount();
		isSearchEditable(false);
		btnRefresh.setEnabled(false);
		isSearch = false;
		btnConfirm.setEnabled(false);
	}
	private void searchDataFindTableWise(String SalesReturn)
	{
		for(int a=modelInsert.getRowCount()-1; a>=0; a--)
		{
			modelInsert.removeRow(a);
		}
		String sql = "Select a.salesReturnNo,a.salesNo,a.curDate,a.returnDate,a.totalAmount,a.userName,b.salesReturnNo,"
				+ "b.productid,b.productName,b.unit,b.tradePrice,b.salesQty,b.receiveQty,b.alreadyReceipt,"
				+ "b.amount,b.remark from tbSalesReturninfo a Inner Join tbSalesReturndetails b "
				+ "on a.salesReturnNo = b.salesReturnNo where a.salesReturnNo='"+SalesReturn+"'";
		try
		{
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			int x=0;
			while(rs.next())
			{
				if(x==0)
				{
					txtSalesReturnNo.setText(rs.getString("salesReturnNo"));
					cmbSalesNo.cmbSuggest.setSelectedItem(rs.getString("salesNo"));
					txtUserName.setText(rs.getString("userName"));
					jdcDate.setDate(rs.getDate("curDate"));
					jdcReturnDate.setDate(rs.getDate("returnDate"));
				}
				modelInsert.addRow(new Object[]
						{
							rs.getString("productid"),rs.getString("productName"),rs.getString("unit"),
							rs.getString("tradePrice"),rs.getString("salesQty"),rs.getString("receiveQty"),
							rs.getString("alreadyReceipt"),rs.getString("amount"),rs.getString("remark")
						});
				x++;
			}
			calcTotalAmount();
			Dbconnection.con.close();
			refreshWork();
			cmbSalesNo.txtSuggest.setEnabled(false);
			btnRefresh.setEnabled(true);
			btnConfirm.setEnabled(false);
			isEdit = false;
			btnClear.setEnabled(true);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from searchDataFindTableWise","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void dateWiseFind()
	{
		for(int i=modelFind.getRowCount()-1; i>=0; i--)
		{
			modelFind.removeRow(i);
		}
		try
		{
			String sql = "Select salesReturnNo,salesNo,totalAmount,returnDate from tbSalesReturninfo where returnDate "
					+ "Between '"+dateFormat.format(jdcFromDate.getDate())+"' and '"+dateFormat.format(jdcToDate.getDate())+"'";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{
				modelFind.addRow(new Object[]
						{
							rs.getString("salesReturnNo"),rs.getString("salesNo"),
							rs.getString("totalAmount"),rs.getString("returnDate")
						});
				while(rs.next())
				{
					modelFind.addRow(new Object[]
							{
								rs.getString("salesReturnNo"),rs.getString("salesNo"),
								rs.getString("totalAmount"),rs.getString("returnDate")
							});
				}
				Dbconnection.con.close();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Sorry!\nWe are not find any Sales Return at this moment.","warning",JOptionPane.WARNING_MESSAGE);
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from dateWiseFind SalesReturn","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void btnAction() 
	{
		cmbSalesNo.cmbSuggest.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!cmbSalesNo.txtSuggest.getText().trim().isEmpty())
				{
					salesWiseProductLoad(cmbSalesNo.txtSuggest.getText().trim());
				}
				else
				{
					cmbProduct.removeAllItems();
					cmbProduct.addItem("");
					refreshProductWise();
				}
			}
		});
		cmbProduct.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(cmbProduct.getSelectedIndex()!=0 && cmbProduct.getSelectedItem()!=null)
				{
					StringTokenizer token = new StringTokenizer(cmbProduct.getSelectedItem().toString(), "~");
					productWiseLoad(token.nextToken());
				}
				else
				{
					refreshProductWise();
				}
			}
		});
		txtReciveQty.addKeyListener(new KeyListener() 
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
					JOptionPane.showMessageDialog(null, "Please Confirm Previous Sales Return Invoice!","warning",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		tableFind.addKeyListener(new KeyListener() 
		{
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) {}
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
						cmbSalesNo.txtSuggest.setEnabled(false);
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
									JOptionPane.showMessageDialog(null, "All information Updated Successfully!",
											"information",JOptionPane.INFORMATION_MESSAGE);
									clearWork();
									cmbSalesNo.txtSuggest.setText("");
									cmbSalesNo.txtSuggest.setEnabled(true);
									btnClear.setEnabled(true);
									isSearch = true;
								}
							}
						}
						isFind = false;
					}
					else
					{
						if(checkConfirmation("Sure To Save?"))
						{
							if(insertData())
							{
								JOptionPane.showMessageDialog(null, "All Information Save Successfully!",
										"information",JOptionPane.INFORMATION_MESSAGE);
								clearWork();
								cmbSalesNo.txtSuggest.setText("");
								cmbSalesNo.txtSuggest.setEnabled(true);
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
				cmbSalesNo.txtSuggest.setEnabled(true);
				btnRefresh.setEnabled(false);
			}
		});
		btnRefresh.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				refreshWork();
				cmbSalesNo.txtSuggest.setText("");
				cmbSalesNo.txtSuggest.setEnabled(true);
			}
		});
		btnClear.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				clearWork();
				cmbSalesNo.txtSuggest.setText("");
				cmbSalesNo.txtSuggest.setEnabled(true);
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
	public void autoSalesReturnNo()
	{
		try
		{
			String sql = "Select ifnull(max(cast(substring(salesReturnNo,Locate('-',salesReturnNo)+1,"
					+ "Length(salesReturnNo)-Locate('-',salesReturnNo))as Unsigned)),0)+1 as id from tbSalesReturninfo";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				txtSalesReturnNo.setText("SalesReturn-"+rs.getString("id"));
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from autoSalesReturnNo SalesReturn","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void salesNoLoad()
	{
		cmbSalesNo.v.clear();
		cmbSalesNo.v.add("");
		try
		{
			String sql = "Select salesNo from tbSalesEntryinfo order by "
					+ "cast(substring(salesNo,locate('-',salesNo)+1,"
					+ "LENGTH(salesNo)-locate('-',salesNo))as UNSIGNED) DESC";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbSalesNo.v.add(rs.getString("salesNo"));
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from salesNoLoad SalesReturn","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void salesWiseProductLoad(String sales)
	{
		cmbProduct.removeAllItems();
		cmbProduct.addItem("");
		try
		{
			String sql = "Select productid,productName from tbSalesEntryDetails where salesNo = '"+sales+"'";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbProduct.addItem(rs.getString("productid")+"~"+rs.getString("productName"));
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from salesWiseProductLoad SalesReturn","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void productWiseLoad(String product)
	{
		try
		{
			String sql = "Select catid,catName,subCatid,subCatName,unit,tradePrice,"
					+ "(select ifnull(sum(salesQty),0) from tbSalesEntryDetails where salesNo = '"+cmbSalesNo.txtSuggest.getText().trim()+"' and productid = '"+product+"') as salesQty,"
					+ "(select ifnull(sum(AlreadyReceipt),0) from tbSalesReturnDetails where productid like '"+product+"') as alreadyReceipt "
					+ "from tbProductinfo where productid = '"+product+"'";
			Dbconnection.connection();
			Dbconnection.con.setAutoCommit(false);
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
				txtTradePrice.setText(rs.getString("tradePrice"));
				txtSalesQty.setText(rs.getString("salesQty"));
				txtAlreadyReceipt.setText(rs.getString("alreadyReceipt"));
			}
			Dbconnection.con.commit();
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from productWiseLoad SalesReturn","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void cmp()
	{
		add(SalesReturnNorth,BorderLayout.NORTH);
		SalesReturnNorth();
		add(SalesReturnCenter,BorderLayout.CENTER);
		SalesReturnCenter();
		add(SalesReturnSouth,BorderLayout.SOUTH);
		SalesReturnSouth();
	}
	@SuppressWarnings("static-access")
	public void SalesReturnNorth()
	{
		SalesReturnNorth.setPreferredSize(new Dimension(0,100));
		SalesReturnNorth.setBackground(bgColor);
		TitledBorder SalesReturnTitle=new TitledBorder("Sales Return");
		SalesReturnTitle.setTitleFont(new Font("Agency FB",Font.BOLD,22));
		SalesReturnTitle.setTitleJustification(TitledBorder.CENTER);
		SalesReturnTitle.setTitlePosition(SalesReturnTitle.TOP);
		SalesReturnNorth.setBorder(SalesReturnTitle);
		SalesReturnNorth.setLayout(new BorderLayout());
		SalesReturnNorth.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=0;
		c.gridy=0;
		SalesReturnNorth.add(lblSalesReturnNo,c);
		lblSalesReturnNo.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=1;
		c.gridy=0;
		SalesReturnNorth.add(txtSalesReturnNo,c);//
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=2;
		c.gridy=0;
		SalesReturnNorth.add(lblUserName,c);
		lblUserName.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.insets=new Insets(5,5, 5, 100);
		c.gridx=3;
		c.gridy=0;
		SalesReturnNorth.add(txtUserName,c);//
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=4;
		c.gridy=0;
		SalesReturnNorth.add(lblDate,c);
		lblDate.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=5;
		c.gridy=0;
		jdcDate.setPreferredSize(new Dimension(120,20));
		jdcDate.setDateFormatString("dd-MM-yyyy");
		SalesReturnNorth.add(jdcDate,c);//
		jdcDate.setDate(new Date());
		jdcDate.setBackground(bgColor);
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=6;
		c.gridy=0;
		SalesReturnNorth.add(lblReturnDate,c);
		lblReturnDate.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));;
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=7;
		c.gridy=0;
		jdcReturnDate.setPreferredSize(new Dimension(120,20));
		jdcReturnDate.setDateFormatString("dd-MM-yyyy");
		SalesReturnNorth.add(jdcReturnDate,c);//
		jdcReturnDate.setDate(new Date());
		jdcReturnDate.setBackground(bgColor);
	}
	public void SalesReturnCenter()
	{
		BorderLayout border=new BorderLayout();
		border.setHgap(0);
		SalesReturnCenter.setLayout(border);
		SalesReturnCenter.add(SalesReturnCenterEast,BorderLayout.EAST);
		SalesReturnCenterEast();
		SalesReturnCenter.add(SalesReturnCenterCenter,BorderLayout.CENTER);
		SalesReturnCenterCenter();
	}
	@SuppressWarnings("static-access")
	public void  SalesReturnCenterEast()
	{
		SalesReturnCenterEast.setBackground(bgColor);
		SalesReturnCenterEast.setPreferredSize(new Dimension(500,0));
		TitledBorder titlePurchaseReciptCenterEast=new TitledBorder("Existing Sales Return Invoice");
		titlePurchaseReciptCenterEast.setTitleFont(new Font("Agency FB", Font.PLAIN+Font.BOLD, 20));
		titlePurchaseReciptCenterEast.setTitleJustification(TitledBorder.CENTER);
		titlePurchaseReciptCenterEast.setTitlePosition(titlePurchaseReciptCenterEast.TOP);
		titlePurchaseReciptCenterEast.setTitleColor(Color.white);
		SalesReturnCenterEast.setBorder(titlePurchaseReciptCenterEast);
		SalesReturnCenterEast.setLayout(new BorderLayout());
		SalesReturnCenterEast.add(SalesReturnCenterEastNorth,BorderLayout.NORTH);
		SalesReturnCenterEastNorth();
		SalesReturnCenterEast.add(SalesReturnCenterEastCenter,BorderLayout.CENTER);
		SalesReturnCenterEastCenter();
	}
	public void SalesReturnCenterEastNorth()
	{
		SalesReturnCenterEastNorth.setBackground(Color.white);
		SalesReturnCenterEastNorth.setPreferredSize(new Dimension(0,70));
		SalesReturnCenterEastNorth.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5, 5, 5, 5);
		c.gridx=0;
		c.gridy=0;
		SalesReturnCenterEastNorth.add(lblFromDate,c);
		lblFromDate.setFont(font);
		c.gridx=1;
		c.gridy=0;
		jdcFromDate.setPreferredSize(new Dimension(120,20));
		jdcFromDate.setDateFormatString("dd-MM-yy");
		SalesReturnCenterEastNorth.add(jdcFromDate,c);//
		jdcFromDate.setDate(new Date());
		jdcFromDate.setBackground(Color.white);
		c.gridx=2;
		c.gridy=0;

		SalesReturnCenterEastNorth.add(lblToDate,c);
		lblToDate.setFont(font);
		c.gridx=3;
		c.gridy=0;
		jdcToDate.setPreferredSize(new Dimension(120,20));
		jdcToDate.setDateFormatString("dd-MM-yy");
		SalesReturnCenterEastNorth.add(jdcToDate,c);//
		jdcToDate.setDate(new Date());
		jdcToDate.setBackground(Color.white);
		c.gridx=4;
		c.gridy=0;
		SalesReturnCenterEastNorth.add(btnfind,c);//
		btnfind.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 15));
		btnfind.setBackground(btnColor);
	}
	public void SalesReturnCenterEastCenter()
	{
		SalesReturnCenterEastCenter.setBackground(Color.white);
		/*tableFind.setAutoResizeMode(tableInsert.AUTO_RESIZE_OFF);
		tableFind.getColumnModel().getColumn(0).setPreferredWidth(110);
		tableFind.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableFind.getColumnModel().getColumn(2).setPreferredWidth(140);
		tableFind.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableFind.getTableHeader().setReorderingAllowed(false);*/
		scrollExistingInvoice.setPreferredSize(new Dimension(465,202));
		SalesReturnCenterEastCenter.add(scrollExistingInvoice);
		scrollExistingInvoice.getViewport().setBackground(Color.white);
		tableFind.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollExistingInvoice.setOpaque(false);
		tableFind.setOpaque(false);
		tableFind.setShowGrid(true);
		tableFind.setRowHeight(tableFind.getRowHeight()+2);
	}
	public void  SalesReturnCenterCenter()
	{
		SalesReturnCenterCenter.setLayout(new BorderLayout());
		SalesReturnCenterCenter.add(SalesReturnCenterCenterCenter,BorderLayout.CENTER);
		SalesReturnCenterCenterCenter();
		SalesReturnCenterCenter.add(SalesReturnCenterCenterSouth,BorderLayout.SOUTH);
		SalesReturnCenterCenterSouth();
	}
	public void SalesReturnCenterCenterCenter()
	{
		SalesReturnCenterCenterCenter.setLayout(new BorderLayout());
		SalesReturnCenterCenterCenter.add(SalesReturnCenterCenterCenterCenter, BorderLayout.CENTER);
		SalesReturnCenterCenterCenterCenter();
		SalesReturnCenterCenterCenter.add(SalesReturnCenterCenterCenterEast, BorderLayout.EAST);
		SalesReturnCenterCenterCenterEast();
	}
	private void SalesReturnCenterCenterCenterCenter() 
	{
		SalesReturnCenterCenterCenterCenter.setBackground(Color.white);
		SalesReturnCenterCenterCenterCenter.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5, 20, 5, 5);
		c.gridx=0;
		c.gridy=0;
		SalesReturnCenterCenterCenterCenter.add(lblSalesNo,c);
		lblSalesNo.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=0;
		SalesReturnCenterCenterCenterCenter.add(cmbSalesNo.cmbSuggest,c);//
		cmbSalesNo.cmbSuggest.setBackground(Color.white);
		cmbSalesNo.cmbSuggest.setPreferredSize(new Dimension(1,28));
		c.gridx=0;
		c.gridy=1;
		SalesReturnCenterCenterCenterCenter.add(lblProduct,c);
		lblProduct.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=1;
		SalesReturnCenterCenterCenterCenter.add(cmbProduct,c);//
		cmbProduct.setBackground(Color.white);
		cmbProduct.setPreferredSize(new Dimension(1,28));
		c.gridx=0;
		c.gridy=2;
		SalesReturnCenterCenterCenterCenter.add(lblCategory,c);
		lblCategory.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=2;
		SalesReturnCenterCenterCenterCenter.add(cmbCategory,c);//
		cmbCategory.setBackground(Color.white);
		cmbCategory.setPreferredSize(new Dimension(1,28));
		c.gridx=0;
		c.gridy=3;
		SalesReturnCenterCenterCenterCenter.add(lblSubCategory,c);
		lblSubCategory.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=3;
		SalesReturnCenterCenterCenterCenter.add(cmbSubCategory,c);//
		cmbSubCategory.setBackground(Color.white);
		cmbSubCategory.setPreferredSize(new Dimension(1,28));
		c.gridx=0;
		c.gridy=4;
		SalesReturnCenterCenterCenterCenter.add(lblUnit,c);
		lblUnit.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=4;
		txtUnit.setPreferredSize(new Dimension(1,28));
		SalesReturnCenterCenterCenterCenter.add(txtUnit,c);//
		c.gridx=0;
		c.gridy=5;
		SalesReturnCenterCenterCenterCenter.add(lblTradePrice,c);
		lblTradePrice.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=5;
		txtTradePrice.setPreferredSize(new Dimension(1,28));
		SalesReturnCenterCenterCenterCenter.add(txtTradePrice,c);//
	}
	private void SalesReturnCenterCenterCenterEast() 
	{
		SalesReturnCenterCenterCenterEast.setBackground(Color.white);
		SalesReturnCenterCenterCenterEast.setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 17);
		c.gridx=0;
		c.gridy=0;
		SalesReturnCenterCenterCenterEast.add(lblSalesQty,c);
		lblSalesQty.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=0;
		SalesReturnCenterCenterCenterEast.add(txtSalesQty,c);//
		txtSalesQty.setPreferredSize(new Dimension(1, 28));
		c.gridx=0;
		c.gridy=1;
		SalesReturnCenterCenterCenterEast.add(lblReciveQuantity,c);
		lblReciveQuantity.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=1;
		SalesReturnCenterCenterCenterEast.add(txtReciveQty,c);//
		txtReciveQty.setPreferredSize(new Dimension(1, 28));
		c.gridx=0;
		c.gridy=2;
		SalesReturnCenterCenterCenterEast.add(lblAlreadyReceipt,c);
		lblAlreadyReceipt.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=2;
		SalesReturnCenterCenterCenterEast.add(txtAlreadyReceipt,c);//
		txtAlreadyReceipt.setPreferredSize(new Dimension(1, 28));
		c.gridx=0;
		c.gridy=3;
		SalesReturnCenterCenterCenterEast.add(lblAmount,c);
		lblAmount.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=3;
		SalesReturnCenterCenterCenterEast.add(txtAmount,c);//
		txtAmount.setPreferredSize(new Dimension(1, 28));
		c.gridx=0;
		c.gridy=4;
		SalesReturnCenterCenterCenterEast.add(lblRemarks,c);
		lblRemarks.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.gridx=1;
		c.gridy=4;
		SalesReturnCenterCenterCenterEast.add(scrollRemarks,c);//
	}
	public void SalesReturnCenterCenterSouth()
	{
		SalesReturnCenterCenterSouth.setBackground(Color.white);
		SalesReturnCenterCenterSouth.setPreferredSize(new Dimension(0,40));
		SalesReturnCenterCenterSouth.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5, 10, 10, 5);
		c.gridx=0;
		c.gridy=0;
		SalesReturnCenterCenterSouth.add(btnSubmit,c);
		btnSubmit.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 13));
		btnSubmit.setBackground(btnColor);
		c.gridx=1;
		c.gridy=0;
		SalesReturnCenterCenterSouth.add(btnEdit,c);
		btnEdit.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 13));
		btnEdit.setBackground(btnColor);
		c.gridx=2;
		c.gridy=0;
		SalesReturnCenterCenterSouth.add(btnRefresh,c);
		btnRefresh.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 13));
		btnRefresh.setBackground(btnColor);
	}
	public void SalesReturnSouth()
	{
		SalesReturnSouth.setPreferredSize(new Dimension(0,320));
		SalesReturnSouth.setLayout(new BorderLayout());
		SalesReturnSouth.add(SalesReturnSouthCenter,BorderLayout.CENTER);
		SalesReturnSouthCenter();
		SalesReturnSouth.add(SalesReturnSouthSouth,BorderLayout.SOUTH);
		SalesReturnSouthSouth();
	}
	public void SalesReturnSouthCenter()
	{
		SalesReturnSouthCenter.setBackground(Color.white);
		tableInsert.getTableHeader().setReorderingAllowed(false);
		scrollMain.setPreferredSize(new Dimension(1130,240));
		SalesReturnSouthCenter.add(scrollMain);
		scrollMain.getViewport().setBackground(Color.white);
		tableInsert.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollMain.setOpaque(false);
		tableInsert.setOpaque(false);
		tableInsert.setShowGrid(true);
		tableInsert.setRowHeight(tableInsert.getRowHeight()+2);
	}
	public void SalesReturnSouthSouth()
	{
		SalesReturnSouthSouth.setBackground(Color.white);
		SalesReturnSouthSouth.setPreferredSize(new Dimension(0,70));
		SalesReturnSouthSouth.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(0, 10, 5, 10);
		c.gridx=0;
		c.gridy=0;
		SalesReturnSouthSouth.add(btnConfirm,c);
		btnConfirm.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 15));
		btnConfirm.setBackground(btnColor);
		c.gridx=1;
		c.gridy=0;
		SalesReturnSouthSouth.add(btnClear,c);//
		btnClear.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 15));
		btnClear.setBackground(btnColor);
		c.insets=new Insets(0, 300, 5, 5);
		c.gridx=2;
		c.gridy=0;
		SalesReturnSouthSouth.add(lblTotalAmount,c);
		lblTotalAmount.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 20));
		c.insets=new Insets(0, 5, 5, 5);
		c.gridx=3;
		c.gridy=0;
		SalesReturnSouthSouth.add(txtTotalAmount,c);//
		txtTotalAmount.setFont(font2);
		txtTotalAmount.setForeground(Color.BLUE);
	}

	public void init()
	{
		setPreferredSize(new Dimension(1140,740));
		setLayout(new BorderLayout());
	}
}