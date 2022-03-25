package nl.han.ica.oopd.tanks;
import static nl.han.ica.oopd.tanks.Game.players;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.TextObject;

import java.awt.*;
import java.util.List;

public class TankPlayer extends Tank  implements ICollidableWithGameObjects {
    private Game world;
    private int id;
    private static int lifes = 3;
    private int mouseX;
    private int mouseY;
    private float speed = (float) 1.5;
    private CollidedTile collidedTile;
    private long lastTimeShot;
    private int Shotinterval = 750;


    protected TankPlayer(Game world, int id) {
        super(world, new Sprite(Game.MEDIA_URL.concat("tankPlayer50_50.png")));
        this.world = world;
        this.id = id;

    }

    @Override
    public void update() {

    }


    @Override
    public void mouseMoved(int x, int y) {
        mouseX = x;
        mouseY = y;
    }

    @Override
    public void keyPressed(int keyCode, char key) {
        if (speed == 0) { //collison
            switch (collidedTile.getCollisionSide()) {
                case BOTTOM:
                    if (keyCode == 83) {
                        speed = (float) 1.5;
                    }
                    break;
                case TOP:
                    if (keyCode == 87) {
                        speed = (float) 1.5;
                    }
                    break;
                case LEFT:
                    if (keyCode == 65) {
                        speed = (float) 1.5;
                    }
                    break;
                case RIGHT:
                    if (keyCode == 68) {
                        speed = (float) 1.5;
                    }
                    break;
            }
        }
        switch (keyCode) {
            case 65:// old arrow keys = world.LEFT
                setDirectionSpeed(270, speed);
                break;
            case 68:   // old arrow keys = world.RIGHT
                setDirectionSpeed(90, speed);
                break;
            case 87:  // old arrow keys = world.UP
                setDirectionSpeed(0, speed);
                break;
            case 83:  // old arrow keys = world.DOWN
                setDirectionSpeed(180, speed);
                break;
        }

        if (keyCode == 32) {
            if (System.currentTimeMillis() - this.lastTimeShot >= this.Shotinterval) {
                tankShootRocketSLow(getAngleFrom(mouseX, mouseY));
                this.lastTimeShot = System.currentTimeMillis();
            }

        }
    }

    public static int getLifes() {
        return lifes;
    }

    public static void setLifes(int lifes) {
        TankPlayer.lifes = lifes;
    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
        speed = 0;
        collidedTile = collidedTiles.get(0);
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        if(TankPlayer.lifes <1){
            world.deleteGameObject(players[0]);
        }else{
            TankPlayer.lifes--;
            world.deleteAllGameOBjects();
            world.placeTanksFirstLevel();
        }
    }
}
