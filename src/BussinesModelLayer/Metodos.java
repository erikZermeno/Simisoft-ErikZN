/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BussinesModelLayer;

import java.awt.Color;
import java.util.LinkedList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;


/**
 *
 * @author zerme
 */
public class Metodos {
    
    public void guardarRegistro(String archivo, String[] datos){
        LinkedList<String[]> lectura = DataAccessLayer.DataAccess.leerCSV(archivo);
        lectura.add(datos);
        DataAccessLayer.DataAccess.escribirCSV(lectura, archivo);
        
    }
   
    public void esNumero(java.awt.event.KeyEvent evt){
        char caracter = evt.getKeyChar();

        // Verificar si la tecla pulsada no es un digito
        if(((caracter < '0') ||
           (caracter > '9')) &&
           (caracter != '\b' /*corresponde a BACK_SPACE*/))
        {
           evt.consume();  // ignorar el evento de teclado
        }
    }
    
    public void graficarInventario() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        //Llenar datos
        LinkedList<String[]> datos = DataAccessLayer.DataAccess.leerCSV("Productos.csv");
        for (int i = 0; i < datos.size()-1; i++) {
            dataset.setValue(Integer.parseInt(datos.get(i+1)[4]), "", datos.get(i+1)[2]);
        }
        
        //Creacion de la tabla
        JFreeChart chart = ChartFactory.createBarChart(
                "Inventario Actual",
                "Productos", 
                "Cantidad", 
                dataset, 
                PlotOrientation.VERTICAL,
                false, 
                false, 
                false
        );
        
        //Personalizacion
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer render = (BarRenderer)plot.getRenderer();
        render.setSeriesPaint(0, Color.DARK_GRAY);
        chart.setBackgroundPaint(Color.WHITE);
        
        //Mostramos la grafica en pantalla
        ChartFrame frame = new ChartFrame("Inventario de Productos", chart);
        frame.pack();
        frame.setVisible(true);
    }

}
