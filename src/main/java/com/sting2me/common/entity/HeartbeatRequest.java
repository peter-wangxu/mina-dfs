package com.sting2me.common.entity;

import java.util.Map;

/**
 * Created by peter on 14-12-5.
 * structure used by data node
 * every interval of time, data node will report status of self to name node
 */
public class HeartbeatRequest {
    private String ip;
    private String hostName;
    // structure like below  "/dev/sdb1": 95.6, "/dev/sdc1": 60.0
    private Map<String, Double> diskUsage;
    // report inode usage is needed as if inode is not enough, file cannot be stored
    private Map<String, Double> inodeUsage;
    // true if there is a backup server connected, otherwise false
    private boolean isBacked;
}
