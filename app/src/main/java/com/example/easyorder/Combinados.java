package com.example.easyorder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Combinados extends AppCompatActivity {
    TextView tipoRon, tipoGin, tipoVodka, tipoWisky;
    ListView ron, gin, vodka, wisky;
    SimpleAdapter ADAhere;
    String numerTable, tipoRonSting, tipoVodkaString, tipoGinString, tipoWiskyString ;
    List<Map<String, String>> data;
    ArrayAdapter<String> mAdapter, mAdapter2;

    // String[] standard;
    ControladorDB controladorDB;
    private HashMap<String, String> listaProducto;
    private ArrayList<String> visualizarProductosRon, visualizarProductosVodka, visualizarProductosGin, visualizarProductosWisky, visualizarRefrescos ;
    ExpandableListAdapter expListAdapter;

    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> combinadosCollection;
    ExpandableListView expListView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combinados);



        Combinados.ConnectMySql connectMySql = new Combinados.ConnectMySql();
        connectMySql.execute("");

        controladorDB = new ControladorDB(this);

        Bundle extra = getIntent().getExtras();
        numerTable = extra.getString("numeroMesa");

    }
    private void createGroupList() {
        groupList = new ArrayList<String>();
        groupList.add("Ron");
        groupList.add("Gin");
        groupList.add("Wisky");
        groupList.add("Vodka");
    }
    public void creando(){
        expListView = (ExpandableListView) findViewById(R.id.combinados_list);
        Drawable expand = getResources().getDrawable( R.drawable.expandir);
        expListView.setGroupIndicator(expand);

        final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
                this, groupList, combinadosCollection);
        expListView.setAdapter(expListAdapter);

        //setGroupIndicatorToRight();

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                final String selected = (String) expListAdapter.getChild(
                        groupPosition, childPosition);


                final String cod_prod;
                final String codContr;

                String compuesto = null;
                final String nomProducto;
                String partes[] = null;
                String precio = null;
                final double precioDouble;
                final int[] cantidad = new int[1];
                final String cod_producto;
                final Insert insertar = new Insert();

                AlertDialog.Builder dialog = new AlertDialog.Builder(Combinados.this);
                View vista = getLayoutInflater().inflate(R.layout.dialog_combinados, null);


                final TextView dialog_title = vista.findViewById(R.id.titulo);
                final TextView dialog_precio = vista.findViewById(R.id.precio_producto);
                final ImageView dialog_img = vista.findViewById(R.id.img_producto);
                final NumberPicker numberPicker = vista.findViewById(R.id.cantidad);
                final Spinner mezcla = (Spinner) vista.findViewById(R.id.mezcla);


                switch (selected) {

                    ///////////////////////////       Ron    //////////////////////////////
                    case "Barcelo":
                        cod_prod = "BCL";
                        compuesto = listaProducto.get(cod_prod);
                        partes = compuesto.split(":");
                        precio = partes[1];
                        precioDouble = Double.parseDouble(precio);
                        nomProducto= partes[0];

                        dialog_img.setImageResource(R.drawable.barcelo);
                        dialog_title.setText(nomProducto);
                        dialog_precio.setText("Precio: " + precio +"€");

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });


                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String tipoMezcla;
                                final double precioFinal;
                                if (mezcla.getSelectedItemPosition() == 0) {


                                } else {
                                    if (mezcla.getSelectedItemPosition() == 1) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 2) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 3) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 4) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 5) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 6) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else {
                                        tipoMezcla = "Red Bull";
                                        precioFinal = precioDouble + 1.00;

                                    }

                                    if (cantidad[0] != 0)
                                        controladorDB.addProductoCombinado(numerTable, cod_prod, nomProducto, precioFinal, cantidad[0], tipoMezcla);
                                }
                            }

                        });

                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "Brugal":
                        cod_prod = "BGL";
                        compuesto = listaProducto.get(cod_prod);
                        partes = compuesto.split(":");
                        precio = partes[1];
                        precioDouble = Double.parseDouble(precio);
                        nomProducto= partes[0];

                        dialog_img.setImageResource(R.drawable.brugal);
                        dialog_title.setText(nomProducto);
                        dialog_precio.setText("Precio: " + precio +"€");

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });


                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String tipoMezcla;
                                final double precioFinal;
                                if (mezcla.getSelectedItemPosition() == 0) {


                                } else {
                                    if (mezcla.getSelectedItemPosition() == 1) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 2) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 3) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 4) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 5) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 6) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else {
                                        tipoMezcla = "Red Bull";
                                        precioFinal = precioDouble + 1.00;

                                    }

                                    if (cantidad[0] != 0)
                                        controladorDB.addProductoCombinado(numerTable, cod_prod, nomProducto, precioFinal, cantidad[0], tipoMezcla);
                                }
                            }

                        });

                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "Cacique":
                        cod_prod = "CCQ";
                        compuesto = listaProducto.get(cod_prod);
                        partes = compuesto.split(":");
                        precio = partes[1];
                        precioDouble = Double.parseDouble(precio);
                        nomProducto= partes[0];

                        dialog_img.setImageResource(R.drawable.cacique);
                        dialog_title.setText(nomProducto);
                        dialog_precio.setText("Precio: " + precio +"€");

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });


                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String tipoMezcla;
                                final double precioFinal;
                                if (mezcla.getSelectedItemPosition() == 0) {


                                } else {
                                    if (mezcla.getSelectedItemPosition() == 1) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 2) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 3) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 4) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 5) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 6) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else {
                                        tipoMezcla = "Red Bull";
                                        precioFinal = precioDouble + 1.00;

                                    }

                                    if (cantidad[0] != 0)
                                        controladorDB.addProductoCombinado(numerTable, cod_prod, nomProducto, precioFinal, cantidad[0], tipoMezcla);
                                }
                            }

                        });

                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;
                    case "Havana":
                        cod_prod = "HVN";
                        compuesto = listaProducto.get(cod_prod);
                        partes = compuesto.split(":");
                        precio = partes[1];
                        precioDouble = Double.parseDouble(precio);
                        nomProducto= partes[0];

                        dialog_img.setImageResource(R.drawable.havana);
                        dialog_title.setText(nomProducto);
                        dialog_precio.setText("Precio: " + precio +"€");

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });


                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String tipoMezcla;
                                final double precioFinal;
                                if (mezcla.getSelectedItemPosition() == 0) {


                                } else {
                                    if (mezcla.getSelectedItemPosition() == 1) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 2) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 3) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 4) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 5) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 6) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else {
                                        tipoMezcla = "Red Bull";
                                        precioFinal = precioDouble + 1.00;

                                    }

                                    if (cantidad[0] != 0)
                                        controladorDB.addProductoCombinado(numerTable, cod_prod, nomProducto, precioFinal, cantidad[0], tipoMezcla);
                                }
                            }

                        });

                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;
                    case "Legendario":
                        cod_prod = "LGD";
                        compuesto = listaProducto.get(cod_prod);
                        partes = compuesto.split(":");
                        precio = partes[1];
                        precioDouble = Double.parseDouble(precio);
                        nomProducto= partes[0];

                        dialog_img.setImageResource(R.drawable.legendario);
                        dialog_title.setText(nomProducto);
                        dialog_precio.setText("Precio: " + precio +"€");

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });


                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String tipoMezcla;
                                final double precioFinal;
                                if (mezcla.getSelectedItemPosition() == 0) {


                                } else {
                                    if (mezcla.getSelectedItemPosition() == 1) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 2) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 3) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 4) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 5) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 6) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else {
                                        tipoMezcla = "Red Bull";
                                        precioFinal = precioDouble + 1.00;

                                    }

                                    if (cantidad[0] != 0)
                                        controladorDB.addProductoCombinado(numerTable, cod_prod, nomProducto, precioFinal, cantidad[0], tipoMezcla);
                                }
                            }

                        });

                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;
                    case "Matusalem":
                        cod_prod = "MTS";
                        compuesto = listaProducto.get(cod_prod);
                        partes = compuesto.split(":");
                        precio = partes[1];
                        precioDouble = Double.parseDouble(precio);
                        nomProducto= partes[0];

                        dialog_img.setImageResource(R.drawable.matusalem);
                        dialog_title.setText(nomProducto);
                        dialog_precio.setText("Precio: " + precio +"€");

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });


                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String tipoMezcla;
                                final double precioFinal;
                                if (mezcla.getSelectedItemPosition() == 0) {


                                } else {
                                    if (mezcla.getSelectedItemPosition() == 1) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 2) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 3) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 4) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 5) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 6) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else {
                                        tipoMezcla = "Red Bull";
                                        precioFinal = precioDouble + 1.00;

                                    }

                                    if (cantidad[0] != 0)
                                        controladorDB.addProductoCombinado(numerTable, cod_prod, nomProducto, precioFinal, cantidad[0], tipoMezcla);
                                }
                            }

                        });

                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

