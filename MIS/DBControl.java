import java.sql.*;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
public class DBControl {
    static Connection con;

    public DBControl() {
        con = DBConnection.getConnection();
        
    }

    // Vraca rezultate posle izvrsavanja sql komande
    public static CachedRowSet returnSet(String sql) {
        try{  
            Statement stmt = con.createStatement();  
            ResultSet rs=stmt.executeQuery(sql);  

            // while(rs.next())  
            //     System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  

            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet rowset = factory.createCachedRowSet();
            rowset.populate(rs);
             
            return rowset;
        }
        catch(Exception e)
        {
            System.out.println(e);
        } 
        return null;
    }

    //Izvrsava Sql i vraca da li je uspeo ili ne
    public static boolean executeSql(String sql) {
        try{  
            Statement stmt = con.createStatement();  
            stmt.executeUpdate(sql);  

            con.close();  
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e);
        } 
        return false;
    }
    
}