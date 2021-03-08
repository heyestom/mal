package mal.step0_repl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Step0_REPL_Test {
    @Test
    public void readEvaluatePrintLoop() {
        final String input = "HELLO WORLD";
        assertEquals("Should return the String passed to it", input, Step0_REPL.rep(input));
    }
}
