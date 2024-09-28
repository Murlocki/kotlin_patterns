package StudentList

interface StudentListInterface {
    fun createMap(el: Any): HashMap<String, Any?>;

    fun convertMap(hashM: Map<String, Any?>): Map<String, Any>;
}