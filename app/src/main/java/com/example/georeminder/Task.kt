package com.example.georeminder

import java.util.Date

class Task {
    private var title: String
    private var coordinates: Coordinates
    private var repeat: Boolean = false
    private lateinit var lastFound: Date

    constructor(title: String, lat: Float, lon: Float, repeat: Boolean) {
        this.title = title
        this.repeat = repeat
        this.coordinates = Coordinates(lat, lon)
    }

    public fun getTitle(): String {
        return this.title
    }

    public fun getCoordinates(): Coordinates {
        return this.coordinates
    }

    public fun getRepeat(): Boolean {
        return this.repeat
    }

}