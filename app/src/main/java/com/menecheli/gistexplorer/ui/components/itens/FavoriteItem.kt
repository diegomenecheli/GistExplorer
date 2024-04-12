package com.menecheli.gistexplorer.ui.components.itens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

@Composable
fun FavoriteItem(gist: Gist, navController: NavController, deleteFavorite: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable(onClick = {
                navController.navigate(Screen.DetailScreen.withArgs(gist.id))
            })
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AvatarImage(avatarUrl = gist.owner.avatar)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = gist.owner.login)
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {
                deleteFavorite()
            }) {
                Icon(
                    Icons.Default.Delete,
                    tint = Color.Gray,
                    contentDescription = "Delete"
                )
            }
        }
    }
}