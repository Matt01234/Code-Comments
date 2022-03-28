/**
*   Simple User class with code comments.
*   The program contains a User class which
*   stores data about a particular user.
*/

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User{
    
    public static void main(String[] args){
        // first user
        User userOne = new User("john123", "John", "john@email.com", "password123");
        System.out.println("User One: " + userOne);
        // second user
        User userTwo = new User("bob123", "Bob", "bob@email.com", "password456");
        System.out.println("User Two: " + userTwo);
        // third user
        User userThree = new User("sally123", "Sally", "sally@email.com", "password789");
        System.out.println("User Three: " + userThree);
        // the amount of users instantiated
       System.out.println("Number of users: " + User.getTotalUsers());
    }
    
    private static int totalUsers; // static field stores the total amount of users
    /**
    *   total static method @return totalUsers
    */
    private static int getTotalUsers(){
        return User.totalUsers;
    }
    
    private String username;    // field stores the username of a user
    private String name;        // field stores the name of a user
    private String email;       // field stores a verified email
    private String password;    // field stores the encrypted password of a user
    
    /**
    *   constructor many args 
    */
    public User(String username, String name, String email, String password){
        this.setUsername(username);
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        totalUsers++;
    }
    /**
    *   setUsername method sets the field username
    *   to an all lowercase version of 
    *   @param username
    */   
    public void setUsername(String username){
        username.toLowerCase();
        this.username = username;    
    }
    /**
    *   setName method sets the field name to 
    *   @param name
    */
    public void setName(String name){
        this.name = name;    
    }
    /**
    *   setEmail method sets the field email to 
    *   @param email only if the email is 
    *   in correct form or @throw an 
    *   IllegalArgumentException
    */
    public void setEmail(String email){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher((CharSequence) email);
        if(!matcher.matches()){
            throw new IllegalArgumentException("Email format not valid!");
        }
        this.email = email;    
    }
    /**
    *   setPassword method sets the field password to 
    *   @param password after the password has been
    *   encrypted if the password is greater than
    *   or equal to 6 characters or @throw an
    *   IllegalArgumentException
    */
    public void setPassword(String password){
        if(password.length() < 6){
            throw new IllegalArgumentException("Password must be atleast 6 characters!");
        }
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] bytePass = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for(byte b : bytePass){
                sb.append(String.format("%02x", b));
            }
            this.password = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }  
    }
    /**
    *   getUsername method @return username field
    */
    public String getUsername(){
        return this.username;    
    }
    /**
    *   getName method @return name field
    */
    public String getName(){
        return this.name;    
    }
    /**
    *   getEmail method @return email field
    */
    public String getEmail(){
        return this.email;    
    }
    /**
    *   getPassword method @return password field
    */
    public String getPassword(){
        return this.password;    
    }
    /**
    *   toString method @override to display the fields
    *   of an instance
    */
    public String toString(){
        return (this.username + " " + this.name + " " + this.email + " " + this.password);
    }
}
