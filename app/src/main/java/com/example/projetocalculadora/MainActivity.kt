package com.example.projetocalculadora

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
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
                AppCalculadorDeGorjeta()
                }
            }
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable

fun AppCalculadorDeGorjeta() {
    var totalConta by remember { mutableStateOf("") }
    var porcentagemGorjeta by remember { mutableStateOf("") }
    var arredondar by remember { mutableStateOf(false) }
    val valorGorjeta = gorjetaCalculo(
        totalConta.toDoubleOrNull() ?: 0.0,
        porcentagemGorjeta.toDoubleOrNull() ?: 0.0,
        arredondar
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Calculador de Gorjeta"

        )
        CampoDeTexto(
            value = totalConta,
            onValueChange = { totalConta = it },
            idTexto = R.string.valorGorjeta,
            imeAction = ImeAction.Next
        )

        CampoDeTexto(
            value = porcentagemGorjeta,
            onValueChange = { porcentagemGorjeta = it },
            idTexto = R.string.valorGorjeta,
            imeAction = ImeAction.Done
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()

        ) {
            Text(
                text = "Arredondar Gorjeta?"
            )
            Switch(
                checked = arredondar,
                onCheckedChange = { arredondar = it }
            )
        }
        Text(

            text = stringResource(R.string.valorGorjeta, valorGorjeta)
        )


    }

}






fun gorjetaCalculo(
    totalConta: Double,
    porcentagemGorjeta: Double = 15.0,
    arredondar: Boolean = false
): String {
    var gorjeta = porcentagemGorjeta / 100 * totalConta
    if (arredondar) {
        gorjeta = kotlin.math.ceil(gorjeta)
    }
    return NumberFormat.getCurrencyInstance().format(gorjeta)

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun CampoDeTexto(
    value: String,
    onValueChange: (String) -> Unit,
    idTexto: Int,
    imeAction: ImeAction,
    @DrawablesRes iconeCampoTexto: Int
) {

    TextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = { Icon( painter = painterResource(id = iconeCampoTexto)) },
        label = {
            Text(text = stringResource(id = idTexto))
        },
        isError = false,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Decimal,
            imeAction = imeAction

        ),

        )
    Spacer(
        modifier = Modifier.size(7.dp)
    )

}








