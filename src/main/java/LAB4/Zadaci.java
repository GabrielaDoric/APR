package LAB4;

import LAB1.Matrix;

public class Zadaci {


    public static void main(String[] args) {

        zad1();
        zad2();
        zad3();
        zad4();
        zad5();
    }


    public static void zad1() {

        Function1 f1 = new Function1();
        Function3 f3 = new Function3(5, new int[]{1, 2, 3, 4, 5});
        Function6 f6 = new Function6();
        Function7 f7 = new Function7();

        f1.setxMin(new Matrix(1.0, 1.0));
        f1.setX0(new Matrix(-1.9, 2.0));
        f3.setxMin(new Matrix(1, 2, 3, 4, 5));
        f3.setX0(new Matrix(0.0, 0.0, 0.0, 0.0, 0.0));
        f6.setxMin(new Matrix(0.0));
        f6.setX0(new Matrix(0.0, 0.0));
        f7.setxMin(new Matrix(0.0));
        f7.setX0(new Matrix(0.0, 0.0));

        System.out.println("ZADATAK 1");

        GeneticAlgorithm geneticAlgorithm1B = new GeneticAlgorithm(f1, 2, true);
        Chromosome solutionBGenerative = geneticAlgorithm1B.generationalGA();
        Chromosome solutionBEliminative = geneticAlgorithm1B.eliminationGA();

        GeneticAlgorithm geneticAlgorithm1FP = new GeneticAlgorithm(f1, 2,false);
        Chromosome solutionFPGenerative = geneticAlgorithm1FP.generationalGA();
        Chromosome solutionFPEliminative = geneticAlgorithm1FP.eliminationGA();

//        System.out.println("F1\nFloating point prikaz\"\nGenerational: x_min=" + solutionBGenerative + "\t F(x_min)=" + solutionBGenerative.getFitness());
        System.out.println("F1\n(B) x_min=" + solutionBEliminative + ", F(x_min)=" + solutionBEliminative.getFitness());
//        System.out.println("\"Binarni prikaz\"\nGenerational: x_min=" + solutionFPGenerative + "\t F(x_min) " + solutionFPGenerative.getFitness());
        System.out.println("(FP) x_min=" + solutionFPEliminative + ", F(x_min)=" + solutionFPEliminative.getFitness());
        System.out.println("Broj evalucija "+f1.getEvaluationNumber());
        System.out.println();


        GeneticAlgorithm geneticAlgorithm3B = new GeneticAlgorithm(f3, 5,true);
//        Chromosome solution3BGenerative = geneticAlgorithm3B.generationalGA();
        Chromosome solution3BEliminative = geneticAlgorithm3B.eliminationGA();

        GeneticAlgorithm geneticAlgorithm3FP = new GeneticAlgorithm(f3, 5,false);
//        Chromosome solution3FPGenerative = geneticAlgorithm3FP.generationalGA();
        Chromosome solution3FPEliminative = geneticAlgorithm3FP.eliminationGA();

//        System.out.println("\"F3\nB prikaz\"\nGeneracijsko rjesenje: x_min=" + solution3BGenerative + "\t F(x_min) " + solution3BGenerative.getFitness());
        System.out.println("F3\n(B) x_min=" + solution3BEliminative + ", F(x_min)=" + solution3BEliminative.getFitness());
//        System.out.println("Binarni prikaz\nGeneracijsko rjesenje: x_min=" + solution3FPGenerative + "\t F(x_min) " + solution3FPGenerative.getFitness());
        System.out.println("(FP)x_min=" + solution3FPEliminative + ", F(x_min)=" + solution3FPEliminative.getFitness());
        System.out.println("Broj evalucija "+f3.getEvaluationNumber());
        System.out.println();


        GeneticAlgorithm geneticAlgorithm6B = new GeneticAlgorithm(f6,2,true);
        Chromosome solution6BGenerative = geneticAlgorithm6B.generationalGA();
        Chromosome solution6BEliminative = geneticAlgorithm6B.eliminationGA();

        GeneticAlgorithm geneticAlgorithm6FP = new GeneticAlgorithm(f6, 2,false);
        Chromosome solution6FPGenerative = geneticAlgorithm6FP.generationalGA();
        Chromosome solution6FPEliminative = geneticAlgorithm6FP.eliminationGA();

//        System.out.println("Generacijsko rjesenje: x_min=" + solution6BGenerative + "\t F(x_min) " + solution6BGenerative.getFitness());
        System.out.println("F6\n(B) x_min=" + solution6BEliminative + ", F(x_min)=" + solution6BEliminative.getFitness());
//        System.out.println("\nGeneracijsko rjesenje: x_min=" + solution6FPGenerative + "\t F(x_min) " + solution6FPGenerative.getFitness());
        System.out.println("(FP) x_min=" + solution6FPEliminative + ", F(x_min)=" + solution6FPEliminative.getFitness());
        System.out.println("Broj evalucija "+f6.getEvaluationNumber());
        System.out.println();


        GeneticAlgorithm geneticAlgorithm7B = new GeneticAlgorithm(f7, 2, true);
        Chromosome solution7BGenerative = geneticAlgorithm7B.generationalGA();
        Chromosome solution7BEliminative = geneticAlgorithm7B.eliminationGA();

        GeneticAlgorithm geneticAlgorithm7FP = new GeneticAlgorithm(f7, 2,false);
        Chromosome solution7FPGenerative = geneticAlgorithm7FP.generationalGA();
        Chromosome solution7FPEliminative = geneticAlgorithm7FP.eliminationGA();

//        System.out.println("Generacijsko rjesenje: x_min=" + solution7BGenerative + "\t F(x_min) " + solution7BGenerative.getFitness());
        System.out.println("F7\n(B) x_min=" + solution7BEliminative + ", F(x_min) " + solution7BEliminative.getFitness());
//        System.out.println("Generacijsko rjesenje: x_min=" + solution7FPGenerative + "\t F(x_min) " + solution7FPGenerative.getFitness());
        System.out.println("(FP) x_min=" + solution7FPEliminative + ", F(x_min) " + solution7FPEliminative.getFitness());
        System.out.println("Broj evalucija "+f7.getEvaluationNumber());
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------");
    }

