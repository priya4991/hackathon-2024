package com.example.hackathon.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hackathon.R
import com.example.hackathon.api.AlternativeItem
import com.example.hackathon.tescoFontFamily
import com.example.hackathon.ui.theme.HackathonTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlternativesView(alternatives: List<AlternativeItem>) {

    if (alternatives.isNotEmpty()) {

        var showAlternatives by remember { mutableStateOf(false) }

        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (showAlternatives) {
                    CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
                        IconButton(onClick = { showAlternatives = false }, modifier = Modifier.size(24.dp)) {
                            Icon(
                                painterResource(R.drawable.baseline_arrow_circle_up_24),
                                contentDescription = ""
                            )
                        }
                    }
                } else {
                    CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
                        IconButton(onClick = { showAlternatives = true }, modifier = Modifier.size(24.dp)) {
                            Icon(
                                painterResource(R.drawable.baseline_arrow_circle_down_24),
                                contentDescription = ""
                            )
                        }
                    }
                }

                Text(
                    "Ways to save",
                    color = Color.Gray,
                    fontSize = 15.sp,
                    fontFamily = tescoFontFamily,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 5.dp),
                )
            }

            if (showAlternatives) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    AlternativesList(alternatives = alternatives)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlternativesPreview() {
    HackathonTheme {
        AlternativesView(
            alternatives = arrayListOf(
                AlternativeItem(
                    "Dr Pepper Zero 330 M",
                    "The setup",
                    "The pubc",
                    1
                )
            )
        )
    }
}