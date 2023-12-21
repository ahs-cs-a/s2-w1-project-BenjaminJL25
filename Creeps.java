import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
public class Creeps {
    private double totalx;
    private double totaly;
    private int finalx;
    private int finaly;
    private int startx;
    private int starty;
    private int diameter;
    public Creeps(){
        this.startx = (int)(Math.random()*2);
        this.starty = (int)(Math.random()*699)+1;
        this.finalx = (int)(Math.random()*699)+1;
        this.finaly = (int)(Math.random()*2);
        this.diameter = (int)(Math.random()*10) + 25;

        if(startx == 0){
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

    public int getDiameter(){
        return this.diameter;
    }
    public int getStartx(){
        return this.startx;
    }
    public int getFinaly(){
        return this.finalx;
    }
    public int getFinalx(){
        return this.finalx;
    }
    public int getStarty(){
        return this.starty;
    }
    public double getTotalx(){
        return this.totalx;
    }
    public double getTotaly(){
        return this.totaly;
    }
    public void setTotalx(double x){
        this.totalx = x;
    }
    public void setTotaly(double y){
        this.totaly =  y;
    }
}
