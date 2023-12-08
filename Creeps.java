import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
public class Creeps {
    private int x;
    private int y;
    private int finalx;
    private int finaly;
    private int startx;
    private int starty;

    int defaultDiameter = 20; //controls the size
//    Creeps creep0 = new Creeps( 0, Color.black, defaultDiameter); //black dot, slower less visible.
//    Creeps creep1 = new Creeps( 1, Color.red, defaultDiameter); //red dot, faster more identifiable dot.

    public Creeps(){
        this.startx = (int)(Math.random()*2);
        this.starty = (int)(Math.random()*699)+1;
        this.finalx = (int)(Math.random()*699)+1;
        this.finaly = (int)(Math.random()*2);


        if(startx== 0){
            this.startx = 0;
            } else {
            this.startx = 700;
        }
        if(this.finaly == 0){
            this.finaly = 0;
        }else{
            this.finaly = 700;
        }
    }
}
