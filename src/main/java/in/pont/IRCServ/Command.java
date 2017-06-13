package in.pont.IRCServ;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Command {

    public abstract String[] hook();
    public abstract Map<String, Object> params(User user, String[] params);
    public abstract void use(User user, HashMap params);
}
