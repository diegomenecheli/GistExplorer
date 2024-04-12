package com.menecheli.gistexplorer.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

@Composable
fun AvatarImage(avatarUrl: String, modifier: Modifier = Modifier) {
    val painter = rememberImagePainter(
        data = avatarUrl,
        builder = {
            transformations(CircleCropTransformation())
        }
    )

    Image(
        painter = painter,
        contentDescription = "Avatar",
        modifier = modifier.size(50.dp),
    )
}

@Preview
@Composable
fun AvatarImagePreview() {
    val avatarUrl = "https://avatars.githubusercontent.com/u/23506375?v=4"
    AvatarImage(avatarUrl = avatarUrl)
}