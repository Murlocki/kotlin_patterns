package org.example

import DataList
import Student
import StudentShort
import copyIfPossible
import java.io.File
import java.io.FileWriter
import kotlin.math.min

class StudentListTxt {
    private val studentList: MutableList<Student> = mutableListOf();


    // Чтение из файла
    fun readFromTxt(filePath: String) = if (!File(filePath).exists()) throw Exception("Ты откуда взял этот файл") else File(filePath).readLines().forEach { studentList.add(Student(it)) }

    // Запись в файл
    fun writeToTxt(filePath: String, fileName: String) {
        val file = File(filePath + "/${fileName}")
        if (!file.exists()) {
            file.createNewFile()
        }
        val fileWriter = FileWriter(filePath + "/${fileName}")
        this.studentList.forEach { fileWriter.appendLine(it.toString()) }
        fileWriter.close()
    }

    fun getStudentById(id:Int) = studentList.find { it.id == id }

    fun getKNStudentShortList(k:Int,n:Int) = DataList<StudentShort>(studentList.slice(k*n..<min(k*n+n,studentList.size)).map { StudentShort(it) }.toTypedArray<StudentShort>());

    fun sortByInitials() = this.studentList.sortedBy { it.getInitials() }


    private fun addNewStudent(student:Student,id: Int = studentList.maxOf { it.id }+1){
        val stud = Student(student.toString())
        stud.id = id
        this.studentList.add(stud)
    }
    fun addNewStudent(student:Student){
        val stud = Student(student.toString())
        stud.id = studentList.maxOf { it.id }+1
        this.studentList.add(stud)
    }
    fun replaceById(id:Int,newStudent: Student){
        val stud = Student(newStudent.toString())
        stud.id = id
        val ind = this.studentList.indexOf(this.studentList.find { it.id==id })
        if(ind!=-1) this.studentList[ind] = stud
        else this.addNewStudent(newStudent,id)
    }

    fun deletebyId(id: Int){
        this.studentList.removeAt(this.studentList.indexOf(this.studentList.find { it.id==id }))
    }
    fun getStudentShortCount() = this.studentList.size

    override fun toString() = this.studentList.toString()

}