package in.pont.IRCServ;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Created by Daniel on 02/06/2017.
 */
@ChannelHandler.Sharable
public class IRCEncoder extends MessageToMessageEncoder<Message> { // (1)
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, List<Object> out) throws Exception {
        out.add(message.getEncoded());
    }
}