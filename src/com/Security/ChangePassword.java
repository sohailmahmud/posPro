package com.Security;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import com.Admin.Dbconnection;
import com.Admin.SuggestText;

public class ChangePassword extends JPanel
{
	private static final long serialVersionUID = 1L;
	JPanel ChangePasswordWorkNorth=new JPanel();
	JPanel ChangePasswordWorkWest=new JPanel();
	JPanel ChangePasswordWorkEast=new JPanel();
	JPanel ChangePasswordWorkCenter=new JPanel();
	JPanel ChangePasswordWorkSouth=new JPanel();
	JPanel ChangePasswordWorkCenterNorth=new JPanel();
	JPanel ChangePasswordWorkCenterSouth=new JPanel();
	JPanel ChangePasswordWorkCenterCenter1=new JPanel();

	JLabel lblUserName=new JLabel("User Name");
	JLabel lblOldPassword=new JLabel("Old Password");
	JLabel lblNewPassword=new JLabel("New Password");
	JLabel lblConfirmPassword=new JLabel("Confirm Password");
	SuggestText cmbUserName = new SuggestText();
	JTextField txtOldPassord=new JTextField(20);
	JTextField txtNewPassword=new JTextField(20);
	JTextField txtConfirmPassword=new JTextField(20);

	JButton btnChangePassword = new JButton("Change Password",new ImageIcon("images/change.png"));
	JButton btnCancel = new JButton("Refresh",new ImageIcon("images/refresh.png"));
	JPanel panelMain=new JPanel();
	JPanel panelWest=new JPanel();
	JPanel panelEast=new JPanel();
	JPanel panelCenter=new JPanel();
	JPanel panelNorth=new JPanel();
	JPanel panelSouth=new JPanel();
	JPanel panelCenterNorth=new JPanel();
	JPanel panelCenterCenter1=new JPanel();
	Font f=new Font("Tahoma",Font.BOLD,12);
	BufferedImage image;
	
