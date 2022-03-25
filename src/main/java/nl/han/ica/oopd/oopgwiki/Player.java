package nl.han.ica.oopd.oopgwiki;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PApplet;

public class Player extends SpriteObject {
    private TutorialWorld world;
    private long  previousMillis;

    public Player(TutorialWorld world) {
        // Met `.concat()` plak je 2 strings aan elkaar.
        // De methode returned een nieuwe String terug.
        super(new Sprite(TutorialWorld.MEDIA_URL.concat("player.png")));
        this.world = world;
    }

    @Override
    public void update() {


    }

    @Override
    public void keyPressed(int keyCode, char key) {
        int interval = 200;
        long currentMillis = System.currentTimeMillis();
        int speed = 5;
        System.out.println(keyCode);
        if (keyCode == world.LEFT) {
            setDirectionSpeed(270, speed);
        }else if (keyCode == world.RIGHT){
            setDirectionSpeed(90, speed);
        }else if (keyCode == world.UP) {
            setDirectionSpeed(0, speed);
        }else if (keyCode == world.DOWN){
            setDirectionSpeed(180, speed);
        }
        if(currentMillis - previousMillis >= interval){
            setDirectionSpeed(0, 0 );
            previousMillis= currentMillis;
        }
    }
}