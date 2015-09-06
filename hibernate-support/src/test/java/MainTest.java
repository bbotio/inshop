import com.inshop.HibernateConfig;
import com.inshop.dao.GenericDao;
import com.inshop.entity.Shop;
import com.inshop.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by savetisyan on 06/09/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfig.class)
@ActiveProfiles("test")
public class MainTest {
    @Autowired
    GenericDao dao;

    @Test
    public void testName() throws Exception {
        User user = new User();
        user.setEmail("email");
        user.setInstagramToken("token");

        dao.save(user);

        Shop shop = new Shop();
        shop.setDomain("domain");
        shop.setDescription("description");
        shop.setTitle("title");

        dao.save(shop);
        user.setShop(shop);
        dao.save(user);

        User read = dao.get(User.class, user.getId());
        System.err.println(read);
    }
}
