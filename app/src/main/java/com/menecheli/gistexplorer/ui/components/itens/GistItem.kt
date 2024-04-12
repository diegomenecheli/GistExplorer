package com.menecheli.gistexplorer.ui.components.itens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.menecheli.gistexplorer.domain.gist.model.Gist
import com.menecheli.gistexplorer.ui.Screen
import com.menecheli.gistexplorer.ui.components.AvatarImage
import com.menecheli.gistexplorer.ui.components.FileTypeChip

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GistItem(gist: Gist, navigation: NavController, toggleFavorite: () -> Unit) {
    var isFavorite by remember { mutableStateOf(gist.favorite) }

    Box(
        modifier = Modifier
            .clickable(onClick = {
                navigation.navigate(Screen.DetailScreen.withArgs(gist.id))
            })
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp)
        ) {
            Row(
                modifier = Modifier.padding(end = 16.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AvatarImage(avatarUrl = gist.owner.avatar)
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Text(
                        text = gist.description?.takeIf { it.isNotBlank() }
                            ?: "No description",
                    )
                    Text(text = gist.owner.login)
                }
                IconButton(onClick = {
                    isFavorite = !isFavorite
                    toggleFavorite()
                }) {
                    Icon(
                        Icons.Default.Favorite,
                        tint = if (isFavorite) Color.Red else Color.Gray,
                        contentDescription = "Favorite"
                    )
                }
            }
            FlowRow {
                gist.files.forEach { files ->
                    FileTypeChip(files.value, navigation)
                }
            }
        }
    }
}