package com.example.easyorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Carta extends AppCompatActivity {
    TextView mesa;
    static String numerTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta);
        getSupportActionBar().hide();

        mesa = findViewById(R.id.mesa);

        Bundle extra = getIntent().getExtras();
        numerTable = extra.getString("mesa");
        mesa.setText("Pedido: " + numerTable);
    }

    public void cartaResfrescos(View view){
        Intent refresco = new Intent(this, Refrescos.class);
        refresco.putExtra("numeroMesa", numerTable);
        startActivity(refresco);
    }
    public void cartaBatidos(View view){
        Intent batidos = new Intent(this, Batidos.class);
        batidos.putExtra("numeroMesa", numerTable);
        startActivity(batidos);
    }
    public void cartaCervezas(View view){
        Intent cervezas = new Intent(this, Cervezas.class);
        cervezas.putExtra("numeroMesa", numerTable);
        startActivity(cervezas);
    }
    public void cartaCombinados(View view){
        Intent combinados= new Intent(this, Combinados.class);
        combinados.putExtra("numeroMesa", numerTable);
        startActivity(combinados);
    }
    public void cartaShishas(View view){
        Intent shishas = new Intent(this, Shishas.class);
        shishas.putExtra("numeroMesa", numerTable);
        startActivity(shishas);
    }
    public void verCarrito(View view){
        Intent carrito = new Intent(this, Carrito.class);
        carrito.putExtra("mesaNum", numerTable);
        startActivity(carrito);
    }
}
