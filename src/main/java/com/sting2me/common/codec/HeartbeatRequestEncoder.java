/**
 * 
 */
package com.sting2me.common.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.sting2me.common.entity.HeartbeatRequest;

/**
 * @author peter
 *
 */
public class HeartbeatRequestEncoder implements ProtocolEncoder {

	@Override
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		HeartbeatRequest request =  (HeartbeatRequest) message;
		String mess = HeartbeatRequest.toJSON(request);
		IoBuffer buffer = IoBuffer.allocate(4 + mess.length());
		buffer.putInt(mess.length());
		buffer.put(mess.getBytes());
		buffer.flip();
		out.write(buffer);
	}

	@Override
	public void dispose(IoSession session) throws Exception {
		// TODO Auto-generated method stub

	}

}
