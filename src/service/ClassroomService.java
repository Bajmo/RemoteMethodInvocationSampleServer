/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.IDao;
import entities.Classroom;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class ClassroomService extends UnicastRemoteObject implements IDao<Classroom> {

    public ClassroomService() throws RemoteException {
        super();
    }

    @Override
    public boolean create(Classroom o) throws RemoteException {
        Session session = null;
        Transaction tx = null;
        boolean state = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            state = true;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return state;
    }

    @Override
    public boolean update(Classroom o) throws RemoteException {
        Session session = null;
        Transaction tx = null;
        boolean state = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            state = true;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return state;
    }

    @Override
    public boolean delete(Classroom o) throws RemoteException {
        Session session = null;
        Transaction tx = null;
        boolean state = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            state = true;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return state;
    }

    @Override
    public Classroom findById(int id) throws RemoteException {
        Session session = null;
        Transaction tx = null;
        Classroom classroom = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            classroom = (Classroom) session.get(Classroom.class, id);
            tx.commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return classroom;
    }

    @Override
    public List<Classroom> findAll() throws RemoteException {
        Session session = null;
        Transaction tx = null;
        List<Classroom> classrooms = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            classrooms = session.getNamedQuery("findAllClassroomsNative").list();
            tx.commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return classrooms;
    }
}
