package com.sting2me.common.entity;

import java.util.Map;

/**
 * Created by peter on 14-12-5.
 * structure used by data node
 * every interval of time, data node will report status of self to name node
 */
public class HeartbeatRequest {
	//IP of data server
    private String ip;
    // data server host name
    private String hostName;
    // structure like below  "/dev/sdb1": 95.6, "/dev/sdc1": 60.0
    private Map<String, Double> diskUsage;
    // report inode usage is needed,because if inode is not enough, file cannot be stored
    private Map<String, Double> inodeUsage;
    // true if there is a backup server connected, otherwise false
    private boolean isBacked;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
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
	public boolean isBacked() {
		return isBacked;
	}
	public void setBacked(boolean isBacked) {
		this.isBacked = isBacked;
	}
	/**
	 * total data length to be sent from data server to name server
	 * @return
	 */
	public int totalLen() {
		int n = 0;
		n += this.ip.length();
		n += this.hostName.length();
		
		for(String key: this.diskUsage.keySet()) {
			n += key.length();
			n += Double.SIZE;
		}
			
		return n;
	}
    
}
