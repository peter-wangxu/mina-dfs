package com.sting2me.dataserver;

import com.sting2me.common.codec.HeartbeatCodecFactory;
import com.sting2me.common.codec.MultimediaCodecFactory;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by peter on 14-12-6.
 */
public class DataServer {
    public static final int PORT = 33789;
    private static Logger logger = LoggerFactory.getLogger(DataServer.class);

    public static void main(String[] args) throws IOException {
        DataServerIoHandler handler = new DataServerIoHandler();
        NioSocketAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getFilterChain().addLast("protocol", new ProtocolCodecFilter(new MultimediaCodecFactory(false)));
        acceptor.setDefaultLocalAddress(new InetSocketAddress(PORT));
        acceptor.setHandler(handler);
        acceptor.bind();
        logger.info("DataServer is listening at port " + PORT);
        //start Data Client to report statistics to Name Server
        logger.info("DataServer is starting to report data at port 9931 ");
        StatisticReporter reporter = new StatisticReporter();
        reporter.start();
    }
}
