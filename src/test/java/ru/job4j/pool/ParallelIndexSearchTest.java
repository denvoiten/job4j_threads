package ru.job4j.pool;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParallelIndexSearchTest {

    @Test
    public void whenArrayLengthLess10ThenFindIndex5() {
        Integer[] array = {1, 4, 7, 21, 3, 6, 8};
        assertEquals(3, ParallelIndexSearch.search(array, 21));
    }

    @Test
    public void whenArrayOfIntegerSize100() {
        Integer[] array = new Integer[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        assertEquals(99, ParallelIndexSearch.search(array, 99));
    }

    @Test
    public void whenArrayOfCharacterSize100() {
        Character[] array = new Character[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = (char) i;
        }
        assertEquals(99, ParallelIndexSearch.search(array, 'c'));
    }

    @Test
    public void whenElementNotFind() {
        Character[] array = new Character[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = (char) i;
        }
        assertEquals(-1, ParallelIndexSearch.search(array, 'Ы'));
    }

    @Test
    public void whenArrayOfString() {
        String[] array = {"Первый", "Второй", "Третий", "Четвертый", "Пятый", "Шестой", "Седьмой"};
        assertEquals(6, ParallelIndexSearch.search(array, "Седьмой"));
    }
}