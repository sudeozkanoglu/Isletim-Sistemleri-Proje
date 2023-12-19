package OperatingSystems;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataReader {
	public static void DataRead() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\berke\\eclipse-workspace\\OperatinSystemsProject\\src\\giris.txt"));

            String satir;
            while ((satir = bufferedReader.readLine()) != null) {
            	
                String[] arr = satir.split(",\\s*");

                // Diziyi kullanarak process oluştur
                System.out.println("dizi uzunluk: "+arr.length);
                for(int i=0;i<arr.length;i++)
                {
                	System.out.println(arr[i]);
                }
                
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	}
}


     
