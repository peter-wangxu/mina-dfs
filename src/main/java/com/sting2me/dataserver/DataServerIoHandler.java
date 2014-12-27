package com.sting2me.dataserver;

import com.sting2me.common.entity.MultimediaRequest;
import com.sting2me.common.entity.MultimediaResponse;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by peter on 14-12-6.
 */
public class DataServerIoHandler extends IoHandlerAdapter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
        cause.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        logger.info("DataServer: messageReceived, sessionid {}", session.getId());
        MultimediaRequest req = (MultimediaRequest) message;
        logger.info("DataServer: length {}, ext {}", req.getFileData().length, req.getFileExtension());
        File dstFile = new File("/tmp/test." + req.getFileExtension());
        FileOutputStream outFile = new FileOutputStream(dstFile);
        outFile.write(req.getFileData());
        outFile.close();
        if (dstFile.exists()) {
            logger.info("DataServer: file write successfully {}", dstFile.getPath());
            MultimediaResponse res = new MultimediaResponse();
            res.setFullPath("/tmp/test." + req.getFileExtension());
            session.write(res);
        }

    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        logger.info("DataServer: messageSent");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
    }
}
