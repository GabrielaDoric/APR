package LAB4;

public interface Chromosome {

    public double getValue(int i);
    public void setValue(int i, double value);
    public int getSize();
    public double getFitness();
    public void setFitness(double fitness);
    public double[] getPoints();


}
