import java.sql.*;
public class DBConnection {		
	public static Connection getConnection() {
		return conn;
	}	
	public static void closeConnection() {
		try{
		 conn.close();
		}
		catch(SQLException e){
		 System.out.println("Neuspesno zatvaranje konekcije");
		 e.printStackTrace();
		} 
	}	
	private static Connection conn = null;	
	static{
		try{
			//Class.forName("com.mysql.jdbc.Driver");
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Teretana", "root", "user");
		}
		catch(Exception e){
		 System.out.println("Database down");
		 e.printStackTrace();
		}
	}
}