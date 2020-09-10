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
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class Clientinfo extends JPanel
{
	private static final long serialVersionUID = 1L;
	SessionBean sessionBean;
	JPanel clientinfoPanelWest=new JPanel();
	JPanel ClientinfoPanelwestnorth=new JPanel();
	JLabel lblClientinfo = new JLabel("Search By : ");
	SuggestText cmbClientinfo = new SuggestText();
	JPanel ClientinfoPanelwestcenter=new JPanel();
	JPanel ClientinfoPanelwestEast=new JPanel();
	JLabel lblUpload = new JLabel();
	JButton btnUpload = new JButton("Upload",new ImageIcon("images/Upload.png"));
	JPanel ClientinfoPanelwestSouth=new JPanel();
	JButton btnAdd=new JButton("Add ",new ImageIcon("images/add.png"));
	JButton btnEdit=new JButton("Edit",new ImageIcon("images/edit.png"));
	JButton btnRefresh=new JButton("Refresh",new ImageIcon("images/refresh.png"));
	JButton btnDelete=new JButton("Delete",new ImageIcon("images/delete.png"));
	JPanel clientinfoPanelCenter=new JPanel();
	JLabel lblclientId=new JLabel("Client ID");
	JLabel lblClientName=new JLabel("Client Name");
	JLabel lblgender=new JLabel("Gender");
	JLabel lblFatherName=new JLabel("Father's Name");
	JLabel lblMotherName=new JLabel("Mother's Name");
	JLabel lblReligion=new JLabel("Religion");
	JLabel lblDateOfBirth=new JLabel("Date of Birth");
	JLabel lblDateOfJoin=new JLabel("Date of Join");
	JLabel lblMobile=new JLabel("Mobile Number");
	JLabel lblAddress=new JLabel("Address");
	JLabel lblEmail=new JLabel("Email");
	JLabel lblNationalId=new JLabel("National ID");
	JLabel lblNationality=new JLabel("Nationality");
	JLabel lblUserName=new JLabel("User Name");
	JLabel lblReferenceBy=new JLabel("Reference By");
	JTextField txtclientId=new JTextField(20);
	JTextField txtClientName=new JTextField(20);
	String select[]={"","Male","Female"};
	JComboBox<String> cmbgender=new JComboBox<>(select);
	JTextField txtFatherName=new JTextField(20);
	JTextField txtMotherName=new JTextField(20);
	JTextField txtreligion=new JTextField(20);
	JDateChooser jdcdateOfBirth=new JDateChooser();
	JDateChooser jdcdateOfjoin=new JDateChooser();
	JTextField txtmobile=new JTextField(20);
	JTextArea txtAreaAddress=new JTextArea(5,1);
	JTextField txtemail=new JTextField(20);
	JTextField txtnationalId=new JTextField(20);
	JTextField txtnationality=new JTextField(20);
	JTextField txtuserName=new JTextField(20);
	JComboBox<String> cmbReference=new JComboBox<>();
	GridBagConstraints c=new GridBagConstraints();
	JScrollPane scrolladdress=new JScrollPane(txtAreaAddress,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	GridBagConstraints cn=new GridBagConstraints();
	String[] column={"Client ID","Client Name","Mobile Number","Email"};
	Object[][] row={};
	DefaultTableModel model=new DefaultTableModel(row,column)
	{
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int column)
		{
			return false;
		}
	};
	JTable table=new JTable(model);
	JScrollPane scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	Font font=new Font("Baskerville O",Font.BOLD,20);

	JFileChooser fileChooser = null;
	File file = null;
	BufferedImage buffer = null;
	boolean upload = false;
	boolean isUpdate = false;
	boolean isDelete = false;
	BufferedImage image;
	ImageIcon uploadImage = new ImageIcon("images/uploadimg.png");

	public Clientinfo(SessionBean bean)
	{
		init();
		cmp();
		btnAction();
		btnini(true);
		defaultEditable(false);
		txtEditable(true);
		lblUpload.setIcon(uploadImage);
		this.sessionBean = bean;
		txtuserName.setText(sessionBean.getUserName());
	}
	public void btnini(boolean b)
	{
		btnAdd.setEnabled(b);
		btnEdit.setEnabled(!b);
		btnDelete.setEnabled(!b);
	}
	private void defaultEditable(boolean d)
	{
		txtclientId.setEditable(d);
		txtuserName.setEditable(d);
	}
	public void txtEditable(boolean e)
	{
		txtClientName.setEditable(e);
		txtFatherName.setEditable(e);
		txtMotherName.setEditable(e);
		txtreligion.setEditable(e);
		txtmobile.setEditable(e);
		txtAreaAddress.setEditable(e);
		txtemail.setEditable(e);
		txtnationalId.setEditable(e);
		txtnationality.setEditable(e);
		jdcdateOfBirth.setEnabled(e);
		jdcdateOfjoin.setEnabled(e);
		cmbgender.setEnabled(e);
		cmbReference.setEnabled(e);
		btnUpload.setEnabled(e);
	}
	private void txtClear()
	{
		cmbClientinfo.txtSuggest.setText("");
		txtClientName.setText("");
		txtFatherName.setText("");
		txtMotherName.setText("");
		txtreligion.setText("");
		txtmobile.setText("");
		txtAreaAddress.setText("");
		txtemail.setText("");
		txtnationalId.setText("");
		txtnationality.setText("");
		jdcdateOfBirth.setDate(new Date());
		jdcdateOfjoin.setDate(new Date());
		cmbgender.setSelectedItem("");
		isUpdate = false;
		isDelete = false;
		txtEditable(true);
		lblUpload.setIcon(uploadImage);
	}
	public void refreshButton()
	{
		autoidcli();
		txtClear();
		btnini(true);
		tableDataLoad();
		cmbReferenceDataLoad();
		cmbDataLoad();
		file = null;
	}
	private boolean checkValidation()
	{
		if(!txtclientId.getText().trim().isEmpty())
		{
			if(!txtClientName.getText().trim().isEmpty())
			{
				if(cmbgender.getSelectedIndex()!=0 && cmbgender.getSelectedItem()!=null)
				{
					if(!txtFatherName.getText().trim().isEmpty())
					{
						if(!txtMotherName.getText().trim().isEmpty())
						{
							if(!txtreligion.getText().trim().isEmpty())
							{
								if(jdcdateOfBirth.getDate().before(jdcdateOfjoin.getDate()))
								{
									if(jdcdateOfjoin.getDate().after(jdcdateOfBirth.getDate()))
									{
										if(!txtmobile.getText().trim().isEmpty())
										{
											if(!txtAreaAddress.getText().trim().isEmpty())
											{
												if(!txtemail.getText().trim().isEmpty())
												{
													if(!txtnationalId.getText().trim().isEmpty())
													{
														if(!txtnationality.getText().trim().isEmpty())
														{
															if(!txtuserName.getText().trim().isEmpty())
															{
																if(cmbReference.getSelectedIndex()!=0&&cmbReference.getSelectedItem()!=null)
																{
																	if(upload)
																	{
																		return true;
																	}
																	else
																	{
																		JOptionPane.showMessageDialog(null, "Please Upload a picture!","Warning...",JOptionPane.WARNING_MESSAGE);
																	}
																}
																else
																{
																	JOptionPane.showMessageDialog(null, "Please select a Reference Please!","Warning",JOptionPane.WARNING_MESSAGE);
																}
															}
															else
															{
																JOptionPane.showMessageDialog(null, "Insert your User name Please!","Warning",JOptionPane.WARNING_MESSAGE);
															}
														}
														else
														{
															JOptionPane.showMessageDialog(null, "Insert your Nationality Please!","Warning",JOptionPane.WARNING_MESSAGE);
														}
													}
													else
													{
														JOptionPane.showMessageDialog(null, "Insert your NationalID No. Please!","Warning",JOptionPane.WARNING_MESSAGE);
													}
												}
												else
												{
													JOptionPane.showMessageDialog(null, "Insert your email Please!","Warning",JOptionPane.WARNING_MESSAGE);
												}
											}
											else
											{
												JOptionPane.showMessageDialog(null, "Insert your address Please!","Warning",JOptionPane.WARNING_MESSAGE);
											}
										}
										else
										{
											JOptionPane.showMessageDialog(null, "Insert your mobile number Please!","Warning",JOptionPane.WARNING_MESSAGE);
										}
									}
									else
									{
										JOptionPane.showMessageDialog(null, "Insert your joining date Please!","Warning",JOptionPane.WARNING_MESSAGE);
									}
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Insert your birth date Please!","Warning",JOptionPane.WARNING_MESSAGE);
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Insert your Religion Please!","Warning",JOptionPane.WARNING_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Insert your mother name Please!","Warning",JOptionPane.WARNING_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Insert your Father name Please!","Warning",JOptionPane.WARNING_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Select a Gender Please!","Warning",JOptionPane.WARNING_MESSAGE);

				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Insert your Name Please!","Warning",JOptionPane.WARNING_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Insert your Client ID Please!","Warning",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	private boolean checkConfirmation(String clientCaption)
	{
		int a = JOptionPane.showConfirmDialog(null, clientCaption,"Confirmation...",JOptionPane.YES_NO_OPTION);
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
			String imageDir = "D:/ProjectImages/ClientImages";
			File file1 = new File(imageDir);
			if(!file1.isDirectory())
			{
				file1.mkdirs();
			}
			buffer = ImageIO.read(file);
			String imagePath = imageDir+"/"+txtclientId.getText()+".jpg";
			File fileWrite = new File(imagePath);
			if(fileWrite.exists())
			{
				fileWrite.delete();
			}
			ImageIO.write(buffer, "jpg", fileWrite);
			
			String birthDate = new SimpleDateFormat("yyyy-MM-dd").format(jdcdateOfBirth.getDate());
			String joinDate = new SimpleDateFormat("yyyy-MM-dd").format(jdcdateOfjoin.getDate());
			StringTokenizer token = new StringTokenizer(cmbReference.getSelectedItem().toString(), "~");
			String refByid = token.nextToken();
			String refByName = token.nextToken();

			String sql="insert into tbclientinfo(clientId,clientName,gender,fatherName,"
					+ "motherName,religion,dob,doj,mobileNo,"
					+ "address,email,nid,nationality,userName,refByid,refByName,"
					+ "userIp,entryTime,picture)values('"+txtclientId.getText().trim()+"','"+txtClientName.getText().trim()+"',"
					+ "'"+cmbgender.getSelectedItem().toString()+"',"
					+ "'"+txtFatherName.getText().trim()+"','"+txtMotherName.getText().trim()+"',"
					+ "'"+txtreligion.getText().trim()+"','"+birthDate+"','"+joinDate+"',"
					+ "'"+txtmobile.getText().trim()+"','"+txtAreaAddress.getText().trim()+"',"
					+ "'"+txtemail.getText().trim()+"','"+txtnationalId.getText().trim()+"',"
					+ "'"+txtnationality.getText().trim()+"','"+txtuserName.getText().trim()+"',"
					+ "'"+refByid+"','"+refByName+"','',now(),'"+txtclientId.getText()+".jpg')";
			Dbconnection.connection();
			Dbconnection.sta.executeUpdate(sql);
			Dbconnection.con.close();
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(from insertData Clientinfo)","Error..",JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	public void searchDataLoad(String clid)
	{
		try
		{
			String sql = "select clientId,clientName,gender,fatherName,motherName,religion,dob,doj,mobileNo,address,email,nid,nationality,userName,refByid,refByName,picture from tbClientinfo where clientid = '"+clid+"'";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{
				txtclientId.setText(rs.getString("clientid"));
				txtClientName.setText(rs.getString("clientName"));
				cmbgender.setSelectedItem(rs.getString("gender"));
				txtFatherName.setText(rs.getString("fathername"));
				txtMotherName.setText(rs.getString("mothername"));
				txtreligion.setText(rs.getString("religion"));
				jdcdateOfBirth.setDate(rs.getDate("dob"));
				jdcdateOfjoin.setDate(rs.getDate("doj"));
				txtmobile.setText(rs.getString("mobileNo"));
				txtAreaAddress.setText(rs.getString("address"));
				txtemail.setText(rs.getString("email"));
				txtnationalId.setText(rs.getString("nid"));
				txtnationality.setText(rs.getString("nationality"));
				txtuserName.setText(rs.getString("username"));
				cmbReference.setSelectedItem(rs.getString("refByid")+"~"+rs.getString("refByName"));

				if(!rs.getString("picture").toString().isEmpty())
				{

					String imagePath = "D:/ProjectImages/ClientImages/"+rs.getString("picture");
					Image image = Toolkit.getDefaultToolkit().getImage(imagePath);
					Image resize = image.getScaledInstance(lblUpload.getWidth(), lblUpload.getHeight(), Image.SCALE_DEFAULT);
					lblUpload.setIcon(new ImageIcon(resize));
				}
				else 
				{
					lblUpload.setIcon(new ImageIcon("images/uploadimg.png"));
				}
				upload = true;
				txtEditable(false);
				btnini(false);
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(from SearchDataLoad Clientinfo)","Error..",JOptionPane.ERROR_MESSAGE);
		}
	}
	public boolean deleteData()
	{
		try
		{
			String sql = "delete from tbclientinfo where clientid = '"+txtclientId.getText().trim()+"'";
			Dbconnection.connection();
			Dbconnection.sta.executeUpdate(sql);
			Dbconnection.con.close();
			return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e+""+"(from deleteData clientinfo())",
					"Warning..",JOptionPane.WARNING_MESSAGE);
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
			String sql="select clientid,clientName,mobileNo,email from tbclientinfo order by" +
					" CAST(substring(clientid,LOCATE('-',clientid)+1,LENGTH(clientid)-locate('-',clientid))as UNSIGNED)";
			Dbconnection.connection();
			ResultSet rs=Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				model.addRow(new Object[]{rs.getString("clientId"),rs.getString("clientName"),rs.getString("mobileNo"),rs.getString("email")});
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(from tableDataLoad Clientinfo)","Warning..",JOptionPane.WARNING_MESSAGE);

		}
	}
	public void cmbDataLoad()
	{
		try
		{
			cmbClientinfo.v.clear();
			cmbClientinfo.v.add("");
			String sql="select clientid,clientName from tbclientinfo order by " +
					"CAST(substring(clientid,LOCATE('-',clientid)+1,LENGTH(clientid)-locate" +
					"('-',clientid))as UNSIGNED)";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbClientinfo.v.add(rs.getString("clientid")+"~"+rs.getString("clientName"));
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e+""+"(cmbdataload ClientInfo)",
					"Warning",JOptionPane.WARNING_MESSAGE);
		}
	}
	public void cmbReferenceDataLoad()
	{
		try
		{
			cmbReference.removeAllItems();
			cmbReference.addItem("");
			String sql = "select userid,name from tbnewuser";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				String idName = rs.getString("userid")+"~"+rs.getString("name");
				cmbReference.addItem(idName);
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(from referenceLoad Newuser)","Error..",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void uploadAction()
	{
		fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("image","jpg","jpeg","png","gif","ico");
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
		txtmobile.addKeyListener(new KeyListener() 
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
		txtnationalId.addKeyListener(new KeyListener()
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
		cmbClientinfo.cmbSuggest.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!cmbClientinfo.txtSuggest.getText().trim().isEmpty())
				{
					String client = cmbClientinfo.txtSuggest.getText().trim();
					StringTokenizer token = new StringTokenizer(client,"~");
					searchDataLoad(token.nextToken());
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
				searchDataLoad(table.getValueAt(row, 0).toString());
			}
		});
		table.addKeyListener(new KeyListener() 
		{
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) 
			{
				int row = table.getSelectedRow();
				searchDataLoad(table.getValueAt(row, 0).toString());
			}
			public void keyPressed(KeyEvent arg0) {}
		});
		btnUpload.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				uploadAction();
			}
		});
		btnAdd.addActionListener(new ActionListener() 
		{


			public void actionPerformed(ActionEvent e) 
			{
				if(checkValidation())
				{
					if(checkConfirmation(isUpdate?"Sure To Upadate?":"Sure To Insert?"))
					{
						if(isUpdate)
						{
							if(deleteData())
							{
								if(insertData())
								{
									JOptionPane.showMessageDialog(null, "Updated successfully :)","Information",JOptionPane.INFORMATION_MESSAGE);
									lblUpload.setIcon(uploadImage);
									refreshButton();
								}
							}
						}
						else
						{
							if(insertData())
							{
								JOptionPane.showMessageDialog(null, "Save successfully :)","Information",JOptionPane.INFORMATION_MESSAGE);
								lblUpload.setIcon(uploadImage);
								refreshButton();
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
				lblUpload.setIcon(uploadImage);
				refreshButton();
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
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				isDelete = true;
				if(checkValidation())
				{
					if(checkConfirmation("Sure To Delete?"))
					{
						if(deleteData())
						{
							JOptionPane.showMessageDialog(null,"Delete Data Successfully!","**information**",
									JOptionPane.INFORMATION_MESSAGE);
							refreshButton();
							lblUpload.setIcon(uploadImage);
						}
					}
				}
			}
		});
	}
	public void autoidcli()
	{
		try
		{
			String sql="select ifnull(max(cast(SubString(clientId,locate('-',clientId)+1,"
					+ "length(clientId)-locate('-',clientId))as UNSIGNED)),0)+1 as cliId from tbclientInfo";
			Dbconnection.connection();
			ResultSet rs=Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{
				String clientId = rs.getString("cliId");
				txtclientId.setText("Clientid-"+clientId);
			}
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(from autoid Clientinfo)","Warning..",JOptionPane.WARNING_MESSAGE);
		}
	}
	public void cmp()
	{
		BorderLayout border = new BorderLayout();
		border.setVgap(0);
		setLayout(border);
		clientinfoPanelWest();
		add(clientinfoPanelWest,BorderLayout.WEST);
		clientinfoPanelCenter();
		add(clientinfoPanelCenter,BorderLayout.CENTER);
	}
	@SuppressWarnings("static-access")
	public void clientinfoPanelWest()
	{
		clientinfoPanelWest.setBackground(new Color(2, 191, 185));
		clientinfoPanelWest.setPreferredSize(new Dimension(600,1));
		TitledBorder clientTitle=new TitledBorder("Client Info");
		clientTitle.setTitleFont(new Font("Agency FB",Font.PLAIN+Font.BOLD,22));
		clientTitle.setTitleJustification(TitledBorder.CENTER);
		clientTitle.setTitlePosition(clientTitle.TOP);
		clientinfoPanelWest.setBorder(clientTitle);
		clientinfoPanelWest.setLayout(new BorderLayout());
		clientinfoPanelWest.add(ClientinfoPanelwestnorth,BorderLayout.NORTH);
		ClientinfoPanelwestnorth();
		clientinfoPanelWest.add(ClientinfoPanelwestcenter,BorderLayout.CENTER);
		ClientinfoPanelwestcenter();
		clientinfoPanelWest.add(ClientinfoPanelwestEast,BorderLayout.EAST);
		ClientinfoPanelwestEast();
		clientinfoPanelWest.add(ClientinfoPanelwestSouth,BorderLayout.SOUTH);
		ClientinfoPanelwestSouth();
	}
	public void ClientinfoPanelwestnorth()
	{
		ClientinfoPanelwestnorth.setBackground(new Color(2, 191, 185));
		ClientinfoPanelwestnorth.setPreferredSize(new Dimension(0,55));
		ClientinfoPanelwestnorth.setLayout(new GridBagLayout());

		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=0;
		cn.gridy=0;
		ClientinfoPanelwestnorth.add(lblClientinfo,cn);
		lblClientinfo.setFont(new Font("Arial Narrow", Font.PLAIN+Font.BOLD, 15));
		lblClientinfo.setPreferredSize(new Dimension(78,30));
		cn.insets=new Insets(5,5,5,5);
		cn.gridx=1;
		cn.gridy=0;
		ClientinfoPanelwestnorth.add(cmbClientinfo.cmbSuggest,cn);
		cmbClientinfo.cmbSuggest.setPreferredSize(new Dimension(280,33));
		cmbClientinfo.cmbSuggest.setBackground(Color.white);
	}
	public void ClientinfoPanelwestcenter()
	{
		ClientinfoPanelwestcenter.setBackground(Color.white);
		GridBagLayout grid=new GridBagLayout();
		ClientinfoPanelwestcenter.setLayout(grid);
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=0;
		cn.gridy=0;
		ClientinfoPanelwestcenter.add(lblclientId,cn);
		lblclientId.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=1;
		cn.gridy=0;
		ClientinfoPanelwestcenter.add(txtclientId,cn);//
		txtclientId.setPreferredSize(new Dimension(1,25));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=0;
		cn.gridy=1;
		ClientinfoPanelwestcenter.add(lblClientName,cn);
		lblClientName.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=1;
		cn.gridy=1;
		ClientinfoPanelwestcenter.add(txtClientName,cn);//
		txtClientName.setPreferredSize(new Dimension(1,25));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=0;
		cn.gridy=2;
		ClientinfoPanelwestcenter.add(lblgender,cn);
		lblgender.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=1;
		cn.gridy=2;
		ClientinfoPanelwestcenter.add(cmbgender,cn);//
		cmbgender.setBackground(Color.white);
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=0;
		cn.gridy=3;
		ClientinfoPanelwestcenter.add(lblFatherName,cn);
		lblFatherName.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=1;
		cn.gridy=3;
		ClientinfoPanelwestcenter.add(txtFatherName,cn);//
		txtFatherName.setPreferredSize(new Dimension(1,25));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=0;
		cn.gridy=4;
		ClientinfoPanelwestcenter.add(lblMotherName,cn);
		lblMotherName.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=1;
		cn.gridy=4;
		ClientinfoPanelwestcenter.add(txtMotherName,cn);//
		txtMotherName.setPreferredSize(new Dimension(1,25));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=0;
		cn.gridy=5;
		ClientinfoPanelwestcenter.add(lblReligion,cn);
		lblReligion.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=1;
		cn.gridy=5;
		ClientinfoPanelwestcenter.add(txtreligion,cn);//
		txtreligion.setPreferredSize(new Dimension(1,25));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=0;
		cn.gridy=6;
		ClientinfoPanelwestcenter.add(lblDateOfBirth,cn);
		lblDateOfBirth.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=1;
		cn.gridy=6;
		jdcdateOfBirth.setDateFormatString("dd-MM-yyyy");
		jdcdateOfBirth.setDate(new Date());
		ClientinfoPanelwestcenter.add(jdcdateOfBirth,cn);//
		jdcdateOfBirth.setPreferredSize(new Dimension(1,25));
		jdcdateOfBirth.setBackground(Color.white);
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=0;
		cn.gridy=7;
		ClientinfoPanelwestcenter.add(lblDateOfJoin,cn);
		lblDateOfJoin.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=1;
		cn.gridy=7;
		ClientinfoPanelwestcenter.add(jdcdateOfjoin,cn);//
		jdcdateOfjoin.setPreferredSize(new Dimension(1,25));
		jdcdateOfjoin.setDateFormatString("dd-MM-yyyy");
		jdcdateOfjoin.setDate(new Date());
		jdcdateOfjoin.setBackground(Color.white);
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=0;
		cn.gridy=8;
		ClientinfoPanelwestcenter.add(lblMobile,cn);
		lblMobile.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=1;
		cn.gridy=8;
		ClientinfoPanelwestcenter.add(txtmobile,cn);//
		txtmobile.setPreferredSize(new Dimension(1,25));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=0;
		cn.gridy=9;
		ClientinfoPanelwestcenter.add(lblAddress,cn);
		lblAddress.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=1;
		cn.gridy=9;
		ClientinfoPanelwestcenter.add(scrolladdress,cn);//
		scrolladdress.setPreferredSize(new Dimension(1,60));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=0;
		cn.gridy=10;
		ClientinfoPanelwestcenter.add(lblEmail,cn);
		lblEmail.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=1;
		cn.gridy=10;
		ClientinfoPanelwestcenter.add(txtemail,cn);//
		txtemail.setPreferredSize(new Dimension(1,25));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=0;
		cn.gridy=11;
		ClientinfoPanelwestcenter.add(lblNationalId,cn);
		lblNationalId.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=1;
		cn.gridy=11;
		ClientinfoPanelwestcenter.add(txtnationalId,cn);//
		txtnationalId.setPreferredSize(new Dimension(1,25));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=0;
		cn.gridy=12;
		ClientinfoPanelwestcenter.add(lblNationality,cn);
		lblNationality.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=1;
		cn.gridy=12;
		ClientinfoPanelwestcenter.add(txtnationality,cn);//
		txtnationality.setPreferredSize(new Dimension(1,25));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=0;
		cn.gridy=13;
		ClientinfoPanelwestcenter.add(lblUserName,cn);
		lblUserName.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=1;
		cn.gridy=13;
		ClientinfoPanelwestcenter.add(txtuserName,cn);//
		txtuserName.setPreferredSize(new Dimension(1,25));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=0;
		cn.gridy=14;
		ClientinfoPanelwestcenter.add(lblReferenceBy,cn);
		lblReferenceBy.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		cn.insets=new Insets(5, 0, 5, 5);
		cn.gridx=1;
		cn.gridy=14;
		ClientinfoPanelwestcenter.add(cmbReference,cn);//
		cmbReference.setBackground(Color.white);

	}
	public void ClientinfoPanelwestEast()
	{
		ClientinfoPanelwestEast.setBackground(Color.white);
		ClientinfoPanelwestEast.setLayout(new GridBagLayout());
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(0, 10, 5, 20);
		cn.gridx=0;
		cn.gridy=0;
		lblUpload.setPreferredSize(new Dimension(130,160));
		lblUpload.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		ClientinfoPanelwestEast.add(lblUpload,cn);
		cn.insets=new Insets(10, 20, 325, 30);
		cn.gridx=0;
		cn.gridy=1;
		ClientinfoPanelwestEast.add(btnUpload,cn);
		btnUpload.setForeground(Color.black);
		btnUpload.setBackground(new Color(0,134,139));
		btnUpload.setFont(new Font("Arial Narrow",Font.PLAIN+Font.BOLD,15));
	}
	public void ClientinfoPanelwestSouth()
	{
		ClientinfoPanelwestSouth.setBackground(Color.white);
		ClientinfoPanelwestSouth.setPreferredSize(new Dimension(0,70));
		ClientinfoPanelwestSouth.setLayout(new GridBagLayout());
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 0, 5,5);
		cn.gridx=0;
		cn.gridy=0;
		ClientinfoPanelwestSouth.add(btnAdd,cn);
		btnAdd.setForeground(Color.black);
		btnAdd.setBackground(new Color(0,134,139));
		btnAdd.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		cn.insets=new Insets(5, 0, 5,5);
		cn.gridx=1;
		cn.gridy=0;
		ClientinfoPanelwestSouth.add(btnEdit,cn);
		btnEdit.setForeground(Color.black);
		btnEdit.setBackground(new Color(0,134,139));
		btnEdit.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		cn.insets=new Insets(5, 0, 5,5);
		cn.gridx=2;
		cn.gridy=0;
		ClientinfoPanelwestSouth.add(btnRefresh,cn);
		btnRefresh.setForeground(Color.black);
		btnRefresh.setBackground(new Color(0,134,139));
		btnRefresh.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
		cn.insets=new Insets(5, 0, 5,5);
		cn.gridx=3;
		cn.gridy=0;
		ClientinfoPanelwestSouth.add(btnDelete,cn);
		btnDelete.setForeground(Color.black);
		btnDelete.setBackground(new Color(0,134,139));
		btnDelete.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,13));
	}
	@SuppressWarnings("static-access")
	public void clientinfoPanelCenter()
	{
		clientinfoPanelCenter.setBackground(new Color(2, 191, 185));
		clientinfoPanelCenter.setPreferredSize(new Dimension(520,1));
		TitledBorder clientTableTitle=new TitledBorder("Existing Client");
		clientTableTitle.setTitleFont(new Font("Agency FB",Font.PLAIN+Font.BOLD,22));
		clientTableTitle.setTitleJustification(TitledBorder.CENTER);
		clientTableTitle.setTitlePosition(clientTableTitle.TOP);
		clientinfoPanelCenter.setBorder(clientTableTitle);
		clientinfoPanelCenter.setLayout(new BorderLayout());
		scroll.setPreferredSize(new Dimension(510,730));
		table.getTableHeader().setReorderingAllowed(false);
		scroll.getViewport().setBackground(Color.white);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setOpaque(false);
		scroll.setOpaque(false);
		table.setShowGrid(true);
		table.setRowHeight(table.getRowHeight()+2);
		clientinfoPanelCenter.add(scroll);
	}
	public void init()
	{
		setPreferredSize(new Dimension(1140,740));
		BorderLayout border=new BorderLayout();
		border.setHgap(25);
		setLayout(border);
	}
}


