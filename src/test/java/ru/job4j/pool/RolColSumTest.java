package ru.job4j.pool;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static ru.job4j.pool.RolColSum.asyncSum;
import static ru.job4j.pool.RolColSum.sum;

public class RolColSumTest {
    private final int[][] matrix = {
            {2, 31, 11},
            {6, 10, 9},
            {1, 5, 20}};

    @Test
    public void whenAsyncSum() throws ExecutionException, InterruptedException {
        assertThat(asyncSum(matrix)[0].getRowSum(), equalTo(44));
        assertThat(asyncSum(matrix)[0].getColSum(), equalTo(9));
        assertThat(asyncSum(matrix)[1].getRowSum(), equalTo(25));
        assertThat(asyncSum(matrix)[1].getColSum(), equalTo(46));
        assertThat(asyncSum(matrix)[2].getRowSum(), equalTo(26));
        assertThat(asyncSum(matrix)[2].getColSum(), equalTo(40));
    }

    @Test
    public void whenSums() {
        assertThat(sum(matrix)[0].getRowSum(), equalTo(44));
        assertThat(sum(matrix)[0].getColSum(), equalTo(9));
        assertThat(sum(matrix)[1].getRowSum(), equalTo(25));
        assertThat(sum(matrix)[1].getColSum(), equalTo(46));
        assertThat(sum(matrix)[2].getRowSum(), equalTo(26));
        assertThat(sum(matrix)[2].getColSum(), equalTo(40));
    }
}