package com.mumbicodes.foodDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FoodDetailsScreenRoute(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth().height(72.dp)) {
        Text("Hello ")
    }
}