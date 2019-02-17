package com.thoughtwork.train.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Simple Txt File Minipulation
 * @author wangxiao
 */
public class ReadWriteIO {
	
	/**
	 * Read Txt File
	 * @param pathname
	 * @return
	 */
	public static String readFile(String pathname) {
		String res = "";
        try {
        	FileReader reader = new FileReader(pathname);
            BufferedReader br = new BufferedReader(reader);
           
            String line;
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
            	res = res + line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	return res;
        }
    }
	
	/**
	 * Write Txt file
	 * @param pathname
	 */
	public static void writeFile(String pathname, String content) {
		BufferedWriter out = null;
		try {
			File writename = new File(pathname);
			writename.createNewFile(); 
			out = new BufferedWriter(new FileWriter(writename));  
			out.write(content); 
			
		} catch (IOException e) {
            e.printStackTrace();
            
        } finally {
        	try {
        		out.flush();
    			out.close();
        	} catch(Exception e) {
        		//TODO
        	}
        	
        }
       
	}
	
	/**
	 * Delte one file
	 * @param sPath
	 * @return
	 */
	public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

}
