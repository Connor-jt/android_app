package com.example.myprogram

class GameState {
    var cameraX = 0.0f
    var cameraY = 0.0f

    var playerObject:ObjectWrapper = ObjectWrapper();

    val objects:MutableList<ObjectWrapper> = mutableListOf();

}