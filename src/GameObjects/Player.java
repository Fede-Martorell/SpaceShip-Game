package GameObjects;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import Graphics.Assets;

import Input.KeyBoard;
import Math.Vector2D;
import States.GameState;

public class Player extends MovingObjects{

    private Vector2D heading;
    private Vector2D acceleration;

    private boolean accelerating = false;

    private Chronometer fireRate;



    public Player(Vector2D position,Vector2D velocity,double maxVel, BufferedImage texture, GameState gameState) {
        super(position,velocity,maxVel, texture, gameState);
        heading = new Vector2D(0,1);
        acceleration = new Vector2D();
        fireRate = new Chronometer();
    }

    @Override
    public void update() {

        if(KeyBoard.SHOOT && !fireRate.isRunning()){
            gameState.getMovingObjects().add(0,new Laser(
                    getCenter().add(heading.scale(width)),heading, Constants.LASER_VEL,
                    angle,Assets.greenLaser, gameState));
            fireRate.run(Constants.FIRERATE);
        }

        //Rotacion sentido aguja del reloj.
        if (KeyBoard.Right || KeyBoard.RIGHT){
            angle += Constants.DELTAANGLE;
        }
        //Rotacion sentido en contra de la aguja del reloj.
        if (KeyBoard.Left || KeyBoard.LEFT){
            angle -= Constants.DELTAANGLE;
        }

        // Vector aceleracion apunta hacia la direccion heading, y su magnitud sera de ACC.
        if (KeyBoard.Up || KeyBoard.UP){
            acceleration = heading.scale(Constants.ACC);
            accelerating = true;
        }else{
            //corroboramos que la velocidad no sea 0
            if (velocity.getMagnitude() != 0){
                //Si la velocidad es negativa, le aplicamos una aceleracion en sentido contrario.
                acceleration = (velocity.scale(-1).normalize()).scale(Constants.ACC/2);
                accelerating = false;
            }

        }

        velocity = velocity.add(acceleration);
        velocity = velocity.limit(maxVel);


        //rotacion en radianes. PI/2 = 90 degrees.
        heading = heading.setDirection(angle-Math.PI/2);

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
        fireRate.update();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        AffineTransform at1 = AffineTransform.getTranslateInstance(position.getX()+ width/2 + 5, position.getY() + height/2 + 10);
        AffineTransform at2 = AffineTransform.getTranslateInstance(position.getX() + 5, position.getY() + height/2 + 10);
    //Dibujamos el velocidad.
        at2.rotate(angle, width/2 - 5,-10);
        at1.rotate(angle, -5,-10);
        if (accelerating){
            g2d.drawImage(Assets.speed,at1,null);
            g2d.drawImage(Assets.speed,at2,null);
        }

        //Transformacion de la posicion a la actual.
        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());

        //Aplicamos la transformacion, este se realiza en torno al centro del objeto.
        at.rotate(angle, width/2, height/2 );

        //Dibujamos el objeto.
        g2d.drawImage(texture, at, null);


    }

    public Vector2D getCenter(){
        return new Vector2D(position.getX() + width/2, position.getY() + height/2);
    }
}