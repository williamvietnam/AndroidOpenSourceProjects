package com.william.classes_objects

fun main() {

}

/*
* Khai báo primary constructor in kotlin:
* Cách 1:
* */
class Student constructor(id: Int, name: String) {
    init {
        println("This is my primary constructor in kotlin: ")
        println("ID: $id \nName: $name")
    }
}

/*
* Khai báo primary constructor in kotlin:
* Cách 2:
* */
class Student2(id: Int, name: String) {
    init {
        println("This is my primary constructor in kotlin: ")
        println("ID: $id \nName: $name")
    }
}

/*
* Khai báo secondary constructor in kotlin:
* */
class Teacher {
    private var name: String = "William"
    private var age: String = "24"
    private var subject: String = "Information"
    private var sex: String = "Men"

    public var Name: String
        set(value) {
            name = value
        }
        get() {
            return name
        }

    public var Age: String
        set(value) {
            age = value
        }
        get() {
            return age
        }
    public var Subject: String
        set(value) {
            subject = value
        }
        get() {
            return subject
        }

    public var Sex: String
        set(value) {
            sex = value
        }
        get() {
            return sex
        }

    constructor() {
        println("This is my secondary constructor 0 parameter")
    }

    constructor(name: String, age: String, subject: String) {
        println("This is my secondary constructor 3 parameter")
        println("Name: $name \nAge: $age \nSubject: $subject")
    }

    constructor(name: String, age: String, subject: String, sex: String) {
        println("This is my secondary constructor 4 parameter")
        println("Name: $name \nAge: $age \nSubject: $subject \nSex: $sex")
    }
}

