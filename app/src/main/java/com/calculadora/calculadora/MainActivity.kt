package com.calculadora.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()

        //set numbers value
        btn_num_zero.setOnClickListener {AddExpression("0", true)}
        btn_num_one.setOnClickListener {AddExpression("1", true)}
        btn_num_two.setOnClickListener {AddExpression("2", true)}
        btn_num_three.setOnClickListener {AddExpression("3", true)}
        btn_num_four.setOnClickListener {AddExpression("4", true)}
        btn_num_five.setOnClickListener {AddExpression("5", true)}
        btn_num_six.setOnClickListener {AddExpression("6", true)}
        btn_num_seven.setOnClickListener {AddExpression("7", true)}
        btn_num_eight.setOnClickListener {AddExpression("8", true)}
        btn_num_nine.setOnClickListener {AddExpression("9", true)}
        btn_dot.setOnClickListener {AddExpression(".", true)}

        //set operator value
        btn_sum.setOnClickListener {AddExpression("+", false)}
        btn_sub.setOnClickListener {AddExpression("-", false)}
        btn_mult.setOnClickListener {AddExpression("*", false)}
        btn_div.setOnClickListener {AddExpression("/", false)}
        btn_potencia.setOnClickListener{AddExpression("^", false)}
        btn_tangente.setOnClickListener{CalculosComplexos("tan")}
        btn_logaritmo.setOnClickListener{CalculosComplexos("log10")}
        btn_logaritmoNatural.setOnClickListener{CalculosComplexos("log")}
        btn_inverso_seno.setOnClickListener{CalculosComplexos("asin")}
        btn_inverso_coseno.setOnClickListener{CalculosComplexos("acos")}


        //clean
        btn_clean.setOnClickListener{
            tv_expression.text = ""
            tv_result.text = ""
        }

        //backspace
        btn_backspace.setOnClickListener{
            val string = tv_expression.text.toString()

            if(string.isNotBlank()){
                tv_expression.text = string.substring(0, string.length-1)
            }

            tv_result.text = ""
        }

        //Equal
        btn_equal.setOnClickListener {
            try {
                val expression = ExpressionBuilder(tv_expression.text.toString()).build()

                val result = expression.evaluate()
                val longResult = result.toLong()

                if(result == longResult.toDouble()){
                    tv_result.text = longResult.toString()
                } else {
                    tv_result.text = result.toString()
                }

            }catch (e: Exception){

            }
        }

        btn_sqrt.setOnClickListener {
            try {
                val expressao = ExpressionBuilder("sqrt("+tv_expression.text.toString()+")").build()

                val resultado = expressao.evaluate()
                val longResult = resultado.toLong()

                if(resultado == longResult.toDouble()){
                    tv_result.text = longResult.toString()
                }else{
                    tv_result.text = resultado.toString()
                }

            }catch (e: Exception){

            }
        }

        btn_change_sinal.setOnClickListener {
            try {
                val expressao = ExpressionBuilder(tv_expression.text.toString()+"* (-1)").build()

                val resultado = expressao.evaluate()
                val longResult = resultado.toLong()


                if(resultado == longResult.toDouble()){
                    tv_result.text = longResult.toString()
                    tv_expression.text = longResult.toString();
                }else{
                    tv_result.text = resultado.toString()
                    tv_expression.text = resultado.toString()
                }

            }catch (e: Exception){

            }
        }

        btn_modulo.setOnClickListener {
            try {
                val expressao = ExpressionBuilder("abs("+tv_expression.text.toString()+")").build()

                val resultado = expressao.evaluate()
                val longResult = resultado.toLong()

                if(resultado == longResult.toDouble()){
                    tv_result.text = longResult.toString()
                }else{
                    tv_result.text = resultado.toString()
                }

            }catch (e: Exception){

            }
        }




        btn_seno.setOnClickListener {
            try {
                val expressao = ExpressionBuilder("sin("+tv_expression.text.toString()+")").build()

                val resultado = expressao.evaluate()
                val longResult = resultado.toLong()

                if(resultado == longResult.toDouble()){
                    tv_result.text = longResult.toString()
                }else{
                    tv_result.text = resultado.toString()
                }

            }catch (e: Exception){

            }
        }


        btn_cosseno.setOnClickListener {
            try {
                val expressao = ExpressionBuilder(btn_cosseno.text.toString()+"("+tv_expression.text.toString()+")").build()

                val resultado = expressao.evaluate()
                val longResult = resultado.toLong()

                if(resultado == longResult.toDouble()){
                    tv_result.text = longResult.toString()
                }else{
                    tv_result.text = resultado.toString()
                }

            }catch (e: Exception){

            }
        }

    }


    fun CalculosComplexos(calculo: String){
        try {
            val expressao = ExpressionBuilder(calculo+"("+tv_expression.text.toString()+")").build()

            val resultado = expressao.evaluate()
            val longResult = resultado.toLong()

            if(resultado == longResult.toDouble()){
                tv_result.text = longResult.toString()
            }else{
                tv_result.text = resultado.toString()
            }

        }catch (e: Exception){

        }
    }

    //write at expression view
    fun AddExpression(string: String, clean_data: Boolean){

        if(tv_result.text.isNotEmpty()){
            tv_expression.text = ""
        }

        if(clean_data){
            tv_result.text = ""
            tv_expression.append(string)
        } else {
            tv_expression.append(tv_result.text)
            tv_expression.append(string)
            tv_result.text = ""
        }

    }
}