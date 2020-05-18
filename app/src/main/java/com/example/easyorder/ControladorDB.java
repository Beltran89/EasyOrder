package com.example.easyorder;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.text.AutoText;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;

public class ControladorDB extends SQLiteOpenHelper {


    public ControladorDB(Context context) {
        super(context, "com.example.easyOrder.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("CREATE TABLE PEDIDO( ID INTEGER PRIMARY KEY AUTOINCREMENT, MESA TEXT NOT NULL, ID_PRODUCTO TEXT NOT NULL, NOMBRE_PRODUCTO TEXT NOT NULL, PRECIO INT NOT NULL, CANTIDAD INT NOT NULL);");
       db.execSQL("CREATE TABLE PEDIDO( ID INTEGER PRIMARY KEY AUTOINCREMENT, MESA TEXT NOT NULL, ID_PRODUCTO TEXT NOT NULL, NOMBRE_PRODUCTO TEXT NOT NULL, PRECIO INT NOT NULL, CANTIDAD INT NOT NULL, MEZCLA TEXT NOT NULL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addProducto(String mesa, String id_producto ,String nombre_producto, double precio, int cantidad) {

        String[] args = new String[] {id_producto};
        SQLiteDatabase dbR = this.getReadableDatabase();
        Cursor c = dbR.rawQuery(" SELECT CANTIDAD FROM PEDIDO WHERE ID_PRODUCTO=? ", args);

        int cantidadActual=0;
        if (c.moveToFirst()) {

            do {
                cantidadActual = c.getInt(0);
                borrarProducto(nombre_producto);
            } while(c.moveToNext());
        }
        dbR.close();

        int cantidaTotal= cantidadActual + cantidad;
        String mezcla= "no";

        ContentValues registro = new ContentValues();
        registro.put("MESA", mesa);
        registro.put("ID_PRODUCTO", id_producto);
        registro.put("NOMBRE_PRODUCTO", nombre_producto);
        registro.put("PRECIO", precio);
        registro.put("CANTIDAD", cantidaTotal);
        registro.put("MEZCLA", mezcla);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("PEDIDO", null, registro);
        db.close();
    }
    public void addProductoCombinado(String mesa, String id_producto ,String nombre_producto, double precio, int cantidad, String mezcla) {

        String[] args = new String[] {id_producto, mezcla};
        SQLiteDatabase dbR = this.getReadableDatabase();
        Cursor c = dbR.rawQuery(" SELECT CANTIDAD FROM PEDIDO WHERE ID_PRODUCTO=? and MEZCLA=?", args);
        int cantidadActual=0;
        if (c.moveToFirst()) {

            do {
                cantidadActual = c.getInt(0);
                borrarProducto(nombre_producto);
            } while(c.moveToNext());
        }
        dbR.close();

        int cantidaTotal= cantidadActual + cantidad;

        ContentValues registro = new ContentValues();
        registro.put("MESA", mesa);
        registro.put("ID_PRODUCTO", id_producto);
        registro.put("NOMBRE_PRODUCTO", nombre_producto);
        registro.put("PRECIO", precio);
        registro.put("CANTIDAD", cantidaTotal);
        registro.put("MEZCLA", mezcla);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("PEDIDO", null, registro);
        db.close();
    }

    public int numeroReg() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PEDIDO", null);
        return cursor.getCount();
    }

    public String[] obtenerProductos() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PEDIDO", null);

        int regs = cursor.getCount();

        if (regs == 0) {
            db.close();
            return null;
        } else {
            String[] productos = new String[regs];

            cursor.moveToFirst();
            for (int i = 0; i < regs; i++) {
                if(cursor.getString(6).equals("no"))
                    productos[i] = cursor.getString(3) +"\n Cantidad :"+cursor.getString(5);
                else
                    productos[i] = cursor.getString(3)+"/"+cursor.getString(6)+"\n Cantidad :"+cursor.getString(5);
                cursor.moveToNext();
            }
            db.close();
            return productos;
        }
    }


    public void borrarProducto(String producto) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("PEDIDO", "NOMBRE_PRODUCTO=?", new String[]{producto});
        db.close();
    }
    public void borrarProductoCombinado(String bebida, String mezcla) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("PEDIDO", "NOMBRE_PRODUCTO=? AND MEZCLA=?", new String[]{bebida, mezcla});
        db.close();
    }
    public void borrarTodo() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("PEDIDO", null, null);
        db.close();
    }
    public void modificarCantidad(String nomProduc, int nuevaCantidad){
        String[] args = new String[] {nomProduc};

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("CANTIDAD", nuevaCantidad);
        db.update("PEDIDO", valores, "NOMBRE_PRODUCTO=?", args);
        db.close();
    }
    public void modificarCantidadCombinado(String bebida,String mezcla, int nuevaCantidad){
        String[] args = new String[] {bebida,mezcla};
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("CANTIDAD", nuevaCantidad);
        db.update("PEDIDO", valores, "NOMBRE_PRODUCTO=? AND MEZCLA=?", args);

        db.close();
    }


    public double enviarPedido() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PEDIDO", null);
        Double total=0.0;
        int regs = cursor.getCount();

        if (regs == 0) {
            db.close();
        } else {
            cursor.moveToFirst();
            double precioDouble;
            String cod_prod;
            int cantidad;
            String mesa, mezcla;

            for (int i = 0; i < regs; i++) {
                mesa = cursor.getString(1);
                cod_prod = cursor.getString(2);
                precioDouble = cursor.getDouble(4);
                cantidad = cursor.getInt(5);
                mezcla = cursor.getString(6);
                total = total + (cantidad * precioDouble);

                Insert insertar = new Insert();
                insertar.setMesaNum(mesa);
                insertar.setCod_prod(cod_prod);
                insertar.setPrecio(precioDouble);
                insertar.setCantidad(cantidad);
                insertar.setMezcla(mezcla);
                insertar.execute("");
                cursor.moveToNext();
            }
            db.close();
            borrarTodo();
        }
        return total;
    }


}
