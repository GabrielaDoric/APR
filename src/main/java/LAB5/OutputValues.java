package LAB5;

import LAB1.Matrix;

import java.io.IOException;
import java.util.StringJoiner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

public class OutputValues {


    public static void outputValues(String filename, double t, double x1, double x2){

        StringJoiner sj = new StringJoiner(",");
        sj.add(String.valueOf(t));
        sj.add(String.valueOf(x1));
        sj.add(String.valueOf(x2));
        String result = sj.toString();


        Writer output;
        try {
            output = new BufferedWriter(new FileWriter(filename,true));
            output.write(result);
            output.write("\n");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
