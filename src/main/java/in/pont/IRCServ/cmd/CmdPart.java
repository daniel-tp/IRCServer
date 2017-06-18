package in.pont.IRCServ.cmd;

import in.pont.IRCServ.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class CmdPart extends Command {
    final Logger logger = LoggerFactory.getLogger(IRCServerUserHandler.class);
    @Override
    public String[] hook() {
        return new String[]{"PART"};
    }

    @Override
    public HashMap<String, Object> params(User user, String[] params) {
        if(params.length==0){
            user.sendMsg(Reply.ERR_NEEDMOREPARAMS, "PART");
            return null;
        }
        String[] channelNames = params[0].split(",");
        for(String channel : channelNames){
            Channel chan =  IRCDaemon.channelMap.get(channel);
            if(chan==null){
                logger.debug("No channel found when parting: {}",channel);
                user.sendMsg(Reply.ERR_NOSUCHCHANNEL, channel);
                return null;
            }
            if(!chan.hasUser(user)){
                user.sendMsg(Reply.ERR_NOTONCHANNEl, channel);
                return null;
            }
        }
        HashMap<String, Object> prms = new HashMap<>();

        prms.put("channels", channelNames);
        if(params.length==2){
            prms.put("message", params[1]);
        }
        return prms;
    }

    @Override
    public void use(User user, HashMap params) {
        String message = (String)params.get("message");
        for(String chanName : (String[])params.get("channels")) {
            Channel chan =  IRCDaemon.channelMap.get(chanName); // always gets a channel, new or otherwise
            for(User other : chan.getJoinedList()){
                if(message!=null) {
                    other.sendMsg(new Message(user.getUniqueID(), "PART", chanName, ":" + message));
                }else{
                    other.sendMsg(new Message(user.getUniqueID(), "PART", chanName));
                }
            }
            chan.removeUser(user);
        }
    }
}
