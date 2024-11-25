package com.example.appf1proyecto;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Pantalla3 extends AppCompatActivity {

    private Spinner pistaFavoritaSpinner;
    private Button fechaButton;
    private TextView fechaSeleccionadaTextView;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla3);

        // Referencias a los elementos de la interfaz
        pistaFavoritaSpinner = findViewById(R.id.pistaFavoritaSpinner);
        fechaButton = findViewById(R.id.fechaButton);
        fechaSeleccionadaTextView = findViewById(R.id.fechaSeleccionadaTextView);
        Button continuarButton = findViewById(R.id.continuarButtonPantalla3);

        mediaPlayer = MediaPlayer.create(this, R.raw.f1_theme);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        // Lista de circuitos
        String[] pistas = {
                "Silverstone (Reino Unido)", "Mónaco (Mónaco)", "Spa-Francorchamps (Bélgica)",
                "Monza (Italia)", "Circuito de Barcelona-Catalunya (España)", "Zandvoort (Países Bajos)",
                "Circuito Gilles Villeneuve (Canadá)", "Interlagos (Brasil)", "Suzuka (Japón)",
                "Circuito de las Américas (Estados Unidos)", "Yas Marina (Abu Dabi)", "Red Bull Ring (Austria)",
                "Hockenheimring (Alemania)", "Circuito de Bahréin (Bahréin)", "Circuito de Shanghái (China)",
                "Nürburgring (Alemania)", "Circuito de Sochi (Rusia)", "Jeddah Street Circuit (Arabia Saudita)",
                "Albert Park (Australia)", "Marina Bay Street Circuit (Singapur)", "Imola (Italia)",
                "Circuito Hermanos Rodríguez (México)", "Circuito de Paul Ricard (Francia)",
                "Indianapolis Motor Speedway (Estados Unidos)", "Circuito de Sepang (Malasia)",
                "Circuito de Estambul (Turquía)", "Circuito de Las Vegas (Estados Unidos)",
                "Circuito callejero de Bakú (Azerbaiyán)"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_personalizado, pistas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pistaFavoritaSpinner.setAdapter(adapter);

        // Recibir datos de Pantalla1 y Pantalla2
        Intent intentGet = getIntent();
        String nombre = intentGet.getStringExtra("nombre");
        String edad = intentGet.getStringExtra("edad");
        String pais = intentGet.getStringExtra("pais");
        String equipoFavorito = intentGet.getStringExtra("equipoFavorito");

        // Configurar el botón para abrir un calendario
        fechaButton.setOnClickListener(v -> {
            DatePickerDialog calendario = new DatePickerDialog(this, R.style.calendarioPersonalizado,
                    (DatePicker view, int dia, int mes, int anio) -> {
                        // Mostrar la fecha seleccionada en el TextView
                        String fechaSeleccionada = dia + "/" + (mes + 1) + "/" + anio;
                        fechaSeleccionadaTextView.setText("Fecha seleccionada: " + fechaSeleccionada);
                    }, 2024, 0, 1);  // Fecha inicial predeterminada
            calendario.show();
        });

        // Configurar el botón de Continuar para pasar datos a la siguiente pantalla
        continuarButton.setOnClickListener(v -> {
            String pistaFavorita = pistaFavoritaSpinner.getSelectedItem().toString();
            String fechaFavorita = fechaSeleccionadaTextView.getText().toString().replace("Fecha seleccionada: ", "");

            if (fechaFavorita.isEmpty()) {
                Toast.makeText(Pantalla3.this, "Por favor, selecciona una fecha", Toast.LENGTH_SHORT).show();
            } else {
                // Pasar los datos a la siguiente pantalla (Pantalla4)
                Intent intent = new Intent(Pantalla3.this, Pantalla4.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("edad", edad);
                intent.putExtra("pais", pais);
                intent.putExtra("equipoFavorito", equipoFavorito);
                intent.putExtra("pistaFavorita", pistaFavorita);
                intent.putExtra("fechaFavorita", fechaFavorita);
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
