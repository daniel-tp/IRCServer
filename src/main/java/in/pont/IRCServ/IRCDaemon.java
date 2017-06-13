package in.pont.IRCServ;
import in.pont.IRCServ.cmd.CmdNick;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

public class IRCDaemon {
    public static Properties config;
    Command[] CmdList = {new CmdNick()};
    public static void main(String[] args) throws Exception {
        final Logger logger = LoggerFactory.getLogger(IRCDaemon.class);
        config = loadConfig();
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
            logger.debug("Read Port as: {}", port);
        } else {
            port = 6667;
            logger.debug("Using default port {}", port);
        }
        logger.info("Starting server at port {}", port);
        new IRCServer(port).run();
    }
    public void loadCommands(){

    }
    public static Properties loadConfig(){
        Properties config = new Properties();
        if(!new File("irc.conf").exists()){
            try(OutputStream ircConf = new FileOutputStream("irc.conf")){

                config.setProperty("servername", "testServer");
                config.store(ircConf, null);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            try (InputStream confFile = new FileInputStream("irc.conf")) {
                config.load(confFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return config;
    }
}
