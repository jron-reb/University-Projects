package socialmedia;

import java.util.ArrayList;

public class Account {
    private Integer uid;
    private String handle;
    private String descField;
    //needs a list of all posts the user has written
    private ArrayList<Post> posts;
    // private ArrayList<OriginalPost> OGPosts;
    // private ArrayList<Comment> comments;
    // private ArrayList<Endorsement> endorsements;

    public Account(String handle, String descField) {
        //Any Verification should be done before instantiating account
        this.uid = SocialMedia.getAccounts().size()+1;
        this.handle = handle;
        this.descField = descField;
        this.posts = new ArrayList<Post>();
    }

    public String getDescField() {
        return descField;
    }

    public Integer getUID() {
        return uid;
    }

    public String getHandle() {
        return handle;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setDescField(String descField) {
        this.descField = descField;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }
}
