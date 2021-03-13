package mal.step1_read_print;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.List;

public class ReaderTest {


	@Test
	public void testTokenizationOfInputWhiteSpaces() {
		String input = "     ";

		List<String> result = Reader.tokenize(input);
		List<String> expectedTokens = List.of();

		assertEquals("Should ignore whitespace", expectedTokens, result);
	}
	
	@Test
	public void testTokenizationOfInputCommas() {
		String input = ",,,,";

		List<String> result = Reader.tokenize(input);
		List<String> expectedTokens = List.of();

		assertEquals("Should treat commas as whitespace", expectedTokens, result);
	}
	
	@Test
	public void testTokenizationOfInputIntegers() {
		String input = "1 2 3";

		List<String> result = Reader.tokenize(input);
		List<String> expectedTokens = List.of("1", "2", "3");

		assertEquals("Should tokenise integers", expectedTokens, result);
	}

	@Test
	public void testTokenizationOfInputStrings() {
		String input = "hello world";

		List<String> result = Reader.tokenize(input);
		List<String> expectedTokens = List.of("hello", "world");

		assertEquals("Should tokenise Strings", expectedTokens, result);
	}

	@Test
	public void testTokenizationOfInputSpecialTidleAtSign() {
		String input = "~@()";

		List<String> result = Reader.tokenize(input);
		List<String> expectedTokens = List.of("~@", "(", ")");

		assertEquals("Should tokenise TidleAtSign", expectedTokens, result);
	}

	@Test
	public void testTokenizationOfAnyCharSeuanceStartingWithSemi() {
		String input = ";this@ is all one capture group ; yep";

		List<String> result = Reader.tokenize(input);
		List<String> expectedTokens = List.of(";this@ is all one capture group ; yep");

		assertEquals("Should tokenise Any Char Seuance Starting With Semi", expectedTokens, result);
	}

	@Test
	public void testTokenizationOfInputSpecialChars() {
		String input = "[]{}()'`~^@";

		List<String> result = Reader.tokenize(input);
		List<String> expectedTokens = List.of("[", "]", "{", "}", "(", ")", "'", "`", "~", "^", "@");

		assertEquals("Should tokenise SpecialChars", expectedTokens, result);
	}

	@Test
	public void testTokenizationOfInputNonSpecialChars() {
		String input = "!£$%^&* true false bob symble - + &";

		List<String> result = Reader.tokenize(input);
		List<String> expectedTokens = List.of("!£$%^&*", "true", "false", "bob", "symble", "-", "+", "&");

		assertEquals("Should tokenise SpecialChars", expectedTokens, result);
	}

	@Test
	public void readingAtomicTokensIntergers() {
		String inputToken = "1234.56";

		MalNum result = (MalNum) Reader.read_atom(inputToken);
		MalNum expectedNumber = new MalNum(1234.56);

		assertEquals("Should pase Intergers into MalNums", expectedNumber, result);
	}

	@Test
	public void readingAtomicTokensSymbolsMinus() {
		String inputToken = "-";

		MalSym result = (MalSym) Reader.read_atom(inputToken);
		MalSym expecteSymbol = new MalSym("-");

		assertEquals("Should pase Symbols into MalSym -", expecteSymbol, result);
	}

	@Test
	public void readingAtomicTokensSymbolsPlus() {
		String inputToken = "+";

		MalSym result = (MalSym) Reader.read_atom(inputToken);
		MalSym expectedSymbol = new MalSym("+");

		assertEquals("Should parse Symbols into MalSym +", expectedSymbol, result);
	}

	@Test
	public void parsingInputInt() {
		String input = "12345";

		MalNum result = (MalNum) Reader.read_str(input);
		MalNum expectedNumber = new MalNum(12345L);

		assertEquals("Should parse an Interger into a MalNum", expectedNumber, result);

	}

	@Test
	public void parsingInputDouble() {
		String input = "12345.678";

		MalNum result = (MalNum) Reader.read_str(input);
		MalNum expectedNumber = new MalNum(12345.678);

		assertEquals("Should parse an Interger into a MalNum", expectedNumber, result);

	}

	@Test
	public void parsingInputSymbol() {
		String input = "+";

		MalSym result = (MalSym) Reader.read_str(input);
		MalSym expectedSymbol = new MalSym("+");

		assertEquals("Should parse a Symbol into a MalSym", expectedSymbol, result);
	}

	@Test
	public void parsingInputListOfSymbols() {
		String input = "(+ = * &)";

		MalList result = (MalList) Reader.read_str(input);

		List<MalType> values = List.of(new MalSym("+"), new MalSym("="), new MalSym("*"), new MalSym("&"));
		MalList expectedMalList = new MalList(values);

		assertEquals("Should parse multiple Symbols into a MalList", expectedMalList, result);
	}

	@Test
	public void parsingInputListOfNumbers() {
		String input = "(1 0.12 4353 -2345)";

		MalList result = (MalList) Reader.read_str(input);
		List<MalType> values = List.of(new MalNum(1L), new MalNum(0.12), new MalNum(4353L), new MalNum(-2345L));
		MalList expectedMalList = new MalList(values);

		assertEquals("Should parse multiple Numbers into a MalList", expectedMalList, result);
	}

	@Test
	public void parsingEmptyList() {
		String input = "()";

		MalList result = (MalList) Reader.read_str(input);
		MalList expectedMalList = new MalList(List.of());

		assertEquals("Should parse empty list", expectedMalList, result);
	}
	
	@Test
	public void parsingNestedLists() {
		String input = "(+ (- 2 4) (* 2 2))";

		MalList result = (MalList) Reader.read_str(input);

		List<MalType> values = List.of(new MalSym("+"),
				new MalList(List.of(new MalSym("-"), new MalNum(2L), new MalNum(4L))),
				new MalList(List.of(new MalSym("*"), new MalNum(2L), new MalNum(2L))));

		MalList expectedMalList = new MalList(values);

		assertEquals("Should parse nested lists", expectedMalList, result);
	}

}
