package com.example.demo.models.studentlist

import com.example.demo.models.student.Student
import com.example.demo.models.student.StudentShort
import java.util.function.Function

interface StudentListAdapter {
    fun getStudentById(id: Int): Student?

    fun getKNStudentShortList(k: Int, n: Int): MutableList<StudentShort>

    fun addNewStudent(student: Student)


    fun replaceById(id: Int, newStudent: Student)

    fun deleteById(id: Int)

    fun getStudentShortCount():Int

    fun sortBy(order:Int,columnName:String)

    fun checkAdapterExisting():Boolean

    fun filterList(function: Function<MutableList<Student>, MutableList<Student>>)

    fun restoreOrderList();
}