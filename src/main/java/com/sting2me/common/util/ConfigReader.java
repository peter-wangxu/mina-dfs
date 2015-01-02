package com.sting2me.common.util;


import java.io.File;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by peter on 14-12-27.
 */
public class ConfigReader {
	private Logger logger = LoggerFactory.getLogger(getClass());
	public static final String [] DEFAULT_CONFIG_LOCATION = new String [] {"/etc/mina-dfs/mina-dfs.properties",
		"~/mina-dfs.properties", "./mina-dfs.properties"};
	
	private String filename;
	private String format;
	private Configuration config;
	public static void main(String[] args) {
		ConfigReader reader = new ConfigReader();
		Configuration config = null;
		try {
			String file = reader.selectConfigFile("");
			config = new PropertiesConfiguration(file);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(config.getInt("test"));
	}
	/**
	 * 
	 */
	public ConfigReader() {
		
	}
	/**
	 * 
	 * @param filename
	 */
	public ConfigReader(String filename) {
		
	}
	
	
	public String selectConfigFile(String dft) {
		for(String path: DEFAULT_CONFIG_LOCATION) {
			File file = new File(path);
			if(file.exists() && file.isFile()) {
				if(!file.canRead()) {
					logger.warn("configuration file:{} could not be read, skip it now.", path);
					continue;
				}
				logger.info("Loading configration info from {}", path);
				return path;
			}
		}
		return null;
	}
}
 