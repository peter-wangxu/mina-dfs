package com.sting2me.common.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * Created by peter on 14-12-7.
 */
public class MultimediaCodecFactory implements ProtocolCodecFactory {
    private ProtocolEncoder encoder;
    private ProtocolDecoder decoder;

    public MultimediaCodecFactory(boolean client) {
        if (client) {
            encoder = new MultimediaRequestEncoder();
            decoder = new MultimediaResponseDecoder();
        } else {
            encoder = new MultimediaResponseEncoder();
            decoder = new MultimediaRequestDecoder();
        }
    }

    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return encoder;
    }

    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return decoder;
    }
}
