package com.example.myprogram

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt

class Joystick : SurfaceView { // implements SurfaceHolder.Callback, View.OnTouchListener
    // private state values
    var inputX: Float = 0f
    var inputY: Float = 0f
    fun stickRadians():Float{ return atan2(inputX, inputY)}

    // junk
    constructor(context: Context) : super(context) {init()}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {init()}
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {init()}
    private fun init(){
        holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                setupDimensions();
                updateJoystick(centerX, centerY);}
            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}
            override fun surfaceDestroyed(holder: SurfaceHolder) {}
        })

        setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            onTouch(view, motionEvent)
            return@OnTouchListener true
        })
    }


    private var centerX = 0f
    private var centerY = 0f
    private var baseRadius = 0f
    private var hatRadius = 0f
    private fun setupDimensions() {
        centerX = width / 2f
        centerY = height / 2f
        baseRadius = width.coerceAtMost(height) / 3f
        hatRadius = width.coerceAtMost(height) / 5f
    }
    private fun updateJoystick(newX: Float, newY: Float) {
        // update stored x,y & radius values
        inputX = (newX - centerX) / baseRadius;
        inputY = (newY - centerY) / baseRadius;
        //val angleDegrees = Math.toDegrees(angleRadians)
        // redraw joysticks
        if (holder.surface.isValid) {
            val myCanvas: Canvas = this.holder.lockCanvas() //Stuff to draw
            val colors = Paint()
            myCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR) // Clear the BG
            //Draw the base first before shading
            colors.setARGB(255, 0, 0, 255)
            myCanvas.drawCircle(centerX, centerY, baseRadius, colors)
            //Drawing the joystick hat
            colors.setARGB(255, 0, 255, 0)
            myCanvas.drawCircle(newX, newY,hatRadius,colors)
            holder.unlockCanvasAndPost(myCanvas) //Write the new drawing to the SurfaceView
    }}

    private fun onTouch(v: View, e: MotionEvent) {
        if (v != this) return
        if (e.action != MotionEvent.ACTION_UP) {
            val displacement = sqrt((e.x - centerX).toDouble().pow(2.0) + (e.y - centerY).toDouble().pow(2.0)).toFloat()
            if (displacement < baseRadius) {
                updateJoystick(e.x, e.y)
            } else {
                val ratio = baseRadius / displacement
                val constrainedX = centerX + (e.x - centerX) * ratio
                val constrainedY = centerY + (e.y - centerY) * ratio
                updateJoystick(constrainedX, constrainedY)
        }} else updateJoystick(centerX, centerY)
    }

}


