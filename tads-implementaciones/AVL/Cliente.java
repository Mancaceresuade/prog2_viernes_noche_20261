public class Cliente implements Comparable<Cliente> {
    private double scoring;

    public Cliente(double scoring) {
        this.scoring = scoring;
    }
    public double getScoring() {
        return scoring;
    }
    public void setScoring(double scoring) {
        this.scoring = scoring;
    }
    @Override
    public String toString() {
        return "Cliente{" +
                "scoring=" + scoring +
                '}';
    }
    @Override
    public int compareTo(Cliente o) {
        return Double.compare(this.scoring, o.scoring);
    }    
}