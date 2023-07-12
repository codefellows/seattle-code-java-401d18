package maps;

public class PackageModifierExample {
  public static void createAppClass() {
    App myApp = new App();
    myApp.getGreeting();
    // int temp = myApp.myVar; <-- doesn't work because myVar is private in App
  }
}
