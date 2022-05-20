package ru.job4j.synch.userstore;

public interface Storage {

    boolean add(User user);

    boolean update(User user);

    boolean delete(User user);

}
