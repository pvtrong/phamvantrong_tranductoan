package GameEntity.GameTile;

import GameEntity.Enemy.Enemy;
import GameStage.GameStage;
import java.util.Arrays;
import java.util.Random;

/** */
public class Road {
    private GameStage gameStage;
    public int roadNumber = 0;
    private int currentPoints;
    private int pointThisRound ;
    private  int currentDelay = 0;
    private boolean roadSpawning;
    public boolean isRoadSpawning() {
        return roadSpawning;
    }

    public void setRoadSpawning(boolean roadSpawning) {
        this.roadSpawning = roadSpawning;
    }



    public Road(GameStage gameStage){
        this.gameStage = gameStage;
    }

    public void nextRoad(){
        this.roadNumber++;
        this.pointThisRound = this.roadNumber * 10;
        this.currentPoints = 0;
        this.roadSpawning = true;
        Arrays.fill(GameStage.enemyMap, null);
    }

    // sinh sản enemy
    public void spawnEnemies(){
        if(this.currentPoints < this.pointThisRound){
            int spawnRate = 2000;
            if(currentDelay < spawnRate){
                currentDelay++;
            }else {
                currentDelay = 0;
                int[] listEnemy = new int[Enemy.enemyList.length];
                int index = 0;

                for (int i = 0 ; i < Enemy.enemyList.length ; i++){
                    if (Enemy.enemyList[i] != null){
                        if (Enemy.enemyList[i].points + currentPoints <= this.pointThisRound && Enemy.enemyList[i].points <= this.pointThisRound){
                            listEnemy[index] = Enemy.enemyList[i].id;
                            index ++;
                        }
                    }
                }
                int enemyID = new Random().nextInt(index);
                this.currentPoints += Enemy.enemyList[enemyID].points;
                this.gameStage.spawnEnemy(listEnemy[enemyID]);
            }
        }else{
            this.roadSpawning = false;
        }
    }

}
