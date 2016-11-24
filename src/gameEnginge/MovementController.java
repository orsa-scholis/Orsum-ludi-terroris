package gameEnginge;

import main.GameGraphic;

/**
 * Created by Philipp on 17/11/2016.
 */
public class MovementController {

    private static GameGraphic gamegraphic;

    public static int UP = 0;
    public static int DOWN = 2;
    public static int RIGHT = 1;
    public static int LEFT = 3;


    public MovementController(GameGraphic gg){
        gamegraphic = gg;
    }

    public boolean move(GameObject gm, int direction){
        System.out.println("-------------Move----------------");
        int posX = gm.getPositionX();
        int posY = gm.getPositionY();
        switch (direction) {
            case 0:
                if (isThereNoObstacle(posX, posY + 1)) {
                    if ((posY += 1) > gamegraphic.getGameSize()-1) {
                        posY = gamegraphic.getGameSize()-1;
                    }
                }
                break;
            case 1:
                if (isThereNoObstacle(posX + 1, posY)) {
                    if ((posX += 1) > gamegraphic.getGameSize()-1) {
                        posX = gamegraphic.getGameSize()-1;
                    }
                }
                break;
            case 2:
                if (isThereNoObstacle(posX, posY - 1)) {
                    if ((posY -= 1) < 0) {
                        posY = 0;
                    }
                }
                break;
            case 3:
                if (isThereNoObstacle(posX - 1, posY)) {
                    if ((posX -= 1) < 0) {
                        posX = 0;
                    }
                }
                break;
            default:
                return false;
        }
        gm.updatePosition(posX, posY);
        return true;
    }

    public boolean isThereNoObstacle(int x, int y){
        if(x < 0 || y < 0 || x > gamegraphic.getGameSize() -1 || y > gamegraphic.getGameSize() -1 ){
            return false;
        }
        if(gamegraphic.getField()[y][x] == 1){
            System.out.println(gamegraphic.getField()[x][y] + " " + x + " " + y);
            return false;
        }
        System.out.println(gamegraphic.getField()[x][y] + " " + x + " " + y);
        return true;
    }


}
