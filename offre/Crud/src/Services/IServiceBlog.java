/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Publication;
import java.util.ArrayList;

/**
 *
 * @author Mahmoud
 */
public interface IServiceBlog<T> {
    
 public int add(T entity);
 public ArrayList<T> getAll();
 public ArrayList<T> getUserPosts(int user_id);
 public ArrayList<T> getTopFive();
 public ArrayList<T> getFourLast();
 public ArrayList<T> getSearchedPost(String s);
 public ArrayList<T> getPostByDay();
 public ArrayList<T> getPostByMonth();
 public ArrayList<T> getPostByYear();
 public ArrayList<T> getNumberPosterPerMonth();
 public ArrayList<T> getNumberPostsPerEntreprise();
 public int getAllPostNumber();
 public int getAllPostersNumber();
 public int getIdFromTitle(String s);
 public int Edit(T entity);
 public int Delete(int id);
 public Publication getPostById(int id);
 public String getUsernameById(int id);
public String getUserEmailById(int post_id); 
 public String getPosterName(int post_id);
 public int getIdPoster(int post_id);
 public void updateviews(int post_id);
}
