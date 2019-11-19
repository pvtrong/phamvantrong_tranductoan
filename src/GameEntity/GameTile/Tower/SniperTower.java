package GameEntity.GameTile.Tower;

import GameEntity.Enemy.Enemy;
import GameEntity.Enemy.EnemyMove;
import GameEntity.GameTile.Tower.Tower;

/** Class này để tạo Sniper Tower có tầm bắn xa, sát thương lớn, nhưng tốc độ bắn thấp. */
public class SniperTower extends Tower {
    SniperTower(int id, int cost, int range, int dame, int maxAttackTime, int maxAttackDelay) {
        super(id, cost, range, dame, maxAttackTime, maxAttackDelay);
    }

    @Override
    public void towerFight(EnemyMove e) {
        e.setHealth(e.getHealth() - this.dame);
    }
}