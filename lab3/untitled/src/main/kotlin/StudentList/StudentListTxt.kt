package StudentList

import DataListPack.DataList
import Student.Student
import Student.StudentShort
import java.io.File
import java.io.FileWriter
import kotlin.math.min

class StudentListTxt():StudentListBase() {

    // Чтение из файла
    override fun readFromFile(filePath: String) = if (!File(filePath).exists()) throw Exception("Ты откуда взял этот файл") else File(filePath).readLines().forEach { studentList.add(
        Student(it)
    ) }

    // Запись в файл
    override fun writeToFile(filePath: String, fileName: String) {
        val file = File(filePath + "/${fileName}")
        if (!file.exists()) {
            file.createNewFile()
        }
        val fileWriter = FileWriter(filePath + "/${fileName}")
        this.studentList.forEach { fileWriter.appendLine(it.toString()) }
        fileWriter.close()
    }

}