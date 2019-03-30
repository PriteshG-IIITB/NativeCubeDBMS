package com.NativeCubeDBMS.IIITB.NativeCubeDBMS;

import java.io.*;
import java.util.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

public class Load
{
	
	public void createBase(String dataFile)throws Exception
	{
		FileInputStream fs= new FileInputStream(new File(dataFile+".xlsx"));
		XSSFWorkbook wb= new XSSFWorkbook(fs);
		XSSFSheet sheet = wb.getSheetAt(0);
		FileOutputStream fos;ObjectOutputStream wo; 
		try 
		{	
			ArrayList<String> aRow;double addr;
			for(Row row :sheet)
			{
				addr=row.getRowNum();
				if(addr==0)
				{
					for (Cell cell :row)
					{createDimension(cell.getColumnIndex(),dataFile);}
					continue;
				}
				fos= new FileOutputStream(new File("db_"+dataFile+"/base/bd"+addr));
				wo= new ObjectOutputStream(fos);
				aRow= new ArrayList<String>();
				for (Cell cell :row)
				{	
					aRow.add(cell.toString());
				}
				wo.writeObject(aRow);wo.close();fos.close();
			}
		} catch (Exception e) 
		{e.printStackTrace();}
		finally
		{fs.close();wb.close();}	
	}
	
	private void createDimension(int columnIndex,String dataFile) throws Exception
	{
		FileInputStream fs= new FileInputStream(new File(dataFile+".xlsx"));
		XSSFWorkbook wb= new XSSFWorkbook(fs);
		XSSFSheet sheet = wb.getSheetAt(0);
		FileOutputStream fos= new FileOutputStream(new File("db_"+dataFile+"/dimensions/dim"+columnIndex));
		ObjectOutputStream wo= new ObjectOutputStream(fos);
		try
		{
			HashMap<String,LinkedList<Double>> dimr= new HashMap<String,LinkedList<Double>>();
			LinkedList<Double>listAddr=new LinkedList<Double>();
			double addr;String key;
			for(Row row :sheet)
			{
				addr=row.getRowNum();
				if(addr==0) {continue;}
				key=row.getCell(columnIndex).toString();
				if(dimr.containsKey(key))
				{listAddr=dimr.get(key);listAddr.add(addr);dimr.put(key, listAddr);}
				else {listAddr=new LinkedList<Double>();listAddr.add(addr);dimr.put(key,listAddr);}
			}
			wo.writeObject(dimr);			
		}
		catch (Exception e)
		{e.printStackTrace();}
		finally
		{wo.close();fos.close();wb.close();fs.close();}		
	}
		
}
