package com.example.easyorder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;

public class Carrito extends AppCompatActivity {

    ControladorDB controladorDB;
    ListView lstData;
    private ArrayAdapter<String> mAdapter, adapterCantidad;
    FloatingActionButton fab;
    String mesa;
    static double total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        controladorDB = new ControladorDB(this);
        lstData = (ListView) findViewById(R.id.lstData);

        fab= findViewById(R.id.validar_carro);
        getSupportActionBar().setTitle("Carro");

        Bundle extra = getIntent().getExtras();
        mesa = extra.getString("mesaNum");
        final DecimalFormat df =new DecimalFormat("0.00");
        actualizarUI();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double cantidad = controladorDB.enviarPedido();
                total = total + cantidad;


                Toast.makeText(Carrito.this, "TOTAL Pedido : " + df.format(cantidad) + "€", Toast.LENGTH_LONG)
                        .show();
                Toast.makeText(Carrito.this, "TOTAL MESA : " + df.format(total) + "€", Toast.LENGTH_LONG)
                        .show();
                Intent carta= new Intent(getApplicationContext(), Carta.class);
                carta.putExtra("mesa", mesa);
                startActivity(carta);
            }
        });
    }



    private void actualizarUI() {


        if (controladorDB.numeroReg() == 0) {
            lstData.setAdapter(null);
        } else {
            mAdapter = new ArrayAdapter<>(this, R.layout.item_carro, R.id.linea_producto_carro , controladorDB.obtenerProductos());
            lstData.setAdapter(mAdapter);
        }
    }
    public void borrarProducto(View view) {

        View parent = (View) view.getParent();
        final int[] cantidad = new int[1];
        final TextView linea = findViewById(R.id.linea_producto_carro);

        final AlertDialog.Builder dialog = new AlertDialog.Builder(Carrito.this);
        View vista = getLayoutInflater().inflate(R.layout.dialog_carro, null);
        TextView dialog_title =  vista.findViewById(R.id.titulo);

       final NumberPicker numberPicker= vista.findViewById(R.id.cantidad);


        TextView tareaTextWiev = (TextView) parent.findViewById(R.id.linea_producto_carro);



        String lineaProducto= tareaTextWiev.getText().toString();
        String partes[]= lineaProducto.split("\n");
        final String nombrePro= partes[0];




        dialog_title.setText(nombrePro);
        dialog.setView(vista);
        numberPicker.setMaxValue(20);
        numberPicker.setMinValue(0);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                cantidad[0] = numberPicker.getValue();
            }
        });

        dialog.setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(cantidad[0]==0){
                            if(nombrePro.contains("/")){
                                String[] partesCombinado= nombrePro.split("/");
                                String bebida= partesCombinado[0];
                                String mezcla = partesCombinado[1];
                                controladorDB.borrarProductoCombinado(bebida,mezcla);
                                actualizarUI();
                            }else{
                            controladorDB.borrarProducto(nombrePro);
                            actualizarUI();}
                        }else{
                            if(nombrePro.contains("/")){
                                String[] partesCombinado= nombrePro.split("/");
                                String bebida= partesCombinado[0];
                                String mezcla = partesCombinado[1];
                                int numeroBebida= bebida.length();
                                int numeroMezcla= mezcla.length();
                                String lengBebida= numeroBebida+"";
                                String lengMezcla= numeroMezcla+"";
                                Log.i ("alcohol", bebida);
                                Log.i ("mezcla", mezcla);
                                Log.i("lengB", lengBebida);
                                Log.i("lengM", lengMezcla);
                                controladorDB.modificarCantidadCombinado(bebida,mezcla, cantidad[0]);
                                actualizarUI();


                            }else {
                            controladorDB.modificarCantidad(nombrePro,cantidad[0]);
                            actualizarUI();}
                        }

            }

        });
        dialog.setNegativeButton("Cancelar", null);
        dialog.create();
        dialog.show();

    }

}
