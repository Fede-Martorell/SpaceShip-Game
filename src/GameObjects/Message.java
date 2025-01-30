package GameObjects;

import States.GameState;
import Math.Vector2D;
import Graphics.Text;

import java.awt.*;

public class Message {
    private float alpha;
    private String text;
    private Vector2D position;
    private Color color;
    private boolean center;
    private boolean fade;
    private Font font;
    private final float deltaAlpha=0.01f;
    private boolean dead;

    public Message(Vector2D position, boolean fade, String text, Color color,
                   boolean center, Font font) {
        this.font = font;
        this.fade = fade;
        this.text = text;
        this.center = center;
        this.color = color;
        this.position = position;
        this.dead = false;
        if(fade){
            alpha = 1;
        }else{
            alpha = 0;
        }
    }
    public void draw(Graphics2D g2d){
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
        Text.drawText(g2d,text,position,center,color,font);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1));
        position.setY(position.getY()-1);
        if(fade){
            alpha -= deltaAlpha;
        }else{
            alpha += deltaAlpha;
        }
        if(fade && alpha < 0){
            dead = true;
        }
        if(!fade && alpha > 1){
            fade = true;
            alpha = 1;
        }

    }
    public boolean isDead() {return dead;}

}
