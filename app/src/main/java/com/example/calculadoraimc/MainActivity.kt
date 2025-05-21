package com.example.calculadoraimc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btnCalcular: Button
    private lateinit var editWeight: EditText
    private lateinit var editHeight: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnCalcular = findViewById(R.id.btnCalcular)
        editWeight = findViewById(R.id.editWeight)
        editHeight = findViewById(R.id.editHeight)

        btnCalcular.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)

            val weight = editWeight.text.toString()
            val height = editHeight.text.toString()

            if(weight.isEmpty()){
                editWeight.error = "Peso é obrigatório!"
                return@setOnClickListener
            }
            if(height.isEmpty()){
                editHeight.error = "Altura é obrigatória!"
                return@setOnClickListener
            }

            intent.putExtra("weight", weight.toDouble())
            intent.putExtra("height", height.toDouble())

            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}