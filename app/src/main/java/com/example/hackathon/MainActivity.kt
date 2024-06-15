package com.example.hackathon

import android.os.Bundle
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
import com.example.hackathon.ui.theme.HackathonTheme

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
                Hackathon()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HackathonTheme {
        Hackathon()
    }
}