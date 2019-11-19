package GameEntity.GameTile.Tower;

import GameEntity.Enemy.EnemyMove;
import GameStage.GameStage;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/** Class này để tạo các thông số, thuộc tính chung của Tower*/
public abstract class Tower implements Cloneable{

    public Image texture;
    public static final Tower[] towerList = new Tower[4];

    // khai báo có 3 loại tháp: nomal, sniper và machineGun.
    public static final Tower normalTower = new NormalTower(0, 10, 1 , 20 , 500, 2).getTextureFile("NormalTower");
    public static final Tower sniperTower = new SniperTower(1, 30, 3 , 40 , 500, 2).getTextureFile("SniperTower");
    public static final Tower machineGunTower = new MachineGunTower(2, 70, 2 , 200 , 500, 2).getTextureFile("MachineGunTower");

    public int id;
    public int cost;
    public int range;
    int dame;
    public EnemyMove target; //Mục tiêu của Tower
    public int attackTime; //
    public int attackDelay; //
    public int maxAttackTime;  // thời gian mỗi đợt tấn công
    public int maxAttackDelay;//khoảng dừng giữa mỗi đợt tấn công

    Tower(int id, int cost, int range, int dame, int maxAttackTime, int maxAttackDelay){
        towerList[id] = this;
        this.id = id;
        this.cost = cost;
        this.range = range;
        this.maxAttackTime = maxAttackTime;
        this.attackDelay = maxAttackDelay;
        this.dame = dame;
    }

    //Hàm tính toán
    public EnemyMove checkEnemy(EnemyMove[] enemyMoves , int x , int y ){
        EnemyMove[] enemyInRange = new EnemyMove[enemyMoves.length];
        int towerRadius = this.range;
        int enemyRadius = 1; // enemy nằm trong phạm vi bắn
        int enemyX;
        int enemyY;
        for (int i = 0 ; i  < enemyMoves.length; i++){
            if (enemyMoves[i] != null){
                enemyX = (int) enemyMoves[i].xPos / GameStage.towerSize -x;
                enemyY = (int) enemyMoves[i].yPos / GameStage.towerSize - y;

                if ((enemyX * enemyX) + (enemyY * enemyY) < (towerRadius + enemyRadius) * (towerRadius + enemyRadius)){
                    enemyInRange[i] = enemyMoves[i];
                }
            }
        }
        int totalEnemies = 0 ;
        for (EnemyMove enemyMove : enemyInRange) {
            if (enemyMove != null) {
                totalEnemies++;
            }
        }
        if (totalEnemies >  0){
            int en = new Random().nextInt(totalEnemies);
            int enTaken = 0;
            int i = 0;
            while (true){
                if (enTaken == en && enemyInRange[i] != null) {
                    return enemyInRange[i];
                }
                if (enemyInRange[i] != null){
                    enTaken ++;
                }
                i++;
            }
        }
        return null;
    }
    public abstract void towerFight(EnemyMove enemyMove);
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    Tower getTextureFile(String str){
        this.texture = new ImageIcon("Res/tower/" + str + ".png").getImage();
        return null;

    }
}