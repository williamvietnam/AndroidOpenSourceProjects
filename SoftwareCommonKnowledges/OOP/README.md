### OOP - Lập trình hướng đối tượng

### Khái niệm về OOP

##### 1. Định nghĩa

- Lập trình hướng đối tượng là kỹ thuật lập trình cho phép lập trình viên tạo ra các đối tượng trong mã nguồn, các đối
  tượng này được trừu tượng hóa từ các đối tượng thực tế trong cuộc sống dựa trên các khái niệm về class và object
- Trong kỹ thuật này, bài toán được phân tích thành các đối tượng, mỗi đối tượng gồm các thuộc tính và phương thức
- Để giải quyết bài toán chúng ta cần đi xây dựng mối quan hệ giữa các đối tượng và cho từng đối tượng thực thi hành vi
  của chính nó.

##### 2. Đối tượng (Object) là gì?

- là một thực thể sống tham gia vào bài toán có đặc điểm và hành vi
- là một danh từ và không thể xác định được miền giá trị trong bài toán
- VD: ConNguoi, DiemSo,...

##### 3. Thuộc tính (attribute) là gì?

- là các thông tin, đặc điểm của một đối tượng
- là một danh từ có giá trị cụ thể và có miền giá trị đã được xác định trong bài toán
- VD: ConNguoi: chân, tay, miệng, tên...

##### 4. Phương thức (method) là gì?

- là hành vi của một đối tượng
- là một động từ mô tả hành động của đối tượng trong bài toán
- VD: ConNguoi: chạy, cầm, nói, NguyenBangGiang,...

### Các tính chất của OOP

- Tính trừu tượng hóa
- Tính kế thừa
- Tính đóng gói và che giấu dữ liệu
- Tính đa hình

##### 1. Tính trừu tượng hóa

- Trừu tượng hóa là việc loại bỏ đi hoặc ko chú ý đến một số khía cạnh, đặc tính của đối tượng mà **tập trung vào các
  đặc điểm cốt lõi của đối tượng đc đề cập đến trong bài toán** nhằm làm nổi bật vai trò của đối tượng và tránh dư thừa
  dữ liệu.
- VD:
    + Bài toán: Quản lý điểm Toán, Anh, Văn của một học sinh. Thông tin học sinh bao gồm: Các thông tin cơ bản, điểm
      toán, điểm văn, điểm anh. Người quản lý có thêm học sinh vào lớp để quản lý, in thông tin,...
    + Phân tích: Đối tượng gồm các thuộc tính: thông tin cơ bản, điểm toán, điểm văn, điểm anh. Với thông tin cơ bản
      trong trường hợp này ko rõ ràng, do đó cần áp dụng tính trừu tượng của OOP để lấy ra các đặc điểm cốt lõi đại diện
      cho thông tin cơ bản của đối tượng HocSinh (nếu ko áp dụng tính trừu tượng thì thông tin cb sẽ có rất nhiều thông
      tin dư thừa ko cần thiết cho bài toán như chiều cao, cân nặng, địa chỉ, sđt,...)

##### 2. Tính kế thừa

- Kế thừa là sự thừa hưởng lại các thuộc tính, phương thức từ một đối cha cho các đối tượng con mà ko cần phải khai báo
  các thuộc tính, phương thức đó.
- Ngoài ra, các đối tượng con sau khi kế thừa các thuộc tính, phương thức từ cha thì có thể mở rộng bổ sung thêm các
  thuộc tính, phương thức mới.
- Cơ sở hình thành MQH kế thừa: Nếu đối tượng A và đối tượng B có cùng bản chất, cùng chung 1 số đặc điểm và hành vi thì
  hình thành lên đối tượng C gồm các đặc điểm chung của 2 đối tượng A&B. Khi đó: đối tượng A & B sẽ kế thừa đối tượng C
- VD: HocSinh, NhanVien, GiaoVien đều có cùng đặc điểm: chân, tay, mắt và hành vi: chạy, cầm, nhìn. Lúc này hình thành
  lên đối tượng ConNguoi đều chứa tất cả những đặc điểm chung ấy nên ConNguoi trở thành đối tượng cha để các đối tượng
  con kế thừa.

##### 3. Tính đóng gói và che giấu dữ liệu


##### 4. Tính đa hình