package ru.job4j.synch.userstore;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenTransfer() {
        UserStorage userStorage = new UserStorage();
        User user = new User(1, 1000);
        User user2 = new User(2, 500);
        User user3 = new User(3, 2500);
        userStorage.add(user);
        userStorage.add(user2);
        userStorage.add(user3);
        userStorage.transfer(1, 3, 1000);
        assertEquals(0, user.getAmount());
        assertEquals(3500, user3.getAmount());
        assertEquals(500, user2.getAmount());
        userStorage.transfer(3, 2, 1000);
        userStorage.transfer(2, 1, 1000);
        assertEquals(2500, user3.getAmount());
        assertEquals(500, user2.getAmount());
        assertEquals(1000, user.getAmount());
    }
}