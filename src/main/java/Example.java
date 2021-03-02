import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Example {

    public static void main(String[] args) throws IOException {

        File newFile = new File("C://Users//User//IdeaProjects//HomeTar//test1.txt");

        try {
            boolean created = newFile.createNewFile();

            if (created)
                System.out.println("File has been created");
            else System.out.println("It is not created   |");

            if (newFile.exists()) System.out.println("File exists         |");
            else System.out.println("File isn't exist   |");

            if (newFile.canRead()) System.out.println("File can be read    |");
            else System.out.println("File can't be read |");

            if (newFile.canWrite()) System.out.println("File can be wrotten |");
            else System.out.println("It can't be wrotten |");

            System.out.println("---------------------");

            String forTest1 = "sample 1\n";
            String forTest2 = "sample 2";

            FileWriter writer = new FileWriter(newFile, false);
            FileReader reader = new FileReader(newFile);
            writer.write(forTest1);
            writer.write(forTest2);
            writer.flush();
            int c;
            while ((c=reader.read())!=-1) {
                System.out.print((char)c);
            }

        } catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
