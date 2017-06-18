package in.pont.IRCServ;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReplyTest {
    @Test
    void getMessage() {
        String msg = Reply.RPL_WELCOME.getMessage("Nickname", "Username", "Hostname");
        assertEquals(msg, "Nickname :Welcome to the Internet Relay Network Nickname!Username@Hostname");
    }

}