package com.sting2me.nameserver;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sting2me.common.entity.HeartbeatRequest;

/**
 * Created by peter on 14-12-6.
 */
public class NameServerIoHandler extends IoHandlerAdapter {
	Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        logger.info("Session created : " + session.getId());
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        logger.info("Messsage received: " + session.getId());
        HeartbeatRequest request = (HeartbeatRequest) message;
        logger.info("data: " + request.getHostName());
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
    }
}
