public class Coin {
    private int x;
    private int y;
    public Coin() {
        this.y = (int)(Math.random()*600) + 40;
        this.x = (int)(Math.random()*600) + 40;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
