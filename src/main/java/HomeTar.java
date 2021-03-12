import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class HomeTar{

    public static void help() {
        System.out.println("This command works with files. " +
                "If you use the key \"-out\" it will combine other files into only one. " +
                "You can use the key \"-u\" to subtract all files from the one that was combined" +
                "Example -out: HomeTar a.txt b.txt -out output.txt" +
                "Example -u: HomeTar -u output.txt");
    }

    public static void main(String[] args) throws IOException {

        if (!args[0].toLowerCase().equals("hometar")) {
            System.out.println("Specify your command line");
            return;
        }

        if (args.length < 2) {
            System.out.println("Specify your command line");
            return;
        }

        if (args[1].toLowerCase().equals("help")) {
            help();
            return;
        }

        if (args[1].toLowerCase().equals("-u")) {
            if (args.length != 3) {
                System.out.println("Specify your command line");
                help();
                return;
            }
            uKey(args[2]);
        } else {
            if (args.length < 5) {
                System.out.println("Specify your command line");
                help();
                return;
            }

            int k = -1;
            List<String> inputName = new ArrayList<>();
            String outputName = null;
            for (int i = 1; i < args.length; i++) {
               if (args[i].equals("-out")) {
                   outputName = args[i+1];
                   k = i+1;
               }
               if (k<i) {
                   inputName.add(args[i]);
               }
            }
            if (outputName == null) {
                System.out.println("Specify your command line");
                help();
                return;
            }
            outKey(inputName, outputName);
        }
    }

    public static void uKey(String inputName) throws IOException {
        FileReader reader = new FileReader(inputName);
        char character;
        StringBuilder nameToOutput = new StringBuilder();
        List<String> outputFileName = new ArrayList<>();
        List<Integer> outputFileNumber = new ArrayList<>();
        int numberOfLines = 0;

        while ((character = (char) reader.read()) != (char) -1) {
            if (character == '\n') {
                if (numberOfLines == 1) break;
            }
            if (character != '\n') {
                nameToOutput.append(character);
                numberOfLines = 0;
            } else {
                numberOfLines++;
                int end = nameToOutput.indexOf("|");
                String name = nameToOutput.substring(0, end);
                int number = Integer.parseInt(nameToOutput.substring(end + 1));

                outputFileName.add(name);
                outputFileNumber.add(number);
                nameToOutput = new StringBuilder();
            }

        }
        if (outputFileName.size() == 0) {
            System.out.println("Specify your command line");
            help();
            return;
        }

        reader.read();
        int i = 0;
        int k = 0;

        File newFile = new File(outputFileName.get(i));
        FileWriter newFileWriter = new FileWriter(newFile);
        while ((character = (char) reader.read()) != (char)-1) {
            if (k == 0) {
                newFileWriter.close();
                newFile = new File(outputFileName.get(i));
                newFileWriter = new FileWriter(newFile);
                i++;
            }
            if (k < outputFileNumber.get(i - 1)) {
                newFileWriter.write(character);
                k++;
            } else {
                k = 0;
            }
        }
        reader.close();
        newFileWriter.close();
    }

    public static void outKey(List<String> listOfInput, String outputName) throws IOException {


        for (String value : listOfInput) {
            if (!Files.exists(Paths.get(value))) {
                System.out.println("\"" + value + "\"" + " file doesn't exist");
                return;
            }
        }

        StringBuilder writer = new StringBuilder();
        StringBuilder info = new StringBuilder();

        for (String s : listOfInput) {
            FileReader reader = new FileReader(s);
            int lengthOfText = 0;

            int character;
            while ((character = reader.read()) != -1) {
                lengthOfText++;
                writer.append((char) character);
            }

            reader.close();
            writer.append("\n");
            info.append(s).append("|").append(lengthOfText).append("\n");
        }

        FileWriter result = new FileWriter(outputName, false);
        writer.deleteCharAt(writer.length() - 1);
        result.write(info + "\n\n");
        result.write(writer.toString());
        result.close();
    }
}