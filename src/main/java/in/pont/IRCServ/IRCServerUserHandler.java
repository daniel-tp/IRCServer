package in.pont.IRCServ;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class IRCServerUserHandler extends ChannelInboundHandlerAdapter {
    final Logger logger = LoggerFactory.getLogger(IRCServerUserHandler.class);
    private User user = null;
    @Override
    public void channelActive(ChannelHandlerContext ctx){
        logger.info("New connection from {}", ctx.channel().remoteAddress());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        // Discard the received data silently.
        try {
            Message in = (Message)msg;
            if(user == null){
                logger.debug("New user connected");
                user = new User(ctx);
            }
            logger.debug("Received message: {}", msg);
            user.rcvMsg(in);
        } finally {
            ReferenceCountUtil.release(msg); // (2)
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx){
        //Should do QUIT here
        if(user!= null){
            logger.info("Removed {} from userlist on disconnect", user.getNickname());
            IRCDaemon.userList.remove(user);
            for(Channel channel : IRCDaemon.channelMap.values()){
                if(channel.hasUser(user)){
                    channel.removeUser(user);
                }
            }
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("Thrown:", cause);

        ctx.close();
    }
}