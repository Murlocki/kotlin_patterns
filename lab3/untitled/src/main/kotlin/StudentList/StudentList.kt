package StudentList

import DataListPack.DataList
import MainPack.UpdateDataInterface
import Student.Student
import Student.StudentShort

open class StudentList(private var studentList: StudentListAdapter) {

    private var subscribers: MutableList<UpdateDataInterface> = mutableListOf();
    fun subscribe(sub: UpdateDataInterface) {
        this.subscribers.add(sub);
    }

    private fun notifySubs() {
        for (sub in this.subscribers) {
            sub.updatePage()
        }
    }


    public fun getStudentById(id: Int): Student? = this.studentList.getStudentById(id);

    public fun getKNStudentShortList(k: Int, n: Int): DataList<StudentShort> =
        this.studentList.getKNStudentShortList(k, n);

    public fun addNewStudent(student: Student) {
        this.studentList.addNewStudent(student);
        this.notifySubs()
    }

    public fun replaceById(id: Int, newStudent: Student) {
        this.studentList.replaceById(id, newStudent);
        this.notifySubs()
    };

    public fun deleteById(id: Int) {
        this.studentList.deleteById(id)
        this.notifySubs()
    };

    public fun getStudentShortCount(): Int = this.studentList.getStudentShortCount();
    public fun sortByInitials(order: Int) = this.studentList.sortByInitials(order)

    public override fun toString(): String {
        return studentList.toString()
    }

    public fun checkStExists(): Boolean = this.studentList.checkAdapterExisting();
}