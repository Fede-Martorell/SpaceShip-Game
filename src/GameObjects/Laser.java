package GameObjects;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Math.Vector2D;
import States.GameState;

public class Laser extends MovingObjects{


    public Laser(Vector2D position, Vector2D velocity, double maxVel, double angle, BufferedImage texture, GameState gameState) {
        super(position, velocity, maxVel, texture, gameState);
        this.angle = angle;
        this.velocity = velocity.scale(maxVel);
    }

    @Override
    public void update() {
        position = position.add(velocity);
        //eliminamos los Laser al salir de la ventana.
        if(position.getX() < 0 || position.getX() > Constants.WIDTH ||
        position.getY() < 0 || position.getY() > Constants.HEIGHT){
            Destroy();
        }

        collideWith();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        at = AffineTransform.getTranslateInstance(position.getX() - width/2 , position.getY());

        at.rotate(angle, width/2,0);

        g2d.drawImage(texture, at, null);
    }
    @Override
    public Vector2D getCenter(){
        return new Vector2D(position.getX() + width/2,
                position.getY() + width/2);
    }
}