	public ChangePassword()
	{
		init();
		cmp();
		btnAction();
	}
	public void refresh()
	{
		cmbDataLoad();
		cmbUserName.txtSuggest.setText("");
		txtOldPassord.setText("");
		txtNewPassword.setText("");
		txtConfirmPassword.setText("");
	}
	public boolean checkValidation()
	{
		if(!cmbUserName.txtSuggest.getText().trim().isEmpty())
		{
			if(!txtOldPassord.getText().trim().isEmpty())
			{
				if(!txtNewPassword.getText().trim().isEmpty())
				{
					if(!txtConfirmPassword.getText().trim().isEmpty())
					{
						return true;
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Please insert confirm password!","**Warning**",
								JOptionPane.WARNING_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Please insert your new password!","**Warning**",
							JOptionPane.WARNING_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Please insert your old password!","**Warning**",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Please Select an UserName!","**Warning**",
					JOptionPane.WARNING_MESSAGE);
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
	public boolean checkNewPassword()
	{
		String s1 = txtNewPassword.getText().trim().toString();
		String s2 = txtConfirmPassword.getText().trim().toString();
		if(s1.equals(s2)){
			return true;
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Sorry! :( " + "Your confirm password is not correct.","**Warning..**",
					JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	public boolean updatePassword()
	{
		try
		{
			String s1 = cmbUserName.txtSuggest.getText().trim();
			StringTokenizer token = new StringTokenizer(s1," ~ ");
			String user = token.nextToken();
			String sql = "mail tbnewuser set password ='"+txtConfirmPassword.getText().trim()+"'" +
					" where userid='"+user+"'";
			Dbconnection.connection();
			Dbconnection.sta.executeUpdate(sql);
			return true;
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null,exp+""+"(updatepassword() ChangePassword)","**Warning**",
					JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	public boolean checkSyntexPassword()
	{
		String password=txtNewPassword.getText().trim().toString();
		 @SuppressWarnings("unused")
		int digit=0;
		 int special=0;
		 int upCount=0;
		 int loCount=0;
		 String locase,upcase,spac;
		if(password.length()>=8&&password.length()<=16)
		{
			for(int i=0;i<password.length();i++){
				char c=password.charAt(i);
				if(Character.isLowerCase(c)){
					loCount++;
				}
				else if(Character.isUpperCase(c))
				{
					upCount++;
				}
				else if(c>=33&&c<=46||c==64)
				{
					special++;
				}
			}
			if(loCount>=1&&upCount>=1&&special>=1)
			{
				return true;
			}
			else{
				if(loCount<1)
				{
					locase="Lowercase Letter";
				}
				else{
					locase=" ";
				}
				if(upCount<1)
				{
					upcase="Uppercase Letter";
				}
				else{
					upcase=" ";
				}
				if(special<1)
				{
					spac="Special Charecter";
				}
				else{
					spac=" ";
				}
				JOptionPane.showMessageDialog(null,"Your passwor have not"+spac+locase+upcase,"**Error**",
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
			JOptionPane.showMessageDialog(null,"Your password is invalide","**Error**",
					JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	public boolean checkOldPassword()
	{
		try
		{
			String s1 = cmbUserName.txtSuggest.getText().trim();
			StringTokenizer token = new StringTokenizer(s1," ~ ");
			String s2 = token.nextToken();
			String sql = "select password from tbnewuser where userid='"+s2+"'";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{
				String oldPassword = rs.getString("password");
				String oldpass = txtOldPassord.getText().trim().toString();
				boolean confirm = oldPassword.equals(oldpass);
				if(confirm)
				{
					return true;
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Sorry! :( \n Your old password is not matches.","**Warning..**",
							JOptionPane.WARNING_MESSAGE);
					return false;
				}
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e+""+"chackOldpassword() ChangePassword",
					"**Warning..**",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	public void cmbDataLoad()
	{
		try
		{
			cmbUserName.v.clear();
			cmbUserName.v.add("");
			String sql = "select userid, username from tbnewuser order by "
					+ "CAST(substring(userid,LOCATE('-',userid)+1,LENGTH(userid)-locate('-',userid))as UNSIGNED)";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				String userid = rs.getString("userid");
				String userName = rs.getString("username");
				String useridname = userid+" ~ "+userName;
				cmbUserName.v.add(useridname);
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e+""+"(cmbDataload ChangePassword)","**Warning**",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void btnAction() 
	{
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				refresh();
			}
		});
		btnChangePassword.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(checkValidation())
				{
					if(checkConfirmation("Are you Sure? \n You want to change your Old password!"))
					{
						if(checkOldPassword())
						{
							if(checkSyntexPassword())
							{
								if(checkNewPassword())
								{
									if(updatePassword())
									{
										JOptionPane.showMessageDialog(null,"Your password changed successfully!","**Information**",
												JOptionPane.INFORMATION_MESSAGE);
										refresh();
									}
								}
							}
						}
					}
				}
			}
		});
		txtNewPassword.addMouseListener(new MouseListener() 
		{
			public void mouseReleased(MouseEvent arg0){}
			public void mousePressed(MouseEvent arg0) 
			{
				JOptionPane.showMessageDialog(null,"Please insert password! \n 1) 8 or more characters," +
						" but not more than 16 characters \n 2) one or more uppercase characters \n " +
						"3) one or more lowercase characters \n 4) one or more digits \n 5) one or more special characters " +
						"(like as $, @, or !)");
			}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent arg0) {}
		});
	}
	private void cmp() 
	{
		add(panelMain);
		panelMain.setLayout(new BorderLayout());
		panelMain.add(panelCenter,BorderLayout.CENTER);
		panelCenterWorks();
		panelMain.add(panelEast, BorderLayout.EAST);
		panelEastWorks();
		panelMain.add(panelWest, BorderLayout.WEST);
		panelWestWorks();
		panelMain.add(panelNorth, BorderLayout.NORTH);
		panelNorthWorks();
		panelMain.add(panelSouth, BorderLayout.SOUTH);
		panelSouthWorks();
	}

	private void panelNorthWorks() 
	{
		panelNorth.setPreferredSize(new Dimension(1,200));
		panelNorth.setBackground(new Color(2, 180, 180));
		panelNorth.setLayout(new BorderLayout());
	}

	private void panelSouthWorks() 
	{
		panelSouth.setPreferredSize(new Dimension(1,200));
		panelSouth.setBackground(new Color(2, 180, 180));
	}

	@SuppressWarnings("static-access")
	private void panelCenterWorks() 
	{
		panelCenter.setBackground(new Color(0, 128, 128));
		TitledBorder changePasswordTitle=new TitledBorder("Change Password");
		changePasswordTitle.setTitleFont(new Font("Agency FB",Font.BOLD,22));
		changePasswordTitle.setTitleJustification(TitledBorder.CENTER);
		changePasswordTitle.setTitlePosition(changePasswordTitle.TOP);
		changePasswordTitle.setTitleColor(Color.WHITE);
		panelCenter.setBorder(changePasswordTitle);
		panelCenter.setLayout(new BorderLayout());
		panelCenter.add(panelCenterNorth, BorderLayout.NORTH);
		panelCenterNorthWorks();
		panelCenter.add(panelCenterCenter1, BorderLayout.CENTER);
		panelCenterCenter1Works();
	}

	private void panelCenterNorthWorks() 
	{
		panelCenterNorth.setBackground(Color.white);
		panelCenterNorth.setPreferredSize(new Dimension(1,240));
		panelCenterNorth.setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5,0,5,5);
		c.gridx=0;
		c.gridy=0;
		panelCenterNorth.add(lblUserName,c);
		lblUserName.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.insets=new Insets(5,0,5,5);
		c.gridx=1;
		c.gridy=0;
		panelCenterNorth.add(cmbUserName.cmbSuggest,c);
		cmbUserName.cmbSuggest.setBackground(Color.white);
		c.insets=new Insets(5,0,5,5);
		c.gridx=0;
		c.gridy=1;
		panelCenterNorth.add(lblOldPassword,c);
		lblOldPassword.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.insets=new Insets(5,0,5,5);
		c.gridx=1;
		c.gridy=1;
		panelCenterNorth.add(txtOldPassord,c);
		c.insets=new Insets(5,0,5,5);
		c.gridx=0;
		c.gridy=2;
		panelCenterNorth.add(lblNewPassword,c);
		lblNewPassword.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.insets=new Insets(5,0,5,5);
		c.gridx=1;
		c.gridy=2;
		panelCenterNorth.add(txtNewPassword,c);
		c.insets=new Insets(5,0,5,5);
		c.gridx=0;
		c.gridy=3;
		panelCenterNorth.add(lblConfirmPassword,c);
		lblConfirmPassword.setFont(new Font("Cambria", Font.PLAIN+Font.BOLD, 14));
		c.insets=new Insets(5,0,5,5);
		c.gridx=1;
		c.gridy=3;
		panelCenterNorth.add(txtConfirmPassword,c);
	}

	private void panelCenterCenter1Works() 
	{
		panelCenterCenter1.setBackground(Color.white);
		panelCenterCenter1.setLayout(new GridBagLayout());
		GridBagConstraints c1=new GridBagConstraints();
		c1.fill=GridBagConstraints.BOTH;
		c1.insets=new Insets(5,80,30,5);
		c1.gridx=0;
		c1.gridy=0;
		panelCenterCenter1.add(btnChangePassword, c1);
		btnChangePassword.setPreferredSize(new Dimension(200,50));
		btnChangePassword.setForeground(Color.black);
		btnChangePassword.setBackground(new Color(0,134,139));
		btnChangePassword.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,15));
		c1.insets=new Insets(5,8,30,5);
		c1.gridx=1;
		c1.gridy=0;
		panelCenterCenter1.add(btnCancel, c1);
		btnCancel.setPreferredSize(new Dimension(200,50));
		btnCancel.setForeground(Color.black);
		btnCancel.setBackground(new Color(0,134,139));
		btnCancel.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,15));
	}

	private void panelEastWorks()
	{
		panelEast.setPreferredSize(new Dimension(200,1));
		panelEast.setBackground(new Color(2, 180, 180));
	}

	private void panelWestWorks() 
	{
		panelWest.setPreferredSize(new Dimension(220,1));
		panelWest.setBackground(new Color(2, 180, 180));
	}

	public void init()
	{
		setPreferredSize(new Dimension(1140,740));
		setLayout(new BorderLayout());
	}
}