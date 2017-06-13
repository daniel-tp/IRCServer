package in.pont.IRCServ;
import in.pont.IRCServ.cmd.CmdNick;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IRCDaemon {
    Command[] CmdList = {new CmdNick()};
    public static void main(String[] args) throws Exception {
        final Logger logger = LoggerFactory.getLogger(IRCDaemon.class);
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
}
