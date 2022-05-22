package ru.job4j.cache;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CacheTest {
    private final Cache cache = new Cache();
    private final Base base = new Base(1, 1);

    @Test
    public void whenAddTheSame() {
        Base sameBase = new Base(1, 1);
        assertTrue(cache.add(base));
        assertFalse(cache.add(sameBase));
    }

    @Test
    public void whenAddTwiceAndDeleteTwiceThenIsEmpty() {
        Base base2 = new Base(2, 1);
        assertTrue(cache.add(base));
        assertTrue(cache.add(base2));
        cache.delete(new Base(2, 1));
        cache.delete(new Base(1, 1));
        assertTrue(cache.isEmpty());
    }

    @Test(expected = OptimisticException.class)
    public void whenUpdateThenException() {
        Base base2 = new Base(1, 2);
        cache.add(base);
        cache.update(base2);
    }

    @Test
    public void whenUpdate() {
        Base base2 = new Base(1, 1);
        assertTrue(cache.add(base));
        base.setName("Old");
        base2.setName("New");
        cache.add(base);
        assertTrue(cache.update(base2));
        assertThat(cache.getMemory().get(1).getName(), equalTo("New"));
        assertThat(cache.getMemory().get(1).getVersion(), equalTo(2));
        Base base3 = new Base(1, 2);
        base3.setName("SecondNew");
        assertTrue(cache.update(base3));
        assertThat(cache.getMemory().get(1).getName(), equalTo("SecondNew"));
        assertThat(cache.getMemory().get(1).getVersion(), equalTo(3));
    }
}