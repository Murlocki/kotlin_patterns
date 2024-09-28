package StudentList

import Student.Student
import java.io.FileWriter

abstract class StudentListBaseStrategy {
    abstract fun processWrite(fileWriter:FileWriter, students:MutableList<Student>)
    abstract fun processRead(mainString:String, students:MutableList<Student>)
}