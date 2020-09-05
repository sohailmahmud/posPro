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

public class Category extends JPanel 
{
	private static final long serialVersionUID = 1L;
	SessionBean sessionBean = new SessionBean();
	GridBagConstraints cn = new GridBagConstraints();
	JPanel panelwest = new JPanel();
	JPanel panelcenter = new JPanel();
	JPanel panelwestNorth = new JPanel();
	JPanel panelwestcenter = new JPanel();
	JPanel panelwestsouth = new JPanel();
	JPanel panelwestCenterCenter = new JPanel();
	JPanel panelwestCenterSouth = new JPanel();

	JLabel lblcat = new JLabel("Search By : ");
	SuggestText cmbCat = new SuggestText();

	JLabel lblCategoryID = new JLabel("Category ID");
	JLabel lblCategoryName = new JLabel("Category Name");
	JLabel lblUserName = new JLabel("User Name");
	JLabel lblUserType = new JLabel("");
	JTextField txtCategoryID = new JTextField(20);
	JTextField txtCategoryName = new JTextField(20);
	JTextField txtUserName = new JTextField(20);
	JButton btnAdd = new JButton("Add",new ImageIcon("images/add.png"));
	JButton btnEdit = new JButton("Edit",new ImageIcon("images/edit.png"));
	JButton btnRefresh = new JButton("Refresh",new ImageIcon("images/refresh.png"));
	JButton btnDelete = new JButton("Delete",new ImageIcon("images/delete.png"));//
	JPanel panelcenterNorth = new JPanel();
	JPanel panelcentercenter = new JPanel();
	JPanel panelcentersouth = new JPanel();
	JPanel panelcenterCenterCenter = new JPanel();
	JPanel panelcenterCenterSouth = new JPanel();
	JLabel lblCmbSubCat = new JLabel("Search By : ");
	SuggestText cmbSubCat = new SuggestText();
	JLabel lblCmbCategoryid = new JLabel("Category");
	SuggestText cmbcategoryid = new SuggestText();
	JLabel lblSubCatID = new JLabel("Sub Category ID");
	JLabel lblSubCatName = new JLabel("Sub Category Name");
	JLabel lblSubUserName = new JLabel("User Name");
	JLabel lblUserTypeSub = new JLabel("");
	JTextField txtSubCatID = new JTextField(20);
	JTextField txtSubCatName = new JTextField(20);
	JTextField txtSubUserName = new JTextField(20);

	JButton btnAddSub = new JButton("Add",new ImageIcon("images/add.png"));
	JButton btnEditSub = new JButton("Edit",new ImageIcon("images/edit.png"));
	JButton btnRefreshSub = new JButton("Refresh",new ImageIcon("images/refresh.png"));
	JButton btnDeleteSub = new JButton("Delete",new ImageIcon("images/delete.png"));//

