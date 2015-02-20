package com.sting2me.nameserver;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sting2me.common.codec.HeartbeatCodecFactory;
import com.sting2me.common.codec.MultimediaCodecFactory;


/**
 * Created by peter on 14-12-6.
 */
public class NameServer {
	public static final int PORT = 9931;
	private static Logger logger = LoggerFactory.getLogger(NameServer.class);
	public static void main(String[] args) throws IOException {
		NameServerIoHandler handler = new NameServerIoHandler();
        NioSocketAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getFilterChain().addLast("protocol", new ProtocolCodecFilter(new HeartbeatCodecFactory(false)));
        acceptor.setDefaultLocalAddress(new InetSocketAddress(PORT));
        acceptor.setHandler(handler);
        acceptor.bind();
        logger.info("DataServer is listening at port " + PORT);
	}
}
