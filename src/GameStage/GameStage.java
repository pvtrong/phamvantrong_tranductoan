package GameStage;
import Account.User;
import GameEntity.Enemy.Enemy;
import GameEntity.Enemy.ControllEnemy;
import GameEntity.Enemy.EnemyMove;
import GameEntity.GameTile.Road;
import GameEntity.GameTile.Tower.Tower;
import Handler.Key;
import Handler.Mouse;
import Map.SpawnMap;
import Map.LoadMap;
import Music.music;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.IOException;


public class GameStage extends JPanel implements Runnable {
    private static User user;
    private Frame frame;
    private int handXPos = 0;
    private int handYPos = 0;

    private int hand = 0;
    private LoadMap loadMap;
    public int scene ;
    /**Có 6 loại draw
     * 0: cỏ
     * 1: đất (đường đi enemy)
     * 2: nơi sinh sản enemy
     * 3: nơi enemy biến mất
     * 4: núi
     * 5: cây
     * */
    public static Image[] draw = new Image[6];
    private Road road;
    private boolean running  = false;
    private Tower choose;
    private SpawnMap spawnMap;
    private ControllEnemy controllEnemy;
    private static Tower[][] towerMap = new Tower[22][13]; // mảng các tháp trong map
    public static int towerSize = 40; // kích cỡ các ô map
    public static EnemyMove[] enemyMap = new EnemyMove[50]; // mảng enemy
    private int [][] map = new int[22][13]; // khởi tạo map 22 x 13
    private music audioPlayer = new music("src/Music/111.wav"); // tạo music
    private int frameWidth;
    private int frameHeight;

