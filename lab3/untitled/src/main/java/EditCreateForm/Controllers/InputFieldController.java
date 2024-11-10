package EditCreateForm.Controllers;

import EditCreateForm.InputField;

import java.awt.*;
import java.util.function.Predicate;

public class InputFieldController {
    public InputField inputField;
    private Predicate<String> validateFunc;
    public InputFieldController(InputField inputField,Predicate<String> validateFunc){
        this.inputField = inputField;
        this.validateFunc = validateFunc;
    }
    public void changeInputFieldBack(boolean correctness){
        if (correctness) {
            this.inputField.mainInputField.setBackground(Color.WHITE); // ������������� ����� ��� ��� ����������� �����
        } else {
            this.inputField.mainInputField.setBackground(Color.RED); // ������������� ������� ��� ��� ������������� �����
        }
    }
    public boolean checkInputCorrect() {
        boolean result = this.validateFunc.test(this.inputField.mainInputField.getText());
        changeInputFieldBack(result);
        return result;
    }
}
