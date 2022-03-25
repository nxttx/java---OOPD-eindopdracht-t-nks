package nl.han.ica.oopd.tanks;

import nl.han.ica.oopg.objects.Sprite;

public class BlockBreakable extends Block {
    protected boolean broken;
    protected int id;
    protected static int amount = 0;

    protected BlockBreakable(Game world, boolean broken, int id ) {
        super(world,new Sprite(Game.MEDIA_URL.concat("blockBreakable50_50.png")));
        this.broken = broken;
        this.id= id;
        amount++;
    }

    public boolean isBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    public static int getAmount() {
        return amount;
    }

    public static void setAmount(int amount) {
        BlockBreakable.amount = amount;
    }

    @Override
    public void update() {

    }
}
