package StudentList

import DataListPack.DataList
import Student.Student
import Student.StudentShort
import kotlin.math.min

abstract class StudentListBase {
    abstract fun getStudentById(id: Int):Student?

    abstract fun getKNStudentShortList(k: Int, n: Int):DataList<StudentShort>

    abstract fun addNewStudent(student: Student)


    abstract fun replaceById(id: Int, newStudent: Student)

    abstract fun deleteById(id: Int)

    abstract fun getStudentShortCount():Int
}