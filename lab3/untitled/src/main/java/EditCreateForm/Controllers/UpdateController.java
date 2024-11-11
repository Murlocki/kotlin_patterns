package EditCreateForm.Controllers;

import EditCreateForm.EditCreateWindow;
import Student.Student;
import StudentList.StudentList;

import java.util.HashMap;

public class UpdateController extends EditCreateWindowController{
    public UpdateController(EditCreateWindow editCreateWindow, StudentList studentList){
        super(editCreateWindow, studentList);
    }
    public int id;
    public void setStudentInfo(){
        Student st = studentList.getStudentById(id);
        assert st != null;
        this.surnameFieldController.inputFieldModel.setInputText(st.getSurname());
        this.surnameFieldController.setInputText();

        this.nameFieldController.inputFieldModel.setInputText(st.getName());
        this.nameFieldController.setInputText();

        this.patronymicFieldController.inputFieldModel.setInputText(st.getPatronymic());
        this.patronymicFieldController.setInputText();

        this.gitHubFieldController.inputFieldModel.setInputText(st.getGitHub());
        this.gitHubFieldController.setInputText();

        this.emailFieldController.inputFieldModel.setInputText(st.getEmail());
        this.emailFieldController.setInputText();

        this.phoneNumberFieldController.inputFieldModel.setInputText(st.getPhoneNumber());
        this.phoneNumberFieldController.setInputText();

        this.telegramFieldController.inputFieldModel.setInputText(st.getTelegram());
        this.telegramFieldController.setInputText();
    }

    @Override
    public void processForm(){
        HashMap<String,Object> hashForStudent = new HashMap<>();


        hashForStudent.put("surname",checkEmpty(this.surnameFieldController.getTextValue()));
        hashForStudent.put("name",checkEmpty(this.nameFieldController.getTextValue()));
        hashForStudent.put("patronymic",checkEmpty(this.patronymicFieldController.getTextValue()));
        hashForStudent.put("phoneNumber",checkEmpty(this.phoneNumberFieldController.getTextValue()));
        hashForStudent.put("telegram",checkEmpty(this.telegramFieldController.getTextValue()));
        hashForStudent.put("email",checkEmpty(this.emailFieldController.getTextValue()));
        hashForStudent.put("gitHub",checkEmpty(this.gitHubFieldController.getTextValue()));
        this.studentList.replaceById(id,new Student(hashForStudent));
        this.editCreateWindow.dispose();
    }
}
