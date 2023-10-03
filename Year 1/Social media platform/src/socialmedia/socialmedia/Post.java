package socialmedia;

public class Post {
    private int pid;
    private String message;
    private Account author;
 
    public Post (String message, Account author){
        this.pid = calcPID();
        this.message = message; 
        this.author = author;
    }

    private int calcPID(){
        //return SocialMedia.getPosts().size() + 1
        return 0; 
    } 
    public Account getAuthor() {
        return author;
    }
    public String getMessage() {
        return message;
    }
    public int getPid() {
        return pid;
    }
}

