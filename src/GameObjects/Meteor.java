package GameObjects;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import Math.Vector2D;
import States.GameState;

public class Meteor extends MovingObjects{

    private Size size;

    public Meteor(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState, Size size) {
        super(position, velocity, maxVel, texture, gameState);
        this.size = size;
    }

    @Override
    public void update() {
        position = position.add(velocity);

        //Evita que el jugador salga de la pantalla.
        if (position.getX() > Constants.WIDTH){
            position.setX(0);
        }
        if (position.getY() > Constants.HEIGHT){
            position.setY(0);
        }
        if (position.getX() < 0){
            position.setX(Constants.WIDTH);
        }
        if (position.getY() < 0){
            position.setY(Constants.HEIGHT);
        }

        angle += Constants.DELTAANGLE/3;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;



        //Transformacion de la posicion a la actual.
        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());

        //Aplicamos la transformacion, este se realiza en torno al centro del objeto.
        at.rotate(angle, width/2, height/2 );

        //Dibujamos el objeto.
        g2d.drawImage(texture, at, null);

    }
    public Size getSize() {
        return size;
    }
}
