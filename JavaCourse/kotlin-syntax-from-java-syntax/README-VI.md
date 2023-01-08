<p align="center">
<img alt="FromJavaToKotlin" src="https://raw.githubusercontent.com/MindorksOpenSource/from-java-to-kotlin/master/assets/from_java_to_kotlin.png">
</p>

# Kiến thức về Kotlin dành cho các nhà phát triển Java

[comment]: <> ([![Mindorks]&#40;https://img.shields.io/badge/mindorks-opensource-blue.svg&#41;]&#40;https://mindorks.com/open-source-projects&#41;)

[comment]: <> ([![Mindorks Community]&#40;https://img.shields.io/badge/join-community-blue.svg&#41;]&#40;https://mindorks.com/join-community&#41;)

[comment]: <> ([![Mindorks Android Store]&#40;https://img.shields.io/badge/Mindorks%20Android%20Store-From%20Java%20To%20Kotlin-blue.svg?style=flat&#41;]&#40;https://mindorks.com/android/store&#41;)

> Ánh xạ từ Java sang Kotlin - Tài liệu tóm tắt nhanh dành cho các nhà phát triển tham khảo

[English](https://github.com/williamvietnam/android/blob/main/java-to-kotlin/README.md)

---

[comment]: <> ([Português]&#40;https://github.com/MindorksOpenSource/from-java-to-kotlin/blob/master/README-PT.md&#41;)

[comment]: <> (---)

[comment]: <> ([Español]&#40;https://github.com/MindorksOpenSource/from-java-to-kotlin/blob/master/README-ES.md&#41;)
---
## Hiển thị ra màn hình Console
> Java

```java
System.out.print("Genius Brains");
System.out.println("Genius Brains");
```

> Kotlin

```kotlin
print("Genius Brains")
println("Genius Brains")
```

---
## Hằng và Biến
> Java

```java
String name = "Genius Brains";
final String name = "Genius Brains";
```

> Kotlin

```kotlin
var name = "Genius Brains"
val name = "Genius Brains"
```

---
## Gán giá trị null
> Java

```java
String otherName;
otherName = null;
```

> Kotlin

```kotlin
var otherName : String?
otherName = null
```

---
## Xác minh nếu giá trị là null
> Java

```java
if (text != null) {
  int length = text.length();
}
```

> Kotlin

```kotlin
text?.let {
    val length = text.length
}
// or simply
val length = text?.length
```

---
## Xác minh nếu giá trị là NotNull (ko bị null) hoặc NotEmpty (không rỗng)
> Java
```java
String sampleString = "GeniusBrains";
if (!sampleString.isEmpty()) {
    myTextView.setText(sampleString);
}
if(sampleString!=null && !sampleString.isEmpty()){
    myTextView.setText(sampleString); 
}
```
> Kotlin

```kotlin
var sampleString ="GeniusBrains"
if(sampleString.isNotEmpty()){  //the feature of kotlin extension function
    myTextView.text=sampleString
}
if(!sampleString.isNullOrEmpty()){
   myTextView.text=sampleString 
}
```
---
## Nối các chuỗi
> Java

```java
String firstName = "William";
String lastName = "Nguyen";
String message = "My name is: " + firstName + " " + lastName;
```

> Kotlin

```kotlin
var firstName = "William"
var lastName = "Nguyen"
var message = "My name is: $firstName $lastName"
```

---
## Xuống dòng với chuỗi String
> Java

```java
String text = "First Line\n" +
              "Second Line\n" +
              "Third Line";
```

> Kotlin

```kotlin
val text = """
        |First Line
        |Second Line
        |Third Line
        """.trimMargin()
```

---

## Chuỗi con (Substring)
> Java

```java
String str = "Kotlin's knowledges for Java developers";
String substr = "";

//print java
substr = str.substring(23, 27);
System.out.println("substring = " + substr);

//print kotlin
substr = str.substring(0, 6);
System.out.println("substring = " + substr);
```

> Kotlin

```kotlin
var str = "Kotlin's knowledges for Java developers"
var substr = ""

//print java
substr = str.substring(23..27) //
println("substring $substr")

//print kotlin
substr = str.substring(0..6)
println("substring $substr")
```

---

## Ternary Operations
> Java

```java
String text = x > 5 ? "x > 5" : "x <= 5";

String message = null;
log(message != null ? message : "");
```

> Kotlin

```kotlin
val text = if (x > 5) "x > 5" else "x <= 5"

val message: String? = null
log(message ?: "")
```

---
## Các toán tử thao tác Bit
> Java

```java
final int andResult  = a & b;
final int orResult   = a | b;
final int xorResult  = a ^ b;
final int rightShift = a >> 2;
final int leftShift  = a << 2;
final int unsignedRightShift = a >>> 2;
```

> Kotlin

```kotlin
val andResult  = a and b
val orResult   = a or b
val xorResult  = a xor b
val rightShift = a shr 2
val leftShift  = a shl 2
val unsignedRightShift = a ushr 2
```

---
## Kiểm tra kiểu và ép kiểu
> Java

```java
if (object instanceof Car) {
	Car car = (Car) object;
}
```

> Kotlin

```kotlin
if (object is Car) {
var car = object as Car
}

// if object is null
var car = object as? Car // var car = object as Car?
```

---
## Kiểm tra kiểu và ép kiểu (implicit)
> Java

```java
if (object instanceof Car) {
   Car car = (Car) object;
}
```

> Kotlin

```kotlin
if (object is Car) {
   var car = object // smart casting
}

// if object is null
if (object is Car?) {
   var car = object // smart casting, car will be null
}
```

---
## Câu điều kiện nhiều điều kiện bên trong sử dụng if(...)
> Java

```java
if (score >= 0 && score <= 300) { }
```

> Kotlin

```kotlin
if (score in 0..300) { }
```

---
## Câu điều kiện nhiều điều kiện bên trong sử dụng sử dụng Switch...case...
> Java

```java
int score = // some score;
String grade;
switch (score) {
	case 10:
	case 9:
		grade = "Excellent";
		break;
	case 8:
	case 7:
	case 6:
		grade = "Good";
		break;
	case 5:
	case 4:
		grade = "OK";
		break;
	case 3:
	case 2:
	case 1:
		grade = "Fail";
		break;
	default:
	    grade = "Fail";				
}
```

> Kotlin

```kotlin
var score = // some score
var grade = when (score) {
	9, 10 -> "Excellent"
	in 6..8 -> "Good"
	4, 5 -> "OK"
	else -> "Fail"
}
```

---
## Vòng lặp For(){...}
> Java

```java
for (int i = 1; i <= 10 ; i++) { }

for (int i = 1; i < 10 ; i++) { }

for (int i = 10; i >= 0 ; i--) { }

for (int i = 1; i <= 10 ; i+=2) { }

for (int i = 10; i >= 0 ; i-=2) { }

for (String item : collection) { }

for (Map.Entry<String, String> entry: map.entrySet()) { }
```

> Kotlin

```kotlin
for (i in 1..10) { }

for (i in 1 until 10) { }

for (i in 10 downTo 0) { }

for (i in 1..10 step 2) { }

for (i in 10 downTo 0 step 2) { }

for (item in collection) { }

for ((key, value) in map) { }
```

---
## Collections: List, Map,...
> Java

```java
final List<Integer> listOfNumber = Arrays.asList(1, 2, 3, 4);

final Map<Integer, String> keyValue = new HashMap<Integer, String>();
map.put(1, "William");
map.put(2, "Giang");
map.put(3, "GeniusBrains");

// Java 9
final List<Integer> listOfNumber = List.of(1, 2, 3, 4);

final Map<Integer, String> keyValue = Map.of(1, "William",
                                             2, "Giang",
                                             3, "GeniusBrains");
```

> Kotlin

```kotlin
val listOfNumber = listOf(1, 2, 3, 4)
val keyValue = mapOf(1 to "William",
                     2 to "Giang",
                     3 to "GeniusBrains")
```

---
## Vòng lặp For(){...}
> Java

```java
// Java 7 and below
for (Car car : cars) {
  System.out.println(car.speed);
}

// Java 8+
cars.forEach(car -> System.out.println(car.speed));

// Java 7 and below
for (Car car : cars) {
  if (car.speed > 100) {
    System.out.println(car.speed);
  }
}

// Java 8+
cars.stream().filter(car -> car.speed > 100).forEach(car -> System.out.println(car.speed));
cars.parallelStream().filter(car -> car.speed > 100).forEach(car -> System.out.println(car.speed));
```

> Kotlin

```kotlin
cars.forEach {
    println(it.speed)
}

cars.filter { it.speed > 100 }
      .forEach { println(it.speed)}

// kotlin 1.1+
cars.stream().filter { it.speed > 100 }.forEach { println(it.speed)}
cars.parallelStream().filter { it.speed > 100 }.forEach { println(it.speed)}
```

---
## Tách mảng
> java

```java
String[] splits = "param=car".split("=");
String param = splits[0];
String value = splits[1];
```


> kotlin

```kotlin
val (param, value) = "param=car".split("=")
```

---
## Định nghĩa phương thức (Khai báo hàm)
> Java

```java
void doSomething() {
   // logic here
}
```

> Kotlin

```kotlin
fun doSomething() {
   // logic here
}
```

### Gán giá trị mặc định cho các tham số phương thức
> Java

```java
double calculateCost(int quantity, double pricePerItem) {
    return pricePerItem * quantity;
}

double calculateCost(int quantity) {
    // default price is 20.5
    return 20.5 * quantity;
}
```

> Kotlin

```kotlin
fun calculateCost(quantity: Int, pricePerItem: Double = 20.5) = quantity * pricePerItem

calculateCost(10, 25.0) // 250
calculateCost(10) // 205

```

---
## Variable number of arguments
> Java

```java
void doSomething(int... numbers) {
   // logic here
}
```

> Kotlin

```kotlin
fun doSomething(vararg numbers: Int) {
   // logic here
}
```

---
## Định nghĩa phương thức có kiểu trả về
> Java

```java
int getScore() {
   // logic here
   return score;
}
```

> Kotlin

```kotlin
fun getScore(): Int {
   // logic here
   return score
}

// as a single-expression function

fun getScore(): Int = score

// even simpler (type will be determined automatically)

fun getScore() = score // return-type is Int
```

---
## Returning result of an operation
> Java

```java
int getScore(int value) {
    // logic here
    return 2 * value;
}
```

> Kotlin

```kotlin
fun getScore(value: Int): Int {
   // logic here
   return 2 * value
}

// as a single-expression function
fun getScore(value: Int): Int = 2 * value

// even simpler (type will be determined automatically)

fun getScore(value: Int) = 2 * value // return-type is int
```

---
## Phương thức khởi tạo của Class
> Java

```java
public class Utils {

    private Utils() {
      // This utility class is not publicly instantiable
    }

    public static int getScore(int value) {
        return 2 * value;
    }

}
```

> Kotlin

```kotlin
class Utils private constructor() {

    companion object {

        fun getScore(value: Int): Int {
            return 2 * value
        }

    }
}

// another way

object Utils {

    fun getScore(value: Int): Int {
        return 2 * value
    }

}
```

---
## Getters and Setters
> Java

```java
public class Developer {

    private String name;
    private int age;

    public Developer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Developer developer = (Developer) o;

        if (age != developer.age) return false;
        return name != null ? name.equals(developer.name) : developer.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

> Kotlin

```kotlin
data class Developer(var name: String, var age: Int)

```

---
## Bản sao và sao chép (Cloning or copying )
> Java

```java
public class Developer implements Cloneable {

    private String name;
    private int age;

    public Developer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (Developer)super.clone();
    }
}

// cloning or copying
Developer dev = new Developer("Mindorks", 30);
try {
    Developer dev2 = (Developer) dev.clone();
} catch (CloneNotSupportedException e) {
    // handle exception
}

```

> Kotlin

```kotlin
data class Developer(var name: String, var age: Int)

// cloning or copying
val dev = Developer("Mindorks", 30)
val dev2 = dev.copy()
// in case you only want to copy selected properties
val dev2 = dev.copy(age = 25)

```

---
## Class methods
> Java

```java
public class Utils {

    private Utils() {
      // This utility class is not publicly instantiable
    }

    public static int triple(int value) {
        return 3 * value;
    }

}

int result = Utils.triple(3);

```

---
## Generics
> Java

```java

// Example #1
interface SomeInterface<T> {
    void doSomething(T data);
}

class SomeClass implements SomeInterface<String> {
    @Override
    public void doSomething(String data) {
        // some logic
    }
}

// Example #2
interface SomeInterface<T extends Collection<?>> {
    void doSomething(T data);
}

class SomeClass implements SomeInterface<List<String>> {

    @Override
    public void doSomething(List<String> data) {
        // some logic
    }
}

```

```kotlin
interface SomeInterface<T> {
    fun doSomething(data: T)
}

class SomeClass: SomeInterface<String> {
    override fun doSomething(data: String) {
        // some logic
    }
}

interface SomeInterface<T: Collection<*>> {
    fun doSomething(data: T)
}

class SomeClass: SomeInterface<List<String>> {
    override fun doSomething(data: List<String>) {
        // some logic
    }
}
```


> Kotlin

```kotlin
fun Int.triple(): Int {
  return this * 3
}

var result = 3.triple()
```

---
## Defining uninitialized objects
> Java

```java
Person person;
```

> Kotlin

```kotlin
internal lateinit var person: Person
```
---
## enum
> Java

```java
public enum Direction {
        NORTH(1),
        SOUTH(2),
        WEST(3),
        EAST(4);

        int direction;

        Direction(int direction) {
            this.direction = direction;
        }

        public int getDirection() {
            return direction;
        }
    }
```
> Kotlin

```kotlin
enum class Direction(val direction: Int) {
    NORTH(1),
    SOUTH(2),
    WEST(3),
    EAST(4);
}
```
---


## Sorting List
> Java

```java
List<Profile> profiles = loadProfiles(context);
Collections.sort(profiles, new Comparator<Profile>() {
    @Override
    public int compare(Profile profile1, Profile profile2) {
        if (profile1.getAge() > profile2.getAge()) return 1;
        if (profile1.getAge() < profile2.getAge()) return -1;
        return 0;
    }
});

```

> Kotlin

```kotlin
val profile = loadProfiles(context)
profile.sortedWith(Comparator({ profile1, profile2 ->
    if (profile1.age > profile2.age) return@Comparator 1
    if (profile1.age < profile2.age) return@Comparator -1
    return@Comparator 0
}))
```
---

## Anonymous Class
> Java

```java
 AsyncTask<Void, Void, Profile> task = new AsyncTask<Void, Void, Profile>() {
    @Override
    protected Profile doInBackground(Void... voids) {
        // fetch profile from API or DB
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // do something
    }
};

```

> Kotlin

```kotlin
val task = object : AsyncTask<Void, Void, Profile>() {
    override fun doInBackground(vararg voids: Void): Profile? {
        // fetch profile from API or DB
        return null
    }

    override fun onPreExecute() {
        super.onPreExecute()
        // do something
    }
}
```
---
## Initialization block
> Java

```java
public class User {
    {  //Initialization block
        System.out.println("Init block");
    }
}

```

> Kotlin

```kotlin
   class User {
        init { // Initialization block
            println("Init block")
        }
    }
```

---

### Mọi thứ về Kotlin chúng ta nên biết (Tài liệu tiếng anh)
* [What is the equivalent of Java static methods in Kotlin?](https://blog.mindorks.com/what-is-the-equivalent-of-java-static-methods-in-kotlin)
* [What is the difference between "const" and "val"?](https://blog.mindorks.com/what-is-the-difference-between-const-and-val)
* [Learn Kotlin - lateinit vs lazy](https://blog.mindorks.com/learn-kotlin-lateinit-vs-lazy)
* [Learn Kotlin - apply vs with](https://blog.mindorks.com/learn-kotlin-apply-vs-with)
* [Learn Kotlin - Data Class](https://blog.mindorks.com/learn-kotlin-data-class)
* [Learn Kotlin - Destructuring Declarations](https://blog.mindorks.com/learn-kotlin-destructuring-declarations)
* [Learn Kotlin - Extension Functions](https://blog.mindorks.com/learn-kotlin-extension-functions)
* [Learn Kotlin - Sealed Classes](https://blog.mindorks.com/learn-kotlin-sealed-classes)
* [Understanding Higher-Order Functions and Lambdas in Kotlin](https://blog.mindorks.com/understanding-higher-order-functions-and-lambdas-in-kotlin)
* [Understanding inline, noinline, and crossinline in Kotlin](https://blog.mindorks.com/understanding-inline-noinline-and-crossinline-in-kotlin)
* [Mastering Kotlin Coroutines In Android - Step By Step Guide](https://blog.mindorks.com/mastering-kotlin-coroutines-in-android-step-by-step-guide)
* [Using Scoped Functions in Kotlin - let, run, with, also, apply](https://blog.mindorks.com/using-scoped-functions-in-kotlin-let-run-with-also-apply)
* [What are Reified Types in Kotlin?](https://blog.mindorks.com/what-are-reified-types-in-kotlin)

### Nếu thấy dự án này hữu ích :heart:
*  Hãy cho tôi một sao :v:

[Xem tất cả các dự án mã nguồn mở của GeniusBrains tại đây](https://github.com/williamvietnam)

### Nếu bạn muốn đóng góp vào dự án này
Hãy tạo Pull Request
