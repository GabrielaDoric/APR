package LAB4;

import java.util.Random;

public class ChromosomeFP implements  Chromosome {

    public static int DG = -50;
    public static int GG = 150;
    private double fitness;
    private double points[];

    public ChromosomeFP(int N) {
        this.fitness = 0;
        this.points = new double[N];
        Random random=new  Random();
        for (int i = 0; i < N; i++) {
            this.points[i] = random.nextDouble() * (GG - DG) + DG;
        }

    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        for (double point : this.points) {
            sb.append(point + " ");
        }
        sb.append("]");
        return sb.toString();
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
    public double getValue(int index) {
        return this.points[index];
    }

    @Override
    public void setValue(int index, double value) {
        this.points[index] = value;
    }


    @Override
    public double[] getPoints() {
        return this.points;
    }


}
