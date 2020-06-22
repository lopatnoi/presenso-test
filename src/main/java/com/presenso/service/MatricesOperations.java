package com.presenso.service;

/**
 * Class for operations on matrices.
 */
public interface MatricesOperations {

    /**
     * Provides operation of matrix multiplication from two matrices.
     * @param a first matrix
     * @param b second matrix
     * @return new matrix as result
     */
    int[][] multiply(int[][] a, int[][] b);
}
