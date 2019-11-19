package GameEntity.Enemy;

/** Class này để Tạo 1 Normal Enemy có chỉ số nằm ở mức cơ bản. */
class NormalEnemy extends Enemy{
    NormalEnemy(int id, int price, int damage, int health, int point, double speed, double attackSpeed) {
        super(id, price, damage, health, point, speed, attackSpeed);
    }
}
