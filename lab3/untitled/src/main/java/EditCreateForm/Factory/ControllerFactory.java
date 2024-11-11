package EditCreateForm.Factory;

import EditCreateForm.Controllers.*;
import EditCreateForm.EditCreateWindow;
import StudentList.StudentList;

public class ControllerFactory {
    public static EditCreateWindowController createCreateController(EditCreateWindow editCreateWindow, StudentList studentList){
        return new EditCreateWindowController(editCreateWindow,studentList);
    }
    public static UpdateController createUpdateController(EditCreateWindow editCreateWindow, StudentList studentList){
        return new UpdateFIOController(editCreateWindow,studentList);
    }
    public static UpdateController createUpdateEmailController(EditCreateWindow editCreateWindow, StudentList studentList){
        return new UpdateEmailController(editCreateWindow,studentList);
    }
    public static UpdateController createUpdateGitController(EditCreateWindow editCreateWindow, StudentList studentList){
        return new UpdateGitController(editCreateWindow,studentList);
    }
    public static UpdateController createUpdatePhoneController(EditCreateWindow editCreateWindow, StudentList studentList){
        return new UpdatePhoneController(editCreateWindow,studentList);
    }
    public static UpdateController createUpdateTelegramController(EditCreateWindow editCreateWindow, StudentList studentList){
        return new UpdateTelegramController(editCreateWindow,studentList);
    }
}
