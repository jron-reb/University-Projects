package socialmedia;

import java.util.ArrayList;

public class OriginalPost extends Post{
    ArrayList <Endorsement> endorsements;
    ArrayList <Comment> comments;

    public OriginalPost(String message, Account author) {
        //TODO
        //throw errors
        super(message, author);
        this.endorsements = new ArrayList<Endorsement>();
        this.comments = new ArrayList<Comment>();

    }
    
    public ArrayList<Endorsement> getEndorsements() {
        return endorsements;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void addEndorsement(Endorsement endorsement) {
        this.endorsements.add(endorsement);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}