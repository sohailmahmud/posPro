package com.Security;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
import com.toedter.calendar.JDateChooser;
public class NewUser extends JPanel
{
	private static final long serialVersionUID = 1L;
	SessionBean sessionBean;
	GridBagConstraints cn = new GridBagConstraints();
	JPanel NewUserPanelWest = new JPanel();
	JPanel NewUserPanelwestnorth = new JPanel();
	JLabel lblNewUser = new JLabel("Search By : ");
	SuggestText cmbNewUser = new SuggestText();
	JPanel NewUserPanelwestcenter = new JPanel();
	JPanel NewUserPanelwestEast = new JPanel();
	JLabel lblUpload = new JLabel();
	JButton btnUpload = new JButton("Upload",new ImageIcon("images/Upload.png"));
	JPanel NewUserPanelwestSouth = new JPanel();
	JButton btnAdd = new JButton("Add",new ImageIcon("images/add.png"));
	JButton btnEdit = new JButton("Edit",new ImageIcon("images/edit.png"));
	JButton btnRefresh = new JButton("Refresh",new ImageIcon("images/refresh.png"));
	JButton btnDelete = new JButton("Delete",new ImageIcon("images/delete.png"));//
	JPanel NewUserPanelCenter = new JPanel();
	JPanel NewUserPanelCenterNorth = new JPanel();
	JPanel NewUserPanelCenterCenter = new JPanel();
	JLabel lblUserId = new JLabel("User ID");
	JLabel lblname = new JLabel("Name");
	JLabel lblUserName = new JLabel("User Name");
	JLabel lblUserTyepe = new JLabel("User Type");
	JLabel lblDesignation = new JLabel("Designation");
	JLabel lblPassWord = new JLabel("Password");
	JLabel lblActivity = new JLabel("Activity");
	JLabel lblNationalID = new JLabel("National ID");
	JLabel lblDateOfJoin = new JLabel("Date of Join");
	JLabel lblMobile = new JLabel("Mobile Number");
	JLabel lblAddress = new JLabel("Address");
	JLabel lblEmail = new JLabel("Email-Address");
	JLabel lblReferenceBy = new JLabel("Reference By");
	JTextField txtUserName = new JTextField(20);
	JTextField txtname = new JTextField(20);
	String ara[] = {"","Admin","Super Admin","Manager","Executive","General"};
	JComboBox<String> cmbUserType = new JComboBox<>(ara);
	String ara2[] = {"","Male","Female"};
	JComboBox<String> cmbgender = new JComboBox<String>(ara2);
	JTextField txtUserId = new JTextField(20);
	JTextField txtDesignation = new JTextField(20);
	JPasswordField txtPassword = new JPasswordField(20);
	String ara3[] = {"","Yes","No"};
	JComboBox<String> cmbActivity = new JComboBox<String>(ara3);
	JDateChooser dofj = new JDateChooser();
	JTextField txtNationalID = new JTextField(20);
	JTextArea txtAreaAddress = new JTextArea(5,1);
	JTextField txtemail = new JTextField(20);
	JTextField txtMobile = new JTextField(20);
	JComboBox<String> cmbreferenceBy = new JComboBox<String>();
	GridBagConstraints c = new GridBagConstraints();
	JScrollPane scrolladdress = new JScrollPane(txtAreaAddress,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	String[] column={"User ID","Name","Activity","Email"};
	Object[][] row={};
	DefaultTableModel tModel=new DefaultTableModel(row,column)
	{
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int column)
		{
			return false;
		}
	};
	JTable table=new JTable(tModel);
	JScrollPane scrollTable=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	Font font=new Font("Baskerville O",Font.BOLD,20);
	JButton btnPrinter=new JButton("Print",new ImageIcon("images/print.png"));
	JFileChooser fileChooser = null;
	File file = null;
	BufferedImage buffer = null;
	boolean upload = false;
	boolean isUpdate = false;
	boolean isDelete = false;
	boolean showMessage = false;
	boolean showEdit = false;
	BufferedImage image;
	ImageIcon uploadImage = new ImageIcon("images/uploadimg.png");