    public static void zad2() {
        System.out.println("ZADATAK 2");

        Function6 f6 = new Function6();
        Function7 f7 = new Function7();
        f6.setxMin(new Matrix(0.0));
        f6.setX0(new Matrix(0.0, 0.0));
        f7.setxMin(new Matrix(0.0));
        f7.setX0(new Matrix(0.0, 0.0));

        int ar[] = {1, 3, 6, 10};
        System.out.println("F6");
        for (int i = 0; i < ar.length; i++) {
            int x = ar[i];
            GeneticAlgorithm geneticAlgorithm6 = new GeneticAlgorithm(f6, x, false);
            Chromosome solution6Generative = geneticAlgorithm6.eliminationGA();
            System.out.println("x_min=" + solution6Generative + "\t F(x_min)=" + solution6Generative.getFitness());
            System.out.println("Broj evalucija "+f6.getEvaluationNumber());
        }

        System.out.println("F7");
        for (int i = 0; i < ar.length; i++) {
            int x = ar[i];
            GeneticAlgorithm geneticAlgorithm7 = new GeneticAlgorithm(f7, x,false);
            Chromosome solution7Generative = geneticAlgorithm7.eliminationGA();
            System.out.println("x_min=" + solution7Generative + "\t F(x_min)=" + solution7Generative.getFitness());
            System.out.println("Broj evalucija "+f7.getEvaluationNumber());

        }
        System.out.println("------------------------------------------------------------------------------------");

    }

