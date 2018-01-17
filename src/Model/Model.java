package Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class Model {
    private LinksDAO linksDAO;
    private MyScraper myScraper;

    public Model(){
        linksDAO=LinksDAO.createLinksDAO();
    }

    public void addInDB(String url) {
        myScraper = new MyScraper(url);

        HashSet<String> linksSet = myScraper.getlinksSet();
        myScraper.createSet("a", "href");
        myScraper.createSet("img", "src");

        for (String link : linksSet) {
            linksDAO.add(url, link);

        }
    }

    public void addInFile(String url, String path){
        try {
            PrintWriter file = new PrintWriter(path+"/index.txt");
            myScraper = new MyScraper(url);
            HashSet<String> linksSet = myScraper.getlinksSet();

            myScraper.createSet("a", "href");
            myScraper.createSet("img", "src");

            for(String  link:linksSet){
                file.println(link);
            }
            file.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        System.out.println("add in file: "+url+" "+path);
    }

    public void search(String url, String path){
        try {
            ArrayList<String> arrayList=linksDAO.get(url);
            PrintWriter file = new PrintWriter(path+"/index.txt");

            for (String str:arrayList){
                file.println(str);
            }
            file.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void delete(String url){
        linksDAO.delete(url);
    }

}
