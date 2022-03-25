package nl.han.ica.oopd.tanks;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import java.util.List;


public abstract class Tank extends SpriteObject implements ICollidableWithTiles {
    private Game world;
    private Sprite sprite;
    private long lastTimeShot;
    private int Shotinterval;
    private CollidedTile collidedTile;


    protected Tank(Game world, Sprite sprite) {
        super(sprite);
        this.world = world;
        setFriction(0.15f);
    }


    public void placeRocketSlow(int id, float xPos, float yPos, float direction,  int amountOfBounce){
        Ammo.ammoRocketSlows.add(new AmmoRocketSlow(world, id, amountOfBounce));
        world.addGameObject(Ammo.ammoRocketSlows.get(id), xPos, yPos);
        float speed = (float)4;
        Ammo.ammoRocketSlows.get(id).setDirectionSpeed(direction, speed);
    }
    public void placeRocketFast(int id, float xPos, float yPos, float direction){
        Ammo.ammoRocketFasts.add(new AmmoRocketFast(world, id));
        world.addGameObject(Ammo.ammoRocketFasts.get(id), xPos, yPos);
        float speed = (float)6;
        Ammo.ammoRocketFasts.get(id).setDirectionSpeed(direction, speed);
    }

    public void tankShootRocketSLow(float angle){
        // standaard bij elke rakket +25 om er voor te zorgen dat hij vanaf het midden van de tank schiet.
        // daarna extra marge er bij zodat de tank niet 'als eerste' wordt geraakt.
        if(angle >= 315 ||  angle <45) { //hoek boven tussen 315 en 45
            placeRocketSlow(Ammo.ammoRocketSlows.size(), getX() +25, getY()+25 -45 , angle,0);
            System.out.println("boven");
        }else if(angle >=45 && angle <135 ){ //hoek rechts tussen 45 & 135
            placeRocketSlow(Ammo.ammoRocketSlows.size(), getX()+25 +25, getY()+25, angle,0);
            System.out.println("rechts");
        }else if(angle >= 135 &&  angle <225){ //hoek onder tussen 135 en 225
            placeRocketSlow(Ammo.ammoRocketSlows.size(), getX()+25, getY()+25 +25, angle,0);
            System.out.println("onder");
        }else if(angle >= 225 &&  angle <315){ // hoek links tussen 225 en 315
            placeRocketSlow(Ammo.ammoRocketSlows.size(), getX()+25-50 , getY()+25, angle,0);
            System.out.println("links");
        }
    }

    @Override
    public abstract void tileCollisionOccurred(List<CollidedTile> collidedTiles);

}