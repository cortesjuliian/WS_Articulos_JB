/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.wsrestapparticulos.general;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


/**
 *
 * @author julia
 */
public class GenericRepositoryJPA<T> implements GenericRepository<T> {

    protected EntityManager entityManager;
    private Class<T> type;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public GenericRepositoryJPA() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    /**
     *
     * @param t
     * @return
     */
    @Override
    public T create(T t) {
        connectionDBJPA connDBJPA = new connectionDBJPA();
        try {
            this.entityManager = connDBJPA.getEntityManager();
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(t);
//            this.entityManager.flush();
//            this.entityManager.refresh(t);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            connDBJPA.closeEntityManager();
            if (this.entityManager != null) {
                if (this.entityManager.isOpen()) {
                    this.entityManager.close();
                }
            }
        }
        return t;
    }

    @Override
    public T update(T t) {
        connectionDBJPA connDBJPA = new connectionDBJPA();
        try {
            this.entityManager = connDBJPA.getEntityManager();
            this.entityManager.getTransaction().begin();
            this.entityManager.merge(t);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (this.entityManager.getTransaction().isActive()) {
                this.entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            connDBJPA.closeEntityManager();
            if (this.entityManager != null) {
                if (this.entityManager.isOpen()) {
                    this.entityManager.close();
                }
            }
        }
        return t;
    }

    @Override
    public void delete(final T t) {
        connectionDBJPA connDBJPA = new connectionDBJPA();
        try {
            this.entityManager = connDBJPA.getEntityManager();
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(entityManager.merge(t));
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (this.entityManager.getTransaction().isActive()) {
                this.entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            connDBJPA.closeEntityManager();
            if (this.entityManager != null) {
                if (this.entityManager.isOpen()) {
                    this.entityManager.close();
                }
            }
        }

    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public T find(final Object id) {
        T findClass = null;
        connectionDBJPA connDBJPA = new connectionDBJPA();
        try {
            this.entityManager = connDBJPA.getEntityManager();
            findClass = (T) this.entityManager.find(type, id);
        } catch (Exception e) {
            System.out.println("com.udec.wsrestapparticulos=>" + e.getCause());
            e.printStackTrace();
        } finally {
            connDBJPA.closeEntityManager();
            if (this.entityManager != null) {
                if (this.entityManager.isOpen()) {
                    this.entityManager.close();
                }
            }
        }
        return findClass;
    }

    @Override
    public List<T> findAll() {
        List<T> lstFindAll = new ArrayList<>();
        connectionDBJPA connDBJPA = new connectionDBJPA();
        try {
            this.entityManager = connDBJPA.getEntityManager();
            CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = cb.createQuery(type);
            Root<T> root = criteriaQuery.from(type);
            criteriaQuery.select(root);
            criteriaQuery.orderBy(cb.asc(root.get("id")));
            TypedQuery<T> query = this.entityManager.createQuery(criteriaQuery);
            lstFindAll = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            connDBJPA.closeEntityManager();
            if (this.entityManager != null) {
                if (this.entityManager.isOpen()) {
                    this.entityManager.close();
                }
            }
        }

        return lstFindAll;
    }

    @Override
    public List<T> findAllOrderByCampo(String sTipoOrder, String sCampoOrder) {

        List<T> listFindAllOrderByCampo = new ArrayList<>();
        connectionDBJPA connDBJPA = new connectionDBJPA();
        try {
            this.entityManager = connDBJPA.getEntityManager();
            CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = cb.createQuery(type);
            Root<T> root = criteriaQuery.from(type);
            criteriaQuery.select(root);
            switch (sTipoOrder.toUpperCase()) {
                case "ASC":
                    criteriaQuery.orderBy(cb.asc(root.get(sCampoOrder)));
                    break;
                case "DESC":
                    criteriaQuery.orderBy(cb.desc(root.get(sCampoOrder)));
                    break;
                default:
                    criteriaQuery.orderBy(cb.desc(root.get(sCampoOrder)));
                    break;
            }

            TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
            listFindAllOrderByCampo = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            connDBJPA.closeEntityManager();
            if (this.entityManager != null) {
                if (this.entityManager.isOpen()) {
                    this.entityManager.close();
                }
            }
        }

        return listFindAllOrderByCampo;
    }

    @Override
    public List<T> findByNamedQueryForValueString(T t, String sNameQuery, String sCampoFind, String sValueFind) {
        List<T> list = new ArrayList<>();
        connectionDBJPA connDBJPA = new connectionDBJPA();
        try {
            this.entityManager = connDBJPA.getEntityManager();
            TypedQuery<T> query = this.entityManager.createNamedQuery(sNameQuery, type);
            query.setParameter(sCampoFind, sValueFind);
            list = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            connDBJPA.closeEntityManager();
            if (this.entityManager != null) {
                if (this.entityManager.isOpen()) {
                    this.entityManager.close();
                }
            }
        }
        return list;
    }

    @Override
    public List<T> findByNamedQueryForValueEntity(T t, String sNameQuery, String sCampoFind, T valueEntity) {
        List<T> list = new ArrayList<>();
        connectionDBJPA connDBJPA = new connectionDBJPA();
        try {
            this.entityManager = connDBJPA.getEntityManager();
            TypedQuery<T> query = this.entityManager.createNamedQuery(sNameQuery, type);
            query.setParameter(sCampoFind, valueEntity);
            list = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            connDBJPA.closeEntityManager();
            if (this.entityManager != null) {
                if (this.entityManager.isOpen()) {
                    this.entityManager.close();
                }
            }
        }
        return list;
    }

}
