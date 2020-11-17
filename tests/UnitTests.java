import static org.junit.Assert.*; 

import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.List; 

import org.junit.Test; 


public class UnitTests { 
     
    public static int passed = 0; 

    @Test 
    public void testPhoto() { 
        Photo p = new Photo("kitten.jpg", 234, "C:\\MyPhotos\\kitten.jpg"); 
        assertEquals("kitten.jpg",p.getName()); 
        assertEquals(234,p.getSize()); 
        assertEquals("C:\\MyPhotos\\kitten.jpg",p.getLocation()); 
        assertNotEquals(0,++passed);         
    } 
     
    @Test 
    public void testPost() { 
        Post p = new Post("03/23/2016","It was as sunny day today!"); 
        assertEquals("03/23/2016",p.getDate()); 
        assertEquals("It was as sunny day today!",p.getText()); 
        assertEquals(null,p.getPhoto()); 
         
        Photo photo = new Photo("kitten.jpg", 234, "C:\\MyPhotos\\kitten.jpg"); 
        p.addPhoto(photo); 
        assertEquals(photo,p.getPhoto()); 
        assertNotEquals(0,++passed);         
    }     
     
    @Test 
    public void testMessage(){ 
        Message m = new Message("Jane Smith", "03/23/2016", "How did your class go?"); 
        assertEquals("Jane Smith", m.getContact()); 
        assertEquals("03/23/2016", m.getDate()); 
        assertEquals("How did your class go?", m.getBody()); 
        assertNotEquals(0,++passed);         
    } 
     
    @Test 
    public void testNegativeAnalysis(){ 
        String text = "This professor is really boring and careless. I don't like her at all."; 
        ArrayList<String> result = Message.getNegativeKeywords(text); 
        assertEquals("boring", result.get(0)); 
        assertEquals("careless", result.get(1)); 
        assertNotEquals(0,++passed);         
    } 
     
    @Test 
    public void testPositiveAnalysis(){ 
        String text = "This professor is really compassionate and nice. I think she's good!"; 
        ArrayList<String> result = Message.getPositiveKeywords(text); 
        assertEquals("compassionate", result.get(0)); 
        assertEquals("good", result.get(1));     
        assertEquals("nice", result.get(2)); 
        assertNotEquals(0,++passed);         
    }     
     
    @Test 
    public void testProfile1(){ 
        Profile p = new Profile("John Smith","male"); 
        assertEquals("John Smith", p.getUsername()); 
        assertEquals("male", p.getGender()); 
        assertNotEquals(0,++passed);         
    } 
     
    @Test 
    public void testProfile2(){ 
        Profile p = new Profile("John Smith","male");         
        String[] lines = {"http://www.cs.gmu.edu/~kdobolyi/headshot.jpg 223"}; 
        String exceptionType = "NONE";         
        try{ 
            p.parseDataDump(lines); 
        }catch (Exception e){ 
            exceptionType = e.getClass().toString(); 
        } 
         
        assertEquals(223, ((Photo)p.getPhotos()[0]).getSize()); 
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot.jpg", ((Photo)p.getPhotos()[0]).getLocation());     
        assertEquals("headshot.jpg", ((Photo)p.getPhotos()[0]).getName());     
        assertEquals(1,p.getPhotos().length); 
        assertEquals("NONE",exceptionType);         
        assertNotEquals(0,++passed);                 
    } 
     
    @Test 
    public void testProfile3(){ 
        Profile p = new Profile("John Smith","male");         
        String[] lines = {"http://www.cs.gmu.edu/~kdobolyi/headshot.jpg223"}; 
        String exceptionType = "NONE"; 
        try{ 
            p.parseDataDump(lines); 
        }catch (Exception e){ 
            exceptionType = e.getClass().toString(); 
        } 
         
        assertNotEquals("NONE",exceptionType); 
        assertNotEquals(0,++passed);         
    }     

