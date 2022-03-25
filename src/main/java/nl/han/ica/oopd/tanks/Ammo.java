package nl.han.ica.oopd.tanks;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

import java.util.ArrayList;
import java.util.List;

public abstract class Ammo extends SpriteObject implements ICollidableWithTiles {
    private Game world;
    private Sprite sprite;
    public static ArrayList<AmmoRocketSlow> ammoRocketSlows = new ArrayList<AmmoRocketSlow>();
    public static ArrayList<AmmoRocketFast> ammoRocketFasts = new ArrayList<AmmoRocketFast>();


    protected Ammo(Game world, Sprite sprite) {
        super(sprite);
        this.world = world;
    }
    @Override
    public abstract void tileCollisionOccurred(List<CollidedTile> collidedTiles);

}
