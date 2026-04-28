package com.example.tp4_productos.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp4_productos.MainActivity;
import com.example.tp4_productos.modelo.Producto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListarViewModel extends ViewModel {

    private final MutableLiveData<List<Producto>> productosLiveData = new MutableLiveData<>();

    public LiveData<List<Producto>> getProductosLiveData() {
        return productosLiveData;
    }

    public void cargarProductosOrdenados() {
        // Hacemos una copia para no modificar la lista original si no queremos, 
        // pero la consigna dice "ordenados alfabéticamente por descripción".
        List<Producto> listaOrdenada = new ArrayList<>(MainActivity.listaProductos);
        
        Collections.sort(listaOrdenada, new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {
                return p1.getDescripcion().compareToIgnoreCase(p2.getDescripcion());
            }
        });
        
        productosLiveData.setValue(listaOrdenada);
    }
}
