package StudentList

import DataListPack.DataList
import NumberOrStringSerializer
import Student.Student
import Student.StudentShort
import java.io.File
import java.io.FileWriter
import kotlin.math.min

import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.*

class StudentListJson:StudentListBase(),StudentListInterface {

    override fun createMap(el:Any):HashMap<String,Any?>{
        val map = HashMap<String, Any?>()
        for ((key, value) in el as JsonObject) {
            map[key] = value.toString().replace("\"","")
        }
        return map
    }
    // Чтение из файла
    override fun readFromFile(filePath: String){
        if (!File(filePath).exists()) throw Exception("Ты откуда взял этот файл")
        else {
            val jsonString = File(filePath).readText()
            val jsonOb = Json.parseToJsonElement(jsonString).jsonArray
            jsonOb.forEach { studentList.add(Student(createMap(it.jsonObject))) }
        }
    }
    override fun convertMap(hashM:Map<String,Any?>):Map<String,Any>{
        val res = mutableMapOf<String,Any>()
        for((key,value) in hashM.entries){
            if(value!=null) res.set(key,if(value is Int) value else value.toString())
        }
        return res
    }
    // Запись в файл
    override fun writeToFile(filePath: String, fileName: String) {
        val file = File(filePath + "/${fileName}")
        if (!file.exists()) {
            file.createNewFile()
        }
        val fileWriter = FileWriter(filePath + "/${fileName}")
        val jsonFormat = Json { prettyPrint = true }
        val res = jsonFormat.encodeToString(ListSerializer(MapSerializer(String.serializer(), NumberOrStringSerializer)),this.studentList.map { convertMap(it.propertiesReturn()) })
        println(res)
        fileWriter.write(res)
        fileWriter.close()
    }
}