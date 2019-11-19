package GameEntity.Enemy;

import Map.SpawnMap;

/** Class này để lấy vị trí trong map 22 x 13 */
public class ControllEnemy {

    private static DirectionEnemy directionEnemy;
    private static RoadOfEnemy roadOfEnemy;
    private static int directionPosX;
    private static int directionPosY;
    public int id;

    public ControllEnemy(SpawnMap spawnMap){
        directionEnemy = new DirectionEnemy(spawnMap);

        directionPosX = directionEnemy.direction.getXPos();
        directionPosY = directionEnemy.direction.getYPos();

        roadOfEnemy = new RoadOfEnemy(0);
    }

    ControllEnemy(int id){
        this.id = id;
    }

    public DirectionEnemy getDirectionEnemy(){
        return directionEnemy;
    }

    public static void setDirectionEnemy(DirectionEnemy directionEnemy) {
        ControllEnemy.directionEnemy = directionEnemy;
    }

    public static RoadOfEnemy getRoadOfEnemy() {
        return roadOfEnemy;
    }

    public static void setRoadOfEnemy(RoadOfEnemy roadOfEnemy) {
        ControllEnemy.roadOfEnemy = roadOfEnemy;
    }

    public static int getDirectionPosX() {
        return directionPosX;
    }

    public static void setDirectionPosX(int directionPosX) {
        ControllEnemy.directionPosX = directionPosX;
    }

    public static int getDirectionPosY() {
        return directionPosY;
    }

    public static void setDirectionPosY(int directionPosY) {
        ControllEnemy.directionPosY = directionPosY;
    }
}