    @Test 
    public void testProfile4(){ 
        Profile p = new Profile("John Smith","male");         
        String[] lines = {"CONTACT Jane Doe"}; 
        String exceptionType = "NONE";         
        try{ 
            p.parseDataDump(lines); 
        }catch (Exception e){ 
            exceptionType = e.getClass().toString(); 
        } 
         
        assertEquals("Jane Doe", ((String)p.getContacts()[0])); 
        assertEquals("NONE",exceptionType);             
        assertNotEquals(0,++passed);         
    } 
     
    @Test 
    public void testProfile5(){ 
        Profile p = new Profile("John Smith","male");                 
        String[] lines = {"POST#12/23/15#my message#http://www.cs.gmu.edu/~kdobolyi/headshot.jpg 223"}; 
        String exceptionType = "NONE"; 
        try{ 
            p.parseDataDump(lines); 
        }catch (Exception e){ 
            exceptionType = e.getClass().toString(); 
        } 
     
        assertEquals("NONE",exceptionType);             
        assertEquals("12/23/15", ((Post)p.getPosts()[0]).getDate()); 
        assertEquals("my message", ((Post)p.getPosts()[0]).getText());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot.jpg", ((Post)p.getPosts()[0]).getPhoto().getLocation());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot.jpg", ((Photo)p.getPhotos()[0]).getLocation());             
        assertNotEquals(0,++passed); 
    }         
     
    @Test 
    public void testProfile6(){ 
        Profile p = new Profile("John Smith","male");                 
        String[] lines = {"POST 12/23/15 my message http://www.cs.gmu.edu/~kdobolyi/headshot.jpg 223"}; 
        String exceptionType = "NONE"; 
        try{ 
            p.parseDataDump(lines); 
        }catch (Exception e){ 
            exceptionType = e.getClass().toString(); 
        } 
     
        assertNotEquals("NONE",exceptionType);                     
        assertNotEquals(0,++passed); 
    } 
     
    @Test 
    public void testProfile7(){ 
        Profile p = new Profile("John Smith","male");                 
        String[] lines = {"MESSAGE#Jane Doe#12/23/15#my message is short"}; 
        String exceptionType = "NONE"; 
        try{ 
            p.parseDataDump(lines); 
        }catch (Exception e){ 
            exceptionType = e.getClass().toString(); 
        } 
     
        assertEquals("NONE",exceptionType);             
        assertEquals("Jane Doe", ((Message)p.getMessages()[0]).getContact());         
        assertEquals("12/23/15", ((Message)p.getMessages()[0]).getDate()); 
        assertEquals("my message is short", ((Message)p.getMessages()[0]).getBody());         
        assertEquals("Jane Doe", ((String)p.getContacts()[0]));         
        assertNotEquals(0,++passed); 
    }         
     
    @Test 
    public void testProfile8(){ 
        Profile p = new Profile("John Smith","male");     
        Photo photo = new Photo("kitten.jpg", 234, "C:\\MyPhotos\\kitten.jpg"); 
        p.inefficientAndDangerousAddObject(photo); 
        assertEquals(photo,(Photo)p.getPhotos()[0]); 
        assertNotEquals(0,++passed);         
    } 
     
    @Test 
    public void testProfile9(){ 
        Profile p = new Profile("John Smith","male");     
        Post post = new Post("03/23/2016","It was as sunny day today!"); 
        p.inefficientAndDangerousAddObject(post); 
        assertEquals(post,(Post)p.getPosts()[0]); 
        assertNotEquals(0,++passed);         
    }     
     
    @Test 
    public void testProfile10(){ 
        Profile p = new Profile("John Smith","male");     
        Message m = new Message("Jane Smith", "03/23/2016", "How did your class go?"); 
        p.inefficientAndDangerousAddObject(m); 
        assertEquals(m,(Message)p.getMessages()[0]); 
        assertNotEquals(0,++passed);         
    }     
     
    @Test 
    public void testProfile11(){ 
        Profile p = new Profile("John Smith","male");     
        String contact = "Jane"; 
        p.inefficientAndDangerousAddObject(contact); 
        assertEquals(contact,(String)p.getContacts()[0]); 
        assertNotEquals(0,++passed);         
    }         
     
    @Test 
    public void testProfile12(){ 
        Profile p = new Profile("John Smith","male");     
        String[] lines = {"Nothing"}; 
        String exceptionType = "NONE"; 
        try{ 
            p.parseDataDump(lines); 
        }catch (Exception e){ 
            exceptionType = e.getClass().toString(); 
        } 
     
        assertEquals("class DataParseException",exceptionType);     
        assertNotEquals(0,++passed);         
    }         
     
    @Test 
    public void testProfile13(){ 
        Profile p = new Profile("John Smith","male");                 
        String[] lines = {"POST#12/23/15#my message#http://www.cs.gmu.edu/~kdobolyi/headshot.jpg 223", "POST#12/23/15#my message#http://www.cs.gmu.edu/~kdobolyi/headshot.jpg 223", "POST#12/23/15#my message#http://www.cs.gmu.edu/~kdobolyi/new.jpg 223"};
        String exceptionType = "NONE"; 
        try{ 
            p.parseDataDump(lines); 
        }catch (Exception e){ 
            exceptionType = e.getClass().toString(); 
        } 
     
        assertEquals("NONE",exceptionType);             
        assertEquals("12/23/15", ((Post)p.getPosts()[0]).getDate()); 
        assertEquals("my message", ((Post)p.getPosts()[0]).getText());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot.jpg", ((Post)p.getPosts()[0]).getPhoto().getLocation());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot.jpg", ((Photo)p.getPhotos()[0]).getLocation());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/new.jpg", ((Photo)p.getPhotos()[1]).getLocation()); 
        assertEquals(2, p.getPhotos().length);             
        assertNotEquals(0,++passed); 
    }     
     
    @Test 
    public void testAnalyzer(){ 
        Analyzer a = new Analyzer(); 
         
        assertEquals(100,a.getProfiles().length); 
        String[] lines = { 
                "PROFILE JaneSmith female", 
                "http://www.cs.gmu.edu/~kdobolyi/headshot.jpg 223", 
                "MESSAGE#Jane Doe#12/23/15#my message is short", 
                "POST#12/23/15#my message#http://www.cs.gmu.edu/~kdobolyi/headshot2.jpg 223", 
                "http://www.google.com/headshot.jpg 2456", 
                "CONTACT Joe Dupont", 
                "MESSAGE#Sam Smith#11/13/15#this is another message", 
                "POST#12/24/15#my message#http://www.cs.gmu.edu/~kdobolyi/headshot3.jpg 223", 
                 
                "PROFILE JohnDoe male", 
                "POST#12/24/15#my message#http://www.cs.gmu.edu/~kdobolyi/headshot4.jpg 223",                     
                "http://www.cs.gmu.edu/~kdobolyi/clouds.jpg 223", 
                "MESSAGE#Johhny Doe#12/23/15#my message is also short", 
                "POST#12/23/15#my message#http://www.cs.gmu.edu/~kdobolyi/headshot3.jpg 223", 
                "MESSAGE#Sally Fields#11/13/15#this is another message to someone else", 
                "http://www.google.com/headshot1.jpg 2456",         
                "http://www.google.com/headshot11.jpg 2456",                     
        }; 
        try { 
            a.parse(lines); 
        } catch (DataParseException e) { 
            fail("unexpected exception"); 
        } 
         
        Profile one = a.getProfiles()[0]; 
        Profile two = a.getProfiles()[1]; 
         
        assertEquals(100,a.getProfiles().length); 
        System.out.println(one); 
        assertEquals("JaneSmith", one.getUsername()); 
        assertEquals("female", one.getGender());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot.jpg", ((Photo)one.getPhotos()[0]).getLocation());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot2.jpg", ((Photo)one.getPhotos()[1]).getLocation()); 
        assertEquals("http://www.google.com/headshot.jpg", ((Photo)one.getPhotos()[2]).getLocation());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot3.jpg", ((Photo)one.getPhotos()[3]).getLocation()); 
        assertEquals(4,one.getPhotos().length);     
        assertEquals("my message is short", ((Message)one.getMessages()[0]).getBody());             
        assertEquals("this is another message", ((Message)one.getMessages()[1]).getBody());     
        assertEquals(2,one.getMessages().length);             
        assertEquals("Jane Doe", one.getContacts()[0].toString());             
        assertEquals("Joe Dupont", one.getContacts()[1].toString());     
        assertEquals("Sam Smith", one.getContacts()[2].toString());             
        assertEquals("12/23/15", ((Post)one.getPosts()[0]).getDate());     
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot2.jpg", ((Post)one.getPosts()[0]).getPhoto().getLocation());     
        assertEquals("12/24/15", ((Post)one.getPosts()[1]).getDate());     
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot3.jpg", ((Post)one.getPosts()[1]).getPhoto().getLocation());     
         
        System.out.println(two); 
        assertEquals("JohnDoe", two.getUsername()); 
        assertEquals("male", two.getGender());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot4.jpg", ((Photo)two.getPhotos()[0]).getLocation());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/clouds.jpg", ((Photo)two.getPhotos()[1]).getLocation()); 
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot3.jpg", ((Photo)two.getPhotos()[2]).getLocation());         
        assertEquals("http://www.google.com/headshot1.jpg", ((Photo)two.getPhotos()[3]).getLocation()); 
        assertEquals("http://www.google.com/headshot11.jpg", ((Photo)two.getPhotos()[4]).getLocation());         
        assertEquals(5,two.getPhotos().length);     
        assertEquals("my message is also short", ((Message)two.getMessages()[0]).getBody());             
        assertEquals("this is another message to someone else", ((Message)two.getMessages()[1]).getBody());     
        assertEquals(2,one.getMessages().length);             
        assertEquals("Johhny Doe", two.getContacts()[0].toString());             
        assertEquals("Sally Fields", two.getContacts()[1].toString());         
        assertEquals("12/24/15", ((Post)two.getPosts()[0]).getDate());     
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot4.jpg", ((Post)two.getPosts()[0]).getPhoto().getLocation());     
        assertEquals("12/23/15", ((Post)two.getPosts()[1]).getDate());     
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot3.jpg", ((Post)two.getPosts()[1]).getPhoto().getLocation()); 
        assertNotEquals(0,++passed);         
    } 
     
    @Test 
    public void testPredictions(){ 
        Analyzer a = new Analyzer(); 
        List result; 
         
        String[] lines = { 
                "PROFILE Dostoevsky male", 
                "http://www.cs.gmu.edu/~kdobolyi/headshot.jpg 223", 
                "MESSAGE#Jane Doe#12/23/15#He was in the hospital from the middle of Lent till after Easter. When he was better, he remembered the dreams he had had while he was feverish and delirious. He dreamt that the whole world was condemned to a terrible new strange plague that had come to Europe from the depths of Asia. All were to be destroyed except a very few chosen. Some new sorts of microbes were attacking the bodies of men, but these microbes were endowed with intelligence and will. Men attacked by them became at once mad and furious. But never had men considered themselves so intellectual and so completely in possession of the truth as these sufferers, never had they considered their decisions, their scientific conclusions, their moral convictions so infallible. Whole villages, whole towns and peoples went mad from the infection. All were excited and did not understand one another. Each thought that he alone had the truth and was wretched looking at the others, beat himself on the breast, wept, and wrung his hands. They did not know how to judge and could not agree what to consider evil and what good; they did not know whom to blame, whom to justify. Men killed each other in a sort of senseless spite. They gathered together in armies against one another, but even on the march the armies would begin attacking each other, the ranks would be broken and the soldiers would fall on each other, stabbing and cutting, biting and devouring each other. The alarm bell was ringing all day long in the towns; men rushed together, but why they were summoned and who was summoning them no one knew. The most ordinary trades were abandoned, because everyone proposed his own ideas, his own improvements, and they could not agree. The land too was abandoned. Men met in groups, agreed on something, swore to keep together, but at once began on something quite different from what they had proposed. They accused one another, fought and killed each other. There were conflagrations and famine. All men and all things were involved in destruction. The plague spread and moved further and further. Only a few men could be saved in the whole world. They were a pure chosen people, destined to found a new race and a new life, to renew and purify the earth, but no one had seen these men, no one had heard their words and their voices. ",                
        }; 
        try { 
            a.parse(lines); 
        } catch (DataParseException e) { 
            fail("unexpected exception"); 
        } 
         
        result = a.analyzeProfiles();         
        assertEquals("Dostoevsky", result.get(0));     
        assertNotEquals(0,++passed);         
    }     
     
    @Test 
    public void countPassed(){ 
        System.out.println(systemCall("curl -k    https://cs.gmu.edu/~kdobolyi/sparc/process.php?user=guest-project2_0-PROGRESS"));             
        assertEquals(20,passed); 
        if (passed == 20) 
            System.out.println(systemCall("curl -k    https://cs.gmu.edu/~kdobolyi/sparc/process.php?user=guest-project2_0-COMPLETED"));             
             
    } 
     
    public static StringBuffer systemCall(String cmd){ 
        StringBuffer result = new StringBuffer(); 
        try 
        { 
            System.err.println("doing "+ cmd); 
            Runtime run = Runtime.getRuntime(); 
            Process pr = run.exec(cmd); 
            pr.waitFor(); 
            BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getErrorStream())); 
            String line = null; 
             
            while ((line=buf.readLine())!=null)  
                result.append(line+"\n"); 
            buf.close(); 
            buf = new BufferedReader(new InputStreamReader(pr.getInputStream())); 
            while ((line=buf.readLine())!=null)  
                result.append(line+"\n");     
            buf.close(); 
            pr.getOutputStream().close(); 
            pr.destroy(); 
        } 
        catch (Exception e) 
        { 
            StackTraceElement[] elts = e.getStackTrace(); 
            for(int i=0; i < elts.length; i++) 
                result.append(elts[i].toString()+"\n"); 
        } 
        return result; 
    }         
     
    @Test 
    public void testStyle(){ 
        assertEquals("passed", checkPrivacy("Analyzer.java", "Profile[]", "profiles", "Analyzer: All appropriate attributes are private and commented.")); 
        assertEquals("passed", checkPrivacy("Analyzer.java", "int", "count", "Analyzer: All appropriate attributes are private and commented.")); 
        assertEquals("passed", checkPrivacy("Message.java", "String", "contact", "Message: All appropriate attributes are private and commented.")); 
        assertEquals("passed", checkPrivacy("Message.java", "String", "date", "Message: All appropriate attributes are private and commented.")); 
        assertEquals("passed", checkPrivacy("Message.java", "String", "body", "Message: All appropriate attributes are private and commented.")); 
        assertEquals("passed", checkPrivacy("Photo.java", "int", "size", "Photo: All appropriate attributes are private and commented.")); 
        assertEquals("passed", checkPrivacy("Photo.java", "String", "name", "Photo: All appropriate attributes are private and commented.")); 
        assertEquals("passed", checkPrivacy("Photo.java", "String", "location", "Photo: All appropriate attributes are private and commented.")); 
        assertEquals("passed", checkPrivacy("Post.java", "String", "text", "Post: All appropriate attributes are private and commented.")); 
        assertEquals("passed", checkPrivacy("Post.java", "String", "date", "Post: All appropriate attributes are private and commented.")); 
        assertEquals("passed", checkPrivacy("Post.java", "Photo", "photo", "Post: All appropriate attributes are private and commented.")); 
        assertEquals("passed", checkPrivacy("Profile.java", "Object[]", "photos", "Profile: All appropriate attributes are private and commented.")); 
        assertEquals("passed", checkPrivacy("Profile.java", "Object[]", "contacts", "Profile: All appropriate attributes are private and commented.")); 
        assertEquals("passed", checkPrivacy("Profile.java", "Object[]", "posts", "Profile: All appropriate attributes are private and commented.")); 
        assertEquals("passed", checkPrivacy("Profile.java", "Object[]", "messages", "Profile: All appropriate attributes are private and commented.")); 
    } 
     
    @Test 
    public void testReuse(){ 
        assertEquals("passed", checkReuse("Profile.java", "private","boolean", "minePhoto","minePost","Profile: The minePhoto method is called in minePost, rather than rewriting this code.", true));
        assertEquals("passed", checkReuse("Profile.java", "private","boolean", "mineContact","mineMessage","Profile: The mineContact method is called in mineMessage, rather than rewriting this code.", true));
        assertEquals("passed", checkUse("Profile.java", "equals","inefficientAndDangerousAddObject","Profile: The equals method is called in inefficientAndDangerousAddObject."));

        assertEquals("passed", checkReuse("Analyzer.java", "public", "void", "parseDataDump","parse","Analyzer: The parseDataDump method is called, rather than rewriting this code.", true));
        assertEquals("passed", checkReuse("Analyzer.java", "public", "void","mineContact","parse","Analyzer: The parseDataDump method is called, rather than rewriting this code.", false));
        assertEquals("passed", checkReuse("Analyzer.java", "public", "void","minePost","parse","Analyzer: The parseDataDump method is called, rather than rewriting this code.", false));
        assertEquals("passed", checkReuse("Analyzer.java", "public", "void","mineMessage","parse","Analyzer: The parseDataDump method is called, rather than rewriting this code.", false));
        assertEquals("passed", checkReuse("Analyzer.java", "public", "void","minePhoto","parse","Analyzer: The parseDataDump method is called, rather than rewriting this code.", false));    
    } 
     
    @Test 
    public void testCrash(){ 
        Photo p1 = new Photo("kitten.jpg", 234, "C:\\MyPhotos\\kitten.jpg"); 
        Photo p2 = new Photo(null, -1, null); 
         
        assertEquals(false, p1.equals(p2)); 
        assertEquals(false, p1.equals("kitten")); 
        assertEquals(false, p1.equals(null)); 

    } 
     
    @Test 
    public void testInheritance(){ 

        //assertEquals("passed", checkOverride("Message.java", "toString", "Message: The @Override tag is used in all the appropriate places.")); 
        assertEquals("passed", checkOverride("Photo.java", "equals", "Photo: The @Override tag is used in all the appropriate places.")); 
        //assertEquals("passed", checkOverride("Profile.java", "toString", "Profile: The @Override tag is used in all the appropriate places.")); 
     
    } 
     
    private String checkUse(String className, String method1, String method2, String errorMsg){ 
        ArrayList<String> body = pullMethodBody(className, "(\\s*)public(\\s+)void(\\s+)"+method2+"(\\s*)\\(.*\\)(\\s*)\\{(.*)"); 
        boolean found = false; 
        for (int i = 0; i < body.size() ; i++){ 
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+body.get(i)); 
            if (body.get(i).matches("(\\s*)"+method1+"\\((.*);")){ 
                return errorMsg; 
            } 
        } 
        return "passed";         
    }     
     
    private String checkReuse(String className, String visibility, String returnType, String method1, String method2, String errorMsg, boolean flag){ 
        ArrayList<String> body = pullMethodBody(className, "(\\s*)"+visibility+"(\\s+)"+returnType+"(\\s+)"+method2+"(\\s*)\\(.*\\)(.*)\\{(.*)"); 
        boolean found = false; 
        for (int i = 0; i < body.size() ; i++){ 
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+body.get(i)); 
            if (body.get(i).matches(".*"+method1+"\\(.*")){ 
                System.out.println("%%%%%%%%%%%%%%MATCHED%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+body.get(i)); 
                found = true; 
                if (flag) 
                    return "passed"; 
            } 
        } 
         
        if (!flag) 
            if (!found) 
                return "passed"; 
        return errorMsg;         
    } 
     
    private String checkSuperCtor(String className, String errorMsg){ 
        ArrayList<String> body = pullMethodBody(className+".java", "(\\s*)public(\\s*)"+className+"(\\s*)\\([a-zA-Z0-9 ,\\[\\]]*\\)(\\s*)\\{(.*)"); 
        for (int i = 0; i < body.size() ; i++){ 
            System.out.println(body.get(i)); 
            if (body.get(i).matches("(\\s*)super\\((.*);")) 
                return "passed"; 
        } 
        return errorMsg;         
    }     
     
    private String checkOverride(String filename, String method, String errorMsg){ 
        String line = null; 
        try{ 
            FileReader fReader = new FileReader( filename); 
            BufferedReader bReader = new BufferedReader(fReader); 

            while((line = bReader.readLine()) != null){ 
                line = line.trim(); 
                if(!line.isEmpty()){ 
                    if (line.contains("@Override")){ 
                        while((line = bReader.readLine()).trim().isEmpty()){ 
                            //spin until next line found 
                        } 
                        if (line.contains("public")) 
                            return "passed"; 
                    } 
                } 
            } 
            bReader.close(); 
        }catch (Exception e){  
            return "NOT MET: Couldn't open "+ filename; 
        } 
        return errorMsg;             
    } 
     
    private String checkPrivacy(String filename, String type, String attribute, String errorMsg){ 
        String line = null; 
        try{ 
            FileReader fReader = new FileReader(filename); 
            BufferedReader bReader = new BufferedReader(fReader); 

            while((line = bReader.readLine()) != null){ 
                //System.out.println("(line.contains (//) || line.contains(/*))" + (line.contains ("//") || line.contains("/*"))); 
                line = line.trim(); 
                if(!line.isEmpty()) 
                    if (line.contains("private") && line.contains(type) &&  
                            line.contains(attribute) && line.contains(";") && 
                            (line.contains ("//") || line.contains("/*"))) 
                            return "passed"; 
            } 
            bReader.close(); 
        }catch (Exception e){  
            return "NOT MET: Couldn't open "+ filename; 
        } 
        return errorMsg; 
    }     
     
    private String checkPropagate(String filename, String errorMsg){ 
        String line = null; 
        try{ 
            FileReader fReader = new FileReader(filename); 
            BufferedReader bReader = new BufferedReader(fReader); 

            while((line = bReader.readLine()) != null){ 
                line = line.trim(); 
                if(!line.isEmpty()){ 
                    if (line.contains("instanceof") || line.contains(".getClass().equals") || line.contains(".getClass() =")) 
                        return errorMsg; 
                } 
            } 
            bReader.close(); 
        }catch (Exception e){  
            return "NOT MET: Couldn't open WhiteBloodCell.java"; 
        } 
        return "passed";             
    } 
     
    private ArrayList<String> pullMethodBody(String filename, String methodRegex){ 
        ArrayList<String> body = new ArrayList<String>(); 
        String line = null; 
        int brackets = 0; 
        boolean found = false; 
        int bodyBrackets = 0; 
        try{ 
            FileReader fReader = new FileReader(filename); 
            BufferedReader bReader = new BufferedReader(fReader); 

            while((line = bReader.readLine()) != null){ 
                System.out.println(line); 
                line = line.trim(); 
                if(!line.isEmpty()){ 
                    System.out.println("SEARCHING METHOD*************"); 
                    if (line.matches(methodRegex) && line.contains("{")){ 
                        System.out.println("FOUND METHOD*************"); 
                        bodyBrackets = brackets;                         
                        brackets++; 
                        found = true; 
                    } 
                    else if (line.contains("{")) 
                        brackets++; 
                    if (line.contains("}")) 
                        brackets--;         
                     
                    if (found){ 
                        body.add(line); 
                        //System.out.println("BRACKETS************* " + brackets); 
                        //System.out.println("BODYBRACKETS************* " + bodyBrackets); 
                    }                     
                     
                    if (found && brackets == bodyBrackets ) 
                        return body; 
                } 
            } 
            bReader.close(); 
        }catch (Exception e){  
            return null; 
        } 
        return null;             
    }         
     
} 
