package nl.han.ica.oopd.oopgwiki;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.View;

public class TutorialWorld3 extends GameEngine {
    public static String MEDIA_URL = "src/main/resources/"; // Deze regel maakt het makkelijker om te refereren naar je plaatjes.


    public static void main(String[] args) {
        TutorialWorld3 tw = new TutorialWorld3();
        tw.runSketch();
    }

    @Override
    public void setupGame() {
        int worldWidth = 800;
        int worldHeight = 600;

        View view = new View(worldWidth, worldHeight);

        setView(view);
        size(worldWidth, worldHeight);
        view.setBackground(loadImage(TutorialWorld3.MEDIA_URL.concat("background.png"))); //achtergrond invoegen. Moet even groot zijn als world width en height.
        //tile.initializeTileMap();
    }

    @Override
    public void update() {
        // Dit doet nog helemaal niks.

    }

}