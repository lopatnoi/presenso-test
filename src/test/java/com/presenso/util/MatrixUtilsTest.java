package com.presenso.util;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MatrixUtilsTest {

    @Test
    public void testTranspose() {
        int n = 3;
        int[][] matrix = new int[n][n];
        matrix[0] = new int[]{1, 0, 0};
        matrix[1] = new int[]{1, 1, 0};
        matrix[2] = new int[]{1, 0, 1};

        int[][] actual = MatrixUtils.transpose(matrix);
        assertArrayEquals(new int[]{1, 1, 1}, actual[0]);
        assertArrayEquals(new int[]{0, 1, 0}, actual[1]);
        assertArrayEquals(new int[]{0, 0, 1}, actual[2]);
    }
}