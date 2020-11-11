package LAB4;

import java.util.*;

public class GeneticAlgorithm {

    public static int DG = -50;
    public static int GG = 150;

    public AbstractFunction f;
    public static Random random = new Random();
    public static boolean binary = false;
    public static double epsilon = 10e-6;
    public static int N = 2;

    public static int populationSize = 40;
    public static double pm = 0.3;
    public static final int p = 5;
    public static int k = 3;
    public static final int elitism = 1;

    public static final int limitation = 10000;
    public static final int maxEvaluation = 100000;


    public GeneticAlgorithm(AbstractFunction f, int N, boolean binary) {
        this.f = f;
        this.N = N;
        this.binary = binary;
    }

    public GeneticAlgorithm(AbstractFunction f, int N, int populationSize, boolean binary) {
        this.f = f;
        this.N = N;
        this.populationSize = populationSize;
        this.binary = binary;

    }

    public GeneticAlgorithm(AbstractFunction f, int N, int populationSize, double pm, boolean binary) {
        this.f = f;
        this.N = N;
        this.populationSize = populationSize;
        this.pm = pm;
        this.binary = binary;

    }

    public GeneticAlgorithm(AbstractFunction f, int N, double pm, int k, boolean binary) {
        this.f = f;
        this.N = N;
        this.k = k;
        this.pm = pm;
        this.binary = binary;

    }

    public Chromosome generationalGA() {
        Chromosome[] population = createPopulation(populationSize);
        for (int i = 0; i < population.length; i++) {
            double fit = f.fitness(population[i].getPoints());
            population[i].setFitness(fit);
        }
        for (int l = 1; l <= limitation; l++) {
            f.incrementEvaluationNumber();
            f.counter++;
            if (f.getEvaluationNumber() > maxEvaluation) {
                break;
            }

            //stvaranje nove populacije
            Chromosome[] newPopulation = new Chromosome[populationSize];
            Chromosome bestSolution = findTheBestSolution(population);
            if (bestSolution.getFitness() <= epsilon) {
                break;
            }
            // elitizam, zadrzi staru najbolju
            if (elitism == 1) {
                newPopulation[0] = bestSolution;
            }
            // nove jedinke, odabir, krizanje i mutiranje
            for (int i = elitism; i < populationSize; i++) {
                Chromosome child = null;
                Chromosome[] parents = proportionalSelection(population);
                child = arithmeticCrossover(parents);
                mutation(child);
                double fit = f.fitness(child.getPoints());
                child.setFitness(fit);
                newPopulation[i] = child;
            }
            population = newPopulation;

        }
        return findTheBestSolution(population);
    }

    public Chromosome eliminationGA() {
        Chromosome[] population = createPopulation(populationSize);
        for (int i = 0; i < population.length; i++) {
            double fit = f.fitness(population[i].getPoints());
            population[i].setFitness(fit);
        }

        for (int l = 1; l <= limitation; l++) {
            f.incrementEvaluationNumber();
            if (f.getEvaluationNumber() > maxEvaluation) {
                break;
            }
            //stvaranje nove populacije, tj popravljene
            Chromosome[] newPopulation = new Chromosome[populationSize];
            Chromosome bestSolution = findTheBestSolution(population);
            if (bestSolution.getFitness() <= epsilon) {
                break;
            }

            int[] indexes = tournamentSelection(population);
            int theWorst = indexes[indexes.length - 1];
            Chromosome[] parents2 = new Chromosome[2];
            parents2[0] = population[indexes[0]];
            parents2[1] = population[indexes[1]];

            Chromosome child = null;
            child = arithmeticCrossover(parents2);
            mutation(child);
            double value = f.fitness(child.getPoints());
            child.setFitness(value);

            // od k odabranih jedinki najgoru zamijeni s djetetom
            for (int i = 0; i < populationSize; i++) {
                if (i == theWorst) {
                    newPopulation[i] = child;
                } else {
                    newPopulation[i] = population[i];
                }
            }
            population = newPopulation;

        }
        f.resetCounter();
        return findTheBestSolution(population);
    }


    public static Chromosome[] createPopulation(int populationSize) {
        Chromosome[] population = new Chromosome[populationSize];
        for (int i = 0; i < populationSize; i++) {
            if (!binary) {
                population[i] = new ChromosomeFP(N);
            } else {
                population[i] = new ChromosomeB(N, p);
            }
        }
        return population;
    }

    public static Chromosome findTheBestSolution(Chromosome[] chromosomes) {
        Chromosome bestSolution = null;
        double minimalError = 0;
        double error;
        for (Chromosome chromosome : chromosomes) {
            error = chromosome.getFitness();
            if (minimalError == 0 || error < minimalError) {
                minimalError = error;
                bestSolution = chromosome;
            }
        }
        return bestSolution;
    }

    public static Chromosome[] proportionalSelection(Chromosome[] chromosomes) {
        Chromosome[] parents = new Chromosome[2];
        double sum = 0;

        for (int i = 0; i < chromosomes.length; i++) {
            double fit=chromosomes[i].getFitness();
            sum += fit;
        }
        sum = 1 / sum;

        for (int i = 0; i < N; i++) {
            double granica = random.nextDouble() * sum;
            int k = 0;
            double gornjaGranica = chromosomes[k].getFitness();
            while (k < (chromosomes.length - 1) && granica > gornjaGranica) {
                k+=1;
                gornjaGranica += chromosomes[k].getFitness();
            }
            parents[i] = chromosomes[k];
        }
        return parents;
    }

    public static int[] tournamentSelection(Chromosome[] chromosomes) {
        Set<Integer> randomIndexes = new HashSet<>();
        while (randomIndexes.size() < k) {
            randomIndexes.add(random.nextInt(chromosomes.length));
        }
        ArrayList<Par> pickedChromosomes = new ArrayList<>();
        for (Integer index : randomIndexes) {
            pickedChromosomes.add(new Par(chromosomes[index].getFitness(), index));
        }

        int[] indexes = new int[k];
        Collections.sort(pickedChromosomes);
        for (int i = 0; i < pickedChromosomes.size(); i++) {
            indexes[i] = pickedChromosomes.get(i).index;
        }
        return indexes;
    }

    public static Chromosome arithmeticCrossover(Chromosome[] parents) {
        Chromosome parent1 = parents[0];
        Chromosome parent2 = parents[1];
        Chromosome child = new ChromosomeFP(parent1.getSize());
        int size = parent2.getSize();
        for (int i = 0; i < size; i++) {
            double value1 = parent1.getValue(i);
            double value2 = parent2.getValue(i);
            double value = random.nextDouble() * value1 + (1 - random.nextDouble()) * value2;
            child.setValue(i, value);
        }
        return child;
    }


    public static void mutation(Chromosome chromosome) {
        for (int i = 0; i < chromosome.getSize(); i++) {
            if (random.nextDouble() < pm) {
                chromosome.setValue(i, random.nextDouble() * (GG - DG) + DG);
            }
        }
    }


    private static class Par implements Comparable<Par> {
        double fitness;
        int index;

        public Par(double fitness, int index) {
            this.index = index;
            this.fitness = fitness;
        }

        @Override
        public int compareTo(Par object) {
            if (this.fitness > object.fitness) {
                return 1;
            } else if (this.fitness < object.fitness) {
                return -1;
            } else {
                return 0;
            }
        }
    }


}