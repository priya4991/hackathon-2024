package com.example.hackathon.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hackathon.tescoFontFamily
import com.example.hackathon.ui.theme.HackathonTheme
import com.example.hackathon.viewmodel.AppViewModel
import com.example.hackathon.viewmodel.AppViewModelFactory

@Composable
fun ErrorMessage(viewModel: AppViewModel?) {
//    HackathonTheme {
    if (viewModel != null) {
        if (viewModel.isError.value) {
            Snackbar(
                modifier = Modifier
                    .padding(16.dp)
                    .zIndex(1f),
                action = {
                    TextButton(
                        onClick = { viewModel.showError(false) },
//                              colors = ButtonDefaults.textButtonColors(contentColor = Color.White)
                    ) {
//                                Icon(
//                                    painterResource(R.drawable.close_24),
//                                    contentDescription = "Click to close"
//                                )
                        Text("Dismiss")
                    }
                },
//                containerColor = colorResource(id = R.color.tesco_red).copy(alpha = 0.25f)
            ) {
                Text("Error. Try again with a different item.",
                    fontFamily = tescoFontFamily)
            }
        }
    }




}

@Preview
@Composable
fun ErrorMessageView() {
    val vm: AppViewModel = viewModel(
        factory = AppViewModelFactory()
    )
    vm.showError(true)
    HackathonTheme {
        ErrorMessage(viewModel = vm)
    }

}