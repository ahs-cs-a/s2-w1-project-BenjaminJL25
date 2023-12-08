import java.awt.*;
import javax.swing.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.ArrayList;
import java.awt.event.*;
import java.time.Duration;
import java.time.Instant;
public class DodgeTheNight extends JPanel implements MouseListener,MouseMotionListener{
    public static void main(String[] args) {
        new DodgeTheNight();
    }
    ArrayList<Creeps> AllCreeps = new ArrayList<Creeps>() ;

    private JFrame myFrame;
    private int lives;
    private String playerName;
    int points = 0;
    int windowWidth = 700;
    int windowHeight = 700;
    boolean gameon = true;
    private int playerX;
    private int playerY;
    private Instant start = Instant.now();
    public int tick = 10;

    public DodgeTheNight() {
        initializeGame();
        myFrame = new JFrame("Dodge!");
        myFrame.add(this);
        myFrame.setSize(windowWidth, windowHeight);
        myFrame.setVisible(true);
        gameStart();
    }
    public void moveCreeps(double delta){
        for (Creeps c: AllCreeps){
            c.setX(c.getX() + (int)(c.getFinalx()/100*delta - c.getStartx()/100*delta));
            c.setY(c.getY() + (int)(c.getFinaly()/100*delta - c.getStarty()/100*delta));
        }
    }


    public void gameStart() {
//        while (gameon){
//            int initInt = timeSec();
//            double init = timeElapsed();
//            System.out.println(timeSec());
//            while (initInt == timeSec()) {
//                delay(tick);
//
//                System.out.println(timeElapsed() - init);
//                moveCreeps(timeElapsed() - init);
//                repaint();
//                System.out.println(playerX + "d" + playerY);
//                init = timeElapsed();
//            }
//        }
    }
    public void initializeGame() {

    }
    public void mouseClicked(MouseEvent event){

    }
    public void mouseMoved(MouseEvent event){
        this.playerX = event.getX();
        this.playerY = event.getY();

        System.out.println("moved");
        repaint();
    }

    public void mouseDragged(MouseEvent e){ }
    public void mouseReleased (MouseEvent event) {
    }

    public void mousePressed (MouseEvent event) {
    }

    public void mouseEntered (MouseEvent event) {
    }

    public void mouseExited (MouseEvent event) {
    }
    public void paintComponent(Graphics g){
        g.drawRect(10, 10, 100, 100);
    }

    public double timeElapsed(){
        Instant end = Instant.now();

        // Calculate the elapsed time in seconds
        Duration elapsedTime = Duration.between(start, end);
        return elapsedTime.getNano() * 1.0 /  1000000000 + elapsedTime.getSeconds();
    }
    public int timeSec(){
        Instant end = Instant.now();
        Duration elapsedTime = Duration.between(start, end);
        return (int)elapsedTime.getSeconds();
    }
    private void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
