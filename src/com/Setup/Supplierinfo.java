package com.Setup;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.ResultSet;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.Admin.Dbconnection;
import com.Admin.SessionBean;
import com.Admin.SuggestText;

public class Supplierinfo extends JPanel
{
	private static final long serialVersionUID = 1L;
	SessionBean sessionBean;
	JPanel supplierPanelwest=new JPanel();
	JPanel supplierPanelwestnorth=new JPanel();
	JPanel supplierPanelwestcenter=new JPanel();

	JPanel supplierPanelwesteast=new JPanel();
	JLabel lblUpload=new JLabel();
	JButton btnUpload=new JButton("Upload",new ImageIcon("images/Upload.png"));
	JPanel supplierPanelwestsouth=new JPanel();
	JPanel supplierPanelwestSouthNorth=new JPanel();
	JButton btnAdd=new JButton("Add",new ImageIcon("images/add.png"));
	JButton btnEdit=new JButton("Edit",new ImageIcon("images/edit.png"));
	JButton btnRefresh=new JButton("Refresh",new ImageIcon("images/refresh.png"));
	JButton btnDelete=new JButton("Delete",new ImageIcon("images/delete.png"));//

	JLabel lblSupplierID=new JLabel("Supplier ID");
	JLabel lblSupplierName=new JLabel("Supplier Name");
	JLabel lblMailAddress=new JLabel("Mail Address");
	JLabel lblPhoneNumber=new JLabel("Phone Number");
	JLabel lblAddress=new JLabel("Address");
	JLabel lblUserName=new JLabel("User Name");
	JLabel lblUserType = new JLabel("");
	JTextField txtSupplierID=new JTextField(20);
	JTextField txtSupplierName=new JTextField(20);
	JTextField txtMailAddress=new JTextField(20);
	JTextField txtPhoneNumber=new JTextField(20);
	JTextArea txtAreaAddress=new JTextArea(3,11);
	JTextField txtUserName=new JTextField(20);
	JScrollPane scrollAddress=new JScrollPane(txtAreaAddress,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	JLabel lblSupplier = new JLabel("Search By : ");
	SuggestText cmbSupplier = new SuggestText();

	JPanel supplierPanelcenter = new JPanel();
	String[] column={"Supplier ID","Supplier Name","Mobile Number"};
	Object[][] row={};
	DefaultTableModel model = new DefaultTableModel(row,column)
	{
		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row,int column)
		{
			return false;
		}
	};
	JTable table = new JTable(model);
	JScrollPane scroll = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	GridBagConstraints cn = new GridBagConstraints();

	JFileChooser fileChooser = null;
	File file = null;
	BufferedImage buffer = null;
	boolean upload = false;
	boolean isUpdate = false;
	boolean isDelete = false;
	BufferedImage image;
	ImageIcon uploadImage = new ImageIcon("images/uploadimg.png");

