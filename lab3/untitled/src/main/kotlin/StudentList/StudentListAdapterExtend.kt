package StudentList


import Student.Student
import java.io.FileWriter

interface StudentListAdapterExtend:StudentListAdapter  {

    fun processWrite(filePath: String, fileName: String)

    fun processRead(filePath: String)

}