package socialmedia;

public class Endorsement extends Post {

    Integer postPointer; // holds the postID that the endorsement is endorsing

    public Endorsement(String message, Account author, Integer postPointer) {
        super(message, author);
        // TODO
        // throw errors

        this.postPointer = postPointer;

    }

    public Integer getPostPointer() {
        return postPointer;
    }

}
