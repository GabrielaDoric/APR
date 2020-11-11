package LAB4;

import java.util.Random;

public class ChromosomeB implements  Chromosome {

    public static int DG = -50;
    public static int GG = 150;
    private int N;
    private int n;
    private double fitness;
    private int[] points;


    public ChromosomeB( int N, int p) {
        Random random=new Random();
        this.fitness = 0;
        this.N = N;
        double value=Math.log(Math.floor(1 + (GG - DG) * Math.pow(10, p))) / Math.log(2);
        this.n = (int) (value);
        this.points = new int[N];
        for (int i = 0; i < N; i++) {
            this.points[i] = random.nextInt((int) Math.pow(2, n - 1));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        for (int bin : this.points) {
            sb.append(fromBinary(bin, n) + " ");
        }
        sb.append("]");
        return sb.toString();
    }


    @Override
    public double[] getPoints() {
        double[] realPoints = new double[N];
        for (int i = 0; i < this.points.length; i++) {
            double value=fromBinary(this.points[i], n);
            realPoints[i] =  value;
        }
        return realPoints;
    }

    @Override
    public double getFitness() {
        return this.fitness;
    }

    @Override
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    @Override
    public int getSize() {
        return this.points.length;
    }

    @Override
    public void setValue(int index, double value) {
        this.points[index] = toBinary(value, index);
    }

    @Override
    public double getValue(int index) {
        return fromBinary(this.points[index], n);
    }


    public static double fromBinary(int b, int n) {
        return DG + b * (GG - DG) / (Math.pow(2, n - 1));
    }
    public static int toBinary(double x, int n) {
        return (int) ((x - DG) / (GG - DG) * (Math.pow(2, n - 1)));
    }


}
