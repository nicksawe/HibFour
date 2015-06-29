package hiberpkg;

import org.hibernate.Session;

import java.util.Date;

public class Program {
    public static void main(String[] args) {
        Session session = new HibernateUtilGenerated().getSession();
        session.getSessionFactory();

        /* save user to db*/
        session.beginTransaction();

        User nick = new User();
        nick.setName("Mpya dude");
        nick.getHistory().add(new UserHistory(new Date(), "Set name to mpya dude"));
        nick.setSex("M");
        nick.getProteinData().setGoal(50);
        nick.getProteinData().setTotal(140);
        nick.getHistory().add(new UserHistory(new Date(), "Set goal to 140"));

        session.save(nick);
        session.getTransaction().commit();
        /*end of save user*/


            /*now retrieve a user*/
            session.beginTransaction();
            //1 is the id. You can use load instead of get in the next line. Get will return null if id
            //doesn t exist, load will return an exception - but i got an exception in both?
            User loadedUser = (User) session.get(User.class, 1);
            System.out.println(loadedUser.getName());
            System.out.println(loadedUser.getSex());

        for (UserHistory history : loadedUser.getHistory())
        {
            System.out.println(history.getEntryTime().toString()+ " "+ history.getEntry());
        }
            //manipulate the object u loaded
            loadedUser.setSex("X"); //user id one now has sex as hermaphrodite!

        if(loadedUser.getProteinData()!=null) {
            loadedUser.getProteinData().setTotal(loadedUser.getProteinData().getTotal() + 50);
        //}else{
         //   loadedUser.setProteinData(new ProteinData().setTotal(120));
        }
            session.getTransaction().commit();

            session.close();
        HibernateUtilGenerated.getSession().getSessionFactory().close();
    }
}
