abstract class DataList<T>(val elements: Array<T>) {
    private val selectedIndices = mutableSetOf<Int>()
    // Метод для выделения элемента по номеру
    fun select(number: Int) {
        if (number in elements.indices) {
            selectedIndices.add(number)
        } else {
            throw IndexOutOfBoundsException("Индекс вне диапазона")
        }
    }
    // Метод для получения массива ID выделенных элементов
    fun getSelectedIds() = selectedIndices.toIntArray()

    // Метод для получения массива наименований атрибутов (не реализован, так как не имеет информации об объектах)
    abstract fun getNames(): Array<String>

    // Метод для получения DataTable (не реализован, так как не имеет информации об объектах)
    abstract fun getData(): DataTable
}