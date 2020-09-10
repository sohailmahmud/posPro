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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.Admin.Dbconnection;
import com.Admin.SessionBean;
import com.Admin.SuggestText;
import com.toedter.calendar.JDateChooser;

public class WastageOrBroken extends JPanel
{
	private static final long serialVersionUID = 1L;
	SessionBean sessionBean;
	JPanel wastagePanelWest=new JPanel();
	JPanel wastagePanelcenter=new JPanel();
	JPanel wastagePanelWestNorth=new JPanel();
	JPanel wastagePanelWestCenter=new JPanel();
	JPanel wastagePanelWestSouth=new JPanel();

	JLabel lblWastageMain = new JLabel("Search By : ");
	SuggestText cmbWastageMain = new SuggestText();

	JButton btnAdd=new JButton("Add",new ImageIcon("images/add.png"));
	JButton btnEdit=new JButton("Edit",new ImageIcon("images/edit.png"));
	JButton btnRefresh=new JButton("Refresh",new ImageIcon("images/refresh.png"));
	JButton btnDelete=new JButton("Delete",new ImageIcon("images/delete.png"));//

	JLabel lblWastageNo=new JLabel("Wastage No");
	JLabel lblProductType=new JLabel("Product Type");
	JLabel lblCategory=new JLabel("Category");
	JLabel lblSubCategory=new JLabel("Sub Category");	
	JLabel lblSupplier = new JLabel("Supplier");
	JLabel lblProduct=new JLabel("Product");
	JLabel lblDate=new JLabel("Date");
	JLabel lblWastageQty=new JLabel("Wastage Qty");
	JLabel lblDealerPrice=new JLabel("Dealer Price");
	JLabel lblAmount=new JLabel("Amount");
	JLabel lblRemarks=new JLabel("Remarks");
	JLabel lblUserName=new JLabel("User Name");

	JTextField txtWastageNo=new JTextField(20);
	JTextField txtWastageQty=new JTextField(20);
	JTextField txtDealerPrice=new JTextField(20);
	JTextField txtAmount=new JTextField(20);
	JTextField txtUserName=new JTextField(20);
	JComboBox<String> cmbCategory=new JComboBox<String>();
	JComboBox<String> cmbSubCategory=new JComboBox<String>();
	JComboBox<String> cmbSupplier = new JComboBox<String>();
	SuggestText cmbProduct=new SuggestText();
	JDateChooser wastageDate=new JDateChooser();
	JTextArea txtRemarks=new JTextArea(2,1);
	JScrollPane scrollRemarks=new JScrollPane(txtRemarks,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	String ara[]={"","Wastage","Broken"};
	JComboBox<String> cmbProductType = new JComboBox<String>(ara);

	String[] column={"Wastage No","Product Name","Wastage Qty","Wastage Date"};
	Object[][] row={};
	DefaultTableModel model = new DefaultTableModel(row,column)
	{
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int column)
		{
			return false;
		}
	};
	JTable table = new JTable(model);
	JScrollPane scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	GridBagConstraints cn=new GridBagConstraints();
	Font font=new Font("Baskerville O",Font.BOLD,20);
	BufferedImage image;
	boolean isUpdate = false;
	boolean isDelete = false;
	boolean isSearch = false;
	Color bgColor = new Color(2, 191, 185);
	Color btnColor = new Color(0, 134, 139);
	DecimalFormat df = new DecimalFormat("#0.00");

