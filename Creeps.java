import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
public class Creeps {
    private int x;
    private int y;
    private int finishx;
    int defaultDiameter = 20; //controls the size
    creep creep0 = new creep( 0, Color.black, defaultDiameter); //black dot, slower less visible.
    creep creep1 = new creep( 1, Color.red, defaultDiameter); //red dot, faster more identifiable dot.

    public Creeps(){

        int leftOrRight = (int)(Math.random()*2);
        int startingHeight = (int)(Math.random()*699)+1;
        int finishx = (int)(Math.random()*699)+1;
        int finaly = (int)(Math.random()*2);

            if (leftOrRight == 0) {
                this.x = 0;
                if(startingHeight == 0){
                    this.y = 0;
                }else{
                    this.y = 700;
                }
            } else {
                this.x = 700;
                if(startingHeight == 0){
                    this.y = 0;
                }else{
                    this.y = 700;
                }
            }
    }
}
