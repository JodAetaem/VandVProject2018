public class MathSupInf {

    public MathSupInf() {
    }

    public boolean strictlySupp(int first, int second) { return (first > second);
    }

    public boolean strictlyInf(double first, double second) {
        return first < second;
    }

    public boolean infOrEq(long first, long second) {
        return (first <= second);
    }

    public boolean supOrEq(float first, float second) {
        return (first >= second);
    }


}