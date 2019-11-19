package Account;

/**Class này để thêm thông tin thông số ban đầu */
public class StartingInformation {
    private int health;
    private int money;
    StartingInformation(User user){
        this.money = user.startingMoney; //Tiền ban đầu
        this.health = user.startingHealth; //Máu ban đầu
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getHealth() {
        return health;
    }

    public int getMoney() {
        return money;
    }
}
