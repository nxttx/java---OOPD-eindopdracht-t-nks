//#TODO: Add ammo mines.
//#TODO: check rotating sprite.
//#TODO: make rockets bounce.
//#TODO: Score.
//#TODO: Add extra rockets in game.

package nl.han.ica.oopd.tanks;

    import nl.han.ica.oopd.tanks.tiles.BoardsTile;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.GameObject;
    import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.TextObject;
    import nl.han.ica.oopg.tile.TileMap;
    import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.View;

    import java.util.ArrayList;    //ArrayList<int> #Naam# = new ArrayList<int>();

public class Game extends GameEngine {
    //dashboard
    private Dashboard dashboard;
    private TextObject textDashboard[] = new TextObject[4];

    //blocks
    public BlockSolid[] solidBlocks = new BlockSolid[10];
    public BlockBreakable[] breakableBlocks = new BlockBreakable[10];

    //gameobjects
    public static GameObject[] players = new TankPlayer[2];
    private GameObject[] tankEnemies = new TankEnemy[5];

    //Scores // #TODO move to TankPlayer.
    public static int[] score = new int[2];
    private int currentMission = 1;


    //basic game
    final int worldWidth = 800;
    final int worldHeight = 600;
    public static String MEDIA_URL = "src/main/resources/";


    public static void main(String[] args) {
        Game main = new Game();
        main.runSketch();
        ArrayList<AmmoRocketSlow> ammoRocketSlows = new ArrayList<AmmoRocketSlow>();
        ArrayList<AmmoRocketFast> ammoRocketFasts = new ArrayList<AmmoRocketFast>();

    }

    @Override
    public void setupGame() {
        View view = new View(worldWidth, worldHeight);
        view.setBackground(255, 255, 255);
        setView(view);
        size(worldWidth, worldHeight);

        initializeTileMap();
        //buildFirstLevelBreakableBlocks(); //Voor de breakableblocks als we die willen implemmenteren
        placeTanksFirstLevel();
        dashboardBuild();
        background(255);

    }

    private void initializeTileMap() {
        /* TILES */
        Sprite boardsSprite = new Sprite("src/main/resources/blockSolid50_50.png");
        TileType<BoardsTile> boardTileType = new TileType<>(BoardsTile.class, boardsSprite);

        TileType[] tileTypes = {boardTileType};
        int tileSize = 50;
        int tilesMap[][] = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0},
                {0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0},
                {0, -1, -1, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, 0},
                {0, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, 0},
                {0, -1, -1, -1, -1, -1, 0, 0, -1, -1, -1, -1, -1, -1, -1, 0},
                {0, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, 0},
                {0, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, -1, -1, -1, -1, 0},
                {0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0},
                {0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}


        };
        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
    }

    @Override
    public void update() {
        dashboardUpdate();
    }

    public void dashboardBuild() {
        dashboard = new Dashboard(0, 550, worldWidth, 100);
        dashboard.setBackground(205, 205, 205);
        textDashboard[0] = new TextObject(String.valueOf(score[0]), 25);
        textDashboard[1] = new TextObject("Missie:".concat(String.valueOf(currentMission)), 25);
        textDashboard[2] = new TextObject("Levens:".concat(String.valueOf(TankPlayer.getLifes())), 25);
        textDashboard[3] = new TextObject(String.valueOf(score[1]), 25);
        textDashboard[0].setForeColor(0, 0, 255, 255);
        textDashboard[3].setForeColor(255, 0, 0, 255);
        dashboard.addGameObject(textDashboard[0], 0, (int) 12.5, 2);
        dashboard.addGameObject(textDashboard[1], width / 4, (int) 12.5, 2);
        dashboard.addGameObject(textDashboard[2], width / 4 * 2, (int) 12.5, 2);
        dashboard.addGameObject(textDashboard[3], width / 8 * 7, (int) 12.5, 2);
        addGameObject(dashboard, 0, 550);
    }

    public void dashboardUpdate(){
        deleteGameObject(dashboard);
        dashboardBuild();
    }



    public void buildFirstLevelBreakableBlocks() {
        blockBreakablePlace(0, 150, 100);
        blockBreakablePlace(1, 200, 100);
        blockBreakablePlace(2, 250, 100);
        blockBreakablePlace(3, 300, 100);
        blockBreakablePlace(4, 300, 400);
        blockBreakablePlace(5, 350, 400);
        blockBreakablePlace(6, 400, 400);
        blockBreakablePlace(7, 450, 400);
    }

    public void blockBreakablePlace(int id, float xPos, float yPos) {
        breakableBlocks[id] = new BlockBreakable(this, false, id );
        addGameObject(breakableBlocks[id], xPos, yPos);
    }


    public void placeTankPlayer(int id, float xPos, float yPos) {
        players[id] = new TankPlayer(this, id);
        addGameObject(players[id], xPos, yPos);
    }

    public void placeTankEnemy(int id, float xPos, float yPos, int type) {
        if(type==0){
            tankEnemies[id] = new TankEnemyBrown(this, id);
            addGameObject(tankEnemies[id], xPos, yPos);
        }else if(type==1){
            tankEnemies[id] = new TankEnemyGray(this, id);
            addGameObject(tankEnemies[id], xPos, yPos);
        }

    }

    public void placeTanksFirstLevel() { // moet de sprites aan passen van de enemy tank :)
        placeTankPlayer(0, 100, 300);
        placeTankEnemy(0, 200, 50, 1);
        placeTankEnemy(1, 500, 450, 1);
        placeTankEnemy(2, 600, 250, 0);

    }

}