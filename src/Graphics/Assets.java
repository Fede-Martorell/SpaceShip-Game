package Graphics;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage player;
    //Effect speed
    public static BufferedImage speed;

    public static void init()
    {
        player = Loader.ImageLoader("/PNG/player.png");
        speed = Loader.ImageLoader("/PNG/Effects/fire08.png");
    }

}