    //Tạo cửa sổ Window
    public GameStage(Frame frame) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.frame  = frame;
        this.frame.addKeyListener(new Key(this));
        this.frame.addMouseListener(new Mouse(this));
        this.frame.addMouseMotionListener(new Mouse(this));
        frameWidth = this.frame.getWidth();
        frameHeight = this.frame.getHeight();
        Thread thread = new Thread(this);
        thread.start();
    }

    //Hàm vẽ đồ họa cho game
    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.clearRect(0 , 0 , this.frameWidth , this.frameHeight );

        if (scene == 0){
            // màn hình lúc start game
            graphics.drawImage(new ImageIcon("Res/background_start.png").getImage(), 0, 0, frameWidth, frameHeight, null);
        }
        else if (scene ==1 ){
            graphics.drawImage(new ImageIcon("Res/background.png").getImage(), 0, 0, frameWidth, frameHeight, null);
            graphics.setColor(Color.BLACK);
            // vẽ map
            for (int i = 0 ; i < 22 ; i++){
                for (int j = 0 ; j < 13; j ++){
                    // vẽ các ô map lên background
                    graphics.drawImage(draw[map[i][j]], 40 + i * 40, j*40  + 40, 40, 40, null);
                    graphics.drawRect(40 + i*40, 40 + j*40 , 40, 40);
                }
            }

            //Enemies
            for (EnemyMove enemyMove : enemyMap) {
                if (enemyMove != null) {
                    graphics.drawImage(enemyMove.getEnemy().texture, (int) enemyMove.xPos + towerSize, (int) enemyMove.yPos + towerSize, towerSize, towerSize, null);
                }
            }
            // Health + Money
            graphics.setColor(Color.YELLOW);
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            graphics.drawString("Health = " + user.startingHealth , 1093  , 566);
            graphics.drawString("Money = " + user.startingMoney , 1093  , 648);

            //tower list: hiển thị các tháp trên ô chọn lựa
            if(Tower.towerList[0] != null) {
                graphics.drawImage(Tower.towerList[0].texture,1059 , 170 , 50 ,50, null);
                if(Tower.towerList[0].cost > user.startingMoney){

                    // nếu hết tiền thì ko mua được tháp 0 và tháp đó chuyển sang màu đỏ
                    graphics.setColor(new Color(255, 0, 0, 100));
                    graphics.fillRect(1059, 170, 50, 50);
                }
            }
            if(Tower.towerList[1] != null) {
                graphics.drawImage(Tower.towerList[1].texture,1060 , 250 , 50 ,50, null);
                if(Tower.towerList[1].cost > user.startingMoney){

                    // nếu hết tiền thì ko mua được tháp 1 và tháp đó chuyển sang màu đỏ
                    graphics.setColor(new Color(255, 0, 0, 100));
                    graphics.fillRect(1060, 250, 50, 50);
                }
            }
            if(Tower.towerList[2] != null) {
                graphics.drawImage(Tower.towerList[2].texture,1061 , 331   , 50 ,50, null);
                if(Tower.towerList[2].cost > user.startingMoney){

                    // nếu hết tiền thì ko mua được tháp và 2 tháp đó chuyển sang màu đỏ
                    graphics.setColor(new Color(255, 0, 0, 100));
                    graphics.fillRect(1061, 331, 50, 50);
                }
            }

            //Vẽ các tháp trên map
            for(int  x = 0; x < 22; x++){
                for(int y = 0; y < 13; y++){
                    if(towerMap[x][y] != null){

                        // khi trỏ chuột vào tower thì mới có vòng tròn
                        if (choose == towerMap[x][y]){
                            graphics.setColor(Color.RED);
                            graphics.drawOval(40 + x * 40 - towerMap[x][y].range * 40, 40 + y * 40 - towerMap[x][y].range * 40,40 + towerMap[x][y].range * 40* 2,40 +  towerMap[x][y].range * 40 * 2);
                            graphics.setColor(new Color(0, 0, 0, 0));
                            graphics.fillOval(40 + x * 40 - towerMap[x][y].range * 40, 40 + y * 40 - towerMap[x][y].range * 40,40 + towerMap[x][y].range * 40* 2, 40 + towerMap[x][y].range * 40 * 2);
                        }

                        // vẽ tháp
                        graphics.drawImage(Tower.towerList[towerMap[x][y].id].texture, 40 + x * 40, 40 + y * 40, 40, 40, null);
                        // tạo đạn màu blue cho tháp
                        if (towerMap[x][y].target != null){
                            graphics.setColor(Color.BLUE);
                            graphics.drawLine(60 + x * 40, 40 + y * 40  + 20, 60 +(int) towerMap[x][y].target.xPos ,60 + (int)towerMap[x][y].target.yPos);
                        }

                    }
                }
            }

            //Vẽ tháp
            if(hand != 0 && Tower.towerList[hand - 1] != null){
                graphics.drawImage(Tower.towerList[hand - 1].texture, this.handXPos - 40/2,this.handYPos - 40/2, 40, 40, null);
            }
            if(user.startingHealth <= 0){
                // nếu hết healht thì xóa Screen và load ảnh "you lose"
                graphics.drawImage(new ImageIcon("Res/youlose.png").getImage(), 0, 0, frameWidth, frameHeight, null);
            }
        } else  {
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0,0,this.frameWidth, this.frameHeight);
        }
    }
    //Chỉ load game 1 lần duy nhất
    private void load(){
        user = new User(this);
        loadMap = new LoadMap();
        road = new Road(this);
        /** Load draw:
         * 0: cỏ
         * 1: đường đi enemy
         * 2: xuất phát của enemy
         * 3: kết thúc của enemy
         * 4: núi
         * 5: cây
         * */
        for (int x = 0; x < 6; x ++) {
            draw[x] = new ImageIcon("Res/draw.png").getImage();
            draw[x] = createImage(new FilteredImageSource(draw[x].getSource(), new CropImageFilter(x * 50, 0, 50, 50)));
        }
        running = true;
    }

    // Hàm bắt đầu
    private void start(User user, String map){
        user.createPlayer();
        this.spawnMap  = loadMap.getMap(map);
        this.map = this.spawnMap.map;
        this.spawnMap.findSpawnDirection();
        this.controllEnemy = new ControllEnemy(this.spawnMap);

        this.scene =1 ;
        this.road.roadNumber = 0;
    }

    @Override
    public void run(){
        long lastFrame = System.currentTimeMillis();
        load();
        while (running){
            this.audioPlayer.play();
            repaint();
            if (System.currentTimeMillis()- 1000 >= lastFrame){
                lastFrame = System.currentTimeMillis();
            }
            update();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }

    // update trạng thái của enemy
    private void enemyFix(){
        for (int i = 0; i < enemyMap.length; i++) {
            if (enemyMap[i] != null){
                if (!enemyMap[i].isAttack()) {
                    ControllEnemy.getRoadOfEnemy().move(enemyMap[i]);
                }
                enemyMap[i] = enemyMap[i].update(user); // kieemr tra xem enemy có chết hay chưa
            }
        }
    }

    // Update trạng thái cả map
    private void update(){
        towerFix();
        enemyFix();
        if(road.isRoadSpawning()){
            road.spawnEnemies();
        }
    }

    // Update trạng thái của các tháp
    private void towerFix() {
        for (int i = 0 ; i < 22; i ++ ){
            for (int j =0 ; j < 13 ; j ++){
                if (towerMap[i][j] != null) towerFight(i ,j);
            }
        }
    }


    // Hàm tháp tấn công enemy
    private void towerFight(int x, int y) {
        if (towerMap[x][y].target == null){
            if (towerMap[x][y].attackDelay >= towerMap[x][y].maxAttackDelay){
                EnemyMove currentEnemy = towerMap[x][y].checkEnemy(enemyMap,x, y);
                if (currentEnemy != null ){
                    towerMap[x][y].towerFight(currentEnemy);

                    towerMap[x][y].target = currentEnemy;
                    towerMap[x][y].attackTime = 0;
                    towerMap[x][y].attackDelay = 0;
                }
            }
            else {
                towerMap[x][y].attackDelay += 1;
            }
        } else {
            if (towerMap[x][y].attackTime < towerMap[x][y].maxAttackTime){
                towerMap[x][y].attackTime += 1;
            } else {
                towerMap[x][y].target = null;
            }
        }
    }
    // Sinh sản Enemy
    public void spawnEnemy(int x){
        for(int i = 0; i < enemyMap.length; i++){
            if(enemyMap[i] == null){
                enemyMap[i] = new EnemyMove(Enemy.enemyList[x], spawnMap.getSpawnDirection());
                break;
            }
        }
    }
    // Tính vị trí tháp trên map
    private void replaceTower(int x, int y) throws CloneNotSupportedException {
        int xPos = x /40;
        int yPos = y /40;
        if(xPos > 0 && xPos <= 22 && yPos <= 13 && yPos > 0){
            xPos -= 1;
            yPos -= 1;
            if(towerMap[xPos][yPos] == null && map[xPos][yPos] == 0){
                user.startingMoney =  user.startingMoney - Tower.towerList[hand - 1].cost;
                towerMap[xPos][yPos] = (Tower) Tower.towerList[hand - 1].clone();
                choose = towerMap[xPos][yPos];
            }
        }
    }
    //Copy tower gốc vào seclec
    private void chooseTower(int x, int y){
        int xPos = (x -40)/ 40;
        int yPos = (y-40) /40;

        if (xPos >=0 && xPos < 22 && yPos >= 0 && yPos < 13){
            if (towerMap[xPos][yPos] != null && map[xPos][yPos] == 0){
                choose = towerMap[xPos][yPos];
            }
        }
    }

    // giữ chuột kéo tháp vào map
    public class PressMouse{
        boolean mouseDown = false;

        public void mouseDown(MouseEvent e) throws CloneNotSupportedException {
            mouseDown = true;
            if(hand != 0){
                replaceTower(e.getXOnScreen(), e.getYOnScreen());
                hand = 0;
            } else {
                chooseTower(e.getX() , e.getY());
            }
            updateMouse(e);
        }

        void updateMouse(MouseEvent e){
            // chọn và mua tháp tương ứng
            //loại 1 ô 1 nomal
            if(scene == 1 && mouseDown && hand == 0 && e.getXOnScreen() >=1059  &&  e.getXOnScreen() < 1109 && e.getYOnScreen() >= 170 && e.getYOnScreen() < 220 && user.startingMoney >= Tower.towerList[0].cost){
                hand = 1;
            }

            // loại 2 ô 2 sniper
            else if(scene == 1 && mouseDown && hand == 0 && e.getXOnScreen() >= 1060 &&  e.getXOnScreen() < 1110 && e.getYOnScreen() >= 250 && e.getYOnScreen() < 300 && user.startingMoney >= Tower.towerList[1].cost){
                hand = 2;
            }

            //loại 3 ô 3 power
            else if(scene == 1 && mouseDown && hand == 0 && e.getXOnScreen() >= 1061 &&  e.getXOnScreen() <  1111 && e.getYOnScreen() >= 331 && e.getYOnScreen() < 381 && user.startingMoney >= Tower.towerList[2].cost){
                hand = 3;
            }
        }

        public void mouseMoved(MouseEvent e) {
            handXPos = e.getXOnScreen();
            handYPos = e.getYOnScreen();
        }
    }
    // chọn key, map
    public class KeyTyped{
        public void keyESC(){
            running = false;
        }

        public void keyENTER()   {
            road.nextRoad();
        }

        public void startMap1() {
            start(user, "map1");
        }

        public void startMap2() {
            start(user, "map2");
        }

        public void startMap3() {
            start(user, "map3");
        }

    }
}