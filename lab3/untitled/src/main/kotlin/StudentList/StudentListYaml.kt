package StudentList

import DataListPack.DataList
import NumberOrStringSerializer
import Student.Student
import Student.StudentShort
import com.charleskorn.kaml.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import java.io.File
import java.io.FileWriter
import kotlin.math.min

class StudentListYaml():StudentListBase(),StudentListInterface {
    override fun createMap(el: Any): HashMap<String, Any?> {
        val map = HashMap<String, Any?>()
        for ((key, value) in (el as YamlMap).entries.entries) {
            map[key.content] = value.yamlScalar.content
        }
        println(map)
        return map
    }

    // Чтение из файла
    override fun readFromFile(filePath: String) {
        if (!File(filePath).exists()) throw Exception("Ты откуда взял этот файл")
        else {
            val yamlString = File(filePath).readText()
            val yamlOb = Yaml.default.parseToYamlNode(yamlString).yamlList
            yamlOb.items.forEach {studentList.add(Student(createMap(it.yamlMap)))}
            println(this.studentList)
        }
    }

    override fun convertMap(hashM: Map<String, Any?>): Map<String, Any> {
        val res = mutableMapOf<String, Any>()
        for ((key, value) in hashM.entries) {
            if (value != null) res.set(key, if (value is Int) value else value.toString())
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
        val res = Yaml.default.encodeToString(ListSerializer(MapSerializer(String.serializer(),
            NumberOrStringSerializer
        )),this.studentList.map { convertMap(it.propertiesReturn()) })

        println(res)
        fileWriter.write(res)
        fileWriter.close()
    }
}