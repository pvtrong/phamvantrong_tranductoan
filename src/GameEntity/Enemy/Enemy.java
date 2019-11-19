package GameEntity.Enemy;

import javax.swing.*;
import java.awt.*;

/** Class này để khởi tạo các thuộc tính chung cho Enemy*/
public class Enemy {
    public static final Enemy[] enemyList =  new Enemy[4]; //Có 4 loại Enemy
    //Tạo NormalEnemy và các chỉ số mặc định
    public static final Enemy normalEnemy = new NormalEnemy(0, 5, 2, 500,1, 6, 4).getTextureFile("NomalEnemy.png");
    //Tạo Tanker Enemy và các chỉ số mặc định
    public static final Enemy tankerEnemy = new TankerEnemy(1, 10, 2, 1000,2, 6, 4).getTextureFile("TankerEnemy.png");
    //Tạo Smaller Enemy và các chỉ số mặc định
    public static final Enemy smallerEnemy = new SmallerEnemy(2 , 15 , 2, 500,5, 12, 4).getTextureFile("SmallerEnemy.png");
    //Tạo bossEnemy và các chỉ số mặc định
    public static final Enemy bossEnemy = new BossEnemy(3 , 20 , 2 , 5000   ,10, 6 , 4).getTextureFile("BossEnemy.png");
    // Các giá trị cần thiết cho Enemy
    public Image texture = null;
    public int id;
    int price;
    double speed;
    private double attackSpeed;
    private int damage;
    int health;
    public int points;

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public int getDamage() {
        return damage;
    }

    public Enemy(int id, int price, int damage, int health , int point, double speed, double attackSpeed){
        enemyList[id] = this;
        this.id = id;
        this.price = price;
        this.damage = damage;
        this.health = health;
        this.points = point;
        this.speed = speed;
        this.attackSpeed = attackSpeed;
    }

    Enemy getTextureFile(String str){
        this.texture = new ImageIcon("Res/enemy/" + str).getImage();
        return this;
    }
}
