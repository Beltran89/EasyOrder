package com.example.easyorder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shishas);

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


                final  String cod_prod;
                final String codContr;

                String compuesto= null;
                String partes[]= null;
                String precio = null;
                final double precioDouble;
                final int[] cantidad = new int[1];
                final String cod_producto;
                final Insert insertar = new Insert();

                AlertDialog.Builder dialog = new AlertDialog.Builder(Shishas.this);
                View vista = getLayoutInflater().inflate(R.layout.dialog_add, null);

                final TextView dialog_title =  vista.findViewById(R.id.titulo);
                final TextView dialog_precio = vista.findViewById(R.id.precio_producto);
                final ImageView dialog_img = vista.findViewById(R.id.img_producto);
                final NumberPicker numberPicker= vista.findViewById(R.id.cantidad);

                switch (selected){

 ///////////////////////////       STANDARD    //////////////////////////////
                    case "Dos Manzanas":
                        cod_prod= "DMA";
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

                        dialog_img.setImageResource(R.drawable.dos_manzanas);
                        dialog_title.setText(visualizarProductosStandard.get(0));
                        dialog_precio.setText("Precio: " + precio +"€");



                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosStandard.get(0), precioDouble, cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;
                    case "Kiwi":
                        cod_prod= "KWI";
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

                        dialog_img.setImageResource(R.drawable.kiwi);
                        dialog_title.setText(visualizarProductosStandard.get(1));
                        dialog_precio.setText("Precio: " + precio +"€");



                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosStandard.get(1), precioDouble, cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;
                    case "Limon y Menta":
                        cod_prod= "LYM";
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

                        dialog_img.setImageResource(R.drawable.limon_menta);
                        dialog_title.setText(visualizarProductosStandard.get(2));
                        dialog_precio.setText("Precio: " + precio +"€");



                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosStandard.get(2), precioDouble, cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;
                    case "Melocoton":
                        cod_prod= "MLC";
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

                        dialog_img.setImageResource(R.drawable.melocon);
                        dialog_title.setText(visualizarProductosStandard.get(3));
                        dialog_precio.setText("Precio: " + precio +"€");



                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosStandard.get(3), precioDouble, cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;
                    case "Pomelo":
                        cod_prod= "PML";
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

                        dialog_img.setImageResource(R.drawable.pomelo);
                        dialog_title.setText(visualizarProductosStandard.get(4));
                        dialog_precio.setText("Precio: " + precio +"€");



                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosStandard.get(4), precioDouble, cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;
                    case "Sandia Helada":
                        cod_prod= "SAH";
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

                        dialog_img.setImageResource(R.drawable.sandia_helada);
                        dialog_title.setText(visualizarProductosStandard.get(5));
                        dialog_precio.setText("Precio: " + precio +"€");



                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosStandard.get(5), precioDouble, cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;
///////////////////////////////     PREMIUM       /////////////////////
                    case "Fruta de la Pasion y Menta":
                        cod_prod= "FPM";
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

                        dialog_img.setImageResource(R.drawable.pasion_menta);
                        dialog_title.setText(visualizarProductosPremium.get(0));
                        dialog_precio.setText("Precio: " + precio +"€");



                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosPremium.get(0), precioDouble, cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "Gominolas de Corazones":
                        cod_prod= "GOC";
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

                        dialog_img.setImageResource(R.drawable.gominolas_corazones);
                        dialog_title.setText(visualizarProductosPremium.get(1));
                        dialog_precio.setText("Precio: " + precio +"€");



                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosPremium.get(1), precioDouble, cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "Limon, Cardamomo y Uva":
                        cod_prod= "LCU";
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

                        dialog_img.setImageResource(R.drawable.limon_cardamomo_uva);
                        dialog_title.setText(visualizarProductosPremium.get(2));
                        dialog_precio.setText("Precio: " + precio +"€");



                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosPremium.get(2), precioDouble, cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "Licor Dulce de Limoncello":
                        cod_prod= "LDL";
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

                        dialog_img.setImageResource(R.drawable.limoncello);
                        dialog_title.setText(visualizarProductosPremium.get(3));
                        dialog_precio.setText("Precio: " + precio +"€");



                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosPremium.get(3), precioDouble, cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "Melon Dulce":
                        cod_prod= "MDL";
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

                        dialog_img.setImageResource(R.drawable.melon_dulce);
                        dialog_title.setText(visualizarProductosPremium.get(4));
                        dialog_precio.setText("Precio: " + precio +"€");



                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosPremium.get(4), precioDouble, cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "Melocoton, Mango, Menta y Hielo":
                        cod_prod= "MMM";
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

                        dialog_img.setImageResource(R.drawable.melocon_mango_menta_hielo);
                        dialog_title.setText(visualizarProductosPremium.get(5));
                        dialog_precio.setText("Precio: " + precio +"€");



                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosPremium.get(5), precioDouble, cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "Ositos de Gominola":
                        cod_prod= "OSG";
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

                        dialog_img.setImageResource(R.drawable.ositos_gominola);
                        dialog_title.setText(visualizarProductosPremium.get(6));
                        dialog_precio.setText("Precio: " + precio +"€");



                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosPremium.get(6), precioDouble, cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "Pomelo y Mandarina":
                        cod_prod= "PMM";
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

                        dialog_img.setImageResource(R.drawable.pomelo_mandarina);
                        dialog_title.setText(visualizarProductosPremium.get(6));
                        dialog_precio.setText("Precio: " + precio +"€");



                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosPremium.get(6), precioDouble, cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;
                    case "Tarta de Limon":
                        cod_prod= "TLM";
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

                        dialog_img.setImageResource(R.drawable.tarta_limon);
                        dialog_title.setText(visualizarProductosPremium.get(7));
                        dialog_precio.setText("Precio: " + precio +"€");



                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cantidad[0]!=0)
                                    controladorDB.addProducto(numerTable,cod_prod, visualizarProductosPremium.get(7), precioDouble, cantidad[0]);

                            }
                        });
                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;


                    default:

                        break;

                }

                Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG)
                        .show();

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
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }*/

    private class ConnectMySql extends AsyncTask<String, Void, String> {
        String res = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(Shishas.this, "Please wait...", Toast.LENGTH_SHORT)
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


               // String result = numerTable;
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

}
