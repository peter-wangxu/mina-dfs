package demo.image_process;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by peter on 14-12-2.
 */
public class ImageRequestDecoder extends CumulativeProtocolDecoder {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        logger.debug("Server: begin to decode request");
        if (in.remaining() >= 12) {
            int width = in.getInt();
            int height = in.getInt();
            int numberOfCharacters = in.getInt();
            ImageRequest request = new ImageRequest(width, height, numberOfCharacters);
            out.write(request);
            return true;
        } else {
            return false;
        }
    }
}
