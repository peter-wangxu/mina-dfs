package demo.file;


import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by peter on 14-12-7.
 */
public class FileRequestEncoder implements ProtocolEncoder {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        logger.info("in encode: {}", this.getClass().getCanonicalName());
        //DefaultFileRegion region = (DefaultFileRegion) message;
        //IoBuffer buffer = this.getNextBuffer(region);
        out.write(message);
    }

    @Override
    public void dispose(IoSession session) throws Exception {

    }


}
