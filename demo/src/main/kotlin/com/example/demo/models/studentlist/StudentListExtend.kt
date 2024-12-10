package com.example.demo.models.studentlist

class StudentListExtend(private var studentListExtend: StudentListAdapterExtend):StudentList(studentListExtend) {


    public fun processWrite() = studentListExtend.processWrite()
    public fun processRead() = studentListExtend.processRead()
}