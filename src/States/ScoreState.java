package States;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import Graphics.Assets;
import GameObjects.Constants;
import Ui.Action;
import Ui.Button;
import Math.Vector2D;
import Graphics.Text;
import io.ScoreData;

public class ScoreState extends State{
    private Button returnButton;
    private PriorityQueue<ScoreData> highScores;
    private Comparator<ScoreData> scoreComparator;
    private ScoreData[] auxArray;

    public ScoreState(){
        returnButton = new Button(Assets.greyBtn, Assets.blueBtn,
                Assets.greyBtn.getHeight(),
                Constants.HEIGHT - Assets.greyBtn.getHeight()*2,
                Constants.RETURN,
                new Action() {
                    @Override
                    public void doAction() {
                        State.changeState(new MenuState());
                    }
                });
        scoreComparator = new Comparator<ScoreData>() {
            @Override
            public int compare(ScoreData e1, ScoreData e2) {
                return e1.getScore() < e2.getScore() ? -1: e1.getScore() > e2.getScore() ? 1: 0;
            }
        };

        highScores = new PriorityQueue<ScoreData>(10, scoreComparator);

    }
    @Override
    public void update() {
        returnButton.update();
    }

    @Override
    public void draw(Graphics g) {
        returnButton.draw(g);

        auxArray = highScores.toArray(new ScoreData[highScores.size()]);

        Arrays.sort(auxArray, scoreComparator);


        Vector2D scorePos = new Vector2D(
                Constants.WIDTH / 2 - 200,
                100
        );
        Vector2D datePos = new Vector2D(
                Constants.WIDTH / 2 + 200,
                100
        );

        Text.drawText(g, Constants.SCORE, scorePos, true, Color.BLUE, Assets.fontBig);
        Text.drawText(g, Constants.DATE, datePos, true, Color.BLUE, Assets.fontBig);

        scorePos.setY(scorePos.getY() + 40);
        datePos.setY(datePos.getY() + 40);

        for(int i = auxArray.length - 1; i > -1; i--) {

            ScoreData d = auxArray[i];

            Text.drawText(g, Integer.toString(d.getScore()), scorePos, true, Color.WHITE, Assets.fontMed);
            Text.drawText(g, d.getDate(), datePos, true, Color.WHITE, Assets.fontMed);

            scorePos.setY(scorePos.getY() + 40);
            datePos.setY(datePos.getY() + 40);

        }

    }
}
