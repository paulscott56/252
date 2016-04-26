package teststormpath;

/**
 * Created by paul on 2016/04/20.
 */
import com.stormpath.sdk.account.Account;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * @since 1.0.RC5
 */
@Service
public class HelloService {

    //Let's just specify some role here so we can grant it access to restricted resources
    public static final String MY_GROUP = "GROUP_HREF_HERE";

    /**
     * Only users who have a Custom Data entry in their Stormpath Account or Group containing something like
     * <code>"springSecurityPermissions":["say:*"]</code> or <code>"springSecurityPermissions":["say:hello"]</code>
     * will be allowed to execute this method.
     */
    @PreAuthorize("hasRole('" + MY_GROUP + "') and hasPermission('say', 'hello')")
    public String sayHello(Account account) {
        return "Hello, " + account.getGivenName() +
                ". You have the required persmissions to access this restricted resource.";
    }


}