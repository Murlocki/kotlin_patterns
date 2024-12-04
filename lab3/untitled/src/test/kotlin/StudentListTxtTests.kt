import Student.Student
import Student.StudentShort
import StudentList.StudentListJson
import StudentList.StudentListTxt
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class StudentListTxtTests {
    @Test
    fun testGetById(){
        val studentListTxt = StudentListTxt("src/test/resources/out.txt","src/test/resources/out.txt");
        val resultStudent = Student("Student(id:0,surname:Aaaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        assert(resultStudent.toString()==studentListTxt.getStudentById(0).toString())
    }
    @Test
    fun testGetNKStudentShort(){
        val studentListTxt = StudentListTxt("src/test/resources/out.txt","src/test/resources/out.txt");
        val resultStudent = Student("Student(id:0,surname:Aaaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        assert(0==studentListTxt.getKNStudentShortList(6,6).elements.size)
        assert(StudentShort(resultStudent).toString() == studentListTxt.getKNStudentShortList(1,1).elements[0].toString())
    }

    @Test
    fun testSortByInitials(){
        val studentListTxt = StudentListTxt("src/test/resources/out.txt","src/test/resources/out.txt");
        val resultStudent = Student("Student(id:3,surname:A-B,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:kk@gmial.com,telegram:,gitHub:)")
        studentListTxt.sortBy(1,"initials")
        assert(StudentShort(resultStudent).toString() == studentListTxt.getKNStudentShortList(1,6).elements[0].toString())
        studentListTxt.sortBy(-1,"initials")
        assert(StudentShort(resultStudent).toString() == studentListTxt.getKNStudentShortList(1,6).elements[4].toString())
    }
    @Test
    fun testAddDelete(){
        val studentListTxt = StudentListTxt("src/test/resources/out.txt","src/test/resources/out.txt");
        val resultStudent = Student("Student(id:6,surname:A-B,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:kk@gmial.com,telegram:,gitHub:)")
        studentListTxt.addNewStudent(resultStudent)
        assert(resultStudent.toString() == studentListTxt.getStudentById(6).toString())
        studentListTxt.deleteById(6)
        assert(null == studentListTxt.getStudentById(6))
    }
    @Test
    fun testReplaceById(){
        val studentListTxt = StudentListTxt("src/test/resources/out.txt","src/test/resources/out.txt");
        val resultStudent = Student("Student(id:5,surname:A-B,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:kk@gmial.com,telegram:,gitHub:)")
        studentListTxt.replaceById(5,resultStudent)
        assert(resultStudent.toString() == studentListTxt.getStudentById(5).toString())
        studentListTxt.replaceById(5,Student("Student(id:5,surname:Bbbbbb,name:E,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)"))
    }
    @Test
    fun testGetStudentCount(){
        val studentListTxt = StudentListTxt("src/test/resources/out.txt","src/test/resources/out.txt");
        assert(5==studentListTxt.getStudentShortCount())
    }

    @Test
    fun testFilter(){
        val studentListTxt =StudentListTxt("src/test/resources/out.txt","src/test/resources/out.txt");

        val resultStudent = Student("Student(id:4,surname:Test,name:Test,patronymic:Test,phoneNumber:,email:,telegram:@testtest,gitHub:)")
        studentListTxt.addNewStudent(resultStudent)


        studentListTxt.filterList(){it.filter { i->i.name=="Test" }.toMutableList()}
        assert(1 == studentListTxt.getStudentShortCount())
        studentListTxt.deleteById(studentListTxt.getKNStudentShortList(1,1).elements[0].id)
    }
}