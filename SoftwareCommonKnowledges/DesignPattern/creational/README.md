## 1. Factory Method Pattern là gì?

Factory Method is a creational design pattern that Define an interface for creating an object, but let subclasses decide
which class to instantiate.
Factory Method lets a class defer instantiation to subclasses.

Factory Method Design Pattern hay gọi ngắn gọn là Factory Pattern là một trong những Pattern thuộc nhóm Creational
Design Pattern.
Nhiệm vụ của Factory Pattern là quản lý và trả về các đối tượng theo yêu cầu, giúp cho việc khởi tạo đổi tượng một cách
linh hoạt hơn.

## 2. Cài đặt Factory Pattern như thế nào?

Một Factory Pattern bao gồm các thành phần cơ bản sau:

- **Super Class**: môt supper class trong Factory Pattern có thể là một interface, abstract class hay một class thông
  thường.
- **Sub Classes**: các sub class sẽ implement các phương thức của supper class theo nghiệp vụ riêng của nó.
- **Factory Class**: một class chịu tránh nhiệm khởi tạo các đối tượng sub class dựa theo tham số đầu vào.
  Lưu ý: lớp này là Singleton hoặc cung cấp một public static method cho việc truy xuất và khởi tạo đối tượng.
  Factory class sử dụng if-else hoặc switch-case để xác định class con đầu ra.

## 3. Sử dụng Factory Pattern khi nào?

Factory Pattern được sử dụng khi:

- Chúng ta có một super class với nhiều class con và dựa trên đầu vào, chúng ta cần trả về một class con.
  Mô hình này giúp chúng ta đưa trách nhiệm của việc khởi tạo một lớp từ phía người dùng (client) sang lớp Factory.
- Chúng ta không biết sau này sẽ cần đến những lớp con nào nữa.
  Khi cần mở rộng, hãy tạo ra sub class và implement thêm vào factory method cho việc khởi tạo sub class này.

## 4. Lợi ích của Factory Pattern là gì?

Lợi ích của Factory Pattern:

- Factory Pattern giúp giảm sự phụ thuộc giữa các module (loose coupling): cung cấp 1 hướng tiếp cận với Interface thay
  thì các implement.
  Giúp chuơng trình độc lập với những lớp cụ thể mà chúng ta cần tạo 1 đối tượng, code ở phía client không bị ảnh hưởng
  khi thay đổi logic ở factory hay sub class.
- Mở rộng code dễ dàng hơn: khi cần mở rộng, chỉ việc tạo ra sub class và implement thêm vào factory method.
- Khởi tạo các Objects mà che giấu đi xử lí logic của việc khởi tạo đấy.
  Người dùng không biết logic thực sực được khởi tạo bên dưới phương thức factory.
- Dễ dạng quản lý life cycle của các Object được tạo bởi Factory Pattern.
- Thống nhất về naming convention: giúp cho các developer có thể hiểu về cấu trúc source code.