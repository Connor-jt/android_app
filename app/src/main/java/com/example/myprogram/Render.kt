package com.example.myprogram

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.util.Log
import android.view.SurfaceHolder


const val screen_space = 10.0f

// responsible for drawing any objects to the screen
class Render(surface: SurfaceHolder, game: Game){
    private val surface: SurfaceHolder
    private val game: Game
    init{this.surface = surface; this.game = game}

    fun draw(){
        // get all objects
        // get camera position
        // draw everything to canvas

        //synchronized(surface){
        try {
            val canvas: Canvas = surface.lockCanvas()
            val colors = Paint()
            canvas.drawColor(Color.RED, PorterDuff.Mode.CLEAR) // Clear the BG
            // call draw events here
            for (thing in game.state.objects) {
                if (thing.obj == null) continue
                val gameObj:GameObject = thing.obj!!
                // convert position to relative offset??
                val pixelX = (((game.state.cameraX - gameObj.posX) / screen_space) + 0.5f) * canvas.width;
                val pixelY = (((game.state.cameraY - gameObj.posY) / screen_space) + 0.5f) * canvas.height;


                colors.setARGB(255, 0, 255, 0)
                canvas.drawCircle(pixelX, pixelY, 35f, colors)

                Log.d("error", "pixel:" + pixelX + "," + pixelY + " world:" + (game.state.cameraX - gameObj.posX) + "," + (game.state.cameraY - gameObj.posY))
            }
            //}
            surface.unlockCanvasAndPost(canvas)
        } catch (e: Exception){
            e.message?.let { Log.d("error", it) }
        }
    }
}





