package com.example.myprogram

class GameObject {

    // position
    var posX:Float = 0.0f;
    var posY:Float = 0.0f
    // height??
    // rotation
    // scale?

    // image

    fun move(x:Float, y:Float){
        posX += x
        posY += y
    }
}