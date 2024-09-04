data class Student(
    var surname:String,
    var name:String,
    var patronymic:String,
    var id:Int= autoGenerateId(),
    var phoneNumber:String?=null,
    var telegram:String?=null,
    var email:String?=null,
    var gitHub:String?=null
)
{
    init {
        require(
            Student.isValidPhone(this.phoneNumber),
            { "Phone must be a valid phone number" }
        );
    }
    companion object{
        // Автосоздание id
        var classId:Int = 0;
        fun autoGenerateId():Int{
            classId+=1;
            return classId;
        }

        // Валидация телефона
        fun isValidPhone(phone: String?): Boolean {
            return phone?.matches(Regex("\\+7\\d{10}")) ?: true; // Для примера - только 10 цифр
        }
    }


    // Конструктор через hasmpam класса
    constructor(studentArgs: HashMap<String,Any?>) : this(id = studentArgs.getOrDefault("id", autoGenerateId()) as Int,
        surname = studentArgs["surname"].toString(),
        name = studentArgs["name"].toString(),
        patronymic = studentArgs["patronymic"].toString(),
        phoneNumber = studentArgs.getOrDefault("phoneNumber",null) as String?,
        telegram = studentArgs.getOrDefault("telegram",null).toString(),
        email = studentArgs.getOrDefault("email",null).toString(),
        gitHub = studentArgs.getOrDefault("gitHub",null).toString()) {}

}
