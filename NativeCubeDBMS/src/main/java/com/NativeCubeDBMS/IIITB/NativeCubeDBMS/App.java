package com.NativeCubeDBMS.IIITB.NativeCubeDBMS;
import java.io.*;
public class App 
{
    public static void main( String[] args )
    {
        String dataFile="sales_datawarehouse";
        //set create to true to create base & dimension index files.We need UI interaction here.
        boolean create=false,genrt=true;
        try
        {
        	if(create==true){loadData(dataFile);}
        	if(genrt==true){genrateLatticeOfCuboids(dataFile);} 
        	//readData(dataFile);
		}catch (Exception e)
        {e.printStackTrace();}
  
    }

    //To generate Lattice of Cuboids.
    private static void genrateLatticeOfCuboids(String dataFile)throws Exception
    {
		LatticeGenerator lattice= new LatticeGenerator();
		//column no. of each dimensions need schema file here.
		char[]set= {'0','1','2','3'};
		lattice.createLatticeOfCuboids(set, set.length, dataFile);
	}

	//To read Data as per OLAP operations :slice/dice/roll up/drill down.We need UI interaction here.
	private static void readData(String dataFile)throws Exception
	{
		ReadData read= new ReadData();
		read.fetch(dataFile);
	}
	
    //To create base & dimension index files
	private static void loadData(String dataFile)throws Exception
	{
        makeDirectory(dataFile);
        Load load =new Load();
        load.createBase(dataFile);        
	}

	//make directory as per excel data file name.It will have two folder base and dimensions.
	private static void makeDirectory(String dataFile) throws Exception
	{
		new File("db_"+dataFile+"/base").mkdirs();
		new File("db_"+dataFile+"/dimensions").mkdirs();
		new File("db_"+dataFile+"/lattice").mkdirs();
	}
}
