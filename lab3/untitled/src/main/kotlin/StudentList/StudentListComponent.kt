package StudentList

import DataListPack.DataList
import MainPack.UpdateDataInterface
import Student.Student
import Student.StudentShort
import java.util.function.Function

interface StudentListComponent {
    fun subscribe(sub: UpdateDataInterface)
    fun notifySubs()
    fun getStudentById(id: Int): Student?
    fun getKNStudentShortList(k: Int, n: Int): DataList<StudentShort>
    fun addNewStudent(student: Student)
    fun replaceById(id: Int, newStudent: Student)
    fun deleteById(id: Int)
    fun getStudentShortCount(): Int
    fun sortByInitials(order: Int)
    fun filterList(function: Function<MutableList<Student>, MutableList<Student>>)
    fun restoreOrderList();
    fun checkStExists(): Boolean
}