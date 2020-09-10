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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.Admin.Dbconnection;
import com.Admin.SessionBean;
import com.Admin.SuggestText;
import com.toedter.calendar.JDateChooser;

public class OpeningStock extends JPanel
{
	private static final long serialVersionUID = 1L;
	SessionBean sessionBean;
	JPanel OpeningStockPanelWest = new JPanel();
	JPanel OpeningStockPanelcenter = new JPanel();
	JPanel OpeningStockPanelWestNorth = new JPanel();
	JPanel OpeningStockPanelWestCenter = new JPanel();
	JPanel OpeningStockPanelWestSouth = new JPanel();
	SuggestText cmbOpeningStock = new SuggestText();
	JLabel lblOpeingStock = new JLabel("Search By:");
	JButton btnAdd = new JButton("Add",new ImageIcon("images/add.png"));
	JButton btnEdit = new JButton("Edit",new ImageIcon("images/edit.png"));
	JButton btnRefresh = new JButton("Refresh",new ImageIcon("images/refresh.png"));
	JButton btnDelete = new JButton("Delete",new ImageIcon("images/delete.png"));//
	JLabel lblStockID = new JLabel("Stock ID");
	JLabel lblCategory = new JLabel("Category");
	JLabel lblSubCategory = new JLabel("Sub Category");
	JLabel lblProduct = new JLabel("Product");	
	JLabel lblUnit = new JLabel("Unit");
	JLabel lblStockQuantity = new JLabel("Stock Qty");
	JLabel lblDealerPrice = new JLabel("Dealer Price");
	JLabel lblAmount = new JLabel("Amount");
	JLabel lblStockDate = new JLabel("Stock Date");
	JLabel lblSupplierName = new JLabel("Supplier");
	JLabel lblUserName = new JLabel("User Name");
	JTextField txtStockID = new JTextField(20);
	JTextField txtUnit = new JTextField(20);
	JTextField txtStockQuantity = new JTextField(20);
	JTextField txtDealerPrice = new JTextField(20);
	JTextField txtAmount = new JTextField(20);
	JTextField txtUserName = new JTextField(20);
	JComboBox<String> cmbCategory = new JComboBox<>();
	JComboBox<String> cmbSubCategory = new JComboBox<String>();
	SuggestText cmbProduct = new SuggestText();
	JComboBox<String> cmbSupplier = new JComboBox<String>();
	JDateChooser jdcStockDate = new JDateChooser();
	String[] column = {"Stock ID","Product Name","Stock Quantity","Stock Date"};
	Object[][] row = {};
	DefaultTableModel modelStock = new DefaultTableModel(row,column)
	{
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int column)
		{
			return false;
		}
	};
	JTable table = new JTable(modelStock);
	JScrollPane scroll = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	GridBagConstraints cn=new GridBagConstraints();
	Font font=new Font("Baskerville O",Font.BOLD,20);
	DecimalFormat df = new DecimalFormat("#0.00");
	boolean isUpdate = false;
	boolean isDelete = false;
	boolean isSearch = false;
	BufferedImage image;

	public OpeningStock(SessionBean bean)
	{
		init();
		cmp();
		btnAction();
		btnini(true);
		defaultEditable(false);
		this.sessionBean = bean;
		txtUserName.setText(sessionBean.getUserName());
	}
	private void btnini(boolean b)
	{
		btnAdd.setEnabled(b);
		btnEdit.setEnabled(!b);
		btnDelete.setEnabled(!b);
	}
	public void defaultEditable(boolean e)
	{
		txtStockID.setEditable(e);
		cmbCategory.setEnabled(e);
		cmbSubCategory.setEnabled(e);
		txtUnit.setEditable(e);
		txtDealerPrice.setEditable(e);
		txtAmount.setEditable(e);
		cmbSupplier.setEnabled(e);
		txtUserName.setEditable(e);
	}
	private void isSearchEditable(boolean s)
	{
		cmbProduct.txtSuggest.setEnabled(s);
		txtStockQuantity.setEditable(s);
		jdcStockDate.setEnabled(s);
	}
	public void refreshWork()
	{
		autoid();
		btnini(true);
		productLoad();
		cmbDataLoad();
		tableDataLoad();
		cmbOpeningStock.txtSuggest.setText("");
		cmbCategory.setSelectedIndex(0);
		cmbSubCategory.setSelectedIndex(0);
		cmbSupplier.setSelectedIndex(0);
		cmbProduct.txtSuggest.setText("");
		txtUnit.setText("");
		txtStockQuantity.setText("");
		txtDealerPrice.setText("");
		txtAmount.setText("");
		jdcStockDate.setDate(new Date());
		isUpdate = false;
		isSearch = false;
	}
	private void productWiseRefresh()
	{
		cmbCategory.removeAllItems();
		cmbCategory.addItem("");
		cmbSubCategory.removeAllItems();
		cmbSubCategory.addItem("");
		cmbSupplier.removeAllItems();
		cmbSupplier.addItem("");
		txtUnit.setText("");
		txtDealerPrice.setText("");
	}
	private void calcAmount()
	{
		double stockQty, dealerPrice, amount;
		stockQty = Double.parseDouble(txtStockQuantity.getText().trim().isEmpty()?"0":txtStockQuantity.getText().trim());
		dealerPrice  = Double.parseDouble(txtDealerPrice.getText().trim().isEmpty()?"0":txtDealerPrice.getText().trim());
		amount = stockQty*dealerPrice;
		txtAmount.setText(df.format(amount));
	}
	private boolean checkValidation()
	{
		if(!txtStockID.getText().trim().isEmpty())
		{
			if(!cmbProduct.txtSuggest.getText().trim().isEmpty())
			{
				if(cmbCategory.getSelectedIndex()!=0 && cmbCategory.getSelectedItem()!=null)
				{
					if(cmbSubCategory.getSelectedIndex()!=0 && cmbSubCategory.getSelectedItem()!=null)
					{
						if(!txtUnit.getText().trim().isEmpty())
						{
							if(!txtStockQuantity.getText().trim().isEmpty())
							{
								if(!txtDealerPrice.getText().trim().isEmpty())
								{
									if(!txtAmount.getText().trim().isEmpty())
									{
										if(cmbSupplier.getSelectedIndex()!=0 && cmbSupplier.getSelectedItem()!=null)
										{
											if(!txtUserName.getText().trim().isEmpty())
											{
												return true;
											}
											else
											{
												JOptionPane.showMessageDialog(null, "Please Enter User Name!","Warning..",JOptionPane.WARNING_MESSAGE);
											}
										}
										else
										{
											JOptionPane.showMessageDialog(null, "Please Enter Supplier Name or id!","Warning..",JOptionPane.WARNING_MESSAGE);
										}
									}
									else
									{
										JOptionPane.showMessageDialog(null, "Please Enter Amount!","Warning..",JOptionPane.WARNING_MESSAGE);
									}
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Please Enter Rate!","Warning..",JOptionPane.WARNING_MESSAGE);
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Please Enter Stock Quantity!","Warning..",JOptionPane.WARNING_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Please Enter Stock unit!","Warning..",JOptionPane.WARNING_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please select a Supplier","Warning..",JOptionPane.WARNING_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please select a SubCategory","Warning..",JOptionPane.WARNING_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please select a category!","Warning..",JOptionPane.WARNING_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please Enter a Stock id","warning..",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	private boolean checkConfirmation(String caption)
	{
		int a = JOptionPane.showConfirmDialog(null, caption,"Confirmation...",JOptionPane.YES_NO_OPTION);
		if(a == JOptionPane.YES_OPTION)
		{
			return true;
		}
		return false;
	}
	private boolean insertData()
	{
		try
		{
			String subCatid = "";
			String subCatName = "";
			StringTokenizer token2 = new StringTokenizer(cmbProduct.txtSuggest.getText().trim(),"~");
			String productid = token2.nextToken();
			String productName = token2.nextToken();
			StringTokenizer token = new StringTokenizer(cmbCategory.getSelectedItem().toString(),"~");
			String catid = token.nextToken();
			String catName = token.nextToken();
			if(cmbSubCategory.getSelectedIndex()!=0 && cmbSubCategory.getSelectedItem()!=null)
			{
				StringTokenizer token1 = new StringTokenizer(cmbSubCategory.getSelectedItem().toString(),"~");
				subCatid = token1.nextToken();
				subCatName = token1.nextToken();
			}
			StringTokenizer token3 = new StringTokenizer(cmbSupplier.getSelectedItem().toString(),"~");
			String supplierid = token3.nextToken();
			String supplierName = token3.nextToken();
			String stockDate = new SimpleDateFormat("yyyy-MM-dd").format(jdcStockDate.getDate());
			
			String sql = "insert into tbopeningstock(stockid,catid,catName,subcatid,subcatName,productid,"
					+ "productName,unit,stockqty,dealerPrice,amount,stockdate,supplierid,supplierName,userName,userip,entryTime) "
					+ "values('"+txtStockID.getText().trim()+"','"+catid+"','"+catName+"','"+subCatid+"',"
					+ "'"+subCatName+"','"+productid+"','"+productName+"','"+txtUnit.getText().trim()+"',"
					+ "'"+txtStockQuantity.getText().trim()+"','"+txtDealerPrice.getText().trim()+"',"
					+ "'"+txtAmount.getText().trim()+"','"+stockDate+"','"+supplierid+"','"+supplierName+"','"+txtUserName.getText().trim()+"','',now())";
			Dbconnection.connection();
			Dbconnection.sta.executeUpdate(sql);
			Dbconnection.con.close();
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(from insertData OpeningStock)","Warning..",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	private void searchData(String stock)
	{
		try
		{
			String sql = "select stockid,catId,catName,subcatid,subcatName,productid,productName,unit,"
					+ "stockqty,dealerPrice,amount,stockdate,supplierid,supplierName,userName from tbopeningstock where stockid ='"+stock+"'";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{
				txtStockID.setText(rs.getString("stockid"));
				cmbProduct.txtSuggest.setText(rs.getString("productid")+"~"+rs.getString("productName"));
				cmbProduct.cmbSuggest.setSelectedItem(rs.getString("productid")+"~"+rs.getString("productName"));
				cmbCategory.addItem(rs.getString("catid")+"~"+rs.getString("catName"));
				cmbCategory.setSelectedItem(rs.getString("catid")+"~"+rs.getString("catName"));
				String subCat = rs.getString("subcatid")+rs.getString("subCatName");
				if(!subCat.trim().isEmpty())
				{
					cmbSubCategory.addItem(rs.getString("subcatid")+"~"+rs.getString("subCatName"));
					cmbSubCategory.setSelectedItem(rs.getString("subcatid")+"~"+rs.getString("subcatName"));
				}
				cmbSupplier.addItem(rs.getString("supplierid")+"~"+rs.getString("supplierName"));
				cmbSupplier.setSelectedItem(rs.getString("supplierid")+"~"+rs.getString("supplierName"));
				txtUnit.setText(rs.getString("unit"));
				txtStockQuantity.setText(rs.getString("stockqty"));
				txtDealerPrice.setText(rs.getString("dealerPrice"));
				txtAmount.setText(rs.getString("amount"));
				jdcStockDate.setDate(rs.getDate("stockdate"));
				txtUserName.setText(rs.getString("userName"));
				isUpdate = true;
				isSearch = true;
				btnini(false);
				isSearchEditable(false);
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(From SearchOpeningStockData)","Error...",JOptionPane.ERROR_MESSAGE);
		}
	}
	private boolean deleteData()
	{
		try
		{
			String sql = "delete from tbopeningstock where stockid = '"+txtStockID.getText().trim()+"'";
			Dbconnection.connection();
			Dbconnection.sta.executeUpdate(sql);
			Dbconnection.con.close();
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(From deleteData OpeningStock)","Warning...",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	public void tableDataLoad()
	{
		for(int i = modelStock.getRowCount()-1; i>=0; i--)
		{
			modelStock.removeRow(i);
		}
		try
		{
			String sql = "select stockid,productName,stockqty,stockdate from tbopeningstock order by "
					+ "cast(SUBSTRING(stockid,LOCATE('-',stockid)+1,LENGTH(stockid)-locate('-',stockid)) as UNSIGNED)";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				modelStock.addRow(new Object[]{rs.getString("stockid"),rs.getString("productName"),rs.getString("stockqty"),rs.getString("stockdate")});
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(From tableDataLoad ProductInfo)","Error...",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void cmbDataLoad()
	{
		cmbOpeningStock.v.clear();
		cmbOpeningStock.v.add("");
		try
		{
			String sql = "select stockid from tbopeningstock order by "
					+ "CAST(substring(stockid,LOCATE('-',stockid)+1,LENGTH(stockid)-locate('-',stockid))as UNSIGNED)";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				String id = rs.getString("stockid").trim();
				cmbOpeningStock.v.add(id);
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(from cmbDataLoadProduct)","Error...",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void btnAction() 
	{
		cmbProduct.cmbSuggest.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(!isSearch)
				{
					if(!cmbProduct.txtSuggest.getText().trim().isEmpty())
					{
						String product = cmbProduct.txtSuggest.getText().trim();
						StringTokenizer token = new StringTokenizer(product, "~");
						productWiseLoad(token.nextToken());
					}
					else
					{
						productWiseRefresh();
					}
				}
			}
		});
		txtStockQuantity.addKeyListener(new KeyListener() 
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
		txtDealerPrice.addKeyListener(new KeyListener() 
		{
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) 
			{
				calcAmount();
			}
			public void keyPressed(KeyEvent arg0) {}
		});
		table.addMouseListener(new MouseListener() 
		{
			public void mouseReleased(MouseEvent arg0){}
			public void mousePressed(MouseEvent arg0){}
			public void mouseExited(MouseEvent arg0){}
			public void mouseEntered(MouseEvent arg0){}
			public void mouseClicked(MouseEvent arg0)
			{
				int row = table.getSelectedRow();
				searchData(table.getValueAt(row, 0).toString());
			}
		});
		table.addKeyListener(new KeyListener() 
		{
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0)
			{
				int row = table.getSelectedRow();
				searchData(table.getValueAt(row, 0).toString());
			}
			public void keyPressed(KeyEvent arg0){}
		});
		cmbOpeningStock.cmbSuggest.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!cmbOpeningStock.txtSuggest.getText().trim().isEmpty())
				{
					String stock = cmbOpeningStock.txtSuggest.getText().trim();
					StringTokenizer token = new StringTokenizer(stock, "~");
					searchData(token.nextToken());
				}
			}
		});
		btnAdd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(checkValidation())
				{
					if(checkConfirmation(isUpdate?"Sure To Update?":"Sure To Insert?"))
					{
						if(isUpdate)
						{
							if(deleteData())
							{
								if(insertData())
								{
									JOptionPane.showMessageDialog(null, "All Information Updated Successfully!","Information..",JOptionPane.INFORMATION_MESSAGE);
									refreshWork();
								}
							}
						}
						else
						{
							if(insertData())
							{
								JOptionPane.showMessageDialog(null, "All Information Save Successfully!","Information..",JOptionPane.INFORMATION_MESSAGE);
								refreshWork();
							}
						}
					}
				}
			}
		});
		btnRefresh.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				refreshWork();
				isSearchEditable(true);
			}
		});
		btnEdit.addActionListener(new ActionListener() 
		{	
			public void actionPerformed(ActionEvent arg0) 
			{
				btnini(true);
				isUpdate = true;
				isSearchEditable(true);
				txtStockQuantity.setEditable(true);
			}
		});
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(checkValidation())
				{
					if(checkConfirmation("Sure To Delete?"))
					{
						if(deleteData())
						{
							JOptionPane.showMessageDialog(null, "Your Information is Deleted!",
									"Success...!",JOptionPane.INFORMATION_MESSAGE);
							refreshWork();
						}
					}
				}
			}
		});
	}
	public void productLoad()
	{
		try
		{
			cmbProduct.v.clear();
			cmbProduct.v.add("");
			String sql = "select productid,productName from tbproductinfo ORDER by cast(substring(productid,locate('-',productid)+1,length(productid)-locate('-',productid))as UNSIGNED)";
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
			JOptionPane.showMessageDialog(null, e+""+"(Error from productLoad OpeningStock)","Warning..",JOptionPane.WARNING_MESSAGE);
		}
	}
	public void productWiseLoad(String product)
	{
		try
		{
			String sql = "Select catid,catName,subCatid,subCatName,unit,dealerPrice,supplierid,supplierName "
					+"from tbProductinfo where productid = '"+product+"'";
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
				cmbSupplier.removeAllItems();
				cmbSupplier.addItem("");
				cmbSupplier.addItem(rs.getString("supplierid")+"~"+rs.getString("supplierName"));
				cmbSupplier.setSelectedItem(rs.getString("supplierid")+"~"+rs.getString("supplierName"));
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from ProductWiseLoad Opening Stock","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void autoid()
	{
		try
		{
			String sql = "select ifnull(max(cast(subString(stockid,locate('-',stockid)+1,"
					+ "length(stockid)-locate('-',stockid))as UNSIGNED)),0)+1 id from tbopeningstock";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{
				String id = rs.getString("id").trim();
				txtStockID.setText("Stockid-"+id);
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public void cmp()
	{
		BorderLayout border = new BorderLayout();
		border.setVgap(0);
		setLayout(border);
		OpeningStockPanelWest();
		add(OpeningStockPanelWest,BorderLayout.WEST);
		OpeningStockPanelcenter();
		add(OpeningStockPanelcenter,BorderLayout.CENTER);
	}
	@SuppressWarnings("static-access")
	public void OpeningStockPanelWest()
	{
		OpeningStockPanelWest.setBackground(new Color(2, 191, 185));
		OpeningStockPanelWest.setPreferredSize(new Dimension(580,1));
		TitledBorder OpeningStockTitle=new TitledBorder("Opeing Stock");
		OpeningStockTitle.setTitleFont(new Font("Agency FB",Font.PLAIN+Font.BOLD,22));
		OpeningStockTitle.setTitleJustification(TitledBorder.CENTER);
		OpeningStockTitle.setTitlePosition(OpeningStockTitle.TOP);
		OpeningStockPanelWest.setBorder(OpeningStockTitle);
		OpeningStockPanelWest.setLayout(new BorderLayout());
		OpeningStockPanelWest.add(OpeningStockPanelWestNorth,BorderLayout.NORTH);
		OpeningStockPanelWestNorth();
		OpeningStockPanelWest.add(OpeningStockPanelWestCenter,BorderLayout.CENTER);
		OpeningStockPanelWestCenter();
		OpeningStockPanelWest.add(OpeningStockPanelWestSouth,BorderLayout.SOUTH);
		OpeningStockPanelWestSouth();
	}
	public void OpeningStockPanelWestNorth ()
	{
		OpeningStockPanelWestNorth.setBackground(new Color(2, 191, 185));
		OpeningStockPanelWestNorth.setPreferredSize(new Dimension(0,120));
		//OpeningStockPanelWestNorth.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		OpeningStockPanelWestNorth.setLayout(new GridBagLayout());

		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(35,5,5,5);
		cn.gridx=0;
		cn.gridy=0;
		OpeningStockPanelWestNorth.add(lblOpeingStock,cn);
		lblOpeingStock.setPreferredSize(new Dimension(78,30));
		lblOpeingStock.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 16));
		cn.insets=new Insets(35,5,5,25);
		cn.gridx=1;
		cn.gridy=0;
		OpeningStockPanelWestNorth.add(cmbOpeningStock.cmbSuggest,cn);
		cmbOpeningStock.cmbSuggest.setPreferredSize(new Dimension(280,33));
		cmbOpeningStock.cmbSuggest.setBackground(Color.white);

	}
	public void OpeningStockPanelWestCenter ()
	{
		OpeningStockPanelWestCenter.setBackground(Color.white);
		OpeningStockPanelWestCenter.setLayout(new GridBagLayout());
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 10, 5, 5);
		cn.gridx=0;
		cn.gridy=0;
		OpeningStockPanelWestCenter.add(lblStockID,cn);
		lblStockID.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.gridx=1;
		cn.gridy=0;
		OpeningStockPanelWestCenter.add(txtStockID,cn);//
		txtStockID.setPreferredSize(new Dimension(1,25));
		cn.gridx=0;
		cn.gridy=1;
		OpeningStockPanelWestCenter.add(lblProduct,cn);
		lblProduct.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.gridx=1;
		cn.gridy=1;
		OpeningStockPanelWestCenter.add(cmbProduct.cmbSuggest,cn);//
		cmbProduct.cmbSuggest.setBackground(Color.white);
		cn.gridx=0;
		cn.gridy=2;
		OpeningStockPanelWestCenter.add(lblCategory,cn);
		lblCategory.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.gridx=1;
		cn.gridy=2;
		OpeningStockPanelWestCenter.add(cmbCategory,cn);//
		cmbCategory.setBackground(Color.white);
		cn.gridx=0;
		cn.gridy=3;
		OpeningStockPanelWestCenter.add(lblSubCategory,cn);
		lblSubCategory.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.gridx=1;
		cn.gridy=3;
		OpeningStockPanelWestCenter.add(cmbSubCategory,cn);//
		cmbSubCategory.setBackground(Color.white);
		cn.gridx=0;
		cn.gridy=4;
		OpeningStockPanelWestCenter.add(lblUnit,cn);
		lblUnit.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.gridx=1;
		cn.gridy=4;
		OpeningStockPanelWestCenter.add(txtUnit,cn);//
		txtUnit.setPreferredSize(new Dimension(1,25));
		cn.gridx=0;
		cn.gridy=5;
		OpeningStockPanelWestCenter.add(lblStockQuantity,cn);
		lblStockQuantity.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.gridx=1;
		cn.gridy=5;
		OpeningStockPanelWestCenter.add(txtStockQuantity,cn);//
		txtStockQuantity.setPreferredSize(new Dimension(1,25));
		cn.gridx=0;
		cn.gridy=6;
		OpeningStockPanelWestCenter.add(lblDealerPrice,cn);
		lblDealerPrice.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.gridx=1;
		cn.gridy=6;
		OpeningStockPanelWestCenter.add(txtDealerPrice,cn);//
		txtDealerPrice.setPreferredSize(new Dimension(1,25));
		cn.gridx=0;
		cn.gridy=7;
		OpeningStockPanelWestCenter.add(lblAmount,cn);
		lblAmount.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.gridx=1;
		cn.gridy=7;
		OpeningStockPanelWestCenter.add(txtAmount,cn);//
		txtAmount.setPreferredSize(new Dimension(1,25));
		cn.gridx=0;
		cn.gridy=8;
		OpeningStockPanelWestCenter.add(lblStockDate,cn);
		lblStockDate.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.gridx=1;
		cn.gridy=8;
		OpeningStockPanelWestCenter.add(jdcStockDate,cn);//
		jdcStockDate.setPreferredSize(new Dimension(1,25));
		jdcStockDate.setDateFormatString("dd-MM-yyyy");
		jdcStockDate.setDate(new Date());
		jdcStockDate.setBackground(Color.white);
		cn.gridx=0;
		cn.gridy=9;
		OpeningStockPanelWestCenter.add(lblSupplierName,cn);
		lblSupplierName.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.gridx=1;
		cn.gridy=9;
		OpeningStockPanelWestCenter.add(cmbSupplier,cn);//
		cmbSupplier.setBackground(Color.white);
		cn.gridx=0;
		cn.gridy=10;
		OpeningStockPanelWestCenter.add(lblUserName,cn);
		lblUserName.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.gridx=1;
		cn.gridy=10;
		OpeningStockPanelWestCenter.add(txtUserName,cn);//
		txtUserName.setPreferredSize(new Dimension(1,25));
	}
	public void OpeningStockPanelWestSouth ()
	{
		OpeningStockPanelWestSouth.setBackground(Color.white);
		OpeningStockPanelWestSouth.setPreferredSize(new Dimension(0,160));
		OpeningStockPanelWestSouth.setLayout(new GridBagLayout());
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 25,5);
		cn.gridx=0;
		cn.gridy=0;
		OpeningStockPanelWestSouth.add(btnAdd,cn);
		btnAdd.setForeground(Color.black);
		btnAdd.setBackground(new Color(0,134,139));
		btnAdd.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		cn.insets=new Insets(5, 5, 25,5);
		cn.gridx=1;
		cn.gridy=0;
		OpeningStockPanelWestSouth.add(btnEdit,cn);
		btnEdit.setForeground(Color.black);
		btnEdit.setBackground(new Color(0,134,139));
		btnEdit.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		cn.insets=new Insets(5, 5, 25,5);
		cn.gridx=2;
		cn.gridy=0;
		OpeningStockPanelWestSouth.add(btnRefresh,cn);
		btnRefresh.setForeground(Color.black);
		btnRefresh.setBackground(new Color(0,134,139));
		btnRefresh.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		cn.insets=new Insets(5, 5, 25,5);
		cn.gridx=3;
		cn.gridy=0;
		OpeningStockPanelWestSouth.add(btnDelete,cn);
		btnDelete.setForeground(Color.black);
		btnDelete.setBackground(new Color(0,134,139));
		btnDelete.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
	}
	@SuppressWarnings("static-access")
	public void OpeningStockPanelcenter()
	{
		OpeningStockPanelcenter.setBackground(new Color(2, 191, 185));
		OpeningStockPanelcenter.setPreferredSize(new Dimension(520,1));
		TitledBorder OpeningStockTableTitle = new TitledBorder("Existing Product Stock");
		OpeningStockTableTitle.setTitleFont(new Font("Agency FB",Font.PLAIN+Font.BOLD,22));
		OpeningStockTableTitle.setTitleJustification(TitledBorder.CENTER);
		OpeningStockTableTitle.setTitlePosition(OpeningStockTableTitle.TOP);
		OpeningStockPanelcenter.setBorder(OpeningStockTableTitle);
		OpeningStockPanelcenter.setLayout(new BorderLayout());
		scroll.setPreferredSize(new Dimension(510,700));
		table.getTableHeader().setReorderingAllowed(false);
		scroll.getViewport().setBackground(Color.white);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setOpaque(false);
		scroll.setOpaque(false);
		table.setShowGrid(true);
		table.setRowHeight(table.getRowHeight()+2);
		OpeningStockPanelcenter.add(scroll);
	}
	public void init()
	{
		setPreferredSize(new Dimension(1140,740));
		BorderLayout border=new BorderLayout();
		border.setHgap(25);
		setLayout(border);
	}
}
