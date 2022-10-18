package com.example.tanktracker

data class Tracker (
    var name: String,
    val size: Int,
    var last: Int = 0,
    var next: Int = 0
)