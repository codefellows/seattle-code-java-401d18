/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import static java.lang.System.*;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }
    private static int myVar = 7;

    public static void main(String[] args) {
        out.println(new App().getGreeting());
      out.println(myVar);

        // Tell me who answered a question without any duplicates
      ArrayList<String> answeredQuestions = new ArrayList<>();
      answeredQuestions.add("Nicholas");
      answeredQuestions.add("Jennifer");
      answeredQuestions.add("Ryan");
      answeredQuestions.add("Rocio");
      answeredQuestions.add("Nicholas");

      HashSet<String> studentsSet = answeredWithoutDuplicates(answeredQuestions);
      out.println("Students who answered questions: " + studentsSet);
      out.println("ArrayList of students: "  + answeredQuestions);

      // Poll: What is your favorite color
      ArrayList<String> studentsFavColors = new ArrayList<>();
      studentsFavColors.add("green");
      studentsFavColors.add("green");
      studentsFavColors.add("red");
      studentsFavColors.add("green");
      studentsFavColors.add("blue");
      studentsFavColors.add("black");
      studentsFavColors.add("blue");

      out.println("Everyone's favorite colors: " + studentsFavColors);
      HashMap<String, Integer> colorMap = createStudentColorMap(studentsFavColors);
      out.println("Student Color Map: " + colorMap);

      // java.util.Date myDate = new java.util.Date();
    }

    public static HashSet<String> answeredWithoutDuplicates(ArrayList<String> students) {
      HashSet<String> studentsWithoutDupes = new HashSet<>();
      for(int i = 0; i < students.size(); i++) {
        studentsWithoutDupes.add(students.get(i));
      }

      return studentsWithoutDupes;
    }

    public static HashMap<String, Integer> createStudentColorMap(ArrayList<String> studentColors) {
      HashMap<String, Integer> studentColorMap = new HashMap<>();

      for(int i = 0; i < studentColors.size(); i++) {
        if(studentColorMap.get(studentColors.get(i)) == null) {
          studentColorMap.put(studentColors.get(i), 1); // green : 1
        } else {
          String key = studentColors.get(i); // green
          int newValue = studentColorMap.get(key) + 1; // 2
          studentColorMap.put(studentColors.get(i), newValue); // green : 2
        }
      }

      return studentColorMap;
    }
}
