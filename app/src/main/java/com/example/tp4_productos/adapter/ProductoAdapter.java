package com.example.tp4_productos.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp4_productos.databinding.ItemProductoBinding;
import com.example.tp4_productos.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private List<Producto> listaProductos = new ArrayList<>();

    public void setProductos(List<Producto> productos) {
        this.listaProductos = productos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductoBinding binding = ItemProductoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        holder.bind(listaProductos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    static class ProductoViewHolder extends RecyclerView.ViewHolder {
        private final ItemProductoBinding binding;

        public ProductoViewHolder(ItemProductoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Producto producto) {
            binding.tvDescripcion.setText(producto.getDescripcion());
            binding.tvCodigo.setText("Cód: " + producto.getCodigo());
            binding.tvPrecio.setText("$" + producto.getPrecio());
        }
    }
}
