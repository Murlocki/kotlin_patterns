//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val students = mutableListOf<Student>();
    students.add(Student("aaaaa","bbbbbb","cccccc"));
    students.add(Student("34","bbbbbb","cccccc", telegram = "@elelelelele"));
    students.add(Student("444","bbbbbb","cccccc", gitHub = "eeefefefef"));
    students.add(Student("AB","bbbbbb","cccccc", email = "kk@gmial.com"));
    students.add(Student("B","bbbbbb","cccccc"));
    students.add(Student("C","bbbbbb","cccccc"));
    students.add(Student("D","bbbbbb","cccccc"));
    students.add(Student("E","bbbbbb","cccccc"));
    students.add(Student(hashMapOf(Pair("name","E"),Pair("surname","bbbbbb"),Pair("patronymic","cccccc"))));
    students.forEach { it: Student -> println(it) };

}