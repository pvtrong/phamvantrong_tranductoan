package GameEntity.Enemy;

import Account.User;
import GameStage.GameStage;
import GameStage.Spawner;
import java.util.Arrays;

/**Class này để di chuyển Enemy*/
public class EnemyMove {
    Enemy enemy;
    public double xPos;
    public double yPos;
    private boolean attack;
    private int directionPosX;
    private int directionPosY;
    private int health;

    public Enemy getEnemy() {
        return enemy;
    }

    public boolean isAttack() {
        return attack;
    }

    int getDirectionPosX() {
        return directionPosX;
    }

    void setDirectionPosX(int routePosX) {
        this.directionPosX = routePosX;
    }

    int getDirectionPosY() {
        return directionPosY;
    }

    void setDirectionPosY(int directionPosY) {
        this.directionPosY = directionPosY;
    }

    public int getHealth() {
        return health;
    }

    public void setAttack() {}

    public void setHealth(int health) {
        this.health = health;
    }

    //Các trạng thái tác động vào Enemy khi di chuyển
    public EnemyMove(Enemy enemy, Spawner spawner){
        this.enemy = enemy;
        this.directionPosX = spawner.getX();
        this.directionPosY = spawner.getY();
        this.xPos = spawner.getX() * GameStage.towerSize ;
        this.yPos = spawner.getY() * GameStage.towerSize ;
        this.attack = false;
        this.health = enemy.health;
    }

    //update tiền và máu sau mỗi lần bắn chết Enemy hay là Enemy về đích
    public EnemyMove update(User user){
        EnemyMove currentEnemy = this;
        if(currentEnemy.health <= 0 && this.xPos != 760 && this.yPos != 0){

            user.startingMoney = user.startingMoney + this.getEnemy().price;
            return null;
        }
        else if (currentEnemy.health > 0 && this.xPos == 760 && this.yPos == 0){
            user.startingHealth = user.startingHealth - this.getEnemy().price;
            user.startingMoney = user.startingMoney + this.getEnemy().price;
            if (user.startingHealth <= 0) {
                System.out.println("You lose");
                Arrays.fill(GameStage.draw, null);
                Arrays.fill(GameStage.enemyMap, null);
            }
            return null;
        }
        else return currentEnemy;
    }

}
