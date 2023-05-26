import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java .awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class Customer 
{
	private String url = "jdbc:mysql://localhost:3306/hotelmanagementsystem?autoReconnect=true&useSSL=false";
	private String user="root";
	private String pass="";

	private DefaultTableModel model,model2;
	
	private JFrame c;
    private JLabel name,title,mno,gender,docheckin,docheckout,add,roo,srch;
    private JTextField tname,tmno,srching;
    private JRadioButton male,female;
    private ButtonGroup gengp;
    private JComboBox room;
    private JTextArea tadd;
    private JButton edit,reset,back;
    private JTable table,table2;
    private JDateChooser idate,odate;
    private JScrollPane pane,pane2; 
    private JPanel jp,jsp,jdp;
    
    private String columns[] = { "Name", "Mobile", "Gender","CheckInDate","CheckOutDate","Address","Room" };
	private String data[][] = new String[50][8];
	private String data2[][] = new String[6][8];

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
	public Customer()
	{
		GraphicsEnvironment graphics =GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice device = graphics.getDefaultScreenDevice();
	    
		c=new JFrame("Hotel Transylvania-Customer Data");
		device.setFullScreenWindow(c);
		c.setVisible(true);
		c.setSize(1920,1080);
		c.setLayout(null);
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		populatedata(c);
		
		title = new JLabel("Customer Info");
		title.setFont(new Font("The Jacklyn",Font.BOLD,60));
	    title.setSize(300, 150);
	    title.setLocation(900, 30);
	    c.add(title);
	 
	    name = new JLabel("Name");
	    name.setFont(new Font("Arial", Font.PLAIN, 20));
	    name.setSize(100, 20);
	    name.setLocation(500, 200);
	    c.add(name);
	 
	    tname = new JTextField();
	    tname.setFont(new Font("Arial", Font.PLAIN, 15));
	    tname.setSize(240, 30);
	    tname.setLocation(600, 200);
	    c.add(tname);
	 
	    mno = new JLabel("Mobile");
	    mno.setFont(new Font("Arial", Font.PLAIN, 20));
	    mno.setSize(100, 20);
	    mno.setLocation(500, 250);
	    c.add(mno);
	 
	    tmno = new JTextField();
	    tmno.setFont(new Font("Arial", Font.PLAIN, 15));
	    tmno.setSize(240, 30);
	    tmno.setLocation(600, 250);
	    c.add(tmno);
	 
	    gender = new JLabel("Gender");
	    gender.setFont(new Font("Arial", Font.PLAIN, 20));
	    gender.setSize(100, 20);
	    gender.setLocation(500, 300);
	    c.add(gender);
	 
	    male = new JRadioButton("Male");
	    male.setFont(new Font("Arial", Font.PLAIN, 15));
	    male.setSelected(false);
	    male.setSize(75, 20);
	    male.setLocation(600, 300);
	    c.add(male);
	 
	    female = new JRadioButton("Female");
	    female.setFont(new Font("Arial", Font.PLAIN, 15));
	    female.setSelected(false);
	    female.setSize(80, 20);
	    female.setLocation(675, 300);
	    c.add(female);
	 
	    gengp = new ButtonGroup();
	    gengp.add(male);
	    gengp.add(female);
	    
	    docheckin = new JLabel("Check-In Date");
	    docheckin.setFont(new Font("Arial", Font.PLAIN, 20));
	    docheckin.setSize(150, 20);
	    docheckin.setLocation(500,350);
	    c.add(docheckin);
	    
	    idate=new JDateChooser();
	    idate.setDateFormatString("yyyy-MM-dd");
	    idate.setFont(new Font("Arial", Font.PLAIN, 20));
	    idate.setSize(190,30);
	    idate.setLocation(650,350);
	    c.add(idate);
	    
	    docheckout = new JLabel("Check-Out Date");
	    docheckout.setFont(new Font("Arial", Font.PLAIN, 20));
	    docheckout.setSize(150, 20);
	    docheckout.setLocation(500, 400);
	    c.add(docheckout);
	    
	    odate=new JDateChooser();
	    odate.setDateFormatString("yyyy-MM-dd");
	    odate.setFont(new Font("Arial", Font.PLAIN, 20));
	    odate.setSize(190,30);
	    odate.setLocation(650,400);
	    c.add(odate);
	 
	    add = new JLabel("Address");
	    add.setFont(new Font("Arial", Font.PLAIN, 20));
	    add.setSize(100, 20);
	    add.setLocation(500, 450);
	    c.add(add);
	 
	    tadd = new JTextArea();
	    tadd.setFont(new Font("Arial", Font.PLAIN, 15));
	    tadd.setSize(240, 60);
	    tadd.setLocation(600, 450);
	    tadd.setLineWrap(true);
	    c.add(tadd);
	    
	    roo = new JLabel("Room Alloted");
	    roo.setFont(new Font("Arial", Font.PLAIN, 20));
	    roo.setSize(200, 20);
	    roo.setLocation(500, 550);
	    c.add(roo);
	    
	    room=new JComboBox(rooms);
	    room.setFont(new Font("Arial", Font.PLAIN, 15));
	    room.setSize(60,30);
	    room.setLocation(700,550);
	    c.add(room);
	    
	    back = new JButton("BACK");
	    back.setFont(new Font("Arial", Font.PLAIN, 15));
	    back.setSize(100, 20);
	    back.setLocation(500, 600);
	    c.add(back);
	    
	    back.addActionListener(new ActionListener()
	    {
	    	@Override
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		c.dispose();
	    		new Home();
	    	}
	    });    
	    edit = new JButton("EDIT");
	    edit.setFont(new Font("Arial", Font.PLAIN, 15));
	    edit.setSize(100, 20);
	    edit.setLocation(620, 600);
	    edit.addActionListener(new ActionListener()
	    {
	    	@Override
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		editdata();
	    	}
	    });
	    c.add(edit);
	    
	    reset = new JButton("RESET");
	    reset.setFont(new Font("Arial", Font.PLAIN, 15));
	    reset.setSize(100, 20);
	    reset.setLocation(740, 600);
	    c.add(reset);
	    
	    reset.addActionListener(new ActionListener()
	    {
	    	@Override
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		resetall();
	    	}
	    });
	    jdp=new JPanel();
	    jdp.setLayout(null);
	    jdp.setLayout(null);
	    jdp.setBackground(Color.LIGHT_GRAY);
	    jdp.setSize(350,100);
	    jdp.setLocation(500,750);
	    c.add(jdp);
	    
	    srch=new JLabel("SEARCH CUSTOMER");
	    srch.setFont(new Font("Arial", Font.PLAIN, 20));
	    srch.setSize(250, 20);
	    srch.setLocation(50,5);
	    srch.setHorizontalAlignment(JLabel.CENTER);
	    jdp.add(srch);
	    
	    srching=new JTextField();
	    srching.setFont(new Font("Arial", Font.PLAIN, 20));
	    srching.setSize(250,35);
	    srching.setLocation(50,35);
	    srching.addKeyListener(new KeyAdapter()
	    {
	    	public void keyReleased(KeyEvent evt)
	    	{
	    		textKeyReleased(evt);
	    	}
	    });
	    jdp.add(srching);
	    
	    model2=new DefaultTableModel(data2,columns);
	    table2=new JTable(model2);
	    table2.setShowGrid(true);
		table2.setShowVerticalLines(true);
		pane2= new JScrollPane(table2);
		jsp=new JPanel();
		jsp.add(pane2);
		jsp.setSize(850,300);
		jsp.setLocation(900,750);
		c.add(jsp);
	}
	
	
	public void populatedata(JFrame jf)
	{
		try 
		{
			String query="select * from customer;";
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery(query);
	    
			int i=0;
			while(res.next())
			{
				String name=res.getString("Name");
				String mobile=res.getString("Mobile");
				String gender=res.getString("Gender");
				String checkin=res.getString("CheckInDate");
				String checkout=res.getString("CheckOutDate");
				String address=res.getString("Address");
				String rom=res.getString("Room");
				
				data[i][0]=name+"";
				data[i][1]=mobile;
				data[i][2]=gender;
				data[i][3]=checkin;
				data[i][4]=checkout;
				data[i][5]=address;
				data[i][6]=rom;
				i++;
			}
			model=new DefaultTableModel(data,columns);
			table=new JTable(model);
			table.setShowGrid(true);
			table.setShowVerticalLines(true);
			table.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(java.awt.event.MouseEvent evt)
				{
	                table_customerMouseClicked(evt);
				}
			});
			
			pane= new JScrollPane(table);
			jp=new JPanel();
			jp.add(pane);
			jp.setSize(850,900);
			jp.setLocation(900,180);
			jf.add(jp);
			
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void textKeyReleased(KeyEvent evt)
	{
		String nme=srching.getText();
		String query="select * from customer where Name='"+nme+"';";
		
		int ro=table2.getRowCount();
		while(ro-->0)
		{
			model2.removeRow(ro);
		}
		try
		{
			ResultSet rs=DBHelper.getData(nme);
			while(rs.next())
			{
				java.util.Vector v = new java.util.Vector();
				v.add(rs.getString("Name"));
				v.add(rs.getString("Mobile"));
				v.add(rs.getString("Gender"));
				v.add(rs.getString("CheckInDate"));
				v.add(rs.getString("CheckOutDate"));
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
		int row = table.getSelectedRow();
		
		tname.setText(table.getModel().getValueAt(row, 0)+"");
		tmno.setText((String)table.getModel().getValueAt(row, 1));
		String gend=(String)table.getModel().getValueAt(row,2);
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
		java.util.Date idt=new SimpleDateFormat("yyyy-MM-dd").parse((String) table.getModel().getValueAt(row,3));
		idate.setDate(idt);
		java.util.Date odt=new SimpleDateFormat("yyyy-MM-dd").parse((String) table.getModel().getValueAt(row,4));
		odate.setDate(odt);
		}
		catch(ParseException ex)
		{
			ex.printStackTrace();
		}
		tadd.setText((String)table.getModel().getValueAt(row,5));
		String rm=(String)table.getModel().getValueAt(row,6);
		for (int j=0; j<room.getItemCount();j++)
		{
		      if (room.getItemAt(j).toString().equals(rm)) 
		      {
		        room.setSelectedIndex(j);
		        break;
		      }
		}
	}
	public void editdata()
	{
		int row = table.getSelectedRow();
		String pname=tname.getText();
		String ptmno=tmno.getText();
		String pgen=getSelectedButtonText(gengp);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String idd=sdf.format(idate.getDate());
		String odd=sdf.format(odate.getDate());
		String ptadd=tadd.getText();
		String prom=room.getSelectedItem().toString();
		try
		{
			row=row+1;
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement stm = con.createStatement();
			String query="update customer set Name='"+pname+"',Mobile='"+ptmno+"',Gender='"+pgen+"',CheckInDate='"+idd+"',CheckOutDate='"+odd+"',Address='"+ptadd+"',Room='"+prom+"' where ID="+row+";";
			stm.executeUpdate(query);
			con.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void resetall()
	{
		String def = "";
        tname.setText(def);
        tadd.setText(def);
        tmno.setText(def);
        idate.setDate(null);
        odate.setDate(null);
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