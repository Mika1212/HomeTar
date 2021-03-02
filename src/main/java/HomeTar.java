import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HomeTar {

    public static void main(String[] args) throws IOException {
        if (!args[0].toLowerCase().equals("hometar")) {
            System.out.println("Specify your command line");
            return;
        }

        ArrayList<String> inputName = new ArrayList<>();
        String outputName = null;

        if (args[1].toLowerCase().equals("-u")) {

            //

        } else {
            for (int i = 1; i < args.length - 1; i++) {

                if (args[i].toLowerCase().equals("-out")) {
                    outputName = args[i + 1];
                    File newFile = new File(outputName);
                    boolean created = newFile.createNewFile();
                    if (created) System.out.println("Output created");
                    else System.out.println("Output doesn't created");
                    break;
                }

                if (!new File(args[i]).exists()) {
                    System.out.println("\"" + args[i] + "\"" + " file doesn't exist");
                    return;
                }

                inputName.add(args[i]);
            }
        }

        if (outputName == null) {
            System.out.println("Output file wasn't specified");
            return;
        }
        System.out.println("----------------------\n");

        FileWriter result = new FileWriter(outputName, false);
        StringBuilder writer = new StringBuilder();

        for (String s : inputName) {
            FileReader reader = new FileReader(s);
            writer.append(s).append(" {\n");

            int character;
            while ((character = reader.read()) != -1) {
                writer.append((char) character);
            }

            writer.append("\n}\n");
        }
        writer.deleteCharAt(writer.length() - 1);

        result.write(String.valueOf(writer));
        result.flush();

        int character;
        FileReader resultReader = new FileReader(outputName);
        while ((character = resultReader.read()) != -1) {
            System.out.print((char) character);
        }
    }
}
