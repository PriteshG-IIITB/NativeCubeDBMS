package com.NativeCubeDBMS.IIITB.NativeCubeDBMS;
import java.io.*;
import java.util.*;

public class ReadData 
{
	public void fetch(Properties prop) throws Exception
	{
		HashMap<Integer,String> dimHsh = new HashMap<Integer,String>();
		//this is sample input given in dimHSh in column no. & column name we need schema file here
		dimHsh.put(0, "R1");dimHsh.put(1, "C1");dimHsh.put(4, "P1");
		List<Double> addrList= new LinkedList<Double>();
		for(int key:dimHsh.keySet())
		{
			if(addrList.isEmpty()){addrList=getAddr(dimHsh.get(key),key,prop);}
			else{addrList.retainAll(getAddr(dimHsh.get(key),key,prop));}
		}
	    getFact(addrList,prop);
	}
	private void getFact(List<Double> addrList, Properties prop)throws Exception 
	{
		FileInputStream fs;ObjectInputStream os;
		fs=new FileInputStream(prop.getProperty("basePath")+"1");
		os= new ObjectInputStream(fs);
		HashMap<Double,ArrayList<String>> baser= (HashMap<Double,ArrayList<String>>)os.readObject();
		ArrayList<String> factRow;
		for(double d:addrList)
		{
			factRow=baser.get(d);
			System.out.println(factRow.toString());
		}
		os.close();fs.close();
	}
	private List<Double> getAddr(String key, int i, Properties prop)throws Exception
	{
		HashMap<String, LinkedList<Double>> dim=readDim(prop.getProperty("dimensionsPath")+i);
		List<Double> addrLst=dim.get(key);
		return addrLst;
	}
	private HashMap<String, LinkedList<Double>> readDim(String fname) throws Exception
	{	
		File file = new File(fname);
	    FileInputStream f = new FileInputStream(file);
	    ObjectInputStream s = new ObjectInputStream(f);
	    HashMap<String, LinkedList<Double>> dim = (HashMap<String, LinkedList<Double>>)s.readObject();
	    s.close();f.close();
	    return dim;
	}
}
