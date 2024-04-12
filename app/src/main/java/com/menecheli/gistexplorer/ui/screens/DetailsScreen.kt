package com.menecheli.gistexplorer.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.menecheli.gistexplorer.domain.gist.data.GistsDataSource
import com.menecheli.gistexplorer.presenter.gistDetails.GistDetailsPresenter
import com.menecheli.gistexplorer.ui.components.itens.GistDetailsItem
import com.menecheli.gistexplorer.ui.views.EmptyView
import com.menecheli.gistexplorer.ui.views.ErrorView
import com.menecheli.gistexplorer.ui.views.LoadingView

@Composable
fun DetailScreen(id: String?, navController: NavController, context: Context) {
    var gistDetailsPresenter: GistDetailsPresenter? by remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        val dataSource = GistsDataSource(
            context
        )
        gistDetailsPresenter = GistDetailsPresenter(dataSource)
        id?.let { nonNullId ->
            gistDetailsPresenter?.getGist(nonNullId)
        }
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Gist Details",
                style = MaterialTheme.typography.headlineSmall
            )
        }
        when {
            gistDetailsPresenter == null -> LoadingView()
            gistDetailsPresenter?.errorMessage!!.value.isNotEmpty() -> ErrorView(errorMessage = gistDetailsPresenter!!.errorMessage.value)
            gistDetailsPresenter?.selectedGist != null -> {
                GistDetailsItem(
                    gist = gistDetailsPresenter?.selectedGist!!,
                    navController = navController
                )
            }
            else -> EmptyView()
        }
    }
}