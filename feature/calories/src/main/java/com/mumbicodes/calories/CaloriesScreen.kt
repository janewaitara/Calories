package com.mumbicodes.calories

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mumbicodes.calories.components.CalorieComponent
import com.mumbicodes.calories.components.SectionSlot
import com.mumbicodes.domain.model.Calorie
import com.mumbicodes.ui.presentation.theme.Space12dp
import com.mumbicodes.ui.presentation.theme.Space16dp
import com.mumbicodes.ui.presentation.theme.Space4dp
import com.mumbicodes.ui.presentation.theme.Space8dp
import com.mumbicodes.ui.presentation.theme.inputFieldBackground
import com.mumbicodes.ui.presentation.theme.normalText
import com.mumbicodes.ui.presentation.theme.strongText

@Composable
fun CaloriesScreenRoute(
    viewmodel: CaloriesScreenViewModel,
    onCalorieClicked: () -> Unit
) {
    val screenState = viewmodel.screenState.collectAsStateWithLifecycle().value

    CaloriesScreenContent(
        state = screenState,
        onSearchParamChanged = viewmodel::updateSearchParam,
        onSearchClicked = viewmodel::searchCalories,
        onCalorieClicked = { calorie ->
            viewmodel.updateCalorie(calorie)
            onCalorieClicked()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CaloriesScreenContent(
    modifier: Modifier = Modifier,
    state: CaloriesScreenState,
    onSearchParamChanged: (String) -> Unit,
    onSearchClicked: () -> Unit,
    onCalorieClicked: (com.mumbicodes.domain.model.Calorie) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(Space16dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.screen_heading),
                style = MaterialTheme.typography.titleLarge
            )
        }
        Spacer(Modifier.height(Space16dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Space8dp)
                .clip(RoundedCornerShape(Space4dp))
                .background(MaterialTheme.colorScheme.inputFieldBackground),
            singleLine = true,
            value = state.searchParam,
            onValueChange = onSearchParamChanged,
            placeholder = {
                Text(
                    modifier = Modifier.alpha(0.8f),
                    text = stringResource(R.string.search_placeHolder),
                    color = MaterialTheme.colorScheme.normalText,
                    style = MaterialTheme.typography.bodySmall
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.inputFieldBackground,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            trailingIcon = {
                IconButton(
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.primaryContainer,
                        disabledContainerColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContentColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    enabled = state.searchParam.isNotEmpty() && (state.caloriesSearchResults !is ListState.Loading),
                    onClick = {
                        keyboardController?.hide()
                        onSearchClicked()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = "search food"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            textStyle = MaterialTheme.typography.bodySmall,
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                    onSearchClicked()
                }
            )
        )

        AnimatedVisibility(visible = state.recentSearches.isNotEmpty()) {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(Space8dp)) {
                items(state.recentSearches) { searchParam ->
                    FilterChip(
                        selected = state.searchParam == searchParam,
                        onClick = {
                            onSearchParamChanged(searchParam)
                            onSearchClicked()
                        },
                        label = {
                            Text(
                                modifier = Modifier.alpha(0.8f),
                                text = searchParam,
                                color = MaterialTheme.colorScheme.normalText,
                                style = MaterialTheme.typography.bodySmall
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.onSecondary,
                            selectedLabelColor = MaterialTheme.colorScheme.surface
                        )
                    )
                }
            }
        }

        Spacer(Modifier.height(Space16dp))

        when (val results = state.caloriesSearchResults) {
            is ListState.Error -> {
                SectionSlot(
                    icon = Icons.Rounded.Warning,
                    sectionHeaderText = stringResource(id = R.string.error_state_header),
                    sectionBodyText = results.errorMessage,
                    contentDesc = stringResource(id = R.string.error_state_content_desc),
                    iconBgColor = MaterialTheme.colorScheme.errorContainer,
                    iconBorderColor = MaterialTheme.colorScheme.onError,
                    iconColor = MaterialTheme.colorScheme.error
                )
            }

            ListState.Idle -> {
                SectionSlot(
                    icon = Icons.Rounded.Search,
                    sectionHeaderText = stringResource(id = R.string.empty_state_header),
                    sectionBodyText = stringResource(id = R.string.empty_state_text),
                    contentDesc = stringResource(id = R.string.empty_state_content_desc),
                    iconBgColor = MaterialTheme.colorScheme.primaryContainer,
                    iconBorderColor = MaterialTheme.colorScheme.onPrimary,
                    iconColor = MaterialTheme.colorScheme.primary
                )
            }

            ListState.Loading -> {
                Column(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is ListState.Success -> {
                when (results.data) {
                    is ListSuccessState.Data -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(Space12dp)
                        ) {
                            stickyHeader {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = stringResource(id = R.string.search_results),
                                    color = MaterialTheme.colorScheme.strongText,
                                    style = MaterialTheme.typography.bodyLarge,
                                    textAlign = TextAlign.Start
                                )

                                Spacer(Modifier.height(Space4dp))
                            }
                            items(results.data.data) { calorie ->
                                CalorieComponent(
                                    modifier = Modifier,
                                    calorie = calorie,
                                    onCalorieClicked = { onCalorieClicked(calorie) }
                                )
                            }
                        }
                    }

                    ListSuccessState.NoResults -> {
                        SectionSlot(
                            icon = Icons.Rounded.Face,
                            sectionHeaderText = stringResource(id = R.string.no_results_header),
                            sectionBodyText = stringResource(id = R.string.no_results_text, state.searchParam),
                            contentDesc = stringResource(id = R.string.no_results_content_desc),
                            iconBgColor = MaterialTheme.colorScheme.primaryContainer,
                            iconBorderColor = MaterialTheme.colorScheme.onPrimary,
                            iconColor = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}