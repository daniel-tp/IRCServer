package in.pont.IRCServ;

import io.netty.channel.ChannelHandlerContext;

public class User {
    private String nickname = null;
    private String username = null;
    private ChannelHandlerContext conn = null;
    private boolean registered = false;
    public User (ChannelHandlerContext connection){
        conn = connection;
    }
    public void rcvMsg(Message m){

    }
    public void sendMsg(String strm){

}
    public void sendMsg(Message m){

    }
    public void sendMsg(Reply rep){
        conn.channel().writeAndFlush(rep.getMessage());

    }
    public void sendMsg(Reply rep, Object... input){
        conn.channel().writeAndFlush(rep.getMessage(input));

    }
    public void setNickname(String nick){
        if(nick.length() <= 9) {
            this.nickname = nick;
        }
    }
}
