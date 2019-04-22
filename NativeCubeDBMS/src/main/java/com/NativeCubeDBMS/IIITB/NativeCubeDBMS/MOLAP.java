package com.NativeCubeDBMS.IIITB.NativeCubeDBMS;

import java.io.*;
import java.util.*;

public class MOLAP{
	
	public void getDice(String code,Properties prop) throws Exception
	{
		File cuboidfile = new File(prop.getProperty("latticePath")+code);
		FileInputStream cfs = new FileInputStream(cuboidfile);
	    ObjectInputStream cos = new ObjectInputStream(cfs);
	    LinkedList<Double> addrList= new LinkedList<Double>();
	    HashMap<String, LinkedList<Double>> cbhsh = (HashMap<String, LinkedList<Double>>)cos.readObject();
	    cos.close();cfs.close();
	    FileInputStream bsfs;ObjectInputStream bsos;
	    bsfs=new FileInputStream(prop.getProperty("basePath")+"1");
		bsos= new ObjectInputStream(bsfs);
		HashMap<Double,ArrayList<String>> baser= (HashMap<Double,ArrayList<String>>)bsos.readObject();
		ArrayList<String> factRow;double res;
	    for(String key:cbhsh.keySet())
	    {
	    	addrList= cbhsh.get(key);
	    	res=0;
	    	for(Double d:addrList)
	    	{
	    		factRow=baser.get(d);
	    		res+=Double.parseDouble(factRow.get(6));
	    	}
	    	System.out.println(key + " "+res);
	    }
	    bsos.close();bsfs.close();
	}
	
}
