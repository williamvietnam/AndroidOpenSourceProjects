public class KySu extends CanBo{

    private String specialized;
    public KySu(String name, int age, int gender, String specialized) {
        super(name, age, gender);
        this.specialized = specialized;
    }

    public String getSpecialized() {
        return specialized;
    }

    public void setSpecialized(String specialized) {
        this.specialized = specialized;
    }
}
