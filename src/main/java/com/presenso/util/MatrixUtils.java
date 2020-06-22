package com.presenso.util;

import java.util.Random;
import java.util.stream.IntStream;

import static com.presenso.util.MatrixConstraints.*;

/**
 * Helper class with matrix operations.
 */
public final class MatrixUtils {

    /**
     * Converts matrix to transposed matrix.
     *
     * @param matrix matrix that will be transposed
     * @return new matrix transposed to current
     */
    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] tMatrix = new int[rows][columns];

        IntStream.range(0, rows).parallel()
                .forEach(i -> IntStream.range(0, columns)
                        .forEach(j -> tMatrix[j][i] = matrix[i][j]));
        return tMatrix;
    }

    /**
     * Prepares matrix with values 0, 1
     * @param n size of matrix
     * @return filled matrix
     */
    public static int[][] fillMatrix(int n) {
        Random rm = new Random();
        int[][] matrix = new int[n][n];

        IntStream.range(0, n).parallel()
                .forEach(i -> IntStream.range(0, n).parallel()
                        .forEach(j -> matrix[i][j] = Math.abs(rm.nextInt()) % MOD));
        return matrix;
    }
}
