package com.example.appf1proyecto;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PantallaFinal extends AppCompatActivity {
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantallafinal);

        mediaPlayer = MediaPlayer.create(this, R.raw.f1_theme);
        mediaPlayer.setLooping(true);  // La música se repite
        mediaPlayer.start();  // Inicia la música

        // Referencias a los elementos de la interfaz
        TextView nombreTextView = findViewById(R.id.nombreTextView);
        TextView edadTextView = findViewById(R.id.edadTextView);
        TextView paisTextView = findViewById(R.id.paisTextView);
        TextView equipoFavoritoTextView = findViewById(R.id.equipoFavoritoTextView);
        TextView pistaFavoritaTextView = findViewById(R.id.pistaFavoritaTextView);
        TextView fechaFavoritaTextView = findViewById(R.id.fechaFavoritaTextView);
        TextView temporadaFavoritaTextView = findViewById(R.id.temporadaFavoritaTextView);
        TextView pilotoFavoritoTextView = findViewById(R.id.pilotoFavoritoTextView);
        TextView climaPreferidoTextView = findViewById(R.id.climaPreferidoTextView);
        TextView motoristaFavoritoTextView = findViewById(R.id.motoristaFavoritoTextView);
        Button cerrarButton = findViewById(R.id.cerrarAplicacion);

        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String edad = intent.getStringExtra("edad");
        String pais = intent.getStringExtra("pais");
        String equipoFavorito = intent.getStringExtra("equipoFavorito");
        String pistaFavorita = intent.getStringExtra("pistaFavorita");
        String fechaFavorita = intent.getStringExtra("fechaFavorita");
        String temporadaFavorita = intent.getStringExtra("temporadaFavorita");
        String pilotoFavorito = intent.getStringExtra("pilotoFavorito");
        String climaPreferido = intent.getStringExtra("climaPreferido");
        String motoristaFavorito = intent.getStringExtra("motoristaFavorito");

        nombreTextView.setText("Nombre: " + nombre);
        edadTextView.setText("Edad: " + edad);
        paisTextView.setText("País: " + pais);
        equipoFavoritoTextView.setText("Equipo favorito: " + equipoFavorito);
        pistaFavoritaTextView.setText("Circuito favorito: " + pistaFavorita);
        fechaFavoritaTextView.setText("Fecha favorita: " + fechaFavorita);
        temporadaFavoritaTextView.setText("Temporada favorita: " + temporadaFavorita);
        pilotoFavoritoTextView.setText("Piloto favorito: " + pilotoFavorito);
        climaPreferidoTextView.setText("Clima preferido: " + climaPreferido);
        motoristaFavoritoTextView.setText("Motorista favorito: " + motoristaFavorito);

        cerrarButton.setOnClickListener(v -> {
        finishAffinity();
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
