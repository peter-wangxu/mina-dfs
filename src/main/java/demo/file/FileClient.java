package demo.file;

import demo.image_process.ImageClientIoHandler;
import demo.image_process.ImageCodecFactory;
import demo.image_process.ImageRequest;
import org.apache.mina.core.file.FilenameFileRegion;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.stream.FileRegionWriteFilter;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;

/**
 * Created by peter on 14-12-7.
 */
public class FileClient {
    private static final int PORT = 9123;
    private static Logger logger = LoggerFactory.getLogger(FileClient.class);
    public static void main(String[] args) {

        String host = "127.0.0.1";
        SocketConnector connector;
        IoSession session;
        // 创建一个socket连接
        connector = new NioSocketConnector();
        // 设置链接超时时间
        connector.setConnectTimeoutMillis(3000);
        // 获取过滤器链
        DefaultIoFilterChainBuilder filterChain = connector.getFilterChain();
        // 添加编码过滤器 处理乱码、编码问题
        filterChain.addFirst("fileFilter", new FileRegionWriteFilter());
        filterChain.addLast("codec", new ProtocolCodecFilter(new MyFactory(true)));
        // 消息核心处理器
        connector.setHandler(new FileClientHandler());
        // 连接服务器，知道端口、地址
        ConnectFuture future = connector.connect(new InetSocketAddress(host, PORT));
        // 等待连接创建完成
        future.awaitUninterruptibly();
        // 获取当前session
        session = future.getSession();
        String filepath = "/home/peter/Downloads/peter.txt";
        File file = new File(filepath);
        FileInputStream fis = null;
        FileChannel channel = null;
        FilenameFileRegion region1 = null;
        FilenameFileRegion region2 = null;
        int len = 0;
        try {

            fis = new FileInputStream(file);
            len = fis.available();
            channel = fis.getChannel();
            logger.info("totalSpace:" + len);
            region1= new FilenameFileRegion(file, channel, 0, len/2);
            session.write(region1);
            region2 = new FilenameFileRegion(file, channel, len/2, len);
            session.write(region2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        future.awaitUninterruptibly();

    }
}
