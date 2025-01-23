package Graphics;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage player;
    //Effect speed
    public static BufferedImage speed;
    //Lasers
    public static BufferedImage blueLaser, redLaser, greenLaser;

    public static BufferedImage[] bigs = new BufferedImage[4];
    public static BufferedImage[] meds = new BufferedImage[2];
    public static BufferedImage[] smalls = new BufferedImage[2];
    public static BufferedImage[] tinies = new BufferedImage[2];


    public static void init()
    {
        player = Loader.ImageLoader("/PNG/player.png");
        speed = Loader.ImageLoader("/PNG/Effects/fire08.png");
        blueLaser = Loader.ImageLoader("/PNG/Lasers/laserBlue01.png");
        redLaser = Loader.ImageLoader("/PNG/Lasers/laserRed01.png");
        greenLaser = Loader.ImageLoader("/PNG/Lasers/laserGreen11.png");

        for(int i = 0; i < bigs.length; i++){
            bigs[i] = Loader.ImageLoader("/PNG/Meteors/meteorBrown_big" + (i+1) + ".png");
        }
        for(int i = 0; i < meds.length; i++){
            meds[i] = Loader.ImageLoader("/PNG/Meteors/meteorBrown_med" + (i+1) + ".png");
        }
        for(int i = 0; i < smalls.length; i++){
            smalls[i] = Loader.ImageLoader("/PNG/Meteors/meteorBrown_small" + (i+1) + ".png");
        }
        for(int i = 0; i < tinies.length; i++){
            tinies[i] = Loader.ImageLoader("/PNG/Meteors/meteorBrown_tiny" + (i+1) + ".png");
        }
    }

}