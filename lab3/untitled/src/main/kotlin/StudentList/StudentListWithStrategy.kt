package StudentList

import DataListPack.DataList
import Student.Student
import Student.StudentShort
import java.io.File
import java.io.FileWriter
import kotlin.math.min

class StudentListWithStrategy(var studentListStrategy: StudentListBaseStrategy):StudentListBase() {
    protected val studentList: MutableList<Student> = mutableListOf();



    // Запись в файл
    fun writeToFile(filePath: String, fileName: String) {
        val file = File(filePath + "/${fileName}")
        if (!file.exists()) {
            file.createNewFile()
        }
        val fileWriter = FileWriter(filePath + "/${fileName}")
        this.studentListStrategy.processWrite(fileWriter,this.studentList)
        fileWriter.close()
    }
    // Чтение из файла
    fun readFromFile(filePath: String){
        if (!File(filePath).exists()) throw Exception("Ты откуда взял этот файл")
        else {
            val mainString = File(filePath).readText()
            this.studentListStrategy.processRead(mainString,this.studentList)
        }
    }

    public override fun getStudentById(id: Int) = studentList.find { it.id == id }

    public override fun getKNStudentShortList(k: Int, n: Int) =
        DataList<StudentShort>(studentList.slice(k * n..<min(k * n + n, studentList.size)).map { StudentShort(it) }
            .toTypedArray<StudentShort>());

    public fun sortByInitials() = this.studentList.sortedBy { it.getInitials() }


    private fun addNewStudent(student: Student, id: Int = studentList.maxOf { it.id } + 1) {
        val stud = Student(student.toString())
        stud.id = id
        this.studentList.add(stud)
    }

    public override fun addNewStudent(student: Student) {
        val stud = Student(student.toString())
        stud.id = studentList.maxOf { it.id } + 1
        this.studentList.add(stud)
    }

    public override fun replaceById(id: Int, newStudent: Student) {
        val stud = Student(newStudent.toString())
        stud.id = id
        val ind = this.studentList.indexOf(this.studentList.find { it.id == id })
        if (ind != -1) this.studentList[ind] = stud
        else this.addNewStudent(newStudent, id)
    }

    public override fun deleteById(id: Int) {
        this.studentList.removeAt(this.studentList.indexOf(this.studentList.find { it.id == id }))
    }

    public override fun getStudentShortCount() = this.studentList.size

    public override fun toString() = this.studentList.toString()
}
