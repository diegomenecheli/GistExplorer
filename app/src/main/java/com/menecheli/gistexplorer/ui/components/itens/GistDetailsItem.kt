package com.menecheli.gistexplorer.ui.components.itens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.menecheli.gistexplorer.domain.gist.model.Gist
import com.menecheli.gistexplorer.ui.Screen
import com.menecheli.gistexplorer.ui.components.AvatarImage
import com.menecheli.gistexplorer.ui.components.FileTypeChip
import com.menecheli.gistexplorer.utils.DateFormater
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GistDetailsItem(gist: Gist, navController: NavController) {
    Text(
        text = gist.description?.takeIf { it.isNotBlank() }
            ?: "No description",
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(16.dp, top = 0.dp)
    )
    Text(
        text = "Owner",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(horizontal = 8.dp)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Row(
            modifier = Modifier.padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AvatarImage(avatarUrl = gist.owner.avatar)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                gist.owner.let { owner ->
                    Text(text = owner.login)
                    val dateFormatter = DateFormater()

                    val formattedDate = dateFormatter.formatDateYear(gist.updated_at)
                    Text(text = formattedDate)
                }
            }
        }
    }
    Text(
        text = "Content",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(horizontal = 8.dp)
    )
    FlowRow {
        gist.files.forEach { files ->
            FileTypeChip(files.value, navController)
        }
        SuggestionChip(
            onClick = {
                val encodedUrl =
                    URLEncoder.encode(gist.url, StandardCharsets.UTF_8.toString())
                navController.navigate(Screen.WebViewScreen.withArgs(encodedUrl))
            },
            label = {
                Text(
                    text = "Check out Github",
                    color = if (isSystemInDarkTheme()) Color.Black else Color.DarkGray
                )
            },
            colors = SuggestionChipDefaults.suggestionChipColors(containerColor = Color.LightGray),
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}