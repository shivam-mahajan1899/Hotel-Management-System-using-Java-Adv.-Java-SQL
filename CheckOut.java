import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java .awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import java.awt.event.*;

public class CheckOut 
{
	private String url = "jdbc:mysql://localhost:3306/hotelmanagementsystem?autoReconnect=true&useSSL=false";
	private String user="root";
	private String pass="";

	private DefaultTableModel model2;
	private JTable table2;
	
	private JScrollPane pane2;

	private JFrame c;
	private JLabel name,title,mno,gender,docheckin,docheckout,add,roo;
	private JTextField tname,tmno;
	private JRadioButton male,female;
	private ButtonGroup gengp;
	private JComboBox room;
	private JTextArea tadd;
	private JButton insert,back;
	private JDateChooser idate,odate;
	private JPanel pane,jsp;


	private String columns[] = { "Name", "Mobile", "Gender","CheckInDate","Address","Room" };
	private String data2[][] = new String[6][8];

	private String rooms[]=	{"A101","A102","A103","A104","A105",
							"A201","A202","A203","A204","A205",
							"A301","B302","A303","A304","A305",
							"B101","B102","B103","B104","B105",
							"B201","B202","B203","B204","B205",
							"B301","B302","B303","B304","B305",
							"C101","C102","C103","C104","C105",
							"C201","C202","C203","C204","C205",
							"C301","C302","C303","C304","C305"};

