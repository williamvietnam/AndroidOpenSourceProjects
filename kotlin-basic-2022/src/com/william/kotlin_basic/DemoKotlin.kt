package com.william.kotlin_basic

fun main(args: Array<String>) {
    initFunction()
}

fun initFunction() {
//    firstProgram()
//    initVariable()
//    demoArrayType()
//    demoChangeDataType()
//    demoWhenInKt(2)
//    demoReadCharFromKeyboard()
//    initStringOnMostLine()
//    demoWhenInKtVersion2(2)
    demoForInKtCloseRangeType(5)
}

/*
* First programming: HelloWorld
* */
fun firstProgram() {
    println("Hello Kotlin Basic")
}

/*
   *Khai báo biến: var nameVariable: DataType = value
   * Vd: var x: Int = 9
   * */
fun initVariable() {
    var x: Int = 8
    println(x)
}

/*
* demo khai báo chuỗi trên nhiều dòng:
* */
fun initStringOnMostLine() {
    var address: String = """
        56-TT4
        KĐT Thành phố Giao Lưu
        Cổ Nhuế 1
        Bắc Từ Liêm
        Hà Nội
                """
    println(address)
}

/*
  * Kiểu dữ liệu mảng:
  * var nameArray: DataTypeArray = XXXArrayOf(value1, value2, value3,...valueN)
  * XXX: là 8 dataType tương ứng: double, float, long, int, short, byte, boolean, char
  * */
fun demoArrayType() {
    var arrayX: IntArray = intArrayOf(3, 2, 1, 5)
    println(arrayX[1]) //giá trị của mảng ở vị trí số 1 tính từ vị trí số 0
    var arrayY: DoubleArray = doubleArrayOf(2.1, 3.4, 6.5)
    println(arrayY[2]) //giá trị của mảng ở vị trí số 2 tính từ vị trí số 0
    var arrayC: CharArray = charArrayOf('g', 'i', 'a', 'n', 'g')
    println(arrayC)
}

/*
* demo ép kiểu:
* */
fun demoChangeDataType() {
    var a: Double = 23.7
    var b: Int = a.toInt()
    println("aDouble = $a\nbInt = $b")
}

/*
* demo đọc dữ liệu từ bàn phím
* */
fun demoReadCharFromKeyboard() {
    print("\nPlease type input your name: ")
    var name: String? = readLine()
    println("My name: $name")

}

/*
* Demo when trong Kotlin:
* */
fun demoWhenInKt(value: Int?) {
    when (value) {
        0 -> println("Hello 0")
        1 -> println("Hello Giang")
        2 -> println("Hello 2")
        else -> println("Hello else") // tương tự case default trong java
    }
}

fun demoWhenInKtVersion2(age: Int) {
    when (age) {
        in 1..5 -> println("Tuoi thieu nhi")
        in 6..9 -> println("Tuoi nhi dong")
        in 10..15 -> println("Tuoi thieu nien")
        in 16..28 -> println("Tuoi thanh nien")
        else -> println("Tuoi trung nien va tuoi gia")
    }
}


/*
* Vòng lặp for kiểu Closed Range cho chúng ta duyệt trong đoạn [a;b]
* */
fun demoForInKtCloseRangeType(n: Int){
    println("demoForInKotlinCloseRange: ")
    for(i in 1..n){
      println(i)
    }
}

/*
*  Vòng lặp for kiểu half open range cho chúng ta duyệt trong nửa đoạn [a;b)
* */
fun demoForInKtHalfOpenRangeType(n: Int){
    println("demoForInKotlinHalfOpenRange: ")
    for(i in 1 until n  ){
        println(i)
    }
}

/*
* Vòng lặp for kiểu step <--> vònglặp for(i=0;i<n;i++)in java/c++
* */
fun demoForInKtStepType(n: Int, x: Int){
    println("demoForInKotlinStepType: ")
    for (i in 1..n step x){
        println(i)
    }
}

/*
* Vòng lặp for kiểu downTo <--> vòng lặp for(i=n;i>0;i--)in java/c++
* */
fun demoForInKtDownToType(n: Int){
    println("demoForInKtDownToType: ")
    for (i in n downTo 1){
        println(i)
    }
}


