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
            Student.isValidSurname(this.surname),
            { "Surname must be a valid surname" }
        );
        require(
            Student.isValidName(this.name),
            { "Name must be a valid name" }
        );
        require(
            Student.isValidPatronymic(this.patronymic),
            { "Patronymic must be a valid patronymic" }
        );
        require(
            Student.isValidPhone(this.phoneNumber),
            { "Phone must be a valid phone number" }
        );
        require(
            Student.isValidTelegram(this.telegram),
            { "Telegram must be a valid telegram" }
        );
        require(
            Student.isValidEmail(this.email),
            { "Email must be a valid email" }
        );
        require(
            Student.isValidGitHub(this.gitHub),
            { "Git must be a valid git" }
        );
    }

    //Проверка наличия гита
    fun gitExist() = this.gitHub!=null

    //Проверка наличия второго контакта
    fun contactExist() = this.email!=null || this.telegram!=null || this.phoneNumber!=null;

    //Проверка наличия гита и 1 из контактов
    fun validate() = this.gitExist() && this.contactExist();

    companion object{
        // Автосоздание id
        var classId:Int = 0;
        fun autoGenerateId():Int{
            classId+=1;
            return classId;
        }

        // Валидация телефона
        fun isValidPhone(phone: String?): Boolean {
            return phone?.matches(Regex("\\+7\\d{10}")) ?: true;
        }

        //Валидация фамилии
        fun isValidSurname(surname: String): Boolean {
            return surname.matches(Regex("^[A-Z][a-z]*(-([A-Za-z]?)[a-z]*)*$"));
        }
        //Валидация имени
        fun isValidName(name: String): Boolean {
            return name.matches(Regex("^[A-Z][a-z]*(-([A-Za-z]?)[a-z]*)*$"));
        }
        //Валидация отчества
        fun isValidPatronymic(patronymic: String): Boolean {
            return patronymic.matches(Regex("^[A-Z][a-z]*(-([A-Za-z]?)[a-z]*)*$"));
        }

        //Валидация телеграм
        fun isValidTelegram(telegram: String?): Boolean {
            return telegram?.matches(Regex("@(?=.{5,64})(?!_)(?!.*__)[a-zA-Z0-9_]+(?<![_.])")) ?: true;
        }

        //Валидация почты
        fun isValidEmail(email: String?): Boolean {
            return email?.matches(Regex("^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$")) ?: true;
        }

        //Валидация гита
        fun isValidGitHub(gitHub: String?): Boolean {
            return gitHub?.let { !Regex("[$%#@&/?]").matches(it) } ?: true;
        }

    }


    // Конструктор через hasmpam класса
    constructor(studentArgs: HashMap<String,Any?>) : this(id = studentArgs.getOrDefault("id", autoGenerateId()) as Int,
        surname = studentArgs["surname"].toString(),
        name = studentArgs["name"].toString(),
        patronymic = studentArgs["patronymic"].toString(),
        phoneNumber = studentArgs.getOrDefault("phoneNumber",null) as String?,
        telegram = studentArgs.getOrDefault("telegram",null) as String?,
        email = studentArgs.getOrDefault("email",null) as String?,
        gitHub = studentArgs.getOrDefault("gitHub",null) as String?) {}

}
