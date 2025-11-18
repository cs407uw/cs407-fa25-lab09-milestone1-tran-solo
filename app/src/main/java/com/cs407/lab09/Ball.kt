package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        // TODO: Call reset()
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }
        // Equation 1 Implemented
        velocityX = (velocityX + (1.0/2.0) * (accX + xAcc) * (dT)).toFloat()
        velocityY = (velocityY + (1.0/2.0) * (accY + yAcc) * (dT)).toFloat()

        // Initial values
        val intVelx = velocityX
        val intVely = velocityY

        // Equation 2 Implemented
        posX = (posX + intVelx * (dT) + (1.0/6.0)*(dT)*(dT)*(3*accX + xAcc)).toFloat()
        posY = (posY + intVely * (dT) + (1.0/6.0)*(dT)*(dT)*(3*accY + yAcc)).toFloat()

        // Ensure ball doesn't leave boundaries
        checkBoundaries()
    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // TODO: implement the checkBoundaries function
        // (Check all 4 walls: left, right, top, bottom)

        val width = backgroundWidth - ballSize
        val height = backgroundHeight - ballSize

        // Check left and right walls which are the horizontal boundaries
        if (width < posX) {
            // X coordinates
            posX=width
            velocityX=0f
            accX=0f
        } else if (0 > posX) {
            // X coordinates
            posX=0f
            velocityX=0f
            accX=0f
        }
        // Check top and bottom walls which are the vertical boundaries
        if (height < posY) {
            // Y coordinates
            posY=height
            velocityY=0f
            accY=0f
        } else if (0 > posY) {
            // Y coordinates
            posY=0f
            velocityY=0f
            accY=0f
        }
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // TODO: implement the reset function
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)
        val width = backgroundWidth - ballSize
        val height = backgroundHeight - ballSize

        // Dividing by 2 ensures that the reset is in the middle
        posX = width/2
        posY = height/2
        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f
        isFirstUpdate = true
    }
}