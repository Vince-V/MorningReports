import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverManager;
import java.sql.Statement;

public class SingletonConnection {
	
//	private static Connection conn = null;
	private static Statement stmt = null;
	 
	final static String USERNAME = "YOUR_USERNAME";
	final static String PASSWORD_PROD = "yourpassword";
	
	private static String urlSAC = "jdbc:oracle:thin:@111.111.11.11:49125/ASERVER.COMPANYEMAIL.COM";
	
	private static SingletonConnection sc;// = new SingletonConnection();
	
	private SingletonConnection(){ 
		
	}
	
	public static SingletonConnection MySingletonConnection() {
		 	
			if (sc == null){
				synchronized(SingletonConnection.class) {
					if (sc == null)
						sc = new SingletonConnection();
				}
			}
					
		   return sc; 
	 }
 

   public static Statement dbConnect()	{
	try { 
		
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	
	stmt =  DriverManager.getConnection(urlSAC, USERNAME, PASSWORD_PROD).createStatement();
	}  catch (Exception e) {
		e.printStackTrace();
	} 	 /*finally{
		try {
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	  return stmt;
   }
} 
 
