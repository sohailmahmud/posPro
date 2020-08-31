package com.Admin;
import javax.swing.*;
import java.awt.*;

public class ProgressBar extends JWindow
{
	private static final long serialVersionUID = 1L;
	JPanel panelMain=new JPanel();
	JPanel panelCenter=new JPanel();
	JLabel lbl_icon=new JLabel(new ImageIcon("images/proglogo1.png"));
	JProgressBar bar=new JProgressBar(0,100);
	JLabel lblParcentage = new JLabel();
	JPanel panelNorth=new JPanel();
	JLabel lblExtracting=new JLabel();
	int c;
	public ProgressBar()
	{
		init();
		cmp();
	}
	public void cmp()
	{
		add(panelMain);
		panelMain.setLayout(new BorderLayout());
		panelMain.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		panelMain.add(lbl_icon,BorderLayout.CENTER);
		panelMain.add(bar,BorderLayout.SOUTH);
		bar.setPreferredSize(new Dimension(300,24));
		bar.setForeground(Color.BLUE);
		bar.setBackground(Color.black);
		FlowLayout flow = new FlowLayout();
		bar.setLayout(flow);
		flow.setVgap(0);
		bar.add(lblParcentage);
		lblParcentage.setFont(new Font("Cambria", Font.PLAIN+Font.ITALIC, 18));
		lblParcentage.setBackground(Color.DARK_GRAY);
		lblParcentage.setForeground(Color.BLACK);
		try 
		{
			for(c=0;c<100;c++)
			{
				int value=bar.getValue();
				if(value<100)
				{
					bar.setValue(++value);
					lblParcentage.setText(value+"%");
				}
				Thread.sleep(20);
			}
			
			dispose();
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Progress Error!","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void init()
	{
		setSize(600,336);
		setVisible(true);
		setLocationRelativeTo(null);
	}

}
