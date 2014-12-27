package demo.file;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by peter on 14-12-7.
 */
public class FileServerHandler extends IoHandlerAdapter{
    Logger logger = LoggerFactory.getLogger(this.getClass());
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
        super.exceptionCaught(session, cause);
        cause.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
        logger.info("Server1: {}", message);
        session.write("hello\\n".getBytes());
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
        logger.info("Server2: {}", message);
    }
}
