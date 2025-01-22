package States;

import java.awt.Graphics;

import GameObjects.Player;
import Graphics.Assets;
import Math.Vector2D;

public class GameState {

    private Player player;

    public GameState()
    {
        player = new Player(new Vector2D(100, 500),new Vector2D(0,0), Assets.player);
    }

    public void update()
    {
        player.update();
    }

    public void draw(Graphics g)
    {
        player.draw(g);
    }
}