    public static void zad3() {
        System.out.println("ZADATAK 3");


        Function6 f6 = new Function6();
        Function7 f7 = new Function7();
        f6.setxMin(new Matrix(0.0));
        f6.setX0(new Matrix(0.0, 0.0));
        f7.setxMin(new Matrix(0.0));
        f7.setX0(new Matrix(0.0, 0.0));

        int ar[] = {3, 6};
        System.out.println("F6");
        for (int i = 0; i < ar.length; i++) {
            int x = ar[i];
            GeneticAlgorithm geneticAlgorithm6FP = new GeneticAlgorithm(f6, x,false);
            Chromosome solution6GenerativeFP = geneticAlgorithm6FP.eliminationGA();
            System.out.println("x_min=" + solution6GenerativeFP + "\t F(x_min)=" + solution6GenerativeFP.getFitness());
            System.out.println("Broj evalucija "+f6.getEvaluationNumber());


            GeneticAlgorithm geneticAlgorithm6B = new GeneticAlgorithm(f6, x,true);
            Chromosome solution6GenerativeB = geneticAlgorithm6B.eliminationGA();
            System.out.println("x_min=" + solution6GenerativeB + "\t F(x_min)=" + solution6GenerativeB.getFitness());
            System.out.println("Broj evalucija "+f6.getEvaluationNumber());

        }

        System.out.println("F7");
        for (int i = 0; i < ar.length; i++) {
            int x = ar[i];
            GeneticAlgorithm geneticAlgorithm7FP = new GeneticAlgorithm(f7, x, false);
            Chromosome solution7GenerativeFP = geneticAlgorithm7FP.eliminationGA();
            System.out.println("x_min=" + solution7GenerativeFP + "\t F(x_min)=" + solution7GenerativeFP.getFitness());
            System.out.println("Broj evalucija "+f7.getEvaluationNumber());


            GeneticAlgorithm geneticAlgorithm7B = new GeneticAlgorithm(f6, x, true);
            Chromosome solution7GenerativeB = geneticAlgorithm7B.eliminationGA();
            System.out.println("x_min=" + solution7GenerativeB + "\t F(x_min)=" + solution7GenerativeB.getFitness());
            System.out.println("Broj evalucija "+f7.getEvaluationNumber());

        }

        System.out.println("------------------------------------------------------------------------------------");

    }

    public static void zad4() {
        System.out.println("ZADATAK 4");
        Function6 f6 = new Function6();
        f6.setxMin(new Matrix(0.0));
        f6.setX0(new Matrix(0.0, 0.0));

        int populationSizeArr[] = {30, 50, 100, 200};
        System.out.println("F6");
        int optimalPopulationSize = 0;
        double error = 10000;
        for (int i = 0; i < populationSizeArr.length; i++) {
            int x = populationSizeArr[i];
            GeneticAlgorithm geneticAlgorithm6FP = new GeneticAlgorithm(f6, 2,populationSizeArr[i], false);
            Chromosome solution6GenerativeFP = geneticAlgorithm6FP.eliminationGA();
            System.out.println("x_min=" + solution6GenerativeFP + "\t F(x_min)=" + solution6GenerativeFP.getFitness());
            if (solution6GenerativeFP.getFitness() < error) {
                error = solution6GenerativeFP.getFitness();
                optimalPopulationSize = x;
            }
        }

        double mutationProbabiliyArr[] = {0.1, 0.3, 0.6, 0.9};
        double optimalMutationProbabiliy = 0;
        error = 1000;
        for (int i = 0; i < populationSizeArr.length; i++) {
            double x = mutationProbabiliyArr[i];
            GeneticAlgorithm geneticAlgorithm6FP = new GeneticAlgorithm(f6, 2,optimalPopulationSize, mutationProbabiliyArr[i],false);
            Chromosome solution6GenerativeFP = geneticAlgorithm6FP.eliminationGA();
            System.out.println("x_min=" + solution6GenerativeFP + "\t F(x_min)=" + solution6GenerativeFP.getFitness());
            if (solution6GenerativeFP.getFitness() < error) {
                error = solution6GenerativeFP.getFitness();
                optimalMutationProbabiliy = x;
            }
        }
        System.out.println("\npopulation_size*=" + optimalPopulationSize + ",mutation_prob*=" + optimalMutationProbabiliy);
        System.out.println("------------------------------------------------------------------------------------");
    }

    public static void zad5() {
        System.out.println("ZADATAK 5");
        Function6 f6 = new Function6();
        f6.setxMin(new Matrix(0.0));
        f6.setX0(new Matrix(0.0, 0.0));

        int ar[] = {2, 3, 4, 5, 6,7,8};
        System.out.println("F6");
        for (int i = 0; i < ar.length; i++) {
            int x = ar[i];
            GeneticAlgorithm geneticAlgorithm6FP = new GeneticAlgorithm(f6, 2, 0.3, x,false);
            Chromosome solution6GenerativeFP = geneticAlgorithm6FP.eliminationGA();
            System.out.println("x_min=" + solution6GenerativeFP + "\t F(x_min)=" + solution6GenerativeFP.getFitness());
        }
        System.out.println("------------------------------------------------------------------------------------");
    }


}



