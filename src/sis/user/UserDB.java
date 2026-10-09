package sis.user;

import sis.DB;

import java.util.Collection;
import java.util.HashMap;

public class UserDB implements DB<String, User> {

    private final HashMap<String, User> users = new HashMap<>();

    @Override
    public User addValue(User value) {
        return users.put(value.getUsername(), value);
    }

    @Override
    public User getValue(String key) {
        return users.get(key);
    }

    @Override
    public boolean hasKey(String key) {
        return users.containsKey(key);
    }

    @Override
    public Collection<User> getAllValues() {
        return users.values();
    }
}
