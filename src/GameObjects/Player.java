package GameObjects;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import Graphics.Assets;

import Input.KeyBoard;
import Math.Vector2D;

public class Player extends MovingObjects{

    private Vector2D heading;
    private Vector2D acceleration;
    private final double ACC = 0.2;
    private double DELTAANGLE = 0.08;

    public Player(Vector2D position,Vector2D velocity,double maxVel, BufferedImage texture) {
        super(position,velocity,maxVel, texture);
        heading = new Vector2D(0,1);
        acceleration = new Vector2D();
    }

    @Override
    public void update() {

        //Rotacion sentido aguja del reloj.
        if (KeyBoard.Right || KeyBoard.RIGHT){
            angle += DELTAANGLE;
        }
        //Rotacion sentido en contra de la aguja del reloj.
        if (KeyBoard.Left || KeyBoard.LEFT){
            angle -= DELTAANGLE;
        }

        // Vector aceleracion apunta hacia la direccion heading, y su magnitud sera de ACC.
        if (KeyBoard.Up || KeyBoard.UP){
            acceleration = heading.scale(ACC);
        }else{
            //corroboramos que la velocidad no sea 0
            if (velocity.getMagnitude() != 0){
                //Si la velocidad es negativa, le aplicamos una aceleracion en sentido contrario.
                acceleration = (velocity.scale(-1).normalize()).scale(ACC/2);
            }

        }

        velocity = velocity.add(acceleration);
        velocity.limit(maxVel);



        //rotacion en radianes. PI/2 = 90 degrees.
        heading = heading.setDirection(angle-Math.PI/2);

        position = position.add(velocity);

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