import java.sql.*;

public class DBHelper2
{
	public static Statement st;
	public static Connection con;
	public static PreparedStatement getData;
	
	static
	{
		try
		{
			String url = "jdbc:mysql://localhost:3306/hotelmanagementsystem?autoReconnect=true&useSSL=false";
			String user="root";
			String pass="";
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement stm = con.createStatement();
			getData=con.prepareStatement("select * from checkin where Name like ?"); 
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static ResultSet getData(String name)throws SQLException
	{
		getData.setString(1,"%"+name+"%");
		return getData.executeQuery();
	}

}
