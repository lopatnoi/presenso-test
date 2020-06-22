package com.presenso.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MatricesOperationsImplTest {

    private MatricesOperationsImpl parallelOperations;
    private MatricesOperationsImpl sequentialOperations;

    @Before
    public void setUp() {
        parallelOperations = new MatricesOperationsImpl(true);
        sequentialOperations = new MatricesOperationsImpl(false);
    }

    @Test
    public void testMultiplyInParallel() {
        int n = 3;
        int[][] a = new int[n][n];
        a[0] = new int[]{1, 0, 0};
        a[1] = new int[]{1, 1, 0};
        a[2] = new int[]{1, 0, 1};

        int[][] b = new int[n][n];
        b[0] = new int[]{1, 0, 0};
        b[1] = new int[]{1, 1, 0};
        b[2] = new int[]{1, 0, 1};

        int[][] actual = parallelOperations.multiply(a, b);
        Assert.assertArrayEquals(new int[] {1,1,1}, actual[0]);
        Assert.assertArrayEquals(new int[] {1,1,1}, actual[1]);
        Assert.assertArrayEquals(new int[] {1,1,1}, actual[2]);
    }

    @Test
    public void testMultiplyInSequential() {
        int n = 3;
        int[][] a = new int[n][n];
        a[0] = new int[]{1, 0, 0};
        a[1] = new int[]{1, 1, 0};
        a[2] = new int[]{1, 0, 1};

        int[][] b = new int[n][n];
        b[0] = new int[]{1, 0, 0};
        b[1] = new int[]{1, 1, 0};
        b[2] = new int[]{1, 0, 1};

        int[][] actual = sequentialOperations.multiply(a, b);
        Assert.assertArrayEquals(new int[] {1,1,1}, actual[0]);
        Assert.assertArrayEquals(new int[] {1,1,1}, actual[1]);
        Assert.assertArrayEquals(new int[] {1,1,1}, actual[2]);
    }
}