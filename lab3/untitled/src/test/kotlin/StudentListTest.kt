import DataBasePack.DbCon
import DataBasePack.StudentListDB
import Student.Student
import Student.StudentShort
import StudentList.StudentList
import StudentList.StudentListJson
import StudentList.StudentListTxt
import StudentList.StudentListYaml
import org.junit.jupiter.api.Test

class StudentListTest {
    @Test
    fun testGetByIdJson(){
        val studentList = StudentList(StudentListJson("src/test/resources/res.json","src/test/resources/res.json"));
        val resultStudent = Student("Student(id:0,surname:Aaaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        assert(resultStudent.toString()==studentList.getStudentById(0).toString())
    }
    @Test
    fun testGetNKStudentShortJson(){
        val studentListJson =  StudentList(StudentListJson("src/test/resources/res.json","src/test/resources/res.json"))
        val resultStudent = Student("Student(id:0,surname:Aaaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        assert(0==studentListJson.getKNStudentShortList(6,6).elements.size)
        assert(StudentShort(resultStudent).toString() == studentListJson.getKNStudentShortList(1,1).elements[0].toString())
    }

    @Test
    fun testSortByInitialsJson(){
        val studentListJson = StudentList(StudentListJson("src/test/resources/res.json","src/test/resources/res.json"))
        val resultStudent = Student("Student(id:0,surname:Aaaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        studentListJson.sortByInitials(1)
        assert(StudentShort(resultStudent).toString() == studentListJson.getKNStudentShortList(1,6).elements[0].toString())
        studentListJson.sortByInitials(-1)
        assert(StudentShort(resultStudent).toString() == studentListJson.getKNStudentShortList(4,1).elements[0].toString())
    }
    @Test
    fun testAddDeleteJson(){
        val studentListJson = StudentList(StudentListJson("src/test/resources/res.json","src/test/resources/res.json"))
        val resultStudent = Student("Student(id:4,surname:Aaaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        studentListJson.addNewStudent(resultStudent)
        assert(resultStudent.toString() ==  studentListJson.getStudentById(4).toString())
        studentListJson.deleteById(4)
        assert(null ==  studentListJson.getStudentById(4))
    }
    @Test
    fun testReplaceByIdJson(){
        val studentListJson = StudentList(StudentListJson("src/test/resources/res.json","src/test/resources/res.json"))
        val resultStudent = Student("Student(id:3,surname:Aeeeeeaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        studentListJson.replaceById(3,resultStudent)
        assert(resultStudent.toString() == studentListJson.getStudentById(3).toString())
        studentListJson.replaceById(3,
            Student("Student(id:3,surname:Araaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        )
    }
    @Test
    fun testGetStudentCountJson(){
        val studentListJson = StudentList(StudentListJson("src/test/resources/res.json","src/test/resources/res.json"))
        assert(4==studentListJson.getStudentShortCount())
    }

    @Test
    fun testGetByIdTxt(){
        val studentListTxt = StudentList(StudentListTxt("src/test/resources/out.txt","src/test/resources/out.txt"))
        val resultStudent = Student("Student(id:0,surname:Aaaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        assert(resultStudent.toString()==studentListTxt.getStudentById(0).toString())
    }
    @Test
    fun testGetNKStudentShortTxt(){
        val studentListTxt = StudentList(StudentListTxt("src/test/resources/out.txt","src/test/resources/out.txt"))
        val resultStudent = Student("Student(id:0,surname:Aaaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        assert(0==studentListTxt.getKNStudentShortList(6,6).elements.size)
        assert(StudentShort(resultStudent).toString() == studentListTxt.getKNStudentShortList(1,1).elements[0].toString())
    }

    @Test
    fun testSortByInitialsTxt(){
        val studentListTxt = StudentList(StudentListTxt("src/test/resources/out.txt","src/test/resources/out.txt"))
        val resultStudent = Student("Student(id:3,surname:A-B,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:kk@gmial.com,telegram:,gitHub:)")
        studentListTxt.sortByInitials(1)
        assert(StudentShort(resultStudent).toString() == studentListTxt.getKNStudentShortList(1,6).elements[0].toString())
        studentListTxt.sortByInitials(-1)
        assert(StudentShort(resultStudent).toString() == studentListTxt.getKNStudentShortList(1,6).elements[5].toString())
    }
    @Test
    fun testAddDeleteTxt(){
        val studentListTxt = StudentList(StudentListTxt("src/test/resources/out.txt","src/test/resources/out.txt"))
        val resultStudent = Student("Student(id:6,surname:A-B,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:kk@gmial.com,telegram:,gitHub:)")
        studentListTxt.addNewStudent(resultStudent)
        assert(resultStudent.toString() == studentListTxt.getStudentById(6).toString())
        studentListTxt.deleteById(6)
        assert(null == studentListTxt.getStudentById(6))
    }
    @Test
    fun testReplaceByIdTxt(){
        val studentListTxt = StudentList(StudentListTxt("src/test/resources/out.txt","src/test/resources/out.txt"))
        val resultStudent = Student("Student(id:5,surname:A-B,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:kk@gmial.com,telegram:,gitHub:)")
        studentListTxt.replaceById(5,resultStudent)
        assert(resultStudent.toString() == studentListTxt.getStudentById(5).toString())
        studentListTxt.replaceById(5,Student("Student(id:5,surname:Bbbbbb,name:E,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)"))
    }
    @Test
    fun testGetStudentCountTxt(){
        val studentListTxt = StudentList(StudentListTxt("src/test/resources/out.txt","src/test/resources/out.txt"))
        assert(6==studentListTxt.getStudentShortCount())
    }
    @Test
    fun testGetByIdYaml(){
        val studentListYaml = StudentList(StudentListYaml("src/test/resources/res.yaml","src/test/resources/res.yaml"))
        val resultStudent = Student("Student(id:4,surname:Aaaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        assert(resultStudent.toString()==studentListYaml.getStudentById(4).toString())
    }
    @Test
    fun testGetNKStudentShortYaml(){
        val studentListYaml = StudentList(StudentListYaml("src/test/resources/res.yaml","src/test/resources/res.yaml"))
        val resultStudent = Student("Student(id:4,surname:Aaaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        assert(studentListYaml.getKNStudentShortList(6, 6).elements.isEmpty())
        assert(StudentShort(resultStudent).toString() == studentListYaml.getKNStudentShortList(1,1).elements[0].toString())
    }

    @Test
    fun testSortByInitialsYaml(){
        val studentListYaml = StudentList(StudentListYaml("src/test/resources/res.yaml","src/test/resources/res.yaml"))
        val resultStudent = Student("Student(id:4,surname:Aaaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        studentListYaml.sortByInitials(1)
        assert(StudentShort(resultStudent).toString() == studentListYaml.getKNStudentShortList(1,6).elements[0].toString())
        studentListYaml.sortByInitials(-1)
        assert(StudentShort(resultStudent).toString() == studentListYaml.getKNStudentShortList(1,6).elements[4].toString())
    }
    @Test
    fun testAddDeleteYaml(){
        val studentListYaml = StudentList(StudentListYaml("src/test/resources/res.yaml","src/test/resources/res.yaml"))
        val resultStudent = Student("Student(id:9,surname:Aaaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        studentListYaml.addNewStudent(resultStudent)
        assert(resultStudent.toString() ==  studentListYaml.getStudentById(9).toString())
        studentListYaml.deleteById(9)
        assert(null ==  studentListYaml.getStudentById(9))
    }
    @Test
    fun testReplaceByIdYaml(){
        val studentListYaml = StudentList(StudentListYaml("src/test/resources/res.yaml","src/test/resources/res.yaml"))
        val resultStudent = Student("Student(id:8,surname:Aeeeeeaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        studentListYaml.replaceById(8,resultStudent)
        assert(resultStudent.toString() == studentListYaml.getStudentById(8).toString())
        studentListYaml.replaceById(8,
            Student("Student(id:3,surname:Aabbb-Abbb,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:@elelelelele,gitHub:)")
        )
    }
    @Test
    fun testGetStudentCountYaml(){
        val studentListYaml = StudentList(StudentListYaml("src/test/resources/res.yaml","src/test/resources/res.yaml"))
        assert(5==studentListYaml.getStudentShortCount())
    }
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
        val studentListDB = StudentListDB()
        studentListDB.tableName = "ref_student_moc"
        val stD = StudentList(studentListDB)
        val resultStudent = Student("Student(id:4,surname:Test,name:Test,patronymic:Test,phoneNumber:,email:,telegram:@testtest,gitHub:)")
        stD.addNewStudent(resultStudent)

        val resultDbStudent = getStudentMockFromBD("Test")
        resultStudent.id = resultDbStudent.id

        assert(resultStudent.toString() ==  resultDbStudent.toString())
        stD.deleteById(resultStudent.id)
        assert(null ==  stD.getStudentById(resultStudent.id))
    }
    @Test
    fun testGetNKStudentShort(){
        val studentListDB = StudentListDB()
        studentListDB.tableName = "ref_student_moc"
        val stD = StudentList(studentListDB)

        val resultStudent = Student("Student(id:4,surname:Test,name:Test,patronymic:Test,phoneNumber:,email:,telegram:@testtest,gitHub:)")
        stD.addNewStudent(resultStudent)

        val resultDbStudent = getStudentMockFromBD("Test")
        resultStudent.id = resultDbStudent.id

        assert(0==stD.getKNStudentShortList(6,6).elements.size)
        assert(StudentShort(resultStudent).toString() == stD.getKNStudentShortList(1,1).elements[0].toString())
        stD.deleteById(resultStudent.id)
    }

    @Test
    fun testSortByInitials(){
        val studentListDB = StudentListDB()
        studentListDB.tableName = "ref_student_moc"
        val stD = StudentList(studentListDB)

        val resultStudent = Student("Student(id:4,surname:Test,name:Test,patronymic:Test,phoneNumber:,email:,telegram:@testtest,gitHub:)")
        stD.addNewStudent(resultStudent)

        val resultStudent2 = Student("Student(id:4,surname:Tester,name:Test,patronymic:Test,phoneNumber:,email:,telegram:@testtest,gitHub:)")
        stD.addNewStudent(resultStudent2)


        val resultDbStudent = getStudentMockFromBD("Test")
        resultStudent.id = resultDbStudent.id

        val resultDbStudent2 = getStudentMockFromBD("Tester")
        resultStudent2.id = resultDbStudent2.id


        stD.sortByInitials(1)
        assert(StudentShort(resultStudent).toString() == stD.getKNStudentShortList(1,6).elements[0].toString())
        stD.sortByInitials(-1)
        assert(StudentShort(resultStudent2).toString() == stD.getKNStudentShortList(1,6).elements[0].toString())

        stD.deleteById(resultStudent.id)
        stD.deleteById(resultStudent2.id)
    }
    @Test
    fun testReplaceById(){
        val studentListDB = StudentListDB()
        studentListDB.tableName = "ref_student_moc"
        val stD = StudentList(studentListDB)

        val resultStudent = Student("Student(id:4,surname:Test,name:Test,patronymic:Test,phoneNumber:,email:,telegram:@testtest,gitHub:)")
        stD.addNewStudent(resultStudent)

        val resultDbStudent = getStudentMockFromBD("Test")
        resultStudent.id = resultDbStudent.id

        val replaceStudent = Student("Student(id:3,surname:Aeeeeeaaa,name:Bbbbbb,patronymic:Cccccc,phoneNumber:,email:,telegram:,gitHub:)")
        replaceStudent.id = resultDbStudent.id

        stD.replaceById(replaceStudent.id,replaceStudent)

        assert(replaceStudent.toString() == stD.getStudentById(resultStudent.id).toString())
        stD.deleteById(resultStudent.id)
    }
    @Test
    fun testGetStudentCount(){
        val studentListDB = StudentListDB()
        studentListDB.tableName = "ref_student_moc"
        val stD = StudentList(studentListDB)

        val resultStudent = Student("Student(id:4,surname:Test,name:Test,patronymic:Test,phoneNumber:,email:,telegram:@testtest,gitHub:)")
        stD.addNewStudent(resultStudent)

        val resultDbStudent = getStudentMockFromBD("Test")
        resultStudent.id = resultDbStudent.id

        assert(1 == stD.getStudentShortCount())
        stD.deleteById(resultStudent.id)
    }
}