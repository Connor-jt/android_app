package com.example.myprogram

import android.graphics.Canvas
import android.view.SurfaceHolder

// responsible for drawing any objects to the screen
class Render(surface: SurfaceHolder, game: Game){
    private val surface: SurfaceHolder
    private val game: Game
    init{this.surface = surface; this.game = game}

    fun draw(){
        // get all objects
        // get camera position
        // draw everything to canvas
        val canvas:Canvas
        synchronized(surface){
            canvas = surface.lockCanvas()
            // call draw events here
        }
        surface.unlockCanvasAndPost(canvas)
    }
}





