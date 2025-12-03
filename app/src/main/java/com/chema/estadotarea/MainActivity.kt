package com.chema.estadotarea

import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var pasosTotales = 1
    private var pasosActuales = 0

    private lateinit var tvProgreso: TextView
    private lateinit var tvPorcentaje: TextView
    private lateinit var btnAvanzar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvProgreso = findViewById(R.id.tvProgreso)
        tvPorcentaje = findViewById(R.id.tvPorcentaje)
        btnAvanzar = findViewById(R.id.btnAvanzar)
        val btnReiniciar = findViewById<Button>(R.id.btnReiniciar)
        val npPasos = findViewById<NumberPicker>(R.id.npPasos)
        val tvSelectorPasos = findViewById<TextView>(R.id.tvSelectorPasos)

        // Configurar NumberPicker
        npPasos.minValue = 1
        npPasos.maxValue = 50
        npPasos.value = pasosTotales
        npPasos.wrapSelectorWheel = true

        // Inicializar vistas
        tvSelectorPasos.text = "Selector de pasos: $pasosTotales"
        actualizarProgreso()

        npPasos.setOnValueChangedListener { _, _, newVal ->
            pasosTotales = newVal
            tvSelectorPasos.text = "Selector de pasos: $pasosTotales"
            // Reiniciar pasos si cambia el total para evitar inconsistencias
            if (pasosActuales > pasosTotales) {
                pasosActuales = pasosTotales
            }
            actualizarProgreso()
        }

        // Configurar Botón Avanzar
        btnAvanzar.setOnClickListener {
            if (pasosActuales < pasosTotales) {
                pasosActuales++
                actualizarProgreso()
            }
        }

        // Configurar Botón Reiniciar
        btnReiniciar.setOnClickListener {
            pasosActuales = 0
            actualizarProgreso()
        }
    }

    private fun actualizarProgreso() {
        // Actualizar contador de pasos
        tvProgreso.text = "$pasosActuales / $pasosTotales"

        // Calcular y actualizar porcentaje
        val porcentaje = if (pasosTotales > 0) {
            (pasosActuales * 100) / pasosTotales
        } else {
            0
        }
        tvPorcentaje.text = "($porcentaje%)"

        // Habilitar o deshabilitar el botón avanzar según el progreso
        btnAvanzar.isEnabled = pasosActuales < pasosTotales
    }
}
