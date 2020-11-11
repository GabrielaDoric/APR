package LAB1;

import java.util.Arrays;
import java.util.List;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Matrix {
    public static double EPSILON = 10e-6;
    private int rowLength;
    private int columnLength;
    private double[][] elements;
    int counter;

    public int getRowLength() {
        return rowLength;
    }

    public void setRowLength(int rowLength) {
        if (rowLength <= 0) {
            throw new IllegalArgumentException();
        }
        this.rowLength = rowLength;
    }

    public int getColumnLength() {
        return columnLength;
    }

    public void setColumnLength(int columnLength) {
        if (columnLength <= 0) {
            throw new IllegalArgumentException();
        }
        this.columnLength = columnLength;
    }

    public double[][] getElements() {
        double[][] newElements = new double[elements.length][elements[0].length];
        for (int i = 0; i < elements.length; i++) {
            newElements[i] = Arrays.copyOf(elements[i], elements[i].length);
        }
        return newElements;
    }

    public void setElements(double[][] elements) {
        setRowLength(elements.length);
        setColumnLength(elements[0].length);
        this.elements = elements;
    }

    public double getElement(int row, int column) {
        return elements[row][column];
    }

    public void setElement(int row, int column, double d) {
        elements[row][column] = d;
    }

    public double[] getRow(int row) {
        if (row < 0 || row > rowLength) {
            throw new IllegalArgumentException();
        }
        return elements[row];
    }

    public void setRow(int row, double[] value) {
        if (row < 0 || row > rowLength || value == null) {
            throw new IllegalArgumentException();
        }
        elements[row] = value;
    }

    public double[] getColumn(int column) {
        if (column < 0 || column >= columnLength) {
            throw new IllegalArgumentException();
        }
        double[] columnVector = new double[rowLength];
        for (int i = 0; i < rowLength; i++) {
            columnVector[i] = elements[i][column];
        }
        return columnVector;
    }

    public void setColumn(int column, double[] value) {
        if (column < 0 || column > columnLength || value == null) {
            throw new IllegalArgumentException();
        }
        int i = 0;
        for (i = 0; i < value.length; i++) {
            elements[i][column] = value[i];
        }
        for (; i < columnLength; i++) {
            elements[i][column] = 0.0;
        }
    }

    @Override
    public String toString() {
        return Arrays.deepToString(this.elements);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (getClass() != object.getClass()) {
            return false;
        }

        Matrix matrix = (Matrix) object;
        if (columnLength != matrix.columnLength)
            return false;
        if (rowLength != matrix.rowLength)
            return false;

        for (int i = 0; i < this.rowLength; i++) {
            for (int j = 0; j < this.columnLength; j++) {
                if (Math.abs(this.elements[i][j] - matrix.getElement(i, j)) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public Matrix(int rowLength, int columnLength) {
        this.rowLength = rowLength;
        this.columnLength = columnLength;
        this.elements = new double[rowLength][columnLength];
    }

    public Matrix(double[][] matrix) {
        this.rowLength = matrix.length;
        this.columnLength = matrix[0].length;
        this.elements = matrix;
    }

    public Matrix(int n) {
        this.rowLength = n;
        this.columnLength = n;
        for (int i = 0; i < n; i++) {
            elements[i][i] = 0;
        }
    }

    public Matrix(double... vector) {
        this.rowLength = 1;
        this.columnLength = vector.length;
        this.elements = new double[1][vector.length];
        for (int i = 0; i < vector.length; i++) {
            this.setElement(0, i, vector[i]);
        }

    }


    public Matrix(Matrix copiedMatrix) {
        this.rowLength = copiedMatrix.rowLength;
        this.columnLength = copiedMatrix.columnLength;
        this.elements = new double[rowLength][columnLength];
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {

                this.elements[i][j] = copiedMatrix.elements[i][j];
            }
        }
    }

    public static Matrix matrixOne(int n) {
        Matrix matrix = new Matrix(n, n);
        for (int i = 0; i < n; i++) {
            matrix.elements[i][i] = 1.0;
        }
        return matrix;
    }

    public static Matrix matrixZero(int n) {
        Matrix matrix = new Matrix(n, n);
        for (int i = 0; i < n; i++) {
            matrix.elements[i][i] = 0.0;
        }
        return matrix;
    }


    public void setToZero() {
        for (int i = 0; i < this.getColumnLength(); i++) {
            this.setElement(0, i, 0.0);
        }
    }

    public void identityMatrix(){
        for(int i = 0; i < this.getColumnLength(); i++) {
            this.setElement(i,i,1);
        }

    }

    public void setAllToZero() {
        for (int i = 0; i < this.getColumnLength(); i++) {
            this.setElement(0, i, 0.0);
        }
    }

    public void changeSizeOfMatrix(int rowLength, int columnLength) {
        if (this.rowLength < rowLength || this.columnLength < columnLength) {
            setElements(new double[rowLength][columnLength]);
        }

        this.rowLength = rowLength;
        this.columnLength = columnLength;
    }

    public Matrix clone() {
        Matrix m = new Matrix(rowLength, columnLength);
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                m.setElement(i, j, elements[i][j]);
            }
        }
        return m;
    }

    public static Matrix loadMatrix(String path) {
        Matrix matrixA = null;
        try {
            Charset charset = Charset.defaultCharset();
            List<String> lines = Files.readAllLines(Paths.get(path), charset);

            int rows = lines.size();
            int columns = lines.get(0).trim().split("\\s+").length;
            double[][] matrixB = new double[rows][columns];

            for (int i = 0; i < rows; i++) {
                String[] values = lines.get(i).trim().split("\\s+");
                if (values.length != columns) {
                    throw new Exception("Pogrešno zadana matrica");
                }
                for (int j = 0; j < columns; j++) {
                    matrixB[i][j] = Double.parseDouble(values[j]);
                }
            }
            matrixA = new Matrix(matrixB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matrixA;
    }

    public static void saveMatrix(String filename, Matrix matrix) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));

            for (int i = 0; i < matrix.rowLength; i++) {
                for (int j = 0; j < matrix.columnLength; j++) {
                    if (j == matrix.columnLength - 1) {
                        bw.write(Double.toString(matrix.getElement(i, j)));
                    } else {
                        bw.write(Double.toString(matrix.getElement(i, j)) + " ");
                    }
                }
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public void printMatrix() {
        System.out.println(this.toString());
    }


    public void addToMatrix(Matrix matrix) {
        if (matrix.getRowLength() != rowLength || matrix.getColumnLength() != columnLength) {
            System.out.println("Pogreška-> Matrice nisu istih dimnezija");
            throw new Error();
        }
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                elements[i][j] += matrix.getElement(i, j);
            }
        }
    }

    public void addToElement(int k, int l, double value) {
        double valueToAdd = this.getElement(k, l) + value;
        this.setElement(k, l, valueToAdd);
    }


    public Matrix add(Matrix matrix) {
        if (matrix.getRowLength() != rowLength || matrix.getColumnLength() != columnLength) {
            System.out.println("Pogreška-> Matrice nisu istih dimnezija");
            throw new Error();
        }
        Matrix m2 = new Matrix(rowLength, columnLength);
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                m2.setElement(i, j, elements[i][j] + matrix.getElement(i, j));
            }
        }
        return m2;
    }


    public Matrix addScalar(double skalar) {
        Matrix m2 = new Matrix(rowLength, columnLength);
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                m2.setElement(i, j, elements[i][j] + skalar);
            }
        }
        return m2;
    }

    public void addScalar2(double skalar) {
        Matrix m2 = new Matrix(rowLength, columnLength);
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                m2.setElement(i, j, elements[i][j] + skalar);
            }
        }
    }

    public void substractMatrix(Matrix matrix) {
        if (matrix.getRowLength() != rowLength || matrix.getColumnLength() != columnLength) {
            System.out.println("Pogreška-> Matrice nisu istih dimnezija");
            throw new Error();
        }
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                elements[i][j] -= matrix.getElement(i, j);
            }
        }
    }

    public Matrix substract(Matrix matrix) {
        if (matrix.getRowLength() != rowLength || matrix.getColumnLength() != columnLength) {
            System.out.println("Pogreška-> Matrice nisu istih dimnezija");
            throw new Error();
        }
        Matrix m2 = new Matrix(rowLength, columnLength);
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                m2.setElement(i, j, elements[i][j] - matrix.getElement(i, j));
            }
        }
        return m2;
    }

    public Matrix multiply(Matrix matrix) {
        if (columnLength != matrix.getRowLength()) {
            System.out.println("Pogreška -> Matrice nisu ulančane!");
            throw new Error();
        }

        double[][] matrixArray = new double[rowLength][matrix.getColumnLength()];
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < matrix.getColumnLength(); j++) {
                matrixArray[i][j] = 0;
                for (int k = 0; k < columnLength; k++) {
                    matrixArray[i][j] += elements[i][k] * matrix.getElement(k, j);
                }
            }
        }

        return new Matrix(matrixArray);
    }

    public void multiplyPOM(double scalar) {
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                elements[i][j] *= scalar;
            }
        }
    }

    public Matrix multiply(double scalar) {
        Matrix m2 = new Matrix(rowLength, columnLength);
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                m2.elements[i][j] = this.getElement(i, j) * scalar;
            }
        }
        return m2;
    }

    public void multiplyMatrix(double scalar) {
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                elements[i][j] *= scalar;
            }
        }
    }


    public void devide(double scalar) {
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                elements[i][j] /= scalar;
            }
        }
    }

    public double devideR(double scalar) {
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                elements[i][j] /= scalar;
            }
        }
        return elements[0][0];
    }

    public Matrix transpose() {
        double[][] transponedArray = new double[columnLength][rowLength];
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                transponedArray[j][i] = elements[i][j];
            }
        }
        return new Matrix(transponedArray);
    }


    public Matrix inverse() {
        if (rowLength != columnLength) {
            System.out.println("Pogreška -> rowLength != columnLength");
            throw new Error();
        }
        Matrix inverse = new Matrix(columnLength, rowLength);
        Matrix matrix = this.clone();
        int[] permutationVector = matrix.LUP();
        for (int i = 0; i < columnLength; i++) {
            Matrix e = new Matrix(rowLength, 1);
            e.setElement(i, 0, 1.0);
            e.permutation(permutationVector);
            Matrix y = matrix.forwardSubstitution(e);
            Matrix x = matrix.backwardSubstitution(y);
            inverse.setColumn(i, x.getRow(0));
        }
        return inverse;
    }

    public double determinant() {
        double determinant = 1.0;

        this.LUP();
        Matrix L = getL();
        Matrix U = getU();
        for (int i = 0; i < rowLength; i++) {
            determinant *= U.getElement(i, i) * L.getElement(i, i);
        }
        if (counter % 2 == 1) {
            determinant *= 1;
        }
        return determinant;
    }


    public void LU() {
        Matrix matrix = this.clone();

        for (int i = 0; i < columnLength - 1; i++) {
            for (int j = i + 1; j < rowLength; j++) {
                if (Math.abs(elements[i][i]) <= EPSILON) {
                    elements = matrix.getElements();
                    System.out.println("Pogreška -> Pivot je 0 ili jako blizu 0.");
                    throw new Error();
                }
                elements[j][i] /= elements[i][i];
                for (int k = i + 1; k < columnLength; k++) {
                    elements[j][k] -= elements[j][i] * elements[i][k];
                }
            }
        }
    }

    public int[] LUP() {
        Matrix matrix = this.clone();

        int[] permutationVector = new int[columnLength];
        for (int i = 0; i < rowLength; i++) {
            permutationVector[i] = i;
        }

        for (int i = 0; i < columnLength - 1; i++) {
            int pivot = i;
            for (int j = i + 1; j < rowLength; j++) {
                if (Math.abs(elements[j][i]) > Math
                        .abs(elements[pivot][i])) {
                    pivot = j;
                }
            }
            if (Math.abs(elements[pivot][i]) < EPSILON) {
                System.out.println(this.toString());
                elements = matrix.getElements();
                System.out.println("Pogreška -> pivot je 0 ili jako blizu 0.");
                throw new Error();
            }
            counter += switchRows(i, pivot);

            int tmp = permutationVector[i];
            permutationVector[i] = permutationVector[pivot];
            permutationVector[pivot] = tmp;

            for (int j = i + 1; j < rowLength; j++) {
                elements[j][i] /= elements[i][i];
                for (int k = i + 1; k < columnLength; k++) {
                    elements[j][k] -= elements[j][i] * elements[i][k];
                }
            }
        }
        return permutationVector;
    }


    public Matrix forwardSubstitution(Matrix b) {
        Matrix y = this.clone();

        double[] bRow = b.getColumn(0).clone();
        for (int i = 0; i < columnLength - 1; i++) {
            for (int j = i + 1; j < columnLength; j++) {
                bRow[j] -= y.getElement(j, i) * bRow[i];
            }
        }

        y.setRowLength(1);
        y.setElements(new double[1][y.getColumnLength()]);
        y.setRow(0, bRow);

        return y;
    }

    public Matrix backwardSubstitution(Matrix y) {
        Matrix x = this.clone();

        double[] bRow = y.getRow(0);
        for (int i = columnLength - 1; i >= 0; i--) {
            bRow[i] /= x.getElement(i, i);
            for (int j = 0; j < i; j++) {
                bRow[j] -= x.getElement(j, i) * bRow[i];
            }
        }

        x.setRowLength(1);
        x.setElements(new double[1][x.getColumnLength()]);
        x.setRow(0, bRow);
        return x;
    }

    public Matrix getL() {
        Matrix L = Matrix.matrixOne(rowLength);

        for (int i = 1; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                if (i > j) {
                    L.elements[i][j] = elements[i][j];
                }
            }
        }
        return L;
    }

    public Matrix getU() {
        Matrix U = Matrix.matrixZero(rowLength);
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                if (i <= j) {
                    U.elements[i][j] = elements[i][j];
                }
            }
        }
        return U;
    }

    public void permutation(int[] permutationVector) {
        double[][] permutatedArray = new double[rowLength][columnLength];
        for (int i = 0; i < rowLength; i++) {
            int permutatedRow = permutationVector[i];
            for (int j = 0; j < columnLength; j++) {
                permutatedArray[i][j] = elements[permutatedRow][j];
            }
        }
        elements = permutatedArray;
    }


    public int switchRows(int i, int j) {
        if (i == j) {
            return 0;
        }
        if (i < 0 || j < 0 || i >= rowLength || j >= rowLength) {
            throw new IllegalArgumentException();
        }

        double[] pom = elements[i];
        elements[i] = elements[j];
        elements[j] = pom;
        return 1;
    }

}