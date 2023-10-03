package socialmedia;

import java.util.ArrayList;

public class Comment extends Post {

    Integer postPointer; // holds the postID that the comment is replying to
    ArrayList <Endorsement> endorsements;
    ArrayList <Comment> comments;

    public Comment(String message, Account author, Integer postPointer) {
        super(message, author);
        //TODO
        //throw errors
        this.endorsements = new ArrayList<Endorsement>();
        this.comments = new ArrayList<Comment>();
        this.postPointer = postPointer;
    }
    public Integer getPostPointer() {
        return postPointer;
    }
    
    public void addEndorsement(Endorsement endorsement) {
        this.endorsements.add(endorsement);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

}
