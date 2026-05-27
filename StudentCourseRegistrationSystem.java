import java.sql.*;
import java.util.ArrayList;

class Student {

    String username;
    String password;

    Student(String username,
            String password) {

        this.username = username;
        this.password = password;
    }

    boolean login(String u,
                  String p) {

        return username.equals(u)
                && password.equals(p);
    }
}

class Course {

    int courseId;
    String courseName;
    int fee;

    Course(int courseId,
           String courseName,
           int fee) {

        this.courseId = courseId;
        this.courseName = courseName;
        this.fee = fee;
    }
}

class RegisteredStudent extends Student {

    ArrayList<Course> enrolledCourses =
            new ArrayList<>();

    RegisteredStudent(
            String username,
            String password) {

        super(username, password);
    }

    void addCourse(Course c) {

        enrolledCourses.add(c);
    }

    void dropCourse(Course c) {

        enrolledCourses.remove(c);
    }

    int calculateFee() {

        int total = 0;

        for(Course c :
                enrolledCourses) {

            total += c.fee;
        }

        return total;
    }
}

public class StudentCourseRegistrationSystem {

    public static void main(
            String[] args) {

        try {

            // Load JDBC Driver
            Class.forName(
            "com.mysql.cj.jdbc.Driver");

            // Database Connection
            Connection con =
            DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/studentdb?useSSL=false&serverTimezone=UTC",
            "root",
            "root");

            System.out.println(
            "Database Connected");

            // Student Login
            RegisteredStudent s =
            new RegisteredStudent(
            "admin","1234");

            if(s.login(
               "admin","1234")) {

                System.out.println(
                "Login Successful");
            }

            // Courses
            Course c1 =
            new Course(
            101,
            "Java Programming",
            5000);

            Course c2 =
            new Course(
            102,
            "Python Programming",
            5500);

            // Add Courses
            s.addCourse(c1);
            s.addCourse(c2);

            // Calculate Total Fee
            int total =
            s.calculateFee();

            System.out.println(
            "Total Fee = Rs."
            + total);

            // Insert Registration
            String query =
            "INSERT INTO registration(username, course_name) VALUES(?,?)";

            PreparedStatement ps =
            con.prepareStatement(query);

            ps.setString(
            1, s.username);

            ps.setString(
            2, c1.courseName);

            ps.executeUpdate();

            System.out.println(
            "Course Registered");

            con.close();

        } catch(Exception e) {

            System.out.println(e);
        }
    }
}