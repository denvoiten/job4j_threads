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
    public void whenSums() {
        RolColSum.Sums[] sums = sum(matrix);
        assertThat(sums[0].getRowSum(), equalTo(44));
        assertThat(sums[0].getColSum(), equalTo(9));
        assertThat(sums[1].getRowSum(), equalTo(25));
        assertThat(sums[1].getColSum(), equalTo(46));
        assertThat(sums[2].getRowSum(), equalTo(26));
        assertThat(sums[2].getColSum(), equalTo(40));
    }

    @Test
    public void whenAsyncSum() throws ExecutionException, InterruptedException {
        RolColSum.Sums[] sums = asyncSum(matrix);
        assertThat(sums[0].getRowSum(), equalTo(44));
        assertThat(sums[0].getColSum(), equalTo(9));
        assertThat(sums[1].getRowSum(), equalTo(25));
        assertThat(sums[1].getColSum(), equalTo(46));
        assertThat(sums[2].getRowSum(), equalTo(26));
        assertThat(sums[2].getColSum(), equalTo(40));
    }
}