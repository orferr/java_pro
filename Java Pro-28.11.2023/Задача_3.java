import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

enum ClassType { BA, FE, QA }

class Student {
    String name;
    double rate;
    boolean finished;
    ClassType type;

    public Student(String name, double rate, boolean finished, ClassType type) {
        this.name = name;
        this.rate = rate;
        this.finished = finished;
        this.type = type;
    }
}

class FrontEndStudent extends Student {
    public FrontEndStudent(String name, double rate, boolean finished) {
        super(name, rate, finished, ClassType.FE);
    }
}

class BackEndStudent extends Student {
    public BackEndStudent(String name, double rate, boolean finished) {
        super(name, rate, finished, ClassType.BA);
    }
}

class QAStudent extends Student {
    public QAStudent(String name, double rate, boolean finished) {
        super(name, rate, finished, ClassType.QA);
    }
}

public class Main {
    public static void main(String[] args) {
        List<FrontEndStudent> frontEndStudents = createStudentsList(ClassType.FE, 5);
        List<BackEndStudent> backEndStudents = createStudentsList(ClassType.BA, 5);
        List<QAStudent> qaStudents = createStudentsList(ClassType.QA, 5);

        List<List<Student>> allStudents = Arrays.asList(frontEndStudents, backEndStudents, qaStudents);

        
        List<ProjectStudent> projectStudents = allStudents.stream()
                .flatMap(List::stream)
                .filter(student -> student.rate > 4.5 && student.finished)
                .peek(student -> {
                    if (student instanceof FrontEndStudent) {
                        student.type = ClassType.FE;
                    } else if (student instanceof BackEndStudent) {
                        student.type = ClassType.BA;
                    } else if (student instanceof QAStudent) {
                        student.type = ClassType.QA;
                    }
                })
                .map(student -> new ProjectStudent(student.name, student.rate, student.type))
                .collect(Collectors.toList());

      
        projectStudents.forEach(student ->
                System.out.println("Name: " + student.name + ", Rate: " + student.rate + ", Type: " + student.type));
    }

    private static <T extends Student> List<T> createStudentsList(ClassType type, int count) {
        List<T> students = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            students.add((T) new Student("Student" + i, i * 1.5, i % 2 == 0, type));
        }
        return students;
    }
}

class ProjectStudent {
    String name;
    double rate;
    ClassType type;

    public ProjectStudent(String name, double rate, ClassType type) {
        this.name = name;
        this.rate = rate;
        this.type = type;
    }
}
