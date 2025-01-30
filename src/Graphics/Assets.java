package Graphics;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {
    public static boolean loaded = false;
    public static float count = 0;
    public static float MAX_COUNT = 46;

    public static BufferedImage player;
    //Effect speed
    public static BufferedImage speed;
    //Lasers
    public static BufferedImage blueLaser, redLaser, greenLaser;
    //meteors
    public static BufferedImage[] bigs = new BufferedImage[4];
    public static BufferedImage[] meds = new BufferedImage[2];
    public static BufferedImage[] smalls = new BufferedImage[2];
    public static BufferedImage[] tinies = new BufferedImage[2];
    //Explosion
    public static BufferedImage[] exp = new BufferedImage[9];
    //UFO
    public static BufferedImage ufo;
    //numers
    public static BufferedImage[] numbers = new BufferedImage[11];
    public static BufferedImage life;
    //FONTS
    public static Font fontBig;
    public static Font fontMed;
    //Sounds
    public static Clip backgroundMusic, explosion, playerLoose, playerShoot, ufoShoot;
    //Buttons
    public static BufferedImage greyBtn, blueBtn;


    public static void init()
    {
        player = loadImage("/PNG/player.png");
        speed = loadImage("/PNG/Effects/fire08.png");
        blueLaser = loadImage("/PNG/Lasers/laserBlue01.png");
        redLaser = loadImage("/PNG/Lasers/laserRed01.png");
        greenLaser = loadImage("/PNG/Lasers/laserGreen11.png");
        life = loadImage("/PNG/UI/playerLife1_blue.png");
        fontBig = loadFont("/Bonus/future.ttf",42);
        fontMed = loadFont("/Bonus/future.ttf",20);

        for(int i = 0; i < bigs.length; i++){
            bigs[i] = loadImage("/PNG/Meteors/meteorBrown_big" + (i+1) + ".png");
        }
        for(int i = 0; i < meds.length; i++){
            meds[i] = loadImage("/PNG/Meteors/meteorBrown_med" + (i+1) + ".png");
        }
        for(int i = 0; i < smalls.length; i++){
            smalls[i] = loadImage("/PNG/Meteors/meteorBrown_small" + (i+1) + ".png");
        }
        for(int i = 0; i < tinies.length; i++){
            tinies[i] = loadImage("/PNG/Meteors/meteorBrown_tiny" + (i+1) + ".png");
        }
        for(int i = 0; i < exp.length; i++){
            exp[i] = loadImage("/PNG/Explosion/exp_"+i+".png");
        }
        ufo = loadImage("/PNG/ufoGreen.png");
        for(int i = 0; i < numbers.length; i++){
            numbers[i] = loadImage("/PNG/UI/numeral" + (i) + ".png");
        }
        backgroundMusic = loadSound("/Sounds/backgroundMusic.wav");
        explosion = loadSound("/Sounds/explosion.wav");
        playerLoose = loadSound("/Sounds/playerLoose.wav");
        playerShoot = loadSound("/Sounds/playerShoot.wav");
        ufoShoot = loadSound("/Sounds/ufoShoot.wav");
        greyBtn = loadImage("/PNG/UI/grey_button.png");
        blueBtn = loadImage("/PNG/UI/blue_button.png");
        loaded = true;
    }
    public static BufferedImage loadImage(String path) {
        count ++;
        return Loader.ImageLoader(path);
    }
    public static Font loadFont(String path, int size) {
        count ++;
        return Loader.loadFont(path, size);
    }
    public static Clip loadSound(String path) {
        count ++;
        return Loader.loadSound(path);
    }

}