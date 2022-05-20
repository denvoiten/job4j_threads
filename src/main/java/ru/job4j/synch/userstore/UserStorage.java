package ru.job4j.synch.userstore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage implements Storage, Transfer {
    @GuardedBy("this")
    private final Map<Integer, User> users = new HashMap<>();

    @Override
    public synchronized boolean add(User user) {
        return users.putIfAbsent(user.getId(), user) == null;
    }

    @Override
    public synchronized boolean update(User user) {
        return users.replace(user.getId(), user) != null;
    }

    @Override
    public synchronized boolean delete(User user) {
        return users.remove(user.getId(), user);
    }

    @Override
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User sender = users.get(fromId);
        User recipient = users.get(toId);
        boolean rsl = (fromId != toId
                && sender != null
                && recipient != null
                && sender.getAmount() >= amount);
        if (rsl) {
            sender.setAmount(sender.getAmount() - amount);
            recipient.setAmount(recipient.getAmount() + amount);
        }
        return rsl;
    }
}
