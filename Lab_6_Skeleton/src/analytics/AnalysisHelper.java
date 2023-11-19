/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analytics;

/**
 *
 * @author harshalneelkamal
 */

import data.DataStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import model.Comment;
import model.Post;
import model.User;


public class AnalysisHelper {
    //Find Average number of likes per comment.
    //TODO
    public void getAverageLikesPerComments() {
        Map<Integer, Comment> comments = DataStore.getInstance().getComments();
        int likeNumber = 0;
        int commentNumber = comments.size();
        for (Comment c : comments.values()) {
            likeNumber += c.getLikes();
        }
        
        System.out.println("Average number of likes per comments: " + likeNumber / commentNumber);
            
    }
    public void getMaxLikeCommentPost(){
        DataStore data= DataStore.getInstance();
        Comment commentWithMaxLikes= null;
        for(Comment c:data.getComments().values()){
            if(commentWithMaxLikes==null){
                commentWithMaxLikes=c;
            }
            if(c.getLikes()>commentWithMaxLikes.getLikes()){
                commentWithMaxLikes=c;
            }
        }
        int postID=commentWithMaxLikes.getPostId();
        System.out.print("Q2-post with most likes per comment:"+data.getPosts().get(postID).getPostId()+"\n");
        
    }
    public void getPostWithMostComments(){
        DataStore data=DataStore.getInstance();
        Post postWithMostComments=null;
        for(Post p:data.getPosts().values()){
            if(postWithMostComments==null){
                postWithMostComments=p;
                
            }
        }
        System.out.print("Q3-post with most comments:"+postWithMostComments.getPostId()+"\n");
    }
    public void getPassiveUsers(){
        DataStore data= DataStore.getInstance();
        HashMap<Integer, Integer>postNumbers= new HashMap<Integer, Integer>();
        for(Post p:data.getPosts().values()){
            int userId=p.getUserId();
            if(postNumbers.containsKey(userId)){
                postNumbers.put(userId, postNumbers.get(userId)+1);
            }else{
                postNumbers.put(userId, 1);
            }
        }
        ArrayList<User>users= new ArrayList(data.getUsers().values());
        Collections.sort(users,new UserMapComparator(postNumbers));
        System.out.print("Q4- following users have the least posts:"+"\n");
        for(int i=0;i<5;i++){
            System.out.println(users.get(i)+",-Post Count :"+postNumbers.get(users.get(i).getId()));
            
        }
    }
    public void getPassiveCommentUsers(){
        DataStore data= DataStore.getInstance();
        HashMap<Integer, Integer>commentNumbers=new HashMap<Integer, Integer>();
        for(Comment c:data.getComments().values()){
            
            int userId= c.getUserId();
            if(commentNumbers.containsKey(userId)){
                commentNumbers.put(userId, commentNumbers.get(userId)+1);
            }else{
                commentNumbers.put(userId, 1);
            }
        }
        ArrayList<User>users= new ArrayList(data.getUsers().values());
        Collections.sort(users,new UserMapComparator(commentNumbers));
        System.out.print("Q5- following users have the least comments:");
        for(int i=0;i<5;i++){
            System.out.println(users.get(i)+",- Comment count:"+commentNumbers.get(users.get(i).getId()));
            
        }
    }
    public void getPassiveAndActiveOverallUsers(){
        DataStore data= DataStore.getInstance();
        HashMap<Integer, Integer>overallNumbers= new HashMap<Integer, Integer>();
        for(Comment c:data.getComments().values()){
            int userId=c.getUserId();
            if(overallNumbers.containsKey(userId)){
                overallNumbers.put(userId,overallNumbers.get(userId)+1+c.getLikes());
                
                
            }}
        for(Post p:data.getPosts().values()){
            int userId=p.getUserId();
            if(overallNumbers.containsKey(userId)){
                overallNumbers.put(userId, overallNumbers.get(userId)+1);
            }else{
                overallNumbers.put(userId, 1);
            }
        }
        ArrayList<User>users= new ArrayList(data.getUsers().values());
        Collections.sort(users,new UserMapComparator(overallNumbers));
        System.out.print("Q6- following users have overall been passive:"+"\n");
        for(int i=0;i<5;i++){
            System.out.print(users.get(i)+"-overall count :"+overallNumbers.get(users.get(i).getId())+"\n");
        }
        
           System.out.print("Q7- following users have overall been active"+"\n");
        for(int i=users.size()-1;i>users.size()-6;i--){
            System.out.print(users.get(i)+"-overall count :"+overallNumbers.get(users.get(i).getId())+"\n");
        }
    }
    
}
