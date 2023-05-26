import java .awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.sql.*;

public class Login 
{
	public JFrame jf;
	public JPanel jp;
	public JLabel uid,upass,loghead;
	public JTextField jpss,jid;
	public JButton frgt,exit,logn,sign;
	
	private String url = "jdbc:mysql://localhost:3306/hotelmanagementsystem?autoReconnect=true&useSSL=false";
	private String user="root";
	private String pass="";
	
	public Login()
	{
		GraphicsEnvironment graphics =GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice device = graphics.getDefaultScreenDevice();
	    
		jf=new JFrame("Hotel Transylvania");
		device.setFullScreenWindow(jf);
		jf.setVisible(true);
		jf.setSize(1920,1080);
		jf.setLayout(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel heading=new JLabel("Hotel Management System");
		heading .setBounds(950,350,1050,450);
		heading.setFont(new Font("The Jacklyn",Font.BOLD,100));
		jf.add(heading);
		
		jp=new JPanel();
		jp.setBounds(100,300,700,600);
		jp.setBackground(Color.lightGray);
		jp.setLayout(null);
		jf.add(jp);
		
			loghead=new JLabel("Login");
			loghead .setBounds(300,10,100,150);
			loghead.setFont(new Font("The Jacklyn",Font.BOLD,60));
			jp.add(loghead);
		
			uid=new JLabel("User ID");
			uid.setFont(new Font("Arial",Font.PLAIN,25));
			uid.setBounds(150,200,100,50);
			jp.add(uid);
		
			jid=new JTextField();
			jid.setBounds(270,200,200,50);
			jid.setFont(new Font("Arial",Font.PLAIN,30));
			jp.add(jid);
			
			upass=new JLabel("Password");
			upass.setFont(new Font("Arial",Font.PLAIN,25));
			upass.setBounds(150,250,150,150);
			jp.add(upass);

			jpss=new JTextField();
			jpss.setBounds(270,300,200,50);
			jpss.setFont(new Font("Arial",Font.PLAIN,30));
			jp.add(jpss);
			
			logn=new JButton("LOGIN");
			logn.setBounds(150,400,150,40);
			jp.add(logn);
			logn.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					//TODO Auto-generated method stub
					checklogin(jf);							
				}	
			});

			sign=new JButton("SIGNIN");
			sign.setBounds(320,400,150,40);
			jp.add(sign);
			
			frgt=new JButton("FORGET PASSWORD");
			frgt.setBounds(210,450,200,40);
			jp.add(frgt);
			
			exit=new JButton("EXIT");
			exit.setBounds(260,500,100,40);
			jp.add(exit);
			exit.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					
					jf.dispose();
				}
			});
	}
	public static void main(String args[])
	{
		new Login();
	}
	public void checklogin(JFrame jf)
	{
		try
		{
			String id=jid.getText();
			String pas=jpss.getText();

			Connection con = DriverManager.getConnection(url, user, pass);
			PreparedStatement stm =(PreparedStatement)con.prepareStatement("select uid,pass from user where uid=? and pass=?");
			stm.setString(1,id);
			stm.setString(2,pas);
			ResultSet rs=stm.executeQuery();
			  if (rs.next())
			  {
				  jf.dispose();
				  new Home();
	          }
			  else
			  {
				  JOptionPane.showMessageDialog(null,"Incorrect email-Id or password..Try Again with correct detail");
			  }
			con.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}