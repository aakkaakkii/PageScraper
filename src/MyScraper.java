import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class MyScraper {
    private HashSet<String> linksSet=new HashSet<>();
    private Document document;
    private String webSite;

    MyScraper(String webSite){
        this.webSite=webSite;
        try {
            document = Jsoup.connect(webSite).get();
        }catch (IOException ex){}
    }

    void createSet(String tag, String attribute){
        Elements elements= document.select(tag);

        for(Element element: elements){
            String elem=element.attr(attribute);
            if((elem.length()>4)
                    &&(elem.substring(0,4).equals("http")))
                linksSet.add(elem);
            else
                linksSet.add(webSite+elem);
        }
    }

    HashSet<String> getlinksSet(){
        return linksSet;
    }
}
