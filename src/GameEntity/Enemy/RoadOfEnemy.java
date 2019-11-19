package GameEntity.Enemy;

import GameStage.GameStage;

/** Class này để tính toán di chuyển của Enemy*/
public class RoadOfEnemy extends ControllEnemy {

    RoadOfEnemy(int id){
        super(id);
    }

    public void move(EnemyMove enemyMove){
        if(enemyMove.xPos %  GameStage.towerSize == 0 && enemyMove.yPos % GameStage.towerSize == 0 &&
                enemyMove.getDirectionPosX() == enemyMove.xPos / GameStage.towerSize && enemyMove.getDirectionPosY()
                == enemyMove.yPos / GameStage.towerSize){
            if(enemyMove.getDirectionPosX() == getDirectionPosX() && enemyMove.getDirectionPosY() == getDirectionPosY()){ }
            else{
                if(this.getDirectionEnemy().directionEnemy[enemyMove.getDirectionPosX()][enemyMove.getDirectionPosY()] == this.getDirectionEnemy().UP){
                    int temp = enemyMove.getDirectionPosY();
                    enemyMove.setDirectionPosY(temp - 1);
                }else

                if(this.getDirectionEnemy().directionEnemy[enemyMove.getDirectionPosX()][enemyMove.getDirectionPosY()] == this.getDirectionEnemy().DOWN){
                    int temp = enemyMove.getDirectionPosY();
                    enemyMove.setDirectionPosY(temp + 1);
                }else

                if(this.getDirectionEnemy().directionEnemy[enemyMove.getDirectionPosX()][enemyMove.getDirectionPosY()] == this.getDirectionEnemy().RIGHT){
                    int temp = enemyMove.getDirectionPosX();
                    enemyMove.setDirectionPosX(temp + 1);
                }else

                if(this.getDirectionEnemy().directionEnemy[enemyMove.getDirectionPosX()][enemyMove.getDirectionPosY()] == this.getDirectionEnemy().LEFT){
                    int temp = enemyMove.getDirectionPosX();
                    enemyMove.setDirectionPosX(temp - 1);
                }
            }
        }else{
            // Tốc độ di chuyển Enemy
            double xPos = enemyMove.xPos / GameStage.towerSize;
            double yPos = enemyMove.yPos / GameStage.towerSize;
            if(xPos > enemyMove.getDirectionPosX()) enemyMove.xPos -= enemyMove.enemy.speed/48;
            if(xPos < enemyMove.getDirectionPosX()) enemyMove.xPos += enemyMove.enemy.speed/48;
            if(yPos > enemyMove.getDirectionPosY()) enemyMove.yPos -= enemyMove.enemy.speed/48;
            if(yPos < enemyMove.getDirectionPosY()) enemyMove.yPos += enemyMove.enemy.speed/48;
        }
    }
}
