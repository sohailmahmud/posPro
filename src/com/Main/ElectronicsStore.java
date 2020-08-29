package com.Main;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import com.Admin.Dbconnection;
import com.Admin.Login;
import com.Admin.ProgressBar;

public class ElectronicsStore 
{
	public static void main(String[] args)
	{
		try 
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Projects Theme was not loaded!\nPlease check it.");
		}
		Dbconnection.connection();
		@SuppressWarnings("unused")
		ProgressBar bar = new ProgressBar();
		Login lg = new Login();
		ImageIcon icon3 = new ImageIcon("images/logopro1.png");
	    lg.setIconImage(icon3.getImage());
	}

}
