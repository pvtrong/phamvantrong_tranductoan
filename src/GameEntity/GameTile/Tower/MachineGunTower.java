package GameEntity.GameTile.Tower;

import GameEntity.Enemy.EnemyMove;

/**  Class này để tạo Machine Gun Tower có tầm bắn gần nhưng tốc độ bắn cao, sát thương cũng tương đối lớn */
public class MachineGunTower extends Tower {
        MachineGunTower(int id, int cost, int range, int dame, int maxAttackTime, int maxAttackDelay) {
            super(id, cost, range, dame, maxAttackTime, maxAttackDelay);
        }

        @Override
        public void towerFight(EnemyMove e) {
            e.setHealth(e.getHealth() - this.dame);
        }
}
