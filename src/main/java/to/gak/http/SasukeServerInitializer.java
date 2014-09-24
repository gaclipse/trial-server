package to.gak.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslContext;

public class SasukeServerInitializer extends
		ChannelInitializer<SocketChannel> {

	private final SslContext sslCtx;

	public SasukeServerInitializer(SslContext sslCtx) {
		this.sslCtx = sslCtx;
	}

	@Override
	public void initChannel(SocketChannel ch) {
		ChannelPipeline channelPipeline = ch.pipeline();
		if (sslCtx != null) {
			channelPipeline.addLast(sslCtx.newHandler(ch.alloc()));
		}
		channelPipeline.addLast(new HttpRequestDecoder());
		channelPipeline.addLast(new HttpResponseEncoder());
		channelPipeline.addLast(new SasukeServerHandler());
	}
}