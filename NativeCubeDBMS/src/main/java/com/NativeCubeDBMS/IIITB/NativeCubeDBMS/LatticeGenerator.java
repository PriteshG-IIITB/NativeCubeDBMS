package com.NativeCubeDBMS.IIITB.NativeCubeDBMS;
//import java.io.*;
//import java.util.*;
public class LatticeGenerator
{
//	public void createLatticeOfCuboids(char []set,int set_size,String dataFile)
//	{
//		/*set_size of power set of a set with set_size n is (2^n )*/
//        long pow_set_size =  (long)Math.pow(2, set_size); 
//        List<Double> addrList= new LinkedList<Double>(); 
//        /*Run from counter 000..1 to 111..1*/
//        for(int counter = 0; counter <  
//                pow_set_size; counter++) 
//        { 
//            for(int j = 1; j < set_size; j++) 
//            { 
//                /* Check if jth bit in the  counter is set If set then  
//                Use dimension file of jth column to create cuboid node in lattice */
//                if((counter & (1 << j)) > 0) 
//                {
//                	if(addrList.isEmpty()){addrList=getAddr(j,dataFile);}
//        			else{addrList.retainAll(getAddr(j,dataFile));}
//                }    
//            } 
//              
//            System.out.println(); 
//        } 
//	}
//
//	private List<Double> getAddr(int j, String dataFile) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
