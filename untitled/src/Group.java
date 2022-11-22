import java.util.*;

public class Group {
    List<Map.Entry<Student, Integer>> marks;
    List<Student> notAskedStudents;
    Student currentStudent;

    Group(List<Student> students) {
        this.notAskedStudents = students;
        currentStudent = null;
        marks = new java.util.ArrayList<>();
    }

    void getRandomStudent() {
        Random random = new Random();
        currentStudent = notAskedStudents.get(random.nextInt(notAskedStudents.size()));
        System.out.print("Selected student - ");
        currentStudent.Print();
    }

    boolean checkPresent() {
        System.out.println("Is he present? Y(yes)/N(no)");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String cmd = scanner.nextLine();
            if (Objects.equals(cmd, "Y")) {
                currentStudent.isAbsent = false;
                return true;
            } else if (Objects.equals(cmd, "N")) {
                currentStudent.isAbsent = true;
                return false;
            }
            System.out.println("Incorrect answer.\nIs he present? Y(yes)/N(no)");
        }
    }

    public void putMark() {
        System.out.printf("Student %s answered your questions.\n" +
                "Put him mark from 1 to 10: ", currentStudent);
        Scanner scanner = new Scanner(System.in);
        int mark;
        while (true) {
            if (scanner.hasNextInt()) {
                mark = scanner.nextInt();
                if (mark >= 1 && mark <= 10) {
                    marks.add(new AbstractMap.SimpleEntry<Student, Integer>(currentStudent, mark));
                    notAskedStudents.remove(currentStudent);
                    break;
                } else {
                    System.out.println("Incorrect number. Please enter number from 1 to 10.");
                }
            } else {
                scanner.next();
                System.out.println("Incorrect input type. Please enter number.");
            }
        }
    }

    public void putZero() {
        marks.add(new AbstractMap.SimpleEntry<Student, Integer>(currentStudent, 0));
        notAskedStudents.remove(currentStudent);
    }

    public void getStudentsWithGrades() {
        System.out.println("There is the list of students with grades:");
        for(int i = 0; i < marks.size(); i++){
            Map.Entry<Student, Integer> pair = marks.get(i);
            pair.getKey().Print(i, pair.getValue());
        }
    }

    public void getStudentsWithNoGrades() {
        System.out.println("There is the list of students with no grades:");
        for(int i = 0; i < notAskedStudents.size(); i++){
            notAskedStudents.get(i).Print(i);
        }
    }

    public void getListOfPresentedStudents() {
        System.out.println("There are all students, that should be presented:");
        for(int i = 0; i < notAskedStudents.size(); i++){
            notAskedStudents.get(i).Print(i);
        }
        for(int i = 0; i < marks.size(); i++){
            Map.Entry<Student, Integer> pair = marks.get(i);
            pair.getKey().Print(i + notAskedStudents.size(), pair.getValue());
        }
    }

    public void getListOfAbsentStudents() {
        System.out.println("There is the list of absent students:");
        for(int i = 0; i < marks.size(); i++){
            Map.Entry<Student, Integer> pair = marks.get(i);
            if(pair.getKey().isAbsent){
                pair.getKey().Print(i, pair.getValue());
            }
        }
    }
}

