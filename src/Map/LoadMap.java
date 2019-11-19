package Map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**Class load map*/
public class LoadMap {

    private SpawnMap spawnMap = new SpawnMap();
    public SpawnMap getMap(String fileName) {
        try {
            // load map: bao gồm cỏ ,... núi , cây
            FileInputStream fileInputStream = new FileInputStream("src/Map/" + fileName + ".txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            Scanner scanner = new Scanner(inputStreamReader);
            spawnMap.map = new int[22][13];
            int x = 0;
            int y = 0;
            while(scanner.hasNext()){
                spawnMap.map[x][y] = scanner.nextInt();
                if(x < 21){
                    x++;
                }else {
                    y++;
                    x = 0;
                }
            }
            return spawnMap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
