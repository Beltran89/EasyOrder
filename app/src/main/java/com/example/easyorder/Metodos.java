package com.example.easyorder;

import android.app.AlertDialog;
import android.icu.text.Transliterator;
import android.widget.NumberPicker;
import android.widget.Spinner;

import java.util.HashMap;

public class Metodos {

    public Double obtenerPrecio(HashMap<String, String> listaProducto, String cod_prod){
       String compuesto=listaProducto.get(cod_prod);
       String[] partes =compuesto.split(":");
       String precio = partes[1];
       Double precioDouble= Double.parseDouble(precio);
        return precioDouble;
    }
    public double precioMezcla(Spinner mezcla, Double precioDouble) {
        double precioFinal;
        String tipoMezcla;


        if (mezcla.getSelectedItemPosition() == 0) {
            

        } else {
            if (mezcla.getSelectedItemPosition() == 1) {
                tipoMezcla = mezcla.getSelectedItem().toString();
                precioFinal = precioDouble;
                return precioFinal;
            } else if (mezcla.getSelectedItemPosition() == 2) {
                tipoMezcla = mezcla.getSelectedItem().toString();
                precioFinal = precioDouble;
                return precioFinal;
            } else if (mezcla.getSelectedItemPosition() == 3) {
                tipoMezcla = mezcla.getSelectedItem().toString();
                precioFinal = precioDouble;
                return precioFinal;
            } else if (mezcla.getSelectedItemPosition() == 4) {
                tipoMezcla = mezcla.getSelectedItem().toString();
                precioFinal = precioDouble;
                return precioFinal;
            } else if (mezcla.getSelectedItemPosition() == 5) {
                tipoMezcla = mezcla.getSelectedItem().toString();
                precioFinal = precioDouble;
                return precioFinal;
            } else if (mezcla.getSelectedItemPosition() == 6) {
                tipoMezcla = mezcla.getSelectedItem().toString();
                precioFinal = precioDouble;
                return precioFinal;
            } else {
                tipoMezcla = "Red Bull";
                precioFinal = precioDouble + 1.00;
                return precioFinal;
            }
            
        }

        return 0.00;
    }
}
