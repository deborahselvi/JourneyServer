package com.journey.map.repository.service.provider;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import com.journey.map.repository.model.UserData;
import com.journey.map.repository.service.UserDataRepositoryService;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class UserDataRepositoryServiceProvider implements UserDataRepositoryService{
   private final SessionFactory factory;

   public UserDataRepositoryServiceProvider(SessionFactory factory) {
      this.factory = factory;
   }
  
  /* Method to CREATE an user in the database */
  @Override
  public Long addUserData(Long roomId, String userName) {
     Session session = factory.openSession();
     Transaction tx = null;
     
     try {
        tx = session.beginTransaction();
        UserData userData = new UserData();
        if (roomId == null) {
         Query query = session.createSQLQuery("SELECT nextval('room_id_seq')");
         roomId = ((BigInteger) query.uniqueResult()).longValue();
        }
        userData.setRoomId(roomId);
        userData.setUsername(userName);
        session.save(userData); 
        tx.commit();
     } catch (HibernateException e) {
        if (tx!=null) tx.rollback();
        e.printStackTrace(); 
     } finally {
        session.close(); 
     }
     return roomId;
  }
  
  /* Method to  READ all the UserDatas */
  @Override
  public void listUserDatas(){
     Session session = factory.openSession();
     Transaction tx = null;
     
     try {
        tx = session.beginTransaction();
        List userDatas = session.createQuery("FROM UserData").list(); 
        for (Iterator iterator = userDatas.iterator(); iterator.hasNext();){
           UserData userData = (UserData) iterator.next(); 
           log.info("Id: {}", userData.getId()); 
           log.info("RoomId: {}", userData.getRoomId()); 
           log.info("Username: {}", userData.getUsername()); 
        }
        tx.commit();
     } catch (HibernateException e) {
        if (tx!=null) tx.rollback();
        e.printStackTrace(); 
     } finally {
        session.close(); 
     }  
  }
  
   /* Method to UPDATE salary for an userData */
   @Override
   public void updateUserData(Integer userDataID, String userName) {
      Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         UserData userData = session.get(UserData.class, userDataID); 
         userData.setUsername(userName);
		 session.update(userData); 
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
   }
   
   /* Method to DELETE an userData from the records */
   @Override
   public void deleteUserData(Integer userDataID) {
      Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         UserData userData = session.get(UserData.class, userDataID); 
         session.delete(userData); 
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
   }
}
