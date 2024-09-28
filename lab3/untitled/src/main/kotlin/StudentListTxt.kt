package org.example

import DataList
import Student
import StudentShort
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

}