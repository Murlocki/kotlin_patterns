package StudentList


import Student.Student
import java.io.FileWriter

interface StudentListAdapterExtend:StudentListAdapter  {
    fun processWrite()
    fun processRead()
}