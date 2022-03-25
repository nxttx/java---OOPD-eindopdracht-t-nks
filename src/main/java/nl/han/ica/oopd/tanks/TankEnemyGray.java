package nl.han.ica.oopd.tanks;

import java.util.List;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;


public class TankEnemyGray extends TankEnemy implements ICollidableWithGameObjects {
    protected long lastTimeShot = 0;
    protected int Shotinterval = 2000;
    protected float speed = (float) 0.75;
    private CollidedTile collidedTile;

    protected TankEnemyGray(Game world, int id) {
        super(world, new Sprite(Game.MEDIA_URL.concat("tankEnemyGrayType1_50_50.png")), id);
    }

    @Override
    public void update() {
        driveAndAttackPlayer();
    }

    @Override
    public void driveAndAttackPlayer() {
        float x = getX();
        float y = getY();

        if (speed == 0) {
            switch (collidedTile.getCollisionSide()) {
                case BOTTOM:
                    speed = (float) 0.75;
                    setDirectionSpeed(180, speed);
                    break;

                case TOP:
                    speed = (float) 0.75;
                    setDirectionSpeed(0, speed);
                    break;

                case LEFT:
                    speed = (float) 0.75;
                    setDirectionSpeed(270, speed);
                    break;

                case RIGHT:
                    speed = (float) 0.75;
                    setDirectionSpeed(90, speed);
                    break;
            }
        }else {
            if (getDistanceFrom(Game.players[0]) > 150) {
                //Wanneer de tank te ver van de player is dan kan de tank hem niet zien en gaat hij rondjes rijden in de hoop hem te vinden.
                setDirectionSpeed(270, speed);
                if (x <= 55 && y > 55) {
                    setDirectionSpeed(0, speed); 
                } else if (x <= 55 && y <= 55) {
                    setDirectionSpeed(90, speed);
                } else if (y <= 55 && x < 700) {
                    setDirectionSpeed(90, speed);
                } else if (x >= 700 && y < 450) {
                    setDirectionSpeed(180, speed);
                } else if (y <= 450) {
                    setDirectionSpeed(270, speed);
                }
            } else if (getDistanceFrom(Game.players[0]) < 150) {
                // Wanneer deze tank in de buurt komt van de player dan ziet deze tank hem en probeert hij zo dicht bij mogelijk te komen.
                if (x + 10 <= Game.players[0].getX()) {
                    setDirectionSpeed(90, speed);
                    if (y + 10 <= Game.players[0].getY()) {
                        setDirectionSpeed(180, speed);
                    } else if (y - 10 >= Game.players[0].getY()) {
                        setDirectionSpeed(0, speed);
                    }
                } else if (x - 10 >= Game.players[0].getX()) {
                    setDirectionSpeed(270, speed);
                    if (y + 10 <= Game.players[0].getY()) {
                        setDirectionSpeed(180, speed);
                    } else if (y - 10 >= Game.players[0].getY()) {
                        setDirectionSpeed(0, speed);
                    }
                }
            }
        }

        if (getDistanceFrom(Game.players[0]) < 100) {
            if (System.currentTimeMillis() - this.lastTimeShot >= this.Shotinterval) {
                tankShootRocketSLow(getAngleFrom(Game.players[0]));
                //System.out.println("Tank brown " + id + " can hit player 0 with angle:" + getAngleFrom(Game.players[0]));
                this.lastTimeShot = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
        speed = 0;
        collidedTile = collidedTiles.get(0);
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        Game.score[0] +=500;
    }
}

;

