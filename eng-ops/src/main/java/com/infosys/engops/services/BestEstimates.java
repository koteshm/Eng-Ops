package com.infosys.engops.services;

import com.infosys.engops.utils.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Saurabh_Nayar on 5/25/2016.
 */
@Path("/bestestimates")
public class BestEstimates {
    @GET
    @Path("/getAllEstimates")
    @Produces(MediaType.APPLICATION_JSON)
    public List<com.infosys.engops.entities.BestEstimates> getAllEstimates() {
        EntityManagerFactory emf = SpringContextUtil.getInstance().getContext().getBean("entityManagerFactory",
                EntityManagerFactory.class);

        return emf.createEntityManager().createQuery("select o from BestEstimates o").getResultList();
    }


    @POST
    @Path("/updateEstimate")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateEstimates(com.infosys.engops.entities.BestEstimates estimate) {
        EntityManagerFactory emf = SpringContextUtil.getInstance().getContext().getBean("entityManagerFactory",
                EntityManagerFactory.class);
        EntityManager em = emf.createEntityManager();
        System.out.println(estimate);

        List<com.infosys.engops.entities.BestEstimates> list = emf.createEntityManager().createQuery("select o from BestEstimates o where o.quarter= '" + estimate.getQuarter()
                + "' and o.manager = '" + estimate.getManager() + "'").getResultList();


        System.out.println(list);
        EntityTransaction t = null;
        t = em.getTransaction();
        System.out.println("Before save");
        t.begin();
        if(list.size() == 0) {
            em.persist(estimate);
        } else {
            em.merge(estimate);
        }
        System.out.println("After save");
        t.commit();
    }
}
