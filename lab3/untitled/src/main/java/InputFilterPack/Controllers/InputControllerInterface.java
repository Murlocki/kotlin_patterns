package InputFilterPack.Controllers;

import Student.Student;

import java.awt.event.ItemEvent;
import java.util.List;
import java.util.function.Function;

interface InputControllerInterface {
    public void clearInput();

    public void setParams();

    public void changeInputText();

    public Function<List<Student>,List<Student>> getRequest();
}
