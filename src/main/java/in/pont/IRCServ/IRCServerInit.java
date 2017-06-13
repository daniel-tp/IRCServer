package in.pont.IRCServ;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * Created by Daniel on 09/06/2017.
 */
public class IRCServerInit extends ChannelInitializer<SocketChannel> {
    private static final StringDecoder strDecode = new StringDecoder(CharsetUtil.UTF_8);
    private static final StringEncoder strEncode = new StringEncoder(CharsetUtil.UTF_8);
    private static final IRCEncoder ircEncode = new IRCEncoder();
    private static final IRCDecoder ircDecode = new IRCDecoder();

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(
                new LineBasedFrameDecoder(512),
                strDecode,
                ircDecode,
                strEncode,
                ircEncode,
                new IRCServerUserHandler()
        );
    }
}
