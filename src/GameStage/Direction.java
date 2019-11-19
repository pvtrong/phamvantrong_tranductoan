package GameStage;

/**Class lấy vị trí x và y của map */
public class Direction {
    private int xPos;
    private int yPos;
    private int RIGHT = 1;
    private int DOWN = 2;
    private int LEFT = 3;
    private int UP = 4;

    public int getXPos() {
        return xPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public int getRIGHT() {
        return RIGHT;
    }

    public void setRIGHT(int RIGHT) {
        this.RIGHT = RIGHT;
    }

    public int getDOWN() {
        return DOWN;
    }

    public void setDOWN(int DOWN) {
        this.DOWN = DOWN;
    }

    public int getLEFT() {
        return LEFT;
    }

    public void setLEFT(int LEFT) {
        this.LEFT = LEFT;
    }

    public int getUP() {
        return UP;
    }

    public void setUP(int UP) {
        this.UP = UP;
    }

    public Direction(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }
}
