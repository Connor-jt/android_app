package com.example.myprogram

import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val game_controller = GameInput(findViewById(R.id.JoystickL), findViewById(R.id.JoystickR))


        // Get surface holder and add callback
        val surface: SurfaceView = findViewById(R.id.GameView); //
        surface.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {}
            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}
            override fun surfaceDestroyed(holder: SurfaceHolder) {}
        })


        val temp = Engine(surface.holder, game_controller)
    }
}
