package com.mumbicodes.data.aggregator.mappers

import com.mumbicodes.domain.model.Calorie
import com.mumbicodes.remote.dto.CaloriesDTO

fun CaloriesDTO.toDomain() = Calorie(
    name = name,
    calories = calories,
    servingSizeGrams = servingSizeGrams,
    fatTotalGrams = fatTotalGrams,
    fatSaturatedGrams = fatSaturatedGrams,
    proteinGrams = proteinGrams,
    sodiumMilligrams = sodiumMilligrams,
    potassiumMilligrams = potassiumMilligrams,
    cholesterolMilligrams = cholesterolMilligrams,
    carbohydratesTotalGrams = carbohydratesTotalGrams,
    fiberGrams = fiberGrams,
    sugarGrams = sugarGrams
)