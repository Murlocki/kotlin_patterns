package DataBasePack

import Student.Student
import DataListPack.DataList
import Student.StudentShort
import StudentList.StudentListAdapter
import java.util.*
import java.util.function.Function
import kotlin.collections.HashMap
import kotlin.math.min

class StudentListDB():StudentListAdapter {
    private var conn: DbCon? = DbCon;
    private var studentList: MutableList<Student> = mutableListOf();
    private var orderedStudentList: MutableList<Student> = mutableListOf();
    private var indexOrder:MutableList<Int> = mutableListOf()
    public var tableName = "ref_student"
    init {
        conn?.createConnection()
        this.read()
    }

    fun read() {
        val request = "SELECT * FROM $tableName"
        val result = this.conn?.executeSqlSelect(request);
        if (result != null) {
            val resultList: MutableList<Student> = mutableListOf()
            while (result.next()) {
                val resultHash: HashMap<String, Any?> = hashMapOf<String, Any?>()
                resultHash.set("id", result.getInt("id"))
                resultHash.set("name", result.getString("name"))
                resultHash.set("surname", result.getString("surname"))
                resultHash.set("patronymic", result.getString("patronymic"))
                resultHash.set("phoneNumber", result.getString("phoneNumber"))
                resultHash.set("gitHub", result.getString("gitHub"))
                resultHash.set("email", result.getString("email"))
                resultHash.set("telegram", result.getString("telegram"))
                resultList.add(Student(resultHash));
            }
            result.close();
            this.studentList = resultList;
            this.indexOrder = this.studentList.map { it.id }.toMutableList();
            this.orderedStudentList = this.studentList.map{Student(it.toString())}.toMutableList()
        }
    }

    override fun checkAdapterExisting():Boolean {
        return this.conn?.getConnection()!=null
    }

    override fun filterList(function: Function<MutableList<Student>, MutableList<Student>>) {
        this.orderedStudentList = function.apply(this.orderedStudentList).toMutableList()
        this.indexOrder = this.indexOrder.filter { i->this.orderedStudentList.map { it.id }.toList().contains(i) }.toMutableList()
    }

    override fun restoreOrderList() {
        this.read()
    }

    override fun getStudentById(id: Int): Student? {
        this.read()
        return this.studentList.find { it.id == id }
    }

    override fun getKNStudentShortList(k: Int, n: Int): DataList<StudentShort> {
        return DataList<StudentShort>(orderedStudentList.slice((k-1) * n..<min((k-1) * n + n, orderedStudentList.size)).map { StudentShort(it) }
            .toTypedArray<StudentShort>());
    }

    override fun addNewStudent(student: Student) {
        val studentProps = student.propertiesReturn()
        var columns = "";
        var values = "";
        for(key in studentProps.keys){
            if(studentProps[key] !=null && key!="id"){
                columns+="${key},"
                values+="'${studentProps[key]}',"
            }
        }
        columns=columns.dropLast(1)
        values=values.dropLast(1)
        val request = "insert into $tableName(${columns}) values (${values})"
        this.conn?.executeSql(request);
        this.read()
    }

    override fun replaceById(id:Int,newStudent: Student) {
        val studentProps = newStudent.propertiesReturn()
        var values = "";
        for(key in studentProps.keys){
            if(studentProps[key] !=null && key!="id"){
                values+="${key}='${studentProps[key]}',"
            }
            else if (studentProps[key] ==null && key!="id"){
                values+="${key}=${studentProps[key]},"
            }
        }
        values=values.dropLast(1)
        val request = "update $tableName t set ${values} where t.id=${id}"
        this.conn?.executeSql(request);
        this.read()
    }

    override fun deleteById(id: Int) {
        val request = "delete from $tableName as t where t.id=${id}"
        this.conn?.executeSql(request);
        this.read()
    }

    override fun getStudentShortCount(): Int {
        return this.orderedStudentList.size
    }

    override fun sortBy(order:Int,columnName:String) {
        println(this.orderedStudentList)
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

}