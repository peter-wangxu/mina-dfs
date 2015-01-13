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
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public IConfig getConfigStore() {
		return configStore;
	}
	public void setConfigStore(IConfig configStore) {
		this.configStore = configStore;
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
			ConfigProperties c = f.getAnnotation(ConfigProperties.class);
			String key = null;
			if (!c.prefix().equals("default")) {
				key = c.prefix() + "." + c.name();
			} else {
				key = c.name();
			}
			try {
				Object value = this.config.getProperty(key);
				if (c.required() && null == value) {
					logger.error("option[{}] is required, please specify in config file", key);
				}
				f.set(this.configStore, this.config.getProperty(key));
			} catch (Exception e) {
					logger.info("skipping option[{}]", key);
			}
		}
		
	}
	/**
	 * unit test 
	 * @param args
	 */
	public static void main(String[] args) {
		ConfigReader reader = new ConfigReader();
		try {
			DataServerConfig data = new DataServerConfig();
			reader.setConfigStore(data);
			reader.load();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
}
 