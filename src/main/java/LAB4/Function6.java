package LAB4;


import LAB1.Matrix;

public class Function6 extends AbstractFunction {


    public Function6() {
        this.xMin = new Matrix(0.0);
        this.fMin = 0;
    }

    @Override
    public double getFunctionValue(double[] m) {

        double sum = 0;
        for (int i = 0; i < m.length; i++) {
            sum += Math.pow(m[i], 2);
        }
        double b = Math.pow(Math.sin(Math.sqrt(sum)), 2) - 0.5;
        double n = Math.pow(1 + 0.001 * sum, 2);
        double result = 0.5 + b / n;
        return result;
    }


    @Override
    public double fitness(double[] point) {
        return this.getFunctionValue(point);
    }

    @Override
    public String toString() {
        return "F6";
    }

}
