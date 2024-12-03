package StudentList

import DataListPack.DataList
import MainPack.UpdateDataInterface
import Student.Student
import Student.StudentShort
import java.util.function.Function

open class StudentList(private var studentList: StudentListAdapter): StudentListComponent {

    private var subscribers: MutableList<UpdateDataInterface> = mutableListOf();
    override fun subscribe(sub: UpdateDataInterface) {
        this.subscribers.add(sub);
    }

    override fun notifySubs() {
        for (sub in this.subscribers) {
            sub.updatePage()
        }
    }


    public override fun getStudentById(id: Int): Student? = this.studentList.getStudentById(id);

    public override fun getKNStudentShortList(k: Int, n: Int): DataList<StudentShort> =
        this.studentList.getKNStudentShortList(k, n);

    public override fun addNewStudent(student: Student) {
        this.studentList.addNewStudent(student);
        this.notifySubs()
    }

    public override fun replaceById(id: Int, newStudent: Student) {
        this.studentList.replaceById(id, newStudent);
        this.notifySubs()
    };

    public override fun deleteById(id: Int) {
        this.studentList.deleteById(id)
        this.notifySubs()
    };

    public override fun getStudentShortCount(): Int = this.studentList.getStudentShortCount();
    public override fun sortBy(order: Int,columnName:String) = this.studentList.sortBy(order,columnName)

    public override fun filterList(function: Function<MutableList<Student>, MutableList<Student>>) = this.studentList.filterList(function);
    override fun restoreOrderList() {
        this.studentList.restoreOrderList()
    }

    public override fun toString(): String {
        return studentList.toString()
    }

    public override fun checkStExists(): Boolean = this.studentList.checkAdapterExisting();
}