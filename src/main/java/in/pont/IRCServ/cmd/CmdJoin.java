package in.pont.IRCServ.cmd;

import in.pont.IRCServ.*;

import java.util.HashMap;

public class CmdJoin extends Command {
    @Override
    public String[] hook() {
        return new String[]{"JOIN"};
    }

    @Override
    public HashMap<String, Object> params(User user, String[] params) {
        if(params.length==0){
            user.sendMsg(Reply.ERR_NEEDMOREPARAMS, "JOIN");
            return null;
        }

        String[] channels = params[0].split(",");
        for(String channel : channels){
            if(!channel.startsWith("#")){
                user.sendMsg(Reply.ERR_NOSUCHCHANNEL, channel);
                return null;
            }
        }
        HashMap<String, Object> prms = new HashMap<>();

        prms.put("channels", channels);
        //prms.put("passwords", params[3]);
        return prms;
    }

    @Override
    public void use(User user, HashMap params) {
        for(String chanName : (String[])params.get("channels")) {
            Channel chan = IRCDaemon.channelMap.computeIfAbsent(chanName, Channel::new);
            // always gets a channel, new or otherwise
            chan.join(user);
            for(User other : chan.getJoinedList()){
                other.sendMsg(new Message(user.getUniqueID(), "JOIN", chanName));
            }
            chan.sendTopic(user);
            chan.sendNames(user);
        }
    }
}