	String[] column={"Category ID","Category Name","User Name"};
	Object[][]row={};
	DefaultTableModel modelCat=new DefaultTableModel(row,column)
	{
		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row, int column)
		{
			return false;
		}
	};
	JTable tableCat = new JTable(modelCat);
	JScrollPane scroll = new JScrollPane(tableCat,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	String[] columnSub = {"Catagory ID","SubCatagory ID","SubCatagory Name","User Name"};
	Object[][]rowSub = {};
	DefaultTableModel modelSubCat = new DefaultTableModel(rowSub,columnSub)
	{
		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row,int column)
		{
			return false;
		}
	};
	JTable tableSubCat = new JTable(modelSubCat);
	JScrollPane scrollSubCat = new JScrollPane(tableSubCat,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	boolean isUpdate = false;
	boolean isUpdateSub = false;
	BufferedImage image;

	public Category(SessionBean bean)
	{
		this.sessionBean = bean;
		init();
		cmp();
		btnAction();
		btnini(true);
		defaultEditable(false);
		btniniSub(true);
		txtUserName.setText(sessionBean.getUserName());
		txtSubUserName.setText(sessionBean.getUserName());
		lblUserType.setText(sessionBean.getUserType());
		lblUserTypeSub.setText(sessionBean.getUserType());
	}
	private void btnini(boolean b)
	{
		btnAdd.setEnabled(b);
		btnEdit.setEnabled(!b);
		btnDelete.setEnabled(!b);
	}
	private void defaultEditable(boolean d)
	{
		txtCategoryID.setEditable(d);
		txtUserName.setEditable(d);
		txtSubCatID.setEditable(d);
		txtSubUserName.setEditable(d);
	}
	public void btniniSub(boolean s)
	{
		btnAddSub.setEnabled(s);
		btnEditSub.setEnabled(!s);
		btnDeleteSub.setEnabled(!s);
	}
	public void txtEditable(boolean e)
	{
		txtCategoryID.setEditable(e);
		txtCategoryName.setEditable(e);
		txtUserName.setEditable(e);
	}
	public void txtEditableSub(boolean es)
	{
		cmbcategoryid.txtSuggest.setEnabled(es);
		txtSubCatID.setEditable(es);
		txtSubCatName.setEditable(es);
		txtSubUserName.setEditable(es);
	}
	private void txtClear()
	{
		txtCategoryID.setText("");
		txtCategoryName.setText("");
		txtUserName.setText("");
		isUpdate = false;
		txtEditable(true);
	}
	public void txtClearSub()
	{
		txtSubCatID.setText("");
		txtSubCatName.setText("");
		txtSubUserName.setText("");
		txtEditableSub(true);
	}
	public void refreshButton()
	{
		txtClear();
		autoid();
		btnini(true);
		tableDataLoadCategory();
		txtUserName.setText(sessionBean.getUserName());
	}
	public void refreshButtonSub()
	{
		txtClearSub();
		autoidsub();
		btniniSub(true);
		tableDataLoadSubCategory();
		txtSubUserName.setText(sessionBean.getUserName());
	}
	public void cmbRefresh()
	{
		cmbCat.txtSuggest.setText("");
		cmbcategoryid.v.clear();
		cmbDataLoadCategory();
	}
	public void cmbRefreshSub()
	{
		cmbcategoryid.txtSuggest.setText("");
		cmbDataLoadCategory();
		cmbSubCat.v.clear();
		cmbSubCat.txtSuggest.setText("");
		cmbDataLoadSubCategory();
	}
	public boolean checkValidation()
	{
		if(!txtCategoryID.getText().trim().isEmpty())
		{
			if(!txtCategoryName.getText().trim().isEmpty())
			{
				if(!txtUserName.getText().trim().isEmpty())
				{
					return true;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please, Enter User Name!","Sorry...",JOptionPane.WARNING_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please, Enter Category Name!","Sorry...",JOptionPane.WARNING_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please, Enter Category id!","Sorry...",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	public boolean checkValidationSub()
	{
		if(!cmbcategoryid.txtSuggest.getText().trim().isEmpty())
		{
			if(!txtSubCatID.getText().trim().isEmpty())
			{
				if(!txtSubCatName.getText().trim().isEmpty())
				{
					if(!txtSubUserName.getText().trim().isEmpty())
					{
						return true;
					}	
					else
					{
						JOptionPane.showMessageDialog(null, "Please provide Sub UserName!","Sorry...",JOptionPane.WARNING_MESSAGE);
					}
				}	
				else
				{
					JOptionPane.showMessageDialog(null, "Please provide SubCategory Name!","Sorry...",JOptionPane.WARNING_MESSAGE);
				}
			}	
			else
			{
				JOptionPane.showMessageDialog(null, "Please Enter a SubCategory id!","Sorry...",JOptionPane.WARNING_MESSAGE);
			}
		}	
		else
		{
			JOptionPane.showMessageDialog(null, "Please Select Category id & Name!","Sorry...",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}

	public boolean checkConfirmation(String caption)
	{
		int a = JOptionPane.showConfirmDialog(null, caption, "Confirmation...",JOptionPane.YES_NO_OPTION);
		if(a == JOptionPane.YES_OPTION)
		{
			return true;
		}
		return false;
	}
	public boolean checkConfirmationSub(String subCaption)
	{
		int a = JOptionPane.showConfirmDialog(null, subCaption,"Confirmation..",JOptionPane.YES_NO_OPTION);
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
			String sql = "insert into tbcategoryinfo(catid,catName,userName,userip,entryTime)"
					+ "values('"+txtCategoryID.getText().trim()+"','"+txtCategoryName.getText().trim()+"','"+txtUserName.getText().trim()+"','',now())";
			Dbconnection.connection();
			Dbconnection.sta.executeUpdate(sql);
			Dbconnection.con.close();
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"","Error..",JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	private boolean insertDataSub()
	{
		try
		{
			String id = cmbcategoryid.txtSuggest.getText().trim();
			StringTokenizer token = new StringTokenizer(id,"~");
			String catid = token.nextToken();
			String catName = token.nextToken();
			String sql = "insert into tbsubcategoryinfo" +
					"(catid,catName,subcatid,subcatName,subUserName,userip,entryTime)" +
					"values('"+catid+"','"+catName+"'," +
					"'"+txtSubCatID.getText().trim()+"'," +
					"'"+txtSubCatName.getText().trim()+"'," +
					"'"+txtSubUserName.getText().trim()+"','',now())";
			Dbconnection.connection();
			Dbconnection.sta.executeUpdate(sql);
			Dbconnection.con.close();
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"","Sorry...",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	private boolean deleteData()
	{
		try
		{
			Dbconnection.connection();
			String sql = "delete from tbCategoryInfo where catid = '"+txtCategoryID.getText()+"'";
			Dbconnection.sta.executeUpdate(sql);
			Dbconnection.con.close();
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"","Warning..",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	private boolean deleteDataSub()
	{
		try
		{
			Dbconnection.connection();
			String sql = "delete from tbsubcategoryinfo where subcatid = '"+txtSubCatID.getText().trim()+"'";
			Dbconnection.sta.executeUpdate(sql);
			Dbconnection.con.close();
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"","Error...",JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	private void searchCategoryData(String id)
	{
		try
		{
			String sql = "select catid,catName,userName from tbCategoryInfo where catid='"+id+"'";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{
				String catid = rs.getString("catid");
				String catName = rs.getString("catName");
				String userName = rs.getString("userName");
				txtCategoryID.setText(catid);
				txtCategoryName.setText(catName);
				txtUserName.setText(userName);
				txtEditable(false);
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(From SearchCategoryData)","Error...",JOptionPane.ERROR_MESSAGE);
		}
		btnini(false);
	}
	public void searchSubCategoryData(String subid)
	{
		try
		{
			String sql = "select catid,catName,subcatid,subcatName,subUserName from tbsubcategoryinfo where subcatid = '"+subid+"'";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{
				cmbcategoryid.cmbSuggest.setSelectedItem(rs.getString("catid")+"~"+rs.getString("catName"));
				txtSubCatID.setText(rs.getString("subcatid"));
				txtSubCatName.setText(rs.getString("subcatName"));
				txtSubUserName.setText(rs.getString("subUserName"));
				isUpdateSub = true;
				txtEditableSub(false);
				btniniSub(false);
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(From SearchSubCategoryData)","Error...",JOptionPane.ERROR_MESSAGE);
		}
		btniniSub(false);
	}
	public void tableDataLoadCategory()
	{
		try
		{
			for(int a = modelCat.getRowCount()-1; a>=0; a--)
			{
				modelCat.removeRow(a);
			}
			String sql = "select catid,catName,userName from tbcategoryinfo order by "
					+ "CAST(substring(catid,LOCATE('-',catid)+1,LENGTH(catid)-locate('-',catid))as UNSIGNED)";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				modelCat.addRow(new Object[]{rs.getString("catid"),rs.getString("catName"),rs.getString("userName")});
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"","Error...",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void tableDataLoadSubCategory()
	{

		int i;
		for(i=modelSubCat.getRowCount()-1; i>=0; i--)
		{
			modelSubCat.removeRow(i);
		}
		try
		{
			String sql = "select autoidsub,catid,subcatid,subcatName,subUserName " +
					"from tbsubcategoryinfo  order by CAST(substring(subcatid,LOCATE('-',subcatid)+1," +
					"LENGTH(subcatid)-locate('-',subcatid))as UNSIGNED)";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				modelSubCat.addRow(new Object[]{rs.getString("catid"),
						rs.getString("subcatid"),
						rs.getString("subcatName"),
						rs.getString("subUserName")
				});
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"","Error..",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void cmbDataLoadCategory()
	{
		try
		{
			cmbCat.v.clear();
			cmbcategoryid.v.clear();
			cmbCat.v.add("");
			cmbcategoryid.v.add("");
			String sql = "select catid,catName from tbCategoryInfo order by CAST(substring(catid,"
					+ "LOCATE('-',catid)+1,LENGTH(catid)-locate('-',catid))as UNSIGNED)";

			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				String id = rs.getString("catid");
				String name = rs.getString("catName");
				String idName = id+"~"+name;
				cmbCat.v.add(idName);
				cmbcategoryid.v.add(idName);
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(cmbDataLoadCategory)","Sorry...",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void cmbDataLoadSubCategory()
	{
		try
		{
			cmbSubCat.v.clear();
			cmbSubCat.v.add("");
			String sql = "select subcatid,subcatName from tbsubCategoryInfo order by "
					+ "CAST(substring(subcatid,LOCATE('-',subcatid)+1,LENGTH(subcatid)-locate('-',subcatid))as UNSIGNED)";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				String subCatid = rs.getString("subcatid");
				String subCatName = rs.getString("subCatName");
				String idNameSub = subCatid+"~"+subCatName;
				cmbSubCat.v.add(idNameSub);
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(cmbDataLoadSubCategory)","Sorry...",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void btnAction()
	{
		cmbCat.cmbSuggest.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!cmbCat.txtSuggest.getText().trim().isEmpty())
				{
					String idName = cmbCat.txtSuggest.getText();
					StringTokenizer token = new StringTokenizer(idName, "~");
					searchCategoryData(token.nextToken());
				}
			}
		});
		cmbSubCat.cmbSuggest.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{	
				if(!cmbSubCat.txtSuggest.getText().trim().isEmpty())
				{
					String s1 = cmbSubCat.txtSuggest.getText();
					StringTokenizer token = new StringTokenizer(s1,"~");
					searchSubCategoryData(token.nextToken());
				}
			}
		});
		tableCat.addMouseListener(new MouseListener() 
		{
			public void mouseReleased(MouseEvent arg0){}
			public void mousePressed(MouseEvent arg0){}
			public void mouseExited(MouseEvent arg0){}
			public void mouseEntered(MouseEvent arg0){}
			public void mouseClicked(MouseEvent arg0)
			{
				int row = tableCat.getSelectedRow(); 
				searchCategoryData(modelCat.getValueAt(row, 0).toString());
			}
		});
		tableCat.addKeyListener(new KeyListener() 
		{
			public void keyTyped(KeyEvent arg0){}
			public void keyReleased(KeyEvent arg0)
			{
				int row = tableCat.getSelectedRow(); 
				searchCategoryData(modelCat.getValueAt(row, 0).toString());
			}
			public void keyPressed(KeyEvent arg0){}
		});
		tableSubCat.addMouseListener(new MouseListener() 
		{
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent arg0) 
			{
				int rowsub = tableSubCat.getSelectedRow();
				searchSubCategoryData(tableSubCat.getValueAt(rowsub, 1).toString());
			}
		});
		tableSubCat.addKeyListener(new KeyListener() 
		{
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) 
			{
				int rowsub = tableSubCat.getSelectedRow();
				searchSubCategoryData(tableSubCat.getValueAt(rowsub, 1).toString());
			}
			public void keyPressed(KeyEvent arg0) {}
		});
		btnAdd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{	
				if(checkValidation())
				{
					if(checkConfirmation(isUpdate?"Sure To Update?":"Sure To Save?"))
					{
						if(isUpdate)
						{
							if(deleteData())
							{
								if(insertData())
								{
									JOptionPane.showMessageDialog(null, "All Information Updated Successfully!",
											"Information...",JOptionPane.INFORMATION_MESSAGE);
									tableDataLoadCategory();
									refreshButton();
									cmbRefresh();
								}
							}
						}
						else
						{
							if(insertData())
							{
								JOptionPane.showMessageDialog(null, "All Information Save Successfully!",
										"Information...",JOptionPane.INFORMATION_MESSAGE);
								tableDataLoadCategory();
								refreshButton();
								cmbRefresh();
							}
						}
					}
				}
			}
		});
		btnAddSub.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(checkValidationSub())
				{
					if(checkConfirmationSub(isUpdateSub?"You want to Update?":"You want to Save?"))
					{
						if(isUpdateSub)
						{
							if(deleteDataSub())
							{
								if(insertDataSub())
								{
									JOptionPane.showMessageDialog(null, "All Information Updated Successfully!",
											"Information...",JOptionPane.INFORMATION_MESSAGE);
									tableDataLoadSubCategory();
									refreshButtonSub();
									cmbRefreshSub();
								}
							}
						}
						else
						{
							if(insertDataSub())
							{
								JOptionPane.showMessageDialog(null, "All Information Save Successfully!",
										"Information...",JOptionPane.INFORMATION_MESSAGE);
								tableDataLoadSubCategory();
								refreshButtonSub();
								cmbRefreshSub();
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
				isUpdate = true;
				txtEditable(true);
			}
		});
		btnEditSub.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				btniniSub(true);
				isUpdateSub = true;
				txtEditableSub(true);
			}
		});
		btnRefresh.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				btnini(true);
				refreshButton();
				cmbRefresh();
			}
		});
		btnRefreshSub.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				btniniSub(true);
				refreshButtonSub();
				cmbRefreshSub();
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
							JOptionPane.showMessageDialog(null, "Delete Data Successfully","Information...",
									JOptionPane.INFORMATION_MESSAGE);
							refreshButton();
							cmbRefresh();
						}
					}
				}
			}
		});
		btnDeleteSub.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(checkValidationSub())
				{
					if(checkConfirmationSub("Sure To Delete?"))
					{
						if(deleteDataSub())
						{
							JOptionPane.showMessageDialog(null, "Delete Data Successfully","Information...",JOptionPane.INFORMATION_MESSAGE);
							refreshButtonSub();
							cmbRefreshSub();
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
			String sql = "select ifnull(max(cast(subString(catid,locate('-',catid)+1,length(catid)-locate('-', "+
					" catid))as UNSIGNED)),0)+1 id from tbcategoryinfo";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{
				String id = rs.getString("id");
				txtCategoryID.setText("Cat-"+id);
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public void autoidsub()
	{
		try
		{
			String sql = "select ifnull(max(cast(subString(subcatid,locate('-',subcatid)+1,"
					+ "length(subcatid)-locate('-',subcatid))as UNSIGNED)),0)+1 id from tbsubcategoryinfo";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{
				String id = rs.getString("id");
				txtSubCatID.setText("SubCat-"+id);
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
		add(panelwest,BorderLayout.WEST);
		panelwest();
		add(panelcenter,BorderLayout.CENTER);
		panelcenter();
	}
	@SuppressWarnings("static-access")
	public void panelwest()
	{
		panelwest.setBackground(new Color(2, 191, 185));
		panelwest.setPreferredSize(new Dimension(560,1));
		TitledBorder categoryTitle=new TitledBorder("Category Info");
		categoryTitle.setTitleFont(new Font("Agency FB",Font.BOLD,22));
		categoryTitle.setTitleJustification(TitledBorder.CENTER);
		categoryTitle.setTitlePosition(categoryTitle.TOP);
		panelwest.setBorder(categoryTitle);
		panelwest.setLayout(new BorderLayout());
		panelwest.add(panelwestNorth,BorderLayout.NORTH);
		panelwestNorth();
		panelwest.add(panelwestcenter,BorderLayout.CENTER);
		panelwestcenter();
		panelwest.add(panelwestsouth,BorderLayout.SOUTH);
		panelwestsouth();


	}
	public void panelwestNorth()
	{
		panelwestNorth.setBackground(new Color(2, 191, 185));
		panelwestNorth.setPreferredSize(new Dimension(0,120));
		//panelwestNorth.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		panelwestNorth.setLayout(new GridBagLayout());
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=0;
		cn.gridy=0;
		panelwestNorth.add(lblcat,cn);
		lblcat.setPreferredSize(new Dimension(78,30));
		lblcat.setFont(new Font("Arial Narrow", Font.PLAIN+Font.BOLD, 15));
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=1;
		cn.gridy=0;
		panelwestNorth.add(cmbCat.cmbSuggest,cn);
		cmbCat.cmbSuggest.setPreferredSize(new Dimension(280,33));
		cmbCat.cmbSuggest.setBackground(Color.white);
	}
	public void panelwestcenter()
	{
		panelwestcenter.setLayout(new BorderLayout());
		panelwestcenter.add(panelwestCenterCenter,BorderLayout.CENTER);
		panelwestCenterCenter.setBackground(Color.white);
		panelwestcenter.add(panelwestCenterSouth,BorderLayout.SOUTH);
		panelwestCenterSouth.setBackground(Color.white);
		panelwestCenterSouth.setPreferredSize(new Dimension(0,60));
		panelwestCenterCenter.setLayout(new GridBagLayout());
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=0;
		cn.gridy=0;
		panelwestCenterCenter.add(lblCategoryID,cn);
		lblCategoryID.setFont(new Font("Cambria",Font.PLAIN+Font.BOLD,14));
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=1;
		cn.gridy=0;
		panelwestCenterCenter.add(txtCategoryID,cn);//
		txtCategoryID.setPreferredSize(new Dimension(1,28));
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=0;
		cn.gridy=1;
		panelwestCenterCenter.add(lblCategoryName,cn);
		lblCategoryName.setFont(new Font("Cambria",Font.PLAIN+Font.BOLD,14));
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=1;
		cn.gridy=1;
		panelwestCenterCenter.add(txtCategoryName,cn);//
		txtCategoryName.setPreferredSize(new Dimension(1,28));
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=0;
		cn.gridy=2;
		panelwestCenterCenter.add(lblUserName,cn);
		lblUserName.setFont(new Font("Cambria",Font.PLAIN+Font.BOLD,14));
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=1;
		cn.gridy=2;
		panelwestCenterCenter.add(txtUserName,cn);//
		txtUserName.setPreferredSize(new Dimension(1,28));
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=2;
		cn.gridy=2;
		panelwestCenterCenter.add(lblUserType,cn);//
		panelwestCenterSouth.setLayout(new GridBagLayout());
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5,5);
		cn.gridx=0;
		cn.gridy=0;
		panelwestCenterSouth.add(btnAdd,cn);
		btnAdd.setForeground(Color.black);
		btnAdd.setBackground(new Color(0,134,139));
		btnAdd.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=1;
		cn.gridy=0;
		panelwestCenterSouth.add(btnEdit,cn);
		btnEdit.setForeground(Color.black);
		btnEdit.setBackground(new Color(0,134,139));
		btnEdit.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=2;
		cn.gridy=0;
		panelwestCenterSouth.add(btnRefresh,cn);
		btnRefresh.setForeground(Color.black);
		btnRefresh.setBackground(new Color(0,134,139));
		btnRefresh.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=3;
		cn.gridy=0;
		panelwestCenterSouth.add(btnDelete,cn);
		btnDelete.setForeground(Color.black);
		btnDelete.setBackground(new Color(0,134,139));
		btnDelete.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
	}
	public void panelwestsouth()
	{
		panelwestsouth.setPreferredSize(new Dimension(0,330));
		panelwestsouth.setBackground(Color.white);
		tableCat.getTableHeader().setReorderingAllowed(false);
		scroll.setPreferredSize(new Dimension(515,315));
		scroll.getViewport().setBackground(Color.white);
		tableCat.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCat.setOpaque(false);
		scroll.setOpaque(false);
		tableCat.setShowGrid(true);
		tableCat.setRowHeight(tableCat.getRowHeight()+2);
		panelwestsouth.add(scroll);
	}
	@SuppressWarnings("static-access")
	public void panelcenter()
	{
		panelcenter.setBackground(new Color(2, 191, 185));
		panelcenter.setPreferredSize(new Dimension(560,1));
		TitledBorder subcatagoryTitle=new TitledBorder("Sub Category Info");
		subcatagoryTitle.setTitleFont(new Font("Agency FB",Font.BOLD,22));
		subcatagoryTitle.setTitleJustification(TitledBorder.CENTER);
		subcatagoryTitle.setTitlePosition(subcatagoryTitle.TOP);
		panelcenter.setBorder(subcatagoryTitle);
		panelcenter.setLayout(new BorderLayout());
		panelcenter.add(panelcenterNorth,BorderLayout.NORTH);
		panelcenterNorth();
		panelcenter.add(panelcentercenter,BorderLayout.CENTER);
		panelcentercenter();
		panelcenter.add(panelcentersouth,BorderLayout.SOUTH);
		panelcentersouth();
	}
	public void panelcenterNorth()
	{
		panelcenterNorth.setBackground(new Color(2, 191, 185));
		panelcenterNorth.setPreferredSize(new Dimension(0,120));
		//panelcenterNorth.setBorder(BorderFactory.createRaisedBevelBorder());
		panelcenterNorth.setLayout(new GridBagLayout());

		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=0;
		cn.gridy=0;
		panelcenterNorth.add(lblCmbSubCat,cn);
		lblCmbSubCat.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 15));
		lblCmbSubCat.setPreferredSize(new Dimension(78,30));
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=1;
		cn.gridy=0;
		panelcenterNorth.add(cmbSubCat.cmbSuggest,cn);
		cmbSubCat.cmbSuggest.setPreferredSize(new Dimension(280,33));
		cmbSubCat.cmbSuggest.setBackground(Color.white);

	}
	public void panelcentercenter()
	{
		panelcentercenter.setLayout(new BorderLayout());
		panelcentercenter.add(panelcenterCenterCenter,BorderLayout.CENTER);
		panelcenterCenterCenter.setBackground(Color.white);
		panelcentercenter.add(panelcenterCenterSouth,BorderLayout.SOUTH);
		panelcenterCenterSouth.setBackground(Color.white);
		panelcenterCenterSouth.setPreferredSize(new Dimension(0,60));
		panelcenterCenterCenter.setLayout(new GridBagLayout());
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5,5,5);
		cn.gridx=0;
		cn.gridy=0;
		panelcenterCenterCenter.add(lblCmbCategoryid,cn);
		lblCmbCategoryid.setFont(new Font("Cambria",Font.PLAIN+Font.BOLD,14));
		cn.insets=new Insets(5, 5,5,5);
		cn.gridx=1;
		cn.gridy=0;
		panelcenterCenterCenter.add(cmbcategoryid.cmbSuggest,cn);//
		cmbcategoryid.cmbSuggest.setPreferredSize(new Dimension(1,28));
		cmbcategoryid.cmbSuggest.setBackground(Color.white);
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5,5,5);
		cn.gridx=0;
		cn.gridy=1;
		panelcenterCenterCenter.add(lblSubCatID,cn);
		lblSubCatID.setFont(new Font("Cambria",Font.PLAIN+Font.BOLD,14));
		cn.insets=new Insets(5, 5,5,5);
		cn.gridx=1;
		cn.gridy=1;
		panelcenterCenterCenter.add(txtSubCatID,cn);//
		txtSubCatID.setPreferredSize(new Dimension(1,28));
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5,5,5);
		cn.gridx=0;
		cn.gridy=2;
		panelcenterCenterCenter.add(lblSubCatName,cn);
		lblSubCatName.setFont(new Font("Cambria",Font.PLAIN+Font.BOLD,14));
		cn.insets=new Insets(5, 5,5,5);
		cn.gridx=1;
		cn.gridy=2;
		panelcenterCenterCenter.add(txtSubCatName,cn);//
		txtSubCatName.setPreferredSize(new Dimension(1,28));
		cn.insets=new Insets(5, 5,5,5);
		cn.gridx=0;
		cn.gridy=3;
		panelcenterCenterCenter.add(lblSubUserName,cn);
		lblSubUserName.setFont(new Font("Cambria",Font.PLAIN+Font.BOLD,14));
		cn.insets=new Insets(5, 5,5,5);
		cn.gridx=1;
		cn.gridy=3;
		panelcenterCenterCenter.add(txtSubUserName,cn);//
		txtSubUserName.setPreferredSize(new Dimension(1,28));
		cn.insets=new Insets(5, 5,5,5);
		cn.gridx=2;
		cn.gridy=3;
		panelcenterCenterCenter.add(lblUserTypeSub,cn);
		
		panelcenterCenterSouth.setLayout(new GridBagLayout());
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5,5);
		cn.gridx=0;
		cn.gridy=0;
		panelcenterCenterSouth.add(btnAddSub,cn);
		btnAddSub.setForeground(Color.black);
		btnAddSub.setBackground(new Color(0,134,139));
		btnAddSub.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		cn.insets=new Insets(5, 5, 5,5);
		cn.gridx=1;
		cn.gridy=0;
		panelcenterCenterSouth.add(btnEditSub,cn);
		btnEditSub.setForeground(Color.black);
		btnEditSub.setBackground(new Color(0,134,139));
		btnEditSub.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		cn.insets=new Insets(5, 5, 5,5);
		cn.gridx=2;
		cn.gridy=0;
		panelcenterCenterSouth.add(btnRefreshSub,cn);
		btnRefreshSub.setForeground(Color.black);
		btnRefreshSub.setBackground(new Color(0,134,139));
		btnRefreshSub.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		cn.insets=new Insets(5, 5, 5,5);
		cn.gridx=3;
		cn.gridy=0;
		panelcenterCenterSouth.add(btnDeleteSub,cn);
		btnDeleteSub.setForeground(Color.black);
		btnDeleteSub.setBackground(new Color(0,134,139));
		btnDeleteSub.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
	}
	public void panelcentersouth()
	{
		panelcentersouth.setBackground(Color.white);
		panelcentersouth.setPreferredSize(new Dimension(0,330));
		tableSubCat.getTableHeader().setReorderingAllowed(false);
		scrollSubCat.setPreferredSize(new Dimension(535,315));
		scrollSubCat.getViewport().setBackground(Color.white);
		tableSubCat.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSubCat.setOpaque(false);
		scrollSubCat.setOpaque(false);
		tableSubCat.setShowGrid(true);
		tableSubCat.setRowHeight(tableSubCat.getRowHeight()+2);
		panelcentersouth.add(scrollSubCat);
	}
	public void init()
	{
		setPreferredSize(new Dimension(1140,740));
		BorderLayout border=new BorderLayout();
		border.setHgap(25);
		setLayout(border);
	}
}
