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
import Graphics.Animation;

public class GameState {

    private Player player;
    private ArrayList<MovingObjects> movingObjects = new ArrayList<MovingObjects>();
    private ArrayList<Animation> explosions = new ArrayList<Animation>();

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

    public void divideMeteor(Meteor meteor){
        //tamaño del meteoro actual
        Size size = meteor.getSize();

        BufferedImage[] texture = size.textures;
        //Cambio de tamaño del meteoro
        Size newSize = null;
        switch (size){
            case BIG:
                newSize = Size.MED;
                break;
            case MED:
                newSize = Size.SMALL;
                break;
            case SMALL:
                newSize = Size.TINY;
                break;
            default:
                return;
        }
        //creamos los nuevos meteoros
        for(int i = 0; i < size.quantity; i++){
            movingObjects.add(new Meteor(meteor.getPosition(),
                    new Vector2D(0,1).setDirection(Math.random()*Math.PI*2),
                    Constants.METEOR_VEL * Math.random() + 1,
                    texture[(int)(Math.random()*texture.length)],this, newSize));
        }

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

    public void playExplosion(Vector2D position){
        explosions.add(new Animation(Assets.exp, 50,position.subtract
                (new Vector2D(Assets.exp[0].getWidth()/2, Assets.exp[0].getHeight()))));

    }


    public void update()
    {
        //actualizacion de objetos
        for(int i = 0; i < movingObjects.size(); i++){
            movingObjects.get(i).update();
        }
        //actualizacion de explosiones
        for(int i = 0; i < explosions.size(); i++){
            Animation anim = explosions.get(i);
            anim.update();
            if(!anim.isRunning()){
                explosions.remove(i);
            }
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
        // Dibujamos las explosiones.
        for(int i = 0; i < explosions.size(); i++){
            Animation anim = explosions.get(i);
            g2d.drawImage(anim.getCurrentFrame(), (int)anim.getPosition().getX(),
                    (int)anim.getPosition().getY(),null);

        }
    }

    public ArrayList<MovingObjects> getMovingObjects() {
        return movingObjects;
    }
}