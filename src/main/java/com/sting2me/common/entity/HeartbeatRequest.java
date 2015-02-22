package com.sting2me.common.entity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by peter on 14-12-5.
 * structure used by data node
 * every interval of time, data node will report status of self to name node
 */
public class HeartbeatRequest {
	//IP of data server
    private String ip;
    // data server host name
    private String hostname;
    // structure like below  "/dev/sdb1": 95.6, "/dev/sdc1": 60.0
    private Map<String, Double> diskUsage;
    // report inode usage is needed,because if inode is not enough, file cannot be stored
    private Map<String, Double> inodeUsage;
    // true if there is a backup server connected, otherwise false
    private boolean isBacked = false;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public Map<String, Double> getDiskUsage() {
		return diskUsage;
	}
	public void setDiskUsage(Map<String, Double> diskUsage) {
		this.diskUsage = diskUsage;
	}
	public Map<String, Double> getInodeUsage() {
		return inodeUsage;
	}
	public void setInodeUsage(Map<String, Double> inodeUsage) {
		this.inodeUsage = inodeUsage;
	}
	public boolean getIsBacked() {
		return isBacked;
	}
	public void setIsBacked(boolean isBacked) {
		this.isBacked = isBacked;
	}
	/**
	 * total data length to be sent from data server to name server
	 * @return
	 */
	public int totalLen() {
		int n = 0;
		n += this.ip.length();
		n += this.hostname.length();
		// isBacked
		n += 1;
		for(String key: this.diskUsage.keySet()) {
			n += key.length();
			n += Double.SIZE;
		}
		for(String key: this.inodeUsage.keySet()) {
			n += key.length();
			n += Double.SIZE;
		}
		
		return n;
	}
	

	public static String toJSON(HeartbeatRequest heartbeat) {
//		StringBuffer json = new StringBuffer(100);
//		json.append("{\"ip\":");
//		json.append("\"" + heartbeat.ip + "\",");
//		json.append("\"hostname\":");
//		json.append("\"" + heartbeat.hostname + "\",");
//		json.append("\"diskUsage\":{");
//		int n = 0;
//		for (String key: heartbeat.getDiskUsage().keySet()) {
//			if (n < heartbeat.getDiskUsage().keySet().size()-1 ) {
//				json.append("\"" + key + "\":\""  + heartbeat.getDiskUsage().get(key) + "\",");
//			} else {
//				json.append("\"" + key + "\":\""  + heartbeat.getDiskUsage().get(key) + "\"");
//			}
//			n += 1;
//		}
//		json.append("}");
//		json.append("\"inodeUsage\":{");
//		n = 0;
//		for (String key: heartbeat.getDiskUsage().keySet()) {
//			if (n < heartbeat.getInodeUsage().keySet().size()-1 ) {
//				json.append("\"" + key + "\":\"" + heartbeat.getInodeUsage().get(key) + "\",");
//			} else {
//				json.append("\"" + key + "\":\"" + heartbeat.getInodeUsage().get(key) + "\"");
//			}
//			n += 1;
//		}
//		json.append("}");
//		json.append("\"isBacked\":\"" + heartbeat.getIsBacked() + "\"");
//		return json.toString();
		ObjectMapper objectMapper = new ObjectMapper();
		String o = null;;
		try {
			o = objectMapper.writeValueAsString(heartbeat);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
    
	public static HeartbeatRequest fromJSON(String json)  {
//		HeartbeatRequest request = new HeartbeatRequest();
//		// TODO return fake data
//		request.setIp("192.168.1.1");
//		request.setHostName("Peter-HOST");
//		Map<String, Double> disk= new HashMap<String, Double>(); 
//		disk.put("/dev/sda", 18.0D);
//		request.setDiskUsage(disk);
//		Map<String, Double> inode= new HashMap<String, Double>(); 
//		inode.put("/dev/sda", 18.0D);
//		request.setInodeUsage(inode);
		ObjectMapper objectMapper = new ObjectMapper();
		HeartbeatRequest request = null;
		try {
			request = objectMapper.readValue(json, HeartbeatRequest.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return request;
	}
}