	public Supplierinfo(SessionBean bean)
	{
		init();
		cmp();
		btnAction();
		btnIni(true);
		defaultEditable(false);
		lblUpload.setIcon(uploadImage);
		this.sessionBean = bean;
		txtUserName.setText(sessionBean.getUserName());
		lblUserType.setText(sessionBean.getUserType());
	}
	private void btnIni(boolean b) 
	{
		btnAdd.setEnabled(b);
		btnEdit.setEnabled(!b);
		btnDelete.setEnabled(!b);
	}
	private void defaultEditable(boolean d)
	{
		txtSupplierID.setEditable(d);
		txtUserName.setEditable(d);
	}
	public void txtEditable(boolean e)
	{
		txtSupplierID.setEditable(e);
		txtSupplierName.setEditable(e);
		txtMailAddress.setEditable(e);
		txtPhoneNumber.setEditable(e);
		txtAreaAddress.setEditable(e);
		//txtUserName.setEditable(e);
		btnUpload.setEnabled(e);
	}
	private void txtClear()
	{
		txtSupplierName.setText("");
		txtMailAddress.setText("");
		txtPhoneNumber.setText("");
		txtAreaAddress.setText("");
		isUpdate = false;	
		upload = false;
		isDelete = false;
		txtEditable(true);
	}
	public void refreshButton()
	{
		autoidsup();
		txtClear();
		btnIni(true);
		tableDataLoadSupplier();
		upload = false;
		file = null;
		lblUpload.setIcon(uploadImage);
	}
	public void cmbRefresh()
	{
		cmbSupplier.txtSuggest.setText("");
		cmbDataLoadSupplier();
	}
	private boolean checkValidation()
	{
		if(!txtSupplierID.getText().trim().isEmpty())
		{
			if(!txtSupplierName.getText().trim().isEmpty())
			{
				if(!txtMailAddress.getText().trim().isEmpty())
				{
					if(!txtPhoneNumber.getText().trim().isEmpty())
					{
						if(!txtAreaAddress.getText().trim().isEmpty())
						{
							if(!txtUserName.getText().trim().isEmpty())
							{
								if(upload||isUpdate||isDelete)
								{
									return true;
								}
								else
								{
									JOptionPane.showMessageDialog(null,"Please Enter upload a picture!","warning...",
											JOptionPane.WARNING_MESSAGE);
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Please, Enter User name!","Sorry...",JOptionPane.WARNING_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Please, Enter Address!","Sorry...",JOptionPane.WARNING_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please, Enter phone number!","Sorry...",JOptionPane.WARNING_MESSAGE);
					}
				}
				else
				{

					JOptionPane.showMessageDialog(null, "Please, Enter mail Address!","Sorry...",JOptionPane.WARNING_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please, Enter Supplier Name!","Sorry...",JOptionPane.WARNING_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please, Enter a Supplier Id!","Sorry...",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	private boolean checkConfirmation(String caption)
	{
		int a = JOptionPane.showConfirmDialog(null, caption,"Confirmation..",JOptionPane.YES_NO_OPTION);
		if(a == JOptionPane.YES_OPTION)
		{
			return true;
		}
		return false;
	}
	private void SearchSupplierData(String sid)
	{
		try
		{
			String sql="select supplierid,supplierName,mailAddress,phoneNumber,address,userName,picture "
					+ "from tbsupplierinfo where supplierId='"+sid+"'";
			Dbconnection.connection();
			ResultSet rs=Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{
				txtSupplierID.setText(rs.getString("supplierId"));
				txtSupplierName.setText(rs.getString("supplierName"));
				txtMailAddress.setText(rs.getString("mailAddress"));
				txtPhoneNumber.setText(rs.getString("phoneNumber"));
				txtAreaAddress.setText(rs.getString("address"));
				txtUserName.setText(rs.getString("userName"));
				txtEditable(false);
				btnIni(false);
				if(!rs.getString("picture").toString().isEmpty())
				{

					String imagePath = "D:/ProjectImages/SupplierImages/"+"/"+rs.getString("picture");
					Image image = Toolkit.getDefaultToolkit().getImage(imagePath);
					Image resize = image.getScaledInstance(lblUpload.getWidth(), lblUpload.getHeight(), Image.SCALE_DEFAULT);
					lblUpload.setIcon(new ImageIcon(resize));
				}
				else
				{
					lblUpload.setIcon(uploadImage);
					upload = false;
				}
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"from searchData SupplierInfo","Error..",JOptionPane.ERROR_MESSAGE);
		}
	}
	private boolean insertData()
	{
		try
		{
			if(upload)
			{
				String imageDir = "D:/ProjectImages/SupplierImages";
				File file1 = new File(imageDir);
				if(!file1.isDirectory())
				{
					file1.mkdirs();
				}
				buffer = ImageIO.read(file);
				String imagePath = imageDir+"/"+txtSupplierID.getText()+".jpg";
				File fileWrite = new File(imagePath);
				if(fileWrite.exists())
				{
					fileWrite.delete();
				}
				ImageIO.write(buffer,"jpg",fileWrite);
			}
			String pictureName = upload?txtSupplierID.getText().trim()+".jpg":"";

			String sql="insert into tbsupplierinfo(supplierid,supplierName,mailAddress,phoneNumber,address,userName,userIp,entryTime,picture)"
					+ "values('"+txtSupplierID.getText().trim()+"','"+txtSupplierName.getText().trim()+"','"+txtMailAddress.getText().trim()+"',"
					+ "'"+txtPhoneNumber.getText().trim()+"','"+txtAreaAddress.getText().trim()+"','"+txtUserName.getText().trim()+"','',now(),'"+pictureName+"')";
			Dbconnection.connection();
			Dbconnection.sta.executeUpdate(sql);
			Dbconnection.con.close();
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(From insertData SupplierInfo)","Error..",JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	private boolean deleteData()
	{
		try
		{
			if(isDelete)
			{
				String imagePath = "D:/ProjectImages/SupplierImages"+"/"+txtSupplierID.getText().trim()+".jgp";
				File file = new File(imagePath);
				if(file.exists())
				{
					file.delete();
				}
			}
			String sql="delete from tbsupplierinfo where supplierid='"+txtSupplierID.getText().trim()+"'";
			Dbconnection.connection();
			Dbconnection.sta.executeUpdate(sql);
			Dbconnection.con.close();
			return true;
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp+"","Error..",JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	public void tableDataLoadSupplier()
	{
		for(int a=model.getRowCount()-1; a>=0; a--)
		{
			model.removeRow(a);
		}
		try
		{
			String sql="select supplierid,supplierName,phoneNumber from tbsupplierinfo order by " +
					"CAST(substring(supplierid,LOCATE('-',supplierid)+1,LENGTH(supplierid)-locate('-',supplierid))" +
					"as UNSIGNED)";
			Dbconnection.connection();
			ResultSet rs=Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				model.addRow(new Object[]{rs.getString("supplierid"),rs.getString("supplierName"),rs.getString("phoneNumber")});
			}
			Dbconnection.con.close();
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp,"Error..",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void cmbDataLoadSupplier()
	{
		try
		{
			cmbSupplier.v.clear();
			cmbSupplier.v.add("");
			String sql="select supplierid,supplierName from tbSupplierInfo order by "
					+ "CAST(substring(supplierid,LOCATE('-',supplierid)+1,LENGTH(supplierid)-locate('-',supplierid))as UNSIGNED)";
			Dbconnection.connection();
			ResultSet rs=Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				String supId=rs.getString("supplierid");
				String supName=rs.getString("supplierName");
				String supIdName=supId+"~"+supName;
				cmbSupplier.v.add(supIdName);
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(From cmbDataLoadSupplier)","Warning..",JOptionPane.WARNING_MESSAGE);
		}
	}
	private void uploadAction()
	{
		fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("image","jpg","jpeg","png","gif");
		fileChooser.setFileFilter(filter);
		fileChooser.showOpenDialog(this);
		fileChooser.setDialogTitle("Please select an image");
		file = fileChooser.getSelectedFile();
		if(file!=null)
		{
			Image img = Toolkit.getDefaultToolkit().getImage(file.getPath());
			Image resize = img.getScaledInstance(lblUpload.getWidth(), lblUpload.getHeight(), Image.SCALE_DEFAULT);
			ImageIcon icon = new ImageIcon(resize);
			lblUpload.setIcon(icon);
			upload = true;
		}
	}
	private void btnAction() 
	{
		btnUpload.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{	
				uploadAction();
			}
		});
		cmbSupplier.cmbSuggest.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(!cmbSupplier.txtSuggest.getText().trim().isEmpty())
				{
					String idSup=cmbSupplier.txtSuggest.getText();
					StringTokenizer tokenSup=new StringTokenizer(idSup,"~");
					SearchSupplierData(tokenSup.nextToken());
				}
				else
				{
					refreshButton();
				}
			}
		});
		txtPhoneNumber.addKeyListener(new KeyListener() 
		{
			public void keyTyped(KeyEvent evt)
			{
				char ch = evt.getKeyChar();
				if(!((Character.isDigit(ch) || ch=='+' || ch=='-' || ch==KeyEvent.VK_BACK_SPACE || ch==KeyEvent.VK_DELETE)))
				{
					getToolkit().beep();
					evt.consume();
				}
			}
			public void keyReleased(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}
		});
		table.addMouseListener(new MouseListener() 
		{
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) 
			{
				int row = table.getSelectedRow();
				SearchSupplierData(table.getValueAt(row, 0).toString());
			}
		});
		table.addKeyListener(new KeyListener() 
		{

			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) 
			{
				int row = table.getSelectedRow();
				SearchSupplierData(table.getValueAt(row, 0).toString());
			}
			public void keyPressed(KeyEvent e) {}
		});
		btnAdd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
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
									tableDataLoadSupplier();
									refreshButton();
									cmbRefresh();
									lblUpload.setIcon(uploadImage);
								}
							}
						}
						else
						{
							if(insertData())
							{
								JOptionPane.showMessageDialog(null, "All information save successfully!","Information..",JOptionPane.INFORMATION_MESSAGE);
								tableDataLoadSupplier();
								refreshButton();
								cmbRefresh();
								lblUpload.setIcon(uploadImage);
							}
						}
					}
				}
			}
		});
		btnEdit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				btnIni(true);
				isUpdate = true;
				txtEditable(true);
			}
		});
		btnRefresh.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				btnIni(true);
				refreshButton();
				cmbRefresh();
				lblUpload.setIcon(uploadImage);
			}
		});
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				isDelete = true;
				if(checkValidation())
				{
					if(checkConfirmation("Sure To Delete?"))
					{
						if(deleteData())
						{
							JOptionPane.showMessageDialog(null, "Your information is Deleted!","Delete..",JOptionPane.INFORMATION_MESSAGE);
							refreshButton();
							cmbRefresh();
							lblUpload.setIcon(uploadImage);
						}
					}
				}

			}
		});
	}
	public void autoidsup()
	{
		try
		{
			String sql="select ifnull(max(cast(SubString(supplierId,locate('-',supplierId)+1,length"
					+ "(supplierId)-locate('-',supplierId))as UNSIGNED)),0)+1 as Id from tbSupplierInfo";
			Dbconnection.connection();
			ResultSet rs=Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{
				String supId=rs.getString("Id");
				txtSupplierID.setText("sup-"+supId);
			}
			Dbconnection.con.close();
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp,"Error..",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void cmp()
	{
		BorderLayout border = new BorderLayout();
		border.setVgap(0);
		setLayout(border);
		supplierPanelwest();
		add(supplierPanelwest,BorderLayout.WEST);
		supplierPanelcenter();
		add(supplierPanelcenter,BorderLayout.CENTER);
	}
	@SuppressWarnings("static-access")
	public void supplierPanelwest()
	{
		supplierPanelwest.setBackground(new Color(2, 191, 185));
		supplierPanelwest.setPreferredSize(new Dimension(620,1));
		TitledBorder supplierTitle=new TitledBorder("Supplier Info");
		supplierTitle.setTitleFont(new Font("Agency FB",Font.PLAIN+Font.BOLD,22));
		supplierTitle.setTitleJustification(TitledBorder.CENTER);
		supplierTitle.setTitlePosition(supplierTitle.TOP);
		supplierPanelwest.setBorder(supplierTitle);
		supplierPanelwest.setLayout(new BorderLayout());
		supplierPanelwest.add(supplierPanelwestnorth,BorderLayout.NORTH);
		supplierPanelwestnorth();
		supplierPanelwest.add(supplierPanelwestcenter,BorderLayout.CENTER);
		supplierPanelwestcenter();
		supplierPanelwest.add(supplierPanelwesteast,BorderLayout.EAST);
		supplierPanelwesteast();
		supplierPanelwest.add(supplierPanelwestsouth,BorderLayout.SOUTH);
		supplierPanelwestsouth();
	}
	public void supplierPanelwestnorth()
	{
		supplierPanelwestnorth.setBackground(new Color(2, 191, 185));
		supplierPanelwestnorth.setPreferredSize(new Dimension(0,120));
		//supplierPanelwestnorth.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		supplierPanelwestnorth.setLayout(new GridBagLayout());
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=0;
		cn.gridy=0;
		supplierPanelwestnorth.add(lblSupplier,cn);
		lblSupplier.setPreferredSize(new Dimension(78,30));
		lblSupplier.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 15));
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=1;
		cn.gridy=0;
		supplierPanelwestnorth.add(cmbSupplier.cmbSuggest,cn);
		cmbSupplier.cmbSuggest.setPreferredSize(new Dimension(280,33));
		cmbSupplier.cmbSuggest.setBackground(Color.white);
	}
	public void supplierPanelwestcenter()
	{
		supplierPanelwestcenter.setBackground(Color.white);
		supplierPanelwestcenter.setLayout(new GridBagLayout());
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=0;
		supplierPanelwestcenter.add(lblSupplierID,cn);
		lblSupplierID.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=0;
		supplierPanelwestcenter.add(txtSupplierID,cn);//
		txtSupplierID.setPreferredSize(new Dimension(1,28));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=1;
		supplierPanelwestcenter.add(lblSupplierName,cn);
		lblSupplierName.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=1;
		supplierPanelwestcenter.add(txtSupplierName,cn);//
		txtSupplierName.setPreferredSize(new Dimension(1,28));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=2;
		supplierPanelwestcenter.add(lblMailAddress,cn);
		lblMailAddress.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=2;
		supplierPanelwestcenter.add(txtMailAddress,cn);//
		txtMailAddress.setPreferredSize(new Dimension(1,28));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=3;
		supplierPanelwestcenter.add(lblPhoneNumber,cn);
		lblPhoneNumber.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=3;
		supplierPanelwestcenter.add(txtPhoneNumber,cn);//
		txtPhoneNumber.setPreferredSize(new Dimension(1,28));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=4;
		supplierPanelwestcenter.add(lblAddress,cn);
		lblAddress.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=4;
		scrollAddress.setPreferredSize(new Dimension(0,60));
		scrollAddress.requestFocusInWindow();
		supplierPanelwestcenter.add(scrollAddress,cn);//
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=5;
		supplierPanelwestcenter.add(lblUserName,cn);
		lblUserName.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=5;
		supplierPanelwestcenter.add(txtUserName,cn);//
		txtUserName.setPreferredSize(new Dimension(1,28));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=2;
		cn.gridy=5;
		supplierPanelwestcenter.add(lblUserType,cn);//
	}
	public void supplierPanelwesteast()
	{
		supplierPanelwesteast.setBackground(Color.white);
		supplierPanelwesteast.setLayout(new GridBagLayout());
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 0, 5, 20);
		cn.gridx=0;
		cn.gridy=0;
		lblUpload.setPreferredSize(new Dimension(130,160));
		lblUpload.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		supplierPanelwesteast.add(lblUpload,cn);
		cn.insets=new Insets(10, 10, 30, 30);
		cn.gridx=0;
		cn.gridy=1;
		supplierPanelwesteast.add(btnUpload,cn);
		btnUpload.setBackground(new Color(0,134,139));
		btnUpload.setFont(new Font("Arial Narrow",Font.PLAIN+Font.BOLD,15));
	}
	public void supplierPanelwestsouth()
	{
		supplierPanelwestsouth.setPreferredSize(new Dimension(0,200));
		supplierPanelwestsouth.setBackground(Color.white);
		supplierPanelwestsouth.setLayout(new BorderLayout());
		supplierPanelwestsouth.add(supplierPanelwestSouthNorth,BorderLayout.NORTH);
		supplierPanelwestSouthNorth.setBackground(Color.white);
		supplierPanelwestSouthNorth.setPreferredSize(new Dimension(0,80));
		supplierPanelwestSouthNorth.setLayout(new GridBagLayout());
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 30,5);
		cn.gridx=0;
		cn.gridy=0;
		supplierPanelwestSouthNorth.add(btnAdd,cn);
		btnAdd.setForeground(Color.black);
		btnAdd.setBackground(new Color(0,134,139));
		btnAdd.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		cn.insets=new Insets(5, 5, 30,5);
		cn.gridx=1;
		cn.gridy=0;
		supplierPanelwestSouthNorth.add(btnEdit,cn);
		btnEdit.setForeground(Color.black);
		btnEdit.setBackground(new Color(0,134,139));
		btnEdit.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		cn.insets=new Insets(5, 5, 30,5);
		cn.gridx=2;
		cn.gridy=0;
		supplierPanelwestSouthNorth.add(btnRefresh,cn);
		btnRefresh.setForeground(Color.black);
		btnRefresh.setBackground(new Color(0,134,139));
		btnRefresh.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		cn.insets=new Insets(5, 5, 30,5);
		cn.gridx=3;
		cn.gridy=0;
		supplierPanelwestSouthNorth.add(btnDelete,cn);
		btnDelete.setForeground(Color.black);
		btnDelete.setBackground(new Color(0,134,139));
		btnDelete.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
	}
	@SuppressWarnings("static-access")
	public void supplierPanelcenter()
	{
		supplierPanelcenter.setBackground(new Color(2, 191, 185));
		supplierPanelcenter.setPreferredSize(new Dimension(500,1));
		TitledBorder supplierTableTitle=new TitledBorder("Existing Supplier");
		supplierTableTitle.setTitleFont(new Font("Agency FB",Font.PLAIN+Font.BOLD,22));
		supplierTableTitle.setTitleJustification(TitledBorder.CENTER);
		supplierTableTitle.setTitlePosition(supplierTableTitle.TOP);
		supplierPanelcenter.setBorder(supplierTableTitle);
		scroll.getViewport().setBackground(Color.white);
		scroll.setPreferredSize(new Dimension(485,695));
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		supplierPanelcenter.add(scroll);
		table.setOpaque(false);
		scroll.setOpaque(false);
		table.setShowGrid(true);
		table.setRowHeight(table.getRowHeight()+2);
	}
	public void init()
	{
		setPreferredSize(new Dimension(1140,740));
		BorderLayout border=new BorderLayout();
		border.setHgap(25);
		setLayout(border);
	}
}

