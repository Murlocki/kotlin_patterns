//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val students = mutableListOf<Student>();
    students.add(Student("Aaaaa","Bbbbbb","Cccccc"));
    students.add(Student("Aabbb-Abbb","Bbbbbb","Cccccc", telegram = "@elelelelele"));
    students.add(Student("Aaabbb","Bbb-Cbbb","Cccccc", gitHub = "eeefefefef"));
    students.add(Student("A-B","Bbbbbb","Cccccc", email = "kk@gmial.com"));
    students.add(Student("B","Vbbbbb","Cccccc", phoneNumber = "+79889889898"));
    students.add(Student(hashMapOf(Pair("name","E"),Pair("surname","Bbbbbb"),Pair("patronymic","Cccccc"))));
    students.forEach { it: Student -> println(it) };

    println(Student(hashMapOf(Pair("name","E"),Pair("surname","Bbbbbb"),Pair("patronymic","Cccccc"))).validate())
    println(Student("A-B","Bbbbbb","Cccccc", email = "kk@gmial.com", gitHub = "eeefefefef").validate());

    val stud = Student("A-B","Bbbbbb","Cccccc", email = "kk@gmial.com", gitHub = "eeefefefef", phoneNumber = "+79885556677");
    stud.setContacts(hashMapOf(Pair("email","kk@gmial.com"),Pair("telegram","@elelelel"),Pair("gitHub",null)));
    println(stud)
}