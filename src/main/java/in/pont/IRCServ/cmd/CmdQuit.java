package in.pont.IRCServ.cmd;

import in.pont.IRCServ.Command;
import in.pont.IRCServ.IRCServerUserHandler;
import in.pont.IRCServ.Reply;
import in.pont.IRCServ.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class CmdQuit extends Command{
    final Logger logger = LoggerFactory.getLogger(IRCServerUserHandler.class);
    @Override
    public String[] hook() {
        return new String[]{"QUIT"};
    }

    @Override
    public HashMap<String, Object> params(User user, String[] params) {
        HashMap<String, Object> prms = new HashMap<>();
        if(params.length==0){
            prms.put("message", user.getNickname());
        }else{
            prms.put("message", params[0]);
        }
        return prms;
    }

    @Override
    public void use(User user, HashMap params) {
        logger.debug("Quitting user {}", user.getNickname());
        user.quit(); // Maybe give message here?
        //In future message will be sent
    }
}
