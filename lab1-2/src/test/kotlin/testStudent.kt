import org.junit.jupiter.api.Test

class testStudent {
    @Test
    fun testReadFromTxt(){
        val students = mutableListOf<Student>();
        students.add(Student("Aaaaa","Bbbbbb","Cccccc"));
        students.add(Student("Aabbb-Abbb","Bbbbbb","Cccccc", telegramValue = "@elelelelele"));
        students.add(Student("Aaabbb","Bbb-Cbbb","Cccccc", gitHubValue = "eeefefefef"));
        students.add(Student("A-B","Bbbbbb","Cccccc", emailValue = "kk@gmial.com"));
        students.add(Student("B","Vbbbbb","Cccccc", phoneNumberValue = "+79889889898"));
        students.add(Student(hashMapOf(Pair("name","E"),Pair("surname","Bbbbbb"),Pair("patronymic","Cccccc"))));
        println(Student.readFromTxt("src/main/kotlin/t.txt").forEach { assert(it.getInfo()==students.find { el->el.id==it.id }?.getInfo()) })
    }
    @Test
    fun testWriteToTxt(){
        val students = Student.readFromTxt("src/main/kotlin/t.txt");
        Student.writeToTxt("src/main/kotlin","out.txt",students);
        println(Student.readFromTxt("src/main/kotlin/out.txt").forEach { assert(it.getInfo()==students.find { el->el.id==it.id }?.getInfo()) })
    }
    @Test
    fun testDataListStudentShort(){
        val students = mutableListOf<Student>();
        students.add(Student("Aaaaa","Bbbbbb","Cccccc"));
        students.add(Student("Aabbb-Abbb","Bbbbbb","Cccccc", telegramValue = "@elelelelele"));
        students.add(Student("Aaabbb","Bbb-Cbbb","Cccccc", gitHubValue = "eeefefefef"));
        students.add(Student("A-B","Bbbbbb","Cccccc", emailValue = "kk@gmial.com"));
        students.add(Student("B","Vbbbbb","Cccccc", phoneNumberValue = "+79889889898"));
        students.add(Student(hashMapOf(Pair("name","E"),Pair("surname","Bbbbbb"),Pair("patronymic","Cccccc"))));
        var dat = DataListStudentShort(students.map { StudentShort(it) }.toTypedArray<StudentShort>())
        assert(dat.getData().getRowCount()==6)
        assert(dat.getData().getColumnCount()==4)
    }
}