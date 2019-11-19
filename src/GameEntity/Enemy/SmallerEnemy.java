package GameEntity.Enemy;

import GameEntity.Enemy.Enemy;

/** Class này để tạo Smaller Enemy có máu thấp, tốc độ di chuyển cao.*/
class SmallerEnemy extends Enemy {
    SmallerEnemy(int id, int price, int damage, int health, int point, double speed, double attackSpeed) {
        super(id, price, damage, health, point, speed, attackSpeed);
    }
}