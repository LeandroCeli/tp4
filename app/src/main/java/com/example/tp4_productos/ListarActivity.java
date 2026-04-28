package com.example.tp4_productos;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tp4_productos.adapter.ProductoAdapter;
import com.example.tp4_productos.databinding.ActivityListarBinding;
import com.example.tp4_productos.viewmodel.ListarViewModel;

public class ListarActivity extends AppCompatActivity {

    private ActivityListarBinding binding;
    private ListarViewModel viewModel;
    private ProductoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar RecyclerView
        adapter = new ProductoAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        // Instanciar el ViewModel
        viewModel = new ViewModelProvider(this).get(ListarViewModel.class);

        // Observar la lista de productos
        viewModel.getProductosLiveData().observe(this, productos -> {
            adapter.setProductos(productos);
        });

        // Solicitar que se carguen los productos
        viewModel.cargarProductosOrdenados();
    }
}
