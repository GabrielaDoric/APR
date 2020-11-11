package LAB4;

import LAB1.Matrix;

public class Function7 extends AbstractFunction {

    public Function7() {
        this.xMin=new Matrix(0.0);
        this.fMin=0;
    }

    @Override
    public double getFunctionValue(double[] m) {

        double sum = 0;
        for (int i=0;i<m.length;i++) {
            sum += Math.pow(m[i],2);
        }

        double a=Math.pow(sum,0.25);
        double b=1+Math.pow(Math.sin(50*Math.pow(sum,0.1)),2);
        double result =a*b;
        return result;
    }

    @Override
    public double fitness(double[] point) {
        return this.getFunctionValue(point);
    }


    @Override
    public String toString() {
        return "F7";
    }

}
