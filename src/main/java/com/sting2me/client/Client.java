package com.sting2me.client;

import com.sting2me.common.codec.MultimediaCodecFactory;
import com.sting2me.common.entity.MultimediaRequest;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by peter on 14-12-6.
 */
public class Client {
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
        filterChain.addLast("codec", new ProtocolCodecFilter(new MultimediaCodecFactory(true)));
        // 消息核心处理器
        connector.setHandler(new ClientIoHandler());
        // 连接服务器，知道端口、地址
        ConnectFuture future = connector.connect(new InetSocketAddress(host, port));
        // 等待连接创建完成
        future.awaitUninterruptibly();
        // 获取当前session
        session = future.getSession();
        MultimediaRequest req = new MultimediaRequest();
        MultimediaRequest req2 = new MultimediaRequest();
        String filepath = "/home/peter/Downloads/apache-tomcat-7.0.56.tar.gz";
        File file = new File(filepath);
        File file2 = new File("/home/peter/Downloads/apache-mina-2.0.9-bin.zip");
        if (file.exists()) {
            byte[] fileBytes = new byte[(int) file.length()];
            byte[] file2Bytes = new byte[(int) file2.length()];
            FileInputStream inputStream = null;
            FileInputStream inputStream2 = null;
            try {
            	inputStream = new FileInputStream(file);
            	inputStream2 = new FileInputStream(file2);
                inputStream.read(fileBytes);
                inputStream2.read(file2Bytes);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
					try {
						if (inputStream != null)
							inputStream.close();
						if (inputStream2 != null)
		            		inputStream2.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            }
            req.setFileData(fileBytes);
            req.setFileExtension("tar.gz");

            //second file
            req2.setFileData(file2Bytes);
            req2.setFileExtension("zip");
        }
        session.write(req);
        session.write(req2);
        future.awaitUninterruptibly();

    }
}
