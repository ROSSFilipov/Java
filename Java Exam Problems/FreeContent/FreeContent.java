import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by user on 11/4/2015.
 */
abstract class Content{
    private String title;
    private String author;
    private BigInteger size;
    private String url;

    protected Content(String title, String author, BigInteger size, String url){
        this.setTitle(title);
        this.setAuthor(author);
        this.setSize(size);
        this.setUrl(url);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigInteger getSize() {
        return this.size;
    }

    public void setSize(BigInteger size) {
        this.size = size;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString(){
        return String.format("%s: %s; %s; %s; %s",
                this.getClass().getName(), this.getTitle(), this.getAuthor(), this.getSize(), this.getUrl());
    }
}

class Book extends Content{
    public Book(String title, String author, BigInteger size, String url){
        super(title, author, size, url);
    }
}

class Movie extends Content{
    public Movie(String title, String author, BigInteger size, String url){
        super(title, author, size, url);
    }
}
class Song extends Content{
    public Song(String title, String author, BigInteger size, String url){
        super(title, author, size, url);
    }
}

class Application extends Content{
    public Application(String title, String author, BigInteger size, String url){
        super(title, author, size, url);
    }
}

public class FreeContent {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Pattern inputPattern =
                Pattern.compile("(Add|Find|Update)(.+)");
        List<Content> allContents = new ArrayList<>();
        while (!scanner.hasNext("End")){
            String currentLine = scanner.nextLine();
            Matcher matcher = inputPattern.matcher(currentLine);
            if (matcher.find()){
                String currentCommand = matcher.group(1);
                switch (currentCommand){
                    case "Add": AddContent(allContents, matcher.group(2));
                        break;
                    case "Find": FindContent(allContents, matcher.group(2));
                        break;
                    case "Update": UpdateContent(allContents, matcher.group(2));
                        break;
                }
            }
        }
    }

    private static void UpdateContent(List<Content> allContents, String currentInfo) {
        Pattern updatePattern = Pattern.compile(":([^;]+);([^;]+)");
        Matcher matcher = updatePattern.matcher(currentInfo);
        if (matcher.find()){
            String oldURL = matcher.group(1).trim();
            String newURL = matcher.group(2).trim();
            int updatedItemsCounter = 0;
            for (int i = 0; i < allContents.size(); i++) {
                if (allContents.get(i).getUrl().equals(oldURL)){
                    allContents.get(i).setUrl(newURL);
                    updatedItemsCounter++;
                }
            }

            System.out.printf("%d items updated\n", updatedItemsCounter);
        }
    }

    private static void FindContent(List<Content> allContents, String currentInfo) {
        Pattern findPattern = Pattern.compile(":([^;]+);\\s*([0-9]+)");

        Matcher matcher = findPattern.matcher(currentInfo);
        if (matcher.find()){
        String currentTitle = matcher.group(1).trim();
        int count = Integer.parseInt(matcher.group(2));
            List<String> contentFound = allContents
                    .stream()
                    .filter(s -> s.getTitle().equals(currentTitle))
                    .map(s -> s.toString())
                    .limit(count)
                    .collect(Collectors.toList());

            if (contentFound.size() == 0){
                System.out.println("No items found");
            } else {
                Collections.sort(contentFound);
                contentFound.forEach(s -> System.out.println(s));
            }
        }
    }

    private static void AddContent(List<Content> allContents, String currentInfo) {
        Pattern addPattern =
                Pattern.compile("\\s*(book|movie|song|application)\\s*:([^;]+);([^;]+);\\s*([0-9]+)\\s*;([^;]+)");
        Matcher matcher = addPattern.matcher(currentInfo);
        if (matcher.find()) {
            String currentType = matcher.group(1);
            String currentTitle = matcher.group(2).trim();
            String currentAuthor = matcher.group(3).trim();
            String currentSize = matcher.group(4);
            String currentURL = matcher.group(5).trim();
            switch (currentType) {
                case "book":
                    allContents.add(new Book(currentTitle, currentAuthor, new BigInteger(currentSize), currentURL));
                    System.out.println("Book added");
                    break;
                case "movie":
                    allContents.add(new Movie(currentTitle, currentAuthor, new BigInteger(currentSize), currentURL));
                    System.out.println("Movie added");
                    break;
                case "song":
                    allContents.add(new Song(currentTitle, currentAuthor, new BigInteger(currentSize), currentURL));
                    System.out.println("Song added");
                    break;
                case "application":
                    allContents.add(new Application(currentTitle, currentAuthor, new BigInteger(currentSize), currentURL));
                    System.out.println("Application added");
                    break;
            }
        }
    }
}
