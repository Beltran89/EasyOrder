package com.example.easyorder;

import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

class Insert extends AsyncTask<String, Void, String> {
    String cod_prod;
    Double precio;
    String res = "";
    int cantidad;
    String mesaNum;
    String mezcla;

    public void setMezcla(String mezcla) {
        this.mezcla = mezcla;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setCod_prod(String cod_prod) {
        this.cod_prod = cod_prod;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setMesaNum(String mesaNum) {this.mesaNum = mesaNum;}



    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://188.127.174.120:3306/productos", "ruben", "ruben");
            System.out.println("Databaseection success");

            String sql = "INSERT INTO pedidos (mesa_id,id_producto,precio,cantidad,mezcla) " +
                    "VALUES (?, ?, ?,?,?)";





            PreparedStatement sentencia;
            sentencia = con.prepareStatement(sql);


            sentencia.setString(1, mesaNum);
            sentencia.setString(2, cod_prod);
            sentencia.setDouble(3, precio);
            sentencia.setInt(4, cantidad);
            sentencia.setString(5,mezcla);
            sentencia.executeUpdate();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            res = e.toString();
        }

        return res;

    }

    @Override
    protected void onPostExecute(String result) {


    }
}
