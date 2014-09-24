package to.gak.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class SasukeServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	public void initChannel(SocketChannel ch) {
		ChannelPipeline channelPipeline = ch.pipeline();
		channelPipeline.addLast(new HttpRequestDecoder());
		channelPipeline.addLast(new HttpResponseEncoder());
		channelPipeline.addLast(new SasukeServerHandler());
	}
}