package nl.han.ica.oopd.oopgwiki;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.View;

public class TutorialWorld extends GameEngine {
    private Player player;

    // Deze regel maakt het makkelijker om te refereren naar je plaatjes.
    public static String MEDIA_URL = "src/main/resources/";

    public static void main(String[] args) {
        TutorialWorld tw = new TutorialWorld();
        tw.runSketch();
    }

    @Override
    public void setupGame() {
        int worldWidth = 500;
        int worldHeight = 500;

        // uiteraard kan je het toevoegen van
        // nieuwe game objects misschien het beste
        // in een aparte methode doen
        // i.p.v. de update zo groot te maken.
        player = new Player(this);
        addGameObject(player, 200, 200);

        View view = new View(worldWidth, worldHeight);

        setView(view);
        size(worldWidth, worldHeight);
    }

    @Override
    public void update() {
        // Dit doet nog helemaal niks.

    }

}