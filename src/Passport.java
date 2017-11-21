
public class Passport {

    private String seria;
    private int number;

    public Passport(String seria, int number) {
        this.seria = seria;
        this.number = number;
    }

    public void setSeria(String seria) {
        this.seria = seria;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Passport() {

    }

    public String getSeria() {
        return seria;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passport)) return false;

        Passport passport = (Passport) o;

        if (getNumber() != passport.getNumber()) return false;
        return getSeria().equals(passport.getSeria());

    }

    @Override
    public int hashCode() {
        int result = getSeria().hashCode();
        result = 31 * result + getNumber();
        return result;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "seria='" + seria + '\'' +
                ", number=" + number +
                '}';
    }
}
