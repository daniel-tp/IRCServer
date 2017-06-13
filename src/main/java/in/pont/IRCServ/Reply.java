package in.pont.IRCServ;

public enum Reply {
    RPL_WELCOME(001, "Welcome to the Internet Relay Network %s!%s@%s"),
    ERR_NONICKNAMEGIVEN(431, "No Nickname given");
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
