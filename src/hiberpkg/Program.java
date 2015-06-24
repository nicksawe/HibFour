package hiberpkg;

import org.hibernate.Session;

public class Program {
    public static void main(String[] args) {
        Session session = new HibernateUtilGenerated().getSession();
        session.getSessionFactory();

        /* save user to db*/
        session.beginTransaction();

        User nick = new User();
        nick.setName("24jun");
        nick.setSex("M");

        session.save(nick);
        session.getTransaction().commit();
        /*end of save user*/


        //now retrieve a user
        session.beginTransaction();
        //1 is the id. You can use load instead of get in the next line. Get will return null if id
        //doesn t exist, load will return an exception - but i got an exception in both?
        User loadedUser = (User) session.get(User.class,1);
        System.out.println(loadedUser.getName());
        System.out.println(loadedUser.getSex());

        //manipulate the object u loaded
        loadedUser.setSex("H"); //user id one now has sex as hermaphrodite!

        session.getTransaction().commit();






        session.close();
        HibernateUtilGenerated.getSession().getSessionFactory().close();
    }
}
