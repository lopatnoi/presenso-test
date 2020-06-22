package com.presenso.service;

import com.presenso.domain.Result;
import com.presenso.domain.Site;

import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Implementation of matrices operations service.
 */
public class MatricesOperationsImpl implements MatricesOperations {

    private static final int MOD = 2;

    private boolean parallel;

    public MatricesOperationsImpl(boolean parallel) {
        this.parallel = parallel;
    }

    @Override
    public int[][] multiply(int[][] a, int[][] b) {
        int rows = a.length;
        int columns = b[0].length;

        // convert to sites, each contains it position and data to multiply
        Stream<Site> sites = IntStream.range(0, rows).parallel()
                .mapToObj(i -> IntStream.range(0, columns)
                        .mapToObj(j -> new Site(i, j, a[i], b[j])))
                .flatMap(Function.identity());

        // callable operation for each site
        Stream<Callable<Result>> tasks = sites.map(site ->
                () -> {
                    int[] r = site.getRows();
                    int[] c = site.getCols();

                    int value = 0;
                    for (int i = 0; i < rows; i++) {
                        if (r[i] == 0 || c[i] == 0) continue;
                        if (value == 1) continue;
                        value += (r[i] * c[i]) % MOD;
                    }

                    return new Result(site.getRowPosition(), site.getColumnPosition(), value);
                }
        );

        // number of threads in parallel equal to available processors * 2 otherwise 1
        int nThreads = parallel ? Runtime.getRuntime().availableProcessors() * 2 : 1;
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        List<Future<Result>> futures;
        try {
            futures = executorService.invokeAll(tasks.collect(Collectors.toList()));
        } catch (InterruptedException e) {
            throw new RuntimeException("Invoke of execution service fail", e);
        }
        shutdown(executorService);

        // convert calculated result in result matrix
        int[][] result = new int[rows][columns];
        for (Future<Result> future : futures) {
            try {
                Result cell = future.get();
                result[cell.getRow()][cell.getColumn()] = cell.getValue();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException("Result of execution service fail.", e);
            }
        }
        return result;
    }

    private void shutdown(ExecutorService executorService) {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
