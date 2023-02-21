import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {

        FrameManipulationService frameManipulationService = new FrameManipulationService(
                Path.of("data"),
                Path.of("output"),
                "frame",
                174);

        frameManipulationService.changeThumbnail();

    }
}
