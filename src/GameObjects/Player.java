package GameObjects;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import Graphics.Assets;

import Input.KeyBoard;
import Math.Vector2D;

public class Player extends MovingObjects{

    private Vector2D heading;

    public Player(Vector2D position,Vector2D velocity, BufferedImage texture) {
        super(position,velocity, texture);
        heading = new Vector2D(0,1);
    }

    @Override
    public void update() {

        //Rotacion sentido aguja del reloj.
        if (KeyBoard.Right || KeyBoard.RIGHT){
            angle += Math.PI/25;
        }
        //Rotacion sentido en contra de la aguja del reloj.
        if (KeyBoard.Left || KeyBoard.LEFT){
            angle -= Math.PI/25;
        }
        //rotacion en radianes. PI/2 = 90 degrees.
        heading = heading.setDirection(angle-Math.PI/2);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        //Transformacion de la posicion a la actual.
        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());

        //Aplicamos la transformacion, este se realiza en torno al centro del objeto.
        at.rotate(angle, Assets.player.getWidth()/2, Assets.player.getHeight()/2 );

        //Dibujamos el objeto.
        g2d.drawImage(Assets.player, at, null);


    }
}