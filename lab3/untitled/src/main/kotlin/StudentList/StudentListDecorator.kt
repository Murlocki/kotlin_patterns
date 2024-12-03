package StudentList

import DataListPack.DataList
import MainPack.UpdateDataInterface
import Student.Student
import Student.StudentShort
import TableGridPack.Controllers.TableViewController
import java.util.function.Function

class StudentListDecorator(private val wrapee:StudentList,private val tableViewController: TableViewController):StudentListComponent{
    override fun subscribe(sub: UpdateDataInterface) {
        this.wrapee.subscribe(sub)
    }

    override fun notifySubs() {
        println(1)
        this.wrapee.notifySubs()
    }

    override fun getStudentById(id: Int): Student? {
        return this.wrapee.getStudentById(id)
    }

    override fun getKNStudentShortList(k: Int, n: Int): DataList<StudentShort> {
        return this.wrapee.getKNStudentShortList(k,n);
    }

    override fun addNewStudent(student: Student) {
        this.wrapee.addNewStudent(student);
    }

    override fun replaceById(id: Int, newStudent: Student) {
        this.wrapee.replaceById(id, newStudent);
    }

    override fun deleteById(id: Int) {
        this.wrapee.deleteById(id);
    }

    override fun getStudentShortCount(): Int {
        return this.wrapee.getStudentShortCount()
    }

    override fun sortByInitials(order: Int) {
        this.wrapee.sortByInitials(order)
    }

    override fun filterList(function: Function<MutableList<Student>, MutableList<Student>>) {
        this.wrapee.filterList(function);
    }

    override fun restoreOrderList() {
        this.wrapee.restoreOrderList();
    }

    override fun checkStExists(): Boolean {
        return this.wrapee.checkStExists();
    }

}