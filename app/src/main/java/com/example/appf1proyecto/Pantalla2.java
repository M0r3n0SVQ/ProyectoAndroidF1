package com.example.appf1proyecto;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Pantalla2 extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Spinner equipoSpinner;
    private EditText paisEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla2);

        mediaPlayer = MediaPlayer.create(this, R.raw.f1_theme);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        // Referencias a los elementos
        equipoSpinner = findViewById(R.id.equipoSpinner);
        paisEditText = findViewById(R.id.paisEditText);

        Button continuarButton = findViewById(R.id.continuarButtonPantalla2);

        // Recibir los datos de la pantalla anterior
        Intent intentGet = getIntent();
        String nombre = intentGet.getStringExtra("nombre");
        String edad = intentGet.getStringExtra("edad");


        // Lista de equipos para el Spinner
        String[] equipos = {
                "Mercedes", "Red Bull", "Ferrari", "McLaren",
                "Aston Martin", "Alpine", "AlphaTauri",
                "Williams", "Haas", "Alfa Romeo"
        };

        // Configurar el Spinner con un ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_personalizado, equipos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        equipoSpinner.setAdapter(adapter);


        continuarButton.setOnClickListener(v -> {
            String pais = paisEditText.getText().toString().trim();
            String equipoFavorito = equipoSpinner.getSelectedItem().toString();

            if (pais.isEmpty()) {
                Toast.makeText(Pantalla2.this, "Por favor, rellene el campo de pais", Toast.LENGTH_SHORT).show();
            } else {
                // Pasar los datos a la siguiente pantalla (Pantalla 3)
                Intent intent = new Intent(Pantalla2.this, Pantalla3.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("edad", edad);
                intent.putExtra("pais", pais);
                intent.putExtra("equipoFavorito", equipoFavorito);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Pausar el MediaPlayer cuando no está en primer plano
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
