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
public class Farmacia {
    
    //Atributos
    private int idFarmacia;
    private String nombreFar, direccion, telefono;
    
    //Constructor
    public Farmacia(int idFarmacia, String nombreFar, String direccion, String telefono) {
        this.idFarmacia = idFarmacia;
        this.nombreFar = nombreFar;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Farmacia() {
    }
    
    
    //Setters y Getters
    public int getIdFarmacia() {
        return idFarmacia;
    }

    public String getNombreFar() {
        return nombreFar;
    }

    public void setNombreFar(String nombreFar) {
        this.nombreFar = nombreFar;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public int sacarID(){
        LinkedList<String[]> listaFar = DataAccessLayer.DataAccess.leerCSV("Farmacias.csv");
        int id = 1;
        if(listaFar.size()==1){
           return id; 
        }else{
           id = Integer.parseInt(listaFar.getLast()[0]) + 1;
           return id;
        }    
    }
    
    public void mostrarDatos(javax.swing.JTable tabla){
        LinkedList<String[]> lista = DataAccessLayer.DataAccess.leerCSV("Farmacias.csv");
        //Matriz
        String matriz[][] = new String[lista.size()-1][4];

        //Se llena la matriz con los datos
        for (int i = 0; i < lista.size()-1; i++) {
            for (int j = 0; j < 4; j++) {
                matriz[i][j] = lista.get(i+1)[j];
            }
        }
        //Se muestra la matriz en la tabla
        tabla.setModel(new javax.swing.table.DefaultTableModel(
                matriz,
                lista.get(0)
        ) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
    }
    
    public String[] aString() {
        String[] datos = new String[4];
        datos[0] = String.valueOf(idFarmacia);
        datos[1] = nombreFar;
        datos[2] = direccion;
        datos[3] = telefono;
        return datos;
    }
    
    public void buscarPorNombre(String nombre, javax.swing.JTable tblFarmacia){
        
        LinkedList<String[]> lista = new LinkedList<String[]>();
        LinkedList<String[]> listaFar = DataAccessLayer.DataAccess.leerCSV("Farmacias.csv");
        for (int i = 0; i < listaFar.size()-1; i++) {
            if (listaFar.get(i)[1].equals(nombre)) {
                lista.add(listaFar.get(i));
            }
        }
        if (lista.isEmpty()) {
            mostrarDatos(tblFarmacia);
            JOptionPane.showMessageDialog(null, "No se encontro ningun registro con ese nombre");
        }else{
            //Matriz
            String matriz[][] = new String[lista.size()][4];

            //Se llena la matriz con los datos
            for (int i = 0; i < lista.size(); i++) {
                for (int j = 0; j < 4; j++) {
                    matriz[i][j] = lista.get(i)[j];
                }
            }
            tblFarmacia.setModel(new javax.swing.table.DefaultTableModel(
                    matriz,
                    listaFar.get(0)
            ) {
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            });
        }
        
    }
    
    public void farmaciasExiste(){
        String path = "Farmacias.csv";
        File archivo = new File(path);
        if (!archivo.exists()) {
            LinkedList<String[]> lista = new LinkedList();
            String[] datos = {"ID Farmacia", "Nombre", "Direccion", "Telefono"};
            lista.add(datos);
            DataAccessLayer.DataAccess.escribirCSV(lista, path);
        }
    }
    
}
