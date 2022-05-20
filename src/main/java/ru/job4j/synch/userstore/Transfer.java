package ru.job4j.synch.userstore;

public interface Transfer {
    boolean transfer(int fromId, int toId, int amount);
}
