package States;

import java.awt.*;
import java.util.ArrayList;

import GameObjects.Constants;
import Ui.Action;
import Ui.Button;
import Graphics.Assets;

public class MenuState extends State{

    public ArrayList<Button> buttons;

    public MenuState(){
        buttons = new ArrayList<Button>();
        buttons.add(new Button(Assets.greyBtn, Assets.blueBtn,
                Constants.WIDTH / 2 - Assets.greyBtn.getWidth() / 2,
                Constants.HEIGHT / 2 - Assets.greyBtn.getHeight() * 2,
                Constants.PLAY,
                new Action() {
            @Override
            public void doAction() {
                State.changeState(new GameState());
            }
        }));
        buttons.add(new Button(Assets.greyBtn, Assets.blueBtn,
                Constants.WIDTH / 2 - Assets.greyBtn.getWidth() / 2,
                Constants.HEIGHT / 2 + Assets.greyBtn.getHeight() * 2,
                Constants.EXIT,
                new Action() {
            @Override
            public void doAction() {
                System.exit(0);
            }
        }));
        buttons.add(new Button(Assets.greyBtn, Assets.blueBtn,
                Constants.WIDTH / 2 - Assets.greyBtn.getWidth() / 2,
                Constants.HEIGHT / 2,
                Constants.HIGH_SCORES,
                new Action() {
                    @Override
                    public void doAction() {
                        State.changeState(new ScoreState());
                    }
                }));

    }


    @Override
    public void update() {
        for(Button b : buttons){
            b.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        for(Button b : buttons){
            b.draw(g);
        }

    }
}
