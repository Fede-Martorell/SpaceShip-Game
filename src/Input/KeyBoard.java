package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {

    private boolean[] keys = new boolean[256];

    public static boolean UP, LEFT, RIGHT, DOWN,SHOOT;
    public static boolean Up, Left, Right, Down;

    public KeyBoard(){
        UP = false;
        LEFT = false;
        RIGHT = false;
        DOWN = false;
        Up = false;
        Left = false;
        Right = false;
        Down = false;
        SHOOT = false;
    }
    public void update(){
        //movimiento por teclado asignamos teclas.
        UP = keys[KeyEvent.VK_UP];
        LEFT = keys[KeyEvent.VK_LEFT];
        RIGHT = keys[KeyEvent.VK_RIGHT];
        DOWN = keys[KeyEvent.VK_DOWN];
        Up = keys[KeyEvent.VK_W];
        Left = keys[KeyEvent.VK_A];
        Right = keys[KeyEvent.VK_D];
        Down = keys[KeyEvent.VK_S];
        //asignamos tecla de disparo
        SHOOT = keys[KeyEvent.VK_L];

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
