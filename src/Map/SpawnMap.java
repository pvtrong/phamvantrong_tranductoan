package Map;

import GameStage.Spawner;

/**Class sinh sản enemy */
public class SpawnMap {
     public int[][] map;

     private Spawner spawner;
     // Tìm vị trí sinh sản enemy
     public void findSpawnDirection(){
          for(int i = 0; i < map.length; i++){
               for(int j = 0; j < map[0].length; j++){
                    if(map[i][j] == 2){
                         spawner = new Spawner(i, j);
                    }
               }
          }
     }

     public Spawner getSpawnDirection() {
          return spawner;
     }

     public void setSpawnPoint(Spawner spawner) {
          this.spawner = spawner;
     }
}
