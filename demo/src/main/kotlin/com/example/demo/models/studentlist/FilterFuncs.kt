package com.example.demo.models.studentlist

import com.example.demo.models.student.Student
import java.util.*

object FilterFuncs {
    var fieldName:String = "";
    var valueField:String = "";
    fun filterYes(list: List<Student>): MutableList<Student> {
        return list.filter { it: Student -> Objects.toString(it.propertiesReturn().get(fieldName), "").contains(valueField) }.toMutableList()
    }
    fun filterSurnameInitials(list: List<Student>): MutableList<Student> {
        return list.filter { it: Student -> it.formStudentInitials().contains(valueField) }.toMutableList()
    }
    fun filterNoMatter(list: List<Student>): MutableList<Student> {
        return list.filter { it: Student ->
            it.propertiesReturn()[fieldName] == null || it.propertiesReturn()[fieldName].toString().contains(valueField)
        }.toMutableList()
    }
    fun filterNo(list: List<Student>): MutableList<Student> {
        return list.filter { it: Student -> it.propertiesReturn()[fieldName] == null }.toMutableList()
    }
}