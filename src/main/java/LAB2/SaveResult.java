package LAB2;

import LAB1.Matrix;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveResult {

    public class saveResult{

        public saveResult() {
        }

        public void saveToFile(String path,StringBuilder sb){
            try{
                BufferedWriter bw=new BufferedWriter(new FileWriter(path));
                bw.append(sb);
            }catch(IOException e){
                System.out.println(e.toString());
            }
        }
    }


//    public static void saveMatrix(String filename, Matrix matrix) {
//        try {
//            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
//
//            for (int i = 0; i < matrix.rowLength; i++) {
//                for (int j = 0; j < matrix.columnLength; j++) {
//                    if (j == matrix.columnLength - 1) {
//                        bw.write(Double.toString(matrix.getElement(i, j)));
//                    } else {
//                        bw.write(Double.toString(matrix.getElement(i, j)) + " ");
//                    }
//                }
//                bw.newLine();
//            }
//            bw.flush();
//        } catch (IOException e) {
//            System.out.println(e.toString());
//        }
//    }
}
