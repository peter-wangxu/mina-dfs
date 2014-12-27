package com.sting2me.common.codec;

import com.sting2me.common.entity.MultimediaResponse;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by peter on 14-12-7.
 */
public class MultimediaResponseEncoder implements ProtocolEncoder {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        logger.debug("DataServer: encoding multimedia response");
        MultimediaResponse response = (MultimediaResponse) message;
        byte[] path = response.getFullPath().getBytes();
        IoBuffer buffer = IoBuffer.allocate(path.length + 4);
        buffer.putInt(path.length);
        buffer.put(path);
        buffer.flip();
        out.write(buffer);
    }

    @Override
    public void dispose(IoSession session) throws Exception {

    }
}
