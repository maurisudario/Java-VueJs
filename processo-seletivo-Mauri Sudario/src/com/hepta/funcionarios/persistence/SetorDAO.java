package com.hepta.funcionarios.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.hepta.funcionarios.entity.Setor;

public class SetorDAO {

    public void save (Setor Setor) throws Exception {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(Setor);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception(e);
        } finally {
            em.close();
        }
    }

    public void delete (Integer id) throws Exception {
        EntityManager em = HibernateUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            Setor Setor = em.find(Setor.class, id);
            em.remove(Setor);
            em.getTransaction().commit();
        }catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception(e);
        }finally {
            em.close();
        }
    }

    public Setor update(Setor Setor) throws Exception {
        EntityManager em = HibernateUtil.getEntityManager();
        Setor SetorAtualizado = null;
        try{
            em.getTransaction().begin();
            SetorAtualizado = em.merge(Setor);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            throw new Exception(e);
        }finally {
            em.close();
        }
        return SetorAtualizado;
    }

    public Setor find(Integer id) throws  Exception {
        EntityManager em = HibernateUtil.getEntityManager();
        Setor Setor = null;
        try{
            Setor = em.find(Setor.class, id);
        }catch (Exception e){
            em.getTransaction().rollback();
            throw new Exception(e);
        }finally {
            em.close();
        }
        return Setor;
    }

    //Retorna todos os setores cadastrados no BD
    @SuppressWarnings("unchecked")
    public List<Setor> getAllSetores() throws Exception {
        EntityManager em = HibernateUtil.getEntityManager();
        List<Setor> setores = new ArrayList<>();

        try{
            Query q = em.createQuery("FROM Setor");
            setores = q.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception(e);
        } finally {
            em.close();
        }
        return setores;
    }

}
