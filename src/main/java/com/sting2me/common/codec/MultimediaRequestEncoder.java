package com.sting2me.common.codec;

import com.sting2me.common.entity.MultimediaRequest;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by peter on 14-12-7.
 * Endcoder used by client to encode client request
 */
public class MultimediaRequestEncoder implements ProtocolEncoder {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        logger.debug("Client: encoding the multimedia request.");
        MultimediaRequest request = (MultimediaRequest) message;
        byte [] data = request.getFileData();
        byte [] name = request.getFileExtension().getBytes();
        IoBuffer ioBuffer = IoBuffer.allocate(data.length + 4 + name.length + 4, false);
        ioBuffer.setAutoExpand(true);
        ioBuffer.putInt(data.length);
        ioBuffer.put(data);
        ioBuffer.putInt(name.length);
        ioBuffer.put(name);
        ioBuffer.flip();
        out.write(ioBuffer);
    }

    @Override
    public void dispose(IoSession session) throws Exception {
    }
}
