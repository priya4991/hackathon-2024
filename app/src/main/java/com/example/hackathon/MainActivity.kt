package com.example.hackathon

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.hackathon.compose.Hackathon
import com.example.hackathon.model.Sku
import com.example.hackathon.ui.theme.HackathonTheme
import java.util.UUID

val tescoFontFamily = FontFamily(
    Font(R.font.light, FontWeight.Light),
    Font(R.font.regular, FontWeight.Normal),
    Font(R.font.italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.medium, FontWeight.Medium),
    Font(R.font.bold, FontWeight.Bold)
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HackathonTheme {
                Hackathon(
                    Sku(
                        UUID.randomUUID(),
                        "Dr Pepper Regular 500 M",
                        "£1.69",
                        "£0.34/100ml",
                        R.drawable.drpepper,
                        valid = "Valid for deliver until 23/06",
                        priceMatched = false,
                        clubcardOffer = arrayListOf(
                            "£5 Meal Deal Clubcard Price £5.50",
                            "Meal Deal Regular Price - Selected"
                        )
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HackathonTheme {
        Hackathon(
            Sku(
                UUID.randomUUID(),
                "Dr Pepper Regular 500 M",
                "£1.69",
                "£0.34/100ml",
                R.drawable.drpepper,
                valid = "Valid for deliver until 23/06",
                priceMatched = false,
                clubcardOffer = arrayListOf("£5 Meal Deal Clubcard Price £5.50","Meal Deal Regular Price - Selected")
            )
        )
    }
}