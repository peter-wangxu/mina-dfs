package com.sting2me.common.entity;

/**
 * Created by peter on 14-12-5.
 */
public class HeartbeatResponse {
	/**
	 * returned by Name Server to Data Server
	 * can be 'success', 'fail'
	 */
    private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
