package in.pont.IRCServ.cmd;

import in.pont.IRCServ.Command;
import in.pont.IRCServ.Reply;
import in.pont.IRCServ.User;

import java.util.HashMap;

import java.util.Map;

public class CmdNick extends Command {
    @Override
    public String[] hook() {
        return new String[]{"NICK"};
    }

    @Override
    public HashMap<String, Object> params(User user, String[] params) {
        if(params.length==0){
            user.sendMsg(Reply.ERR_NONICKNAMEGIVEN);
            return null;
        }
        if(params[0].length()>32){
            user.sendMsg(Reply.ERR_ERRONEUSNICKNAME, params[0]);
            return null;
        }
        HashMap<String, Object> prms = new HashMap<>();
        prms.put("nickname", params[0]);
        return prms;
    }

    @Override
    public void use(User user, HashMap params) {
        user.setNickname((String)params.get("nickname"));
    }
}
