package EditCreateForm.Controllers;

import EditCreateForm.EditCreateWindow;
import Student.Student;
import StudentList.StudentList;

import java.util.HashMap;

public class UpdateFIOController extends UpdateController{

    public UpdateFIOController(EditCreateWindow editCreateWindow, StudentList studentList) {
        super(editCreateWindow, studentList);
        this.gitHubFieldController.changeEditable(false);
        this.phoneNumberFieldController.changeEditable(false);
        this.emailFieldController.changeEditable(false);
        this.telegramFieldController.changeEditable(false);
    }
}
