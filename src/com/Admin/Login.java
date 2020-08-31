package com.Admin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Login extends JFrame
{
	private static final long serialVersionUID = 1L;
	SessionBean sessionBean = new SessionBean();
	JFrame frm = new JFrame();

	JPanel mainPanel=new JPanel();
	JPanel panelNorth=new JPanel();
	JPanel panelSouth=new JPanel();
	JPanel panelCenter=new JPanel();

	JLabel lblTitle=new JLabel("Electronics Store Management");
	JLabel lblFooter = new JLabel("Developed By Sohail Sami");
	//ImageIcon icon=new ImageIcon("images/adminicon.png");

	JButton btnLogIn=new JButton("Login",new ImageIcon("images/login.png"));
	JButton btnCancel=new JButton("Cancel",new ImageIcon("images/cancel.png"));
	JButton btnForgotPassword = new JButton("ForgotPassword?",new ImageIcon("images/forgot.png"));
	JLabel lblUser = new JLabel("Username");
	JLabel lblpass = new JLabel("Password");
	JLabel lblUserType = new JLabel("User Type");
	JTextField txtUser = new JTextField(16);
	JPasswordField txtpass = new JPasswordField(16);
	String ara[] = {"","Admin","Super Admin","Manager","Executive","General"};
	JComboBox<String> cmbUserType = new JComboBox<>(ara);
	JLabel lblShowPassword = new JLabel(new ImageIcon("images/ShowPassword.png"));

	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	Color bgColor = new Color(2, 191, 185);
	Color btnColor = new Color(0, 134, 139);
	boolean main = true;
	boolean owner = false;
	boolean newPassword = false;
	boolean login = true;
	int temp = 0;
	String userName = "";
	int count = 0;
	//public JFrame frame;

	public Login()
	{
		init();
		cmp();
		btnAction();
	}
	public void workingPanelAction()
	{
		sessionBean.setUserName(txtUser.getText().trim());
		sessionBean.setCompanyName("Electronics Store Management");
		sessionBean.setCompanyAddress("Panchlaish, Chittagong-4212.");
		sessionBean.setDeveloperAddress("Software Developed By: CTG TECH CLUB.   Mob: 01624340883.   Web: www.ctgtechclub.com");
		mainPanel.setVisible(false);
		WorkingPanel wp = new WorkingPanel(sessionBean,this); 
		add(wp);
		wp.setVisible(true);
		setSize(screen);
		setLocationRelativeTo(null);
		setTitle("Welcome To Electronics Store Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@SuppressWarnings("deprecation")
	private boolean checkValidation()
	{
		if(!txtUser.getText().trim().toString().isEmpty())
		{
			if(!txtpass.getText().trim().isEmpty())
			{
				return true;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Insert Your Password Please!");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Insert Your Username or Email Please!");
		}
		return false;
	}
	public boolean checkUser()
	{
		boolean match = false;
		try
		{
			String sql = "select userName from tbNewUser";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			while(rs.next())
			{
				boolean eq = rs.getString("userName").equals(txtUser.getText().trim());
				if(eq)
				{
					match = true;
				}
			}
			Dbconnection.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+""+"(from checkUser Login.java)","Warning...",JOptionPane.WARNING_MESSAGE);
		}
		if(!match)
		{
			JOptionPane.showMessageDialog(null, "Your UserName is not matches!!","Warning.. :( ",JOptionPane.WARNING_MESSAGE);
		}
		return match;
	}
	public void count()
	{
		if(count == 3)
		{
			int select = JOptionPane.showConfirmDialog(null,"Are you forgot your password? don't worry you can change your password ","Forgot",JOptionPane.YES_NO_OPTION);
			if(select == JOptionPane.YES_OPTION)
			{
				JOptionPane.showMessageDialog(null,"You can change your password Please type forgotten password button");
				btnLogIn.setEnabled(false);
				btnForgotPassword.setEnabled(true);
			}
			else if(select == JOptionPane.NO_OPTION)
			{
				setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				count = 0;
			}
		}
	}
	@SuppressWarnings("deprecation")
	public boolean checkPassworMatching()
	{
		try
		{
			String s1=txtUser.getText().trim().toString();
			String sql="select pass from tbnewuserinfo where username='"+s1+"'";
			Dbconnection.connection();
			ResultSet rs = Dbconnection.sta.executeQuery(sql);
			if(rs.next())
			{
				String password = rs.getString("3rr0r");
				String newpassword = txtpass.getText().trim();
				boolean confirm=password.equals(newpassword);
				if(confirm)
				{
					return true;
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Your password in not correct","**Error**",
							JOptionPane.ERROR_MESSAGE);
					count++;
					count();
				}
			}
			Dbconnection.con.close();
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null,exp+""+"( chackpasswordmaching() )",
					"**Error**",JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	private void btnAction() 
	{
		btnLogIn.addActionListener(new ActionListener() 
		{	
			public void actionPerformed(ActionEvent e) 
			{
				if(checkValidation())
				{
					workingPanelAction();
				}

			}
		});
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JDialog.setDefaultLookAndFeelDecorated(true);
				int select = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit now?","Exit",JOptionPane.YES_NO_OPTION);
				if(select == JOptionPane.YES_OPTION)
				{
					dispose();
				}
				else if(select == JOptionPane.NO_OPTION)
				{
					setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		btnForgotPassword.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!txtUser.getText().trim().isEmpty())
				{
					if(checkUser())
					{
						userName = txtUser.getText().trim().toString();
						JOptionPane.showMessageDialog(null, "Please, Insert Owner verification code for security","Information...", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Insert your userName first!","Warning...",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		this.addWindowListener(new WindowListener() 
		{
			public void windowOpened(WindowEvent arg0) {}
			public void windowIconified(WindowEvent arg0) {}
			public void windowDeiconified(WindowEvent arg0) {}
			public void windowDeactivated(WindowEvent arg0) {}
			public void windowClosing(WindowEvent arg0) 
			{
				int a = JOptionPane.showConfirmDialog(null, "Are You Sure?\nYou want to exit now!","Confirm",JOptionPane.YES_NO_OPTION);
				if(a==JOptionPane.YES_OPTION)
				{
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				else
				{
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
			public void windowClosed(WindowEvent arg0) {}
			public void windowActivated(WindowEvent arg0) {}
		});
	}
	private void cmp() 
	{
		add(mainPanel);
		mainPanel.setLayout(new BorderLayout()); 
		mainPanel.add(panelNorth,BorderLayout.NORTH);
		panelNorthWorks();
		mainPanel.add(panelSouth,BorderLayout.SOUTH);
		panelSouthWorks();
		mainPanel.add(panelCenter, BorderLayout.CENTER);
		panelCenterWorks();
	}

	private void panelCenterWorks() 
	{
		panelCenter.setBackground(Color.white);
		GridBagLayout grid = new GridBagLayout();
		panelCenter.setLayout(grid);
		GridBagConstraints c=new GridBagConstraints();
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5,5,5,5);
		c.gridx=0;
		c.gridy=0;
		panelCenter.add(lblUser, c);
		lblUser.setForeground(Color.BLACK);
		lblUser.setFont(new Font("Cambria",Font.PLAIN+Font.BOLD,14));
		c.insets=new Insets(5,5,5,5);
		c.gridx=1;
		c.gridy=0;
		panelCenter.add(txtUser, c);
		txtUser.setCursor(new Cursor(DISPOSE_ON_CLOSE));
		txtUser.requestFocusInWindow();
		txtUser.setText("Sami");
		txtUser.setPreferredSize(new Dimension(300, 30));
		txtUser.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
		c.gridx=0;
		c.gridy=1;
		panelCenter.add(lblpass, c);
		lblpass.setFont(new Font("Cambria",Font.PLAIN+Font.BOLD,14));
		c.gridx=1;
		c.gridy=1;
		panelCenter.add(txtpass, c);
		txtpass.requestFocusInWindow();
		txtpass.setBackground(Color.white);
		txtpass.setText("Sohail@12345");	
		txtpass.setPreferredSize(new Dimension(300, 30));
		txtpass.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
		c.gridx=0;
		c.gridy=2;
		panelCenter.add(lblUserType, c);
		lblUserType.setFont(new Font("Cambria",Font.PLAIN+Font.BOLD,14));
		c.gridx=1;
		c.gridy=2;
		panelCenter.add(cmbUserType, c);
		cmbUserType.setSelectedItem("Admin");
		cmbUserType.setBackground(Color.white);	
		cmbUserType.setPreferredSize(new Dimension(300, 30));
		cmbUserType.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
	}
	private void panelSouthWorks() 
	{
		panelSouth.setBackground(Color.white);
		panelSouth.setPreferredSize(new Dimension(1,55));
		panelSouth.setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5,5,20,5);
		c.gridx=0;
		c.gridy=0;
		panelSouth.add(btnForgotPassword,c);
		btnForgotPassword.setForeground(Color.BLACK);
		btnForgotPassword.setBackground(btnColor);
		btnForgotPassword.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,15));
		c.insets=new Insets(5,5,20,5);
		c.gridx=1;
		c.gridy=0;
		panelSouth.add(btnLogIn,c);
		btnLogIn.setForeground(Color.BLACK);
		btnLogIn.setBackground(btnColor);
		btnLogIn.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,15));
		c.insets=new Insets(5,5,20,5);
		c.gridx=2;
		c.gridy=0;
		panelSouth.add(btnCancel,c);
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setBackground(btnColor);
		btnCancel.setFont(new Font("Arial",Font.PLAIN+Font.BOLD,15));
	}

	private void panelNorthWorks() 
	{
		panelNorth.setBackground(bgColor);
		panelNorth.setPreferredSize(new Dimension(0,60));
		FlowLayout flow = new FlowLayout();
		flow.setVgap(15);
		panelNorth.setLayout(flow);
		panelNorth.add(lblTitle);
		lblTitle.setForeground(Color.black);
		lblTitle.setFont(new Font("Cambria",Font.PLAIN+Font.BOLD,25));

	}
	private void init() 
	{
		setSize(450,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Access Panel");
		setResizable(false);
	}
}
