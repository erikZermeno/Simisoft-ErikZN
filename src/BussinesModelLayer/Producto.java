package BussinesModelLayer;

import java.io.File;
import java.util.LinkedList;
import javax.swing.JOptionPane;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zerme
 */
public class Producto {
    
    //Atributos
    private int codProducto, stock, idFarmacia;
    private String nombreProd, fechaVencimiento; 

    //Constructor
    public Producto(int codProducto, int stock, String nombreProd, String fechaVencimiento, int idFarmacia) {
        this.codProducto = codProducto;
        this.stock = stock;
        this.nombreProd = nombreProd;
        this.fechaVencimiento = fechaVencimiento;
        this.idFarmacia = idFarmacia;
    }

    public Producto() {
    }
    
    
    //Metodos
    public int getCodProducto() {
        return codProducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getIdFarmacia() {
        return idFarmacia;
    }
   
    public int sacarID(){
        LinkedList<String[]> listaProd = DataAccessLayer.DataAccess.leerCSV("Productos.csv");
        int id = 1;
        if(listaProd.size()==1){
           return id; 
        }else{
           id = Integer.parseInt(listaProd.getLast()[0]) + 1;
           return id;
        }    
    }
    
    public void mostrarDatos(javax.swing.JTable tabla, int id){
        
        LinkedList<String[]> lista = DataAccessLayer.DataAccess.leerCSV("Productos.csv");
        //Verificar cuantas filas tiene
        int cont = 0;  
        for (int i = 0; i < lista.size()-1; i++) {        
            if(Integer.parseInt(lista.get(i+1)[1]) == (id)){
                   cont++;      
            }
        }
        //Matriz
        String matriz[][] = new String[cont][5];

        //Se llena la matriz con los datos
        cont = 0;
        for (int i = 0; i < lista.size()-1; i++) {

            if(Integer.parseInt(lista.get(i+1)[1]) == (id)){
                for (int j = 0; j < 5; j++) {
                    matriz[cont][j] = lista.get(i+1)[j];
                }
                cont++;
            }
        }

        //Se muestra la matriz en la tabla
        tabla.setModel(new javax.swing.table.DefaultTableModel(

            matriz,
            lista.get(0)
        ){
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }});
    }
    
    public String[] aString() {
        String[] datos = new String[5];
        datos[0] = String.valueOf(codProducto);
        datos[1] = String.valueOf(idFarmacia);
        datos[2] = nombreProd;
        datos[3] = fechaVencimiento;
        datos[4] = String.valueOf(stock);
        return datos;
    }
    
    public void buscarPorNombre(String nombre, javax.swing.JTable tblProducto, int indice){
        
        LinkedList<String[]> lista = new LinkedList<String[]>();
        LinkedList<String[]> listaProd = DataAccessLayer.DataAccess.leerCSV("Productos.csv");
        for (int i = 0; i < listaProd.size(); i++) {
            if (listaProd.get(i)[2].equals(nombre)&&indice==Integer.parseInt(listaProd.get(i)[1])) {
                lista.add(listaProd.get(i));
            }
        }
        if (lista.isEmpty()) {
            mostrarDatos(tblProducto, indice);
            JOptionPane.showMessageDialog(null, "No se encontro ningun registro con ese nombre");
        }else{
            //Verificar cuantas filas tiene
        int cont = 0;  
        for (int i = 0; i < lista.size(); i++) {        
             if(Integer.parseInt(lista.get(i)[1]) == (indice)){
                   cont++;      
            }
        }
        //Matriz
        String matriz[][] = new String[cont][5];

        //Se llena la matriz con los datos
        cont = 0;
        for (int i = 0; i < lista.size(); i++) {

            if(Integer.parseInt(lista.get(i)[1]) == (indice)){
                for (int j = 0; j < 5; j++) {
                    matriz[cont][j] = lista.get(i)[j];
                }
                cont++;
            }
        }

        //Se muestra la matriz en la tabla
        tblProducto.setModel(new javax.swing.table.DefaultTableModel(

            matriz,
            listaProd.get(0)
        ){
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }});
        }
        
        
    }
    
    public void productosExiste(){
        String path = "Productos.csv";
        File archivo = new File(path);
        if (!archivo.exists()) {
            LinkedList<String[]> lista = new LinkedList();
            String[] datos = {"ID Producto","ID Farmacia", "Nombre", "Caducidad", "Inventario"};
            lista.add(datos);
            DataAccessLayer.DataAccess.escribirCSV(lista, path);
        }
    }
    
}
