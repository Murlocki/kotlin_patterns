package DataBasePack

import Student.Student
import DataBasePack.DbCon
import java.sql.Connection

class StudentsListDB() {
    private var conn: Connection? = null;

    init {
        DbCon.createConnection()
        this.conn = DbCon.getConnection()
    }

    fun getStudentById(id: Int): Student? {
        val request = "SELECT * FROM ref_student as t where t.id=${id}"
        val result = DbCon.executeSqlSelect(request);
        if (result != null && result.next()) {
            val resultHash:HashMap<String,Any?> = hashMapOf<String,Any?>()
            resultHash.set("id",result.getInt("id"))
            resultHash.set("name",result.getString("name"))
            resultHash.set("surname",result.getString("surname"))
            resultHash.set("patronymic",result.getString("patronymic"))
            resultHash.set("phoneNumber",result.getString("phoneNumber"))
            resultHash.set("gitHub",result.getString("gitHub"))
            resultHash.set("email",result.getString("email"))
            resultHash.set("telegram",result.getString("telegram"))
            result.close();
            return Student(resultHash);
        };
        return null;
    }

    fun getKStudents(n: Int, k: Int): List<Student> {
        val request = "SELECT * FROM ref_student as t ORDER BY t.id OFFSET ${(n-1)*k} ROWS LIMIT ${k}"
        val result = DbCon.executeSqlSelect(request);
        if (result != null) {
            val resultList:MutableList<Student> = mutableListOf()
            while(result.next()){
                val resultHash:HashMap<String,Any?> = hashMapOf<String,Any?>()
                resultHash.set("id",result.getInt("id"))
                resultHash.set("name",result.getString("name"))
                resultHash.set("surname",result.getString("surname"))
                resultHash.set("patronymic",result.getString("patronymic"))
                resultHash.set("phoneNumber",result.getString("phoneNumber"))
                resultHash.set("gitHub",result.getString("gitHub"))
                resultHash.set("email",result.getString("email"))
                resultHash.set("telegram",result.getString("telegram"))
                resultList.add(Student(resultHash));
            }
            result.close();
            return resultList;
        };
        return mutableListOf();
    }

    fun addStudent(student: Student) {
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
        val request = "insert into ref_student(${columns}) values (${values})"
        DbCon.executeSql(request);
    }

    fun updateStudent(id:Int,student: Student) {
        val studentProps = student.propertiesReturn()
        var values = "";
        for(key in studentProps.keys){
            if(studentProps[key] !=null && key!="id"){
                values+="${key}='${studentProps[key]}',"
            }
        }
        values=values.dropLast(1)
        val request = "update ref_student t set ${values} where t.id=${id}"
        DbCon.executeSql(request);
    }

    fun deleteStudent(id: Int) {
        val request = "delete from ref_student as t where t.id=${id}"
        DbCon.executeSql(request);
    }
//
    fun getCount(): Int {
        val request = "SELECT count(*) as c FROM ref_student"
        val result = DbCon.executeSqlSelect(request);
        if (result != null && result.next()) {
            val count = result.getInt("c");
            result.close();
            return count;
        };
        return 0;
    }
}