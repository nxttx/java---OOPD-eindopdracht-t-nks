package nl.han.ica.oopd.tanks;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public abstract class Block extends SpriteObject {
    private Game world;
    private Sprite sprite;


    protected Block(Game world, Sprite sprite) {
        super(sprite);
        this.world = world;
    }

    public void drawBlock(){

    }

}
