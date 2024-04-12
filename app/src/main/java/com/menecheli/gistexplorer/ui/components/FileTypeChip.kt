package com.menecheli.gistexplorer.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults.suggestionChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.menecheli.gistexplorer.domain.gist.model.Files
import com.menecheli.gistexplorer.ui.Screen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun FileTypeChip(files: Files, navigation: NavController) {
    SuggestionChip(
        onClick = {
            val encodedUrl = URLEncoder.encode(files.url, StandardCharsets.UTF_8.toString())
            navigation.navigate(Screen.WebViewScreen.withArgs(encodedUrl))
        },
        label = {
            Text(
                text = files.type,
                color = if (isSystemInDarkTheme()) Color.Black else Color.DarkGray
            )
        },
        colors = suggestionChipColors(containerColor = if (isSystemInDarkTheme()) Color.Gray else Color.LightGray),
        modifier = Modifier.padding(horizontal = 4.dp)
    )
}
