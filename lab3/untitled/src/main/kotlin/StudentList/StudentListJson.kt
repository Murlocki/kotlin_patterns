package StudentList

import NumberOrStringSerializer
import Student.Student
import java.io.FileWriter

import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.*

class StudentListJson : StudentListBaseExtend(),StudentListInterface {

    override fun createMap(el: Any): HashMap<String, Any?> {
        val map = HashMap<String, Any?>()
        for ((key, value) in el as JsonObject) {
            map[key] = value.toString().replace("\"", "")
        }
        return map
    }

    override fun convertMap(hashM: Map<String, Any?>): Map<String, Any> {
        val res = mutableMapOf<String, Any>()
        for ((key, value) in hashM.entries) {
            if (value != null) res.set(key, if (value is Int) value else value.toString())
        }
        return res
    }

    override fun writeToFile(fileWriter: FileWriter, students: MutableList<Student>) {
        val jsonFormat = Json { prettyPrint = true }
        val res = jsonFormat.encodeToString(
            ListSerializer(MapSerializer(String.serializer(), NumberOrStringSerializer)),
            students.map { convertMap(it.propertiesReturn()) })
        fileWriter.appendLine(res)
    }

    override fun readFromFile(mainString: String, students: MutableList<Student>) {
        val jsonOb = Json.parseToJsonElement(mainString).jsonArray
        jsonOb.forEach { students.add(Student(createMap(it.jsonObject))) }
    }

}
