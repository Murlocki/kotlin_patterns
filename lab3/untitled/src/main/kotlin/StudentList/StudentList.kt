package StudentList

import DataListPack.DataList
import Student.Student
import Student.StudentShort

open class StudentList(private var studentList:StudentListAdapter) {
    public fun getStudentById(id: Int): Student? = this.studentList.getStudentById(id);

    public fun getKNStudentShortList(k: Int, n: Int): DataList<StudentShort> = this.studentList.getKNStudentShortList(k,n);

    public fun addNewStudent(student: Student) = this.studentList.addNewStudent(student);

    public fun replaceById(id: Int, newStudent: Student) = this.studentList.replaceById(id,newStudent);

    public fun deleteById(id: Int) = this.studentList.deleteById(id);

    public fun getStudentShortCount():Int = this.studentList.getStudentShortCount();
    public fun sortByInitials():List<Student>? = this.studentList.sortByInitials()

    public override fun toString(): String {
        return studentList.toString()
    }
}