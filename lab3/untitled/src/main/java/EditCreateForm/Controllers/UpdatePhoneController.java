package EditCreateForm.Controllers;

import EditCreateForm.EditCreateWindow;
import Student.Student;
import StudentList.StudentList;

import java.util.HashMap;

public class UpdatePhoneController extends UpdateController{

    public UpdatePhoneController(EditCreateWindow editCreateWindow, StudentList studentList) {
        super(editCreateWindow, studentList);
        this.gitHubFieldController.changeEditable(false);
        this.surnameFieldController.changeEditable(false);
        this.nameFieldController.changeEditable(false);
        this.patronymicFieldController.changeEditable(false);
        this.emailFieldController.changeEditable(false);
        this.telegramFieldController.changeEditable(false);
    }
}
