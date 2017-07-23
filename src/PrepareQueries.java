	//Adding header gui pop up
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
import java.util.Set;

import javax.swing.*;

import oracle.jdbc.pool.OracleDataSource;

import java.awt.*;
import java.awt.event.*;

public class PrepareQueries {
 
	private static long startTime;
	private static long endTime;
	private static long responseTimeinms;
	private static ResultSetMetaData queryMetaData2;

	//
	private static Connection conn = null;

	private static Connection sacConn = null;
	
	private static Statement stmt = null;

	final static String USERNAME = "YOUR_USERNAME";
	final static String PASSWORD_PROD = "yourPassword";
	
	private static HashMap<String, Connection> connectionMap = new HashMap<String, Connection>();
 
	private static String urlSAC = "jdbc:oracle:thin:@111.111.11.11:8080/emai.company.COM";
	
	private static File newfile;
	private static Desktop desktop;
 
	private static JFileChooser fc;
	private static JButton openButton;
	private static JButton searchButton;
	private static JTextField tableField;

	private static final String nextline = "\n";

	private static Set<String> keys;

	private static String newPath = null;
	private static String tableColumnSearch = null;

	// put values in table

	public static void main(String[] args) {
		GuiPopUp gp = new GuiPopUp();
		gp.createAndShowGUI();

		System.out.println("start program");

		try {
			sacConn = DriverManager.getConnection(urlSAC, USERNAME,
					PASSWORD_PROD);
			connectionMap.put("SAC", sacConn);
			/* */
		} catch (Exception e) {
			e.printStackTrace();
		}
		keys = connectionMap.keySet();

	} // end main

public static void dbSearch() {
		ResultSet rs =  null;

		try {
	
			String path = "src//ObjectSearch.txt";

			FileInputStream fstreamQuery = new FileInputStream(newPath);
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Get the object of DataInputStr3eam
			DataInputStream in = new DataInputStream(fstreamQuery);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			String html = "<HTML><HEAD><style type=\"text/css\">table.MsoNormalTable {   font-size: 10.0pt;font-family: \"sans-serif\" , \"serif\";} p.MsoNormal {   margin-bottom: .0001pt;  font-size: 10.0pt;   font-family: \"Calibri\" , \"sans-serif\";   margin-left: 0in;   margin-right: 0in;   margin-top: 0in;} </style> </HEAD><body><center><p class=\"MsoNormal\"><span style=\"font-size: 16.0pt; color: black\"> </br></span></p>";
			html += "<table border=0 cellpadding=0 cellspacing=0 class=\"MsoNormalTable\">";
			boolean headersExist = false;

			while ((strLine = br.readLine()) != null) {
				String columnName;
				columnName = strLine;

				for (String key : keys) {

					stmt = connectionMap.get(key).createStatement();
					System.out.println("From " + key + " db");

					String dbQuery = "select sysdate from dual";
 
					startTime = System.currentTimeMillis();
					PreparedStatement pstmt = connectionMap.get(key)
							.prepareStatement(dbQuery);
 
					rs = pstmt.executeQuery();
					queryMetaData2 = rs.getMetaData();// new
					int ownerColumns = queryMetaData2.getColumnCount();
	 
					if (!headersExist) {

						for (int i = 1; i <= ownerColumns; i++) {
		 
							html += "<th style = \" background-color:orange; border:1px solid black; padding: 2px;\">"
									+ queryMetaData2.getColumnName(i) + "</th>";

						}

						html += "</tr></thead><tbody>";
						headersExist = true;
					}

					System.out.println("Column is " + columnName);

					html += "<tr>";
					int keyCount = 0;
					do {
						String tmpColName = null;
			 
						System.out.println("Object count is " + ownerColumns);
						if (!rs.next()) {
 
					 
							for (int i = 2; i <= ownerColumns; i++) {

								tmpColName = columnName;
					 
								if (i == 4) {
									html += "<td style = \" border:1px solid black; padding: 2px; \">"
											+ columnName + "</td>";
								}

								html += "<td style = \" border:1px solid black; padding: 2px; \">"
										+ "N/A" + "</td>";
							}

						} // end !rs.next()
						else {
 
							for (int i = 1; i <= ownerColumns; i++) {
 
								System.out.printf("%-9s\t", rs.getObject(i));

								System.out.println();

								html += "<td style = \" border:1px solid black; padding: 2px; \">"
										+ rs.getObject(i) + "</td>";

							}

						} // end else
					} while (rs.next());// end while

					endTime = System.currentTimeMillis();
					responseTimeinms = (endTime - startTime);

	 				newfile = new File("C:\\temp\\" + "ObjectAddSearch.html");
					OutputStream htmlfile = new FileOutputStream(newfile);

					PrintStream printhtml = new PrintStream(htmlfile);
					printhtml.println(html);

				}// end for loop

			}// end while
			html += "</tbody></table></html>";

			if (!Desktop.isDesktopSupported()) {
				System.out.println("Desktop is not supported");
				return;
			} else {
				try {
 
					File newtxtfile = new File("C:\\" + "ObjectAddSearch.html");
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
			if (null != conn) {
				try {
					conn.close();
					sacConn.close();
 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}  // end finally
	} // end executable logic

public static class GuiPopUp extends JPanel implements ActionListener {

 

	public String createAndShowGUI() {
		// initiate file chooser
		fc = new JFileChooser();
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Object Release Validation");
		// frame.setSize(400, 100);
		frame.setPreferredSize(new Dimension(350, 250));
		openButton = new JButton("Open txt file with list of Schemas");
		openButton.addActionListener(this);

		searchButton = new JButton("Click to search");
		searchButton.addActionListener(this);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pane = new JPanel(new GridLayout(0, 1));

		tableField = new JTextField(10);
		JLabel label2 = new JLabel("Search for objects in Owners in txt file");

		pane.add(openButton);
		pane.add(label2);
 
		pane.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		frame.getContentPane().add(pane);
		frame.pack();
		frame.setVisible(true);


		return newPath;

	}

	public void actionPerformed(ActionEvent e) {

		// Handle open button action.
		if (e.getSource() == openButton) {
			int returnVal = fc.showOpenDialog(GuiPopUp.this);
		 
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();

				newPath = file.getPath();

				System.out.println("newPath is: " + newPath + "."
						+ nextline);
				dbSearch();

			} else {
				System.out.println("Open command cancelled by user."
						+ nextline);
			}

			// Handle save button action.
		} else if (e.getSource() == searchButton) {

				tableColumnSearch = tableField.getText();
			if (tableColumnSearch != null) {
				dbSearch();

			} else {
				// log.append("Save command cancelled by user." + nextline);
			}
 
		}
	}

} // end GuiPopUp

}
