package LAB4;

import LAB1.Matrix;

public class Function3 extends AbstractFunction {

    int n;
    int[] coefs;

    public Function3(int n,int[] coefs) {
        double[][] vector=new double[1][n];
        for (int i=0;i<n;i++){
            vector[0][i]=n+1;
        }
        this.xMin=new Matrix(vector);
        this.fMin=0;
        this.n=n;
        this.coefs=coefs;
    }

    public Function3() {
    }

    @Override
    public double getFunctionValue(double[] m) {

        double sum = 0;
        for (int i = 0; i < coefs.length; i++) {
            sum += Math.pow(m[i] - coefs[i] , 2);
        }
        return sum;
    }

    @Override
    public double fitness(double[] point) {
        return this.getFunctionValue(point);
    }

    @Override
    public String toString() {
        return "F3";
    }

}
