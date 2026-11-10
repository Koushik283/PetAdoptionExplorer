
package com.example.petadoptionexplorer.ui.components

import android.widget.ImageView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide
import com.example.petadoptionexplorer.model.Dog

@Composable
fun DogCard(
    dog: Dog,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            val imageUrl = dog.image?.url

            if (!imageUrl.isNullOrBlank()) {
                AndroidView(
                    factory = {
                        ImageView(it).apply {
                            scaleType = ImageView.ScaleType.CENTER_CROP
                        }
                    },
                    update = { imageView ->
                        Glide.with(imageView)
                            .load(imageUrl)
                            .centerCrop()
                            .into(imageView)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp)
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Image not available")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = dog.name,
                style = MaterialTheme.typography.titleLarge
            )

            dog.bred_for?.let {
                Text("Bred for: $it")
            }

            dog.temperament?.let {
                Text("Temperament: $it")
            }

            dog.life_span?.let {
                Text("Life span: $it")
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Tap to view details",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
