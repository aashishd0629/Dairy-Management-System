package guidesign;
import java.sql.*;

public class Dbconn {
public static Connection con;
public static Statement s;
static {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dairydata", "root",
				"Aashish1244@");
		s= con.createStatement();
	}
	catch(Exception ee) {
}
	
}
}
