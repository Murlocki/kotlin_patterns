//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val students = mutableListOf<Student>();
    students.add(Student("Aaaaa","Bbbbbb","Cccccc"));
    students.add(Student("Aabbb-Abbb","Bbbbbb","Cccccc", telegramValue = "@elelelelele"));
    students.add(Student("Aaabbb","Bbb-Cbbb","Cccccc", gitHubValue = "eeefefefef"));
    students.add(Student("A-B","Bbbbbb","Cccccc", emailValue = "kk@gmial.com"));
    students.add(Student("B","Vbbbbb","Cccccc", phoneNumberValue = "+79889889898"));
    students.add(Student(hashMapOf(Pair("name","E"),Pair("surname","Bbbbbb"),Pair("patronymic","Cccccc"))));
    students.forEach { it: Student -> println(it) };

    println(Student(hashMapOf(Pair("name","E"),Pair("surname","Bbbbbb"),Pair("patronymic","Cccccc"))).validate())
    println(Student("A-B","Bbbbbb","Cccccc", emailValue = "kk@gmial.com", gitHubValue = "eeefefefef").validate());

    val stud = Student("A-B","Bbbbbb","Cccccc", emailValue = "kk@gmial.com", gitHubValue = "eeefefefef", phoneNumberValue = "+79885556677");
    stud.setContacts(hashMapOf(Pair("email","kkkkk@gmial.com"),Pair("telegram","@elelelel"),Pair("gitHub",null)));
    println(stud)
    stud.name = "Abbbbcddd";
    println(stud)
}