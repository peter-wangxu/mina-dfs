package com.sting2me.common.codec;

import com.sting2me.common.entity.MultimediaResponse;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by peter on 14-12-7.
 */
public class MultimediaResponseDecoder extends CumulativeProtocolDecoder {
    //max path length of a file
    public static final int MAX_PATH_LENGTH = 255;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        logger.debug("Client: decoding Multimedia response .");
        if (in.prefixedDataAvailable(4, MAX_PATH_LENGTH)) {
            int pathLen = in.getInt();
            byte[] data = new byte[pathLen];
            in.get(data);
            MultimediaResponse response = new MultimediaResponse();
            response.setFullPath(new String(data));
            out.write(response);
            return true;
        }
        return false;
    }
}
