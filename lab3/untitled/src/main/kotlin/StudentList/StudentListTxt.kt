package StudentList

import Student.Student
import java.io.FileWriter
import java.util.function.Function

class StudentListTxt(readFilePath:String?,writeFilePath:String?):StudentListBaseExtend(readFilePath,writeFilePath) {

    // Чтение из файла
    override fun readFromFile(mainString: String, students: MutableList<Student>) {
        mainString.split('\n').dropLast(1).forEach {
            students.add(
                Student(it)
            )
        }
    }

    override fun filterList(function: Function<MutableList<Student>, MutableList<Student>>) {
        TODO("Not yet implemented")
    }

    override fun restoreOrderList() {
        TODO("Not yet implemented")
    }

    // Запись в файл
    override fun writeToFile(fileWriter: FileWriter, students: MutableList<Student>) {
        students.forEach { fileWriter.appendLine(it.toString()) }
    }

}