package com.sting2me.client;

import com.sting2me.common.entity.MultimediaResponse;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by peter on 14-12-6.
 */
public class ClientIoHandler extends IoHandlerAdapter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
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
        cause.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {

        logger.info("Client:messageReceived");
        MultimediaResponse response = (MultimediaResponse) message;
        logger.info("Client: path -->{}", response.getFullPath());
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        logger.info("Client: messageSent");

    }
}
