package in.pont.IRCServ;

public enum Reply {
    RPL_WELCOME(001, "Welcome to the Internet Relay Network %s!%s@%s"),
    ERR_NONICKNAMEGIVEN(431, "No Nickname given"),
    ERR_UNKNOWNCOMMAND(421, "%s :Unknown command"),
    ERR_ERRONEUSNICKNAME(432, "%s :Erroneus nickname");
    final int ID;
    final String msg;
    Reply(int ID, String msg){
        this.ID = ID;
        this.msg = msg;
    }
    public String getMessage(Object... input){
        // Use strsubstitutor with number variables, put the object array into a hashmap and use the position values as keys?
        return String.format(msg, input);
    }
    public String getMessage(){
        return msg;
    }
}
