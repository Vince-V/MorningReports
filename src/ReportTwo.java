/*
 * 3/17/17 - Added Singleton reference
 */
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.swing.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import oracle.jdbc.pool.OracleDataSource;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.BigInteger;

public class ReportTwo {
	 
	private static ResultSetMetaData queryMetaData2;

	private static Statement stmt = null;
 
	private static BigDecimal d;
 
	private static LinkedHashMap<String, String> connectionMap = new LinkedHashMap<String, String>();

	private static Set<String> keys;

	private static String day = null;

	private static XSSFWorkbook workbook = new XSSFWorkbook();
	private static XSSFSheet sheet = workbook.createSheet("Exception Report");
	private static FileOutputStream out;
	private static DataFormat df = workbook.createDataFormat();
	private static CellStyle cs = workbook.createCellStyle();

	private static XSSFCellStyle timeStampstyle;// = setTimestamp(xwb);

	private static DateTest dateCheck;
	public static ProdLoadDataTuesThruFriQueries mdq;
	public static ProdLoadDataMonQueries mdqMon;

	private static String query = null;

//	 public static void main(String[] args) {
	public ReportTwo() {

		dateCheck = new DateTest();
		day = dateCheck.isDateSatThruMon();

		System.out.println("Start Exception program");

		try {
			if (day.equalsIgnoreCase("TuesThruFri")) {
				 
				mdq = new ProdLoadDataTuesThruFriQueries();
				connectionMap.put("dbQuery1", mdq.exceptionQuery());

			} else {
//				System.out.println(day);
				mdqMon = new ProdLoadDataMonQueries();
				connectionMap.put("dbQuery1", mdqMon.exceptionQuery());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		keys = connectionMap.keySet();
		dbSearch();
		System.out.println(query);
	} // end main

	public static void dbSearch() {
		ResultSet rs = null;

		try {
 
			String columnName;

			for (String key : keys) {
				
//				SingletonConnection singletonInstance = SingletonConnection.MySingletonConnection();
//				System.out.println("Location from Exceptionfile  "+  SingletonConnection.MySingletonConnection());	
//	 				System.out.println(connectionMap.get(key));
	 			
	 			rs = SingletonConnection.MySingletonConnection().dbConnect().executeQuery(connectionMap.get(key));	
//				rs = singletonInstance.dbConnect().executeQuery(connectionMap.get(key));
	 			
				queryMetaData2 = rs.getMetaData();// new
				int ownerColumns = queryMetaData2.getColumnCount();
				System.out.println();
				int rownum = 1;
				int columnNameRownum = 0;

				writeData(rs, sheet, workbook);
				while (rs.next()) {
					 
					int cellnum = 0;
					Row row = sheet.createRow(rownum++);
					Row columnNameRow = sheet.createRow(columnNameRownum);
					for (int i = 1; i <= ownerColumns; i++) {
						int j = i - 1;
						int k = i - 1;
						Cell cell = columnNameRow.createCell(j++);
						cell.setCellValue(queryMetaData2.getColumnName(i));
						System.out.printf("%-9s\t",
								queryMetaData2.getColumnName(i));
						cell = row.createCell(k++);
 
							if (rs.getObject(i) instanceof String) {
							cell.setCellValue((String) rs.getObject(i));
							System.out.println((String) rs.getObject(i));
						}
						else if (rs.getObject(i) instanceof Date) {
							cell.setCellValue((Date) rs.getDate(i));
							System.out.println((Date) rs.getDate(i));
						}
						else if (rs.getObject(i) instanceof Number) {
							cell.setCellValue((String) rs.getObject(i));// getNumber(i));
							System.out.println((String) rs.getObject(i));
						}//temp
						else if (rs.getObject(i) instanceof Double) {
							cell.setCellValue((Double) rs.getObject(i));
							System.out.print("Double type!");
						} else if (rs.getObject(i) instanceof Long) {
							cell.setCellValue((Long) rs.getObject(i));
							System.out.print("Long type!");
						} else if (rs.getObject(i) instanceof Float) {
							cell.setCellValue((Float) rs.getObject(i));
							System.out.print("Float type!");
						} else if (rs.getObject(i) instanceof BigInteger) {
							cell.setCellValue((Double) rs.getObject(i));
							System.out.print("BigInteger type!");
						} else if (rs.getObject(i) instanceof java.math.BigDecimal) {
			
							cell.setCellValue((String) rs.getObject(i)
									.toString());
							cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
							System.out.print("java.math.BigDecimal type!");
							// cell.setCellStyle(cs);

						} else if (rs.getObject(i) instanceof BigInteger) {
							cell.setCellValue(new Date());
							cell.setCellStyle(timeStampstyle);
							System.out.print("BigInteger type!");
						} else
							cell.setCellValue((String) rs.getObject(i)
									.toString());
						cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
						System.out.print(rs.getObject(i).getClass() + " type!");
 					}
					
				} // end while
				out = new FileOutputStream(
						new File(
								"C:\\Users\\Documents\\Reports\\Prod Data Load Exception log.xlsx"));
				workbook.write(out);
				out.close();
				System.out.println("excels written successfully");
 
					String tmpColName = null;
				 
					System.out.println("Column count is " + ownerColumns);
	 
			}// end for loop
 
 		} catch (SQLException sqle) {
			sqle.printStackTrace();
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
 
		} // end finally
	} // end executable logic

 
	public static void writeData(ResultSet re, XSSFSheet xs, XSSFWorkbook xwb)
			throws SQLException, IOException {
		int j = 1;
		ResultSet firstData = re;
		XSSFSheet sheet = xs;

		ResultSetMetaData metadata = firstData.getMetaData();
		int columnCount = metadata.getColumnCount();
		XSSFRow row = sheet.createRow(0);
		XSSFCellStyle headerStyle = setHeaderStyle(xwb);
		XSSFCellStyle borderStyle = setBorderStyle(xwb);
		XSSFCellStyle timeStampstyle = setTimestamp(xwb);
		for (int i = 0; i < columnCount; i++) {
			XSSFCell cell = row.createCell(i);
			cell.setCellStyle(headerStyle);
			cell.setCellValue(metadata.getColumnName(i + 1));
		}
		while (firstData.next()) {
			row = sheet.createRow(j);
			for (int k = 0; k < columnCount; k++) {
				XSSFCell cell = row.createCell(k);
				if (k == 4) {
					if (firstData.getTimestamp(k + 1) != null) {
						cell.setCellStyle(timeStampstyle);
						cell.setCellValue(firstData.getTimestamp(k + 1));
					} else {
						cell.setCellStyle(borderStyle);
						cell.setCellValue(firstData.getString(k + 1));

					}
				} else {
					cell.setCellStyle(borderStyle);
					cell.setCellValue(firstData.getString(k + 1));
				}
			}
			j++;
		}
		for (int columnPosition = 0; columnPosition < 26; columnPosition++) {
			sheet.autoSizeColumn(columnPosition);
		}
	}

	private static XSSFCellStyle setBorderStyle(XSSFWorkbook sampleWorkBook) {
		XSSFCellStyle cellStyle = sampleWorkBook.createCellStyle();
		cellStyle.setWrapText(false);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setAlignment((short) 2);

		return cellStyle;
	}

	private static XSSFCellStyle setHeaderStyle(XSSFWorkbook sampleWorkBook) {
		XSSFFont font = sampleWorkBook.createFont();

		font.setColor(IndexedColors.BLACK.getIndex());
		font.setBold(true);

		XSSFCellStyle cellStyle = sampleWorkBook.createCellStyle();
		cellStyle = setBorderStyle(sampleWorkBook);
		cellStyle.setFont(font);
		cellStyle.setWrapText(false);

		cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT
				.getIndex());
		cellStyle.setAlignment((short) 2);
		cellStyle.setFillPattern((short) 1);

		return cellStyle;
	}

	private static XSSFCellStyle setTimestamp(XSSFWorkbook sampleWorkBook) {
		CreationHelper creationHelper = sampleWorkBook.getCreationHelper();
		XSSFCellStyle cellStyle = sampleWorkBook.createCellStyle();
		cellStyle = setBorderStyle(sampleWorkBook);
		cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat(
				"MM/dd/yyyy HH:mm:ss AM/PM"));
		return cellStyle;
	}

}
