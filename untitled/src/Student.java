public class Student {
    String name;
    String surname;
    boolean isAbsent;

    Student(String name, String surname) {
        this.surname = surname;
        this.name = name;
        isAbsent = false;
    }

    public String toString() {
        return surname + " " + name;
    }

    void Print(int position, int mark) {
        System.out.printf("%d) %s %s - %d%n", position, surname, name, mark);
    }

    void Print(int position) {
        System.out.printf("%d) %s %s%n", position, surname, name);
    }

    void Print() {
        System.out.printf("%s %s%n", surname, name);
    }
}

