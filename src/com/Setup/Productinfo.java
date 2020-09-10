package com.Setup;
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

public class Productinfo extends JPanel
{
	private static final long serialVersionUID = 1L;
	SessionBean sessionBean;
	JPanel productinfoPanelWest=new JPanel();
	JPanel productinfoPanelWestNorth=new JPanel();
	JLabel lblProductinfo = new JLabel("Search By : ");
	SuggestText cmbProductinfo = new SuggestText();
	JPanel productinfoPanelWestCenter = new JPanel();
	JLabel lblProductId = new JLabel("Product ID");
	JLabel lblProductName = new JLabel("Product Name");
	JLabel lblProductDescription = new JLabel("Description");
	JLabel lblDealerPrice = new JLabel("Dealer Price");
	JLabel lblTradePrice = new JLabel("Trade Price");
	JLabel lblSupplierName = new JLabel("Supplier");
	JLabel lblUserName = new JLabel("User Name");
	JLabel lblUnit = new JLabel("Unit");
	JLabel lblUserType = new JLabel("");
	JTextField txtProductId = new JTextField(20);
	JTextField txtProductName = new JTextField(20);
	JTextField txtUnit = new JTextField(20);
	JTextField txtProductDescription = new JTextField(20);
	JTextField txtDealerPrice = new JTextField(20);
	JTextField txtTradePrice = new JTextField(20);
	JTextField txtProfitPerUint = new JTextField(20);
	JComboBox<String> cmbSupplier = new JComboBox<>();
	JTextField txtUserName = new JTextField(20);
	JLabel lblCategory = new JLabel("Category");
	JComboBox<String> cmbCategory = new JComboBox<>();
	JLabel lblSubCategory = new JLabel("Sub Category");
	JComboBox<String> cmbSubCategory = new JComboBox<>();
	JPanel productinfoPanelWestSouth = new JPanel();
	JButton btnAdd = new JButton("Add",new ImageIcon("images/add.png"));
	JButton btnEdit = new JButton("Edit",new ImageIcon("images/edit.png"));
	JButton btnRefresh = new JButton("Refresh",new ImageIcon("images/refresh.png"));
	JButton btnDelete = new JButton("Delete",new ImageIcon("images/delete.png"));//
	JPanel productinfoPanelcenter = new JPanel();
	String[] column = {"Product ID","Product Name","M.R.P Price","Net Price"};
	Object[][] row = {};
	DefaultTableModel modelPro = new DefaultTableModel(row,column)
	{
		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row, int column)
		{
			return false;
		}
	};
	JTable tablePro = new JTable(modelPro);
	JScrollPane scrollPro = new JScrollPane(tablePro,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	GridBagConstraints cn = new GridBagConstraints();
	Font font = new Font("Baskerville O",Font.BOLD,20);
	Color bgColor = new Color(2, 191, 185);
	Color btnColor = new Color(0, 134, 139);
	boolean isUpdatePro = false;
	BufferedImage image;

	public Productinfo(SessionBean bean)
	{
		init();
		cmp();
		btnAction();
		btnini(true);
		defaultEditable(false);
		this.sessionBean = bean;
		txtUserName.setText(sessionBean.getUserName());
		lblUserType.setText(sessionBean.getUserType());
	}
	private void btnini(boolean b) 
	{
		btnAdd.setEnabled(b);
		btnEdit.setEnabled(!b);
		btnDelete.setEnabled(!b);
	}
	private void defaultEditable(boolean d)
	{
		txtProductId.setEditable(d);
		txtUserName.setEditable(d);
	}
	public void txtEditable(boolean e)
	{
		txtProductId.setEditable(e);
		cmbCategory.setEnabled(e);
		cmbSubCategory.setEnabled(e);
		cmbSupplier.setEnabled(e);
		txtProductName.setEditable(e);
		txtProductDescription.setEditable(e);
		txtUnit.setEditable(e);
		txtDealerPrice.setEditable(e);
		txtTradePrice.setEditable(e);
		txtUserName.setEditable(e);
	}
	private void txtClear()
	{
		cmbProductinfo.txtSuggest.setText("");
		txtProductName.setText("");
		txtProductDescription.setText("");
		txtUnit.setText("");
		txtDealerPrice.setText("");
		txtTradePrice.setText("");
	}
	public void refreshWork()
	{
		autoidpro();
		txtClear();
		txtUserName.setText(sessionBean.getUserName());
		categoryLoad();
		subCategoryLoad("%");
		supplierLoad();
		cmbDataLoadProduct();
		tableDataLoadProduct();
		btnini(true);
		isUpdatePro = false;
		txtEditable(true);
	}
	private boolean checkValidation()
	{
		if(!txtProductId.getText().trim().isEmpty())
		{
			if(cmbCategory.getSelectedIndex()!=0 && cmbCategory.getSelectedItem()!=null)
			{
				if(cmbSubCategory.getSelectedIndex()!=0 && cmbSubCategory.getSelectedItem()!=null)
				{
					if(!txtProductName.getText().trim().isEmpty())
					{
						if(!txtProductDescription.getText().trim().isEmpty())
						{
							if(!txtUnit.getText().trim().isEmpty())
							{
								if(!txtDealerPrice.getText().trim().isEmpty())
								{
									if(!txtTradePrice.getText().trim().isEmpty())
									{
										if(cmbSupplier.getSelectedIndex()!=0 && cmbSupplier.getSelectedItem()!=null)
										{
											if(!txtUserName.getText().trim().isEmpty())
											{
												return true;
											}
											else
											{
												JOptionPane.showMessageDialog(null, "Please Enter a User Name!","Warning..",JOptionPane.WARNING_MESSAGE);
											}
										}
										else
										{
											JOptionPane.showMessageDialog(null, "Please Select a Supplier Name!","Warning..",JOptionPane.WARNING_MESSAGE);
										}
									}
									else
									{
										JOptionPane.showMessageDialog(null, "Please Enter a Net Price!","Warning..",JOptionPane.WARNING_MESSAGE);
									}
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Please Enter a M.R.P price!","Warning..",JOptionPane.WARNING_MESSAGE);
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Please Enter Unit!","Warning..",JOptionPane.WARNING_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Please Enter some product description!","Warning..",JOptionPane.WARNING_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please Enter a product Name!","Warning..",JOptionPane.WARNING_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please select a sub category!","Warning..",JOptionPane.WARNING_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please select a category!","Warning..",JOptionPane.WARNING_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please Enter a product id!","Warning..",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	private boolean checkConfirmation(String caption)
	{
		int a = JOptionPane.showConfirmDialog(null,caption,"Confirmation...",JOptionPane.YES_NO_OPTION);
		if(a == JOptionPane.YES_OPTION)
		{
			return true;
		}
		return false;
	}
	private void searchProductData(String proid)
	{
		try
		{
			String sql = "select catId,catName,subcatid,subcatName,productid,productName,productDesc,unit,dealerPrice,tradePrice,supplierId,supplierName,userName from tbproductinfo where productid ='"+proid+"'";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				txtProductId.setText(rs.getString("productid"));
				cmbCategory.setSelectedItem(rs.getString("catid")+"~"+rs.getString("catName"));
				cmbSubCategory.setSelectedItem(rs.getString("subCatid")+"~"+rs.getString("subcatName"));
				txtProductName.setText(rs.getString("productName"));
				txtProductDescription.setText(rs.getString("productDesc"));
				txtUnit.setText(rs.getString("unit"));
				txtDealerPrice.setText(rs.getString("dealerPrice"));
				txtTradePrice.setText(rs.getString("tradePrice"));
				cmbSupplier.setSelectedItem(rs.getString("supplierId")+"~"+rs.getString("supplierName"));
				txtUserName.setText(rs.getString("userName"));
				isUpdatePro = true;
				btnini(false);
				txtEditable(false);
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(From SearchProductData)","Warning...",JOptionPane.WARNING_MESSAGE);
		}
	}
	private boolean insertData()
	{
		try
		{
			String category = cmbCategory.getSelectedItem().toString();
			StringTokenizer token = new StringTokenizer(category,"~");
			String catId = token.nextToken();
			String catName = token.nextToken();
			String subcat = cmbSubCategory.getSelectedItem().toString();
			StringTokenizer token1 = new StringTokenizer(subcat,"~");
			String subcatId = token1.nextToken();
			String subcatName = token1.nextToken();
			String supplier = cmbSupplier.getSelectedItem().toString();
			StringTokenizer token2 = new StringTokenizer(supplier,"~");
			String supId = token2.nextToken();
			String supName = token2.nextToken();
			String sql = "insert into tbproductinfo (catId,catName,subcatid,subcatName,productID,productName,productDesc,unit,dealerPrice,tradePrice,supplierId,supplierName,userName,userip,entryTime) values "
					+ "('"+catId+"','"+catName+"','"+subcatId+"','"+subcatName+"','"+txtProductId.getText().trim()+"','"+txtProductName.getText().trim()+"','"+txtProductDescription.getText().trim()+"','"+txtUnit.getText().trim()+"','"+txtDealerPrice.getText().trim()+"','"+txtTradePrice.getText().trim()+"',"
					+ "'"+supId+"','"+supName+"','"+txtUserName.getText().trim()+"','',now())";
			Dbconnection.connection();
			Dbconnection.sta.executeUpdate(sql);
			Dbconnection.con.close();
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(From insertData ProductInfo)","Warning...",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	private boolean deleteData()
	{
		try
		{
			String sql = "delete from tbproductinfo where productid = '"+txtProductId.getText().trim()+"'";
			Dbconnection.connection();
			Dbconnection.sta.executeUpdate(sql);
			Dbconnection.con.close();
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(From deleteData ProductInfo)","Warning...",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	public void tableDataLoadProduct()
	{
		for(int i=modelPro.getRowCount()-1; i>=0; i--)
		{
			modelPro.removeRow(i);
		}
		try
		{
			String sql = "select productid,productName,dealerPrice,tradePrice from tbproductinfo order by "
					+ "cast(SUBSTRING(productid,LOCATE('-',productid)+1,LENGTH(productid)-locate('-',productid)) as UNSIGNED)";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				modelPro.addRow(new Object[]{rs.getString("productid"),rs.getString("productName"),rs.getString("dealerPrice"),rs.getString("tradePrice")});
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(From tableDataLoad ProductInfo)","Error...",JOptionPane.ERROR_MESSAGE);
		}
	}

	public void cmbDataLoadProduct()
	{
		try
		{
			cmbProductinfo.v.clear();
			cmbProductinfo.v.add("");
			String sql = "select productid,productName from tbproductInfo order by "
					+ "CAST(substring(productid,LOCATE('-',productid)+1,LENGTH(productid)-locate('-',productid))as UNSIGNED)";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				String productid = rs.getString("productid").trim();
				String productName = rs.getString("productName").trim();
				String idName = productid+"~"+productName;
				cmbProductinfo.v.add(idName);
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
		txtDealerPrice.addKeyListener(new KeyListener() 
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
			public void keyReleased(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}
		});
		txtTradePrice.addKeyListener(new KeyListener()
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
			public void keyReleased(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}
		});
		tablePro.addMouseListener(new MouseListener() 
		{
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent arg0) 
			{
				int row = tablePro.getSelectedRow();
				searchProductData(tablePro.getValueAt(row, 0).toString());
			}
		});
		tablePro.addKeyListener(new KeyListener() 
		{
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) 
			{
				int row = tablePro.getSelectedRow();
				searchProductData(tablePro.getValueAt(row, 0).toString());
			}
			public void keyPressed(KeyEvent arg0) {}
		});
		cmbProductinfo.cmbSuggest.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!cmbProductinfo.txtSuggest.getText().trim().isEmpty())
				{
					String pro = cmbProductinfo.txtSuggest.getText().trim();
					StringTokenizer token = new StringTokenizer(pro,"~");
					searchProductData(token.nextToken());
				}
			}
		});
		cmbCategory.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(cmbCategory.getSelectedIndex()!=0 && cmbCategory.getSelectedItem()!=null)
				{
					String s1 = cmbCategory.getSelectedItem().toString();
					StringTokenizer token = new StringTokenizer(s1,"~");
					subCategoryLoad(token.nextToken());
				}
				else
				{
					subCategoryLoad("%");
				}
			}
		});
		btnAdd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(checkValidation())
				{
					if(checkConfirmation(isUpdatePro?"Sure To Update":"Sure To Save?"))
					{
						if(isUpdatePro)
						{
							if(deleteData())
							{
								if(insertData())
								{
									JOptionPane.showMessageDialog(null, "All Information Updated Successfully!",
											"Information...",JOptionPane.INFORMATION_MESSAGE);
									refreshWork();
								}
							}
						}
						else
						{
							if(insertData())
							{
								JOptionPane.showMessageDialog(null, "All Information Save Successfully!",
										"Information...",JOptionPane.INFORMATION_MESSAGE);
								refreshWork();
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
				btnini(true);
				isUpdatePro = true;
				txtEditable(true);
			}
		});
		btnRefresh.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				refreshWork();
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
									"Delete...",JOptionPane.INFORMATION_MESSAGE);
							refreshWork();
						}
					}
				}
			}
		});
	}
	public void autoidpro()
	{
		try
		{
			String sql="select ifnull(max(cast(SubString(productId,locate('-',productId)+1,"
					+ "length(productId)-locate('-',productId))as UNSIGNED)),0)+1 as proId from tbproductinfo";
			Dbconnection.connection();
			ResultSet rs=Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{
				String proid = rs.getString("proid");
				txtProductId.setText("Productid-"+proid);
			}
			Dbconnection.con.close();
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp,"Error..",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void categoryLoad()
	{
		try
		{
			cmbCategory.removeAllItems();
			cmbCategory.addItem("");
			String sql = "select catId,catName from tbcategoryinfo ORDER by cast(substring(catid,locate('-',catid)+1,length(catid)-locate('-',catid))as UNSIGNED)";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				String id = rs.getString("catid").trim();
				String Name = rs.getString("catName").trim();
				cmbCategory.addItem(id+"~"+Name);
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(from CategoryLoad ProductInfo)","Sorry...",JOptionPane.WARNING_MESSAGE);
		}
	}
	public void subCategoryLoad(String catid)
	{
		try
		{
			cmbSubCategory.removeAllItems();
			cmbSubCategory.addItem("");
			String sql = "select subcatid,subcatName from tbsubcategoryinfo where catid like ('"+catid+"') order by cast(substring(subcatid,locate('-',subcatid)+1,length(subcatid)-locate('-',subcatid))as UNSIGNED)";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				String id = rs.getString("subcatid");
				String Name = rs.getString("subcatName");
				cmbSubCategory.addItem(id+"~"+Name);
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(from subCategoryLoad ProductInfo)","Warning...",JOptionPane.WARNING_MESSAGE);
		}
	}
	public void supplierLoad()
	{
		try
		{
			cmbSupplier.removeAllItems();
			cmbSupplier.addItem("");
			String sql = "select supplierid,supplierName from tbsupplierinfo order by " +
					"cast(SUBSTRING(supplierid,LOCATE('-',supplierid)+1,LENGTH(supplierid)-" +
					"locate('-',supplierid)) as UNSIGNED)";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				String supplierid = rs.getString("supplierid").trim();
				String supplierName = rs.getString("supplierName").trim();
				cmbSupplier.addItem(supplierid+"~"+supplierName);
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(From SupplierLoad ProductInfo)","Warning...",JOptionPane.WARNING_MESSAGE);
		}
	}
	public void cmp()
	{
		BorderLayout border = new BorderLayout();
		border.setVgap(0);
		setLayout(border);
		productinfoPanelWest();
		add(productinfoPanelWest,BorderLayout.WEST);
		productinfoPanelcenter();
		add(productinfoPanelcenter,BorderLayout.CENTER);
	}
	@SuppressWarnings("static-access")
	public void productinfoPanelWest()
	{
		productinfoPanelWest.setBackground(bgColor);
		productinfoPanelWest.setPreferredSize(new Dimension(560,1));
		TitledBorder productTitle=new TitledBorder("Product Info");
		productTitle.setTitleFont(new Font("Agency FB",Font.PLAIN+Font.BOLD,22));
		productTitle.setTitleJustification(TitledBorder.CENTER);
		productTitle.setTitlePosition(productTitle.TOP);
		productinfoPanelWest.setBorder(productTitle);
		productinfoPanelWest.setLayout(new BorderLayout());
		productinfoPanelWest.add(productinfoPanelWestNorth,BorderLayout.NORTH);
		productinfoPanelWestNorth();
		productinfoPanelWest.add(productinfoPanelWestCenter,BorderLayout.CENTER);
		productinfoPanelWestCenter();
		productinfoPanelWest.add(productinfoPanelWestSouth,BorderLayout.SOUTH);
		productinfoPanelWestSouth();

	}
	public void productinfoPanelWestNorth()
	{
		productinfoPanelWestNorth.setBackground(bgColor);
		productinfoPanelWestNorth.setPreferredSize(new Dimension(0,150));
		//productinfoPanelWestNorth.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		productinfoPanelWestNorth.setLayout(new GridBagLayout());

		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(35,5,5,5);
		cn.gridx=0;
		cn.gridy=0;
		productinfoPanelWestNorth.add(lblProductinfo,cn);
		lblProductinfo.setPreferredSize(new Dimension(78,30));
		lblProductinfo.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 15));
		cn.insets=new Insets(35,5,5,25);
		cn.gridx=1;
		cn.gridy=0;
		productinfoPanelWestNorth.add(cmbProductinfo.cmbSuggest,cn);
		cmbProductinfo.cmbSuggest.setPreferredSize(new Dimension(280,33));
		cmbProductinfo.cmbSuggest.setBackground(Color.white);

	}
	public void productinfoPanelWestCenter()
	{
		productinfoPanelWestCenter.setBackground(Color.white);
		productinfoPanelWestCenter.setLayout(new GridBagLayout());
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=0;
		productinfoPanelWestCenter.add(lblProductId,cn);
		lblProductId.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=0;
		productinfoPanelWestCenter.add(txtProductId,cn);//
		txtProductId.setPreferredSize(new Dimension(1,28));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=1;
		productinfoPanelWestCenter.add(lblCategory,cn);
		lblCategory.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=1;
		productinfoPanelWestCenter.add(cmbCategory,cn);//
		cmbCategory.setPreferredSize(new Dimension(1,28));
		cmbCategory.setBackground(Color.white);
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=2;
		productinfoPanelWestCenter.add(lblSubCategory,cn);
		lblSubCategory.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=2;
		productinfoPanelWestCenter.add(cmbSubCategory,cn);//
		cmbSubCategory.setPreferredSize(new Dimension(1,28));
		cmbSubCategory.setBackground(Color.white);
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=3;
		productinfoPanelWestCenter.add(lblProductName,cn);
		lblProductName.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=3;
		productinfoPanelWestCenter.add(txtProductName,cn);//
		txtProductName.setPreferredSize(new Dimension(1,28));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=4;
		productinfoPanelWestCenter.add(lblProductDescription,cn);
		lblProductDescription.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=4;
		productinfoPanelWestCenter.add(txtProductDescription,cn);//
		txtProductDescription.setPreferredSize(new Dimension(1,28));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=5;
		productinfoPanelWestCenter.add(lblUnit,cn);
		lblUnit.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=5;
		productinfoPanelWestCenter.add(txtUnit,cn);//
		txtUnit.setPreferredSize(new Dimension(1,28));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=6;
		productinfoPanelWestCenter.add(lblDealerPrice,cn);
		lblDealerPrice.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=6;
		productinfoPanelWestCenter.add(txtDealerPrice,cn);//
		txtDealerPrice.setPreferredSize(new Dimension(1,28));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=7;
		productinfoPanelWestCenter.add(lblTradePrice,cn);
		lblTradePrice.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=7;
		productinfoPanelWestCenter.add(txtTradePrice,cn);//
		txtTradePrice.setPreferredSize(new Dimension(1,28));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=8;
		productinfoPanelWestCenter.add(lblSupplierName,cn);
		lblSupplierName.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=8;
		productinfoPanelWestCenter.add(cmbSupplier,cn);//
		cmbSupplier.setPreferredSize(new Dimension(1,28));
		cmbSupplier.setBackground(Color.white);
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=9;
		productinfoPanelWestCenter.add(lblUserName,cn);
		lblUserName.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=9;
		productinfoPanelWestCenter.add(txtUserName,cn);//
		txtUserName.setPreferredSize(new Dimension(1,28));
		cn.gridx=2;
		cn.gridy=9;
		productinfoPanelWestCenter.add(lblUserType,cn);//
	}
	public void productinfoPanelWestSouth()
	{
		productinfoPanelWestSouth.setBackground(Color.white);
		productinfoPanelWestSouth.setPreferredSize(new Dimension(0,150));
		productinfoPanelWestSouth.setLayout(new GridBagLayout());
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5,5);
		cn.gridx=0;
		cn.gridy=0;
		productinfoPanelWestSouth.add(btnAdd,cn);
		btnAdd.setForeground(Color.black);
		btnAdd.setBackground(new Color(0,134,139));
		btnAdd.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		cn.insets=new Insets(5, 5, 5,5);
		cn.gridx=1;
		cn.gridy=0;
		productinfoPanelWestSouth.add(btnEdit,cn);
		btnEdit.setForeground(Color.black);
		btnEdit.setBackground(new Color(0,134,139));
		btnEdit.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		cn.insets=new Insets(5, 5, 5,5);
		cn.gridx=2;
		cn.gridy=0;
		productinfoPanelWestSouth.add(btnRefresh,cn);
		btnRefresh.setForeground(Color.black);
		btnRefresh.setBackground(new Color(0,134,139));
		btnRefresh.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		cn.insets=new Insets(5, 5, 5,5);
		cn.gridx=3;
		cn.gridy=0;
		productinfoPanelWestSouth.add(btnDelete,cn);
		btnDelete.setForeground(Color.black);
		btnDelete.setBackground(new Color(0,134,139));
		btnDelete.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
	}
	@SuppressWarnings("static-access")
	public void productinfoPanelcenter()
	{
		productinfoPanelcenter.setBackground(bgColor);
		productinfoPanelcenter.setPreferredSize(new Dimension(560,1));
		TitledBorder productTableTitle=new TitledBorder("Existing Product");
		productTableTitle.setTitleFont(new Font("Agency FB",Font.PLAIN+Font.BOLD,22));
		productTableTitle.setTitleJustification(TitledBorder.CENTER);
		productTableTitle.setTitlePosition(productTableTitle.TOP);
		productinfoPanelcenter.setBorder(productTableTitle);
		scrollPro.setPreferredSize(new Dimension(540,690));
		tablePro.getTableHeader().setReorderingAllowed(false);
		scrollPro.getViewport().setBackground(Color.white);
		tablePro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePro.setOpaque(false);
		scrollPro.setOpaque(false);
		tablePro.setShowGrid(true);
		tablePro.setRowHeight(tablePro.getRowHeight()+2);
		productinfoPanelcenter.add(scrollPro);
	}
	public void init()
	{
		setPreferredSize(new Dimension(1140,740));
		BorderLayout border=new BorderLayout();
		border.setHgap(25);
		setLayout(border);
	}

}
