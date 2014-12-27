package demo.image_process;

import com.sting2me.common.entity.MultimediaResponse;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by peter on 14-12-2.
 */
public class ImageClientIoHandler extends IoHandlerAdapter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void messageReceived(IoSession session, Object message) throws Exception {
        logger.debug("Client: message received");
        MultimediaResponse response = (MultimediaResponse) message;
        //imageListener.onImages(response.getImage1(), response.getImage2());
        logger.debug("{}", response.getFullPath());
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
        cause.printStackTrace();
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        logger.debug("Client message sent.");
        super.messageSent(session, message);

    }
}