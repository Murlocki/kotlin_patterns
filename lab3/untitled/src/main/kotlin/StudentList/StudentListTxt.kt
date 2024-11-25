package StudentList

import DataListPack.DataList
import Student.Student
import Student.StudentShort
import java.io.File
import java.io.FileWriter
import kotlin.math.min

class StudentListTxt(readFilePath:String?,writeFilePath:String?):StudentListBaseExtend(readFilePath,writeFilePath) {

    // Чтение из файла
    override fun readFromFile(mainString: String, students: MutableList<Student>) = mainString.split('\n').forEach { students.add(
        Student(it)
    ) }

    // Запись в файл
    override fun writeToFile(fileWriter: FileWriter, students: MutableList<Student>) {
        students.forEach { fileWriter.appendLine(it.toString()) }
    }

}