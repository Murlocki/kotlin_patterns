import Student.Student
import Student.StudentShort
import StudentList.StudentListJson
import StudentList.StudentListYaml
import org.junit.jupiter.api.Test

class StudentListYamlTests {
    @Test
    fun testGetById(){
        val studentListYaml = StudentListYaml("src/test/resources/res.yaml","src/test/resources/res.yaml");
        val resultStudent = Student("Student(id:4,surname:Aaaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        assert(resultStudent.toString()==studentListYaml.getStudentById(4).toString())
    }
    @Test
    fun testGetNKStudentShort(){
        val studentListYaml = StudentListYaml("src/test/resources/res.yaml","src/test/resources/res.yaml");
        val resultStudent = Student("Student(id:4,surname:Aaaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        assert(studentListYaml.getKNStudentShortList(6, 6).elements.isEmpty())
        assert(StudentShort(resultStudent).toString() == studentListYaml.getKNStudentShortList(1,1).elements[0].toString())
    }

    @Test
    fun testSortByInitials(){
        val studentListYaml = StudentListYaml("src/test/resources/res.yaml","src/test/resources/res.yaml");
        val resultStudent = Student("Student(id:4,surname:Aaaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        studentListYaml.sortByInitials(1)
        assert(StudentShort(resultStudent).toString() == studentListYaml.getKNStudentShortList(1,6).elements[0].toString())
        studentListYaml.sortByInitials(-1)
        assert(StudentShort(resultStudent).toString() == studentListYaml.getKNStudentShortList(1,6).elements[4].toString())
    }
    @Test
    fun testAddDelete(){
        val studentListYaml = StudentListYaml("src/test/resources/res.yaml","src/test/resources/res.yaml");
        val resultStudent = Student("Student(id:9,surname:Aaaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        studentListYaml.addNewStudent(resultStudent)
        assert(resultStudent.toString() ==  studentListYaml.getStudentById(9).toString())
        studentListYaml.deleteById(9)
        assert(null ==  studentListYaml.getStudentById(9))
    }
    @Test
    fun testReplaceById(){
        val studentListYaml = StudentListYaml("src/test/resources/res.yaml","src/test/resources/res.yaml");
        val resultStudent = Student("Student(id:8,surname:Aeeeeeaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        studentListYaml.replaceById(8,resultStudent)
        assert(resultStudent.toString() == studentListYaml.getStudentById(8).toString())
        studentListYaml.replaceById(8,
            Student("Student(id:3,surname:Aabbb-Abbb,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:@elelelelele,gitHub:)")
        )
    }
    @Test
    fun testGetStudentCount(){
        val studentListYaml = StudentListYaml("src/test/resources/res.yaml","src/test/resources/res.yaml");
        assert(5==studentListYaml.getStudentShortCount())
    }
}