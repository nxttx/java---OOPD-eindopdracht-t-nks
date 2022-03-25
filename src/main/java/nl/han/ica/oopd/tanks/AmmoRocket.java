package nl.han.ica.oopd.tanks;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.ArrayList;
import java.util.List;

public abstract class AmmoRocket extends Ammo implements ICollidableWithGameObjects  {
    private Game world;


    protected AmmoRocket(Game world, Sprite sprite) {
        super(world, sprite);
        this.world = world;
    }


    @Override
    public abstract void tileCollisionOccurred(List<CollidedTile> collidedTiles);

    @Override
    public abstract void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects);
}
