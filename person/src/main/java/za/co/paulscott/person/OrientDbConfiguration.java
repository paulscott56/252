package za.co.paulscott.person;

/**
 * Created by pscot on 3/7/2016.
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.data.orient.commons.repository.config.EnableOrientRepositories;
import org.springframework.data.orient.object.repository.support.OrientObjectRepositoryFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by pscot on 3/2/2016.
 */

@Configuration
@EnableOrientRepositories(basePackages = "com.dstvdm.person.repository", repositoryFactoryBeanClass = OrientObjectRepositoryFactoryBean.class)
@EnableTransactionManagement
public class OrientDbConfiguration {

   /* @Bean
    public OrientGraphFactory factory() {
        OrientGraphFactory factory = new OrientGraphFactory("plocal:/opt/orientdatabases/person", "admin", "admin").setupPool(1,10);
        return factory;
    }*/


   /* @Bean
    public OrientGraph graphTemplate() {
        String url = "plocal:/opt/orientdatabases/person";
        try {
            OServerAdmin serverAdmin = new OServerAdmin(url).connect("admin", "admin");
            if(serverAdmin.existsDatabase()) {
                OrientGraphFactory factory = new OrientGraphFactory(url);
                return factory.getTx();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("DB does not exist");
        }
        return null;
    }*/

}
