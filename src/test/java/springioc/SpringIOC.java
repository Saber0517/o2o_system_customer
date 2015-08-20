package springioc;


import com.oocl.jyhon.dao.UserEntityDao;
import com.oocl.jyhon.daoimple.UserEntityDaoImple;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by WhiteSaber on 15/8/20.
 */
public class SpringIOC {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("mvc-dispatcher-servlet.xml");
        UserEntityDaoImple userEntityDao = (UserEntityDaoImple) context.getBean("userEntityDaoImple");
        System.out.printf(String.valueOf(userEntityDao));
    }
}
