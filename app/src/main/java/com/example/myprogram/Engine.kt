package com.example.myprogram

import android.graphics.Canvas
import android.view.SurfaceHolder
import com.example.myprogram.Render

const val MAX_TPS:Long = 30
const val TPS_PERIOD:Long = 1000 / MAX_TPS
class Engine(canvas: SurfaceHolder, inputs:GameInput) : Thread() {
    private var isRunning = true
    private val game:Game = Game()
    private val renderer:Render = Render(canvas, game)
    // publicly accessible data
    var TPS: Double = 0.0;
    var FPS: Double = 0.0;

    init {start()}
    fun Terminate() {
        isRunning = false
        try {
            join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
    fun GameTick(){
        game.tick()
    }
    fun GameRender(){
        renderer.draw()
    }


    override fun run() {
        super.run()

        // Declare time and cycle count variables
        var updateCount = 0
        var frameCount = 0
        var elapsedTime: Long
        var sleepTime: Long
        var startTime: Long = System.currentTimeMillis()
        // Game loop
        while (isRunning) {

            // run game
            GameTick()
            GameRender()
            updateCount++
            frameCount++;

            // Pause game loop to not exceed target TPS
            elapsedTime = System.currentTimeMillis() - startTime
            sleepTime = (updateCount * TPS_PERIOD - elapsedTime)
            if (sleepTime > 0) {
                try {
                    sleep(sleepTime)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

            // Skip frames to keep up with target TPS
            while (sleepTime < 0 && updateCount < MAX_TPS - 1) {
                GameTick()
                updateCount++
                elapsedTime = System.currentTimeMillis() - startTime
                sleepTime = (updateCount * TPS_PERIOD - elapsedTime)
            }

            // Calculate average TPS and FPS
            elapsedTime = System.currentTimeMillis() - startTime
            if (elapsedTime >= 1000) {
                TPS = updateCount / (0.01 * elapsedTime);
                FPS = frameCount / (0.01 * elapsedTime);
                updateCount = 0
                startTime = System.currentTimeMillis()
            }
        }

    }
}