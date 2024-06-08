package com.mumbicodes.foodDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mumbicodes.calories.CaloriesScreenViewModel
import com.mumbicodes.data.domain.model.Calorie
import com.mumbicodes.food_details.R
import com.mumbicodes.ui.presentation.theme.Space24dp
import com.mumbicodes.ui.presentation.theme.Space8dp
import com.mumbicodes.ui.presentation.theme.normalText
import com.mumbicodes.ui.presentation.theme.strongText

@Composable
fun FoodDetailsScreenRoute(
    viewModel: CaloriesScreenViewModel
) {
    val calorie by viewModel.calorieClicked.collectAsStateWithLifecycle()

    FoodDetailsContent(calorie = calorie)
}

@Composable
fun FoodDetailsContent(
    modifier: Modifier = Modifier,
    calorie: Calorie
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(Space24dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = calorie.name,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(Space24dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Space8dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.calories),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.normalText
            )
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.calories_unit, calorie.calories),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.strongText
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Space8dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.fat),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.normalText
            )
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.unit, calorie.fatTotalGrams),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.strongText
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Space8dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.proteins),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.normalText
            )
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.unit, calorie.proteinGrams),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.strongText
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Space8dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.carbs),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.normalText
            )
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.unit, calorie.carbohydratesTotalGrams),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.strongText
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Space8dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.cholesterol),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.normalText
            )
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.unit, calorie.cholesterolMilligrams),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.strongText
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Space8dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.sodium),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.normalText
            )
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.unit, calorie.sodiumMilligrams),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.strongText
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Space8dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.potassium),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.normalText
            )
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.unit, calorie.potassiumMilligrams),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.strongText
            )
        }
    }
}