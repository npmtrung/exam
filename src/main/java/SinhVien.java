public class SinhVien {
    protected int ID;
    protected String name;
    protected String email;
    protected double bonus;
    protected double report;
    protected double app;
    protected double lt;
    protected double TB;

    public SinhVien() {
    }

    public SinhVien(int ID, String name, String email, double bonus, double report, double app, double lt) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.bonus = bonus;
        this.report = report;
        this.app = app;
        this.lt = lt;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getReport() {
        return report;
    }

    public void setReport(double report) {
        this.report = report;
    }

    public double getApp() {
        return app;
    }

    public void setApp(double app) {
        this.app = app;
    }

    public double getLt() {
        return lt;
    }

    public void setLt(double lt) {
        this.lt = lt;
    }

    public double getTB() {
        return TB;
    }

    public void setTB(double TB) {
        this.TB = TB;
    }

    @Override
    public String toString() {
        return ID + "," + name + "," + email + "," + Math.round(bonus) + "," + Math.round(report) + "," + Math.round(app) + "," + lt + "," + TB;
    }
}


