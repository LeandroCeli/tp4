package com.example.tp4_productos.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp4_productos.MainActivity;
import com.example.tp4_productos.modelo.Producto;

public class CargarViewModel extends ViewModel {

    private final MutableLiveData<String> mensajeError = new MutableLiveData<>();
    public LiveData<String> getMensajeError() {
        return mensajeError;
    }

    private final MutableLiveData<Boolean> productoGuardado = new MutableLiveData<>();
    public LiveData<Boolean> getProductoGuardado() {
        return productoGuardado;
    }

    public void guardarProducto(String codigo, String descripcion, String precioStr) {
        if (codigo.trim().isEmpty() || descripcion.trim().isEmpty() || precioStr.trim().isEmpty()) {
            mensajeError.setValue("Todos los campos son obligatorios.");
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioStr);
            if (precio <= 0) {
                mensajeError.setValue("El precio debe ser mayor a 0.");
                return;
            }
        } catch (NumberFormatException e) {
            mensajeError.setValue("Precio inválido.");
            return;
        }

        // Verificar si el código ya existe
        for (Producto p : MainActivity.listaProductos) {
            if (p.getCodigo().equalsIgnoreCase(codigo.trim())) {
                mensajeError.setValue("El código ya existe en otro producto.");
                return;
            }
        }

        // Si todo está bien, crear y agregar
        Producto nuevoProducto = new Producto(codigo.trim(), descripcion.trim(), precio);
        MainActivity.listaProductos.add(nuevoProducto);
        
        productoGuardado.setValue(true);
    }
}
