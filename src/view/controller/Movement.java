package view.controller;

import logic.graph.Point;

/**
 * Created by Philipp on 17/11/2016.
 */
public class Movement {

    public static int UP = 0;
    public static int DOWN = 2;
    public static int RIGHT = 1;
    public static int LEFT = 3;


    public Movement(){

    }

    public boolean move(Point pt, int direction){
        System.out.println("-------------Move----------------");
        double posX = pt.getX();
        double posY = pt.getY();
        switch (direction) {
            case 0:
                if (isThereNoObstacle(posX, posY + 1)) {
                    /*if ((posY += 1) > gamegraphic.getGameSize()-1) {
                        posY = gamegraphic.getGameSize()-1;
                    }*/
                }
                break;
            case 1:
                if (isThereNoObstacle(posX + 1, posY)) {
                    /*if ((posX += 1) > gamegraphic.getGameSize()-1) {
                        posX = gamegraphic.getGameSize()-1;
                    }*/
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
        return true;
    }

    public boolean isThereNoObstacle(double posX, double d){
        return true;
    }


}
