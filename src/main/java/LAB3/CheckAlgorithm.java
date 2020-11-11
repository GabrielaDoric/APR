package LAB3;
import LAB1.Matrix;
import LAB2.AbstractFunction;
import LAB2.GoldenSectionSearch;
import LAB2.Interval;
import LAB2.Simplex;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class CheckAlgorithm {


    public static void checkGradientDescent(AbstractFunction f, boolean useGoldenRatio) {

        Matrix m=f.getX0();
//        double value=m.getElement(0,0);
        Matrix m1=new Matrix((m));

        Matrix minValue = GradientDescent.gradientDescent(f, m1, useGoldenRatio);
        System.out.println("Gradijentni spust\txMin="+minValue);
        System.out.println("Broj evaluacija:"+f.counter);
        System.out.println();
        f.resetCounter();

    }

    public static void checkNewtonRaphson(AbstractFunction f,boolean useGoldenRatio) {
        Matrix m=f.getX0();
        Matrix m1=new Matrix((m));

        Matrix minValue = NewtonRaphson.newtonRaphson(f, m1, useGoldenRatio);
        System.out.println("Newton-Raphson\txMin="+minValue);
        System.out.println("Broj evaluacija:"+f.counter);
        System.out.println();
        f.resetCounter();
    }


    public static void checkBoxAlgorithm(AbstractFunction f) {
        Matrix m=f.getX0();
        Matrix m1=new Matrix((m));

        BoxAlgorithm boxAlgorithm=new BoxAlgorithm();
        Matrix minValue = boxAlgorithm.findSolution(f, m1);
        System.out.println("Boxov algoritam\txMin="+minValue);
        System.out.println("Broj evaluacija:"+f.counter);
        System.out.println();
        f.resetCounter();
    }

    public static void checkTransformation1(AbstractFunction f) {
        ImplicitConstraint4 implicitConstraint4=new ImplicitConstraint4();
        ImplicitConstraint5 implicitConstraint5=new ImplicitConstraint5();


        List<AImplicitConstraint> list1=List.of(implicitConstraint4,implicitConstraint5);
//        List<AImplicitConstraint> list2=List.of(null);

//        UnutarnjaTockaFunction unutarnjaTockaFunction=new UnutarnjaTockaFunction(list1);
//        Matrix xGood= Simplex.simplex(unutarnjaTockaFunction,f.getX0());
        TransformedFunction transformedFunction=new TransformedFunction(f,list1,new ArrayList<>(),1);


        Matrix x=TransformationAlgorithm.TransformationAlgorithm(transformedFunction,f.getX0());
        System.out.println("xMin="+x);
        System.out.println("Broj evaluacija:"+transformedFunction.counter);


    }

    public static void checkTransformation2(AbstractFunction f) {
        ImplicitConstraint1 implicitConstraint1=new ImplicitConstraint1();
        ImplicitConstraint2 implicitConstraint2=new ImplicitConstraint2();
        ImplicitConstraint3 implicitConstraint3=new ImplicitConstraint3();

        List<AImplicitConstraint> list1=List.of(implicitConstraint1,implicitConstraint2);
        List<AImplicitConstraint> list2=List.of(implicitConstraint3);

        UnutarnjaTockaFunction unutarnjaTockaFunction=new UnutarnjaTockaFunction(list1);
        Matrix xGood= Simplex.simplex(unutarnjaTockaFunction,f.getX0());
        TransformedFunction transformedFunction=new TransformedFunction(f,list1,list2,1);


        Matrix x=TransformationAlgorithm.TransformationAlgorithm(transformedFunction,xGood);
        System.out.println("xMin="+x);
        System.out.println("Broj evaluacija:"+transformedFunction.counter);
    }
}
