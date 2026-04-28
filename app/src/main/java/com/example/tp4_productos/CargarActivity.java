package com.example.tp4_productos;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp4_productos.databinding.ActivityCargarBinding;
import com.example.tp4_productos.viewmodel.CargarViewModel;

public class CargarActivity extends AppCompatActivity {

    private ActivityCargarBinding binding;
    private CargarViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCargarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Instanciar el ViewModel
        viewModel = new ViewModelProvider(this).get(CargarViewModel.class);

        // Observar eventos del ViewModel
        viewModel.getMensajeError().observe(this, mensaje -> {
            if (mensaje != null) {
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.getProductoGuardado().observe(this, guardado -> {
            if (guardado != null && guardado) {
                Toast.makeText(this, "Producto guardado con éxito", Toast.LENGTH_SHORT).show();
                finish(); // Cierra la activity y vuelve al menú
            }
        });

        // Configurar botón de guardar
        binding.btnGuardar.setOnClickListener(v -> {
            String codigo = binding.etCodigo.getText().toString();
            String descripcion = binding.etDescripcion.getText().toString();
            String precio = binding.etPrecio.getText().toString();

            viewModel.guardarProducto(codigo, descripcion, precio);
        });
    }
}
