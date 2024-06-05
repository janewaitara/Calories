package com.mumbicodes.calories.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mumbicodes.ui.presentation.theme.normalText
import com.mumbicodes.ui.presentation.theme.strongText

/**
 * This is a slot section that has an icon, header and body text
 *
 * The icon colors can be improved but due to time constraints, I had to add them that way.
 * */
@Composable
fun SectionSlot(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    sectionHeaderText: String,
    sectionBodyText: String,
    contentDesc: String,
    iconBgColor: Color,
    iconBorderColor: Color,
    iconColor: Color
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .background(color = iconBgColor)
                .border(border = BorderStroke(width = 1.dp, color = iconBorderColor), shape = RoundedCornerShape(4.dp))
                .size(72.dp)
                .padding(12.dp),
            imageVector = icon,
            tint = iconColor,
            contentDescription = contentDesc
        )
        Spacer(Modifier.height(32.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = sectionHeaderText,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.strongText,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(8.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = sectionBodyText,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.normalText,
            style = MaterialTheme.typography.bodySmall
        )
    }
}