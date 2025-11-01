package ru.sumin.weatherapp.data.mapper

import ru.sumin.weatherapp.data.network.dto.CityDto
import ru.sumin.weatherapp.domain.entity.City

fun CityDto.toEntity(): City = City(id, name, country)