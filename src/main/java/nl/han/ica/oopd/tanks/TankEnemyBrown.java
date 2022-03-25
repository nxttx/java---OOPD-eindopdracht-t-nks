package nl.han.ica.oopd.tanks;

import java.util.List;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public class TankEnemyBrown extends TankEnemy implements ICollidableWithGameObjects {
    protected long lastTimeShot = 0;
    protected int Shotinterval = 2000;

    protected TankEnemyBrown(Game world, int id) {
        super(world, new Sprite(Game.MEDIA_URL.concat("tankEnemyBrownType0_50_50.png")), id);
    }

    @Override
    public void update() {
        driveAndAttackPlayer();
    }

    @Override
    public void driveAndAttackPlayer() {
        if (getDistanceFrom(Game.players[0]) < 150) {
            if (System.currentTimeMillis() - this.lastTimeShot >= this.Shotinterval) {
                tankShootRocketSLow(getAngleFrom(Game.players[0]));
                this.lastTimeShot = System.currentTimeMillis();
            }
        }
    }
    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
        // TODO Auto-generated method stub

    }
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        Game.score[0] +=250;
    }
    ;

}

