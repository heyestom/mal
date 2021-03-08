package mal.step0_repl;


public class Step0_REPL {

    private static String READ(String string) {
        return string;
    }

    private static String EVAL(String string) {
        return string;
    }

    private static String PRINT(String string) {
        return string;
    }

    public static String rep(String input) {
        return PRINT(EVAL(READ(input)));
    }

}
