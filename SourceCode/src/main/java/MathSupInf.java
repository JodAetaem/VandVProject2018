public class MathSupInf {

    public MathSupInf() {
    }

    public boolean strictlySupp(int first, int second) { return (first > second);
    }

    public boolean strictlyInf(double first, double second) {
        return first < second;
    }

    public boolean infOrEq(int first, int second) {
        return (first <= second);
    }

    public boolean supOrEq(int first, int second) {
        return (first >= second);
    }


}