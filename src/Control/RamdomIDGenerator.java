package Control;

public class RamdomIDGenerator {

    public static int generate() {
        return (int) (Math.random() * 9000) + 1000;
    }

    public static int generate(int len) {
        int result = 0;
        for (int i = 0; i < len; i++) {
            result += (int) (Math.random() * 10) * Math.pow(10, i);
        }
        return result;
    }
}
