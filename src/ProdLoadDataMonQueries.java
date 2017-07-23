
public class ProdLoadDataMonQueries {
 
	public String returnQueryEmails(){
		
		return "select '<table><TR>To:<br /> recipient1@emailaddress.com,recipient2@emailaddress.com,recipient3@emailaddress.com,recipient4@emailaddress.com,recipient5@emailaddress.com,dg.emailgroup@emailaddress.com,dg.emailgroup2@emailaddress.com,\r\n" + 
				"dg.emailgroup3@emailaddress.com,lastemailrecepient@emailaddress.com</TR></table>' from dual";
	}
	public String returnQuery(){
		
		return "select * from (SELECT '<HTML><HEAD><style type=\"text/css\">table.MsoNormalTable {   font-size: 10.0pt;    font-family: \"sans-serif\" , \"serif\";}\r\n" + 
				"p.MsoNormal {   margin-bottom: .0001pt;  font-size: 10.0pt;   font-family: \"Calibri\" , \"sans-serif\";   margin-left: 0in;   margin-right: 0in;   margin-top: 0in;} </style>\r\n" + 
				"</HEAD><body><center><p class=\"MsoNormal\"><span style=\"font-size: 16.0pt; color: black\"> </br></span></p>' FROM dual)";
	}
	public String returnQuery2(){

		return "SELECT '<table border=0 cellpadding=0 cellspacing=0 class=\"MsoNormalTable\">' from dual";
	}
	public String returnQuery3(){

		return "select '<tr style=\"height:18.75pt\">\r\n" + 
				"<th colspan=\"2\"; style=\"width: 700.0pt; border-top: solid windowtext 1.0pt; border-left: solid windowtext 1.0pt;border-bottom: solid windowtext 1.0pt; border-right: solid black 1.0pt; background: #6495ED;padding: 0in 5.4pt 0in 5.4pt;\" valign=\"bottom\"><p align=\"center\" class=\"MsoNormal\" style=\"text-align: center\"><b><span style=\"font-size: 12.0pt;\">\r\n" + 
				"Prod Data Load for ' ||TO_CHAR(sysdate-3, 'MM/DD')|| '<br></br>\r\n" + 
				"Your Organization's Report \r\n" + 
				"</tr>\r\n" + 
				"'FROM dual";
	}
	public String returnQuery4(){

		return "select '<tr style=\"height:18.75pt\">\r\n" + 
				"<td style=\"width: 30.0pt; border: solid windowtext 1.0pt; border-top: none;padding: 0in 5.4pt 0in 5.4pt; height: 12.75pt\" valign=\"bottom\"><p class=\"MsoNormal\"><span style=\"font-size: 10.0pt;\">'||'Application Name/ID:'||'</span></p></td>\r\n" + 
				"<td style=\"width: 30.0pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt;border-right: solid windowtext 1.0pt; background: white; padding: 0in 5.4pt 0in 5.4pt;height: 12.75pt\" valign=\"bottom\" width=\"93\"><p class=\"MsoNormal\"><span style=\"font-size: 10.0pt; color: black\">'||'AML Management of Cases (AIT: 29091)'||'</span></p></td>\r\n" + 
				"</tr>'\r\n" + 
				"FROM dual";
	}
	public String returnQuery5(){
		return "select '<tr style=\"height:18.75pt\">\r\n" + 
				"<td style=\"width: 30.0pt; border: solid windowtext 1.0pt; border-top: none;padding: 0in 5.4pt 0in 5.4pt; height: 12.75pt\" valign=\"bottom\"><p class=\"MsoNormal\"><span style=\"font-size: 10.0pt;\">'||'Production  Load Completion:'||'</span></p></td>\r\n" + 
				"<td style=\"width: 30.0pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt;border-right: solid windowtext 1.0pt; background: white; padding: 0in 5.4pt 0in 5.4pt;height: 12.75pt\" valign=\"bottom\" width=\"93\"><p class=\"MsoNormal\"><span style=\"font-size: 10.0pt; color: black\">'||'Load completed successfully for first run on  '||''||TO_CHAR(sysdate-3, 'MM/DD')||' ET</span></p></td>\r\n" + 
				"</tr>'\r\n" + 
				"FROM dual";}
	
