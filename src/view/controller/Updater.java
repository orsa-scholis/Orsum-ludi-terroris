package view.controller;

import logic.Game;
import logic.graph.Point;
import main.Driver;
import view.input.KeyboardInput;

import static view.controller.Movement.*;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

/**
 *
 * Diese Klasse kümmert sich darum, dass die Datensätze von Monster und Spieler aktualisert werden.
 * Beim Monster passiert dies von Aufruf zu Aufruf, da das Monster dem Pfad folgt.
 * Beim Spieler ändern sich die Daten nur, wenn eine Eingabe per Tastatur vorgenommen wurde.
 *
 * @author Philipp
 *
 */
public class Updater {
    private Driver driver;
    private ArrayList<Point> points;
    private float pathLength;
	private long monsterTimer;
	private float velocity;

    /**
     * Updater-Konstruktor
     *
     * @param driver Um auf die Daten des Monsters und des Spielers zugreifen zu können muss dem Updated, der Driver mitgegeben werden.
     */
    public Updater(Driver driver) {
        this.driver = driver;
        points = null;
        velocity = 0.0003f;

    	refreshMonsterPath();
    }

	/**
	 *
	 * Diese Funktion wird bei jedem Frame aufgerufen.
	 *
	 */
    public void update() {
        if (points == null) {
        	System.out.println("Refresh!");
        	refreshMonsterPath();
        }
        else{
        	float deltaT = System.currentTimeMillis() - monsterTimer;

        	float length = deltaT * velocity;

        	if(Math.abs(length) <= Math.abs(pathLength) && getGame().getMonster().getPoint() != points.get(points.size() - 2)){

		    	Point[] wAIpoints = whereAmI(length);

		    	try {
			    	float deltaX = wAIpoints[1].getFX() - wAIpoints[0].getFX();
			    	float deltaY = wAIpoints[1].getFY() - wAIpoints[0].getFY();

			    	float percent = length / calcLength(wAIpoints[0], wAIpoints[1]);

			    	Point newP = new Point(wAIpoints[0].getX() + deltaX * percent, wAIpoints[0].getY() + deltaY * percent);

			    	getMove().moveTo(getGame().getMonster(), newP);
		    	}
		    	catch (NullPointerException e){
		    		e.printStackTrace();
		    	}

        	}
        	else{
        		refreshMonsterPath();
        	}
        }

        if (KeyboardInput.isKeyDown(GLFW_KEY_W)) {
            getMove().move(getGame().getPlayer(), UP);
            refreshMonsterPath();
        }
        if (KeyboardInput.isKeyDown(GLFW_KEY_S)) {
            getMove().move(getGame().getPlayer(), DOWN);
            refreshMonsterPath();
        }
        if (KeyboardInput.isKeyDown(GLFW_KEY_A)) {
            getMove().move(getGame().getPlayer(), LEFT);
            refreshMonsterPath();
        }
        if (KeyboardInput.isKeyDown(GLFW_KEY_D)) {
            getMove().move(getGame().getPlayer(), RIGHT);
            refreshMonsterPath();
        }
    }

    /**
     * Generiert den Pfad für das Monster neu.
     */
    private void refreshMonsterPath(){
    	points = getGame().getPathForMonster().getPoints();
        //points.remove(0);
        //points.remove(points.size()-1);
        calcPathLength();
        monsterTimer = System.currentTimeMillis();
    }

    private Point[] whereAmI(float length){
    	float cLength = 0;
    	Point lastP = points.get(0);
    	for(int i = 1; i < points.size() - 2; i++){
    		Point pt = points.get(i);
    		cLength += calcLength(lastP, pt);

    		if(cLength >= length){
    			return new Point[]{lastP, pt};
    		}

    		lastP = pt;
    	}
    	return new Point[]{lastP, points.get(points.size() - 1)};
    	//return null;
    }

    private float calcLength(Point one, Point two){
    	return ( (one.getFX() - two.getFX() ) * 2 + (one.getFY() - two.getFY()) * 2 );
    }

    private void calcPathLength(){
    	pathLength = 0;
    	Point lastP = points.get(0);
    	for(Point pt : points){
    		pathLength += calcLength(lastP, pt);
    	}
    }

    private Game getGame() {
        return driver.getGame();
    }

    private Movement getMove() {
        return driver.getMove();
    }

}
