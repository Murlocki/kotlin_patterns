package StudentList

import Student.Student
import java.io.FileWriter

class StudentListExtend(private var studentListExtend: StudentListAdapterExtend):StudentList(studentListExtend) {
    public fun processWrite(filePath: String, fileName: String) = studentListExtend.processWrite(filePath, fileName);
    public fun processRead(filePath: String) = studentListExtend.processRead(filePath);
}