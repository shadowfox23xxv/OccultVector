package dev.OccultVector.display;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Display {
    
    private JFrame frame;
    private Canvas canvas;
    
    private String title;
    private int width, height;
    
    //constructor
    public Display(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        
        createDisplay(); //JFrame initiallization seperated to avoid cluttering constructor
    }
    
    private void createDisplay(){
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ensures program closes when window is closed
        frame.setResizable(false);//window is a set size
        frame.setLocationRelativeTo(null);//sets window to open in center
        frame.setVisible(true);//JFrame is not visible by default; changes JFrame to be visible
        
        canvas= new Canvas();//create new canvas object to draw graphics to
        //next three lines ensure that canvas stays the size we want
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);//ensures only JFrame gets focus so keylistener functions properly
        
        frame.add(canvas);//add canvas to JFrame frame
        frame.pack();//resize the frame slightly to accomodate the cavas
    }
    
    public Canvas getCanvas(){
        return canvas;
    }
    
    public JFrame getFrame(){
        return frame;
    }
}