	public CheckOut()
	{
		GraphicsEnvironment graphics =GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = graphics.getDefaultScreenDevice();
    
		c=new JFrame("Hotel Transylvania-CheckIn");
		device.setFullScreenWindow(c);
		c.setVisible(true);
		c.setSize(1920,1080);
		c.setLayout(null);
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		title = new JLabel("Check Out");
		title.setFont(new Font("The Jacklyn",Font.BOLD,60));
		title.setSize(300, 150);
		title.setLocation(900, 30);
		c.add(title);
    
		pane=new JPanel();
		pane.setSize(1000,500);
		pane.setLocation(475,200);
		pane.setLayout(null);
		pane.setBackground(Color.LIGHT_GRAY);
		c.add(pane);
    
		name = new JLabel("Name");
		name.setFont(new Font("Arial", Font.PLAIN, 20));
		name.setSize(100, 20);
		name.setLocation(350, 50);
		pane.add(name);
		
		tname = new JTextField();
		tname.setFont(new Font("Arial", Font.PLAIN, 15));
		tname.setSize(240, 30);
		tname.setLocation(450, 50);
		tname.addKeyListener(new KeyAdapter()
	    {
	    	public void keyReleased(KeyEvent evt)
	    	{
	    		textKeyReleased(evt);
	    	}
	    });
		pane.add(tname);
		
		
		mno = new JLabel("Mobile");
		mno.setFont(new Font("Arial", Font.PLAIN, 20));
		mno.setSize(100, 20);
		mno.setLocation(350, 100);
		pane.add(mno);
 
		tmno = new JTextField();
		tmno.setFont(new Font("Arial", Font.PLAIN, 15));
		tmno.setSize(240, 30);
		tmno.setLocation(450, 100);
		tmno.setEditable(false);
		pane.add(tmno);
		
		gender = new JLabel("Gender");
		gender.setFont(new Font("Arial", Font.PLAIN, 20));
		gender.setSize(100, 20);
		gender.setLocation(350, 150);
		pane.add(gender);
		
		male = new JRadioButton("Male");
		male.setFont(new Font("Arial", Font.PLAIN, 15));
		male.setSelected(false);
		male.setSize(75, 20);
		male.setLocation(450, 150);
		male.setEnabled(false);
		pane.add(male);
 
		female = new JRadioButton("Female");
    	female.setFont(new Font("Arial", Font.PLAIN, 15));
    	female.setSelected(false);
    	female.setSize(80, 20);
    	female.setLocation(525, 150);
    	female.setEnabled(false);
    	pane.add(female);
 
    	gengp = new ButtonGroup();
    	gengp.add(male);
    	gengp.add(female);
    	
    	docheckin = new JLabel("Check-In Date");
	    docheckin.setFont(new Font("Arial", Font.PLAIN, 20));
	    docheckin.setSize(150, 20);
	    docheckin.setLocation(350,200);
	    pane.add(docheckin);
    	
    	idate=new JDateChooser();
	    idate.setDateFormatString("yyyy-MM-dd");
	    idate.setFont(new Font("Arial", Font.PLAIN, 20));
	    idate.setSize(190,30);
	    idate.setLocation(500,200);
	    idate.setEnabled(false);
	    pane.add(idate);
    	
    	docheckout = new JLabel("Check-Out Date");
    	docheckout.setFont(new Font("Arial", Font.PLAIN, 20));
    	docheckout.setSize(150, 20);
    	docheckout.setLocation(350,250);
    	pane.add(docheckout);
    
    	odate=new JDateChooser();
    	odate.setDateFormatString("yyyy-MM-dd");
    	odate.setFont(new Font("Arial", Font.PLAIN, 20));
    	odate.setSize(190,30);
    	odate.setLocation(500,250);
    	pane.add(odate);
 
    	add = new JLabel("Address");
    	add.setFont(new Font("Arial", Font.PLAIN, 20));
    	add.setSize(100, 20);
    	add.setLocation(350, 300);
    	pane.add(add);
 
    	tadd = new JTextArea();
    	tadd.setFont(new Font("Arial", Font.PLAIN, 15));
    	tadd.setSize(240, 60);
    	tadd.setLocation(450, 300);
    	tadd.setEditable(false);
    	tadd.setLineWrap(true);
    	pane.add(tadd);
    
    	roo = new JLabel("Room Alloted");
    	roo.setFont(new Font("Arial", Font.PLAIN, 20));
    	roo.setSize(200, 20);
    	roo.setLocation(350, 400);
    	pane.add(roo);
    
    	room=new JComboBox(rooms);
    	room.setFont(new Font("Arial", Font.PLAIN, 15));
    	room.setSize(60,30);
    	room.setLocation(550,400);
    	room.setEnabled(false);
    	pane.add(room);
    
    	back = new JButton("BACK");
    	back.setFont(new Font("Arial", Font.PLAIN, 15));
    	back.setSize(150, 35);
    	back.setLocation(300, 450);
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
    	insert = new JButton("CHECK OUT");
    	insert.setFont(new Font("Arial", Font.PLAIN, 15));
    	insert.setSize(150, 35);
    	insert.setLocation(520, 450);
    	insert.addActionListener(new ActionListener()
    	{
    		@Override
    		public void actionPerformed(ActionEvent e)
    		{
    			insertdata();
    		}
    	});
    	pane.add(insert);
    	
    	model2=new DefaultTableModel(data2,columns);
	    table2=new JTable(model2);
	    table2.setShowGrid(true);
		table2.setShowVerticalLines(true);
		table2.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt)
			{
                table_customerMouseClicked(evt);
			}
		});
		pane2= new JScrollPane(table2);
		jsp=new JPanel();
		jsp.add(pane2);
		jsp.setSize(850,300);
		jsp.setLocation(575,750);
		c.add(jsp);
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
			String odd=sdf.format(odate.getDate());
			String ptadd=tadd.getText();
			String prom=room.getSelectedItem().toString();
			
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement stm = con.createStatement();
			String query="insert into customer(Name,Mobile,Gender,CheckInDate,CheckOutDate,Address,Room)value('"+pname+"','"+ptmno+"','"+pgen+"','"+idd+"','"+odd+"','"+ptadd+"','"+prom+"');";
			stm.executeUpdate(query);
			
		}
		catch(SQLException exc)
		{
			exc.printStackTrace();
		}
	}
	public void textKeyReleased(KeyEvent evt)
	{
		String nme=tname.getText();
		String query="select * from checkin where Name='"+nme+"';";
		
		int ro=table2.getRowCount();
		while(ro-->0)
		{
			model2.removeRow(ro);
		}
		try
		{
			ResultSet rs=DBHelper2.getData(nme);
			while(rs.next())
			{
				java.util.Vector v = new java.util.Vector();
				v.add(rs.getString("Name"));
				v.add(rs.getString("Mobile"));
				v.add(rs.getString("Gender"));
				v.add(rs.getString("CheckInDate"));
				v.add(rs.getString("Address"));
				v.add(rs.getString("Room"));
				
				model2.addRow(v);
			}
		}
		catch(SQLException exc)
		{
			JOptionPane.showMessageDialog(null,"Database Error");
		}
	}
	public void table_customerMouseClicked(MouseEvent evt)
	{
		int row = table2.getSelectedRow();
		
		tname.setText(table2.getModel().getValueAt(row, 0)+"");
		tmno.setText((String)table2.getModel().getValueAt(row, 1));
		String gend=(String)table2.getModel().getValueAt(row,2);
		String m=male.getText();
		if(gend.equals(m))
		{
			male.setSelected(true);
		}
		else
		{
			female.setSelected(true);
		}
		try
		{	
		java.util.Date idt=new SimpleDateFormat("yyyy-MM-dd").parse((String) table2.getModel().getValueAt(row,3));
		idate.setDate(idt);
		}
		catch(ParseException ex)
		{
			ex.printStackTrace();
		}
		tadd.setText((String)table2.getModel().getValueAt(row,4));
		String rm=(String)table2.getModel().getValueAt(row,5);
		for (int j=0; j<room.getItemCount();j++)
		{
		      if (room.getItemAt(j).toString().equals(rm)) 
		      {
		        room.setSelectedIndex(j);
		        break;
		      }
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