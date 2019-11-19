package Account;

import GameStage.GameStage;

/** Khởi tạo các giá trị mặc định*/
public class User {
    public int startingMoney = 100; //khởi tạo số tiền mặc định
    public int startingHealth = 50; //khởi tạo máu mặc định

    public User(GameStage gameStage) {
        gameStage.scene = 0;
    }

    public void createPlayer(){
        StartingInformation startingInformation = new StartingInformation(this);
    }

}
