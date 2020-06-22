package com.presenso;

import com.presenso.service.MatricesOperations;
import com.presenso.service.MatricesOperationsImpl;
import com.presenso.util.MatrixUtils;

import static com.presenso.util.MatrixConstraints.MAX_SIZE;
import static com.presenso.util.MatrixConstraints.MIN_SIZE;

public class Application {

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Should provide size of matrices");
        }

        int n = Integer.parseInt(args[0]);
        if (n < MIN_SIZE || n > MAX_SIZE) {
            throw new IllegalArgumentException("Size should be in range [" + MIN_SIZE + "," + MAX_SIZE + "]");
        }

        int[][] a = MatrixUtils.fillMatrix(n);
        int[][] b = MatrixUtils.fillMatrix(n);
        int[][] transposedB = MatrixUtils.transpose(b);

        Application app = new Application();
        app.measure(true, a, transposedB);
        app.measure(false, a, transposedB);
    }

    private void measure(boolean parallel, int[][] first, int[][] second) {
        MatricesOperations matricesOperations = new MatricesOperationsImpl(parallel);
        long start = System.currentTimeMillis();
        matricesOperations.multiply(first, second);
        long stop = System.currentTimeMillis();
        System.out.printf("%s method, time: %dms.%n", parallel ? "parallel" : "sequential", stop - start);
    }
}
