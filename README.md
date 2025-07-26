# Student Management System

Hệ thống quản lý sinh viên được xây dựng bằng Spring Boot với MongoDB và Spring Security.

## Công nghệ sử dụng

- **Spring Boot 3.5.4**
- **Spring Security**
- **Spring Data MongoDB**
- **Lombok**
- **Java 8** <!-- Đã chuyển từ Java 24 sang Java 8 để tương thích tốt hơn -->

## Cấu trúc dự án

```
src/main/java/com/axyl/student_management/
├── config/           # Cấu hình
├── controller/       # Controllers
├── dto/             # Data Transfer Objects
├── entity/          # Entities
├── repository/      # Repositories
└── service/         # Services
```

## Cài đặt và chạy

### Yêu cầu hệ thống

- Java 8 trở lên (**bắt buộc phải cài đặt JDK, không chỉ JRE**)
- MongoDB
- Maven

### Cài đặt MongoDB

```bash
# Ubuntu/Debian
sudo apt-get install mongodb

# macOS
brew install mongodb-community

# Windows
# Tải từ https://www.mongodb.com/try/download/community
```

### Chạy ứng dụng

```bash
# Clone repository
git clone <repository-url>
cd student-management

# Chạy MongoDB
sudo systemctl start mongodb

# Build và chạy ứng dụng
./mvnw spring-boot:run
```

**Lưu ý:** Nếu gặp lỗi về compiler, hãy đảm bảo bạn đã cài đặt JDK (không chỉ JRE).

Ứng dụng sẽ chạy tại: http://localhost:8080

## API Endpoints

**Tất cả các endpoint hiện tại đều mở (không yêu cầu đăng nhập, không cần token, không cần Basic Auth).**

### Authentication

- `POST /api/v1/auth/login` - Đăng nhập
- `POST /api/v1/auth/register` - Đăng ký
- `POST /api/v1/auth/logout` - Đăng xuất
- `GET /api/v1/auth/profile?username={username}` - Lấy thông tin profile

### Students

- `GET /api/v1/students` - Lấy danh sách sinh viên
- `GET /api/v1/students/{id}` - Lấy sinh viên theo ID
- `GET /api/v1/students/student-id/{studentId}` - Lấy sinh viên theo Student ID
- `GET /api/v1/students/email/{email}` - Lấy sinh viên theo email
- `GET /api/v1/students/class/{classId}` - Lấy sinh viên theo lớp
- `GET /api/v1/students/status/{status}` - Lấy sinh viên theo trạng thái
- `GET /api/v1/students/search?keyword={keyword}` - Tìm kiếm sinh viên
- `POST /api/v1/students` - Tạo sinh viên mới
- `PUT /api/v1/students/{id}` - Cập nhật sinh viên
- `DELETE /api/v1/students/{id}` - Xóa sinh viên

### Classes

- `GET /api/v1/classes` - Lấy danh sách lớp
- `GET /api/v1/classes/{id}` - Lấy lớp theo ID
- `GET /api/v1/classes/name/{className}` - Lấy lớp theo tên
- `GET /api/v1/classes/teacher/{teacherId}` - Lấy lớp theo giáo viên
- `GET /api/v1/classes/status/{status}` - Lấy lớp theo trạng thái
- `GET /api/v1/classes/search?keyword={keyword}` - Tìm kiếm lớp
- `POST /api/v1/classes` - Tạo lớp mới
- `PUT /api/v1/classes/{id}` - Cập nhật lớp
- `DELETE /api/v1/classes/{id}` - Xóa lớp

### Users

- `GET /api/v1/users` - Lấy danh sách users
- `GET /api/v1/users/{id}` - Lấy user theo ID
- `GET /api/v1/users/username/{username}` - Lấy user theo username
- `GET /api/v1/users/email/{email}` - Lấy user theo email
- `POST /api/v1/users` - Tạo user mới
- `PUT /api/v1/users/{id}` - Cập nhật user
- `DELETE /api/v1/users/{id}` - Xóa user

### Teachers

- `GET /api/v1/teachers` - Lấy danh sách giáo viên
- `GET /api/v1/teachers/{id}` - Lấy giáo viên theo ID
- `GET /api/v1/teachers/teacher-id/{teacherId}` - Lấy giáo viên theo Teacher ID
- `GET /api/v1/teachers/email/{email}` - Lấy giáo viên theo email
- `GET /api/v1/teachers/department/{department}` - Lấy giáo viên theo khoa
- `GET /api/v1/teachers/status/{status}` - Lấy giáo viên theo trạng thái
- `GET /api/v1/teachers/search?keyword={keyword}` - Tìm kiếm giáo viên
- `POST /api/v1/teachers` - Tạo giáo viên mới
- `PUT /api/v1/teachers/{id}` - Cập nhật giáo viên
- `DELETE /api/v1/teachers/{id}` - Xóa giáo viên

### Subjects

