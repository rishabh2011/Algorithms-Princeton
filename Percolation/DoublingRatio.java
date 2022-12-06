
public class DoublingRatio {

    // This class should not be instantiated.
    private DoublingRatio() { }

    public static double timeTrial(int n, int trials) {
        Stopwatch timer = new Stopwatch();
        PercolationStats pStats = new PercolationStats(n, trials);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        double prev = timeTrial(100, 10);
        int n = 100;
        int t = 2;
        for (; true; n += n) {
            double time = timeTrial(n, t);
            StdOut.printf("%7d %7.1f %5.1f\n", n, time, time/prev);
            prev = time;
        }
    }
}
