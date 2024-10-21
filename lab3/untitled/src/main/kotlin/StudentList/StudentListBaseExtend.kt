package StudentList

import DataListPack.DataList
import Student.Student
import Student.StudentShort
import java.io.File
import java.io.FileWriter
import kotlin.math.min

abstract class StudentListBaseExtend():StudentListAdapterExtend {
    protected val studentList: MutableList<Student> = mutableListOf();

    abstract fun writeToFile(fileWriter:FileWriter, students:MutableList<Student>)
    abstract fun readFromFile(mainString:String, students:MutableList<Student>)

    // Запись в файл
    open override fun processWrite(filePath: String, fileName: String) {
        val file = File(filePath + "/${fileName}")
        if (!file.exists()) {
            file.createNewFile()
        }
        val fileWriter = FileWriter(filePath + "/${fileName}")
        this.writeToFile(fileWriter,this.studentList)
        fileWriter.close()
    }
    // Чтение из файла
    open override fun processRead(filePath: String){
        if (!File(filePath).exists()) throw Exception("Ты откуда взял этот файл")
        else {
            val mainString = File(filePath).readText()
            this.readFromFile(mainString,this.studentList)
        }
    }

    open override fun getStudentById(id: Int) = studentList.find { it.id == id }

    open override fun getKNStudentShortList(k: Int, n: Int) =
        DataList<StudentShort>(studentList.slice(k * n..<min(k * n + n, studentList.size)).map { StudentShort(it) }
            .toTypedArray<StudentShort>());

    open override fun sortByInitials() = this.studentList.sortedBy { it.getInitials() }


    private fun addNewStudent(student: Student, id: Int = studentList.maxOf { it.id } + 1) {
        val stud = Student(student.toString())
        stud.id = id
        this.studentList.add(stud)
    }

    open override fun addNewStudent(student: Student) {
        val stud = Student(student.toString())
        stud.id = studentList.maxOf { it.id } + 1
        this.studentList.add(stud)
    }

    open override fun replaceById(id: Int, newStudent: Student) {
        val stud = Student(newStudent.toString())
        stud.id = id
        val ind = this.studentList.indexOf(this.studentList.find { it.id == id })
        if (ind != -1) this.studentList[ind] = stud
        else this.addNewStudent(newStudent, id)
    }

    open override fun deleteById(id: Int) {
        this.studentList.removeAt(this.studentList.indexOf(this.studentList.find { it.id == id }))
    }

    open override fun getStudentShortCount() = this.studentList.size

    open override fun toString() = this.studentList.toString()
}
