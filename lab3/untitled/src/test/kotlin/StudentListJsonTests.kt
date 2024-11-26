import Student.Student
import Student.StudentShort
import StudentList.StudentListJson
import org.junit.jupiter.api.Test

class StudentListJsonTests {
    @Test
    fun testGetById(){
        val studentListJson = StudentListJson("src/test/resources/res.json","src/test/resources/res.json");
        val resultStudent = Student("Student(id:0,surname:Aaaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        assert(resultStudent.toString()==studentListJson.getStudentById(0).toString())
    }
    @Test
    fun testGetNKStudentShort(){
        val studentListJson = StudentListJson("src/test/resources/res.json","src/test/resources/res.json");
        val resultStudent = Student("Student(id:0,surname:Aaaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        assert(0==studentListJson.getKNStudentShortList(6,6).elements.size)
        assert(StudentShort(resultStudent).toString() == studentListJson.getKNStudentShortList(1,1).elements[0].toString())
    }

    @Test
    fun testSortByInitials(){
        val studentListJson = StudentListJson("src/test/resources/res.json","src/test/resources/res.json");
        val resultStudent = Student("Student(id:0,surname:Aaaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        studentListJson.sortByInitials(1)
        assert(StudentShort(resultStudent).toString() == studentListJson.getKNStudentShortList(1,6).elements[0].toString())
        studentListJson.sortByInitials(-1)
        assert(StudentShort(resultStudent).toString() == studentListJson.getKNStudentShortList(4,1).elements[0].toString())
    }
    @Test
    fun testAddDelete(){
        val studentListJson = StudentListJson("src/test/resources/res.json","src/test/resources/res.json");
        val resultStudent = Student("Student(id:4,surname:Aaaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        studentListJson.addNewStudent(resultStudent)
        assert(resultStudent.toString() ==  studentListJson.getStudentById(4).toString())
        studentListJson.deleteById(4)
        assert(null ==  studentListJson.getStudentById(4))
    }
    @Test
    fun testReplaceById(){
        val studentListJson = StudentListJson("src/test/resources/res.json","src/test/resources/res.json");
        val resultStudent = Student("Student(id:3,surname:Aeeeeeaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        studentListJson.replaceById(3,resultStudent)
        assert(resultStudent.toString() == studentListJson.getStudentById(3).toString())
        studentListJson.replaceById(3,
            Student("Student(id:3,surname:Araaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        )
    }
    @Test
    fun testGetStudentCount(){
        val studentListJson = StudentListJson("src/test/resources/res.json","src/test/resources/res.json");
        assert(4==studentListJson.getStudentShortCount())
    }
}