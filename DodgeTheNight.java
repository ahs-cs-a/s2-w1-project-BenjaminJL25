import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;
import java.awt.event.*;

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


    public DodgeTheNight() {
        initializeGame();
        myFrame = new JFrame("Dodge!");
        myFrame.add(this);
        myFrame.setSize(windowWidth, windowHeight);
        myFrame.setVisible(true);
        gameStart();
    }
    public void gameStart() {
        while (gameon){
            System.out.println();
        }
    }
    public void initializeGame() {

    }
    public void mouseClicked(MouseEvent event){

    }
    public void mouseMoved(MouseEvent event){
        this.playerX = event.getX();
        this.playerY = event.getY();
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

}
    private void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
