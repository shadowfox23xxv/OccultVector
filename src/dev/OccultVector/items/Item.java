package dev.OccultVector.items;

import dev.OccultVector.Handler;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Item {

    //Handler
    public static Item[] items = new Item[256];
    //items should be added just like in the static handler section of the tile class
    //generating new items in game should be done by accessing the items array at the 
    //appropriate ID based index and calling the createNew() method to generate a copy to 
    //be used.
    
    
    //CLASS
    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32, PICKED_UP = -1;
    
    private Handler handler;
    private BufferedImage texture;
    private String name;
    private int id;
    private int x;
    private int y;
    private int count;
    
    public Item(BufferedImage texture, String name, int id){
        this.texture = texture;
        this.name = name;
        this.id = id;
        count = 1;
        
        items[id] = this;
    }
    
    public void tick(){
        
    }
    
    public void render(Graphics g){
        if(getHandler() == null)
            return;
        render(g, (int) (getX() - getHandler().getGameCamera().getxOffset()), (int) (getY() - getHandler().getGameCamera().getyOffset()));
    }
    
    //this render method allows us to render the item in an inventory slot on screen, rather than
    //the items in-world location
    public void render(Graphics g, int x, int y){
        g.drawImage(getTexture(), x, y, ITEMWIDTH, ITEMHEIGHT, null);
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public Item createNew(int x, int y){
        Item i = new Item(texture, name, id);
        i.setPosition(x, y);
        return i;
    }
    
    //GETTERS/SETTERS
    /**
     * @return the handler
     */
    public Handler getHandler() {
        return handler;
    }

    /**
     * @param handler the handler to set
     */
    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    /**
     * @return the texture
     */
    public BufferedImage getTexture() {
        return texture;
    }

    /**
     * @param texture the texture to set
     */
    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }
}
