package com.example.myprogram

import android.content.Context
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.View


class Joystick : View {
    // private state values
    private var input_x: Float = 0f
    private var input_y: Float = 0f
    // public state variables
    var x: Float
        get() = input_x
        private set(value) {input_x = value}
    var y: Float
        get() = input_y
        private set(value) {input_y = value}
    var degrees: Float
        get() = 0f // it would be ideal to cache the results instead of recomputing it each time its called???
        private set(value) {} // that would be a tradeoff i guess

    // potentially junk
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}


    private var centerX = 0f
    private var centerY = 0f


    private fun setupDimensions() {
        centerX = (width / 2).toFloat()
        centerY = (height / 2).toFloat()
    }

    fun test(){
        setOnTouchListener(this)
        if (context is JoystickListener) joystickCallback = context as JoystickListener
    }


    private fun drawJoystick(newX: Float, newY: Float) {
        //First determine the sin and cos of the angle that the touched point is at relative to the center of the joystick
        val hypotenuse:Float = sqrt(
            pow(
                (newX - centerX).toDouble(),
                2.0
            ) + pow((newY - centerY).toDouble(), 2.0)
        ).toFloat()
        val sin:Float = (newY - centerY) / hypotenuse //sin = o/h
        val cos:Float = (newX - centerX) / hypotenuse //cos = a/h
    }

    fun surfaceCreated(holder: SurfaceHolder?) {
        setupDimensions()
    }
    fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {}
    fun surfaceDestroyed(holder: SurfaceHolder?) {}

    fun onTouch(v: View, e: MotionEvent): Boolean {
        if (v == this) {
            if (e.action != MotionEvent.ACTION_UP) {
                val displacement = sqrt(
                    pow(
                        (e.x - centerX).toDouble(),
                        2.0
                    ) + pow((e.y - centerY).toDouble(), 2.0)
                ).toFloat()
                if (displacement < baseRadius) {
                    drawJoystick(e.x, e.y)
                    joystickCallback!!.onJoystickMoved(
                        (e.x - centerX) / baseRadius, (e.y - centerY) / baseRadius,
                        id
                    )
                } else {
                    val ratio = baseRadius / displacement
                    val constrainedX = centerX + (e.x - centerX) * ratio
                    val constrainedY = centerY + (e.y - centerY) * ratio
                    drawJoystick(constrainedX, constrainedY)
                    joystickCallback!!.onJoystickMoved(
                        (constrainedX - centerX) / baseRadius,
                        (constrainedY - centerY) / baseRadius,
                        id
                    )
                }
            } else drawJoystick(centerX, centerY)
            joystickCallback!!.onJoystickMoved(0, 0, id)
        }
        return true
    }


    interface JoystickListener {
        fun onJoystickMoved(xPercent: Float, yPercent: Float, id: Int)
    }

}