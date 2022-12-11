package com.therzarzayev.retrofitask.model

import org.intellij.lang.annotations.Language

data class MovieModel(
    val movieName: String,
    val movieRate: String,
    val movieImagePath: String,
    val movieLanguage: String,
    val movieDate: String,
    val movieDesc: String
)
