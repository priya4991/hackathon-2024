package com.example.hackathon.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hackathon.R
import com.example.hackathon.tescoFontFamily
import com.example.hackathon.ui.theme.HackathonTheme

@Composable
fun ShoppingList(onClick: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(55.dp)
        .background(color = colorResource(R.color.tesco_blue)),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically) {
        TextButton(onClick = onClick) {
            Text("Close",
                color = Color.White,
                fontFamily = tescoFontFamily,
                fontSize = 18.sp
            )
        }

    }
}
//
//@Preview
//@Composable
//fun ShoppingListView() {
//    HackathonTheme {
//        ShoppingList(null)
//    }
//}