package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final int from;
    private final int to;
    private final T el;

    public ParallelIndexSearch(T[] array, int from, int to, T el) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.el = el;
    }

    @Override
    protected Integer compute() {
        int rsl = -1;
        if (to - from <= 10) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(el)) {
                    rsl = i;
                    break;
                }
            }
            return rsl;
        }
        int mid = (from + to) / 2;
        ParallelIndexSearch<T> leftParallelSearch =
                new ParallelIndexSearch<>(array, from, mid, el);
        ParallelIndexSearch<T> rightParallelSearch =
                new ParallelIndexSearch<>(array, mid + 1, to, el);
        leftParallelSearch.fork();
        rightParallelSearch.fork();
        return Math.max(leftParallelSearch.join(), rightParallelSearch.join());
    }

    public int search(T[] array, T el) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelIndexSearch<>(array, 0, array.length - 1, el));
    }
}
