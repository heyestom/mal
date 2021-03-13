package mal.step1_read_print;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Step1_Read_Print_Test {
	@Test
	public void readEvaluatePrintLoop() {
		final String input = "\"HELLO WORLD\"";
		assertEquals("Should return the String passed to it", input, Step1_REPL.rep(input));
	}
}
