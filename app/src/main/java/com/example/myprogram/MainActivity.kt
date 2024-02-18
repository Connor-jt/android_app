package com.example.myprogram

import android.os.Bundle
import android.view.SurfaceHolder
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val game_controller = GameInput(findViewById(R.id.JoystickL), findViewById(R.id.JoystickR))


        // Get surface holder and add callback

        // Get surface holder and add callback
        val surfaceHolder: SurfaceHolder = R.id.GameView.getHolder(); //
        surfaceHolder.addCallback(this)


        val temp = Engine(this.getHolder(), game_controller)
    }
}
