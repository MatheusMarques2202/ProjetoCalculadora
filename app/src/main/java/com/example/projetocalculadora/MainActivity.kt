package com.example.projetocalculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projetocalculadora.ui.theme.ProjetoCalculadoraTheme
import java.text.NumberFormat


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjetoCalculadoraTheme {

                }
            }
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable

fun calcularGorjeta(){
 var valorTotal by remember { mutableStateOf("") }
    var porcentagemGorjeta by remember { mutableStateOf("") }
    val valorGorjeta = calculoDaGorjeta(
        valorTotal.toDoubleOrNull()?: 0.0,
        porcentagemGorjeta.toDoubleOrNull()?: 0.0,
    )

    Column(
       verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    )

    {
        Text(
            text = stringResource(R.string.Gorjeta)
        )
       TextField(
           value = valorTotal ,
           onValueChange = {valorTotal = it},
            label = {
                Text(
                    text = "valor"
                )

            },
           isError = false,
           shape = RectangleShape,
           singleLine = true,
           keyboardOptions = KeyboardOptions (keyboardType = KeyboardType.Number)

       )
        Spacer(modifier = Modifier.size(10.dp))


        TextField(
            value = porcentagemGorjeta,
            onValueChange = {porcentagemGorjeta = it},
            label = {
                Text(
                    text = "% gorjeta "
                )

            },
            isError = false,
            shape = RectangleShape,
            singleLine = true,
            keyboardOptions = KeyboardOptions (keyboardType = KeyboardType.Number)



        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = stringResource(id = R.string.valorGorjeta, valorGorjeta)
        )


    }
}
fun calculoDaGorjeta(valorTotal:Double, porcentagemGorjeta:Double = 15.0):String{

  val gorjeta =  porcentagemGorjeta/100 * (valorTotal)
    return NumberFormat.getCurrencyInstance().format(gorjeta)


}






