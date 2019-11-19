package GameEntity.Enemy;

import GameStage.Direction;
import Map.SpawnMap;

/** class này để tìm đường đi tiếp theo cho Enemy*/
class DirectionEnemy {

    private SpawnMap spawnMap;
    int[][] directionEnemy = new int[22][13];
    int RIGHT = 1;
    int DOWN = 2;
    int LEFT = 3;
    int UP = 4;

    private int lastPos = -1;


    private int xPos;
    private int yPos;
    Direction direction;

    DirectionEnemy(SpawnMap spawnMap){
        this.spawnMap = spawnMap;
        this.xPos = this.spawnMap.getSpawnDirection().getX();
        this.yPos = this.spawnMap.getSpawnDirection().getY();
        calculateDirection();
    }

    private void calculateDirection(){
        while(direction == null){
            nextPos();
        }
    }

    //Tìm đường đi tiếp theo của Enemy
    private void nextPos() {
        for(int i = 1; i < 5; i++){
            if(i != lastPos){
                if(yPos > 0 && i == UP){
                    if(spawnMap.map[xPos][yPos - 1] == 1){
                        lastPos = DOWN;
                        directionEnemy[xPos][yPos] = UP;
                        yPos--;
                        break;
                    }else if(spawnMap.map[xPos][yPos - 1] == 3){
                        direction = new Direction(xPos, yPos);
                        break;
                    }
                }
                if(xPos < 22 && i == RIGHT){
                    if(spawnMap.map[xPos + 1][yPos] == 1){
                        lastPos = LEFT;
                        directionEnemy[xPos][yPos] = RIGHT;
                        xPos++;
                        break;
                    }else if(spawnMap.map[xPos + 1][yPos] == 3){
                        direction = new Direction(xPos, yPos);
                        break;
                    }
                }
                if(xPos > 0 && i == LEFT){
                    if(spawnMap.map[xPos  - 1][yPos] == 1){
                        lastPos = RIGHT;
                        directionEnemy[xPos][yPos] = LEFT;
                        xPos--;
                        break;
                    }else if(spawnMap.map[xPos - 1][yPos] == 3){
                        direction = new Direction(xPos, yPos);
                        break;
                    }
                }
                if(yPos < 13 && i == DOWN){
                    if(spawnMap.map[xPos][yPos + 1] == 1){
                        lastPos = UP;
                        directionEnemy[xPos][yPos] = DOWN;
                        yPos++;
                        break;
                    }else if(spawnMap.map[xPos][yPos + 1] == 3){
                        direction = new Direction(xPos, yPos);
                        break;
                    }
                }
            }
        }
    }
}
