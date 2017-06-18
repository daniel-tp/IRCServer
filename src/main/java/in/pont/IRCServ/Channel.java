package in.pont.IRCServ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Channel {
    private String name = null;
    private String topic = null;
    private String password = null;
    private List<User> joined = new ArrayList<>();
    final Logger logger = LoggerFactory.getLogger(Channel.class);

    public Channel(String name){
        this.name = name;
    }
    public void join(User user){
        logger.info("Adding user {} to channel {}", user.getNickname(), name);
        joined.add(user);

    }
    public void sendTopic(User user){
        logger.debug("Sending topic for channel {} to user {}", topic, user.getNickname());
        if(topic==null){
            user.sendMsg(Reply.RPL_NOTOPIC,user.getNickname(), name);
        }else{
            user.sendMsg(Reply.RPL_TOPIC, user.getNickname(),name, topic);
        }
    }
    public void sendNames(User user){
        logger.debug("Sending names for channel {} to user {}", topic, user.getNickname());
        StringBuilder nameList = new StringBuilder();
        for(User other : joined){
            nameList.append(other.getNickname()+" ");
        }
        user.sendMsg(Reply.RPL_NAMREPLY, user.getUniqueID(),name, nameList.toString()); //Will need to be chunked into multiple calls.
        user.sendMsg(Reply.RPL_ENDOFNAMES, user.getNickname(),name);
    }
    public void removeUser(User user){
        //Do any messaging here?
        logger.debug("Removing user {} from channel {}", user.getNickname(), name);
        joined.remove(user);
    }
    public boolean hasUser(User user){
        return joined.contains(user);
    }
    public List<User> getJoinedList(){
        return joined;
    }
}
