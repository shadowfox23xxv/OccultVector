package dev.tilegame.worlds;

import dev.tilegame.Game;
import dev.tilegame.Handler;
import dev.tilegame.entities.EntityManager;
import dev.tilegame.entities.creatures.Player;
import dev.tilegame.entities.statics.Tree;
import dev.tilegame.items.ItemManager;
import dev.tilegame.tiles.Tile;
import dev.tilegame.utils.Utils;
import java.awt.Graphics;


public class World {

    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;
    //Entities
    private EntityManager entityManager;
    private Tree tree;
    //Item
    private ItemManager itemManager;
    
    public World(Handler handler, String path){
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 100, 100));
        itemManager = new ItemManager(handler);
        tree = new Tree(handler, 100, 350);
        entityManager.addEntity(tree);
        
        loadWorld(path);
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
        
    }
    
    public void tick(){
        itemManager.tick();
        getEntityManager().tick();
    }
    
    public void render(Graphics g){
        /** 
         * using the start and end variables to prevent the game from rendering tiles that 
         * cannot be seen by the player. They are still ticked so everything updates, but
         * time and resources are not wasted drawing them.  Consider applying this to entities
         * 
         * Math.max stops rendering when we've hit the edge of the map when we've reached the top or left
         * edge. Math.min does the same for the bottom and right edge of the map.
         * 
         **/
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH); 
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
        
        for (int y = yStart; y < yEnd; y++){
            for (int x = xStart; x < xEnd; x++){
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), 
                        (int) (y * Tile.TILEHEIGHT- handler.getGameCamera().getyOffset()));
            }
        }
        //Items
        itemManager.render(g);
        //Entities
        entityManager.render(g);
       
    }
    
    public Tile getTile(int x, int y){
        if( x< 0 || y< 0 || x >= width || y >= height)
            return Tile.grassTile;
        
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
            return Tile.dirtTile;
        return t;
    }
    
    private void loadWorld(String path){
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);
        
        tiles = new int[width][height];
        for(int y = 0;y < height;y++){
            for (int x = 0; x < width; x++){
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }

 
    public EntityManager getEntityManager() {
        return entityManager;
    }

    
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
