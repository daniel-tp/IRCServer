package in.pont.IRCServ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Message {
    final Logger logger = LoggerFactory.getLogger(Message.class);
    String prefix;
    String type;
    List<String> params = new ArrayList<>();

    public Message(String message){
        Pattern p = Pattern.compile("^(?::([^\\r\\n ]+) +|())([^\\r\\n ]+)(?: +([^:\\r\\n ]+[^\\r\\n ]*(?: +[^:\\r\\n ]+[^\\r\\n ]*)*)|())?(?: +:([^\\r\\n]*)| +())?[\\r\\n]*$");
        Matcher m = p.matcher(message);
        if(m.find()){
            prefix = m.group(1);
            type = m.group(3);
            if(m.group(4)!= null) {
                params.addAll(Arrays.asList(m.group(4).split(" ")));
            }
            if(m.group(6)!= null) {
                params.add(m.group(6));
            }
            logger.debug("Message parsed: {} [{}] [{}]", m.group(0), m.group(1), m.group(3));
        }else{
            logger.debug("Message received but not parsed: {}", message);
        }
    }
    public Message(int type, String... params){
        this(String.valueOf(type), params);
    }
    public Message(String type, String... params){
        this.prefix = ":"+IRCDaemon.config.getProperty("serverName");
        this.type = type;
        this.params = Arrays.asList(params);

    }
    // :Name COMMAND parameter list
    // Trailing = Message
    public String getRaw(){
        return (prefix == null ? "" : prefix+" ")+ type+" "+String.join(" ", params);
    }
    public String getEncoded(){
        return getRaw()+"\r\n";
    }
    @Override
    public String toString(){
        return getRaw() + "(["+prefix+"] ["+type+"] ["+params+"])";
    }
}
