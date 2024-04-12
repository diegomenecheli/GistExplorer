package com.menecheli.gistexplorer.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.menecheli.gistexplorer.R
import com.menecheli.gistexplorer.ui.components.BorderedButton

@Composable
fun EmptyView(reloadData: (() -> Unit)? = null) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            val imagePainter = painterResource(id = R.drawable.empty_folder)
            Image(
                painter = imagePainter,
                contentDescription = "Empty Folder",
                modifier = Modifier
                    .size(250.dp)
            )
            reloadData?.let {
                BorderedButton(
                    onClick = { reloadData() },
                    buttonText = "Reload Informations",
                    modifier = Modifier
                        .width(250.dp)
                        .padding(top = 8.dp)
                )
            } ?: run {
                Text(
                    text = "No data found.",
                    style = androidx.compose.material3.MaterialTheme.typography.labelLarge
                )
            }

        }
    }
}
