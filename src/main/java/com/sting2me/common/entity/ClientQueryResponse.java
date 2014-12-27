package com.sting2me.common.entity;

/**
 * Created by peter on 14-12-5.
 */
public class ClientQueryResponse {
    private String ip;
    private String name;
    private String port;
    //like  TCP  UDP  TCP_UDP
    private String protocols;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getProtocols() {
        return protocols;
    }

    public void setProtocols(String protocols) {
        this.protocols = protocols;
    }
}
