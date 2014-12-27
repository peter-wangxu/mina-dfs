package demo;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.util.Date;

/**
 * Created by peter on 14-12-2.
 */
public class TimeServerHandler implements IoHandler {
    @Override
    public void sessionCreated(IoSession ioSession) throws Exception {

    }

    @Override
    public void sessionOpened(IoSession ioSession) throws Exception {

    }

    @Override
    public void sessionClosed(IoSession ioSession) throws Exception {

    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus idleStatus) throws Exception {
        System.out.println("IDLE " + session.getIdleCount(idleStatus));
    }

    @Override
    public void exceptionCaught(IoSession ioSession, Throwable throwable) throws Exception {
        throwable.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object o) throws Exception {
        String str = o.toString();
        if (str.trim().equalsIgnoreCase("quit")) {
            session.close(false);
            return;
        }
        Date date = new Date();
        session.write(date.toString());
        System.out.println("Message written...");
    }

    @Override
    public void messageSent(IoSession ioSession, Object o) throws Exception {

    }

    @Override
    public void inputClosed(IoSession ioSession) throws Exception {

    }
}