	public String returnQuery6(){
		return "select '<tr style=\"height:18.75pt\">\r\n" + 
				"<td style=\"width: 30.0pt; border: solid windowtext 1.0pt; border-top: none;padding: 0in 5.4pt 0in 5.4pt; height: 12.75pt\" valign=\"bottom\"><p class=\"MsoNormal\"><span style=\"font-size: 10.0pt;\">'||'Production Load as of Date:'||'</span></p></td>\r\n" + 
				"<td style=\"width: 30.0pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt;border-right: solid windowtext 1.0pt; background: white; padding: 0in 5.4pt 0in 5.4pt;height: 12.75pt\" valign=\"bottom\" width=\"93\"><p class=\"MsoNormal\"><span style=\"font-size: 10.0pt; color: black\">'||''||''||TO_CHAR(sysdate-3, 'MM/DD/YYYY')||' ET</span></p></td>\r\n" + 
				"</tr>'\r\n" + 
				"FROM dual";}
	public String returnQuery7(){return "select '<tr style=\"height:18.75pt\">\r\n" + 
			"<td style=\"width: 30.0pt; border: solid windowtext 1.0pt; border-top: none;padding: 0in 5.4pt 0in 5.4pt; height: 12.75pt\" valign=\"bottom\"><p class=\"MsoNormal\"><span style=\"font-size: 10.0pt;\">'||'Production Load Information:'||'</span></p></td>\r\n" + 
			"<td style=\"width: 30.0pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt;border-right: solid windowtext 1.0pt; background: white; padding: 0in 5.4pt 0in 5.4pt;height: 12.75pt\" valign=\"bottom\" width=\"93\"><p class=\"MsoNormal\"><span style=\"font-size: 10.0pt; color: black\">' ||'Number_of_New_Cases_loaded: '||(select sum(new_cases_count)from user_schema.table1 WHERE start_time >  TRUNC(SYSDATE - 3) and start_time <  TRUNC(SYSDATE))|| ' </p></br>\r\n" + 
			"<span style=\"font-size: 10.0pt; color: black\">'||'Number_of_Manual_Events_loaded: '||''||(select count(1) from user_schema2.TABLE2 where SRC_SYS_ID = 'IDField')||'</span></p></td></tr>'\r\n" + 
			"FROM dual";}
	public String returnQuery8(){
		return "select '<tr style=\"height:18.75pt\">\r\n" + 
				"<td style=\"width: 30.0pt; border: solid windowtext 1.0pt; border-top: none;padding: 0in 5.4pt 0in 5.4pt; height: 12.75pt\" valign=\"bottom\"><p class=\"MsoNormal\"><span style=\"font-size: 10.0pt;\">'||'Contact Information:'||'</span></p></td>\r\n" + 
				"<td style=\"width: 30.0pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt;border-right: solid windowtext 1.0pt; background: white; padding: 0in 5.4pt 0in 5.4pt;height: 12.75pt\" valign=\"bottom\" width=\"93\"><p class=\"MsoNormal\"><span style=\"font-size: 10.0pt; color: black\">' ||'For questions about this daily load or other questions please notify '|| ' </p></br>\r\n" + 
				"<span style=\"font-size: 10.0pt; color: black\">' ||'support@emailaddress.com'|| '</span></p></td>\r\n" + 
				"</tr>\r\n" + 
				"'\r\n" + 
				"FROM dual,\r\n" + 
				"user_schema.table1 \r\n" + 
				"WHERE start_time >  TRUNC(SYSDATE - 2)\r\n" + 
				"and start_time <  TRUNC(SYSDATE) and rownum = 1";}
	public String returnQuery9(){
		return "select '<tr style=\"height:18.75pt\">\r\n" + 
				"<th colspan=\"2\"; style=\"width: 700.0pt;  border-left: solid windowtext 1.0pt;border-bottom: solid windowtext 1.0pt; border-right: solid black 1.0pt; padding: 0in 5.4pt 0in 5.4pt;\" valign=\"bottom\"><p align=\"center\" class=\"MsoNormal\" style=\"text-align: center\"><b><span style=\"font-size: 10.0pt;\">\r\n" + 
				"' ||'Please Forward This Message to Interested Stakeholders'|| '<br>\r\n" + 
				"</tr>\r\n" + 
				"'FROM dual";}
	public String returnQuery10(){
		return "select '</table>' as b from dual";}
	public String returnQuery11(){
		return "select '</center></body>' as b from dual";}
	
	public String exceptionQuery(){
		return "SELECT SLE.*\r\n" + 
				"  FROM user_schema2.TABLE3_BATCH_LOG SBL , \r\n" + 
				"            user_schema2.TABLE4_LOAD_EXCPTN SLE\r\n" + 
				"WHERE SBL.BATCH_ID = SLE.STG_BATCH_ID\r\n" + 
				"     AND SBL.START_TS > (SYSDATE - 3)\r\n" + 
				"     ORDER BY SBL.START_TS, LD_EXCPTN_ID";}
 
public String RefreshQuery(){
	return "select * from user_schema2.table4_view_analysis ORDER BY  FULLREFRESHTIM desc";
	}
}
