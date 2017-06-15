package in.pont.IRCServ;

import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String nickname = null;
    private String username = null;
    private ChannelHandlerContext conn = null;
    private boolean registered = false;
    final Logger logger = LoggerFactory.getLogger(IRCDaemon.class);
    public User (ChannelHandlerContext connection){
        conn = connection;
    }
    public void rcvMsg(Message m){
        if(IRCDaemon.CmdMap.containsKey(m.type)){
            Command cmd;
            cmd = IRCDaemon.CmdMap.get(m.type);
            String[] params = m.params.toArray(new String[m.params.size()]);
            HashMap cmdParams = cmd.params(this, params);
            if(cmdParams == null){
                return;
            }
            cmd.use(this, cmdParams);
        }else if(registered){
            sendMsg(Reply.ERR_UNKNOWNCOMMAND, m.type);
        }

    }
    public void sendMsg(String strm){

    }
    public void sendMsg(Message m){
        logger.info("Sending message: {}", m);
        conn.channel().writeAndFlush(m);
    }
    public void sendMsg(Reply rep){
        sendMsg(new Message(rep.ID, rep.getMessage()));
    }
    public void sendMsg(Reply rep, Object... input){
        sendMsg(new Message(rep.ID, rep.getMessage(input)));

    }
    public void setNickname(String nick){
        logger.info("Setting nickname from {} to {}", nickname, nick);
        if(nick.length() <= 9) {
            this.nickname = nick;
        }
    }
}
