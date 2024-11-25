package StudentList

import Student.Student
import java.io.FileWriter

class StudentListExtend(private var studentListExtend: StudentListAdapterExtend):StudentList(studentListExtend) {


    public fun processWrite() = studentListExtend.processWrite()
    public fun processRead() = studentListExtend.processRead()
}