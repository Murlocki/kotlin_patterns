package com.example.demo.models.studentlist
import com.example.demo.models.student.Student
import com.example.demo.models.student.StudentShort
import java.util.*
import java.util.function.Function

open class StudentList(private var studentList: StudentListAdapter) {

    fun getStudentById(id: Int): Student? = this.studentList.getStudentById(id);

    fun getKNStudentShortList(k: Int, n: Int): MutableList<StudentShort>{
        this.restSourceStudentLisT()
        return this.studentList.getKNStudentShortList(k, n);
    }

    fun addNewStudent(student: Student) {
        this.restSourceStudentLisT()
        this.studentList.addNewStudent(student);
    }

    fun replaceById(id: Int, newStudent: Student) {
        this.restSourceStudentLisT()
        this.studentList.replaceById(id, newStudent);
    };

    fun deleteById(id: Int) {
        this.restSourceStudentLisT()
        this.studentList.deleteById(id)
    };

    fun getStudentShortCount(): Int{
        this.restSourceStudentLisT()
        return this.studentList.getStudentShortCount()
    };
    fun sortBy(order: Int, columnName:String){
        this.restSourceStudentLisT()
        this.studentList.sortBy(order,columnName)
    }

    fun filterList(function: Function<MutableList<Student>, MutableList<Student>>){
        this.restSourceStudentLisT()
        this.studentList.filterList(function)
    };
    fun restoreOrderList() {
        this.restSourceStudentLisT()
        this.studentList.restoreOrderList()
    }

    override fun toString(): String {
        return studentList.toString()
    }

    private fun checkStExists(): Boolean = this.studentList.checkAdapterExisting();

    private fun restSourceStudentLisT(){
        if(!this.checkStExists())createSourceStudentList();
    }

    private fun createSourceStudentList() {
        //Создаем модель списка студентов
        val st: StudentListDB = StudentListDB()
        if (st.checkAdapterExisting()) {
            this.studentList = st;
        }

        //Проверка для других списков
        val list = LinkedList<StudentListAdapterExtend>()
        list.add(StudentListTxt("src/main/resources/t.txt", "src/main/resources/t.txt"))
        list.add(StudentListYaml("src/main/resources/test.yaml", "src/main/resources/test.yaml"))
        list.add(StudentListJson("src/main/resources/file.json", "src/main/resources/file.json"))

        var resultList: StudentListAdapterExtend? = null
        for (studentListAdapter in list) {
            if (studentListAdapter.checkAdapterExisting()) {
                resultList = studentListAdapter
                this.studentList = resultList
                break;
            }
        }
        this.studentList = StudentListDB()
    }
}