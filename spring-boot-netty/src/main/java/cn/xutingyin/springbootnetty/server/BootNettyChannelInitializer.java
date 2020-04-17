package cn.xutingyin.springbootnetty.server;


import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 通道初始化
 */
public class BootNettyChannelInitializer<SocketChannel> extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        // ChannelOutboundHandler，依照逆序执行
        // 这里在实际中，一般服务端和客户端会相互约定报文格式，这时就需要自定义编码，解码器来处理数据
        ch.pipeline().addLast("encoder", new StringEncoder());

        // 属于ChannelInboundHandler，依照顺序执行
        ch.pipeline().addLast("decoder", new StringDecoder());
        /**
         * 自定义ChannelInboundHandlerAdapter
         */
        ch.pipeline().addLast(new BootNettyChannelInboundHandlerAdapter());

    }

}