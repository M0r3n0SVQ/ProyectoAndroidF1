package com.example.appf1proyecto;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Pantalla5 extends AppCompatActivity {

    private EditText climaPreferido;
    private Spinner motoristaFavorito;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla5);

        climaPreferido = findViewById(R.id.climaPreferidoEditText);
        motoristaFavorito = findViewById(R.id.motoristaFavorito);
        Button continuarButton = findViewById(R.id.continuarButtonPantalla5);

        mediaPlayer = MediaPlayer.create(this, R.raw.f1_theme);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        // Lista de motoristas
        String[] motoristasF1 = {
                "Alfa Romeo", "Aston Martin", "BMW", "BRM", "Bugatti", "Climax",
                "Ferrari", "Ford", "Gordini", "Hart", "Honda", "Illmor", "Jaguar",
                "Judd", "Maserati", "Matra", "Mercedes", "Mecachrome", "Mugen-Honda",
                "Osella", "Peugeot", "Playlife", "Porsche", "Renault", "Repco", "Supertec",
                "TAG", "Toyota", "Weslake", "Yamaha", "Zakspeed"
        };

        // Configurar el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_personalizado, motoristasF1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        motoristaFavorito.setAdapter(adapter);

        // Recibir los datos de las pantallas anteriores
        Intent intentGet = getIntent();
        String nombre = intentGet.getStringExtra("nombre");
        String edad = intentGet.getStringExtra("edad");
        String pais = intentGet.getStringExtra("pais");
        String equipoFavorito = intentGet.getStringExtra("equipoFavorito");
        String pistaFavorita = intentGet.getStringExtra("pistaFavorita");
        String fechaFavorita = intentGet.getStringExtra("fechaFavorita");
        String temporadaFavorita = intentGet.getStringExtra("temporadaFavorita");
        String pilotoFavorito = intentGet.getStringExtra("pilotoFavorito");


        continuarButton.setOnClickListener(v -> {
            String clima = climaPreferido.getText().toString().trim();
            String motorista = motoristaFavorito.getSelectedItem().toString(); // Obtener el motorista seleccionado

            if (clima.isEmpty() || motorista.isEmpty()) {
                Toast.makeText(Pantalla5.this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                // Pasar los datos a la pantalla final
                Intent intent = new Intent(Pantalla5.this, PantallaFinal.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("edad", edad);
                intent.putExtra("pais", pais);
                intent.putExtra("equipoFavorito", equipoFavorito);
                intent.putExtra("pistaFavorita", pistaFavorita);
                intent.putExtra("fechaFavorita", fechaFavorita);
                intent.putExtra("temporadaFavorita", temporadaFavorita);
                intent.putExtra("pilotoFavorito", pilotoFavorito);
                intent.putExtra("climaPreferido", clima);
                intent.putExtra("motoristaFavorito", motorista);
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