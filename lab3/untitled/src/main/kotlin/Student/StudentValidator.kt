package Student

class StudentValidator {
    companion object {
        //Валидация фамилии
        internal fun isValidSurname(surname: String): Boolean {
            return surname.matches(Regex("^[A-Z][a-z]*(-([A-Z][a-z]*)|[a-z]+)*$"))
        }
        //Валидация имени
        internal fun isValidName(name: String): Boolean {
            return name.matches(Regex("^[A-Z][a-z]*(-([A-Z][a-z]*)|[a-z]+)*$"))
        }
        //Валидация отчества
        internal fun isValidPatronymic(patronymic: String): Boolean {
            return patronymic.matches(Regex("^[A-Z][a-z]*(-([A-Z][a-z]*)|[a-z]+)*$"))
        }
        // Валидация телефона
        internal fun isValidPhone(phone: String?): Boolean {
            return phone?.matches(Regex("\\+7\\d{10}")) ?: true
        }

        //Валидация телеграм
        internal   fun isValidTelegram(telegram: String?): Boolean {
            return telegram?.matches(Regex("@(?=.{5,64})(?!_)(?!.*__)[a-zA-Z0-9_]+(?<![_.])")) ?: true
        }

        //Валидация почты
        internal   fun isValidEmail(email: String?): Boolean {
            return email?.matches(Regex("^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$")) ?: true
        }

        //Валидация гита
        internal   fun isValidGitHub(gitHub: String?): Boolean {
            return gitHub?.let { !Regex("[$%#@&/?]").matches(it) } ?: true
        }

        // Функция для валидации полей по регулярке
        internal fun <T>validatorFunc(value:T, errorMessage:String, valudatorFunction: (T)->Boolean){
            require(valudatorFunction(value)) { errorMessage }
        }

        // Валидатор фамилии
        internal fun validatorSurname(surname: String) = validatorFunc(surname, "Surname must be a valid surname",
            Companion::isValidSurname
        )

        // Валидатор имени
        internal fun validatorName(name: String) = validatorFunc(name, "Name must be a valid name",
            Companion::isValidName
        )

        // Валидатор отчества
        internal fun validatorPatronymic(patronymic: String) = validatorFunc(patronymic, "Patronymic must be a valid patronymic",
            Companion::isValidPatronymic
        )

        // Валидатор гита
        internal fun validatorGit(gitHub: String?) = validatorFunc(gitHub,"Git must be a valid git",
            Companion::isValidGitHub
        )

        // Валидатор почты
        internal fun validatorEmail(email: String?) = validatorFunc(email,"Email must be a valid email",
            Companion::isValidEmail
        )

        // Валидатор телефона
        internal fun validatorPhoneNumber(phone: String?) = validatorFunc(phone, "Phone must be a valid phone number",
            Companion::isValidPhone
        )

        // Валидатор телеги
        internal fun validatorTelegram(telegram: String?) = validatorFunc(telegram, "Telegram must be a valid telegram",
            Companion::isValidTelegram
        )
    }
}