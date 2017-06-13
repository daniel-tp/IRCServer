package in.pont.IRCServ;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * Created by Daniel on 02/06/2017.
 */
@ChannelHandler.Sharable
public class IRCDecoder extends MessageToMessageDecoder<String> { // (1)
    @Override
    protected void decode(ChannelHandlerContext ctx, String msg,
                       List<Object> out) throws Exception {

        out.add(new Message(msg)); // (4)
    }
}