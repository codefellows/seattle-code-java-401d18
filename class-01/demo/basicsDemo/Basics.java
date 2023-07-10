import java.util.Random;

// file name and class name must match!!
public class Basics {
  public static void main(String[] args){
    System.out.println("Hello world!");

    int int1 = 5;
    long long1 = 100;
    byte byte1 = (byte)0xFF;
    short short1 = (short)0xFFFF;

    float float1 = 0.1f;
    float float2 = 0.2f;
    float float3 = float1 + float2;
    boolean booleanFloat = (float3 == 0.3f);  // true
    System.out.println("booleanFloat: " + booleanFloat);
    System.out.println("float3: " + float3);

    double double1 = 0.1;
    double double2 = 0.2;
    double double3 = double1 + double2;
    boolean booleanDouble = (double3 == 0.3); // false!
    System.out.println("booleanDouble: " + booleanDouble);
    System.out.println("double3: " + double3);

    char char1 = 'a';
    char char2 = 'b';
    System.out.println("char1: " + char1);
    System.out.println(char1 + char2); // 195

    int maxInt = Integer.MAX_VALUE;
    System.out.println("Maximum integer is: " + maxInt);
    int minInt = Integer.MIN_VALUE;
    System.out.println("Mininum integer is: " + minInt);

    // maxInt += 1; // maxInt = maxInt + 1;
    // System.out.println(maxInt);

    Random rand = new Random();

    Double DoubleRandom1 = rand.nextDouble();
    double doubleRandom2 = rand.nextDouble();
    System.out.println("DoubleRandom1: " + DoubleRandom1 + " doubleRandom2: " + doubleRandom2);

    Integer IntegerRandom2 = rand.nextInt(100);
    int intRandom1 = rand.nextInt();
    System.out.println("intRandom1: " + intRandom1 + " IntegerRandomRandom2: " + IntegerRandom2);

    int int2 = 20;
    String numberString1 = "30";
    System.out.println("int2 + numberString1: " + (int2 + numberString1)); // 2030
    System.out.println("int2 + Integer.valueOf(numberString1): " + (int2 + Integer.valueOf(numberString1))); // 50

    String hello = "hello";
    String hel = "hel";
    String lo = "lo";

    System.out.println("\"hello\" == \"hel\" + \"lo\": " + ("hello" == ("hel" + "lo")));
    System.out.println("\"hello\" == hel + lo: " + ("hello" == (hel + lo))); //false
    System.out.println("hello == \"hel\" + \"lo\": " + (hello == ("hel" + "lo")));
    System.out.println("hello == hel + lo: " + (hello == (hel + lo))); //false
    System.out.println("\"hello\".equals(\"hel\" + \"lo\"): " + ("hello".equals("hel" + "lo")));
    System.out.println("\"hello\".equals(hel + lo): " + ("hello".equals(hel + lo)));
    System.out.println("hello.equals(\"hel\" + \"lo\"): " + (hello.equals("hel" + "lo")));
    System.out.println("hello.equals(hel + lo): " + (hello.equals(hel + lo)));

    int foo = 17;
    int bar = 12;
    if(foo == bar) {
      System.out.println("They're equal");
    } else {
      System.out.println("They're not equal");
    }

    int month = 2;
    String monthString;
    switch (month) {
      case 1: monthString = "January";
        break;
      case 2: monthString = "February";
        break;
      case 3: monthString = "March";
        break;
      case 4: monthString = "April";
        break;
      default: monthString = "I don't know";
    }

    System.out.println(monthString);

    boolean myBool = true;
    int myInt = 3;
    int iterator = 0;
    while(myBool) {
      System.out.println("in the loop!");
      if(iterator == myInt) {
        break;
      }
      iterator++;
    }

    int[] intArray = {1, 5, 18};
    for(int i = 0; i < intArray.length; i++) {
      System.out.println("intArray[" +i+"]: " + intArray[i]);
    }

    int[] arrFilledWithForLoop = new int[5];
    for(int i = 0; i < arrFilledWithForLoop.length; i++) {
      arrFilledWithForLoop[i] = (i+1) * 5;
      System.out.println("arrFilledWithForLoop["+i+"]: " + arrFilledWithForLoop[i]);
    }


    for(int num : arrFilledWithForLoop) {
      System.out.println(num);
    }
  }
}
