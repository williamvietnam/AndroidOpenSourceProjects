package com.william.classes_objects

/*
* learn DataClass, EnumClass, InnerClass, NestedClass
* */
fun main(args: Array<String>) {
//    val user1 = User("William", "williamNB", "giang123","Hanoi")
//    println("Name: ${user1.name} \nNick: ${user1.nick} \nPass: ${user1.pass} \nAddress: ${user1.address}")

    /*
    *
    * */
//    val testNestedClass = Outer.Nested().display()
//    val testMethodInOuterClass = Outer().show()


    val studentWilliam = SinhVien().xepLoaiHS(5.6)
    if (studentWilliam == XepLoai.TrungBinh){
        println("Xep loai Trung Binh")
    }else{
        println("Xep loai ko Trung Binh")
    }
}

/*
* DataClass: là kiểu class cung cấp cho chúng ta sẵn các method sau:
* equals()/hashCode()
* toString()
* componentN()
* copy()
* */
data class User(val name: String, val nick: String, val pass: String, val address: String)

/*
*NestedClass: là 1 class nằm trong 1 class
* */
class Outer {
    var string: String = "Hello Nested Class";

    class Nested {
        fun display() {
            println("I am in nested class")
        }
    }

    fun show() {
        println("I am in outer class")
        Outer.Nested().display()
    }
}

/*
* InnerClass: cũng là 1 class nằm trong 1 class giống nestedClass
* Sự khác biệt:
* - Các method của nestedClass không thể truy xuất dc data của class ngoài
*   nhưng với InnerClass thì có thể truy xuất được data của class ngoài
* - Với innerClass thì có thêm từ khóa Inner trước class (còn nested thì ko có gì cả)
* */
class Out {
    var string: String = "Hello Inner Class";

    inner class Inner {
        fun display() {
            println(string)
        }
    }

    fun show() {
        println("I am in outer class")
        Outer.Nested().display()
    }
}

/*
* EnumClass: là 1 tập các thuộc tính (gần giống kiểu struct or enum trong c++)
* */
enum class XepLoai {
    XuatSac, Gioi, Kha, TrungBinh, Yeu, Kem
} //--> Sử dụng enumClass xepLoai:

class SinhVien() {
    fun xepLoaiHS(diemTB: Double): XepLoai {
        return when (diemTB) {
            in 9.1..10.0 -> XepLoai.XuatSac
            in 8.0..9.0 -> XepLoai.Gioi
            in 6.0..7.9 -> XepLoai.Kha
            in 4.5..5.9 -> XepLoai.TrungBinh
            in 3.0..4.4 -> XepLoai.Yeu
            else -> XepLoai.Kem
        }
    }
}