package GameEntity.Enemy;

/** Class này để tạo thêm Boss Enemy có máu rất cao, tốc độ di chuyển thấp, có phần thưởng lớn*/
class BossEnemy extends Enemy{
    BossEnemy(int id, int price, int damage, int health, int point, double speed, double attackSpeed) {
        super(id, price, damage, health, point, speed, attackSpeed);
    }
}
