package GameEntity.Enemy;

import GameEntity.Enemy.Enemy;

/** Class này để tọa Tanker Enemy có máu cao, tốc độ di chuyển thấp. */
class TankerEnemy extends Enemy {

    TankerEnemy(int id, int price, int damage, int health, int point, double speed, double attackSpeed) {
        super(id, price, damage, health, point, speed, attackSpeed);
    }
}