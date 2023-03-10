public class NhanVien extends CanBo{
    private String job;
    public NhanVien(String name, int age, int gender, String job) {
        super(name, age, gender);
        this.job = job;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
