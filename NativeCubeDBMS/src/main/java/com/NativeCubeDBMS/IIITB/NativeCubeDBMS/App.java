package com.NativeCubeDBMS.IIITB.NativeCubeDBMS;
import java.io.*;
import java.util.Properties;
public class App 
{
	static Properties prop=new Properties();
    public static void main( String[] args )
    {
    	//load configuration file
    	try{prop.load(new FileInputStream(new File("config.properties")));}
		catch (Exception e)
		{e.printStackTrace();}
    	//set create to true to create base & dimension index files.We need UI interaction here.
    	boolean create=false,genrt=true,metadimgenrt=false;
        try
        {
        	if(create==true){loadData(prop);}
        	if(genrt==true){genrateLatticeOfCuboids(prop);} 
        	if(metadimgenrt==true) {genrateDimensionMetaData(prop);}
        	readData(prop);
		}catch (Exception e)
        {e.printStackTrace();}
    }

    //To generate Lattice of Cuboids.
    private static void genrateLatticeOfCuboids(Properties prop)throws Exception
    {
		LatticeGenerator lattice= new LatticeGenerator();
		//column no. of each dimensions need schema file here.
		char[]set= {'0','1','2','3'};
		lattice.createLatticeOfCuboids(set, set.length, prop);
	}

	//To read Data as per OLAP operations :slice/dice/roll up/drill down.We need UI interaction here.
	private static void readData(Properties prop)throws Exception
	{
		ReadData read= new ReadData();
		read.fetch(prop);
	}
	
    //To create base & dimension index files
	private static void loadData(Properties prop)throws Exception
	{
        makeDirectory(prop);
        Load load =new Load();
        load.createBase(prop);        
	}

	//make directory as per excel data file name.It will have two folder base and dimensions.
	private static void makeDirectory(Properties prop) throws Exception
	{
		new File(prop.getProperty("baseDirPath")).mkdirs();
		new File(prop.getProperty("dimensionsDirPath")).mkdirs();
		new File(prop.getProperty("latticeDirPath")).mkdirs();
		new File(prop.getProperty("dimensionInfoDirPath")).mkdirs();
	}
	private static void genrateDimensionMetaData(Properties prop) throws Exception
	{
		MetaDimGenerator dimMeta= new MetaDimGenerator();
		dimMeta.genratedimMeta(prop);
		
	}
}
