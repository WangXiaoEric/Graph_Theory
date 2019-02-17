package com.thoughtwork.train.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

import com.thoughtwork.train.exception.TrainException;

public class Configuration {
	
	public static Properties trainProps = new Properties();
	
	public static void initialize() throws TrainException {
		try {
			//InputStream in = Configuration.class.getResourceAsStream("application.properties");
			File file = new File("src/main/resources/application.properties");
			InputStream in = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(in, "UTF-8");
			trainProps.load(inputStreamReader);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			throw new TrainException("加载配置文件失败");
		}
		
		
		
	}
	
	public static void main(String[] args) throws TrainException {
		Configuration.initialize();
	}

}