	public NewUser(SessionBean bean)
	{
		init();
		cmp();
		btnAction();
		btnini(true);
		lblUpload.setIcon(uploadImage);
		this.sessionBean = bean;
		//txtUserName.setText(sessionBean.getUserName());
	}
	private void btnini(boolean b) 
	{
		btnAdd.setEnabled(b);
		btnEdit.setEnabled(!b);
		btnDelete.setEnabled(!b);	
	}
	public void txtEdit(boolean e)
	{
		txtname.setEditable(e);
		cmbUserType.setEnabled(e);
		txtDesignation.setEditable(e);
		txtPassword.setEditable(e);
		dofj.setEnabled(e);
		cmbActivity.setEnabled(e);
		txtemail.setEditable(e);
		txtNationalID.setEditable(e);
		txtAreaAddress.setEditable(e);
		txtMobile.setEditable(e);
		btnUpload.setEnabled(e);
	}
	private void txtClear()
	{
		cmbNewUser.txtSuggest.setText("");
		txtname.setText("");
		txtUserName.setText("");
		txtDesignation.setText("");
		txtPassword.setText("");
		dofj.setDate(new java.util.Date());
		cmbActivity.setSelectedItem("");
		txtemail.setText("");
		txtNationalID.setText("");
		txtAreaAddress.setText("");
		txtMobile.setText("");
		lblUpload.setIcon(new ImageIcon(""));
		autoidnu();
		tableDataLoadNewUser();
		otherRefresh();
	}
	private void otherRefresh()
	{
		btnini(true);
		isUpdate = false;
		isDelete = false;
		txtEdit(true);
		upload = false;
		showMessage = false;
		showEdit = false;
		lblUpload.setIcon(uploadImage);
	}
	public void refreshButton()
	{
		autoidnu();
		txtClear();
		tableDataLoadNewUser();
		cmbDataLoadNewUser();
		otherRefresh();
		lblUpload.setIcon(uploadImage);
	}
	@SuppressWarnings("deprecation")
	private boolean checkValidation()
	{
		int m = txtMobile.getText().trim().length();
		int n = txtNationalID.getText().trim().length();

		if(!txtUserId.getText().trim().isEmpty())
		{
			if(!txtname.getText().trim().isEmpty())
			{
				if(!txtUserName.getText().trim().isEmpty())
				{
					if(!txtDesignation.getText().trim().isEmpty())
					{
						if(!txtPassword.getText().trim().isEmpty())
						{
							if(cmbActivity.getSelectedIndex()!=0 && cmbActivity.getSelectedItem()!=null)
							{
								if(!txtemail.getText().trim().isEmpty())
								{
									if(!txtNationalID.getText().trim().isEmpty())
									{
										if(!txtAreaAddress.getText().trim().isEmpty())
										{
											if(!txtMobile.getText().trim().isEmpty())
											{
												if(upload||isUpdate||isDelete)
													{
														if(checkPassword())
														{
															if(n == 13)
															{
																if(m == 11)
																{
																	return true;
																}
																else
																{
																	JOptionPane.showMessageDialog(null, "Your Mobile Number is Not Valid!","Warning..",JOptionPane.WARNING_MESSAGE);
																}
															}
															else
															{
																JOptionPane.showMessageDialog(null, "Your National Id is not Valid!","Warning...",JOptionPane.WARNING_MESSAGE);
															}
														}
													}
													else
													{
														JOptionPane.showMessageDialog(null, "Please Enter upload a picture!","warning..",JOptionPane.WARNING_MESSAGE);
													}
											}
											else
											{
												JOptionPane.showMessageDialog(null, "Please Enter Mobile Number!","warning..",JOptionPane.WARNING_MESSAGE);
											}
										}
										else
										{
											JOptionPane.showMessageDialog(null, "Please Enter Address!","warning..",JOptionPane.WARNING_MESSAGE);
										}
									}
									else
									{
										JOptionPane.showMessageDialog(null, "Please Enter National id!","warning..",JOptionPane.WARNING_MESSAGE);
									}
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Please Enter Emai id!","warning..",JOptionPane.WARNING_MESSAGE);
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Please Enter Activation!","warning..",JOptionPane.WARNING_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Please Enter Password!","warning..",JOptionPane.WARNING_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please Enter Designation!","warning..",JOptionPane.WARNING_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Enter UserName!","warning..",JOptionPane.WARNING_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please Enter Name!","warning..",JOptionPane.WARNING_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please Enter userid!","warning..",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	private boolean checkConfirmation(String caption)
	{
		int a = JOptionPane.showConfirmDialog(null, caption,"Confirmation...",JOptionPane.YES_NO_OPTION);
		if(a==JOptionPane.YES_OPTION)
		{
			return true;
		}
		return false;
	}
	private void searchNewUserData(String user)
	{
		try
		{
			String sql = "select userid,name,userName,userType,designation,password,doj,activition," +
					"email,nid,address,mobile,picture from tbnewuser where userid = '"+user+"'";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{	
				txtUserId.setText(rs.getString("userid"));
				txtname.setText(rs.getString("name"));
				txtUserName.setText(rs.getString("userName"));
				cmbUserType.setSelectedItem(rs.getString("userType"));
				txtDesignation.setText(rs.getString("designation"));
				txtPassword.setText(rs.getString("password"));
				dofj.setDate(rs.getDate("doj"));
				cmbActivity.setSelectedItem(rs.getString("activition"));
				txtemail.setText(rs.getString("email"));
				txtNationalID.setText(rs.getString("nid"));
				txtAreaAddress.setText(rs.getString("address"));
				txtMobile.setText(rs.getString("mobile"));

				if(!rs.getString("picture").toString().isEmpty())
				{
				
					String imagePath = "D:/ProjectImages/NewUserImages/"+rs.getString("picture");
					Image image = Toolkit.getDefaultToolkit().getImage(imagePath);
					Image resize = image.getScaledInstance(lblUpload.getWidth(), lblUpload.getHeight(), Image.SCALE_DEFAULT);
					lblUpload.setIcon(new ImageIcon(resize));
				}
				else
				{
					lblUpload.setIcon(uploadImage);
				}
				upload = true;
				txtEdit(false);
				btnini(false);
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(from searchNewUserData)","Error..",JOptionPane.ERROR_MESSAGE);
		}
	}
	private boolean insertData()
	{
		try
		{
			String imageDir = "D:/ProjectImages/NewUserImages";
			File file1 = new File(imageDir);
			if(!file1.isDirectory())
			{
				file1.mkdirs();
			}
			buffer = ImageIO.read(file);
			String imagepath = imageDir+"/"+txtUserId.getText().trim()+".jpg";
			File fileWrite = new File(imagepath);
			if(fileWrite.exists())
			{
				fileWrite.delete();
			}
			ImageIO.write(buffer, "jpg", fileWrite);
			
			String joinDate = new SimpleDateFormat("yyyy-MM-dd").format(dofj.getDate());

			@SuppressWarnings("deprecation")
			String sql = "insert into tbnewuser (userid,name,userName,userType,designation,password,doj,activition,email,nid,address,mobile,userip,entryTime,picture)"
					+ "values('"+txtUserId.getText().trim()+"','"+txtname.getText().trim()+"','"+txtUserName.getText().trim()+"',"
					+ "'"+cmbUserType.getSelectedItem().toString()+"','"+txtDesignation.getText().trim()+"','"+txtPassword.getText().trim()+"',"
					+ "'"+joinDate+"','"+cmbActivity.getSelectedItem().toString()+"','"+txtemail.getText().trim()+"',"
					+ "'"+txtNationalID.getText().trim()+"','"+txtAreaAddress.getText().trim()+"',"
					+ "'"+txtMobile.getText().trim()+"','',now(),'"+txtUserId.getText()+".jpg')";
			Dbconnection.connection();
			Dbconnection.sta.executeUpdate(sql);
			Dbconnection.con.close();
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(from insertData NewUser)","Warning...",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	private boolean deleteData()
	{
		try
		{
			String sql="delete from tbnewuser where userid = '"+txtUserId.getText().trim()+"'";
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
	@SuppressWarnings("deprecation")
	public boolean deleteConfirmation()
	{
		if(!txtPassword.getText().trim().isEmpty())
		{
			if(!isDelete)
			{
				txtPassword.setText("");
				JOptionPane.showMessageDialog(null,"If you want to delete this user information?" +
						" Please insert this user password","**Information**",JOptionPane.INFORMATION_MESSAGE);
				txtPassword.setEditable(true);
				isDelete = true;
				return false;
			}
		}
		return true;
	}
	public void tableDataLoadNewUser()
	{
		for(int a=tModel.getRowCount()-1; a>=0; a--)
		{
			tModel.removeRow(a);
		}
		try
		{
			String sql="select userid,name,activition,email from tbnewuser order by " +
					"CAST(substring(userid,LOCATE('-',userid)+1,LENGTH(userid)-locate('-',userid))as UNSIGNED)";
			Dbconnection.connection();
			ResultSet rs=Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				tModel.addRow(new Object[]{rs.getString("userid"),rs.getString("name"),rs.getString("activition"),rs.getString("email")});
			}
			Dbconnection.con.close();
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, exp+""+"(from tableDataLoad NewUser)","Warning..",JOptionPane.WARNING_MESSAGE);
		}
	}
	public void cmbDataLoadNewUser()
	{
		try
		{
			cmbNewUser.v.clear();
			cmbNewUser.v.add("");
			String sql="select userid,Name from tbnewuser order by "
					+ "CAST(substring(userid,LOCATE('-',userid)+1,LENGTH(userid)-locate('-',userid))as UNSIGNED)";
			Dbconnection.connection();
			ResultSet rs=Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				String userid=rs.getString("userid");
				String userName=rs.getString("Name");
				String useridName=userid+" ~ "+userName;
				cmbNewUser.v.add(useridName);
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(From cmbDataLoadNewUser)","Error..",JOptionPane.ERROR_MESSAGE);
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
		else 
		{
			lblUpload.setIcon(uploadImage);
		}
	}
	public boolean emailValidation()
	{
		return false;
	}
	public boolean checkUserName()
	{
		boolean match = true;
		try
		{
			String sql = "Select userName from tbnewuser";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				boolean eq = rs.getString("userName").equals(txtUserName.getText().trim());
				if(eq)
				{
					match = false;
				}
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e+""+"(chackUserName NewUser)","**Warning**",
					JOptionPane.WARNING_MESSAGE);
		}
		return match;
	}
	@SuppressWarnings("deprecation")
	public boolean checkPassword()
	{
		String password = txtPassword.getText().trim().toString();
		@SuppressWarnings("unused")
		int digit=0;
		int special=0;
		int upCount=0;
		int loCount=0;
		String locase,upcase,space;
		if(password.length()>=8&&password.length()<=16)
		{
			for(int i=0; i<password.length(); i++)
			{
				char c = password.charAt(i);
				if(Character.isLowerCase(c))
				{
					loCount++;
				}
				else if(Character.isUpperCase(c))
				{
					upCount++;
				}
				else if(c>=33 && c<=46 || c==64)
				{
					special++;
				}
			}
			if(loCount>=1 && upCount>=1 && special>=1)
			{
				return true;
			}
			else
			{
				if(loCount<1)
				{
					locase ="Lowercase Letter";
				}
				else
				{
					locase =" ";
				}
				if(upCount<1)
				{
					upcase ="Uppercase Letter";
				}
				else{
					upcase =" ";
				}
				if(special<1)
				{
					space ="Special Charecter";
				}
				else
				{
					space =" ";
				}
				JOptionPane.showMessageDialog(null,"Your password have not"+space+locase+upcase,"**Error**",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(password.length()<8)
		{
			JOptionPane.showMessageDialog(null,"Your password is Small.please insert password in 8-16 charecter","**Error**",
					JOptionPane.ERROR_MESSAGE);
		}
		else if(password.length()>16)
		{
			JOptionPane.showMessageDialog(null,"Your password is big.please insert password in 8-16 charecter","**Error**",
					JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Your password is invalid","**Error**",
					JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	@SuppressWarnings("deprecation")
	public boolean checkOldPassword()
	{
		try
		{
			String s1 = txtUserId.getText().trim().toString();
			StringTokenizer token = new StringTokenizer(s1, "~");
			String s2 = token.nextToken();
			String sql = "Select password from tbnewuser where userid = '"+s2+"'";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{
				String oldPassword = rs.getString("password");
				String oldPass = txtPassword.getText().trim();
				boolean confirm = oldPassword.equals(oldPass);
				if(confirm)
				{
					return true;
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Sorry! Your password in not correct.\n You can not delete this user information","**Warning**",
							JOptionPane.WARNING_MESSAGE);
				}
				Dbconnection.con.close();
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e+""+"chackOldpassword() NewUser",
					"**Warning**",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	private void btnAction() 
	{
		txtNationalID.addKeyListener(new KeyAdapter() 
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
		});
		txtMobile.addKeyListener(new KeyAdapter() 
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
		});
		txtPassword.addMouseListener(new MouseListener()
		{
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0){}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent arg0) 
			{
				if(!showMessage)
				{
					JOptionPane.showMessageDialog(null,"Please insert password\n 8 or more characters," +
							" but not more than 16 characters \n one or more uppercase characters \n " +
							"one or more lowercase characters \n one or more digits \n one or more special characters " +
							"(like $, @, or !)");
				}
				if(showEdit)
				{
					JOptionPane.showMessageDialog(null,"If you want to change password. please click change password button!","**Warning**",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnUpload.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				uploadAction();
			}
		});
		cmbNewUser.cmbSuggest.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{

				if(!cmbNewUser.txtSuggest.getText().trim().isEmpty())
				{
					String userid = cmbNewUser.txtSuggest.getText().trim();
					StringTokenizer token = new StringTokenizer(userid," ~ ");
					searchNewUserData(token.nextToken());
				}
				else
				{
					txtClear();
				}
			}
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
				searchNewUserData(table.getValueAt(row, 0).toString());
			}
		});
		table.addKeyListener(new KeyListener() 
		{

			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) 
			{
				int row = table.getSelectedRow();
				searchNewUserData(table.getValueAt(row, 0).toString());
			}
			public void keyPressed(KeyEvent e) {}
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
									JOptionPane.showMessageDialog(null, "All Information Updated Successfully!","Information...",JOptionPane.INFORMATION_MESSAGE);
									refreshButton();
								}

							}
						}
						else
						{
							if(checkUserName())
							{
								if(insertData())
								{
									JOptionPane.showMessageDialog(null, "All Information Save Successfully!","Information...",JOptionPane.INFORMATION_MESSAGE);
									refreshButton();
									
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null,"Sorry! :( \n Your userName is used by another user. please insert another new user name!","**Warning**",
										JOptionPane.WARNING_MESSAGE);
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
				btnini(true);
				isUpdate = true;
				txtEdit(true);
				upload = true;
				showEdit = true;
				txtPassword.setEditable(false);
			}
		});
		btnRefresh.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				refreshButton();
				
			}
		});
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(deleteConfirmation())
				{
					if(checkValidation())
					{
						if(checkOldPassword())
						{
							if(checkConfirmation("Sure To Delete?"))
							{
								if(deleteData())
								{
									JOptionPane.showMessageDialog(null, "Success! :) \n Your information is Deleted!","Delete..",JOptionPane.INFORMATION_MESSAGE);
									refreshButton();
									
								}
							}
						}
					}
				}
			}
		});
	}
	public void autoidnu()
	{
		try{ 
			String sql="select ifnull(max(cast(SubString(userId,locate('-',userId)+1,"
					+ "length(userId)-locate('-',userId))as UNSIGNED)),0)+1 as userId from tbnewuser";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{
				String userId = rs.getString("userId");
				txtUserId.setText("user-"+userId);
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(from autoid NewUser)","Error..",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void cmp()
	{
		BorderLayout border = new BorderLayout();
		border.setVgap(0);
		setLayout(border);
		NewUserPanelWest();
		add(NewUserPanelWest,BorderLayout.WEST);
		NewUserPanelCenter();
		add(NewUserPanelCenter,BorderLayout.CENTER);
	}
	@SuppressWarnings("static-access")
	public void NewUserPanelWest()
	{
		NewUserPanelWest.setBackground(new Color(2, 191, 185));
		NewUserPanelWest.setPreferredSize(new Dimension(600,1));
		TitledBorder newUserTitle=new TitledBorder("New User");
		newUserTitle.setTitleFont(new Font("Agency FB",Font.BOLD,22));
		newUserTitle.setTitleJustification(TitledBorder.CENTER);
		newUserTitle.setTitlePosition(newUserTitle.TOP);
		NewUserPanelWest.setBorder(newUserTitle);
		NewUserPanelWest.setLayout(new BorderLayout());
		NewUserPanelWest.add(NewUserPanelwestnorth,BorderLayout.NORTH);
		NewUserPanelwestnorth();
		NewUserPanelWest.add(NewUserPanelwestcenter,BorderLayout.CENTER);
		NewUserPanelwestcenter();
		NewUserPanelWest.add(NewUserPanelwestEast,BorderLayout.EAST);
		NewUserPanelwestEast();
		NewUserPanelWest.add(NewUserPanelwestSouth,BorderLayout.SOUTH);
		NewUserPanelwestSouth();
	}
	public void NewUserPanelwestnorth()
	{
		NewUserPanelwestnorth.setBackground(new Color(2, 191, 185));
		NewUserPanelwestnorth.setPreferredSize(new Dimension(0,70));
		NewUserPanelwestnorth.setLayout(new GridBagLayout());

		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=0;
		cn.gridy=0;
		NewUserPanelwestnorth.add(lblNewUser,cn);
		lblNewUser.setFont(new Font("Agency FB", Font.PLAIN+Font.BOLD, 17));
		lblNewUser.setPreferredSize(new Dimension(78,30));
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=1;
		cn.gridy=0;
		NewUserPanelwestnorth.add(cmbNewUser.cmbSuggest,cn);
		cmbNewUser.cmbSuggest.setPreferredSize(new Dimension(280,33));
		cmbNewUser.cmbSuggest.setBackground(Color.white);

	}
	public void NewUserPanelwestcenter()
	{
		NewUserPanelwestcenter.setBackground(Color.white);
		GridBagLayout grid=new GridBagLayout();
		NewUserPanelwestcenter.setLayout(grid);
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=0;
		NewUserPanelwestcenter.add(lblUserId,cn);
		lblUserId.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=0;
		NewUserPanelwestcenter.add(txtUserId,cn);//
		txtUserId.setPreferredSize(new Dimension(1,25));
		txtUserId.setEditable(false);
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=1;
		NewUserPanelwestcenter.add(lblname,cn);
		lblname.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=1;
		NewUserPanelwestcenter.add(txtname,cn);//
		txtname.setPreferredSize(new Dimension(1,25));
		//txtname.setText(sessionBean.getUserName());
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=2;
		NewUserPanelwestcenter.add(lblUserName,cn);
		lblUserName.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=2;
		NewUserPanelwestcenter.add(txtUserName,cn);//
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=3;
		NewUserPanelwestcenter.add(lblUserTyepe,cn);
		lblUserTyepe.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=3;
		NewUserPanelwestcenter.add(cmbUserType,cn);//
		cmbUserType.setPreferredSize(new Dimension(1,25));
		cmbUserType.setBackground(Color.white);
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=4;
		NewUserPanelwestcenter.add(lblDesignation,cn);
		lblDesignation.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=4;
		NewUserPanelwestcenter.add(txtDesignation,cn);//
		txtDesignation.setPreferredSize(new Dimension(1,25));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=5;
		NewUserPanelwestcenter.add(lblPassWord,cn);
		lblPassWord.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=5;
		NewUserPanelwestcenter.add(txtPassword,cn);//
		txtPassword.setPreferredSize(new Dimension(1,25));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=6;
		NewUserPanelwestcenter.add(lblDateOfJoin,cn);
		lblDateOfJoin.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=6;
		NewUserPanelwestcenter.add(dofj,cn);//
		dofj.setPreferredSize(new Dimension(1,25));
		dofj.setDateFormatString("dd-MM-YYYY");
		dofj.setDate(new java.util.Date());
		dofj.setBackground(Color.white);
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=7;
		NewUserPanelwestcenter.add(lblActivity,cn);
		lblActivity.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=7;
		NewUserPanelwestcenter.add(cmbActivity,cn);//
		cmbActivity.setPreferredSize(new Dimension(1,25));
		cmbActivity.setBackground(Color.white);
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=8;
		NewUserPanelwestcenter.add(lblEmail,cn);
		lblEmail.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=8;
		NewUserPanelwestcenter.add(txtemail,cn);//
		txtemail.setPreferredSize(new Dimension(1,25));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=9;
		NewUserPanelwestcenter.add(lblNationalID,cn);
		lblNationalID.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=9;
		NewUserPanelwestcenter.add(txtNationalID,cn);//
		txtNationalID.setPreferredSize(new Dimension(1,25));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=10;
		NewUserPanelwestcenter.add(lblAddress,cn);
		lblAddress.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=10;
		NewUserPanelwestcenter.add(scrolladdress,cn);//
		scrolladdress.setPreferredSize(new Dimension(1,60));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=0;
		cn.gridy=11;
		NewUserPanelwestcenter.add(lblMobile,cn);
		lblMobile.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 5, 5, 5);
		cn.gridx=1;
		cn.gridy=11;
		NewUserPanelwestcenter.add(txtMobile,cn);//
		txtMobile.setPreferredSize(new Dimension(1,25));
	}
	public void NewUserPanelwestEast()
	{
		NewUserPanelwestEast.setBackground(Color.white);
		NewUserPanelwestEast.setLayout(new GridBagLayout());
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(30, 5, 5, 15);
		cn.gridx=0;
		cn.gridy=0;
		lblUpload.setPreferredSize(new Dimension(130,160));
		lblUpload.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		NewUserPanelwestEast.add(lblUpload,cn);
		cn.insets=new Insets(10, 20, 255, 30);
		cn.gridx=0;
		cn.gridy=1;
		NewUserPanelwestEast.add(btnUpload,cn);
		btnUpload.setForeground(Color.black);
		btnUpload.setBackground(new Color(0,134,139));
		btnUpload.setFont(new Font("Arial Narrow",Font.PLAIN+Font.BOLD,15));
	}
	public void NewUserPanelwestSouth()
	{
		NewUserPanelwestSouth.setBackground(Color.white);
		NewUserPanelwestSouth.setPreferredSize(new Dimension(0,120));
		NewUserPanelwestSouth.setLayout(new GridBagLayout());
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 40,5);
		cn.gridx=0;
		cn.gridy=0;
		NewUserPanelwestSouth.add(btnAdd,cn);
		btnAdd.setForeground(Color.black);
		btnAdd.setBackground(new Color(0,134,139));
		btnAdd.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		cn.insets=new Insets(5, 5, 40,5);
		cn.gridx=1;
		cn.gridy=0;
		NewUserPanelwestSouth.add(btnEdit,cn);
		btnEdit.setForeground(Color.black);
		btnEdit.setBackground(new Color(0,134,139));
		btnEdit.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		cn.insets=new Insets(5, 5, 40,5);
		cn.gridx=2;
		cn.gridy=0;
		NewUserPanelwestSouth.add(btnRefresh,cn);
		btnRefresh.setForeground(Color.black);
		btnRefresh.setBackground(new Color(0,134,139));
		btnRefresh.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		cn.insets=new Insets(5, 5, 40,5);
		cn.gridx=3;
		cn.gridy=0;
		NewUserPanelwestSouth.add(btnDelete,cn);
		btnDelete.setForeground(Color.black);
		btnDelete.setBackground(new Color(0,134,139));
		btnDelete.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
	}
	public void NewUserPanelCenter()
	{
		BorderLayout border = new BorderLayout();
		border.setHgap(0);
		NewUserPanelCenter.setLayout(border);
		NewUserPanelCenter.setPreferredSize(new Dimension(520,1));
		NewUserPanelCenter.add(NewUserPanelCenterNorth,BorderLayout.NORTH);
		NewUserPanelCenterNorthWorks();
		NewUserPanelCenter.add(NewUserPanelCenterCenter, BorderLayout.CENTER);
		NewUserPanelCenterCenterWorks();
	}
	@SuppressWarnings("static-access")
	private void NewUserPanelCenterNorthWorks() 
	{
		NewUserPanelCenterNorth.setBackground(new Color(2, 191, 185));
		TitledBorder newUserDataTitle=new TitledBorder("Existing User");
		newUserDataTitle.setTitleFont(new Font("Agency FB",Font.BOLD,22));
		newUserDataTitle.setTitleJustification(TitledBorder.CENTER);
		newUserDataTitle.setTitlePosition(newUserDataTitle.TOP);
		NewUserPanelCenterNorth.setBorder(newUserDataTitle);
		scrollTable.setPreferredSize(new Dimension(505,620));
		table.getTableHeader().setReorderingAllowed(false);
		scrollTable.getViewport().setBackground(Color.white);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setOpaque(false);
		scrollTable.setOpaque(false);
		table.setShowGrid(true);
		table.setRowHeight(table.getRowHeight()+2);
		NewUserPanelCenterNorth.add(scrollTable);
	}
	private void NewUserPanelCenterCenterWorks() 
	{
		NewUserPanelCenterCenter.setBackground(new Color(2, 191, 185));
		NewUserPanelCenterCenter.setPreferredSize(new Dimension(540,100));
		NewUserPanelCenterCenter.setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5,5,15,5);
		c.gridx=0;
		c.gridy=0;
		NewUserPanelCenterCenter.add(btnPrinter, c);
		btnPrinter.setPreferredSize(new Dimension(510,40));
		btnPrinter.setForeground(Color.white);
		btnPrinter.setBackground(new Color(0,70,70));
		btnPrinter.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
	}
	public void init()
	{
		setPreferredSize(new Dimension(1140,740));
		BorderLayout border=new BorderLayout();
		border.setHgap(25);
		setLayout(border);
	}
}
