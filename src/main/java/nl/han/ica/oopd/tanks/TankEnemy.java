package nl.han.ica.oopd.tanks;

import nl.han.ica.oopg.objects.Sprite;

public abstract class TankEnemy extends Tank {
    protected int id;

    protected TankEnemy(Game world, Sprite sprite, int id) {
        super(world, sprite);
        this.id = id;
    }

    abstract void driveAndAttackPlayer();

}

