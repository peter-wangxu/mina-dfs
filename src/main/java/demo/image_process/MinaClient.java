package demo.image_process;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

/**
 * Created by peter on 14-12-4.
 */
public class MinaClient {


    public static void main(String[] args) {

        String host = "127.0.0.1";
        int port = 33789;
        SocketConnector connector;
        IoSession session;
        // 创建一个socket连接

        connector = new NioSocketConnector();

        // 设置链接超时时间

        connector.setConnectTimeoutMillis(3000);

        // 获取过滤器链

        DefaultIoFilterChainBuilder filterChain = connector.getFilterChain();

        // 添加编码过滤器 处理乱码、编码问题

        filterChain.addLast("codec", new ProtocolCodecFilter(new ImageCodecFactory(true)));

        // 消息核心处理器

        connector.setHandler(new ImageClientIoHandler());
        // 连接服务器，知道端口、地址

        ConnectFuture future = connector.connect(new InetSocketAddress(host, port));
        // 等待连接创建完成
        future.awaitUninterruptibly();

        // 获取当前session

        session = future.getSession();
        ImageRequest req = new ImageRequest(10, 10, 5);
        session.write(req);
        future.awaitUninterruptibly();

    }
}
