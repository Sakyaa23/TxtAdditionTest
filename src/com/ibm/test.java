package com.ibm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class test {

    public static void main(String[] args) throws Exception {
    	String fileName = "C:\\Automation_L1\\SizeReports.xlsx";

        // load the workbook
        InputStream inp = new FileInputStream(fileName);
        Workbook wb = WorkbookFactory.create(inp);
        inp.close();

        // make some changes
        Sheet sh = wb.getSheetAt(0);
        Row r = sh.createRow(sh.getPhysicalNumberOfRows());
        int x=r.getRowNum();
        System.out.println(x);
        Cell c = r.createCell(0);
        c.setCellValue("Mail");
        c = r.createCell(1);
        c.setCellValue("Samanta2");
        c = r.createCell(2);
        c.setCellValue("ss3");
        Cell cell =sh.getRow(x-1).getCell(2);
        cell.setCellValue("Updated");

        // overwrite the workbook with changed workbook
        FileOutputStream fileOut = new FileOutputStream(fileName);
        wb.write(fileOut);
        fileOut.close();
        Tset_Email nw = new Tset_Email();
        nw.EmailNotification();
        testPath("C:\\Automation_L1");
    }
    private static void testPath(String Path_Details) throws IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy  hh:mm a");
		Date date = new Date();
		String currentDateTime=dateFormat.format(date);
		System.out.println("CurrentDate : "+currentDateTime);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, -10);
		Date newDateTime = c.getTime();
		String updatedDateTime=dateFormat.format(newDateTime);
		System.out.println("UpdatedDate : "+updatedDateTime);
		File file = new File(Path_Details);
		File[] fileList = file.listFiles();
		String ext_input1="";
		List<String> files = new ArrayList<String>();
		int count = 0;
		if(fileList!=null) {
			for(int i=0;i<fileList.length;i++) {
				//System.out.println(fileList[i]);
				long timee1 = fileList[i].lastModified();
				String timeInputFiles = dateFormat.format(timee1);
				String namee_input1 = fileList[i].getName().toString();
				System.out.println(namee_input1);
				int index_input1 = namee_input1.lastIndexOf(".");
				if(index_input1 >0) {
					ext_input1 = namee_input1.substring(index_input1+1);
				}else {
					ext_input1="";
				}
				if(timeInputFiles.compareTo(updatedDateTime)>0) {
					if(ext_input1!="" && !(ext_input1.equals("db")) && !(ext_input1.equals("lnk"))
							&& (ext_input1.equals("txt") || ext_input1.equals("pdf") || ext_input1.equals("sh"))) {
						count++;
						String name_input1 = fileList[i].getName().toString();	
						long timee_input1 = fileList[i].lastModified();
						String time1 = dateFormat.format(timee_input1);
						String final_input1 = name_input1+"      "+time1;
						files.add(final_input1);
						//System.out.println("Count and name : "+count+"    "+name_input1);
					}
				}

			}
		}
	}
}