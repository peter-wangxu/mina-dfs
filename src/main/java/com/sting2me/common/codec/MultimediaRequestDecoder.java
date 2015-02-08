package com.sting2me.common.codec;

import com.sting2me.common.entity.MultimediaRequest;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by peter on 14-12-7.
 * this is used by DataServer to decode the request from client
 */
public class MultimediaRequestDecoder extends CumulativeProtocolDecoder {
    //support image size not larger than 20M
    public static final int MAX_IMAGE_SIZE = 9 * 1024 * 1024;
    private static final String DECODER_STATE_KEY = MultimediaRequestDecoder.class.getName() + ".STATE";
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        logger.debug("DataServer: decoding the multimedia request.");

        DecoderState decoderState = (DecoderState) session.getAttribute(DECODER_STATE_KEY);
        if (null == decoderState) {
            decoderState = new DecoderState();
            session.setAttribute(DECODER_STATE_KEY, decoderState);
        }
        logger.debug("Remain out: {}", in.remaining());
        if (in.prefixedDataAvailable(4, MAX_IMAGE_SIZE)) {
            logger.info("Remain: {}", in.remaining());
            int length = in.getInt();
            byte[] data = new byte[length];
            in.get(data);
            int suffixLength = in.getInt();
            byte[] suffix = new byte[suffixLength];
            in.get(suffix);
            MultimediaRequest request = new MultimediaRequest();
            request.setFileData(data);
            request.setFileExtension(new String(suffix));
            out.write(request);
            return true;
        }

        // return false as not enough data
        return false;
    }

    private static class DecoderState {
    }
}
