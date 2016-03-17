package hw2;

import java.io.Serializable;

public class MatrixMax implements Serializable {

  private static final long serialVersionUID = 1L;
  private int[][] matrix;
  public volatile int[] rowMaxes;

  public MatrixMax(int matrixSize) {
    this.matrix = buildMatrix(matrixSize);
    this.rowMaxes = new int[matrixSize];
  }

  public void print() {
    System.out.println("Matrix:");
    printMatrix(this.getMatrix());
    System.out.println();
  }

  // get Matrix
  public int[][] getMatrix() {
    return this.matrix;
  }

  // get Row
  public int[] getRow(int rowNum) {
    return this.matrix[rowNum];
  }

  public static int findMax(int[] row) {
    int max = row[0];
    for (int i = 0; i < row.length; i++) {
        if (row[i] > max) {
          max = row[i];
        }
    }
    return max;
  }

  // prints a row
  public static void printRow(int[] row) {
    for (int i = 0; i < row.length; i++) {
          System.out.print(row[i] + " ");
      }
    System.out.println();
  }

  // prints the matrix
  public static void printMatrix(int[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
        printRow(matrix[i]);
    }
  }

  // creates an m x m matrix and populates it with random values from 0 to 100
  private int[][] buildMatrix(int m) {
    int[][] matrix = new int[m][m];
    for(int x = 0; x <  m; x++) {
      for(int y = 0; y <  m; y++) {
            matrix[x][y] = (int)(Math.random() * 100);
      }
        }
    return matrix;
  }

  public static void main(String[] args) throws InterruptedException {
    // Matrix Size
    int MATRIX_SIZE = 3;

    // Create matrixMax
    MatrixMax matrixMax = new MatrixMax(MATRIX_SIZE);

    // Print Matrix
    matrixMax.print();

    // Create array of workers
    Thread[] workers = new Thread[MATRIX_SIZE];
    for (int i = 0; i < MATRIX_SIZE; i++) {
      workers[i] = new MatrixWorker(matrixMax, i);
    }

    // Start workers
    for (Thread worker : workers) {
      worker.start();
    }

    // Wait for workers to finish
    for (Thread worker : workers) {
      worker.join();
    }

    // Print Row Maxes
    System.out.println("Row Maxes:");
    printRow(matrixMax.rowMaxes);
    System.out.println();

    // Print Matrix Max
    System.out.println("Matrix Max:");
    System.out.println(MatrixMax.findMax(matrixMax.rowMaxes));
    System.out.println();
  }
}
