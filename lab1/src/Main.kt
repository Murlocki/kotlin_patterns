//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val students = mutableListOf<Student>();
    students.add(Student("aaaaa","bbbbbb","cccccc"));
    students.add(Student("34","bbbbbb","cccccc", studentTelegram = "@elelelelele"));
    students.add(Student("444","bbbbbb","cccccc", studentGit = "eeefefefef"));
    students.add(Student("AB","bbbbbb","cccccc", studentEmail = "kk@gmial.com"));
    students.add(Student("B","bbbbbb","cccccc"));
    students.add(Student("C","bbbbbb","cccccc"));
    students.add(Student("D","bbbbbb","cccccc"));
    students.add(Student("E","bbbbbb","cccccc"));
    students.forEach { it: Student -> println(it) };

}