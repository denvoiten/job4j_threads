package ru.job4j.pool;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParallelIndexSearchTest {

    @Test
    public void whenArrayLengthLess10ThenFindIndex5() {
        Integer[] array = {1, 4, 7, 21, 3, 6, 8};
        ParallelIndexSearch<Integer> parallelIndexSearch =
                new ParallelIndexSearch<>(array, 0, array.length - 1, 21);
        assertEquals(3, parallelIndexSearch.search(array, 21));
    }

    @Test
    public void whenArrayOfIntegerSize100() {
        Integer[] array = new Integer[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        ParallelIndexSearch<Integer> parallelIndexSearch =
                new ParallelIndexSearch<>(array, 0, array.length - 1, 99);
        assertEquals(99, parallelIndexSearch.search(array, 99));
    }

    @Test
    public void whenArrayOfCharacterSize100() {
        Character[] array = new Character[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = (char) i;
        }
        ParallelIndexSearch<Character> parallelIndexSearch =
                new ParallelIndexSearch<>(array, 0, array.length - 1, 'c');
        assertEquals(99, parallelIndexSearch.search(array, 'c'));
    }

    @Test
    public void whenElementNotFind() {
        Character[] array = new Character[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = (char) i;
        }
        ParallelIndexSearch<Character> parallelIndexSearch =
                new ParallelIndexSearch<>(array, 0, array.length - 1, 'Ы');
        assertEquals(-1, parallelIndexSearch.search(array, 'Ы'));
    }
}