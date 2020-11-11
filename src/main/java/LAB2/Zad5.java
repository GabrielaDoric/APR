package LAB2;

import LAB1.Matrix;

import java.util.Random;

public class Zad5 {

    public static void main(String[] args) {

        Function6 f6=new Function6();
        f6.setxMin(new Matrix(0.0));
        f6.setX0(new Matrix(0.0,0.0));


        int counter=0;
        Random random=new Random();
        for (int i = 0; i < 10000; i++) {
            f6.setX0(new Matrix(random.nextDouble()*100-50,random.nextDouble()*100-50));
            Matrix matrix= Evaluate.evaluateSimplex(f6);
            if ((Math.abs(matrix.getElement(0,0))==0)&&(Math.abs(matrix.getElement(0,0))==0)){
                counter++;
            }
        }

        System.out.println("Minimum pronaden"+counter+" puta");

    }
}

