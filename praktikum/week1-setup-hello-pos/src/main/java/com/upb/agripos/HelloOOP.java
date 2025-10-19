// Hello.OOP
class Student {
    String nim;
    String name;
    Student(String nim, String name) {
        this.nim = nim;
        this.name = name;
    }

    void introduce() {
        System.out.println("Hello world, I am " + name + " - " + nim);
    }
}

public class HelloOOP {
    public static void main(String[] args) {
        Student s = new Student("240202897", "Fakhri Fahmi R");
        s.introduce();
    }
}