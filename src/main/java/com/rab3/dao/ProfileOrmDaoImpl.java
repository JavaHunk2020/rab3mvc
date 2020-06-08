package com.rab3.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rab3.controller.dto.ProfileDTO;
import com.rab3.dao.entity.ProfileEntity;


@Repository
//below is mandatory 
@Transactional
public class ProfileOrmDaoImpl  implements ProfileDao{
	
	   @Autowired
	    private SessionFactory sessionFactory;
	    
	    private Session getSession(){
	             return sessionFactory.getCurrentSession();
	    }
	    
	    @Override
		public ProfileEntity auth(String username,String password) {
			TypedQuery<ProfileEntity> query=getSession().createQuery("from ProfileEntity pt where pt.username=:pusername and pt.password=:ppassword"); //HQL
			query.setParameter("pusername", username);
			query.setParameter("ppassword", password);
			 ProfileEntity  profileEntity=null;
			 try {
				 profileEntity=query.getSingleResult();
			 }catch (Exception e) {
				 //e.printStackTrace();
			}
			return profileEntity;
	}


	@Override
	public String forgetPassword(String email) {
		TypedQuery<ProfileEntity> query=getSession().createQuery("from ProfileEntity pt where pt.email=:pemail"); //HQL
		 query.setParameter("pemail", email);
		 ProfileEntity  profileEntity=null;
		 try {
			 profileEntity=query.getSingleResult();
		 }catch (Exception e) {
			 //e.printStackTrace();
		}
		return profileEntity!=null ? profileEntity.getPassword() : null;
	}

	@Override
	public String saveProfile(ProfileEntity profileEntity) {
		//Session session=this.getSession();
		this.getSession().save(profileEntity);
		return "success";
	}

	@Override
	public ProfileEntity findById(int aid) {
		return this.getSession().get(ProfileEntity.class, aid);
	}

	@Override
	public String update(ProfileEntity profileEntity) {
		this.getSession().merge(profileEntity);
		return "done";
	}

	@Override
	public List<ProfileEntity> findAll() {
		Query query=this.getSession().createQuery("from ProfileEntity");
		return query.list();
	}

	@Override
	public byte[] findPhotoById(int aid) {
		return this.getSession().get(ProfileEntity.class, aid).getHphoto();
	}

}