/////////////////              WISKY             ///////////////////////


                    case "Ballantines":
                        cod_prod = "BLL";
                        compuesto = listaProducto.get(cod_prod);
                        partes = compuesto.split(":");
                        precio = partes[1];
                        precioDouble = Double.parseDouble(precio);
                        nomProducto= partes[0];

                        dialog_img.setImageResource(R.drawable.ballantines);
                        dialog_title.setText(nomProducto);
                        dialog_precio.setText("Precio: " + precio +"€");

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });


                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String tipoMezcla;
                                final double precioFinal;
                                if (mezcla.getSelectedItemPosition() == 0) {


                                } else {
                                    if (mezcla.getSelectedItemPosition() == 1) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 2) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 3) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 4) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 5) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 6) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else {
                                        tipoMezcla = "Red Bull";
                                        precioFinal = precioDouble + 1.00;

                                    }

                                    if (cantidad[0] != 0)
                                        controladorDB.addProductoCombinado(numerTable, cod_prod, nomProducto, precioFinal, cantidad[0], tipoMezcla);
                                }
                            }

                        });

                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "Cutty Sark":
                        cod_prod = "CSK";
                        compuesto = listaProducto.get(cod_prod);
                        partes = compuesto.split(":");
                        precio = partes[1];
                        precioDouble = Double.parseDouble(precio);
                        nomProducto= partes[0];

                        dialog_img.setImageResource(R.drawable.cutty_sark);
                        dialog_title.setText(nomProducto);
                        dialog_precio.setText("Precio: " + precio +"€");

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });


                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String tipoMezcla;
                                final double precioFinal;
                                if (mezcla.getSelectedItemPosition() == 0) {


                                } else {
                                    if (mezcla.getSelectedItemPosition() == 1) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 2) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 3) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 4) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 5) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 6) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else {
                                        tipoMezcla = "Red Bull";
                                        precioFinal = precioDouble + 1.00;

                                    }

                                    if (cantidad[0] != 0)
                                        controladorDB.addProductoCombinado(numerTable, cod_prod, nomProducto, precioFinal, cantidad[0], tipoMezcla);
                                }
                            }

                        });

                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "J&B":
                        cod_prod = "J&B";
                        compuesto = listaProducto.get(cod_prod);
                        partes = compuesto.split(":");
                        precio = partes[1];
                        precioDouble = Double.parseDouble(precio);
                        nomProducto= partes[0];

                        dialog_img.setImageResource(R.drawable.jb);
                        dialog_title.setText(nomProducto);
                        dialog_precio.setText("Precio: " + precio +"€");

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });


                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String tipoMezcla;
                                final double precioFinal;
                                if (mezcla.getSelectedItemPosition() == 0) {


                                } else {
                                    if (mezcla.getSelectedItemPosition() == 1) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 2) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 3) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 4) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 5) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 6) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else {
                                        tipoMezcla = "Red Bull";
                                        precioFinal = precioDouble + 1.00;

                                    }

                                    if (cantidad[0] != 0)
                                        controladorDB.addProductoCombinado(numerTable, cod_prod, nomProducto, precioFinal, cantidad[0], tipoMezcla);
                                }
                            }

                        });

                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "Johnnie Walker":
                        cod_prod = "JHW";
                        compuesto = listaProducto.get(cod_prod);
                        partes = compuesto.split(":");
                        precio = partes[1];
                        precioDouble = Double.parseDouble(precio);
                        nomProducto= partes[0];

                        dialog_img.setImageResource(R.drawable.johnnie_walker);
                        dialog_title.setText(nomProducto);
                        dialog_precio.setText("Precio: " + precio +"€");

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });


                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String tipoMezcla;
                                final double precioFinal;
                                if (mezcla.getSelectedItemPosition() == 0) {


                                } else {
                                    if (mezcla.getSelectedItemPosition() == 1) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 2) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 3) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 4) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 5) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 6) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else {
                                        tipoMezcla = "Red Bull";
                                        precioFinal = precioDouble + 1.00;

                                    }

                                    if (cantidad[0] != 0)
                                        controladorDB.addProductoCombinado(numerTable, cod_prod, nomProducto, precioFinal, cantidad[0], tipoMezcla);
                                }
                            }

                        });

                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "Johnnie Walker Black Label":
                        cod_prod = "JWB";
                        compuesto = listaProducto.get(cod_prod);
                        partes = compuesto.split(":");
                        precio = partes[1];
                        precioDouble = Double.parseDouble(precio);
                        nomProducto= partes[0];

                        dialog_img.setImageResource(R.drawable.johnnie_walker_black);
                        dialog_title.setText(nomProducto);
                        dialog_precio.setText("Precio: " + precio +"€");

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });


                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String tipoMezcla;
                                final double precioFinal;
                                if (mezcla.getSelectedItemPosition() == 0) {


                                } else {
                                    if (mezcla.getSelectedItemPosition() == 1) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 2) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 3) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 4) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 5) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 6) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else {
                                        tipoMezcla = "Red Bull";
                                        precioFinal = precioDouble + 1.00;

                                    }

                                    if (cantidad[0] != 0)
                                        controladorDB.addProductoCombinado(numerTable, cod_prod, nomProducto, precioFinal, cantidad[0], tipoMezcla);
                                }
                            }

                        });

                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "Jack Daniels":
                        cod_prod = "JKD";
                        compuesto = listaProducto.get(cod_prod);
                        partes = compuesto.split(":");
                        precio = partes[1];
                        precioDouble = Double.parseDouble(precio);
                        nomProducto= partes[0];

                        dialog_img.setImageResource(R.drawable.jack_daniels);
                        dialog_title.setText(nomProducto);
                        dialog_precio.setText("Precio: " + precio +"€");

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });


                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String tipoMezcla;
                                final double precioFinal;
                                if (mezcla.getSelectedItemPosition() == 0) {


                                } else {
                                    if (mezcla.getSelectedItemPosition() == 1) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 2) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 3) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 4) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 5) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 6) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else {
                                        tipoMezcla = "Red Bull";
                                        precioFinal = precioDouble + 1.00;

                                    }

                                    if (cantidad[0] != 0)
                                        controladorDB.addProductoCombinado(numerTable, cod_prod, nomProducto, precioFinal, cantidad[0], tipoMezcla);
                                }
                            }

                        });

                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "White Label":
                        cod_prod = "WHL";
                        compuesto = listaProducto.get(cod_prod);
                        partes = compuesto.split(":");
                        precio = partes[1];
                        precioDouble = Double.parseDouble(precio);
                        nomProducto= partes[0];

                        dialog_img.setImageResource(R.drawable.white_label);
                        dialog_title.setText(nomProducto);
                        dialog_precio.setText("Precio: " + precio +"€");

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });


                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String tipoMezcla;
                                final double precioFinal;
                                if (mezcla.getSelectedItemPosition() == 0) {


                                } else {
                                    if (mezcla.getSelectedItemPosition() == 1) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 2) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 3) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 4) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 5) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 6) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else {
                                        tipoMezcla = "Red Bull";
                                        precioFinal = precioDouble + 1.00;

                                    }

                                    if (cantidad[0] != 0)
                                        controladorDB.addProductoCombinado(numerTable, cod_prod, nomProducto, precioFinal, cantidad[0], tipoMezcla);
                                }
                            }

                        });

                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

