package com.NativeCubeDBMS.IIITB.NativeCubeDBMS;
import java.io.*;
import java.util.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class MetaDimGenerator
{
	public void genratedimMeta(Properties prop) throws Exception 
	{
		FileInputStream fs= new FileInputStream(new File(prop.getProperty("datafile")));
		XSSFWorkbook wb= new XSSFWorkbook(fs);
		for (int i = 1; i < wb.getNumberOfSheets(); i++)
		{
			XSSFSheet sheet = wb.getSheetAt(i);
			FileOutputStream fos;
			ObjectOutputStream wo;
			ArrayList<String> aRow= new ArrayList<String>();
			double addr;
			fos= new FileOutputStream(new File(prop.getProperty("dimensionInfoPath")+ wb.getSheetName(i)));
			wo= new ObjectOutputStream(fos);
			String rowstr="";
			for(Row row: sheet)
			{
				addr=row.getRowNum();
				if(addr==0)
				{continue;}
				rowstr="";
				for(Cell cell : row ) {rowstr=rowstr+cell.toString()+" ";}
				aRow.add(rowstr);
			}
			wo.writeObject(aRow);
			wo.close();
			fos.close();	
		}
		wb.close();
	 }
}

  
