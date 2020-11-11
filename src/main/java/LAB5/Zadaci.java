package LAB5;

import LAB1.Matrix;


public class Zadaci {

//    public static int printAfter = 10;

    public static void main(String[] args) {

//        zad1();
//        zad2();
//        zad3();
        zad4();
    }

    public static void zad1() {

        int printAfter = 250;
        System.out.println("ZADATAK 1");

        String filename1 = "./src/main/java/LAB5/inputFiles/A1.txt";
        Matrix a = null;
        Matrix A = a.loadMatrix(filename1);

        String filename2 = "./src/main/java/LAB5/inputFiles/pocetnoStanje1.txt";
        Matrix b = null;
        Matrix x0 = b.loadMatrix(filename2);

        Matrix B = new Matrix(2, 2);
        B.setToZero();

        Matrix r = new Matrix(2, 1);
        r.setToZero();

        runAllMethods(A, B, x0, r, 0.01, 10, true, printAfter, true, "ZAD1");
    }


    public static void zad2() {

        int printAfter = 10;
        System.out.println("ZADATAK 2");

        String filename1 = "./src/main/java/LAB5/inputFiles/A2.txt";
        Matrix a = null;
        Matrix A = a.loadMatrix(filename1);

        String filename2 = "./src/main/java/LAB5/inputFiles/pocetnoStanje2.txt";
        Matrix b = null;
        Matrix x0 = b.loadMatrix(filename2);

        Matrix B = new Matrix(2, 2);
        B.setToZero();

        Matrix r = new Matrix(2, 1);
        r.setToZero();


        runAllMethods(A, B, x0, r, 0.1, 1, true, printAfter, false, "ZAD2");


    }


    public static void zad3() {
        int printAfter = 250;
        System.out.println("ZADATAK 3");

        String filename1 = "./src/main/java/LAB5/inputFiles/A3.txt";
        Matrix a = null;
        Matrix A = a.loadMatrix(filename1);

        String filename2 = "./src/main/java/LAB5/inputFiles/pocetnoStanje3.txt";
        Matrix b = null;
        Matrix x0 = b.loadMatrix(filename2);

        String filename3 = "./src/main/java/LAB5/inputFiles/B3.txt";
        Matrix pom = null;
        Matrix B = pom.loadMatrix(filename3);

        Matrix r = new Matrix(2, 1);
        r.setElement(0, 0, 1);
        r.setElement(1, 0, 1);

        runAllMethods(A, B, x0, r, 0.01, 10, true, printAfter, false, "ZAD3");
    }

    public static void zad4() {
        int printAfter = 100;
        System.out.println("ZADATAK 4");

        String filename1 = "./src/main/java/LAB5/inputFiles/A4.txt";
        Matrix a = null;
        Matrix A = a.loadMatrix(filename1);

        String filename2 = "./src/main/java/LAB5/inputFiles/pocetnoStanje4.txt";
        Matrix b = null;
        Matrix x0 = b.loadMatrix(filename2);

        String filename3 = "./src/main/java/LAB5/inputFiles/B4.txt";
        Matrix pom = null;
        Matrix B = pom.loadMatrix(filename3);

        Matrix r = new Matrix(2, 1);


        runAllMethods(A, B, x0, r, 0.01, 1, false, printAfter, false, "ZAD4");
    }


    static void runAllMethods(Matrix A, Matrix B, Matrix x0, Matrix r, double T, double interval, boolean rIsConstant, int printAfter, boolean printError, String task) {
        MethodEuler euler = new MethodEuler(printAfter);
        System.out.println("---------------------------------- Izravni Euler --------------------------");
        euler.integrate(A, B, x0, r, T, interval, rIsConstant, printError, task);

        MethodBackwardEuler backwardEuler = new MethodBackwardEuler(printAfter);
        System.out.println("---------------------------------- Obrnuti Euler --------------------------");
        backwardEuler.integrate(A, B, x0, r, T, interval, rIsConstant, printError, task);

        MethodRungeKutta rungeKutta = new MethodRungeKutta(printAfter);
        System.out.println("---------------------------------- Runge-Kutta ----------------------------");
        rungeKutta.integrate(A, B, x0, r, T, interval, rIsConstant, printError, task);

        MethodTrapezeRule trapezeMethod = new MethodTrapezeRule(printAfter);
        System.out.println("---------------------------------- Trapezni postupak ----------------------");
        trapezeMethod.integrate(A, B, x0, r, T, interval, rIsConstant, printError, task);

        MethodPECE PECE = new MethodPECE(printAfter, 1, "Euler", "Trapeze" );
        System.out.println("---------------------------------- PECE -----------------------------------");
        PECE.integrate(A, B, x0, r, T, interval, rIsConstant, printError, task);

        MethodPECE PECE2 = new MethodPECE(printAfter, 2, "Euler", "BackwardEuler");
        System.out.println("---------------------------------- PE(CE)2 --------------------------------");
        PECE2.integrate(A, B, x0, r, T, interval, rIsConstant, printError, task);
        System.out.println();
    }



}