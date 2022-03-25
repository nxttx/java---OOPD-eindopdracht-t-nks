package nl.han.ica.oopd.tanks;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class AmmoRocketFast extends AmmoRocket implements ICollidableWithGameObjects {
    protected Game world;
    protected int id;

    protected AmmoRocketFast(Game world, int id) {
        super(world, new Sprite(Game.MEDIA_URL.concat("bulletFast_25_13.png")));
        this.world = world;
        this.id = id;

    }

    @Override
    public void update() {

    }

    public void tileCollisionOccurred(List<CollidedTile> collidedTiles){
        world.deleteGameObject(ammoRocketFasts.get(id));
        Ammo.ammoRocketFasts.remove(id);
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        world.deleteGameObject(ammoRocketFasts.get(id));
        Ammo.ammoRocketFasts.remove(id);
        System.out.println(collidedGameObjects.get(0));
        world.deleteGameObject(collidedGameObjects.get(0));
    }
}
