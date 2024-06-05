package com.mumbicodes.calories.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import com.mumbicodes.calories.R
import com.mumbicodes.data.domain.model.Calorie
import com.mumbicodes.ui.presentation.theme.Space12dp
import com.mumbicodes.ui.presentation.theme.Space16dp
import com.mumbicodes.ui.presentation.theme.Space24dp
import com.mumbicodes.ui.presentation.theme.Space4dp
import com.mumbicodes.ui.presentation.theme.normalText
import com.mumbicodes.ui.presentation.theme.strongText

@Composable
fun CalorieComponent(
    modifier: Modifier,
    calorie: Calorie,
    onCalorieClicked: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(Space4dp))
            .clickable { onCalorieClicked() }
            .padding(Space16dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = Color.Transparent
        )
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = calorie.name,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.strongText,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(Space12dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.calories_count, calorie.calories.toString()),
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.strongText,
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(Modifier.height(Space24dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(Space12dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.bodySmall.toSpanStyle()
                            .copy(
                                color = MaterialTheme.colorScheme.strongText
                            )
                    ) {
                        append("${calorie.fatTotalGrams} ")
                    }
                    withStyle(
                        style = MaterialTheme.typography.labelSmall.toSpanStyle()
                            .copy(
                                color = MaterialTheme.colorScheme.normalText
                            )
                    ) {
                        append(stringResource(id = R.string.fat))
                    }
                },
                textAlign = TextAlign.Start,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            CircleDecoration()

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.bodySmall.toSpanStyle()
                            .copy(
                                color = MaterialTheme.colorScheme.strongText
                            )
                    ) {
                        append("${calorie.proteinGrams} ")
                    }
                    withStyle(
                        style = MaterialTheme.typography.labelSmall.toSpanStyle()
                            .copy(
                                color = MaterialTheme.colorScheme.normalText
                            )
                    ) {
                        append(stringResource(id = R.string.proteins))
                    }
                },
                textAlign = TextAlign.Start,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            CircleDecoration()

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.bodySmall.toSpanStyle()
                            .copy(
                                color = MaterialTheme.colorScheme.strongText
                            )
                    ) {
                        append("${calorie.carbohydratesTotalGrams} ")
                    }
                    withStyle(
                        style = MaterialTheme.typography.labelSmall.toSpanStyle()
                            .copy(
                                color = MaterialTheme.colorScheme.normalText
                            )
                    ) {
                        append(stringResource(id = R.string.carbs))
                    }
                },
                textAlign = TextAlign.Start,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}