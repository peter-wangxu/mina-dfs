package demo.file;


import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by peter on 14-12-7.
 */
public class FileRequestDecoder extends CumulativeProtocolDecoder {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private int MAX_LENGTH = 10 * 1024 * 1024;

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        int remain = in.remaining();
        Object o = session.getAttribute("COUNT");
        if (null == o) {
            session.setAttribute("COUNT", remain);
        } else {
            Integer i = (Integer) session.getAttribute("COUNT");
            session.setAttribute("COUNT", i + remain);
        }
        logger.info("count :{}", session.getAttribute("COUNT"));
        if (in.prefixedDataAvailable(4, MAX_LENGTH)) {
            byte data[] = new byte[in.remaining()];
            in.get(data);
            out.write(data);
            logger.info("in doDecode True");
            return true;
        }
        logger.info("in doDecode False");
        return false;
    }
}
