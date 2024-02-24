package com.example.myprogram


const val player_speed:Float = 0.006f

class Game(_input:GameInput) {
    private val deltaTimer = DeltaTime()
    private val input:GameInput = _input
    val state:GameState = GameState()


    init{
        //setup player
        state.playerObject = ObjectWrapper(GameObject())
        state.objects.add(state.playerObject)

    }

    fun tick(){
        val deltaTime = deltaTimer.delta()

        // move player character
        // get the current controller input
        val speed = deltaTime * player_speed
        state.playerObject.obj?.move(-input.rightJoystick.inputX * speed, -input.rightJoystick.inputY * speed)


        // interpolate player camera??



    }
}