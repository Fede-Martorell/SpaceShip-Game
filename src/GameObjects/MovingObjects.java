package GameObjects;

import Math.Vector2D;
import States.GameState;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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

    protected void collideWith(){
        ArrayList<MovingObjects> movingObjects = gameState.getMovingObjects();

        for (int i = 0; i < movingObjects.size(); i++){
            MovingObjects m = movingObjects.get(i);

            if(m.equals(this)){
                continue;
            }
            // el segundo getCenter es el objeto con el que colisionariamos.
            double distance = m.getCenter().subtract(getCenter()).getMagnitude();
            //segunda condicion es para asegurar que el objeto este en el array.
            if(distance < m.width/2 + width/2 && movingObjects.contains(this)){
                objectCollision(m,this);
            }
        }

    }
    //no destruye los dos meteoros, siguen su curso
    //momento de colision
    private void objectCollision(MovingObjects a, MovingObjects b){
        if (!(a instanceof Meteor && b instanceof Meteor)) {
            gameState.playExplosion(getCenter());
            a.Destroy();
            b.Destroy();
        }
    }

    protected void Destroy(){
        gameState.getMovingObjects().remove(this);
    }

    protected Vector2D getCenter(){

        return new Vector2D(position.getX() + width/2,
                position.getY() + height/2);
    }
}
