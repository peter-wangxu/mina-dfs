/**
 * 
 */
package com.sting2me.common.util.config;

/**
 * @author peter
 *
 */
public class DataServerConfig implements IConfig {

	private String 	nameServerIp;
	private String 	backupServerIp;
	private int		reportInterval;
	private String 	domain;
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getNameServerIp() {
		return nameServerIp;
	}
	public void setNameServerIp(String nameServerIp) {
		this.nameServerIp = nameServerIp;
	}
	public String getBackupServerIp() {
		return backupServerIp;
	}
	public void setBackupServerIp(String backupServerIp) {
		this.backupServerIp = backupServerIp;
	}
	public int getReportInterval() {
		return reportInterval;
	}
	public void setReportInterval(int reportInterval) {
		this.reportInterval = reportInterval;
	}
	
	
}
