package book;

public class book {
    private int id;
    private String title;
    private String author;
    private String press;
    private int num;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPress(){return press;}
    public void setPress(String press){this.press=press;}
    public int getNum(){return num;}
    public void setNum(int num){this.num=num;}
    public book(int id, String title, String author, String press,int num) {
        super();
        this.id = id;
        this.title = title;
        this.author = author;
        this.press = press;
        this.num = num;
    }
}
