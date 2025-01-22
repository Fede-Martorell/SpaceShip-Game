package GameObjects;

import Math.Vector2D;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class MovingObjects extends GameObject {

    protected Vector2D velocity;
    protected AffineTransform at;   //rotacion
    protected double angle;

    public MovingObjects(Vector2D position,Vector2D velocity, BufferedImage texture) {
        super(position, texture);
        this.velocity = velocity;
        angle = 0;
    }
}
