package com.example.demo.controllers
import com.example.demo.models.student.Student
import com.example.demo.models.student.StudentShort
import com.example.demo.models.studentlist.FilterFuncs
import com.example.demo.models.studentlist.StudentList
import com.example.demo.models.studentlist.StudentListDB
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/students")
class StudentListController() {
    private val studentService: StudentList = StudentList(StudentListDB());
    private val logger = LoggerFactory.getLogger(StudentListController::class.java)

    @PostMapping
    fun addStudent(@RequestBody student: Student?): ResponseEntity<Void> {
        logger.info(student.toString());
        if (student != null) {
            studentService.addNewStudent(student)
        }
        return ResponseEntity(HttpStatus.CREATED)
    }
    @GetMapping("/{id}")
    fun read(@PathVariable id: Int): ResponseEntity<Student> {
        val student: Student? = studentService.getStudentById(id)
        return if (student == null) ResponseEntity(HttpStatus.NOT_FOUND) else ResponseEntity(student, HttpStatus.OK)
    }
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Void> {
        studentService.deleteById(id)
        return if (studentService.getStudentById(id)==null) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.NOT_FOUND)
    }
    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody student: Student?): ResponseEntity<Void> {
        if (student != null) {
            studentService.replaceById(id, student)
        }
        return ResponseEntity(HttpStatus.OK)
    }
    @GetMapping("/count")
    fun getStudentShortCount(): ResponseEntity<Int> {
        val count = studentService.getStudentShortCount()
        return ResponseEntity(count, HttpStatus.OK)
    }
    @PostMapping("/restoreOrderList")
    fun restoreOrderList(): ResponseEntity<Void> {
        studentService.restoreOrderList()
        return ResponseEntity(HttpStatus.OK)
    }
    @GetMapping("/sort/{columnName}/{order}")
    fun getStudentShortCount(@PathVariable columnName: String,@PathVariable order: Int): ResponseEntity<Void> {
        studentService.sortBy(order,columnName)
        return ResponseEntity(HttpStatus.OK)
    }
    @PostMapping("/filter/{fieldName}/{functionName}")
    fun filterList(@PathVariable fieldName: String,@PathVariable functionName: String,@RequestBody valueField:String): ResponseEntity<Void> {
        FilterFuncs.fieldName = fieldName;
        FilterFuncs.valueField = valueField;
        when (functionName) {
            "No" -> this.studentService.filterList(FilterFuncs::filterNo);
            "Yes" -> this.studentService.filterList(FilterFuncs::filterYes);
            "NoMatter" -> this.studentService.filterList(FilterFuncs::filterNoMatter);
            "SurnameInitials" -> this.studentService.filterList(FilterFuncs::filterSurnameInitials);
        }
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/studentShortList/{k}/{n}")
    fun getKNStudentShortList(@PathVariable k: Int, @PathVariable n: Int): ResponseEntity<MutableList<StudentShort>>{
        return ResponseEntity(this.studentService.getKNStudentShortList(k, n), HttpStatus.OK);
    }
}