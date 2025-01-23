package States;

import java.awt.*;
import java.util.ArrayList;

import GameObjects.Constants;
import GameObjects.MovingObjects;
import GameObjects.Player;
import Graphics.Assets;
import Math.Vector2D;

public class GameState {

    private Player player;
    private ArrayList<MovingObjects> movingObjects = new ArrayList<MovingObjects>();

    public GameState()
    {
        player = new Player(new Vector2D(Constants.WIDTH/2 - Assets.player.getWidth()/2,
                Constants.HEIGHT/2 - Assets.player.getHeight()/2),
                new Vector2D(), Constants.PLAYER_MAX_VEL, Assets.player, this);
        movingObjects.add(player);
    }

    public void update()
    {
        for(int i = 0; i < movingObjects.size(); i++){
            movingObjects.get(i).update();
        }
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