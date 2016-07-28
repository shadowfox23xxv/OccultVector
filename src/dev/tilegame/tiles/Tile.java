package dev.tilegame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Tile {
    //Static stuff here
    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile dirtTile = new DirtTile(1);
    public static Tile rockTile = new RockTile(2);
    
    //Class
    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
    
    protected BufferedImage texture;
    protected final int id;
    
    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;
        tiles[id] = this;
    }
    
    public boolean isSolid(){
        return false;
    }
    
    public void tick(){
        
    }
    
    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
        
    }
    
    public int getID(){
        return id;
    }
    
    
}
