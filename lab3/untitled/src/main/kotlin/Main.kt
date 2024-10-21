package org.example
import DataBasePack.DbCon
import DataBasePack.StudentListDB
import Student.Student

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
//    val students = mutableListOf<Student>();
//    students.add(Student("Aaaaa", "Bbbbbb", "Cccccc"));
//    students.add(Student("Aabbb-Abbb", "Bbbbbb", "Cccccc", telegramValue = "@elelelelele"));
//    students.add(Student("Aaabbb", "Bbb-Cbbb", "Cccccc", gitHubValue = "eeefefefef"));
//    students.add(Student("A-B", "Bbbbbb", "Cccccc", emailValue = "kk@gmial.com"));
//    students.add(Student("B", "Vbbbbb", "Cccccc", phoneNumberValue = "+79889889898"));
//    students.add(Student(hashMapOf(Pair("name", "E"), Pair("surname", "Bbbbbb"), Pair("patronymic", "Cccccc"))));
//    students.forEach { it: Student -> println(it) };

//    println(Student(hashMapOf(Pair("name","E"),Pair("surname","Bbbbbb"),Pair("patronymic","Cccccc"))).validate())
//    println(Student("A-B","Bbbbbb","Cccccc", emailValue = "kk@gmial.com", gitHubValue = "eeefefefef").validate());
//
//    val stud = Student("A-B","Bbbbbb","Cccccc", emailValue = "kk@gmial.com", gitHubValue = "eeefefefef", phoneNumberValue = "+79885556677");
//    stud.setContacts(hashMapOf(Pair("email","kkkkk@gmial.com"),Pair("telegram","@elelelel"),Pair("gitHub",null)));
//    println(stud)
//    stud.name = "Abbbbcddd";
//    val data = Student.parseString("Student(id:4,surname:A,name:Bbbbbb,patronymic:Cccccc,phoneNumber:+79885556677,email:kk@gmial.com,telegram:,gitHub:eeefefefef)")
//    println(data)
//    println(stud)
//    println(Student(data))
//    println(Student(Student.parseString("Student(id:aaaa,surname:A,name:Bbbbbb,patronymic:Cccccc,gitHub:)")))
//    println(stud.getInfo())
//    println(StudentShort(4,Student("A-B","A-B","Cccc", gitHubValue = "kk@gmail.com", telegramValue = "@aaaaa").getInfo()))
//    println(StudentShort(stud))
//
//
//    val studListTxt = StudentListBase(StudentListTxtStrategy())
//    studListTxt.readFromFile("src/main/resources/t.txt")
//    studListTxt.writeToFile("src/main/resources/","out.txt")
//    println(studListTxt.sortByInitials())
//    studListTxt.addNewStudent(Student("A-B", "Bbbbbb", "Cccccc", emailValue = "kk@gmial.com"))
//    println(studListTxt)
//    studListTxt.deletebyId(4)
//    println(studListTxt)
//    studListTxt.replaceById(0,
//        Student(hashMapOf(Pair("name", "E"), Pair("surname", "Bbbbbb"), Pair("patronymic", "Cccccc")))
//    )
//    println(studListTxt)
//
//    val studentListJson = StudentListBase(StudentListJsonStrategy())
//    studentListJson.readFromFile("src/main/resources/file.json")
//    studentListJson.writeToFile("src/main/resources/","res.json")
//
//    val studentListYaml = StudentListBase(StudentListYamlStrategy())
//    studentListYaml.readFromFile("src/main/resources/test.yaml")
//    studentListYaml.writeToFile("src/main/resources/","res.yaml")

    println(StudentListDB().getStudentById(11))
    println(StudentListDB().getKNStudentShortList(3,1).getData())
    println(StudentListDB().getStudentShortCount())
    StudentListDB().deleteById(13)
    //StudentListDB().addNewStudent(Student("Aabbb", "Bbbbbb", "Cccccc", telegramValue = "@elelelelele"))
    //StudentListDB().replaceById(11,Student("Ad", "Bbbbbb", "Cccccc", telegramValue = "@elelelelele"))
}