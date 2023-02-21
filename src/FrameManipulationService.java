import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FrameManipulationService {

    private final Path inputLocation;
    private final Path outputLocation;
    private final String baseName;
    private final int step;

    public FrameManipulationService(Path inputLocation, Path outputLocation, String baseName, int step) {
        this.inputLocation = inputLocation;
        this.outputLocation = outputLocation;
        this.baseName = baseName;
        this.step = step;
    }

    public void changeThumbnail() {
        File[] listOfFiles = this.inputLocation.toFile().listFiles();

        if (listOfFiles == null || this.step > listOfFiles.length) {
            System.out.println("Can't shift list is out of range. List length is: " + (listOfFiles.length - 1));
            return;
        }

        List<File> firstGroup = List.of(listOfFiles).subList(0, this.step);
        List<File> secondGroup = List.of(listOfFiles).subList(this.step, listOfFiles.length);
        List<File> newOrderedList = new ArrayList<>(secondGroup);
        newOrderedList.addAll(firstGroup);

        //do rename
        int index = 0;
        for (File frame : newOrderedList) {
            String frameName = generateFrameName(frame, index);
            File newFilePath = outputLocation.resolve(frameName).toFile();
            File originalFile = new File(frame.getPath());
            boolean renameSuccess = originalFile.renameTo(newFilePath);
            if (!renameSuccess) {
                System.out.println("Renaming failure!");
                return;
            }
            index++;
        }
        System.out.println("DONE");
    }

    private String generateFrameName(File frame, int index) {
        int extensionIndex = frame.toString().lastIndexOf(".");
        String extension = frame.toString().substring(extensionIndex);
        return String.format(this.baseName + "_%03d", index) + extension;
    }
}

