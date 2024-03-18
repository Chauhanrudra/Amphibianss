@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.amphibians.screens

import androidx.compose.foundation.Image
import coil.compose.rememberImagePainter
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibians.data.Resource
import com.example.amphibians.viewmodel.AmphibianViewModel
import com.example.amphibians.data.Amphibian

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterial3Api
@Composable
fun AmphibianListScreen(viewModel: AmphibianViewModel) {
    val resource by viewModel.amphibians.observeAsState(initial = Resource.Loading())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Amphibian List") }
            )
        }
    ) { innerPadding ->
        when (resource) {
            is Resource.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is Resource.Success -> {
                val amphibians = (resource as Resource.Success<List<Amphibian>>).data
                if (amphibians != null) {
                    AmphibianList(amphibians, innerPadding)
                }
            }
            is Resource.Error -> {
                val message = (resource as Resource.Error).message
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    if (message != null) {
                        Text(text = message)
                    }
                }
            }
        }
    }
}

@Composable
fun AmphibianListItem(amphibian: Amphibian) {
    Card(
        modifier = Modifier.padding(bottom = 6.dp, top = 6.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Image(
                painter = rememberImagePainter(data = amphibian.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = amphibian.name)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = amphibian.type)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = amphibian.description)
        }
    }
}

@Composable
fun AmphibianList(amphibians: List<Amphibian>, paddingValues: PaddingValues) {
    LazyColumn(contentPadding = paddingValues) {
        items(amphibians) { amphibian ->
            AmphibianListItem(amphibian)
        }
    }
}


