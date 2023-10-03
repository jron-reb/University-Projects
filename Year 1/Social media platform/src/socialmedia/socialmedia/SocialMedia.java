package socialmedia;

import java.io.IOException;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;

/**
 * SocialMedia is a minimally compiling, but non-functioning implementor of the
 * SocialMediaPlatform interface.
 * 
 * @author Jeroen Mijer
 * @author Alex Robertson
 * @version 0.1
 */
public class SocialMedia implements SocialMediaPlatform {

	// Public list of all accounts
	private transient static ArrayList<Account> accounts;
	// public list of all posts
	// contains 3 lists, original posts, comments and endorsements
	private transient ArrayList<Post> posts;

	public SocialMedia() {
		this.accounts = new ArrayList<Account>();
		this.posts = new ArrayList<Post>();
	}

	@Override
	public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
		// check if handle is valid
		// empty, more than 30 characters, has white spaces then is invalid
		if (handle.isEmpty() || handle.length() > 30 || handle.contains(" ")) {
			throw new InvalidHandleException("empty, OR more than 30 characters, OR has white spaces then is invalid");
		}

		// check if Handle (handle) is unique
		for (Account account : accounts) {
			if (account.getHandle().equals(handle)) {
				throw new IllegalHandleException("That handle is not unique");
			}
		}

		// create account with descfield=""
		Account accountTemp = new Account(handle, "");

		// add account to account list
		accounts.add(accountTemp);

