package com.calculadora.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tela: MutableList<String> = mutableListOf("")

        supportActionBar!!.hide()

        //set numbers value
        btn_num_zero.setOnClickListener {AddExpression("0", true, tela)}
        btn_num_one.setOnClickListener {AddExpression("1", true, tela)}
        btn_num_two.setOnClickListener {AddExpression("2", true, tela)}
        btn_num_three.setOnClickListener {AddExpression("3", true, tela)}
        btn_num_four.setOnClickListener {AddExpression("4", true, tela)}
        btn_num_five.setOnClickListener {AddExpression("5", true, tela)}
        btn_num_six.setOnClickListener {AddExpression("6", true, tela)}
        btn_num_seven.setOnClickListener {AddExpression("7", true, tela)}
        btn_num_eight.setOnClickListener {AddExpression("8", true, tela)}
        btn_num_nine.setOnClickListener {AddExpression("9", true, tela)}
        btn_dot.setOnClickListener {AddExpression(".", true, tela)}

        //set operator value
        btn_sum.setOnClickListener {AddExpression("+", false, tela)}
        btn_sub.setOnClickListener {AddExpression("-", false, tela)}
        btn_mult.setOnClickListener {AddExpression("*", false, tela)}
        btn_div.setOnClickListener {AddExpression("/", false, tela)}
        btn_potencia.setOnClickListener{AddExpression("^", false, tela)}
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
    @ExperimentalStdlibApi
    fun AddExpression(string: String, limpar_dados: Boolean, listaAtual: MutableList<String>){

        if(tv_result.text.isNotEmpty()){
            var texto: String

            texto = this.resultTela(listaAtual, tv_expression.text.toString());
            tv_historico.text = "\n "+texto+" \n";

            tv_expression.text = ""

        }

        if(limpar_dados){
            tv_result.text = ""
            tv_expression.append(string)
        }else{
            tv_expression.append(tv_result.text)
            tv_expression.append(string)
            tv_result.text = ""
        }
    }


    @ExperimentalStdlibApi
    fun resultTela(listaAtual: MutableList<String>, operacao: String): String {
        var retorno = "";
        if(listaAtual.size == 10){
            listaAtual.removeFirst();
        }
        if(!listaAtual.last().contains("\n")){
            listaAtual[listaAtual.size-1] = listaAtual.last() + operacao;
        }else{
            listaAtual.add(operacao)
        }


        for(x in listaAtual){
            retorno = retorno + x
        }

        return retorno;
    }
}