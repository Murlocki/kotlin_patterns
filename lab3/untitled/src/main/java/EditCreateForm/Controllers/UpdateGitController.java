package EditCreateForm.Controllers;

import EditCreateForm.EditCreateWindow;
import Student.Student;
import StudentList.StudentList;

import java.util.HashMap;

public class UpdateGitController extends UpdateController{
    public UpdateGitController(EditCreateWindow editCreateWindow, StudentList studentList) {
        super(editCreateWindow, studentList);
        this.surnameFieldController.changeEditable(false);
        this.nameFieldController.changeEditable(false);
        this.patronymicFieldController.changeEditable(false);
        this.phoneNumberFieldController.changeEditable(false);
        this.emailFieldController.changeEditable(false);
        this.telegramFieldController.changeEditable(false);
    }
}
