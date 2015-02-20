package com.sting2me.dataserver;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sting2me.common.codec.HeartbeatCodecFactory;
import com.sting2me.common.entity.HeartbeatRequest;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class will report data server statistics to name server periodically
 * Created by peter on 14-12-15.
 */
public class StatisticReporter {
	//TODO This should be loaded from config file
    public final static long REPORT_PERIOD = 15 * 1000;
    private IoSession session = null;
    private String host = "127.0.0.1";
    private int port = 9931;

    public IoSession reconnect() {
        if (this.session != null) {
            if (false == this.session.isConnected()) {
                this.session = this.connect(this.host, this.port);
                return this.session;
            }
            this.session.close(true);
            this.session = this.connect(this.host, this.port);
            return this.session;
        }
        this.session = this.connect(this.host, this.port);
        return this.session;

    }

    public IoSession connect(String host, int port) {
        SocketConnector connector;
        // 创建一个socket连接
        connector = new NioSocketConnector();
        // 设置链接超时时间
        connector.setConnectTimeoutMillis(3000);
        // 获取过滤器链
        DefaultIoFilterChainBuilder filterChain = connector.getFilterChain();
        // 添加编码过滤器 处理乱码、编码问题
        filterChain.addLast("codec", new ProtocolCodecFilter(new HeartbeatCodecFactory(true)));
        // 消息核心处理器
        connector.setHandler(new StatisticIoHandler());
        // 连接服务器，知道端口、地址
        ConnectFuture future = connector.connect(new InetSocketAddress(host, port));
        // 等待连接创建完成
        future.awaitUninterruptibly();
        return future.getSession();
    }

    public void start() {
        //initialize connection with name server
        this.session = this.connect(host, port);
        Timer periodTimer = new Timer("DataServerReport", true);
        periodTimer.schedule(new ReportTask(this.session, this), new Date(), REPORT_PERIOD);
    }
}

class ReportTask extends TimerTask {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private IoSession session;
    private StatisticReporter report;

    public ReportTask(IoSession session, StatisticReporter report) {
        this.session = session;
        this.report = report;
    }

    public  HeartbeatRequest getFakeRequest() {
    	HeartbeatRequest request = new HeartbeatRequest();
    	request.setIp("192.168.1.1");
		request.setHostName("Peter-HOST");
		Map<String, Double> disk= new HashMap<String, Double>(); 
		disk.put("/dev/sda", 18.0D);
		request.setDiskUsage(disk);
		Map<String, Double> inode= new HashMap<String, Double>(); 
		inode.put("/dev/sda", 18.0D);
		request.setInodeUsage(inode);
    	return request;
    }
    @Override
    public void run() {
        //collect data and report to Name Server periodically
        try {
            session.write(this.getFakeRequest());
        } catch (Exception ex) {
            //TODO need to sleep some time here?
            logger.warn("Trying to reconnect to data server...");
            this.session = this.report.reconnect();
            this.session.write(this.getFakeRequest());
        } finally {
            logger.info("Data Server statistics sent successfully.");
        }
    }
}
