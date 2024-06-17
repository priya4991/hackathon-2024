package com.example.hackathon.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hackathon.R
import com.example.hackathon.model.AlternativeItemSku
import com.example.hackathon.tescoFontFamily
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlternativesList(alternatives: List<AlternativeItemSku>) {
    var showAlternatives by remember { mutableStateOf(false) }
    Column {
        Spacer(modifier = Modifier.height(15.dp))
        CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
            IconButton(
                onClick = { showAlternatives = !showAlternatives },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = if (showAlternatives) painterResource(R.drawable.baseline_arrow_circle_up_24)
                        else painterResource(R.drawable.baseline_arrow_circle_down_24),
                        contentDescription = "Click to toggle list",
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        "Ways to save",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontFamily = tescoFontFamily,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 5.dp),
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .padding(all = 5.dp)
                .alpha(if (showAlternatives) 1f else 0f)
        ) {
            val visibleItems = remember { mutableStateOf(emptyList<AlternativeItemSku>()) }

            // LaunchedEffect to handle the appearance of each item with delay
            LaunchedEffect(alternatives) {
                // Only reset and animate if the alternatives have changed
                if (alternatives != visibleItems.value) {
                    visibleItems.value = emptyList()
                    alternatives.forEachIndexed { _, alternative ->
                        delay(100) // shorter delay to reduce compounded delay effect
                        visibleItems.value += alternative
                    }
                }
            }

            for (alternative in visibleItems.value) {
                key(alternative) {
                    AnimatedVisibility(
                        visible = showAlternatives,
                        enter = fadeIn(animationSpec = tween(700)) + slideInVertically(
                            initialOffsetY = { -it / 2 }), // Adjust offset for smoother effect
                        exit = fadeOut(animationSpec = tween(700)) + slideOutVertically(
                            targetOffsetY = { -it / 2 }) // Adjust offset for smoother effect
                    ) {
                        SkuView(sku = alternative)
                    }
                }
            }
        }
    }
}
