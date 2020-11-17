import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Analyzer {
	private Profile[] profiles=new Profile[100];//
	private int count=0;//
	private Profile profile;//
	
	
	public void parse(String[] fileLines) throws DataParseException{
		for(int i=0;i<fileLines.length;i++) {
			String temp= (String) fileLines[i];
			if(temp.startsWith("PROFILE")) {
				String name= temp.substring(8,temp.lastIndexOf(" "));// Getting name after profile
				String gender= temp.substring(temp.lastIndexOf(" ")+1);// Getting gender after space till end	
			profile = new Profile(name,gender);
				profiles[this.count]= profile;
				this.count++;}
			else {
				Object[] notProfile= new Object[1];
				notProfile[0]= temp;
				profile.parseDataDump(notProfile);
			}}
				
			
			
			
		
	}
	
	
	public List analyzeProfiles() {
		List<String> negProfiles= new ArrayList<String>();
		List<String> neg= new ArrayList<String>();
		List<String> pos= new ArrayList<String>();
		for(int i=0;i<profiles.length;i++) {
			for(int j=0;j<profiles[i].getMessages().length;j++) {
				String body=(((Message) (profiles[i].getMessages()[j])).getBody());
				neg= ((Message)(profiles[i].getMessages()[j])).getNegativeKeywords(body);// Checking for negative words
				pos= ((Message)(profiles[i].getMessages()[j])).getPositiveKeywords(body);// Checking for positive words
				System.out.println(neg);
				System.out.println(pos);
				}
			if(neg.size()>0) {
				negProfiles.add(profiles[i].getUsername());
				break;}
		}
		
				
		return negProfiles;
			}
				
			
	


	public Profile[] getProfiles() {
		return profiles;
	}


	public void setProfiles(Profile[] profiles) {
		this.profiles = profiles;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public Profile getProfile() {
		return profile;
	}


	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}
