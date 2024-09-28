import java.io.File
import java.io.FileWriter
import kotlin.math.min

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import kotlinx.serialization.json.Json.Default.encodeToJsonElement

class StudentListJson {
    private val studentList: MutableList<Student> = mutableListOf();

    fun createMap(el:JsonObject):HashMap<String,Any?>{
        val map = HashMap<String, Any?>()
        for ((key, value) in el) {
            map[key] = value.toString().replace("\"","")
        }
        return map
    }
    // Чтение из файла
    fun readFromJson(filePath: String){
        if (!File(filePath).exists()) throw Exception("Ты откуда взял этот файл")
        else {
            val jsonString = File(filePath).readText()
            val jsonOb = Json.parseToJsonElement(jsonString).jsonArray
            jsonOb.forEach { studentList.add(Student(createMap(it.jsonObject))) }
        }
    }
    fun convertMap(hashM:Map<String,Any?>):Map<String,Any>{
        val res = mutableMapOf<String,Any>()
        for((key,value) in hashM.entries){
            if(value!=null) res.set(key,if(value is Int) value else value.toString())
        }
        return res
    }
    // Запись в файл
    fun writeToJson(filePath: String, fileName: String) {
        val file = File(filePath + "/${fileName}")
        if (!file.exists()) {
            file.createNewFile()
        }
        val fileWriter = FileWriter(filePath + "/${fileName}")
        val jsonFormat = Json { prettyPrint = true }
        val res = jsonFormat.encodeToString(ListSerializer(MapSerializer(String.serializer(),NumberOrStringSerializer)),this.studentList.map { convertMap(it.propertiesReturn()) })
        println(res)
        fileWriter.write(res)
        fileWriter.close()
    }

    fun getStudentById(id:Int) = studentList.find { it.id == id }

    fun getKNStudentShortList(k:Int,n:Int) = DataList<StudentShort>(studentList.slice(k*n..<min(k*n+n,studentList.size)).map { StudentShort(it) }.toTypedArray<StudentShort>());

    fun sortByInitials() = this.studentList.sortedBy { it.getInitials() }


    private fun addNewStudent(student:Student,id: Int = studentList.maxOf { it.id }+1){
        val stud = Student(student.toString())
        stud.id = id
        this.studentList.add(stud)
    }
    fun addNewStudent(student:Student){
        val stud = Student(student.toString())
        stud.id = studentList.maxOf { it.id }+1
        this.studentList.add(stud)
    }
    fun replaceById(id:Int,newStudent: Student){
        val stud = Student(newStudent.toString())
        stud.id = id
        val ind = this.studentList.indexOf(this.studentList.find { it.id==id })
        if(ind!=-1) this.studentList[ind] = stud
        else this.addNewStudent(newStudent,id)
    }

    fun deletebyId(id: Int){
        this.studentList.removeAt(this.studentList.indexOf(this.studentList.find { it.id==id }))
    }
    fun getStudentShortCount() = this.studentList.size

    override fun toString() = this.studentList.toString()
}