package mal.step1_read_print;

public class Step1_REPL {

	private static MalType READ(String input) {
		return Reader.read_str(input);
	}

	private static MalType EVAL(MalType malType) {
		return malType;
	}

	private static String PRINT(MalType malType) {
		return Printer.pr_str(malType);
	}

	public static String rep(String input) {
		return PRINT(EVAL(READ(input)));
	}

}
