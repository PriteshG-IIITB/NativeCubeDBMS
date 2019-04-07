package com.NativeCubeDBMS.IIITB.NativeCubeDBMS;

import java.io.*;
import java.util.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

public class Load
{
	public void createBase(Properties prop)throws Exception
	{
		FileInputStream fs= new FileInputStream(new File(prop.getProperty("datafile")));
		XSSFWorkbook wb= new XSSFWorkbook(fs);
		XSSFSheet sheet = wb.getSheetAt(0);
		FileOutputStream fos;ObjectOutputStream wo;int filenos=1; 
		fos= new FileOutputStream(new File(prop.getProperty("basePath")+filenos));filenos++;
		wo= new ObjectOutputStream(fos);
		try 
		{	
			HashMap<Double,ArrayList<String>> baser= new HashMap<Double,ArrayList<String>>();
			ArrayList<String> aRow;double addr,keys=0;
			for(Row row :sheet)
			{
				addr=row.getRowNum();
				if(addr==0)
				{
					for (Cell cell :row)
					{createDimension(cell.getColumnIndex(),prop);}
					continue;
				}
				aRow= new ArrayList<String>();
				for (Cell cell :row)
				{aRow.add(cell.toString());}
				if(keys<=Double.parseDouble(prop.getProperty("hashMapLimit")))
				{baser.put(addr, aRow);keys++;}
				else 
				{
					wo.writeObject(baser);wo.close();fos.close();
					fos= new FileOutputStream(new File(prop.getProperty("basePath")+"base"+filenos));filenos++;
					wo= new ObjectOutputStream(fos);
					baser= new HashMap<Double,ArrayList<String>>();
					baser.put(addr, aRow);keys=1;
				}
			}
			wo.writeObject(baser);wo.close();fos.close();
		} catch (Exception e) 
		{e.printStackTrace();}
		finally
		{fs.close();wb.close();}	
	}
	
	private void createDimension(int columnIndex,Properties prop) throws Exception
	{
		FileInputStream fs= new FileInputStream(new File(prop.getProperty("datafile")));
		XSSFWorkbook wb= new XSSFWorkbook(fs);
		XSSFSheet sheet = wb.getSheetAt(0);
		FileOutputStream fos= new FileOutputStream(new File(prop.getProperty("dimensionsPath")+columnIndex));
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
