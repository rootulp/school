package hw2;

public class MatrixWorker extends Thread {

  private MatrixMax matrixMax;
  private int rowNum;

  public MatrixWorker(MatrixMax matrixMax, int rowNum) {
    this.matrixMax = matrixMax;
    this.rowNum = rowNum;
  }

  public void run() {
    int max = findMax(matrixMax.getRow(rowNum));
    matrixMax.rowMaxes[rowNum] = max;
  }

  private int findMax(int[] row) {
    int max = row[0];
    for (int i = 0; i < row.length; i++) {
        if (row[i] > max) {
          max = row[i];
        }
    }
    return max;
  }
}
