package dev.tilegame.states;

import dev.tilegame.Handler;
import dev.tilegame.gfx.Assets;
import dev.tilegame.ui.ClickListener;
import dev.tilegame.ui.UIImageButton;
import dev.tilegame.ui.UIManager;
import java.awt.Color;
import java.awt.Graphics;


public class MenuState extends State {

    private UIManager uiManager;
    
    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        uiManager.addObject(new UIImageButton(200, 200, 128, 64, Assets.btn_start, new ClickListener(){//lambda expression to define onCLick behavior for this button only
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));
    }

    @Override
    public void tick() {
       uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
       uiManager.render(g);
    }

}
