package com.example.hackathon.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hackathon.api.AlternativeItem
import com.example.hackathon.tescoFontFamily
import com.example.hackathon.ui.theme.HackathonTheme

@Composable
fun AlternativesList(alternatives: List<AlternativeItem>) {

    Column(modifier = Modifier.padding(all = 5.dp)) {
        for (alternative in alternatives) {
            Row {
                Text(
                    alternative.setup,
                    fontSize = 14.sp,
                    fontFamily = tescoFontFamily,
                    fontWeight = FontWeight.Normal
                )

                Text(
                    alternative.punchline,
                    fontSize = 14.sp,
                    fontFamily = tescoFontFamily,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlternativesListPreview() {
    HackathonTheme {
        AlternativesList(
            alternatives = arrayListOf(
                AlternativeItem(
                    "Dr Pepper Zero 330 M",
                    "The setup",
                    "The punchline",
                    1
                )
            )
        )
    }
}