/**
 * 
 */
package com.sting2me.common.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sting2me.common.entity.HeartbeatRequest;


/**
 * @author peter
 *
 */
public class HeartbeatRequestDecoder extends CumulativeProtocolDecoder {
	Logger logger = LoggerFactory.getLogger(getClass());
	private static int MAX_HEART_BEAT_LEN = 10000;
	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		if(in.prefixedDataAvailable(4, MAX_HEART_BEAT_LEN)) {
			logger.debug("Begin to decode heart beat");
			int l = in.getInt();
			byte [] data = new byte[l];
			in.get(data, 0, l);
			out.write(HeartbeatRequest.fromJSON(""));
			return true;
		}
		return false;
	}

}
