package com.example.tp4_productos;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.tp4_productos.databinding.ActivityMainBinding;
import com.example.tp4_productos.modelo.Producto;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Producto> listaProductos = new ArrayList<>();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configurar Listeners
        binding.btnCargar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CargarActivity.class);
            startActivity(intent);
        });

        binding.btnListar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListarActivity.class);
            startActivity(intent);
        });

        binding.btnSalir.setOnClickListener(v -> mostrarDialogoSalir());
    }

    private void mostrarDialogoSalir() {
        new AlertDialog.Builder(this)
                .setTitle("Salir")
                .setMessage("¿Estás seguro que deseas salir de la aplicación?")
                .setPositiveButton("Sí", (dialog, which) -> finishAffinity())
                .setNegativeButton("No", null)
                .show();
    }
}