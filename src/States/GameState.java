package States;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import GameObjects.*;
import Graphics.Assets;
import Math.Vector2D;
import Graphics.Animation;

public class GameState {

    private Player player;
    private ArrayList<MovingObjects> movingObjects = new ArrayList<MovingObjects>();
    private ArrayList<Animation> explosions = new ArrayList<Animation>();

    private int score = 0;

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

    public void addScore(int value){
        score += value;
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
        spawnUfo();
    }
    //activamos las explosiones en el lugar del objeto destruido.
    public void playExplosion(Vector2D position){
        explosions.add(new Animation(Assets.exp, 50,position.subtract
                (new Vector2D(Assets.exp[0].getWidth()/2, Assets.exp[0].getHeight()/2))));

    }

    public void spawnUfo(){
        int rand = (int) (Math.random()*2);

        double x = rand == 0 ? (Math.random()*Constants.WIDTH) : 0;
        double y = rand == 0 ? 0 : (Math.random()*Constants.HEIGHT);

        ArrayList<Vector2D> path = new ArrayList<Vector2D>();

        double posX, posY;
        //dividimos la pantalla en 4 cuadrantes, y buscamos una posicion aleatoria para que
        //Ufo la recorra.
        posX = Math.random()*Constants.WIDTH/2;
        posY = Math.random()*Constants.HEIGHT/2;
        path.add(new Vector2D(posX, posY));

        posX = Math.random()*(Constants.WIDTH/2) + Constants.WIDTH/2;
        posY = Math.random()*Constants.HEIGHT/2;
        path.add(new Vector2D(posX, posY));

        posX = Math.random()*Constants.WIDTH/2;
        posY = Math.random()*(Constants.HEIGHT/2) + Constants.HEIGHT/2;
        path.add(new Vector2D(posX, posY));

        posX = Math.random()*(Constants.WIDTH/2) + Constants.WIDTH/2;
        posY = Math.random()*(Constants.HEIGHT/2) + Constants.HEIGHT/2;
        path.add(new Vector2D(posX, posY));

        movingObjects.add(new Ufo(new Vector2D(x,y),
                new Vector2D(),Constants.UFO_MAX_VEL,
                Assets.ufo,path,this));

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
        drawScore(g);
    }

    private void drawScore(Graphics g){
        Vector2D pos = new Vector2D(850,25);
        String scoreToString = Integer.toString(score);
        for(int i = 0; i < scoreToString.length(); i++){
            g.drawImage(Assets.numbers[Integer.parseInt(scoreToString.substring(i,i+1))],
                    (int)pos.getX(), (int)pos.getY(),null);
            pos.setX(pos.getX() + 20);
        }
    }

    public ArrayList<MovingObjects> getMovingObjects() {
        return movingObjects;
    }
    public Player getPlayer(){
        return player;
    }
}