package LAB3;

import LAB1.Matrix;
import LAB2.GoldenSectionSearch;
import LAB2.AbstractFunction;

public class GradientDescent {

    public static double epsilon = 10e-6;
    public static int maxNumOfIter = 100;
    static Matrix gradient;
    static Matrix v;
    static int numOfIter=0;
    static double lambda;


    public static double euclideanNorm(Matrix gradient){
        double sum=0;
        for (int i=0;i<gradient.getColumnLength();i++){
            sum+=Math.pow(gradient.getElement(0,i),2);
        }
        double norm=Math.sqrt(sum);
        return norm;
    }

    public static Matrix gradientDescent(AbstractFunction f, Matrix x0, boolean useGoldenRatio){
        Matrix x=x0;
        double lastValue = 0.0;
        lambdaFunction lambdaFunction=new lambdaFunction();
        lambdaFunction.function=f;
        do{
            f.counter++;
            gradient=f.getGradientValue(x);

            v=gradient.multiply(-1);

            if (useGoldenRatio==true){
                //find lambda
                lambdaFunction.x0=x;
                lambdaFunction.v0=v;
                lambda=GoldenSectionSearch.goldenRatioValue(lambdaFunction,1,x);

                v=v.multiply(lambda);
                x=x.add(v);
            }
            else{
                x=x.add(v);
            }

            numOfIter+=1;
//            System.out.println("x: " + x);
            if (lastValue != f.getFunctionValue(x)) {
                numOfIter = 0;
                lastValue = f.getFunctionValue(x);
            }
        }while (euclideanNorm(gradient)>epsilon && numOfIter<maxNumOfIter);
        return x;
    }
}
