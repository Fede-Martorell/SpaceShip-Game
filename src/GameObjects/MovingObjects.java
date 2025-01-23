package GameObjects;

import Math.Vector2D;
import States.GameState;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class MovingObjects extends GameObject {

    protected Vector2D velocity;
    protected AffineTransform at;   //rotacion
    protected double angle;
    protected double maxVel;
    protected int width;
    protected int height;
    protected GameState gameState;



    public MovingObjects(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState) {
        super(position, texture);
        this.velocity = velocity;
        this.maxVel = maxVel;
        this.gameState = gameState;
        width = texture.getWidth();
        height = texture.getHeight();
        angle = 0;
    }
}
