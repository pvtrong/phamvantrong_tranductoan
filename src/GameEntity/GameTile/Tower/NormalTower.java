package GameEntity.GameTile.Tower;

import GameEntity.Enemy.EnemyMove;

/** Class này để tạo Normal Tower có chỉ số nằm ở mức cơ bản. */
public class NormalTower extends Tower{
    NormalTower(int id, int cost, int range, int dame, int maxAttackTime, int maxAttackDelay) {
        super(id, cost, range, dame, maxAttackTime, maxAttackDelay);
    }

    @Override
    public void towerFight(EnemyMove e) {
        e.setHealth(e.getHealth() - this.dame);
    }
}