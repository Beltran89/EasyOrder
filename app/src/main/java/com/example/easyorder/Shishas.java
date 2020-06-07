package com.example.easyorder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import java.util.LinkedHashMap;


import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

public class Shishas extends AppCompatActivity {

    String numerTable, tipoStandard, tipoPremium;
    ControladorDB controladorDB;
    private HashMap<String, String> listaProducto;
    private ArrayList<String> visualizarProductosStandard, visualizarProductosPremium;
    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> shishasCollection;
    ExpandableListView expListView;
    Metodos metodos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shishas);
        getSupportActionBar().setTitle("Shishas");

        Shishas.ConnectMySql connectMySql = new Shishas.ConnectMySql();
        connectMySql.execute("");
        controladorDB = new ControladorDB(this);

        Bundle extra = getIntent().getExtras();
        numerTable = extra.getString("numeroMesa");

    }

    private void createGroupList() {
        groupList = new ArrayList<String>();
        groupList.add("Standard");
        groupList.add("Premium");
    }
    public void creando(){
        expListView = (ExpandableListView) findViewById(R.id.shishas_list);
        Drawable expand = getResources().getDrawable( R.drawable.expandir);
        expListView.setGroupIndicator(expand);
        final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
                this, groupList, shishasCollection);
        expListView.setAdapter(expListAdapter);

        //setGroupIndicatorToRight();

        expListView.setOnChildClickListener(new OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                final String selected = (String) expListAdapter.getChild(
                        groupPosition, childPosition);

                AlertDialog.Builder dialog = new AlertDialog.Builder(Shishas.this);
                View vista = getLayoutInflater().inflate(R.layout.dialog_add, null);
                metodos =  new Metodos();
                final  String cod_prod;
                final String codContr;

                String compuesto= null;
                String partes[]= null;
                String precio = null;
                final double precioDouble;
                final int[] cantidad = new int[1];
                final String cod_producto;
                final Insert insertar = new Insert();



                final TextView dialog_title =  vista.findViewById(R.id.titulo);
                final TextView dialog_precio = vista.findViewById(R.id.precio_producto);
                final ImageView dialog_img = vista.findViewById(R.id.img_producto);
                final NumberPicker numberPicker= vista.findViewById(R.id.cantidad);

                switch (selected){

 ///////////////////////////       STANDARD    //////////////////////////////
                    case "Dos Manzanas":
                        cod_prod= "DMA";

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });

                        dialog_img.setImageResource(R.drawable.dos_manzanas);
                        dialog_title.setText(visualizarProductosStandard.get(0));
                        dialog_precio.setText("Precio: " + metodos.obtenerPrecio(listaProducto,cod_prod).toString() +"€");



                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosStandard.get(0), metodos.obtenerPrecio(listaProducto,cod_prod), cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;
                    case "Kiwi":
                        cod_prod= "KWI";

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });

                        dialog_img.setImageResource(R.drawable.kiwi);
                        dialog_title.setText(visualizarProductosStandard.get(1));
                        dialog_precio.setText("Precio: " + metodos.obtenerPrecio(listaProducto,cod_prod).toString() +"€");



                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosStandard.get(1), metodos.obtenerPrecio(listaProducto,cod_prod), cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;
                    case "Limon y Menta":
                        cod_prod= "LYM";

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });

                        dialog_img.setImageResource(R.drawable.limon_menta);
                        dialog_title.setText(visualizarProductosStandard.get(2));
                        dialog_precio.setText("Precio: " + metodos.obtenerPrecio(listaProducto,cod_prod).toString() +"€");



                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosStandard.get(2), metodos.obtenerPrecio(listaProducto,cod_prod), cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;
                    case "Melocoton":
                        cod_prod= "MLC";

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });

                        dialog_img.setImageResource(R.drawable.melocon);
                        dialog_title.setText(visualizarProductosStandard.get(3));
                        dialog_precio.setText("Precio: " + metodos.obtenerPrecio(listaProducto,cod_prod).toString() +"€");



                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosStandard.get(3), metodos.obtenerPrecio(listaProducto,cod_prod), cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;
                    case "Pomelo":
                        cod_prod= "PML";

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });

                        dialog_img.setImageResource(R.drawable.pomelo);
                        dialog_title.setText(visualizarProductosStandard.get(4));
                        dialog_precio.setText("Precio: " + metodos.obtenerPrecio(listaProducto,cod_prod).toString() +"€");

                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosStandard.get(4), metodos.obtenerPrecio(listaProducto,cod_prod), cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;
                    case "Sandia Helada":
                        cod_prod= "SAH";

                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);
                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });

                        dialog_img.setImageResource(R.drawable.sandia_helada);
                        dialog_title.setText(visualizarProductosStandard.get(5));
                        dialog_precio.setText("Precio: " + metodos.obtenerPrecio(listaProducto,cod_prod).toString() +"€");



                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosStandard.get(5), metodos.obtenerPrecio(listaProducto,cod_prod), cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

