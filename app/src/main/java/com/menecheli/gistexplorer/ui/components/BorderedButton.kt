package com.menecheli.gistexplorer.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.menecheli.gistexplorer.BuildConfig

@Composable
fun BorderedButton(onClick: () -> Unit, buttonText: String, modifier: Modifier) {
    val isDarkMode = BuildConfig.USE_DARK_THEME
    OutlinedButton(
        modifier = modifier,
        onClick = { onClick() },
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isDarkMode) Color.Black else Color.White,
            contentColor = if (isDarkMode) Color.White else Color.Black,
        ),
        border = BorderStroke(
            width = 2.dp,
            color = if (isDarkMode) Color.White else Color.Black
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 2.dp
        )
    ) {
        Text(
            text = buttonText,
            color = if (isDarkMode) Color.White else Color.Black
        )
    }
}