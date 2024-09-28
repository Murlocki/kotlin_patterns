class DataListStudentShort(studentShortArray: Array<StudentShort>):DataList<StudentShort>(studentShortArray) {
    override fun functionGetPropsNames(): Array<String> = StudentShort.returnPropertyNames().drop(1).toTypedArray<String>()

    override fun getPropertiesOfClass(value: StudentShort): List<Any?> = value.propertiesReturn().values.drop(1).toList()

}