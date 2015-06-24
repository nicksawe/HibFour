package hiberpkg;

import org.hibernate.Session;

public class Program {
    public static void main(String[] args) {
        Session session = new HibernateUtilGenerated().getSession();
        session.getSessionFactory();
        session.beginTransaction();

        UserEntity nick = new UserEntity();
        nick.setName("24jun");
        nick.setSex("M");

        session.save(nick);
        session.getTransaction().commit();

        session.close();
        HibernateUtilGenerated.getSession().getSessionFactory().close();
    }
}
