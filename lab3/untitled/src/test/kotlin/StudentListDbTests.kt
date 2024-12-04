import DataBasePack.DbCon
import DataBasePack.StudentListDB
import Student.Student
import Student.StudentShort
import StudentList.StudentListJson
import org.junit.jupiter.api.Test

class StudentListDbTests {

    fun getStudentMockFromBD(surname:String): Student {
        val conn = DbCon;
        val result = conn?.executeSqlSelect("SELECT * FROM ref_student_moc as t where t.surname='$surname' and t.name='Test' and t.patronymic='Test'");
        result?.next()
        val resultHash: HashMap<String, Any?> = hashMapOf<String, Any?>()
        resultHash.set("id", result?.getInt("id"))
        resultHash.set("name", result?.getString("name"))
        resultHash.set("surname", result?.getString("surname"))
        resultHash.set("patronymic", result?.getString("patronymic"))
        resultHash.set("phoneNumber", result?.getString("phoneNumber"))
        resultHash.set("gitHub", result?.getString("gitHub"))
        resultHash.set("email", result?.getString("email"))
        resultHash.set("telegram", result?.getString("telegram"))
        val resultDBStudent = Student(resultHash)
        result?.close()
        return resultDBStudent
    }
    @Test
    fun testAddDelete(){
        val studentListDB = StudentListDB();
        studentListDB.tableName = "ref_student_moc"
        val resultStudent = Student("Student(id:4,surname:Test,name:Test,patronymic:Test,phoneNumber:,email:,telegram:@testtest,gitHub:)")
        studentListDB.addNewStudent(resultStudent)

        val resultDbStudent = getStudentMockFromBD("Test")
        resultStudent.id = resultDbStudent.id

        assert(resultStudent.toString() ==  resultDbStudent.toString())
        studentListDB.deleteById(resultStudent.id)
        assert(null ==  studentListDB.getStudentById(resultStudent.id))
    }
    @Test
    fun testGetNKStudentShort(){
        val studentListDB = StudentListDB();
        studentListDB.tableName = "ref_student_moc"

        val resultStudent = Student("Student(id:4,surname:Test,name:Test,patronymic:Test,phoneNumber:,email:,telegram:@testtest,gitHub:)")
        studentListDB.addNewStudent(resultStudent)

        val resultDbStudent = getStudentMockFromBD("Test")
        resultStudent.id = resultDbStudent.id

        assert(0==studentListDB.getKNStudentShortList(6,6).elements.size)
        assert(StudentShort(resultStudent).toString() == studentListDB.getKNStudentShortList(1,1).elements[0].toString())
        studentListDB.deleteById(resultStudent.id)
    }

    @Test
    fun testSortByInitials(){
        val studentListDB = StudentListDB();
        studentListDB.tableName = "ref_student_moc"

        val resultStudent = Student("Student(id:4,surname:Test,name:Test,patronymic:Test,phoneNumber:,email:,telegram:@testtest,gitHub:)")
        studentListDB.addNewStudent(resultStudent)

        val resultStudent2 = Student("Student(id:4,surname:Tester,name:Test,patronymic:Test,phoneNumber:,email:,telegram:@testtest,gitHub:)")
        studentListDB.addNewStudent(resultStudent2)


        val resultDbStudent = getStudentMockFromBD("Test")
        resultStudent.id = resultDbStudent.id

        val resultDbStudent2 = getStudentMockFromBD("Tester")
        resultStudent2.id = resultDbStudent2.id


        studentListDB.sortBy(1,"initials")
        assert(StudentShort(resultStudent).toString() == studentListDB.getKNStudentShortList(1,6).elements[0].toString())
        studentListDB.sortBy(-1,"initials")
        assert(StudentShort(resultStudent2).toString() == studentListDB.getKNStudentShortList(1,6).elements[0].toString())

        studentListDB.deleteById(resultStudent.id)
        studentListDB.deleteById(resultStudent2.id)
    }
    @Test
    fun testReplaceById(){
        val studentListDB = StudentListDB();
        studentListDB.tableName = "ref_student_moc"

        val resultStudent = Student("Student(id:4,surname:Test,name:Test,patronymic:Test,phoneNumber:,email:,telegram:@testtest,gitHub:)")
        studentListDB.addNewStudent(resultStudent)

        val resultDbStudent = getStudentMockFromBD("Test")
        resultStudent.id = resultDbStudent.id

        val replaceStudent = Student("Student(id:3,surname:Aeeeeeaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        replaceStudent.id = resultDbStudent.id

        studentListDB.replaceById(replaceStudent.id,replaceStudent)

        assert(replaceStudent.toString() == studentListDB.getStudentById(resultStudent.id).toString())
        studentListDB.deleteById(resultStudent.id)
    }
    @Test
    fun testGetStudentCount(){
        val studentListDB = StudentListDB();
        studentListDB.tableName = "ref_student_moc"

        val resultStudent = Student("Student(id:4,surname:Test,name:Test,patronymic:Test,phoneNumber:,email:,telegram:@testtest,gitHub:)")
        studentListDB.addNewStudent(resultStudent)

        val resultDbStudent = getStudentMockFromBD("Test")
        resultStudent.id = resultDbStudent.id

        assert(1 == studentListDB.getStudentShortCount())
        studentListDB.deleteById(resultStudent.id)
    }

    @Test
    fun testFilter(){
        val studentListDB = StudentListDB();
        studentListDB.tableName = "ref_student_moc"

        val resultStudent = Student("Student(id:4,surname:Test,name:Test,patronymic:Test,phoneNumber:,email:,telegram:@testtest,gitHub:)")
        studentListDB.addNewStudent(resultStudent)

        val resultDbStudent = getStudentMockFromBD("Test")
        resultStudent.id = resultDbStudent.id

        studentListDB.filterList(){it.filter { i->i.name=="Test" }.toMutableList()}
        assert(1 == studentListDB.getStudentShortCount())
        studentListDB.deleteById(resultStudent.id)
    }
}