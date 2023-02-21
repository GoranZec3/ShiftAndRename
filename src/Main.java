public class Main {

    public static void main(String[] args) {

         String myLocation = "data\\";

         ShiftAndRename shiftAndRename = new ShiftAndRename(myLocation, "frame", 60);

         shiftAndRename.doShiftAndRename();
    }
}
