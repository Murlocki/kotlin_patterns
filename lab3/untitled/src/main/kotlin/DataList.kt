import kotlin.reflect.full.companionObject
import kotlin.reflect.full.functions
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties

open class DataList<T>(val elements: Array<T>) {
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

    private fun getNames(): Array<String>{
        if(this.elements.isEmpty() || this.elements[0]!!::class.companionObject?.memberFunctions?.find{ it.name == "returnPropertyNames" }==null)  return arrayOf<String>()
        else{
            return (this.elements[0]!!::class.companionObject?.memberFunctions?.find{ it.name == "returnPropertyNames" }!!.call(this.elements[0]!!::class.companionObject?.objectInstance) as Set<String>).drop(1).toTypedArray<String>()
        }
    }


    // Метод для получения DataTable (не реализован, так как не имеет информации об объектах)
    protected open fun getPropertiesOfClass(value:T):List<Any?>{
        if(this.elements.isEmpty() || value!!::class.memberFunctions.find{ it.name == "propertiesReturn" }==null)  return listOf<Any?>()
        else{
            return (value!!::class.memberFunctions.find{ it.name == "propertiesReturn" }!!.call(value) as HashMap<String,Any?>).values.drop(1).toList<Any?>()
        }
    }
    fun getData(): DataTable {
        val dataList:MutableList<Array<Any?>> = mutableListOf()
        var rowNumber = 0;
        dataList.add(arrayOf("ID",*getNames()))
        for(el in this.elements){
            dataList.add(
                arrayOf(rowNumber,*getPropertiesOfClass(el).toTypedArray<Any?>())
            )
            rowNumber+=1;
        }
        return DataTable(dataList.toTypedArray<Array<Any?>>())
    }
}