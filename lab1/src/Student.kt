
data class Student(private var surname:String,
                   private var name:String,
                   private var patronymic:String,
                   private var id:Int= autoGenerateId(),
                   private var phoneNumber:String?=null,
                   private var telegram:String?=null,
                   private var email:String?=null,
                   private var gitHub:String?=null)
{
    init {
        require(
            isValidSurname(this.surname),
            { "Surname must be a valid surname" }
        )
        require(
            isValidName(this.name),
            { "Name must be a valid name" }
        )
        require(
            isValidPatronymic(this.patronymic),
            { "Patronymic must be a valid patronymic" }
        )
        require(
            isValidPhone(this.phoneNumber),
            { "Phone must be a valid phone number" }
        )
        require(
            isValidTelegram(this.telegram),
            { "Telegram must be a valid telegram" }
        )
        require(
            isValidEmail(this.email),
            { "Email must be a valid email" }
        )
        require(
            isValidGitHub(this.gitHub),
            { "Git must be a valid git" }
        )
    }

    //Проверка наличия гита
    private fun gitExist() = this.gitHub!=null

    //Проверка наличия второго контакта
    private fun contactExist() = this.email!=null || this.telegram!=null || this.phoneNumber!=null

    //Проверка наличия гита и 1 из контактов
    fun validate() = this.gitExist() && this.contactExist()


    //Метод set_contacts
    private fun checkValueAndPropertyNotNull(value:String?,propertyValue:String?) = value==null && propertyValue!=null || value!=null
    private fun contactSetter(value: String?,propertyValue: String?,setter:(value: String?)->(Unit)){
        if(this.checkValueAndPropertyNotNull(value,propertyValue)){
            setter(value)
        }
    }

    // Сеттер телефона
    private fun phoneSetter(value: String?) {
        require(isValidPhone(value))
        this.phoneNumber = value
    }
    private fun setPhoneNumber(value:String?) = this.contactSetter(value, this.phoneNumber) { this.phoneSetter(it) }


    // Сеттер телеграмма
    private fun telegramSetter(value: String?) {
        require(isValidTelegram(value))
        this.telegram = value
    }
    private fun setTelegram(value:String?) = this.contactSetter(value,this.telegram,this::telegramSetter)

    //Сеттер почты
    private fun emailSetter(value: String?) {
        require(isValidEmail(value))
        this.email = value
    }
    private fun setEmail(value:String?) = this.contactSetter(value,this.email,this::emailSetter)

    //Сеттер гита
    private fun gitSetter(value: String?) {
        require(isValidGitHub(value))
        this.gitHub = value
    }
    private fun setGit(value:String?) = this.contactSetter(value,this.gitHub,this::gitSetter);

    // Сеттер контактов
    fun setContacts(contacts:HashMap<String,String?>){
        this.setPhoneNumber(contacts.getOrDefault("phoneNumber",this.phoneNumber))
        this.setGit(contacts.getOrDefault("gitHub",this.gitHub))
        this.setEmail(contacts.getOrDefault("email",this.email))
        this.setTelegram(contacts.getOrDefault("telegram",this.telegram))
    }

    companion object{
        // Автосоздание id
        var classId:Int = 0
        fun autoGenerateId():Int{
            classId+=1
            return classId
        }

        // Валидация телефона
        fun isValidPhone(phone: String?): Boolean {
            return phone?.matches(Regex("\\+7\\d{10}")) ?: true
        }

        //Валидация фамилии
        fun isValidSurname(surname: String): Boolean {
            return surname.matches(Regex("^[A-Z][a-z]*(-([A-Za-z]?)[a-z]*)*$"))
        }
        //Валидация имени
        fun isValidName(name: String): Boolean {
            return name.matches(Regex("^[A-Z][a-z]*(-([A-Za-z]?)[a-z]*)*$"))
        }
        //Валидация отчества
        fun isValidPatronymic(patronymic: String): Boolean {
            return patronymic.matches(Regex("^[A-Z][a-z]*(-([A-Za-z]?)[a-z]*)*$"))
        }

        //Валидация телеграм
        fun isValidTelegram(telegram: String?): Boolean {
            return telegram?.matches(Regex("@(?=.{5,64})(?!_)(?!.*__)[a-zA-Z0-9_]+(?<![_.])")) ?: true
        }

        //Валидация почты
        fun isValidEmail(email: String?): Boolean {
            return email?.matches(Regex("^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$")) ?: true
        }

        //Валидация гита
        fun isValidGitHub(gitHub: String?): Boolean {
            return gitHub?.let { !Regex("[$%#@&/?]").matches(it) } ?: true
        }

    }

    // Конструктор через hasmpam класса
    constructor(studentArgs: HashMap<String,Any?>) : this(
        surname = studentArgs["surname"].toString(),
        name = studentArgs["name"].toString(),
        patronymic = studentArgs["patronymic"].toString(),
        phoneNumber = studentArgs.getOrDefault("phoneNumber",null) as String?,
        telegram = studentArgs.getOrDefault("telegram",null) as String?,
        email = studentArgs.getOrDefault("email",null) as String?,
        gitHub = studentArgs.getOrDefault("gitHub",null) as String?)

}