- `GET /api/v1/subjects` - Lấy danh sách môn học
- `GET /api/v1/subjects/{id}` - Lấy môn học theo ID
- `GET /api/v1/subjects/name/{subjectName}` - Lấy môn học theo tên
- `GET /api/v1/subjects/department/{department}` - Lấy môn học theo khoa
- `GET /api/v1/subjects/search?keyword={keyword}` - Tìm kiếm môn học
- `POST /api/v1/subjects` - Tạo môn học mới
- `PUT /api/v1/subjects/{id}` - Cập nhật môn học
- `DELETE /api/v1/subjects/{id}` - Xóa môn học

### Enrollments

- `GET /api/v1/enrollments` - Lấy danh sách đăng ký
- `GET /api/v1/enrollments/{id}` - Lấy đăng ký theo ID
- `GET /api/v1/enrollments/student/{studentId}` - Lấy đăng ký theo sinh viên
- `GET /api/v1/enrollments/subject/{subjectId}` - Lấy đăng ký theo môn học
- `GET /api/v1/enrollments/teacher/{teacherId}` - Lấy đăng ký theo giáo viên
- `GET /api/v1/enrollments/semester/{semester}` - Lấy đăng ký theo học kỳ
- `POST /api/v1/enrollments` - Tạo đăng ký mới
- `PUT /api/v1/enrollments/{id}` - Cập nhật đăng ký
- `DELETE /api/v1/enrollments/{id}` - Xóa đăng ký

## Ví dụ sử dụng API

### Đăng ký user mới

```bash
curl -X POST http://localhost:8080/api/v1/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "password123",
    "email": "admin@example.com",
    "fullName": "Administrator",
    "role": "ADMIN"
  }'
```

### Đăng nhập

```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "password123"
  }'
```

### Tạo sinh viên mới

```bash
curl -X POST http://localhost:8080/api/v1/students \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Nguyễn Văn A",
    "email": "nguyenvana@example.com",
    "studentId": "SV001",
    "dateOfBirth": "2000-01-01",
    "gender": "MALE",
    "phoneNumber": "0123456789",
    "address": "Hà Nội",
    "classId": "class001",
    "status": "ACTIVE"
  }'
```

### Lấy danh sách sinh viên

```bash
curl -X GET http://localhost:8080/api/v1/students
```

### Tạo giáo viên mới

```bash
curl -X POST http://localhost:8080/api/v1/teachers \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Jane Smith",
    "email": "jane@example.com",
    "teacherId": "TCH001",
    "department": "Computer Science",
    "status": "ACTIVE"
  }'
```

### Tạo lớp mới

```bash
curl -X POST http://localhost:8080/api/v1/classes \
  -H "Content-Type: application/json" \
  -d '{
    "className": "CNTT2024A",
    "description": "Lớp Công nghệ thông tin 2024",
    "year": 2024,
    "major": "CNTT",
    "teacherId": "TCH001",
    "status": "ACTIVE"
  }'
```

### Tạo môn học mới

```bash
curl -X POST http://localhost:8080/api/v1/subjects \
  -H "Content-Type: application/json" \
  -d '{
    "subjectName": "Lập trình Java",
    "credits": 3,
    "department": "CNTT"
  }'
```

### Tạo đăng ký mới

```bash
curl -X POST http://localhost:8080/api/v1/enrollments \
  -H "Content-Type: application/json" \
  -d '{
    "studentId": "STU001",
    "subjectId": "SUB001",
    "teacherId": "TCH001",
    "semester": "2024A"
  }'
```

## Response Format

Tất cả API đều trả về response theo format:

```json
{
  "success": true,
  "message": "Success message",
  "data": {
    // Data object
  }
}
```

## Error Handling

Khi có lỗi, API sẽ trả về:

```json
{
  "success": false,
  "message": "Error message",
  "data": null
}
```

## Bảo mật

- Sử dụng BCrypt để mã hóa password
- CORS được cấu hình để cho phép cross-origin requests
- CSRF được disable cho API endpoints
- Tất cả endpoints hiện tại đều được permitAll() (có thể thay đổi sau)

## Phát triển thêm

Để phát triển thêm tính năng:

1. Tạo entity mới trong package `entity`
2. Tạo repository trong package `repository`
3. Tạo service trong package `service`
4. Tạo controller trong package `controller`
5. Thêm DTO nếu cần trong package `dto`

## Liên hệ

Nếu có vấn đề hoặc câu hỏi, vui lòng tạo issue trên repository.

## Changelog

### 2025-07-26

- Chuyển cấu hình Java từ 24 về 8 để tương thích tốt hơn với môi trường thực tế.
- Đảm bảo tất cả các entity, service, repository, controller đều đầy đủ CRUD và search cho Students, Teachers, Classes, Subjects, Enrollments.
- Đã kiểm thử thực tế tất cả endpoint (GET, POST, PUT, DELETE, SEARCH) đều hoạt động đúng.
- Đã mở toàn bộ endpoint (không cần đăng nhập, không cần token, không cần Basic Auth).
- Đã chuẩn hóa response format và error handling.
- Đã bổ sung ví dụ curl cho tất cả thực thể chính.
- Lưu ý: Cần cài đặt JDK (không chỉ JRE) để build/run.
