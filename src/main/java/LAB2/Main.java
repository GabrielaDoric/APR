package LAB2;

import LAB1.Matrix;

public class Main {
    public static void main(String[] args) {
        String path="C:/Users/Gabriela/Desktop/APR/src/main/java/LAB2/x0.txt";
        Matrix a=null;
        Matrix m1=a.loadMatrix(path);

        m1.printMatrix();
    }
}
