package demo.image_process;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by peter on 14-12-2.
 */
public class ImageServer {
    public static final int PORT = 33789;

    public static void main(String[] args) throws IOException {
        ImageServerIoHandler handler = new ImageServerIoHandler();
        NioSocketAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getFilterChain().addLast("protocol", new ProtocolCodecFilter(new ImageCodecFactory(false)));
        acceptor.setDefaultLocalAddress(new InetSocketAddress(PORT));
        acceptor.setHandler(handler);
        acceptor.bind();
        System.out.println("server is listenig at port " + PORT);
    }
}
