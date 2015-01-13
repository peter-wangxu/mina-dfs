/**
 * 
 */
package com.sting2me.common.util.config;

/**
 * Configuration entity mapped with data server
 * @author peter
 *
 */
public class DataServerConfig implements IConfig {

	@ConfigProperties(name="name_server_ip", prefix="dataserver")
	public String 		nameServerIp;
	@ConfigProperties(name="backup_server_ip", prefix="dataserver")
	public String 		backupServerIp;
	@ConfigProperties(name="report_interval", prefix="dataserver")
	public Integer		reportInterval;
	@ConfigProperties(name="domain", prefix="dataserver")
	public String 		domain;
	@ConfigProperties(name="partitions", prefix="dataserver")
	public String[] 	partitions;

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
	public Integer getReportInterval() {
		return reportInterval;
	}
	public void setReportInterval(Integer reportInterval) {
		this.reportInterval = reportInterval;
	}
	public String[] getPartitions() {
		return partitions;
	}
	public void setPartitions(String[] partitions) {
		this.partitions = partitions;
	}
	
	
}
