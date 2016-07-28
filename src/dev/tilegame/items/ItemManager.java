package dev.tilegame.items;

import dev.tilegame.Handler;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This item manager class is not intended to handle inventory items.
 * It only handles in world items
 * 
 */

public class ItemManager {

    private Handler handler;
    private ArrayList<Item> items;
    
    public ItemManager(Handler handler){
        this.handler = handler;
        items = new ArrayList<Item>();
    }
    
    public void tick(){
        Iterator<Item> it = items.iterator();
        while(it.hasNext()){
            Item i = it.next();
            i.tick();
            if(i.getCount() == Item.PICKED_UP)
            it.remove();
        }
    }
    
    public void render(Graphics g){
        for(Item i : items)
            i.render(g);
    }
    
    public void addItem(Item i){
        i.setHandler(handler);
        items.add(i);
        
    }

   //GETTERS/SETTERS
    public Handler getHandler() {
        return handler;
    }
    
}
