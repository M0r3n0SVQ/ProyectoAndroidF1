package com.example.appf1proyecto;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Pantalla4 extends AppCompatActivity {

    private EditText temporadaEditText;
    private EditText pilotoEditText;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla4);

        mediaPlayer = MediaPlayer.create(this, R.raw.f1_theme);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        // Inicializar los elementos de la interfaz
        temporadaEditText = findViewById(R.id.temporadaEditText);
        pilotoEditText = findViewById(R.id.pilotoEditText);
        Button continuarButton = findViewById(R.id.continuarButtonPantalla4);

        // Recibir los datos previos desde Pantalla3
        Intent intentGet = getIntent();
        String nombre = intentGet.getStringExtra("nombre");
        String edad = intentGet.getStringExtra("edad");
        String equipoFavorito = intentGet.getStringExtra("equipoFavorito");
        String pais = intentGet.getStringExtra("pais");
        String pistaFavorita = intentGet.getStringExtra("pistaFavorita");
        String fechaFavorita = intentGet.getStringExtra("fechaFavorita");

        // Configuración del botón para continuar a la siguiente pantalla
        continuarButton.setOnClickListener(v -> {
            String temporadaFavorita = temporadaEditText.getText().toString().trim();
            String pilotoFavorita = pilotoEditText.getText().toString().trim();

            if (temporadaFavorita.isEmpty() || pilotoFavorita.isEmpty()) {
                Toast.makeText(Pantalla4.this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
            } else {

                Intent intent = new Intent(Pantalla4.this, Pantalla5.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("edad", edad);
                intent.putExtra("equipoFavorito", equipoFavorito);
                intent.putExtra("pais", pais);
                intent.putExtra("pistaFavorita", pistaFavorita);
                intent.putExtra("fechaFavorita", fechaFavorita);
                intent.putExtra("temporadaFavorita", temporadaFavorita);
                intent.putExtra("pilotoFavorito", pilotoFavorita);
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