///////////////////////////////     PREMIUM       /////////////////////
                    case "Fruta de la Pasion y Menta":
                        cod_prod= "FPM";

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);
                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });

                        dialog_img.setImageResource(R.drawable.pasion_menta);
                        dialog_title.setText(visualizarProductosPremium.get(0));
                        dialog_precio.setText("Precio: " + metodos.obtenerPrecio(listaProducto,cod_prod).toString() +"€");



                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosPremium.get(0), metodos.obtenerPrecio(listaProducto,cod_prod), cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "Gominolas de Corazones":
                        cod_prod= "GOC";
                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });

                        dialog_img.setImageResource(R.drawable.gominolas_corazones);
                        dialog_title.setText(visualizarProductosPremium.get(1));
                        dialog_precio.setText("Precio: " + metodos.obtenerPrecio(listaProducto,cod_prod).toString() +"€");

                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosPremium.get(1), metodos.obtenerPrecio(listaProducto,cod_prod), cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "Limon, Cardamomo y Uva":
                        cod_prod= "LCU";

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);
                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });

                        dialog_img.setImageResource(R.drawable.limon_cardamomo_uva);
                        dialog_title.setText(visualizarProductosPremium.get(2));
                        dialog_precio.setText("Precio: " + metodos.obtenerPrecio(listaProducto,cod_prod).toString() +"€");
                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosPremium.get(2), metodos.obtenerPrecio(listaProducto,cod_prod), cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "Licor Dulce de Limoncello":
                        cod_prod= "LDL";
                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });

                        dialog_img.setImageResource(R.drawable.limoncello);
                        dialog_title.setText(visualizarProductosPremium.get(3));
                        dialog_precio.setText("Precio: " + metodos.obtenerPrecio(listaProducto,cod_prod).toString() +"€");



                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosPremium.get(3), metodos.obtenerPrecio(listaProducto,cod_prod), cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "Melon Dulce":
                        cod_prod= "MDL";

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });

                        dialog_img.setImageResource(R.drawable.melon_dulce);
                        dialog_title.setText(visualizarProductosPremium.get(4));
                        dialog_precio.setText("Precio: " + metodos.obtenerPrecio(listaProducto,cod_prod).toString() +"€");
                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosPremium.get(4), metodos.obtenerPrecio(listaProducto,cod_prod), cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "Melocoton, Mango, Menta y Hielo":
                        cod_prod= "MMM";
                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);
                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });

                        dialog_img.setImageResource(R.drawable.melocon_mango_menta_hielo);
                        dialog_title.setText(visualizarProductosPremium.get(5));
                        dialog_precio.setText("Precio: " + metodos.obtenerPrecio(listaProducto,cod_prod).toString() +"€");

                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosPremium.get(5), metodos.obtenerPrecio(listaProducto,cod_prod), cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "Ositos de Gominola":
                        cod_prod= "OSG";
                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);
                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });

                        dialog_img.setImageResource(R.drawable.ositos_gominola);
                        dialog_title.setText(visualizarProductosPremium.get(6));
                        dialog_precio.setText("Precio: " + metodos.obtenerPrecio(listaProducto,cod_prod).toString() +"€");

                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosPremium.get(6), metodos.obtenerPrecio(listaProducto,cod_prod), cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "Pomelo y Mandarina":
                        cod_prod= "PMM";
                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);
                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });

                        dialog_img.setImageResource(R.drawable.pomelo_mandarina);
                        dialog_title.setText(visualizarProductosPremium.get(6));
                        dialog_precio.setText("Precio: " + metodos.obtenerPrecio(listaProducto,cod_prod).toString() +"€");
                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosPremium.get(6), metodos.obtenerPrecio(listaProducto,cod_prod), cantidad[0]);
                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "Tarta de Limon":
                        cod_prod= "TLM";
                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);
                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });

                        dialog_img.setImageResource(R.drawable.tarta_limon);
                        dialog_title.setText(visualizarProductosPremium.get(7));
                        dialog_precio.setText("Precio: " + metodos.obtenerPrecio(listaProducto,cod_prod).toString() +"€");



                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosPremium.get(7), metodos.obtenerPrecio(listaProducto,cod_prod), cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;
                    default:

                        break;

                }


                return true;
            }
        });
    }

    private void createCollection(String[] listaStandard, String[] listaPremium) {
        // preparing laptops collection(child)

        String[] productoStandard = listaStandard;
        String[] productoPremium = listaPremium;
        shishasCollection = new LinkedHashMap<String, List<String>>();

        for (String shishas : groupList) {
             if (shishas.equals("Standard"))
                loadChild(productoStandard);
            else
                 loadChild(productoPremium);

            shishasCollection.put(shishas, childList);
        }
    }

    private void loadChild(String[] laptopModels) {
        childList = new ArrayList<String>();
        for (String model : laptopModels)
            childList.add(model);
    }

    private void setGroupIndicatorToRight() {
        /* Get the screen width */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;

        expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
                - getDipsFromPixel(5));
    }

    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }


    private class ConnectMySql extends AsyncTask<String, Void, String> {
        String res = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(Shishas.this, "Cargando Carta", Toast.LENGTH_SHORT)
                    .show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://188.127.174.120:3306/productos", "ruben", "ruben");

                listaProducto = new HashMap<>();
                visualizarProductosStandard = new ArrayList<>();
                visualizarProductosPremium= new ArrayList<>();

                PreparedStatement st = con.prepareStatement("SELECT * FROM shishas WHERE tipo=?");
                tipoStandard = "standard";
                st.setString(1, tipoStandard);
                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    listaProducto.put(rs.getString(1), rs.getString(2) + ":" + rs.getDouble(3));
                    visualizarProductosStandard.add(rs.getString(2));
                }

                PreparedStatement st2 = con.prepareStatement("SELECT * FROM shishas WHERE tipo=?");
                tipoPremium = "premium";
                st.setString(1, tipoPremium);
                ResultSet rs2 = st.executeQuery();

                while (rs2.next()) {
                    listaProducto.put(rs2.getString(1), rs2.getString(2) + ":" + rs2.getDouble(3));
                    visualizarProductosPremium.add(rs2.getString(2));
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

            int numero = visualizarProductosStandard.size();
            String [] standard = new String[numero];
            for (int i=0; i<visualizarProductosStandard.size(); i++) {
                standard[i] = visualizarProductosStandard.get(i);
            }

            int numeroP = visualizarProductosPremium.size();
            String [] premium = new String[numeroP];
            for (int i=0; i<visualizarProductosPremium.size(); i++) {
                premium[i] = visualizarProductosPremium.get(i);
            }

            createGroupList();
            createCollection(standard, premium);
            creando();


        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        verCarrito();
        return super.onOptionsItemSelected(item);
    }
    public void verCarrito(){
        Intent carrito = new Intent(this, Carrito.class);
        carrito.putExtra("mesaNum", numerTable);
        startActivity(carrito);
    }

}
