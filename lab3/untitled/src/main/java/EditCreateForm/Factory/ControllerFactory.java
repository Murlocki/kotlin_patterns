package EditCreateForm.Factory;

import EditCreateForm.Controllers.*;
import EditCreateForm.EditCreateWindow;
import StudentList.StudentList;
import StudentList.StudentListComponent;

public class ControllerFactory {
    public static EditCreateWindowController createCreateController(EditCreateWindow editCreateWindow, StudentListComponent studentList){
        return new EditCreateWindowController(editCreateWindow,studentList);
    }
    public static UpdateController createUpdateFIOController(EditCreateWindow editCreateWindow, StudentListComponent studentList){
        UpdateController updateController = new UpdateController(editCreateWindow,studentList);
        updateController.gitHubFieldController.changeEditable(false);
        updateController.phoneNumberFieldController.changeEditable(false);
        updateController.emailFieldController.changeEditable(false);
        updateController.telegramFieldController.changeEditable(false);
        return updateController;
    }
    public static UpdateController createUpdateEmailController(EditCreateWindow editCreateWindow, StudentListComponent studentList){
        UpdateController updateController = new UpdateController(editCreateWindow,studentList);
        updateController.gitHubFieldController.changeEditable(false);
        updateController.phoneNumberFieldController.changeEditable(false);
        updateController.surnameFieldController.changeEditable(false);
        updateController.nameFieldController.changeEditable(false);
        updateController.patronymicFieldController.changeEditable(false);
        updateController.telegramFieldController.changeEditable(false);
        return updateController;
    }
    public static UpdateController createUpdateGitController(EditCreateWindow editCreateWindow, StudentListComponent studentList){
        UpdateController updateController = new UpdateController(editCreateWindow,studentList);
        updateController.surnameFieldController.changeEditable(false);
        updateController.nameFieldController.changeEditable(false);
        updateController.patronymicFieldController.changeEditable(false);
        updateController.phoneNumberFieldController.changeEditable(false);
        updateController.emailFieldController.changeEditable(false);
        updateController.telegramFieldController.changeEditable(false);
        return updateController;
    }
    public static UpdateController createUpdatePhoneController(EditCreateWindow editCreateWindow, StudentListComponent studentList){
        UpdateController updateController = new UpdateController(editCreateWindow,studentList);
        updateController.gitHubFieldController.changeEditable(false);
        updateController.surnameFieldController.changeEditable(false);
        updateController.nameFieldController.changeEditable(false);
        updateController.patronymicFieldController.changeEditable(false);
        updateController.emailFieldController.changeEditable(false);
        updateController.telegramFieldController.changeEditable(false);
        return updateController;
    }
    public static UpdateController createUpdateTelegramController(EditCreateWindow editCreateWindow, StudentListComponent studentList){
        UpdateController updateController = new UpdateController(editCreateWindow,studentList);
        updateController.gitHubFieldController.changeEditable(false);
        updateController.phoneNumberFieldController.changeEditable(false);
        updateController.emailFieldController.changeEditable(false);
        updateController.surnameFieldController.changeEditable(false);
        updateController.nameFieldController.changeEditable(false);
        updateController.patronymicFieldController.changeEditable(false);
        return updateController;
    }
}
