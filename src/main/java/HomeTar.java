import java.io.*;
import java.util.ArrayList;

public class HomeTar{

    public static void uKey(String inputName) throws IOException {
        FileReader reader = new FileReader(inputName);
        int character;
        StringBuilder nameToOutput = new StringBuilder();
        ArrayList<String> outputFileName = new ArrayList<>();
        ArrayList<Integer> outputFileNumber = new ArrayList<>();
        int numberOfLines = 0;

        while ((character = reader.read()) != -1) {
            if ((char) character == '\n') {
                if (numberOfLines == 1) break;
            }
            if ((char) character != '\n') {
                nameToOutput.append((char) character);
                numberOfLines = 0;
            } else {
                numberOfLines++;
                int end = nameToOutput.indexOf(" ");
                String name = nameToOutput.substring(0, end);
                System.out.println(name);
                int number = Integer.parseInt(nameToOutput.substring(end + 1));

                outputFileName.add(name);
                outputFileNumber.add(number);
                nameToOutput = new StringBuilder();
            }

        }
        reader.read();
        int i = 0;
        int k = 0;
        File newFile = new File(outputFileName.get(i));
        FileWriter newFileWriter = new FileWriter(newFile);
        while ((character = reader.read()) != -1) {
            if (k == 0) {
                newFile = new File(outputFileName.get(i));
                newFileWriter = new FileWriter(newFile);
                i++;
            }
            if (k<outputFileNumber.get(i - 1)) {
                newFileWriter.write(character);
                k++;
            } else {
                newFileWriter.flush();
                k = 0;
            }
        }

        newFileWriter.flush();

    }

    public static void outKey(String[] args) throws IOException {

        ArrayList<String> inputName = new ArrayList<>();
        String outputName = null;


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


        if (outputName == null) {
            System.out.println("Output file wasn't specified");
            return;
        }
        System.out.println("----------------------\n");

        FileWriter result = new FileWriter(outputName, false);
        StringBuilder writer = new StringBuilder();
        StringBuilder info = new StringBuilder();

        for (String s : inputName) {
            FileReader reader = new FileReader(s);
            int lengthOfText = 0;

            int character;
            while ((character = reader.read()) != -1) {
                lengthOfText++;
                writer.append((char) character);
            }

            writer.append("\n");
            info.append(s).append(" ").append(lengthOfText).append("\n");
        }

        writer.deleteCharAt(writer.length() - 1);
        result.write(info + "\n\n");
        result.write(String.valueOf(writer));
        result.flush();

        int character;
        FileReader resultReader = new FileReader(outputName);
        while ((character = resultReader.read()) != -1) {
            System.out.print((char) character);
        }
    }



    public static void main(String[] args) throws IOException {

        if (!args[0].toLowerCase().equals("hometar")) {
            System.out.println("Specify your command line");
            return;
        }

        if (args[1].toLowerCase().equals("-u")) {
            uKey(args[2]);
        } else
           outKey(args);
    }

    public static boolean isEqual(File it, File other) throws IOException {
        FileReader itReader = new FileReader(it);
        FileReader otherReader = new FileReader(other);
        int itCharacter;
        int otherCharacter;

        while ((itCharacter = itReader.read()) != -1) {
            otherCharacter = otherReader.read();
            while (otherCharacter == 13) otherCharacter = otherReader.read();
            while (itCharacter == 13) itCharacter = itReader.read();
            if (itCharacter != otherCharacter) {
                return false;
            }
        }
        return true;
    }
}
