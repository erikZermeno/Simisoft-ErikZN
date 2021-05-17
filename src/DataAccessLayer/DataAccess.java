/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;


/**
 *
 * @author zerme
 */
public class DataAccess {
    
    public static void escribirCSV(LinkedList<String[]> dato, String archivo) {

        try {
            CSVWriter escribir = new CSVWriter(new FileWriter(archivo));
            escribir.writeAll(dato);
            escribir.close();
        } catch (IOException ex) {
            System.out.println("FileWriter " + ex);
        }
    }

    public static LinkedList<String[]> leerCSV(String archivo) {

        LinkedList<String[]> datos = null;
        try {
            CSVReader leer = new CSVReader(new FileReader(archivo));
            try {
                datos = (LinkedList<String[]>) leer.readAll();
            } catch (IOException ex) {
                System.out.println(ex);
            } catch (CsvException ex) {
                System.out.println(ex);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("FileReader " + ex);
        }
        return datos;
    }
}
