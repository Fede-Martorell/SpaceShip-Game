package GameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Input.KeyBoard;
import Math.Vector2D;

public class Player extends GameObject{

    public Player(Vector2D position, BufferedImage texture) {
        super(position, texture);
    }

    @Override
    public void update() {
        if(KeyBoard.RIGHT || KeyBoard.Right){
            position.setX(position.getX() + 1);
        }
        if( KeyBoard.LEFT || KeyBoard.Left){
            position.setX(position.getX() - 1);
        }
        if( KeyBoard.UP || KeyBoard.Up){
            position.setY(position.getY() - 1);
        }
        if( KeyBoard.DOWN || KeyBoard.Down){
            position.setY(position.getY() +1);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(texture, (int)position.getX(), (int)position.getY(), null);
    }
}