package in.pont.IRCServ;

public enum Reply {
    RPL_WELCOME(1, "%1$s :Welcome to the Internet Relay Network %1$s!%2$s@%3$s"),
    RPL_TOPIC(332, "%s %s :%s"),
    RPL_NOTOPIC(331, "%s %s :No topic is set"),
    RPL_NAMREPLY(353, "%s = %s :%s"),
    RPL_ENDOFNAMES(366, "%s %s :End of /NAMES list"),
    ERR_NONICKNAMEGIVEN(431, ":No Nickname given"),
    ERR_UNKNOWNCOMMAND(421, "%s :Unknown command"),
    ERR_NEEDMOREPARAMS(461, "%s :Not enough parameters"),
    ERR_ALREADYREGISTERED(462, ":Unauthorized command (already registered)"),
    ERR_ERRONEUSNICKNAME(432, "%s :Erroneus nickname"),
    ERR_NOTONCHANNEl(442, "%s :You're not on that channel"),
    ERR_NOSUCHCHANNEL(403, "%s :No such channel");


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
