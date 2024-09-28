package StudentList

import DataListPack.DataList
import Student.Student
import Student.StudentShort
import kotlin.math.min

abstract class StudentListBase {
    protected val studentList: MutableList<Student> = mutableListOf();

    // Чтение из файла
    abstract fun readFromFile(filePath: String);


    // Запись в файл
    abstract fun writeToFile(filePath: String, fileName: String);

    public fun getStudentById(id: Int) = studentList.find { it.id == id }

    public fun getKNStudentShortList(k: Int, n: Int) =
        DataList<StudentShort>(studentList.slice(k * n..<min(k * n + n, studentList.size)).map { StudentShort(it) }
            .toTypedArray<StudentShort>());

    public fun sortByInitials() = this.studentList.sortedBy { it.getInitials() }


    private fun addNewStudent(student: Student, id: Int = studentList.maxOf { it.id } + 1) {
        val stud = Student(student.toString())
        stud.id = id
        this.studentList.add(stud)
    }

    public fun addNewStudent(student: Student) {
        val stud = Student(student.toString())
        stud.id = studentList.maxOf { it.id } + 1
        this.studentList.add(stud)
    }

    public fun replaceById(id: Int, newStudent: Student) {
        val stud = Student(newStudent.toString())
        stud.id = id
        val ind = this.studentList.indexOf(this.studentList.find { it.id == id })
        if (ind != -1) this.studentList[ind] = stud
        else this.addNewStudent(newStudent, id)
    }

    public fun deletebyId(id: Int) {
        this.studentList.removeAt(this.studentList.indexOf(this.studentList.find { it.id == id }))
    }

    public fun getStudentShortCount() = this.studentList.size

    public override fun toString() = this.studentList.toString()
}
