package com.julia.onlinemenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.ScrollView
import android.widget.Toast
import com.julia.onlinemenu.databinding.ActivityMainBinding
import kotlin.collections.count as count

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    var totalValue = 0f

    val listCheckBox = listOf(
        R.id.checkboxEntradas1,
        R.id.checkboxEntradas2,
        R.id.checkboxEntradas3,
        R.id.checkboxEntradas4,
        R.id.checkboxEntradas5,

        R.id.checkboxPP1,
        R.id.checkboxPP2,
        R.id.checkboxPP3,
        R.id.checkboxPP4,
        R.id.checkboxPP5,

        R.id.checkboxBebidas1,
        R.id.checkboxBebidas2,
        R.id.checkboxBebidas3,

        R.id.checkboxSobremesas1,
        R.id.checkboxSobremesas2,
        R.id.checkboxSobremesas3,
        R.id.checkboxSobremesas4,
        )

    private var checkboxStates = mutableMapOf<Int, Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalculate.setOnClickListener(this)
        binding.buttonBuy.setOnClickListener(this)

        // Inicialização do estado do CheckBox no mapa
        for (cb in listCheckBox) {
            checkboxStates[cb] = false
        }
    }

    override fun onClick(view: View) {
        if(view.id == R.id.buttonCalculate){
            if(isValided()){
                calculate()
            } else{
                Toast.makeText(this, "Selecione algum produto!", Toast.LENGTH_LONG).show()
            }

        } else if(view.id == R.id.buttonBuy){
            if(isValided()){
                Toast.makeText(this, "Seu pedido foi enviado para o balcão do restaurante!", Toast.LENGTH_LONG).show()
                uncheckAllCheckboxes()
                binding.textLabelTotal.text = "R$ %.2f".format(totalValue)
            } else{
                Toast.makeText(this, "Selecione algum produto!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculate(){

        for(cb in listCheckBox){
            val CHECK_BOX = findViewById<CheckBox>(cb)
            val PRICE = getPriceCheckBox(cb)

            if(checkboxStates[cb] == false && CHECK_BOX.isChecked){
                totalValue += PRICE
                checkboxStates[cb] = true
            } else if(checkboxStates[cb] == true && !(CHECK_BOX.isChecked)){
                totalValue -= PRICE
                checkboxStates[cb] = false
            } else if(checkboxStates[cb] == false && !(CHECK_BOX.isChecked)){
                continue
            }

        }

        binding.textLabelTotal.text = "R$ %.2f".format(totalValue)

    }

    fun isValided():Boolean{
        var count:Int = 0
        for(cb in listCheckBox){
            val CHECK_BOX = findViewById<CheckBox>(cb)
            if(CHECK_BOX.isChecked){
                count++
                break
            }
        }
        if(count == 0){
            return false
        } else {
            return true
        }
    }

    fun uncheckAllCheckboxes() {
        for (cb in listCheckBox) {
            val checkBox = findViewById<CheckBox>(cb)
            checkBox.isChecked = false
            checkboxStates[cb] = false
        }
        totalValue = 0f
    }

    fun getPriceCheckBox(checkBox: Int):Float{
        return when (checkBox){
            R.id.checkboxEntradas1 -> binding.textPriceEntradas1.text.toString().toFloat()
            R.id.checkboxEntradas2 -> binding.textPriceEntradas2.text.toString().toFloat()
            R.id.checkboxEntradas3 -> binding.textPriceEntradas3.text.toString().toFloat()
            R.id.checkboxEntradas4 -> binding.textPriceEntradas4.text.toString().toFloat()
            R.id.checkboxEntradas5 -> binding.textPriceEntradas5.text.toString().toFloat()

            R.id.checkboxPP1 -> binding.textPricePP1.text.toString().toFloat()
            R.id.checkboxPP2 -> binding.textPricePP2.text.toString().toFloat()
            R.id.checkboxPP3 -> binding.textPricePP3.text.toString().toFloat()
            R.id.checkboxPP4 -> binding.textPricePP4.text.toString().toFloat()
            R.id.checkboxPP5 -> binding.textPricePP5.text.toString().toFloat()

            R.id.checkboxBebidas1 -> binding.textPriceBebidas1.text.toString().toFloat()
            R.id.checkboxBebidas2 -> binding.textPriceBebidas2.text.toString().toFloat()
            R.id.checkboxBebidas3 -> binding.textPriceBebidas3.text.toString().toFloat()

            R.id.checkboxSobremesas1 -> binding.textPriceSobremesas1.text.toString().toFloat()
            R.id.checkboxSobremesas2 -> binding.textPriceSobremesas2.text.toString().toFloat()
            R.id.checkboxSobremesas3 -> binding.textPriceSobremesas3.text.toString().toFloat()
            R.id.checkboxSobremesas4 -> binding.textPriceSobremesas4.text.toString().toFloat()

            else -> {0f}
        }
    }
}
