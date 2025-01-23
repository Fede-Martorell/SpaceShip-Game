package Graphics;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage player;
    //Effect speed
    public static BufferedImage speed;
    //Lasers
    public static BufferedImage blueLaser, redLaser, greenLaser;

    public static void init()
    {
        player = Loader.ImageLoader("/PNG/player.png");
        speed = Loader.ImageLoader("/PNG/Effects/fire08.png");
        blueLaser = Loader.ImageLoader("/PNG/Lasers/laserBlue01.png");
        redLaser = Loader.ImageLoader("/PNG/Lasers/laserRed01.png");
        greenLaser = Loader.ImageLoader("/PNG/Lasers/laserGreen11.png");
    }

}