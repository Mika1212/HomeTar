import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class HomeTar{

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
            System.out.println("This command works with files. " +
                    "If you use the key \"-out\" it will combine other files into only one. " +
                    "You can use the key \"-u\" to subtract all files from the one that was combined");
            return;
        }


        List<String> inputName = new ArrayList<>();
        String outputName = null;

        if (args[1].toLowerCase().equals("-u")) {
            inputName.add(args[2]);
            uKey(inputName.get(0));
        } else {
            for (int i = 2; i < args.length; i++) {
               if (args[i].equals("-out")) {
                   outputName = args[i+1];
                   break;
               }
               inputName.add(args[i]);
            }
            if (outputName == null) {
                System.out.println("Specify your command line");
                return;
            }
            outKey(inputName, outputName);
        }
    }

    public static void uKey(String inputName) throws IOException {
        FileReader reader = new FileReader(inputName);
        int character;
        StringBuilder nameToOutput = new StringBuilder();
        List<String> outputFileName = new ArrayList<>();
        List<Integer> outputFileNumber = new ArrayList<>();
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

    public static void outKey(List<String> listOfInput, String outputName) throws IOException {

        for (int i = 0; i < listOfInput.size() - 1; i++) {
            if (!new File(listOfInput.get(i)).exists()) {
                System.out.println("\"" + listOfInput.get(i) + "\"" + " file doesn't exist");
                return;
            }
        }

        FileWriter result = new FileWriter(outputName, false);
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

            writer.append("\n");
            info.append(s).append(" ").append(lengthOfText).append("\n");
        }

        writer.deleteCharAt(writer.length() - 1);
        result.write(info + "\n\n");
        result.write(String.valueOf(writer));
        result.flush();
    }
}