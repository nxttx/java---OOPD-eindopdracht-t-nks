package nl.han.ica.oopd.tanks;

import nl.han.ica.oopg.objects.Sprite;

public class BlockSolid extends Block {

    protected BlockSolid(Game world) {
        super(world, new Sprite(Game.MEDIA_URL.concat("blockSolid50_50.png")));
    }

    @Override
    public void update() {
    }

}
