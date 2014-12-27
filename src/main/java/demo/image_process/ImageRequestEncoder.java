package demo.image_process;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by peter on 14-12-2.
 */
public class ImageRequestEncoder implements ProtocolEncoder {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        logger.debug("Client: begin to encode request");
        ImageRequest request = (ImageRequest) message;
        IoBuffer buffer = IoBuffer.allocate(12, false);
        buffer.putInt(request.getWidth());
        buffer.putInt(request.getHeight());
        buffer.putInt(request.getNumberOfCharacters());
        buffer.flip();
        out.write(buffer);
    }

    public void dispose(IoSession session) throws Exception {
        // nothing to dispose
    }
}
