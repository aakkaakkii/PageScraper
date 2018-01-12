import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

public class PageScraper {
    public static void main(String args[]) throws IOException {

        MyScraper myS=new MyScraper("https://www.google.com");
        PrintWriter file = new PrintWriter("index.txt");
        HashSet<String> linksSet=myS.getlinksSet();

        myS.createSet("a", "href");
        myS.createSet("img", "src");

        for(String  link:linksSet){
            file.println(link);
        }
        file.close();

    }
}
