package StudentList

import DataListPack.DataList
import Student.Student
import Student.StudentShort
import java.io.File
import java.io.FileWriter
import kotlin.math.min

abstract class StudentListBaseExtend():StudentListAdapterExtend {
    protected val studentList: MutableList<Student> = mutableListOf();
    private var orderStudentList: MutableList<Student> = mutableListOf();
    abstract fun writeToFile(fileWriter:FileWriter, students:MutableList<Student>)
    abstract fun readFromFile(mainString:String, students:MutableList<Student>)

    // Запись в файл
    open override fun processWrite(filePath: String, fileName: String) {
        val file = File(filePath + "/${fileName}")
        if (!file.exists()) {
            file.createNewFile()
        }
        val fileWriter = FileWriter(filePath + "/${fileName}")
        this.writeToFile(fileWriter,this.studentList)
        fileWriter.close()
    }
    // Чтение из файла
    open override fun processRead(filePath: String){
        if (!File(filePath).exists()) throw Exception("Ты откуда взял этот файл")
        else {
            val mainString = File(filePath).readText()
            this.readFromFile(mainString,this.studentList)
            this.orderStudentList = this.studentList.map{Student(it.toString())}.toMutableList()
        }
    }
    override fun checkAdapterExisting(): Boolean {
        return this.studentList.size!=0;
    }
    open override fun getStudentById(id: Int) = studentList.find { it.id == id }

    open override fun getKNStudentShortList(k: Int, n: Int) =
        DataList<StudentShort>(orderStudentList.slice((k-1) * n..<min((k-1) * n + n, orderStudentList.size)).map { StudentShort(it) }
            .toTypedArray<StudentShort>());

    open override fun sortByInitials(order:Int){
        if(order==-1) this.studentList.sortByDescending { it.getInitials() }
        else if(order==1) this.studentList.sortBy { it.getInitials() }
        else this.orderStudentList = this.studentList.map{Student(it.toString())}.toMutableList()
    }


    private fun addNewStudent(student: Student, id: Int = studentList.maxOf { it.id } + 1) {
        val stud = Student(student.toString())
        stud.id = id
        this.studentList.add(stud);

        this.orderStudentList = this.studentList.map{Student(it.toString())}.toMutableList()
    }

    open override fun addNewStudent(student: Student) {
        val stud = Student(student.toString())
        stud.id = studentList.maxOf { it.id } + 1
        this.studentList.add(stud)

        this.orderStudentList = this.studentList.map{Student(it.toString())}.toMutableList()
    }

    open override fun replaceById(id: Int, newStudent: Student) {
        val stud = Student(newStudent.toString())
        stud.id = id
        val ind = this.studentList.indexOf(this.studentList.find { it.id == id })
        if (ind != -1) this.studentList[ind] = stud
        else this.addNewStudent(newStudent, id)

        this.orderStudentList = this.studentList.map{Student(it.toString())}.toMutableList()
    }

    open override fun deleteById(id: Int) {
        this.studentList.removeAt(this.studentList.indexOf(this.studentList.find { it.id == id }))
        this.orderStudentList = this.studentList.map{Student(it.toString())}.toMutableList()
    }

    open override fun getStudentShortCount() = this.studentList.size

    open override fun toString() = this.studentList.toString()
}
