package InputFilterPack.States;

import Student.Student;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class FilterStateNo implements FilterState {

    private String fieldName;

    public FilterStateNo(String fieldName) {
        this.fieldName = fieldName;
    }

    public Function<List<Student>,List<Student>> getRequestPart() {
        return this::filter;
    }


    private List<Student> filter(List<Student> list) {
        return list.stream().filter(it->it.propertiesReturn().get(fieldName)==null).toList();
    }

    @Override
    public String toString() {
        return "FilterStateNo";
    }
}