///////////////             GIN        ///////////////////////////////

                    case "Beefeater":
                        cod_prod = "BEE";
                        compuesto = listaProducto.get(cod_prod);
                        partes = compuesto.split(":");
                        precio = partes[1];
                        precioDouble = Double.parseDouble(precio);
                        nomProducto= partes[0];

                        dialog_img.setImageResource(R.drawable.beefeater);
                        dialog_title.setText(nomProducto);
                        dialog_precio.setText("Precio: " + precio +"€");

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });


                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String tipoMezcla;
                                final double precioFinal;
                                if (mezcla.getSelectedItemPosition() == 0) {


                                } else {
                                    if (mezcla.getSelectedItemPosition() == 1) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 2) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 3) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 4) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 5) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 6) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else {
                                        tipoMezcla = "Red Bull";
                                        precioFinal = precioDouble + 1.00;

                                    }

                                    if (cantidad[0] != 0)
                                        controladorDB.addProductoCombinado(numerTable, cod_prod, nomProducto, precioFinal, cantidad[0], tipoMezcla);
                                }
                            }

                        });

                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "Larios 12":
                        cod_prod = "L12";
                        compuesto = listaProducto.get(cod_prod);
                        partes = compuesto.split(":");
                        precio = partes[1];
                        precioDouble = Double.parseDouble(precio);
                        nomProducto= partes[0];

                        dialog_img.setImageResource(R.drawable.larios_12);
                        dialog_title.setText(nomProducto);
                        dialog_precio.setText("Precio: " + precio +"€");

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });


                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String tipoMezcla;
                                final double precioFinal;
                                if (mezcla.getSelectedItemPosition() == 0) {


                                } else {
                                    if (mezcla.getSelectedItemPosition() == 1) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 2) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 3) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 4) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 5) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 6) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else {
                                        tipoMezcla = "Red Bull";
                                        precioFinal = precioDouble + 1.00;

                                    }

                                    if (cantidad[0] != 0)
                                        controladorDB.addProductoCombinado(numerTable, cod_prod, nomProducto, precioFinal, cantidad[0], tipoMezcla);
                                }
                            }

                        });

                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "Larios":
                        cod_prod = "LRS";
                        compuesto = listaProducto.get(cod_prod);
                        partes = compuesto.split(":");
                        precio = partes[1];
                        precioDouble = Double.parseDouble(precio);
                        nomProducto= partes[0];

                        dialog_img.setImageResource(R.drawable.larios);
                        dialog_title.setText(nomProducto);
                        dialog_precio.setText("Precio: " + precio +"€");

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });


                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String tipoMezcla;
                                final double precioFinal;
                                if (mezcla.getSelectedItemPosition() == 0) {


                                } else {
                                    if (mezcla.getSelectedItemPosition() == 1) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 2) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 3) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 4) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 5) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 6) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else {
                                        tipoMezcla = "Red Bull";
                                        precioFinal = precioDouble + 1.00;

                                    }

                                    if (cantidad[0] != 0)
                                        controladorDB.addProductoCombinado(numerTable, cod_prod, nomProducto, precioFinal, cantidad[0], tipoMezcla);
                                }
                            }

                        });

                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

 /////////////////              VODKA   //////////////////////

                    case "Absolut":
                        cod_prod = "ABT";
                        compuesto = listaProducto.get(cod_prod);
                        partes = compuesto.split(":");
                        precio = partes[1];
                        precioDouble = Double.parseDouble(precio);
                        nomProducto= partes[0];

                        dialog_img.setImageResource(R.drawable.absoluts);
                        dialog_title.setText(nomProducto);
                        dialog_precio.setText("Precio: " + precio +"€");

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });


                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String tipoMezcla;
                                final double precioFinal;
                                if (mezcla.getSelectedItemPosition() == 0) {


                                } else {
                                    if (mezcla.getSelectedItemPosition() == 1) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 2) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 3) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 4) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 5) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 6) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else {
                                        tipoMezcla = "Red Bull";
                                        precioFinal = precioDouble + 1.00;

                                    }

                                    if (cantidad[0] != 0)
                                        controladorDB.addProductoCombinado(numerTable, cod_prod, nomProducto, precioFinal, cantidad[0], tipoMezcla);
                                }
                            }

                        });

                        dialog.setNegativeButton("Cancelar", null);
                        dialog.create();
                        dialog.show();
                        break;

                    case "Eristoff":
                        cod_prod = "EFF";
                        compuesto = listaProducto.get(cod_prod);
                        partes = compuesto.split(":");
                        precio = partes[1];
                        precioDouble = Double.parseDouble(precio);
                        nomProducto= partes[0];

                        dialog_img.setImageResource(R.drawable.absoluts);
                        dialog_title.setText(nomProducto);
                        dialog_precio.setText("Precio: " + precio +"€");

                        dialog.setView(vista);
                        numberPicker.setMaxValue(20);
                        numberPicker.setMinValue(0);


                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                cantidad[0] = numberPicker.getValue();
                            }
                        });


                        dialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String tipoMezcla;
                                final double precioFinal;
                                if (mezcla.getSelectedItemPosition() == 0) {


                                } else {
                                    if (mezcla.getSelectedItemPosition() == 1) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 2) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 3) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 4) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 5) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else if (mezcla.getSelectedItemPosition() == 6) {
                                        tipoMezcla = mezcla.getSelectedItem().toString();
                                        precioFinal = precioDouble;

                                    } else {
                                        tipoMezcla = "Red Bull";
                                        precioFinal = precioDouble + 1.00;

                                    }

                                    if (cantidad[0] != 0)
                                        controladorDB.addProductoCombinado(numerTable, cod_prod, nomProducto, precioFinal, cantidad[0], tipoMezcla);
                                }
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
    private void createCollection(String[] listaRon, String[] listaWisky, String[] listaGin, String[] listaVodka) {
        // preparing laptops collection(child)

        String[] productoRon = listaRon;
        String[] productoWisky = listaWisky;
        String[] productoGin = listaGin;
        String[] productoVodka = listaVodka;
        combinadosCollection = new LinkedHashMap<String, List<String>>();

        for (String combinados : groupList) {
            if (combinados.equals("Ron"))
                loadChild(productoRon);
            else if(combinados.equals("Wisky"))
                loadChild(productoWisky);
            else if(combinados.equals("Gin"))
                loadChild(productoGin);
            else
                loadChild(productoVodka);

            combinadosCollection.put(combinados, childList);
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
                Toast.makeText(Combinados.this, "Please wait...", Toast.LENGTH_SHORT)
                        .show();

            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    //Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/easy_order", "ruben_admin", "anmarulo");
                    Connection con = DriverManager.getConnection("jdbc:mysql://188.127.174.120:3306/productos", "ruben", "ruben");
                    listaProducto = new HashMap<>();
                    visualizarProductosRon = new ArrayList<>();
                    visualizarProductosVodka = new ArrayList<>();
                    visualizarProductosGin = new ArrayList<>();
                    visualizarProductosWisky = new ArrayList<>();
                    visualizarRefrescos = new ArrayList<>();

                    String result = numerTable;
                    PreparedStatement st = con.prepareStatement("SELECT * FROM combinados WHERE tipo=?");
                    tipoRonSting = "ron";
                    st.setString(1, tipoRonSting);
                    ResultSet rs = st.executeQuery();

                    while (rs.next()) {
                        listaProducto.put(rs.getString(1), rs.getString(2) + ":" + rs.getDouble(3));
                        visualizarProductosRon.add(rs.getString(2));
                    }



                    PreparedStatement stV = con.prepareStatement("SELECT * FROM combinados WHERE tipo=?");
                    tipoVodkaString = "vodka";
                    stV.setString(1, tipoVodkaString);
                    rs = stV.executeQuery();

                        while (rs.next()) {
                            listaProducto.put(rs.getString(1), rs.getString(2) + ":" + rs.getDouble(3));
                            visualizarProductosVodka.add(rs.getString(2));
                        }


                    PreparedStatement stG = con.prepareStatement("SELECT * FROM combinados WHERE tipo=?");
                    tipoGinString = "gin";
                    stG.setString(1, tipoGinString);
                    rs = stG.executeQuery();

                    while (rs.next()) {
                        listaProducto.put(rs.getString(1), rs.getString(2) + ":" + rs.getDouble(3));
                        visualizarProductosGin.add(rs.getString(2));
                    }


                    PreparedStatement stW = con.prepareStatement("SELECT * FROM combinados WHERE tipo=?");
                    tipoWiskyString = "wisky";
                    stW.setString(1, tipoWiskyString);
                    rs = stW.executeQuery();

                    while (rs.next()) {
                        listaProducto.put(rs.getString(1), rs.getString(2) + ":" + rs.getDouble(3));
                        visualizarProductosWisky.add(rs.getString(2));
                    }
                    PreparedStatement stR = con.prepareStatement("SELECT * FROM refrescos");
                    rs = stR.executeQuery();

                    while (rs.next()) {
                        //listaProducto.put(rs.getString(1), rs.getString(2) + ":" + rs.getDouble(3));
                        visualizarRefrescos.add(rs.getString(2));
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
                int numero = visualizarProductosRon.size();
                String [] ron = new String[numero];
                for (int i=0; i<visualizarProductosRon.size(); i++) {
                    ron[i] = visualizarProductosRon.get(i);


                }

                int numeroW = visualizarProductosWisky.size();
                String [] wisky = new String[numeroW];
                for (int i=0; i<visualizarProductosWisky.size(); i++) {
                    wisky[i] = visualizarProductosWisky.get(i);
                }

                int numeroG = visualizarProductosGin.size();
                String [] gin = new String[numeroG];
                for (int i=0; i<visualizarProductosGin.size(); i++) {
                    gin[i] = visualizarProductosGin.get(i);
                }

                int numeroV = visualizarProductosVodka.size();
                String [] vodka = new String[numeroV];
                for (int i=0; i<visualizarProductosVodka.size(); i++) {
                    vodka[i] = visualizarProductosVodka.get(i);
                }



                createGroupList();
                createCollection(ron, wisky, gin, vodka);
                creando();


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
