package States;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import GameObjects.Constants;
import GameObjects.Meteor;
import GameObjects.MovingObjects;
import GameObjects.Player;
import GameObjects.Size;
import Graphics.Assets;
import Math.Vector2D;

public class GameState {

    private Player player;
    private ArrayList<MovingObjects> movingObjects = new ArrayList<MovingObjects>();

    private int meteors;

    public GameState()
    {
        player = new Player(new Vector2D(Constants.WIDTH/2 - Assets.player.getWidth()/2,
                Constants.HEIGHT/2 - Assets.player.getHeight()/2),
                new Vector2D(), Constants.PLAYER_MAX_VEL, Assets.player, this);
        movingObjects.add(player);
        meteors = 1;
        startWave();
    }

    private void startWave(){
        double x, y;
        for(int i=0; i < meteors; i++){
            x = i % 2 == 0 ? Math.random()* Constants.WIDTH : 0;
            y = i % 2 == 0 ? Math.random()* Constants.HEIGHT : 0;

            BufferedImage texture = Assets.bigs[(int)(Math.random()*Assets.bigs.length)];

            movingObjects.add(new Meteor(
                    new Vector2D(x, y),
                    new Vector2D(0,1).setDirection(Math.random()*Math.PI*2),
                    Constants.METEOR_VEL * Math.random() + 1,
                    texture,this, Size.BIG));
        }
        meteors++;
    }


    public void update()
    {
        for(int i = 0; i < movingObjects.size(); i++){
            movingObjects.get(i).update();
        }
        // cuando rompo un meteoro genero otro.
        for(int i = 0; i < movingObjects.size(); i++) {
            if (movingObjects.get(i) instanceof Meteor) {
                return;
            }
        }
        startWave();
    }

    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        // Mejoramos las texturas en rotaciones.
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        for(int i = 0; i < movingObjects.size(); i++){
            movingObjects.get(i).draw(g);
        }
    }

    public ArrayList<MovingObjects> getMovingObjects() {
        return movingObjects;
    }
}