	public WastageOrBroken(SessionBean bean)
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
		btnAdd.setEnabled(b);
		btnEdit.setEnabled(!b);
		btnDelete.setEnabled(!b);
	}
	private void defaultEditable(boolean e)
	{
		txtWastageNo.setEditable(e);
		cmbCategory.setEnabled(e);
		cmbSubCategory.setEnabled(e);
		cmbSupplier.setEnabled(e);
		txtDealerPrice.setEditable(e);
		txtAmount.setEditable(e);
		txtUserName.setEditable(e);
	}
	private void isSearchEditable(boolean s)
	{
		cmbProduct.txtSuggest.setEnabled(s);
		cmbProductType.setEnabled(s);
		wastageDate.setEnabled(s);
		txtWastageQty.setEditable(s);
		txtRemarks.setEditable(s);
	}
	private void refreshWork()
	{
		autoid();
		btnIni(true);
		productLoad();
		tableDataLoad();
		cmbDataLoad();
		isSearchEditable(true);
		cmbWastageMain.txtSuggest.setText("");
		cmbProduct.txtSuggest.setText("");
		cmbCategory.setSelectedItem("");
		cmbSubCategory.setSelectedItem("");
		cmbSupplier.setSelectedItem("");
		cmbProductType.setSelectedItem("");
		wastageDate.setDate(new Date());
		txtWastageQty.setText("");
		txtDealerPrice.setText("");
		txtAmount.setText("");
		txtRemarks.setText("");
		isUpdate = false;
		isSearch = false;
	}
	private void productWiseRefresh()
	{
		cmbCategory.setSelectedItem("");
		cmbSubCategory.setSelectedItem("");
		cmbSupplier.setSelectedItem("");
		txtDealerPrice.setText("");
	}
	private void calcAmount()
	{
		double wastageQty, dealerPrice, amount;
		wastageQty = Double.parseDouble(txtWastageQty.getText().trim().isEmpty()?"0":txtWastageQty.getText().trim());
		dealerPrice = Double.parseDouble(txtDealerPrice.getText().trim().isEmpty()?"0":txtDealerPrice.getText().trim());
		amount = wastageQty*dealerPrice;
		txtAmount.setText(df.format(amount));
	}
	private boolean checkValidation()
	{
		if(!txtWastageNo.getText().trim().isEmpty())
		{
			if(!cmbProduct.txtSuggest.getText().trim().isEmpty())
			{
				if(cmbProductType.getSelectedIndex()!=0 && cmbProductType.getSelectedItem()!=null)
				{
					if(!txtWastageQty.getText().trim().isEmpty())
					{
						return true;
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please Enter Wastage Quantity!","Warning..",JOptionPane.WARNING_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please select a Type of Product!","Warning..",JOptionPane.WARNING_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please select a Product id or Name!","Warning..",JOptionPane.WARNING_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please Enter Wastage Number!","Warning..",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	private boolean checkConfirmation(String wastageCaption)
	{
		int a = JOptionPane.showConfirmDialog(null, wastageCaption,"Confirmation..",JOptionPane.YES_NO_OPTION);
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
			StringTokenizer token = new StringTokenizer(cmbProduct.txtSuggest.getText().trim(), "~");
			String productid = token.nextToken();
			String productName = token.nextToken();
			StringTokenizer token1 = new StringTokenizer(cmbCategory.getSelectedItem().toString(), "~");
			String catid = token1.nextToken();
			String catName = token1.nextToken();
			if(cmbSubCategory.getSelectedIndex()!=0 && cmbSubCategory.getSelectedItem()!=null)
			{
				StringTokenizer token2 = new StringTokenizer(cmbSubCategory.getSelectedItem().toString(), "~");
				subCatid = token2.nextToken();
				subCatName = token2.nextToken();
			}
			StringTokenizer token2 = new StringTokenizer(cmbSupplier.getSelectedItem().toString(), "~");
			String supid = token2.nextToken();
			String supName = token2.nextToken();
			String WastageDate = new SimpleDateFormat("yyyy-MM-dd").format(wastageDate.getDate());

			String sql = "insert into tbwastage (wastageNo,productid,productName,catid,catName,subCatid,subCatName,supplierid,supplierName,productType,"
					+ "wDate,wastageQty,dealerPrice,amount,remark,userName,userip,entryTime)"
					+ "values ('"+txtWastageNo.getText().trim()+"','"+productid+"','"+productName+"',"
					+ "'"+catid+"','"+catName+"','"+subCatid+"','"+subCatName+"',"
					+ "'"+supid+"','"+supName+"','"+cmbProductType.getSelectedItem().toString()+"',"
					+ "'"+WastageDate+"','"+txtWastageQty.getText().trim()+"','"+txtDealerPrice.getText().trim()+"',"
					+ "'"+txtAmount.getText().trim()+"','"+txtRemarks.getText().trim()+"','"+txtUserName.getText().trim()+"','',now())";
			Dbconnection.connection();
			Dbconnection.sta.executeUpdate(sql);
			Dbconnection.con.close();
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(from insertData Wastage)","Error..",JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	public void searchDataWastage(String wastage)
	{
		try
		{
			String sql = "select wastageNo,productid,productName,catid,catName,subCatid,subCatName,supplierid,"
					+ "supplierName,productType,wDate,wastageQty,dealerPrice,amount,remark,userName from tbwastage where wastageNo = '"+wastage+"'";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{
				txtWastageNo.setText(rs.getString("wastageNo"));
				cmbProduct.txtSuggest.setText(rs.getString("productid")+"~"+rs.getString("productName"));
				cmbProduct.cmbSuggest.setSelectedItem(rs.getString("productid")+"~"+rs.getString("productName"));
				cmbCategory.addItem(rs.getString("catid")+"~"+rs.getString("catName"));
				cmbCategory.setSelectedItem(rs.getString("catid")+"~"+rs.getString("catName"));
				String subCat = rs.getString("subCatid")+rs.getString("subCatName");
				if(!subCat.trim().isEmpty())
				{
					cmbSubCategory.addItem(rs.getString("subCatid")+"~"+rs.getString("subCatName"));
					cmbSubCategory.setSelectedItem(rs.getString("subCatid")+"~"+rs.getString("subCatName"));
				}
				cmbSupplier.addItem(rs.getString("supplierid")+"~"+rs.getString("supplierName"));
				cmbSupplier.setSelectedItem(rs.getString("supplierid")+"~"+rs.getString("supplierName"));
				cmbProductType.addItem(rs.getString("productType"));
				cmbProductType.setSelectedItem(rs.getString("productType"));
				wastageDate.setDate(rs.getDate("wDate"));
				txtWastageQty.setText(rs.getString("wastageQty"));
				txtDealerPrice.setText(rs.getString("dealerPrice"));
				txtAmount.setText(rs.getString("amount"));
				txtRemarks.setText(rs.getString("remark"));
				txtUserName.setText(rs.getString("userName"));
				btnIni(false);
				isSearch = true;
				isUpdate = true;
				isSearchEditable(false);
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(from searchDataWastage wastageOrBroken)","Error..",JOptionPane.ERROR_MESSAGE);
		}
	}
	public boolean delteData()
	{
		try
		{
			String sql = "delete from tbWastage where wastageNo = '"+txtWastageNo.getText().trim()+"'";
			Dbconnection.connection();
			Dbconnection.sta.executeUpdate(sql);
			Dbconnection.con.close();
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(from deleteData Wastage)","Error..",JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	public void tableDataLoad()
	{
		try
		{
			for(int a = model.getRowCount()-1; a>=0; a--)
			{
				model.removeRow(a);
			}
			String sql = "Select wastageNo, productName, wastageQty, wDate from tbwastage order by" +
					" CAST(substring(wastageNo,LOCATE('-',wastageNo)+1,LENGTH(wastageNo)-locate('-',wastageNo))as UNSIGNED)";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				model.addRow(new Object[]{rs.getString("wastageNo"),rs.getString("productName"),rs.getString("wastageQty"),rs.getString("wDate")});
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(from tableDataLoad wastage)","warning..",JOptionPane.WARNING_MESSAGE);
		}
	}
	public void cmbDataLoad()
	{
		try
		{
			cmbWastageMain.v.clear();
			cmbWastageMain.v.add("");
			String sql="select wastageNo from tbwastage order by " +
					"CAST(substring(wastageNo,LOCATE('-',wastageNo)+1,LENGTH(wastageNo)-locate" +
					"('-',wastageNo))as UNSIGNED)";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbWastageMain.v.add(rs.getString("wastageNo"));
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e+""+"(cmbdataload wastage)",
					"Warning",JOptionPane.WARNING_MESSAGE);
		}
	}
	private void btnAction()
	{
		cmbWastageMain.cmbSuggest.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!cmbWastageMain.txtSuggest.getText().trim().isEmpty())
				{
					String wastage = cmbWastageMain.txtSuggest.getText().trim();
					StringTokenizer token = new StringTokenizer(wastage, "~");
					searchDataWastage(token.nextToken());
				}
			}
		});
		cmbProduct.cmbSuggest.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!isSearch)
				{
					if(!cmbProduct.txtSuggest.getText().trim().isEmpty())
					{
						StringTokenizer token = new StringTokenizer(cmbProduct.txtSuggest.getText().trim(), "~");
						productWiseLoad(token.nextToken());
					}
					else
					{
						productWiseRefresh();
					}
				}
			}
		});
		table.addMouseListener(new MouseListener() 
		{
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent arg0) 
			{
				int row = table.getSelectedRow();
				searchDataWastage(table.getValueAt(row, 0).toString());
			}
		});
		table.addKeyListener(new KeyListener() 
		{
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) 
			{
				int row = table.getSelectedRow();
				searchDataWastage(table.getValueAt(row, 0).toString());
			}
			public void keyPressed(KeyEvent arg0) {}
		});
		txtWastageQty.addKeyListener(new KeyListener() 
		{
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) 
			{
				calcAmount();
			}
			public void keyPressed(KeyEvent arg0){}
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
							if(delteData())
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
		btnEdit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				btnIni(true);
				isUpdate = true;
				isSearchEditable(true);
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
						if(delteData())
						{
							JOptionPane.showMessageDialog(null, "Delete data Successfully!","Information..",JOptionPane.INFORMATION_MESSAGE);
							refreshWork();
						}
					}
				}
			}
		});
	}
	public void autoid()
	{
		try
		{
			String sql = "select ifnull(max(cast(subString(wastageNo,locate('-',wastageNo)+1,"
					+ "length(wastageNo)-locate('-',wastageNo))as UNSIGNED)),0)+1 id from tbwastage";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{
				String WNo = rs.getString("id").trim();
				txtWastageNo.setText("WastageNo-"+WNo);
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
			JOptionPane.showMessageDialog(null, e+""+"(Error from productLoad wastage)","Warning..",JOptionPane.WARNING_MESSAGE);
		}
	}
	private void productWiseLoad(String product)
	{
		try
		{
			String sql = "Select catid,catName,subCatid,subCatName,supplierid,supplierName,dealerPrice from "
					+ "tbProductinfo where productid = '"+product+"'";
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
				txtDealerPrice.setText(rs.getString("dealerPrice"));
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from productWiseLoad Wastage Or Broken","error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void cmp()
	{
		BorderLayout border = new BorderLayout();
		border.setVgap(0);
		setLayout(border);
		OpeningStockPanelWest();
		add(wastagePanelWest,BorderLayout.WEST);
		OpeningStockPanelcenter();
		add(wastagePanelcenter,BorderLayout.CENTER);
	}
	@SuppressWarnings("static-access")
	public void OpeningStockPanelWest()
	{
		wastagePanelWest.setBackground(bgColor);
		wastagePanelWest.setPreferredSize(new Dimension(560,1));
		TitledBorder WastageTitle = new TitledBorder("Wastage Or Broken");
		WastageTitle.setTitleFont(new Font("Agency FB",Font.BOLD+Font.PLAIN,22));
		WastageTitle.setTitleJustification(TitledBorder.CENTER);
		WastageTitle.setTitlePosition(WastageTitle.TOP);
		wastagePanelWest.setBorder(WastageTitle);
		wastagePanelWest.setLayout(new BorderLayout());
		wastagePanelWest.add(wastagePanelWestNorth,BorderLayout.NORTH);
		OpeningStockPanelWestNorth();
		wastagePanelWest.add(wastagePanelWestCenter,BorderLayout.CENTER);
		OpeningStockPanelWestCenter();
		wastagePanelWest.add(wastagePanelWestSouth,BorderLayout.SOUTH);
		OpeningStockPanelWestSouth();
	}
	public void OpeningStockPanelWestNorth ()
	{
		wastagePanelWestNorth.setBackground(bgColor);
		wastagePanelWestNorth.setPreferredSize(new Dimension(0,110));
		wastagePanelWestNorth.setLayout(new GridBagLayout());
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=0;
		cn.gridy=0;
		wastagePanelWestNorth.add(lblWastageMain,cn);
		lblWastageMain.setPreferredSize(new Dimension(78,30));
		lblWastageMain.setFont(new Font("Agency FB", Font.PLAIN+Font.BOLD, 17));
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=1;
		cn.gridy=0;
		wastagePanelWestNorth.add(cmbWastageMain.cmbSuggest,cn);
		cmbWastageMain.cmbSuggest.setPreferredSize(new Dimension(280,33));
		cmbWastageMain.cmbSuggest.setBackground(Color.white);
	}
	public void OpeningStockPanelWestCenter ()
	{
		wastagePanelWestCenter.setBackground(Color.white);
		wastagePanelWestCenter.setLayout(new GridBagLayout());
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=0;
		wastagePanelWestCenter.add(lblWastageNo,cn);
		lblWastageNo.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.gridx=1;
		cn.gridy=0;
		wastagePanelWestCenter.add(txtWastageNo,cn);//
		txtWastageNo.setPreferredSize(new Dimension(1,28));
		cn.gridx=0;
		cn.gridy=1;
		wastagePanelWestCenter.add(lblProduct,cn);
		lblProduct.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.gridx=1;
		cn.gridy=1;
		wastagePanelWestCenter.add(cmbProduct.cmbSuggest,cn);//
		cmbProduct.cmbSuggest.setBackground(Color.white);
		cmbProduct.cmbSuggest.setPreferredSize(new Dimension(1,28));
		cn.gridx=0;
		cn.gridy=2;
		wastagePanelWestCenter.add(lblCategory,cn);
		lblCategory.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.gridx=1;
		cn.gridy=2;
		wastagePanelWestCenter.add(cmbCategory,cn);//
		cmbCategory.setBackground(Color.white);
		cmbCategory.setPreferredSize(new Dimension(1,28));
		cn.gridx=0;
		cn.gridy=3;
		wastagePanelWestCenter.add(lblSubCategory,cn);
		lblSubCategory.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.gridx=1;
		cn.gridy=3;
		wastagePanelWestCenter.add(cmbSubCategory,cn);//
		cmbSubCategory.setBackground(Color.white);
		cmbSubCategory.setPreferredSize(new Dimension(1,28));
		cn.gridx=0;
		cn.gridy=4;
		wastagePanelWestCenter.add(lblSupplier,cn);
		lblSupplier.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.gridx=1;
		cn.gridy=4;
		wastagePanelWestCenter.add(cmbSupplier,cn);//
		cmbSupplier.setBackground(Color.white);
		cmbSupplier.setPreferredSize(new Dimension(1,28));
		cn.gridx=0;
		cn.gridy=5;
		wastagePanelWestCenter.add(lblProductType,cn);
		lblProductType.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.gridx=1;
		cn.gridy=5;
		wastagePanelWestCenter.add(cmbProductType,cn);//
		cmbProductType.setBackground(Color.white);
		cmbProductType.setPreferredSize(new Dimension(1,28));
		cn.gridx=0;
		cn.gridy=6;
		wastagePanelWestCenter.add(lblDate,cn);
		lblDate.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.gridx=1;
		cn.gridy=6;
		wastagePanelWestCenter.add(wastageDate,cn);//
		wastageDate.setDateFormatString("dd-MM-yyyy");
		wastageDate.setPreferredSize(new Dimension(1,28));
		wastageDate.setDate(new Date());
		wastageDate.setBackground(Color.white);
		cn.gridx=0;
		cn.gridy=7;
		wastagePanelWestCenter.add(lblWastageQty,cn);
		lblWastageQty.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.gridx=1;
		cn.gridy=7;
		wastagePanelWestCenter.add(txtWastageQty,cn);//
		txtWastageQty.setPreferredSize(new Dimension(1,28));
		cn.gridx=0;
		cn.gridy=8;
		wastagePanelWestCenter.add(lblDealerPrice,cn);
		lblDealerPrice.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.gridx=1;
		cn.gridy=8;
		wastagePanelWestCenter.add(txtDealerPrice,cn);//
		txtDealerPrice.setPreferredSize(new Dimension(1,28));
		cn.gridx=0;
		cn.gridy=9;
		wastagePanelWestCenter.add(lblAmount,cn);
		lblAmount.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.gridx=1;
		cn.gridy=9;
		wastagePanelWestCenter.add(txtAmount,cn);//
		txtAmount.setPreferredSize(new Dimension(1,28));
		cn.gridx=0;
		cn.gridy=10;
		wastagePanelWestCenter.add(lblRemarks,cn);//
		lblRemarks.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.gridx=1;
		cn.gridy=10;
		wastagePanelWestCenter.add(scrollRemarks,cn);//
		scrollRemarks.setPreferredSize(new Dimension(1,50));
		cn.gridx=0;
		cn.gridy=11;
		wastagePanelWestCenter.add(lblUserName,cn);//
		lblUserName.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.gridx=1;
		cn.gridy=11;
		wastagePanelWestCenter.add(txtUserName,cn);//
		txtUserName.setPreferredSize(new Dimension(1,28));
	}
	public void OpeningStockPanelWestSouth ()
	{
		wastagePanelWestSouth.setBackground(Color.white);
		wastagePanelWestSouth.setPreferredSize(new Dimension(0,100));
		wastagePanelWestSouth.setLayout(new GridBagLayout());
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 10, 5);
		cn.gridx=0;
		cn.gridy=0;
		wastagePanelWestSouth.add(btnAdd,cn);
		btnAdd.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 13));
		btnAdd.setBackground(btnColor);
		cn.insets=new Insets(5, 5, 10, 5);
		cn.gridx=1;
		cn.gridy=0;
		wastagePanelWestSouth.add(btnEdit,cn);
		btnEdit.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 13));
		btnEdit.setBackground(btnColor);
		cn.insets=new Insets(5, 5, 10, 5);
		cn.gridx=2;
		cn.gridy=0;
		wastagePanelWestSouth.add(btnRefresh,cn);
		btnRefresh.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 13));
		btnRefresh.setBackground(btnColor);
		cn.insets=new Insets(5, 5, 10, 5);
		cn.gridx=3;
		cn.gridy=0;
		wastagePanelWestSouth.add(btnDelete,cn);
		btnDelete.setFont(new Font("Arial", Font.PLAIN+Font.BOLD, 13));
		btnDelete.setBackground(btnColor);
	}
	@SuppressWarnings("static-access")
	public void OpeningStockPanelcenter()
	{
		wastagePanelcenter.setBackground(bgColor);
		wastagePanelcenter.setPreferredSize(new Dimension(560,1));
		TitledBorder wastageTableTitle = new TitledBorder("Existing Wastage or Broken");
		wastageTableTitle.setTitleFont(new Font("Agency FB",Font.BOLD+Font.PLAIN,22));
		wastageTableTitle.setTitleJustification(TitledBorder.CENTER);
		wastageTableTitle.setTitlePosition(wastageTableTitle.TOP);
		wastagePanelcenter.setBorder(wastageTableTitle);
		wastagePanelcenter.setLayout(new BorderLayout());
		scroll.setPreferredSize(new Dimension(550,730));
		table.getTableHeader().setReorderingAllowed(false);
		scroll.getViewport().setBackground(Color.white);
		table.setOpaque(false);
		scroll.setOpaque(false);
		table.setShowGrid(true);
		table.setRowHeight(table.getRowHeight()+2);
		wastagePanelcenter.add(scroll);
	}
	public void init()
	{
		setPreferredSize(new Dimension(1140,740));
		BorderLayout border=new BorderLayout();
		border.setHgap(25);
		setLayout(border);
	}
}


