import java.io.File;

public class ShiftAndRename {

    private String filesLocation;
    private String setName;
    private int shiftBy;
    private File locatedFile;
    private File[] listOfFiles;

    public ShiftAndRename(String filesLocation, String setName, int shiftBy) {
        this.filesLocation = filesLocation;
        this.locatedFile = new File(this.filesLocation);
        this.setName = setName;
        this.shiftBy = shiftBy;
    }

    public void doShiftAndRename(){
        listOfFiles = this.locatedFile.listFiles();
        int shiftCounter = 0;

        //do shift
        while(shiftCounter < this.shiftBy) {
            if (this.shiftBy > listOfFiles.length) {
                System.out.println("Can't shift list is out of range. List length is: " + (listOfFiles.length-1));
                break;
            }else {
                for (int i = 0; i < listOfFiles.length-1; i++) {
                    File temp = listOfFiles[i];
                    listOfFiles[i] = listOfFiles[i + 1];
                    listOfFiles[i + 1] = temp;
                }
                shiftCounter += 1;
            }
        }

        //do rename
        int fileNumbering = 0;
        for(File aFile: listOfFiles){
            File fileToRename = new File(aFile.getPath());
            int extensionIndex = aFile.toString().lastIndexOf(".");
            String extension = aFile.toString().substring(extensionIndex);
            File fileNaming = new File(filesLocation + String.format(this.setName + "_%03d", fileNumbering)+extension);
            fileToRename.renameTo(fileNaming);
            fileNumbering++;
        }
        System.out.println("DONE");
    }
}

