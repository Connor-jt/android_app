package com.example.myprogram

class DeltaTime {
    private var lastTime:Long? = null
    fun delta(): Long {
        val newTime = System.currentTimeMillis()
        var deltaTime:Long = 0
        if (lastTime != null) deltaTime = newTime - lastTime!!
        lastTime = newTime
        return deltaTime
    }
}