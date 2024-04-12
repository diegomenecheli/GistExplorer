package com.menecheli.gistexplorer.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.menecheli.gistexplorer.R

@Composable
fun ErrorView(errorMessage: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            val imagePainter = painterResource(id = R.drawable.error)
            Image(
                painter = imagePainter,
                contentDescription = "Empty Folder",
                modifier = Modifier
                    .size(250.dp)
            )
            Text(
                text = errorMessage,
                style = androidx.compose.material3.MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(top = 16.dp),
                color = Color.Red
            )
        }
    }
}
