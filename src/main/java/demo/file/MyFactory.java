package demo.file;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.textline.TextLineDecoder;
import org.apache.mina.filter.codec.textline.TextLineEncoder;

/**
 * Created by peter on 14-12-7.
 */
public class MyFactory implements ProtocolCodecFactory {
    private ProtocolEncoder encoder;
    private ProtocolDecoder decoder;

    public MyFactory(boolean client) {
        if (client) {
            this.decoder = new TextLineDecoder();
            this.encoder = new FileRequestEncoder();
        } else {
            this.decoder = new FileRequestDecoder();
            this.encoder = new TextLineEncoder();
        }
    }

    public MyFactory(ProtocolEncoder encoder, ProtocolDecoder decoder) {
        this.encoder = encoder;
        this.decoder = decoder;
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return encoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return decoder;
    }
}
