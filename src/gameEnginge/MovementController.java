package gameEnginge;

import main.GameGraphic;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Philipp on 17/11/2016.
 */
public class MovementController {

    private GameGraphic gamegraphic;
    private NumberFormat nf;

    public MovementController(GameGraphic gg){
        gamegraphic = gg;
        nf = NumberFormat.getCurrencyInstance(Locale.GERMAN);
    }

    public boolean isThereObstacle(GameObject gm, float x, float y){
        int i = Math.abs((int) ( x / 0.125));
        if(x % 0.125 != 0){
            System.out.println("x: " + nf.format(x % 0.125));
            //i--;
        }
        int j = Math.abs((int) ( y / 0.125));
        if(y % 0.125 != 0){
            System.out.println("y: " + nf.format(y % 0.125));
            //j--;
        }
        else{

        }

        if(gamegraphic.getField()[i][j] == 1){
            return true;
        }

        return false;
    }


}
