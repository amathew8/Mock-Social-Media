import java.util.ArrayList;

public class Message {
	private String contact;//
	private String date;//
	private String body;//
	public static final String[] NEGATIVE_WORDS= {"terrible", "strange", "mad", "furious", "aggressive", "aloof", "arrogant", "belligerent", "big-headed", "bitchy", "boastful", "bone-idle", "boring", "bossy", "callous", "cantankerous", "careless", "changeable", "clinging", "compulsive", "conservative", "cowardly", "cruel", "cunning", "cynical", "deceitful", "detached", "dishonest", "dogmatic", "domineering", "finicky", "foolish", "foolhardy", "fussy", "greedy", "grumpy", "gullible", "harsh", "impatient", "impolite", "impulsive", "inconsiderate", "inconsistent", "indecisive", "indiscreet", "inflexible", "interfering", "intolerant", "irresponsible", "jealous", "lazy", "Machiavellian", "materialistic", "mean", "miserly", "moody", "narrow-minded", "nasty", "naughty", "nervous", "obsessive", "obstinate", "overcritical", "overemotional", "parsimonious", "patronizing", "perverse", "pessimistic", "pompous", "possessive", "pusillanimous", "quarrelsome", "quick-tempered", "resentful", "rude", "ruthless", "sarcastic", "secretive", "selfish", "self-centred", "self-indulgent", "silly", "sneaky", "stingy", "stubborn", "stupid", "superficial", "tactless", "timid", "touchy", "thoughtless", "truculent", "unkind", "unpredictable", "unreliable", "untidy", "untrustworthy", "vague", "vain", "vengeful", "vulgar", "weak-willed"};//
	public static final String[] POSITIVE_WORDS= {"adaptable", "adventurous", "affable", "affectionate", "agreeable", "ambitious", "amiable", "amicable", "amusing", "brave", "bright", "broad-minded", "calm", "careful", "charming", "communicative", "compassionate", "conscientious", "considerate", "convivial", "courageous", "courteous", "creative", "decisive", "determined", "diligent", "diplomatic", "discreet", "dynamic", "easygoing", "emotional", "energetic", "enthusiastic", "exuberant", "fair-minded", "faithful", "fearless", "forceful", "frank", "friendly", "funny", "generous", "gentle", "good", "gregarious", "hard-working", "helpful", "honest", "humorous", "imaginative", "impartial", "independent", "intellectual", "intelligent", "intuitive", "inventive", "kind", "loving", "loyal", "modest", "neat", "nice", "optimistic", "passionate", "patient", "persistent", "pioneering", "philosophical", "placid", "plucky", "polite", "powerful", "practical", "pro-active", "quick-witted", "quiet", "rational", "reliable", "reserved", "resourceful", "romantic", "self-confident", "self-disciplined", "sensible", "sensitive", "shy", "sincere", "sociable", "straightforward", "sympathetic", "thoughtful", "tidy", "tough", "unassuming", "understanding", "versatile", "warmhearted", "willing", "witty"};//
	private static ArrayList<String> negKeywords= new ArrayList<String>();//
	private static ArrayList<String> posKeywords= new ArrayList<String>();//
	
	public Message(String contact, String date, String body) {
		this.contact=contact;
		this.date=date;
		this.body=body;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	public static String[] getNegativeWords() {
		return NEGATIVE_WORDS;
	}


	public static String[] getPositiveWords() {
		return POSITIVE_WORDS;
	}
	
	public static ArrayList<String> getNegativeKeywords(String body) {
		for(int i=0;i<NEGATIVE_WORDS.length;i++) {
			if(body.contains(NEGATIVE_WORDS[i])) {
				negKeywords.add(NEGATIVE_WORDS[i]);
			}
		}
		return negKeywords;
		
	}
	
	public static ArrayList<String> getPositiveKeywords(String body) {
		for(int i=0;i<POSITIVE_WORDS.length;i++) {
			if(body.contains(POSITIVE_WORDS[i])) {
				posKeywords.add(POSITIVE_WORDS[i]);
			}
		}
		return posKeywords;
		
	}
	

}
