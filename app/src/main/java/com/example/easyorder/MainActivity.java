package com.example.easyorder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        scaner();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null)
            if (result.getContents() != null){
                Intent goCarta= new Intent(this, Carta.class);
                goCarta.putExtra("mesa", result.getContents());
                startActivity(goCarta);
                finish();
            }else{
                Toast.makeText(MainActivity.this, "Error Lectura Codigo", Toast.LENGTH_LONG).show();
            }
    }


    public void scaner(){
        IntentIntegrator lector = new IntentIntegrator(this);
        lector.setPrompt("Leea Codigo Bidi Del Establecimiento");
        lector.setOrientationLocked(false);
        lector.initiateScan();


    }
}