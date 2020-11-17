
public class Photo {
	private int size;//
	private String name;//
	private String location;//
	
	public Photo(String name, int size, String location) {
		this.name=name;
		this.size=size;
		this.location=location;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Photo) {
		if (this.location.equals(((Photo) o).getLocation())) {
			return true;}}
		else if(o instanceof String|| o==null){
			if(o!=null) {
				if(this.location.equals(o)) {
					return true;
				}}
			else {
				if(this.location==null) {
					return true;
				}}
			}
			
		
		return false;
		}
	}


