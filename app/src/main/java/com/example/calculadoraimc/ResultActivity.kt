package com.example.calculadoraimc

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {

    private lateinit var textWeight: TextView
    private lateinit var textHeight: TextView
    private lateinit var textResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        textWeight = findViewById(R.id.textWeight)
        textHeight = findViewById(R.id.textHeight)
        textResult = findViewById(R.id.textResult)

        val bundle = intent.extras
        if (bundle != null) {
            val weight = bundle.getDouble("weight")
            val height = bundle.getDouble("height") / 100

            textWeight.text = "Peso informado: %.2f Kg".format(weight)
            textHeight.text = "Altura informada: %.2f m".format(height)

            val imc = weight / (height * height)

            textResult.text = when {
                imc in 18.5..24.9 -> "Seu IMC é %.2f. Você está no peso normal.".format(imc)
                imc in 25.0..29.9 -> "Seu IMC é %.2f. Você está com sobrepeso.".format(imc)
                imc in 30.0..34.9 -> "Seu IMC é %.2f. Você está com obesidade.".format(imc)
                else -> "Seu IMC é %.2f. Você está com obesidade grave.".format(imc)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}