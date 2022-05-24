package ru.job4j.pool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public int getColSum() {
            return colSum;
        }
    }

    private static Sums getSum(int[][] matrix, int index) {
        int rowSum = 0;
        int colSum = 0;
        for (int j = 0; j < matrix.length; j++) {
            rowSum += matrix[index][j];
            colSum += matrix[j][index];
        }
        return new Sums(rowSum, colSum);
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] rsl = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            rsl[i] = getSum(matrix, i);
        }
        return rsl;
    }

    public static Sums[] asyncSum(int[][] matrix) throws InterruptedException, ExecutionException {
        Sums[] rsl = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            rsl[i] = getTask(matrix, i).get();
        }
        return rsl;
    }

    private static CompletableFuture<Sums> getTask(int[][] matrix, int index) {
        return CompletableFuture.supplyAsync(() -> getSum(matrix, index));
    }
}
