package in.pont.IRCServ.cmd;

import in.pont.IRCServ.Command;
import in.pont.IRCServ.IRCDaemon;
import in.pont.IRCServ.Reply;
import in.pont.IRCServ.User;

import java.util.HashMap;

public class CmdUser extends Command {

    @Override
    public String[] hook() {
        return new String[]{"USER"};
    }

    @Override
    public HashMap<String, Object> params(User user, String[] params) {
        if(params.length<4){
            user.sendMsg(Reply.ERR_NEEDMOREPARAMS, "USER");
            return null;
        }
        HashMap<String, Object> prms = new HashMap<>();
        //Hostname/servername only implemented for server to server communication
        prms.put("username", params[0]);
        prms.put("realname", params[3]);
        return prms;
    }

    @Override
    public void use(User user, HashMap params) {
        if(user.isRegistered()){
            user.sendMsg(Reply.ERR_ALREADYREGISTERED);
            return;
        }
        user.setUsername((String)params.get("username"));
        user.setRealname((String)params.get("realname"));
        if(!user.isRegistered()){
            if(user.getNickname()!= null){
                IRCDaemon.userList.add(user);
                user.setRegistered(true);
                user.sendWelcome();
            }
        }
    }
}
