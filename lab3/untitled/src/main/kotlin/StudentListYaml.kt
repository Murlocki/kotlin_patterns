import com.charleskorn.kaml.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import java.io.File
import java.io.FileWriter
import kotlin.math.min
import kotlinx.serialization.decodeFromString

class StudentListYaml {
    private val studentList: MutableList<Student> = mutableListOf();

    fun createMap(el: YamlMap): HashMap<String, Any?> {
        val map = HashMap<String, Any?>()
        for ((key, value) in el.entries.entries) {
            map[key.content] = value.yamlScalar.content
        }
        println(map)
        return map
    }

    // Чтение из файла
    fun readFromYaml(filePath: String) {
        if (!File(filePath).exists()) throw Exception("Ты откуда взял этот файл")
        else {
            val yamlString = File(filePath).readText()
            val yamlOb = Yaml.default.parseToYamlNode(yamlString).yamlList
            yamlOb.items.forEach {studentList.add(Student(createMap(it.yamlMap)))}
            println(this.studentList)
        }
    }

    fun convertMap(hashM: Map<String, Any?>): Map<String, Any> {
        val res = mutableMapOf<String, Any>()
        for ((key, value) in hashM.entries) {
            if (value != null) res.set(key, if (value is Int) value else value.toString())
        }
        return res
    }

    // Запись в файл
    fun writeToYaml(filePath: String, fileName: String) {
        val file = File(filePath + "/${fileName}")
        if (!file.exists()) {
            file.createNewFile()
        }
        val fileWriter = FileWriter(filePath + "/${fileName}")
        val res = Yaml.default.encodeToString(ListSerializer(MapSerializer(String.serializer(), NumberOrStringSerializer)),this.studentList.map { convertMap(it.propertiesReturn()) })

        println(res)
        fileWriter.write(res)
        fileWriter.close()
    }

    fun getStudentById(id: Int) = studentList.find { it.id == id }

    fun getKNStudentShortList(k: Int, n: Int) =
        DataList<StudentShort>(studentList.slice(k * n..<min(k * n + n, studentList.size)).map { StudentShort(it) }
            .toTypedArray<StudentShort>());

    fun sortByInitials() = this.studentList.sortedBy { it.getInitials() }


    private fun addNewStudent(student: Student, id: Int = studentList.maxOf { it.id } + 1) {
        val stud = Student(student.toString())
        stud.id = id
        this.studentList.add(stud)
    }

    fun addNewStudent(student: Student) {
        val stud = Student(student.toString())
        stud.id = studentList.maxOf { it.id } + 1
        this.studentList.add(stud)
    }

    fun replaceById(id: Int, newStudent: Student) {
        val stud = Student(newStudent.toString())
        stud.id = id
        val ind = this.studentList.indexOf(this.studentList.find { it.id == id })
        if (ind != -1) this.studentList[ind] = stud
        else this.addNewStudent(newStudent, id)
    }

    fun deletebyId(id: Int) {
        this.studentList.removeAt(this.studentList.indexOf(this.studentList.find { it.id == id }))
    }

    fun getStudentShortCount() = this.studentList.size

    override fun toString() = this.studentList.toString()
}