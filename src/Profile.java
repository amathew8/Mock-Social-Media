
public class Profile {
	private Object[] photos = new Object[0];//
	private Object[] contacts = new Object[0];//
	private Object[] posts = new Object[0];//
	private Object[] messages = new Object[0];//
	private String username;//
	private String gender;//
	private int photoCount = 0;//
	private int postCount = 0;//
	private int messageCount = 0;//
	private int contactCount = 0;//

	public Profile(String username, String gender) {
		this.username = username;
		this.gender = gender;
	}

	public Object[] getPhotos() {
		return photos;
	}

	public void setPhotos(Object[] photos) {
		this.photos = photos;
	}

	public Object[] getContacts() {
		return contacts;
	}

	public void setContacts(Object[] contacts) {
		this.contacts = contacts;
	}

	public Object[] getPosts() {
		return posts;
	}

	public void setPosts(Object[] posts) {
		this.posts = posts;
	}

	public Object[] getMessages() {
		return messages;
	}

	public void setMessages(Object[] messages) {
		this.messages = messages;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void parseDataDump(Object[] lines) throws DataParseException {
		for (int i = 0; i < lines.length; i++) {
			String line = (String) lines[i];
			System.out.println("nve");
			if (mineContact(line)) {
			} 
			else if (minePhoto(line)) {
			} 
			else if (minePost(line)) {
			}
			else if (mineMessage(line)) {
			}
			else {
				throw new DataParseException(line);
			}

		}
	}

	private boolean minePhoto(String line) {
		boolean isPhoto = false;
		if ((!line.startsWith("POST")) && (line.contains(":/"))) {
			isPhoto = true;
		} else {
			return false;
		}
		String name = line.substring(line.lastIndexOf("/") + 1, line.lastIndexOf(" "));
		int size = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
		String location = line.substring(0, line.lastIndexOf(" "));
		Photo p = new Photo(name, size, location);
		boolean duplicate = false;
		for (int i = 0; i < photos.length; i++) {
			if (photos[i].equals(p)) {
				duplicate = true;// CHECK FOR DUPLICATE PHOTOS BEFORE ADDING TO COUNT AND ARRAY
			}
		}
		if (duplicate == false) {
			photoCount++;
			inefficientAndDangerousAddObject(p);
		}
		return isPhoto;
	}

	private boolean mineContact(String line) {
		boolean isContact = false;
		if (line.startsWith("CONTACT")) {
			isContact = true;
		} else {
			return false;
		}
		String name = line.substring(8);
		contactCount++;
		inefficientAndDangerousAddObject(name);
		return isContact;
	}

	private boolean minePost(String line) {
		boolean isPost = false;
		Post p = null;
		String[] temp = line.split("#");
		if (line.startsWith("POST")) {
			isPost = true;
		} else {
			return false;
		}

		if (line.contains(":/")) {
			minePhoto(temp[3]);
		}
		String text = temp[2];
		String date = temp[1];
		if (line.contains(":/")) {
			p = new Post(date, text, photos[photos.length - 1]);
		} else {
			p = new Post(date, text);
		}
		postCount++;
		inefficientAndDangerousAddObject(p);
		return isPost;
	}

	private boolean mineMessage(String line) {
		boolean isMessage=false;
		Message m=null;
		if(line.startsWith("MESSAGE")) {
			isMessage=true;
		}
		else {
			return false;
		}
		
		String[] temp = line.split("#");
		String c="CONTACT "+temp[1];
		mineContact(c);

		m= new Message(temp[1],temp[2],temp[3]);
		messageCount++;
		inefficientAndDangerousAddObject(m);
		return isMessage;
		
	}
		
	

	public void inefficientAndDangerousAddObject(Object object) {
		if (object instanceof Photo) {
			if (photoCount > 0) {// IF CALLING PARSEDATADUMP
				Photo[] temp = null;
				if (photos.length < photoCount) {
					temp = new Photo[photoCount];
					System.arraycopy(photos, 0, temp, 0, photos.length);
				}
				for (int i = 0; i < temp.length; i++) {
					if (temp[i] == null) {
						temp[i] = (Photo) object;
					}
				}
				photos = temp.clone();
			} else {// IF NOT CALLING PARSEDATADUMP
				Photo[] temp = new Photo[photos.length + 1];
				System.arraycopy(photos, 0, temp, 0, photos.length);
				for (int i = 0; i < temp.length; i++) {
					if (temp[i] == null) {
						temp[i] = (Photo) object;
					}
				}
				photos = temp.clone();
			}
		}

		else if (object instanceof Post) {
			if(photoCount>0) {
			Post[] temp = null;
			if (posts.length < postCount) {
				temp = new Post[postCount];
				System.arraycopy(posts, 0, temp, 0, posts.length);
			}
			for (int i = 0; i < temp.length; i++) {
				if (temp[i] == null) {
					temp[i] = (Post) object;
				}
			}
			posts = temp.clone();}
			else {
			Post[] temp = new Post[posts.length + 1];
			System.arraycopy(posts, 0, temp, 0, posts.length);
			for (int i = 0; i < temp.length; i++) {
				if (temp[i] == null) {
					temp[i] = (Post) object;
				}
			}
			posts = temp.clone();
		}
	}
		else if (object instanceof Message) {
			if(messageCount>0) {
			Message[] temp = null;
			if (messages.length < messageCount) {
				temp = new Message[messageCount];
				System.arraycopy(messages, 0, temp, 0, messages.length);
			}
			for (int i = 0; i < temp.length; i++) {
				if (temp[i] == null) {
					temp[i] = (Message) object;
				}
			}
			messages = temp.clone();}
			else {
			Message[] temp = new Message[posts.length + 1];
			System.arraycopy(messages, 0, temp, 0, messages.length);
			for (int i = 0; i < temp.length; i++) {
				if (temp[i] == null) {
					temp[i] = (Message) object;
				}
			}
			messages = temp.clone();
		}
	}
		else {
			if(contactCount>0) {
				String [] temp = null;
				if (contacts.length < contactCount) {
					temp = new String[contactCount];
					System.arraycopy(contacts, 0, temp, 0, contacts.length);
				}
				for (int i = 0; i < temp.length; i++) {
					if (temp[i] == null) {
						temp[i] = (String) object;
					}
				}
				contacts = temp.clone();}
				else {
				String[] temp = new String[contacts.length + 1];
				System.arraycopy(contacts, 0, temp, 0, contacts.length);
				for (int i = 0; i < temp.length; i++) {
					if (temp[i] == null) {
						temp[i] = (String) object;
					}
				}
				contacts=temp.clone();
				
			}
		}
}
}

