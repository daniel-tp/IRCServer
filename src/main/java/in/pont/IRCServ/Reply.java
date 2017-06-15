package in.pont.IRCServ;

public enum Reply {
    RPL_WELCOME(001, ":Welcome to the Internet Relay Network %s!%s@%s"),
    ERR_NONICKNAMEGIVEN(431, ":No Nickname given"),
    ERR_UNKNOWNCOMMAND(421, "%s :Unknown command"),
    ERR_NEEDMOREPARAMS(461, "%s :Not enough parameters"),
    ERR_ALREADYREGISTERED(462, ":Unauthorized command (already registered)"),
    ERR_ERRONEUSNICKNAME(432, "%s :Erroneus nickname");
    final int ID;
    final String msg;
    Reply(int ID, String msg){
        this.ID = ID;
        this.msg = msg;
    }
    public String getMessage(Object... input){
        return String.format(msg, input);
    }
    public String getMessage(){
        return msg;
    }
}
