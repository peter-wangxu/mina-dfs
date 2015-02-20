package com.sting2me.common.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class HeartbeatCodecFactory implements ProtocolCodecFactory {
	private ProtocolEncoder encoder;
    private ProtocolDecoder decoder;
    
	public HeartbeatCodecFactory(boolean dataServer) {
		if(dataServer) {
			this.encoder = new  HeartbeatRequestEncoder();
			this.decoder = null;
		} else {
			this.encoder = null;
			this.decoder = new HeartbeatRequestDecoder();
		}
		
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		return encoder;
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		return decoder;
	}

}
