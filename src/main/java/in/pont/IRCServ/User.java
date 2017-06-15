package in.pont.IRCServ;

import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String nickname = null;
    private String username = null;
    private String realname = null;
    private String hostname = null;
    private ChannelHandlerContext conn = null;
    private boolean registered = false;
    final Logger logger = LoggerFactory.getLogger(IRCDaemon.class);
    public User (ChannelHandlerContext connection){
        conn = connection;
        hostname = ((InetSocketAddress)connection.channel().remoteAddress()).getHostName();
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
        }else if(isRegistered()){
            sendMsg(Reply.ERR_UNKNOWNCOMMAND, m.type);
        }

    }
    public void sendWelcome(){
        sendMsg(Reply.RPL_WELCOME, nickname, username, hostname);
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
        if(isRegistered()){
            sendMsg(new Message(rep.ID, nickname+ " "+rep.getMessage(input)));
        }else{
            sendMsg(new Message(rep.ID, rep.getMessage(this, input)));
        }


    }
    public void setNickname(String nick){
        logger.info("Setting nickname from {} to {}", nickname, nick);
        if(nick.length() <= 9) {
            this.nickname = nick;
        }
    }
    public String getNickname(){
        return nickname;
    }
    public void setUsername(String user){
        logger.info("Setting username from {} to {}", username, user);
        this.username = user;
    }
    public void setRealname(String name){
        logger.info("Setting realname from {} to {}", realname, name);
        this.realname = name;
    }
    public boolean isRegistered(){
        return registered;
    }
    public void setRegistered(boolean set){
        registered = set;
    }
}
