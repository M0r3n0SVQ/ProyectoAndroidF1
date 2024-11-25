package com.example.appf1proyecto;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Pantalla1 extends AppCompatActivity {

    private EditText nombreEditText;
    private EditText edadEditText;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla1);  // Cargar el XML

        // Inicializar los EditText
        nombreEditText = findViewById(R.id.nombreEditText);
        edadEditText = findViewById(R.id.edadEditText);

        // Crear un nuevo MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.f1_theme);
        mediaPlayer.setLooping(true);  // La música se repite
        mediaPlayer.start();  // Inicia la música

        // Configurar el botón de continuar
        Button continuarButton = findViewById(R.id.continuarButtonPantalla1);
        continuarButton.setOnClickListener(v -> {
            // Obtener los datos
            String nombre = nombreEditText.getText().toString().trim();
            String edad = edadEditText.getText().toString().trim();

            if (nombre.isEmpty() || edad.isEmpty()) {
                Toast.makeText(Pantalla1.this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                // Crear un Intent para pasar los datos a la siguiente pantalla (Pantalla2)
                Intent intent = new Intent(Pantalla1.this, Pantalla2.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("edad", edad);

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