		// return uid
		return accountTemp.getUID();

	}

	@Override
	public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
		// check if handle is valid
		// empty, more than 30 characters, has white spaces then is invalid
		if (handle.isEmpty() || handle.length() > 30 || handle.contains(" ")) {
			throw new InvalidHandleException("empty, OR more than 30 characters, OR has white spaces then is invalid");
		}

		// check if Handle (handle) is unique
		for (Account account : accounts) {
			if (account.getHandle().equals(handle)) {
				throw new IllegalHandleException("That handle is not unique");
			}
		}

		// create account with descfield=""
		Account accountTemp = new Account(handle, description);

		// add account to account list
		accounts.add(accountTemp);

		// return uid
		return accountTemp.getUID();
	}

	@Override
	public void removeAccount(int id) throws AccountIDNotRecognisedException {
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getUID() == id) {
				accounts.remove(id - 1);
			}
		}
		throw new AccountIDNotRecognisedException();
	}

	@Override
	public void removeAccount(String handle) throws HandleNotRecognisedException {
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getHandle().equals(handle)) {
				accounts.remove(i);
			}
		}
		throw new HandleNotRecognisedException();
	}

	@Override
	public void changeAccountHandle(String oldHandle, String newHandle)
			throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
		// Check if new handle is valid
		if (newHandle.isEmpty() || newHandle.length() > 30 || newHandle.contains(" ")) {
			throw new InvalidHandleException("empty, OR more than 30 characters, OR has white spaces then is invalid");
		}

		// check if new handle is unique
		for (Account account : accounts) {
			if (account.getHandle().equals(newHandle)) {
				throw new IllegalHandleException("That handle is not unique");
			}
		}

		// Change handle from oldHandle to newHandle
		for (Account account : accounts) {
			if (account.getHandle().equals(oldHandle)) {
				account.setDescField(newHandle);
			}
		}
		throw new HandleNotRecognisedException();

	}

	@Override
	public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
		for (Account account : accounts) {
			if (account.getHandle().equals(handle)) {
				account.setDescField(description);
			}
		}
		throw new HandleNotRecognisedException();
		// for account in accounts:
		// if account.getHandle() = handle:
		// account.setDescField(description)
		// break
	}

	@Override
	public String showAccount(String handle) throws HandleNotRecognisedException {
		// TODO Auto-generated method stub
		// Account account;
		// for searchAccount in accounts:
		// if searchAccount.getHandle() == handle:
		// account = searchAccount
		// break
		//
		// String string = String.format("""
		// ID: %d
		// Handle: %s
		// Description: %s
		// Post Count: %d
		// Endorse Count: %d
		// """, account.getUID(), account.getHandle(), account.getDescField(),
		// postCount(account.getHandle()), EndorseCount(account.getHandle()))
		// return string
		return null;
	}

	@Override
	public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
		// verification
		if (message.length() <= 100 && message.isEmpty() == false) {
			// look through list of accounts
			for (Account account : accounts) {
				// find account with matching handle
				if (account.getHandle().equals(handle)) {
					// put in account into new og post
					OriginalPost post = new OriginalPost(message, account);
					account.addPost(post);
					this.posts.add(post);
					return post.getPid();
				}
			}
			throw new HandleNotRecognisedException();
		} else {
			throw new InvalidPostException();
		}

	}

	@Override
	public int endorsePost(String handle, int pid)
			throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
		// TODO Auto-generated method stub
		// look through list of accounts

		// TODO check if post has already been endorsed in which case simply break

		for (Account account : accounts) {
			if (account.getHandle().equals(handle)) {
				// find post that wants to be endorsed using pid
				// check if endorsing an endorsement
				for (Post post : posts) {
					if (post.getPid() == pid) {
						if (!(post instanceof Endorsement)) {
							//
							String message = "EP@" + post.getAuthor().getHandle() + ": " + post.getMessage();
							Endorsement endorsement = new Endorsement(message, account, pid);
							// have to add endorsement to list of posts, account and make it a child of
							// other post
							this.posts.add(endorsement);
							account.addPost(endorsement);
							// check if endorsing an original post or a comment in order to downcast
							return endorsement.getPid();
						}
						throw new NotActionablePostException();
					}
				}
				throw new PostIDNotRecognisedException();
			}
		}
		throw new HandleNotRecognisedException();
		// find account that made the post
		// exctract message
		// put it in format with endorsed accoutn etc
		// make endorsement
		// link endorsement to account, list of all posts, list of posts in socialmedia,
		// to ogpost or comment it is derived from

	}

	private int cognitiveReducer(String message, Account account, int pid, Post post) {
		
		Comment comment = new Comment(message, account, pid);
		// have to add endorsement to list of posts, account and make it a child of
		// other post
		this.posts.add(comment);
		account.addPost(comment);
		// check if endorsing an original post or a comment in order to downcast
		if (post instanceof OriginalPost) {
			((OriginalPost) post).addComment(comment);
		}
		if (post instanceof Comment) {
			((Comment) post).addComment(comment);
		}
		//
		return comment.getPid();

		
	}
	
	@Override
	public int commentPost(String handle, int pid, String message) throws HandleNotRecognisedException,
			PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
		// test if message is valid
		if (!(message.length() <= 100 && message.isEmpty() == false)) {
			throw new InvalidPostException();
		}

		//find account that wants to comment on post
		//instantiate account with handle longer than 30 (32), since this can't be created by user, will be unique
		//instead of if accountcommentor null then test if the length <= 30
		//Doesnt matter if uid or anything else is unique, especially since checking for accounts existing is done by iterating through list of accounts
		Account accountCommentor = new Account("admin123456789123456789123456789", "");
		//instantiate account with handle longer than 30, since this can't be created by user, will be unique
		//instead of if accountcommentor null then test if the length <= 30
		// ahdnle = fdcgghfvgfdvghjvhgvgvghvbj
		
			for (Account account : accounts) {
				if (account.getHandle().equals(handle)) {
					accountCommentor = account;
					break;
				}
			}	
			if (accountCommentor.getHandle().length() <= 30) {
				for (Post post : posts) {
					if (post.getPid() == pid) {
						if (!(post instanceof Endorsement)) {
							return cognitiveReducer(message, accountCommentor, pid, post);
						}
						throw new NotActionablePostException();
					}
				}
				throw new PostIDNotRecognisedException();
			} throw new HandleNotRecognisedException();

				// find account that wants to comment on the post
		// find the post that wants to be commented on using pid
		// check if commenting on an endorsement
		// create endorsement
		// add endorsement to list of pists, to account making comment, make it child of
		// other post
		//
	}

	@Override
	public void deletePost(int id) throws PostIDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public String showIndividualPost(int pid) throws PostIDNotRecognisedException {
		String id = "ID: " + pid;
		String account = "Account: " + posts.get(pid-1).getAuthor();
		String number; 
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringBuilder showPostChildrenDetails(int id)
			throws PostIDNotRecognisedException, NotActionablePostException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfAccounts() {
		return accounts.size();
	}

	@Override
	public int getTotalOriginalPosts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalEndorsmentPosts() {
		// TODO Auto-generated method stub
		// ALEX
		return 0;
	}

	@Override
	public int getTotalCommentPosts() {
		// TODO Auto-generated method stub
		// ALEX
		return 0;
	}

	@Override
	public int getMostEndorsedPost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMostEndorsedAccount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void erasePlatform() {
		// TODO Auto-generated method stub
		// Method empties this SocialMediaPlatform of its contents and resets all
		// internal counters.
		// set lists to empty
		this.accounts.clear();
		this.posts.clear();

	}

	@Override
	public void savePlatform(String filename) throws IOException {
		// Prepend platform content to file filename
		try (FileOutputStream file = new FileOutputStream(filename, false);
				ObjectOutput stream = new ObjectOutputStream(file)) {
			Integer length = accounts.size();//

			stream.writeObject(length);
			for (Account account : accounts) {
				stream.writeObject(account);
			}

			length = posts.size();

			stream.writeObject(length);
			for (Post post : posts) {
				stream.writeObject(post);

			}

			stream.flush();

		}

	}

	@Override
	public void loadPlatform(String filename) throws IOException, ClassNotFoundException {

		// SocialMedia platform = new SocialMedia();

		try (FileInputStream file = new FileInputStream(filename);
				ObjectInputStream stream = new ObjectInputStream(file)) {

			// make generic account and post
			BufferedReader br = new BufferedReader(new FileReader(filename));
			if (br.readLine() == null) {
				//file is empty here
				try {
					createAccount("admin");
					createPost("admin", "The original content was removed from the system and is no longer available.");
				
				} catch (Exception e) {
				System.out.println("how have ypu broken this .");
				}
				
			}
			br.close();

			Integer length = (Integer) stream.readObject();
			for (int i = 0; i < length; i++) {
				accounts.add((Account) stream.readObject());
			}

			length = (Integer) stream.readObject();
			for (int j = 0; j < length; j++) {
				posts.add((Post) stream.readObject());
			}

		}
	}

	public static ArrayList<Account> getAccounts() {
		return accounts;
	}

	public ArrayList<Post> getPosts() {
		return posts;
	}

}
