/*
 * 3/17/17 - Added Singleton reference
 */
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
 
 



import oracle.jdbc.pool.OracleDataSource;

import java.awt.*;
import java.awt.event.*;

public class ReportThree {
 
	private static long startTime;
	private static long endTime;
	private static long responseTimeinms;
	private static ResultSetMetaData queryMetaData2;
 
	final static String USERNAME = "YOUR_USERNAME";
	final static String PASSWORD_PROD = "yourPassword";
	
	private static LinkedHashMap<String, String> connectionMap = new LinkedHashMap<String, String>();

	private static File newfile;
	private static Desktop desktop;
 
	private static Set<String> keys;

	private static DateTest dateCheck;
	private static String day = null;
	public static ProdLoadDataTuesThruFriQueries mdq;
	public static ProdLoadDataMonQueries mdqMon;
 
    public ReportThree (){

//	public static void main(String[] args) {
		dateCheck = new DateTest();
		day = dateCheck.isDateSatThruMon();
	 
		System.out.println("start ProdLoadData report");
		
		try {
			
			if (day.equalsIgnoreCase("TuesThruFri")) {
				 
				mdq = new ProdLoadDataTuesThruFriQueries();	
 
				connectionMap.put("dbQuery0", mdq.returnQueryEmails());
				connectionMap.put("dbQuery1", mdq.returnQuery());
				connectionMap.put("dbQuery2", mdq.returnQuery2());
				connectionMap.put("dbQuery3", mdq.returnQuery3());
				connectionMap.put("dbQuery4", mdq.returnQuery4());
				connectionMap.put("dbQuery5", mdq.returnQuery5());
				connectionMap.put("dbQuery6", mdq.returnQuery6());
				connectionMap.put("dbQuery7", mdq.returnQuery7());
				connectionMap.put("dbQuery8", mdq.returnQuery8());
				connectionMap.put("dbQuery9", mdq.returnQuery9());
				connectionMap.put("dbQuery10", mdq.returnQuery10());
				connectionMap.put("dbQuery11", mdq.returnQuery11());
			} else {
				  
				 mdqMon = new ProdLoadDataMonQueries();
					connectionMap.put("dbQuery0", mdqMon.returnQueryEmails());
					connectionMap.put("dbQuery1", mdqMon.returnQuery());
					connectionMap.put("dbQuery2", mdqMon.returnQuery2());
					connectionMap.put("dbQuery3", mdqMon.returnQuery3());
					connectionMap.put("dbQuery4", mdqMon.returnQuery4());
					connectionMap.put("dbQuery5", mdqMon.returnQuery5());
					connectionMap.put("dbQuery6", mdqMon.returnQuery6());
					connectionMap.put("dbQuery7", mdqMon.returnQuery7());
					connectionMap.put("dbQuery8", mdqMon.returnQuery8());
					connectionMap.put("dbQuery9", mdqMon.returnQuery9());
					connectionMap.put("dbQuery10", mdqMon.returnQuery10());
					connectionMap.put("dbQuery11", mdqMon.returnQuery11());
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		keys = connectionMap.keySet();
		dbSearch();
	} // end main

public static void dbSearch() {
		ResultSet rs = null;
 
		try {

			String html ="";
 
				String columnName;
 
				for (String key : keys) {
					

					startTime = System.currentTimeMillis();
  
					ResultSetMetaData queryMetaData;// new
					int tableColumns; // new
 
					rs = SingletonConnection.MySingletonConnection().dbConnect().executeQuery(connectionMap.get(key));
					queryMetaData2 = rs.getMetaData();// new
					int ownerColumns = queryMetaData2.getColumnCount();
 
					int keyCount = 0;
					do {
						String tmpColName = null;
 
						if (!rs.next()) {
 
							keyCount++;
 
						} // end !rs.next()
						else {
							int working = 0;	
							for (int i = 1; i <= ownerColumns; i++) {

								working ++;

								html += rs.getObject(i) + "\n";
							}

						} // end else
					} while (rs.next());// end while

					endTime = System.currentTimeMillis();
					responseTimeinms = (endTime - startTime);

 					newfile = new File("C:\\Users\\Documents\\Reports\\Prod_Data_Load.html");
					OutputStream htmlfile = new FileOutputStream(newfile);

					PrintStream printhtml = new PrintStream(htmlfile);
					
					printhtml.println(html);

				}// end for loop
 
 
			if (!Desktop.isDesktopSupported()) {
				System.out.println("Desktop is not supported");
				return;
			} else {
				try {
 
					File newtxtfile = new File("C:\\Users\\Documents\\Reports\\Prod_Data_Load.html");
 
					desktop.getDesktop().open(newfile);

				} catch (Exception e) {
					e.printStackTrace();
				}
				;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != rs) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
//			if (null != conn) {
//				try {
//					conn.close();
// 
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
		}  // end finally
	} // end executable logic

}
