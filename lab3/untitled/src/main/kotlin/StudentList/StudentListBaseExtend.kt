package StudentList

import DataListPack.DataList
import Student.Student
import Student.StudentShort
import java.io.File
import java.io.FileWriter
import java.util.*
import java.util.function.Function
import kotlin.math.min

abstract class StudentListBaseExtend(var readFilePath:String?, var writeFilePath:String?):StudentListAdapterExtend {
    protected val studentList: MutableList<Student> = mutableListOf();
    private var orderedStudentList: MutableList<Student> = mutableListOf();
    private var indexOrder:MutableList<Int> = mutableListOf();

    init {
        this.processRead()
    }

    abstract fun writeToFile(fileWriter:FileWriter, students:MutableList<Student>)
    abstract fun readFromFile(mainString:String, students:MutableList<Student>)

    // Запись в файл
    open override fun processWrite() {
        val file = File(this.writeFilePath)
        if (!this.checkAdapterExisting()) {
            file.createNewFile()
        }
        val fileWriter = FileWriter(writeFilePath)
        this.writeToFile(fileWriter,this.studentList)
        fileWriter.close()
    }
    // Чтение из файла
    open override fun processRead(){
        if (!this.checkAdapterExisting()) throw Exception("Ошибка")
        else {
            val mainString = File(this.readFilePath).readText()
            this.readFromFile(mainString,this.studentList)
            this.indexOrder = this.studentList.map { it.id }.toMutableList();
            this.orderedStudentList = this.studentList.map{Student(it.toString())}.toMutableList()
        }
    }
    override fun checkAdapterExisting(): Boolean =
        when{
        readFilePath==null -> false;
        writeFilePath==null -> false;
        !File(readFilePath!!).exists()-> false;
        !File(writeFilePath!!).exists() -> false;
        else -> true;
    }
    open override fun getStudentById(id: Int):Student?{
        this.processRead()
        return studentList.find { it.id == id }
    }

    open override fun getKNStudentShortList(k: Int, n: Int) =
        DataList<StudentShort>(orderedStudentList.slice((k-1) * n..<min((k-1) * n + n, orderedStudentList.size)).map { StudentShort(it) }
            .toTypedArray<StudentShort>());

    open override fun sortBy(order:Int,columnName:String){
        if(order==-1){
            this.orderedStudentList.sortByDescending{ Objects.toString(StudentShort(it).propertiesReturn()[columnName],"")}
        }
        else if (order==1){
            this.orderedStudentList.sortBy { Objects.toString(StudentShort(it).propertiesReturn()[columnName],"") }
        }
        else{
            val oldList = this.orderedStudentList;
            this.orderedStudentList = mutableListOf<Student>();
            for (i in indexOrder) {
                this.orderedStudentList.add(oldList.first { it.id == i });
            }

        }
    }


    private fun addNewStudent(student: Student, id: Int = studentList.maxOf { it.id } + 1) {
        val stud = Student(student.toString())
        stud.id = id
        this.studentList.add(stud);

        this.orderedStudentList = this.studentList.map{Student(it.toString())}.toMutableList()
        this.processWrite();
    }

    open override fun addNewStudent(student: Student) {
        val stud = Student(student.toString())
        stud.id = studentList.maxOf { it.id } + 1
        this.studentList.add(stud)

        this.processWrite();
        this.processRead()

    }

    open override fun replaceById(id: Int, newStudent: Student) {
        val stud = Student(newStudent.toString())
        stud.id = id
        val ind = this.studentList.indexOf(this.studentList.find { it.id == id })
        if (ind != -1) this.studentList[ind] = stud
        else this.addNewStudent(newStudent, id)

        this.processWrite();
        this.processRead()
    }

    open override fun deleteById(id: Int) {
        val ind = this.studentList.indexOf(this.studentList.find { it.id == id })
        if(ind!=-1)
        {
            this.studentList.removeAt(this.studentList.indexOf(this.studentList.find { it.id == id }))
            this.orderedStudentList = this.studentList.map{Student(it.toString())}.toMutableList()
            this.processWrite();
        }
        this.processRead()
    }

    open override fun getStudentShortCount() = this.orderedStudentList.size

    open override fun toString() = this.orderedStudentList.toString()

    override fun filterList(function: Function<MutableList<Student>, MutableList<Student>>) {
        this.orderedStudentList = function.apply(this.orderedStudentList).toMutableList()
        this.indexOrder = this.indexOrder.filter { i->this.orderedStudentList.map { it.id }.toList().contains(i) }.toMutableList()
    }

    override fun restoreOrderList() {
        this.processRead()
    }
}
