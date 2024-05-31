package br.igor.calculadoraaposentadoria

import android.app.Activity
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class MainActivity : Activity() {
    lateinit var spSexo: Spinner
    lateinit var etIdade: EditText
    lateinit var btCalcular: Button
    lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        loadComponent()
        setupSpinnerSexo()
        setupEventListeners()
    }

    private fun setupEventListeners() {
        btCalcular.setOnClickListener{
            val idade = etIdade.text.toString().toInt()
            val sexoSelecionado = spSexo.selectedItem as String
            val resultado = when(sexoSelecionado){
                "masculino" -> (65 - idade)
                else -> 62 - idade
            }

            tvResultado.text = "Faltam $resultado anos para você se aposentar."
            tvResultado.visibility = View.VISIBLE

        }
    }

    private fun setupSpinnerSexo() {
        spSexo.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listOf("masculino", "feminino")
        )
    }


    private fun loadComponent() {
        spSexo = findViewById(R.id.sp_sexo)
        etIdade = findViewById(R.id.et_idade)
        btCalcular = findViewById(R.id.bt_calcular)
        tvResultado = findViewById(R.id.tv_resultado)
    }

}