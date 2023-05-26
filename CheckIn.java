import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java .awt.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class CheckIn 
{
	private String url = "jdbc:mysql://localhost:3306/hotelmanagementsystem?autoReconnect=true&useSSL=false";
	private String user="root";
	private String pass="";
	
	private JFrame c;
    private JLabel name,title,mno,gender,docheckin,add,roo;
    private JTextField tname,tmno;
    private JRadioButton male,female;
    private ButtonGroup gengp;
    private JComboBox room;
    private JTextArea tadd;
    private JButton insert,back;
    private JDateChooser idate;
    private JPanel pane;
	
	private String rooms[]
	    	= {"A101","A102","A103","A104","A105",
	    	   "A201","A202","A203","A204","A205",
	    	   "A301","B302","A303","A304","A305",
	    	   "B101","B102","B103","B104","B105",
	    	   "B201","B202","B203","B204","B205",
	    	   "B301","B302","B303","B304","B305",
	    	   "C101","C102","C103","C104","C105",
	    	   "C201","C202","C203","C204","C205",
	    	   "C301","C302","C303","C304","C305"};
	
	public CheckIn()
	{
		GraphicsEnvironment graphics =GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice device = graphics.getDefaultScreenDevice();
	    
	    c=new JFrame("Hotel Transylvania-CheckIn");
		device.setFullScreenWindow(c);
		c.setVisible(true);
		c.setSize(1920,1080);
		c.setLayout(null);
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		title = new JLabel("Check In");
		title.setFont(new Font("The Jacklyn",Font.BOLD,60));
	    title.setSize(300, 150);
	    title.setLocation(900, 30);
	    c.add(title);
	    
	    pane=new JPanel();
	    pane.setSize(1000,600);
	    pane.setLocation(475,200);
	    pane.setLayout(null);
	    pane.setBackground(Color.LIGHT_GRAY);
	    c.add(pane);
	    
	    name = new JLabel("Name");
	    name.setFont(new Font("Arial", Font.PLAIN, 20));
	    name.setSize(100, 20);
	    name.setLocation(350, 150);
	    pane.add(name);
	 
	    tname = new JTextField();
	    tname.setFont(new Font("Arial", Font.PLAIN, 15));
	    tname.setSize(240, 30);
	    tname.setLocation(450, 150);
	    pane.add(tname);
	 
	    mno = new JLabel("Mobile");
	    mno.setFont(new Font("Arial", Font.PLAIN, 20));
	    mno.setSize(100, 20);
	    mno.setLocation(350, 200);
	    pane.add(mno);
	 
	    tmno = new JTextField();
	    tmno.setFont(new Font("Arial", Font.PLAIN, 15));
	    tmno.setSize(240, 30);
	    tmno.setLocation(450, 200);
	    pane.add(tmno);
	 
	    gender = new JLabel("Gender");
	    gender.setFont(new Font("Arial", Font.PLAIN, 20));
	    gender.setSize(100, 20);
	    gender.setLocation(350, 250);
	    pane.add(gender);
	 
	    male = new JRadioButton("Male");
	    male.setFont(new Font("Arial", Font.PLAIN, 15));
	    male.setSelected(false);
	    male.setSize(75, 20);
	    male.setLocation(450, 250);
	    pane.add(male);
	 
	    female = new JRadioButton("Female");
	    female.setFont(new Font("Arial", Font.PLAIN, 15));
	    female.setSelected(false);
	    female.setSize(80, 20);
	    female.setLocation(525, 250);
	    pane.add(female);
	 
	    gengp = new ButtonGroup();
	    gengp.add(male);
	    gengp.add(female);
	    
	    docheckin = new JLabel("Check-In Date");
	    docheckin.setFont(new Font("Arial", Font.PLAIN, 20));
	    docheckin.setSize(150, 20);
	    docheckin.setLocation(350,300);
	    pane.add(docheckin);
	    
	    idate=new JDateChooser();
	    idate.setDateFormatString("yyyy-MM-dd");
	    idate.setFont(new Font("Arial", Font.PLAIN, 20));
	    idate.setSize(190,30);
	    idate.setLocation(500,300);
	    pane.add(idate);
	 
	    add = new JLabel("Address");
	    add.setFont(new Font("Arial", Font.PLAIN, 20));
	    add.setSize(100, 20);
	    add.setLocation(350, 350);
	    pane.add(add);
	 
	    tadd = new JTextArea();
	    tadd.setFont(new Font("Arial", Font.PLAIN, 15));
	    tadd.setSize(240, 60);
	    tadd.setLocation(450, 350);
	    tadd.setLineWrap(true);
	    pane.add(tadd);
	    
	    roo = new JLabel("Room Alloted");
	    roo.setFont(new Font("Arial", Font.PLAIN, 20));
	    roo.setSize(200, 20);
	    roo.setLocation(350, 450);
	    pane.add(roo);
	    
	    room=new JComboBox(rooms);
	    room.setFont(new Font("Arial", Font.PLAIN, 15));
	    room.setSize(60,30);
	    room.setLocation(550,450);
	    pane.add(room);
	    
	    back = new JButton("BACK");
	    back.setFont(new Font("Arial", Font.PLAIN, 15));
	    back.setSize(150, 35);
	    back.setLocation(300, 550);
	    pane.add(back);
	    
	    back.addActionListener(new ActionListener()
	    {
	    	@Override
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		c.dispose();
	    		new Home();
	    	}
	    });    
	    insert = new JButton("CHECK IN");
	    insert.setFont(new Font("Arial", Font.PLAIN, 15));
	    insert.setSize(150, 35);
	    insert.setLocation(520, 550);
	    insert.addActionListener(new ActionListener()
	    {
	    	@Override
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		insertdata();
	    	}
	    });
	    pane.add(insert);
	}
	public void insertdata()
	{
		try 
		{
			String pname=tname.getText();
			String ptmno=tmno.getText();
			String pgen=getSelectedButtonText(gengp);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String idd=sdf.format(idate.getDate());
			String ptadd=tadd.getText();
			String prom=room.getSelectedItem().toString();
			
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement stm = con.createStatement();
			String query="insert into checkin(Name,Mobile,Gender,CheckInDate,Address,Room)value('"+pname+"','"+ptmno+"','"+pgen+"','"+idd+"','"+ptadd+"','"+prom+"');";
			stm.executeUpdate(query);
		}
		catch(SQLException exc)
		{
			exc.printStackTrace();
		}
	}
	String getSelectedButtonText(ButtonGroup buttonGroup) 
	{
	    for (Enumeration buttons = buttonGroup.getElements(); buttons.hasMoreElements();) 
	    {
	        AbstractButton button = (AbstractButton) buttons.nextElement();

	        if (button.isSelected()) 
	        {
	            return button.getText();
	        }
	    }
	    return null;
	}
}