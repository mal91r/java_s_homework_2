import java.util.*;

public class Main {
    public static List<Student> generation() {
        String[] surnames = {
                "Malanin",
                "Balabanov",
                "Demkov",
                "Shatravka",
                "Mironov",
                "Tikhonov",
                "Gogotova",
                "Karpov",
                "Ganina",
                "Karamov",
                "Stepanov",
                "Parakhin"
        };
        String[] names = {
                "Artem",
                "Max",
                "Mike",
                "Daniel",
                "Pavel",
                "Tim",
                "Lera",
                "Mike",
                "Ksenia",
                "Stepan",
                "Alexey",
                "Nikolay"
        };
        System.out.print("Enter count of students in class(1-20): ");
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                count = scanner.nextInt();
                if (count >= 1 && count <= 20) {
                    System.out.printf("There are %d students in your group:\n\n", count);
                    break;
                } else {
                    System.out.println("Incorrect number\nEnter count of students in class(1-20): ");
                }
            } else {
                System.out.print("Incorrect input\nEnter count of students in class(1-20): ");
                scanner.next();
            }
        }
        Random random = new Random();
        List<Student> students = new ArrayList<Student>();
        int number;
        String selectedName;
        String selectedSurname;
        Student student;
        for (int i = 0; i < count; i++) {
            number = random.nextInt(12);
            selectedSurname = surnames[number];
            number = random.nextInt(12);
            selectedName = names[number];
            student = new Student(selectedSurname, selectedName);
            students.add(student);
            student.Print(i+1);
        }
        return students;
    }

    public static void lesson(Group group){
        String rules = """
                Commands:
                /r - ask random student if he is not absent
                /sg - list of students with grades
                /sng - list of student with no grades
                /sp - list of presented students
                /snp - list of absent students
                /e - exit
                /h - help
                """;
        System.out.println(rules);
        Scanner scanner = new Scanner(System.in);
        String cmd;
        while(true){
            cmd = scanner.nextLine();
            switch (cmd){
                case "/r":
                    group.getRandomStudent();
                    if(group.checkPresent()){
                        group.putMark();
                    } else {
                        group.putZero();
                        System.out.println("This student is absent! His mark is 0.");
                    }
                    break;
                case "/sg":
                    group.getStudentsWithGrades();
                    break;
                case "/sng":
                    group.getStudentsWithNoGrades();
                    break;
                case "/sp":
                    group.getListOfPresentedStudents();
                    break;
                case "/snp":
                    group.getListOfAbsentStudents();
                    break;
                case "/e":
                    return;
                case "/h":
                    System.out.println(rules);
                    break;
                default:
                    System.out.print("Incorrect command! ");
                    break;
            }
            System.out.println("\nNow you can enter another command.");
        }

    }

    public static void main(String[] args) {
        System.out.println("""
                Welcome to school
                Commands:
                /s - start
                /e - exit
                """);
        String cmd;
        Scanner scanner = new Scanner(System.in);
        Group group;
        while (true) {
            cmd = scanner.nextLine();
            if (Objects.equals(cmd, "/s")) {
                List<Student> students = generation();
                group = new Group(students);
                System.out.println("\nGroup generated!");
                break;
            } else if (Objects.equals(cmd, "/e")) {
                System.out.println("Bye, bye!");
                return;
            } else {
                System.out.println("Incorrect command!");
            }
        }
        lesson(group);
    }
}