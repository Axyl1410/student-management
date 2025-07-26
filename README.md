# Student Management System

Hệ thống quản lý sinh viên được xây dựng bằng Spring Boot với MongoDB và Spring Security.

## Công nghệ sử dụng

- **Spring Boot 3.5.4**
- **Spring Security**
- **Spring Data MongoDB**
- **Lombok**
- **Java 24**

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
- Java 24
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

Ứng dụng sẽ chạy tại: http://localhost:8080

## API Endpoints

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