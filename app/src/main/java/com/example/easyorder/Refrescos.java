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

public class Refrescos extends AppCompatActivity {


    ListView lstData;
    ArrayAdapter<String> mAdapter;
    private HashMap<String, String> listaProducto;
    private ArrayList<String> viusalizarProductos;
    String numerTable;
    ControladorDB controladorDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refrescos);
        getSupportActionBar().setTitle("Refrescos");
        controladorDB = new ControladorDB(this);

        Bundle extra = getIntent().getExtras();
        numerTable = extra.getString("numeroMesa");

        Refrescos.ConnectMySql connectMySql = new Refrescos.ConnectMySql();
        connectMySql.execute("");



        lstData = findViewById(R.id.lstData);
        lstData.setClickable(true);
        lstData.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {


                final  String cod_prod;
                final String codContr;

                String compuesto= null;
                String partes[]= null;
                String precio = null;
                final double precioDouble;
                final int[] cantidad = new int[1];
                final String cod_producto;
                final Insert insertar = new Insert();
                AlertDialog.Builder dialog = new AlertDialog.Builder(Refrescos.this);
                View vista = getLayoutInflater().inflate(R.layout.dialog_add, null);

                final TextView dialog_title =  vista.findViewById(R.id.titulo);
                final TextView dialog_precio = vista.findViewById(R.id.precio_producto);
                final ImageView dialog_img = vista.findViewById(R.id.img_producto);
                final NumberPicker numberPicker= vista.findViewById(R.id.cantidad);


                switch (position){
                    case 0:

                        cod_prod= "CCL";
                        compuesto=listaProducto.get(cod_prod);
                        partes =compuesto.split(":");
                        precio = partes[1];
                        precioDouble= Double.parseDouble(precio);


                        dialog.setView(vista);

                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });

                        dialog_img.setImageResource(R.drawable.ccl);
                        dialog_title.setText(viusalizarProductos.get(0));
                        dialog_precio.setText("Precio: " + precio +"€");



                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                if (cantidad[0]!=0)
                                controladorDB.addProducto(numerTable,cod_prod, viusalizarProductos.get(0), precioDouble, cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case 1:

                        cod_prod= "CCN";
                        codContr= cod_prod;
                        compuesto=listaProducto.get(cod_prod);
                        partes =compuesto.split(":");
                        precio = partes[1];
                        precioDouble= Double.parseDouble(precio);


                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);
                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });

                        dialog_img.setImageResource(R.drawable.ccn);
                        dialog_title.setText(viusalizarProductos.get(1));
                        dialog_precio.setText("Precio: " + precio +"€");
                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (cantidad[0]!=0)
                                controladorDB.addProducto(numerTable,codContr, viusalizarProductos.get(1), precioDouble, cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case 2:
                        cod_prod= "CCZ";
                        codContr= cod_prod;
                        compuesto=listaProducto.get(cod_prod);
                        partes =compuesto.split(":");
                        precio = partes[1];
                        precioDouble= Double.parseDouble(precio);


                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);
                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });

                        dialog_img.setImageResource(R.drawable.ccz);
                        dialog_title.setText(viusalizarProductos.get(2));
                        dialog_precio.setText("Precio: " + precio +"€");
                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (cantidad[0]!=0)
                                controladorDB.addProducto(numerTable,codContr, viusalizarProductos.get(2), precioDouble, cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case 3:
                        cod_prod= "FL";
                        codContr= cod_prod;
                        compuesto=listaProducto.get(cod_prod);
                        partes =compuesto.split(":");
                        precio = partes[1];
                        precioDouble= Double.parseDouble(precio);
                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);
                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });

                        dialog_img.setImageResource(R.drawable.fl);
                        dialog_title.setText(viusalizarProductos.get(3));
                        dialog_precio.setText("Precio: " + precio +"€");
                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (cantidad[0]!=0)
                                controladorDB.addProducto(numerTable,codContr, viusalizarProductos.get(3), precioDouble, cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;



                    case 4:
                        cod_prod= "FN";
                        codContr= cod_prod;
                        compuesto=listaProducto.get(cod_prod);
                        partes =compuesto.split(":");
                        precio = partes[1];
                        precioDouble= Double.parseDouble(precio);
                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);
                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });

                        dialog_img.setImageResource(R.drawable.fn);
                        dialog_title.setText(viusalizarProductos.get(4));
                        dialog_precio.setText("Precio: " + precio +"€");
                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (cantidad[0]!=0)
                                controladorDB.addProducto(numerTable,codContr, viusalizarProductos.get(4), precioDouble, cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;
                    case 5:
                        cod_prod= "RB";
                        codContr= cod_prod;
                        compuesto=listaProducto.get(cod_prod);
                        partes =compuesto.split(":");
                        precio = partes[1];
                        precioDouble= Double.parseDouble(precio);
                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);
                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });

                        dialog_img.setImageResource(R.drawable.rb);
                        dialog_title.setText(viusalizarProductos.get(5));
                        dialog_precio.setText("Precio: " + precio +"€");
                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (cantidad[0]!=0)
                                controladorDB.addProducto(numerTable,codContr, viusalizarProductos.get(5), precioDouble, cantidad[0]);

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
            Toast.makeText(Refrescos.this, "Please wait...", Toast.LENGTH_SHORT)
                    .show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                //Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/easy_order", "ruben_admin", "anmarulo");
                Connection con = DriverManager.getConnection("jdbc:mysql://188.127.174.120:3306/productos", "ruben", "ruben");

                String result = "Database Connection Successful\n";
                PreparedStatement st = con.prepareStatement("SELECT * FROM refrescos");
                ResultSet rs = st.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();

                listaProducto = new HashMap<>();
                viusalizarProductos= new ArrayList<>();
                while (rs.next()) {

                    listaProducto.put(rs.getString(1),rs.getString(2)+":"+rs.getDouble(3));
                    viusalizarProductos.add(rs.getString(2));



                }

                while (rs.next()) {
                    result += rs.getString(3) + "\n";
                }
                res = result;
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