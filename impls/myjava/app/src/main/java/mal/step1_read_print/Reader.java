package mal.step1_read_print;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reader {

	private int position;
	private List<String> tokens;

	public Reader(int position, List<String> tokens) {
		this.position = position;
		this.tokens = tokens;
	}

	public String next() {
		String token = tokens.get(position);
		position++;
		return token;
	}

	public String peek() {
		return tokens.get(position);
	}

	public static MalType read_str(String input) {
		List<String> tokens = tokenize(input);
		Reader reader = new Reader(0, tokens);
		return read_from(reader);
	}

	public static MalType read_atom(String token) {
		try {
			return new MalNum(Long.parseLong(token));
		} catch (NumberFormatException e) {
			try {
				return new MalNum(Double.parseDouble(token));
			} catch (NumberFormatException d) {
				return new MalSym(token);
			}
		}
	}

	public static MalList read_list(Reader reader) {

		// drop leading parenthesis
		String token = reader.next();

		List<MalType> malTypes = new ArrayList<MalType>();

		while (!(token = reader.peek()).equals(")")) {
			malTypes.add(read_from(reader));
		}

		// drop trailing parentheses
		reader.next();

		return new MalList(malTypes);
	}

	public static MalType read_from(Reader reader) {

		char firstChar = reader.peek().charAt(0);

		switch (firstChar) {
		case '(':
			return read_list(reader);
		default:
			return read_atom(reader.next());
		}
	}

	public static List<String> tokenize(String input) {
		Pattern pattern = Pattern
				.compile("[\\s,]*(~@|[\\[\\]{}()'`~^@]|\"(?:\\\\.|[^\\\\\"])*\"?|;.*|[^\\s\\[\\]{}('\"`,;)]*)", Pattern.CASE_INSENSITIVE);
				
		Matcher matcher = pattern.matcher(input);
		
		// group 1 ignores whitespace and commas 
		List<String> tokens = matcher.results() // Streams
				.map(x -> x.group(1))
				.map(String::trim)
				.filter(string -> !string.isBlank()) // new in java 11!
				.collect(Collectors.toList());

		return tokens;
	}

}
