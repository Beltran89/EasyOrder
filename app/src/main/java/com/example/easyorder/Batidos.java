package com.example.easyorder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;

public class Batidos extends AppCompatActivity {

    ListView lstData;
    private HashMap<String, String> listaProducto;
    private ArrayList<String> viusalizarProductos;
    ArrayAdapter<String> mAdapter;
    ControladorDB controladorDB;
    String numerTable;
    Metodos metodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batidos);
        getSupportActionBar().setTitle("Batidos");
        controladorDB = new ControladorDB(this);

        Bundle extra = getIntent().getExtras();
        numerTable = extra.getString("numeroMesa");
        Batidos.ConnectMySql connectMySql = new Batidos.ConnectMySql();
        connectMySql.execute("");

        lstData = findViewById(R.id.lstData);
        lstData.setClickable(true);
        lstData.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int position, long arg3) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(Batidos.this);
                View vista = getLayoutInflater().inflate(R.layout.dialog_add, null);
                metodos = new Metodos();
                final int[] cantidad = new int[1];
                final  String cod_prod;
                final TextView dialog_title =  vista.findViewById(R.id.titulo);
                final TextView dialog_precio = vista.findViewById(R.id.precio_producto);
                final ImageView dialog_img = vista.findViewById(R.id.img_producto);
                final NumberPicker numberPicker= vista.findViewById(R.id.cantidad);

                switch (position){

                    case 0:
                        cod_prod= "BCA";
                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);
                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });
                        dialog_img.setImageResource(R.drawable.chip_ahoy);
                        dialog_title.setText(viusalizarProductos.get(position));
                        dialog_precio.setText("Precio: " + metodos.obtenerPrecio(listaProducto,cod_prod).toString() +"€");
                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, viusalizarProductos.get(position), metodos.obtenerPrecio(listaProducto,cod_prod), cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case 1:
                        cod_prod= "BCH";
                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);
                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });
                        dialog_img.setImageResource(R.drawable.chocolate);
                        dialog_title.setText(viusalizarProductos.get(position));
                        dialog_precio.setText("Precio: " + metodos.obtenerPrecio(listaProducto,cod_prod).toString() +"€");
                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, viusalizarProductos.get(position), metodos.obtenerPrecio(listaProducto,cod_prod), cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case 2:
                        cod_prod= "BDN";
                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);
                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });
                        dialog_img.setImageResource(R.drawable.donetes);
                        dialog_title.setText(viusalizarProductos.get(position));
                        dialog_precio.setText("Precio: " + metodos.obtenerPrecio(listaProducto,cod_prod).toString() +"€");
                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, viusalizarProductos.get(position), metodos.obtenerPrecio(listaProducto,cod_prod), cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case 3:
                        cod_prod= "BKB";
                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);
                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });
                        dialog_img.setImageResource(R.drawable.kinder_bueno);
                        dialog_title.setText(viusalizarProductos.get(position));
                        dialog_precio.setText("Precio: " + metodos.obtenerPrecio(listaProducto,cod_prod).toString() +"€");
                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, viusalizarProductos.get(3), metodos.obtenerPrecio(listaProducto,cod_prod), cantidad[0]);
                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case 4:
                        cod_prod= "BOR";
                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);
                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });
                        dialog_img.setImageResource(R.drawable.oreo);
                        dialog_title.setText(viusalizarProductos.get(position));
                        dialog_precio.setText("Precio: " + metodos.obtenerPrecio(listaProducto,cod_prod).toString() +"€");
                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, viusalizarProductos.get(position), metodos.obtenerPrecio(listaProducto,cod_prod), cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case 5:
                        cod_prod= "BPR";
                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);
                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });
                        dialog_img.setImageResource(R.drawable.pantera_rosa);
                        dialog_title.setText(viusalizarProductos.get(position));
                        dialog_precio.setText("Precio: " + metodos.obtenerPrecio(listaProducto,cod_prod).toString() +"€");
                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, viusalizarProductos.get(position), metodos.obtenerPrecio(listaProducto,cod_prod), cantidad[0]);
                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case 6:
                        cod_prod= "BVA";
                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);
                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });
                        dialog_img.setImageResource(R.drawable.vainilla);
                        dialog_title.setText(viusalizarProductos.get(position));
                        dialog_precio.setText("Precio: " + metodos.obtenerPrecio(listaProducto,cod_prod).toString() +"€");
                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, viusalizarProductos.get(position), metodos.obtenerPrecio(listaProducto,cod_prod), cantidad[0]);
                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;
                    default:
                        break;
                }




            }


        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        verCarrito();
        return super.onOptionsItemSelected(item);
    }
    private class ConnectMySql extends AsyncTask<String, Void, String> {
        String res = "";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(Batidos.this, "Cargando Carta", Toast.LENGTH_SHORT)
                    .show();

        }
        @Override
        protected String doInBackground(String... params) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
               Connection con = DriverManager.getConnection("jdbc:mysql://188.127.174.120:3306/productos", "ruben", "ruben");

                PreparedStatement st = con.prepareStatement("SELECT * FROM batidos");
                ResultSet rs = st.executeQuery();
                listaProducto = new HashMap<>();
                viusalizarProductos= new ArrayList<>();

                while (rs.next()) {
                    listaProducto.put(rs.getString(1),rs.getString(2)+":"+rs.getDouble(3));
                    viusalizarProductos.add(rs.getString(2));
                }

                con.close();

            } catch (Exception e) {
                e.printStackTrace();
                res = e.toString();
            }
            return res;
        }

        @Override
        protected void onPostExecute(String result) {
            mAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.item, R.id.linea_producto, viusalizarProductos);
            lstData.setAdapter(mAdapter);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void verCarrito(){
        Intent carrito = new Intent(this, Carrito.class);
        carrito.putExtra("mesaNum", numerTable);
        startActivity(carrito);
    }
}