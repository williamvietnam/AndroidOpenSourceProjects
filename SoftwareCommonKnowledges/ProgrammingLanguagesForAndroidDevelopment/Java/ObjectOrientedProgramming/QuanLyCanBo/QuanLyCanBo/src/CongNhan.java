public class CongNhan extends CanBo {
    private int level; // level: 1 --> 10;

    public CongNhan(String name, int age, int gender, int level) {
        super(name, age, gender);
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
