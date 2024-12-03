package StudentList

import DataListPack.DataList
import Student.Student
import Student.StudentShort
import java.util.function.Function

interface StudentListAdapter {
    fun getStudentById(id: Int): Student?

    fun getKNStudentShortList(k: Int, n: Int): DataList<StudentShort>

    fun addNewStudent(student: Student)


    fun replaceById(id: Int, newStudent: Student)

    fun deleteById(id: Int)

    fun getStudentShortCount():Int

    fun sortBy(order:Int,columnName:String)

    fun checkAdapterExisting():Boolean

    fun filterList(function: Function<MutableList<Student>, MutableList<Student>>)

    fun restoreOrderList();
}