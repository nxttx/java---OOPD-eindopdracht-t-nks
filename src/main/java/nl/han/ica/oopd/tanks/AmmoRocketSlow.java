package nl.han.ica.oopd.tanks;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;

import java.util.List;

public class AmmoRocketSlow extends AmmoRocket implements ICollidableWithGameObjects {
    protected Game world;
    protected int id;
    protected int amountOfBounce;

    protected AmmoRocketSlow(Game world, int id, int amountOfBounce) {
        super(world, new Sprite(Game.MEDIA_URL.concat("bulletSlow_25_13.png")));
        this.world = world;
        this.id = id;
        this.amountOfBounce = amountOfBounce;

    }

    @Override
    public void update() {
    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
        System.out.println(amountOfBounce);
        if (amountOfBounce < 1) {
            System.out.println(getDirection());
            float angle = getDirection();
            // this calculates the angle of income witch is the angle of outgoing.
            // so the bullet bounces.
            if (angle > 315 || angle < 45) { //Top
                angle = (90 - angle) + 90;
            } else if (angle > 45 && angle < 135) { //right
                angle = (180 - angle) + 180;
            } else if (angle > 135 && angle < 225) { //bottom
                float i = angle -90;
                angle= 90-i;
            } else if (angle > 225 && angle < 315) { //left
                if (angle < 270) { //left-top  (below tank)
                    float i =(angle -180);
                    angle = 180 -i;
                } else { // left-bottom (above tank)
                    angle = 360-angle;
                }
            }
            float x = getX();
            float y = getY();
            amountOfBounce += 1;
            setDirection(angle);
        } else if (amountOfBounce < 4) {
            float angle = getDirection();
            float x = getX();
            float y = getY();
            amountOfBounce += 1;
        } else {
            System.out.println(getDirection());
            world.deleteGameObject(ammoRocketSlows.get(0));
            Ammo.ammoRocketSlows.remove(0);
        }
    }


    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        world.deleteGameObject(ammoRocketSlows.get(0));
        Ammo.ammoRocketSlows.remove(0);
        System.out.println(collidedGameObjects.get(0));
        world.deleteGameObject(collidedGameObjects.get(0));
    }

    public void placeRocketSlow(int id, float xPos, float yPos, float direction, int amountOfBounce) {
        Ammo.ammoRocketSlows.add(new AmmoRocketSlow(world, id, amountOfBounce));
        world.addGameObject(Ammo.ammoRocketSlows.get(id), xPos, yPos);
        float speed = (float) 4;
        Ammo.ammoRocketSlows.get(id).setDirectionSpeed(direction, speed);
    }
}
