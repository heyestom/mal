package mal.step1_read_print;

import java.util.List;
import java.util.stream.Collectors;

public class MalList implements MalType {
	final List<MalType> value;

	public MalList(List<MalType> values) {
		this.value = values;
	}

	@Override
	public String toString() {
		String listElements = value.stream()
				.map(x -> x.toString())
				.collect(Collectors.joining(" "));
		
		return "(" + listElements +  ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MalList other = (MalList) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
