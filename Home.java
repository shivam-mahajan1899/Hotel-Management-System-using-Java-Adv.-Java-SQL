import java.sql.*;
import java .awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Home
{
	public Home()
	{
		GraphicsEnvironment graphics =GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice device = graphics.getDefaultScreenDevice();
	    
		JFrame jf=new JFrame("Hotel Transylvania-Home");
		device.setFullScreenWindow(jf);
		jf.setVisible(true);
		jf.setSize(1920,1080);
		jf.setLayout(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel heading=new JLabel("Hotel Management System");
		heading .setBounds(550,20,1050,250);
		heading.setFont(new Font("The Jacklyn",Font.BOLD,100));
		jf.add(heading);
		
		JPanel jp=new JPanel();
		jp.setBounds(460,425,950,350);
		jp.setBackground(Color.lightGray);
		jp.setLayout(new FlowLayout(20,10,10));
		jf.add(jp);
		
		JLabel home=new JLabel("                          Home                    ");
		home.setFont(new Font("The Jacklyn",Font.BOLD,60));
		jp.add(home);
		
		Dimension bd = new Dimension(225,175);
		
		String cst="<html>"+"<center>"+"CUSTOMER"+"<br>"+"DATA"+"</center>"+"</html>";
		JButton custdata=new JButton(cst);
		custdata.setPreferredSize(bd);
		custdata.setBackground(Color.white);
		custdata.setFont(new Font("Arial",Font.PLAIN,25));
		jp.add(custdata);
		custdata.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				jf.dispose();
				new Customer();				
			}
		});
		
		String cin="<html>"+"<center>"+"CHECK"+"<br>"+"IN"+"</center>"+"</html>";
		JButton checkin=new JButton(cin);
		checkin.setPreferredSize(bd);
		checkin.setBackground(Color.white);
		checkin.setFont(new Font("Arial",Font.PLAIN,25));
		jp.add(checkin);
		checkin.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				jf.dispose();
				new CheckIn();
			}
		});
		
		String cout="<html>"+"<center>"+"CHECK"+"<br>"+"OUT"+"</center>"+"</html>";
		JButton checkout=new JButton(cout);
		checkout.setFont(new Font("Arial",Font.PLAIN,25));
		checkout.setBackground(Color.white);
		checkout.setPreferredSize(bd);
		jp.add(checkout);
		checkout.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				jf.dispose();
				new CheckOut();
			}
		});
		
		JButton back=new JButton("BACK");
		back.setFont(new Font("Arial",Font.PLAIN,25));
		back.setBackground(Color.white);
		back.setPreferredSize(bd);
		jp.add(back);
		back.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//TODO Auto-generated method stub
				jf.dispose();
				new Login();
			}	
			
		});
		
	}
}