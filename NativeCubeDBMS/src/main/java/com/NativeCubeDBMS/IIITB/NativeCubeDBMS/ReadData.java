package com.NativeCubeDBMS.IIITB.NativeCubeDBMS;
import java.io.*;
import java.util.*;

public class ReadData 
{
	public void fetch(String dataFile) throws Exception
	{
		HashMap<Integer,String> dimHsh = new HashMap<Integer,String>();
		//this is sample input given in dimHSh in column no. & column name we need schema file here
		dimHsh.put(0, "P1");dimHsh.put(2, "C1");dimHsh.put(3, "S2");
		List<Double> addrList= new LinkedList<Double>();
		for(int key:dimHsh.keySet())
		{
			if(addrList.isEmpty()){addrList=getAddr(dimHsh.get(key),key,dataFile);}
			else{addrList.retainAll(getAddr(dimHsh.get(key),key,dataFile));}
		}
	    getFact(addrList,dataFile);
	}
	private void getFact(List<Double> addrList, String dataFile)throws Exception 
	{
		FileInputStream fs;ObjectInputStream os;
		ArrayList<String> factRow;
		for(double d:addrList)
		{
			fs=new FileInputStream("db_"+dataFile+"/base/bd"+d);
			os= new ObjectInputStream(fs);
			factRow=(ArrayList<String>) os.readObject();
			System.out.println(factRow.toString());
			os.close();fs.close();
		}
	}
	private List<Double> getAddr(String key, int i, String dataFile)throws Exception
	{
		HashMap<String, LinkedList<Double>> dim=readDim("db_"+dataFile+"/dimensions/dim"+i);
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
