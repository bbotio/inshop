import com.inshop.HibernateConfig;
import com.inshop.dao.GenericDao;
import com.inshop.entity.Shop;
import com.inshop.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by savetisyan on 06/09/15.
 */
public class MainTest {
    GenericDao dao;

    @Before
    public void setup() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(HibernateConfig.class);
        dao = ctx.getBean(GenericDao.class);
    }

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
        System.out.println(read);
    }
}
