package com.sting2me.common.util.config;


import java.io.File;
import java.lang.reflect.Field;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by peter on 14-12-27.
 */
public class ConfigReader {
	public static final String [] DEFAULT_CONFIG_LOCATION = new String [] {"/etc/mina-dfs/mina-dfs.properties",
		"~/mina-dfs.properties", "./mina-dfs.properties"};
	
	private Logger 			logger = LoggerFactory.getLogger(getClass());
	private String 			filename;
	private String 			format;
	private Configuration 	config;
	private IConfig			configStore;
	/**
	 * Default constructor
	 */
	public ConfigReader() {
		this.filename = this.selectDefault();
		this.format   = "properties";
	}
	/**
	 * Constructor with filename
	 * @param filename
	 */
	public ConfigReader(String filename) {
		this.filename = filename;
		this.format = "properties";
		
	}
	public String selectDefault() {
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
	
	public void load() throws ConfigurationException {
		this.config = new PropertiesConfiguration(this.filename);
		if(null == this.configStore) {
			logger.error("Please initialize store first.");
			throw new ConfigurationException("Need to set config store first.");
		}
		Field[] fields = this.configStore.getClass().getDeclaredFields();
		for(Field f: fields) {
			//TODO extract property name from the field name 
		}
		
	}
	public static void main(String[] args) {
		ConfigReader reader = new ConfigReader();
		Configuration config = null;
		try {
			String file = reader.selectDefault();
			config = new PropertiesConfiguration(file);
			
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(config.getInt("test"));
	}
}
 