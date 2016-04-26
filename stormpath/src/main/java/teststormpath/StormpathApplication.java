package teststormpath;

import com.stormpath.sdk.account.*;
import com.stormpath.sdk.api.ApiAuthenticationResult;
import com.stormpath.sdk.api.ApiKey;
import com.stormpath.sdk.api.ApiKeyOptions;
import com.stormpath.sdk.application.*;
import com.stormpath.sdk.authc.AuthenticationRequest;
import com.stormpath.sdk.authc.AuthenticationResult;
import com.stormpath.sdk.directory.AccountStore;
import com.stormpath.sdk.directory.CustomData;
import com.stormpath.sdk.directory.DirectoryCriteria;
import com.stormpath.sdk.group.CreateGroupRequest;
import com.stormpath.sdk.group.Group;
import com.stormpath.sdk.group.GroupCriteria;
import com.stormpath.sdk.group.GroupList;
import com.stormpath.sdk.idsite.IdSiteCallbackHandler;
import com.stormpath.sdk.idsite.IdSiteUrlBuilder;
import com.stormpath.sdk.oauth.OauthRequestAuthenticator;
import com.stormpath.sdk.provider.ProviderAccountRequest;
import com.stormpath.sdk.provider.ProviderAccountResult;
import com.stormpath.sdk.resource.ResourceException;
import com.stormpath.sdk.tenant.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;
import java.util.Map;

@SpringBootApplication
@ComponentScan
public class StormpathApplication implements Application {

	public static void main(String[] args) {
		SpringApplication.run(StormpathApplication.class, args);
	}


    public String getName() {
        return null;
    }

    public Application setName(String s) {
        return null;
    }

    public String getDescription() {
        return null;
    }

    public Application setDescription(String s) {
        return null;
    }

    public ApplicationStatus getStatus() {
        return null;
    }

    public Application setStatus(ApplicationStatus applicationStatus) {
        return null;
    }

    public AccountList getAccounts() {
        return null;
    }

    public AccountList getAccounts(Map<String, Object> map) {
        return null;
    }

    public AccountList getAccounts(AccountCriteria accountCriteria) {
        return null;
    }

    public Account createAccount(Account account) throws ResourceException {
        return null;
    }

    public Account createAccount(CreateAccountRequest createAccountRequest) throws ResourceException {
        return null;
    }

    public GroupList getGroups() {
        return null;
    }

    public GroupList getGroups(Map<String, Object> map) {
        return null;
    }

    public GroupList getGroups(GroupCriteria groupCriteria) {
        return null;
    }

    public Group createGroup(Group group) throws ResourceException {
        return null;
    }

    public Group createGroup(CreateGroupRequest createGroupRequest) {
        return null;
    }

    public Tenant getTenant() {
        return null;
    }

    public PasswordResetToken sendPasswordResetEmail(String s) throws ResourceException {
        return null;
    }

    public PasswordResetToken sendPasswordResetEmail(String s, AccountStore accountStore) throws ResourceException {
        return null;
    }

    public Account verifyPasswordResetToken(String s) {
        return null;
    }

    public Account resetPassword(String s, String s1) {
        return null;
    }

    public AuthenticationResult authenticateAccount(AuthenticationRequest authenticationRequest) throws ResourceException {
        return null;
    }

    public ProviderAccountResult getAccount(ProviderAccountRequest providerAccountRequest) {
        return null;
    }

    public AccountStoreMappingList getAccountStoreMappings() {
        return null;
    }

    public AccountStoreMappingList getAccountStoreMappings(Map<String, Object> map) {
        return null;
    }

    public AccountStoreMappingList getAccountStoreMappings(AccountStoreMappingCriteria accountStoreMappingCriteria) {
        return null;
    }

    public AccountStore getDefaultAccountStore() {
        return null;
    }

    public void setDefaultAccountStore(AccountStore accountStore) {

    }

    public AccountStore getDefaultGroupStore() {
        return null;
    }

    public void setDefaultGroupStore(AccountStore accountStore) {

    }

    public AccountStoreMapping createAccountStoreMapping(AccountStoreMapping accountStoreMapping) throws ResourceException {
        return null;
    }

    public AccountStoreMapping addAccountStore(AccountStore accountStore) throws ResourceException {
        return null;
    }

    public ApiKey getApiKey(String s) throws ResourceException, IllegalArgumentException {
        return null;
    }

    public ApiKey getApiKey(String s, ApiKeyOptions apiKeyOptions) throws ResourceException, IllegalArgumentException {
        return null;
    }

    public ApiAuthenticationResult authenticateApiRequest(Object o) throws IllegalArgumentException, ResourceException {
        return null;
    }

    public OauthRequestAuthenticator authenticateOauthRequest(Object o) throws IllegalArgumentException {
        return null;
    }

    public IdSiteUrlBuilder newIdSiteUrlBuilder() {
        return null;
    }

    public IdSiteCallbackHandler newIdSiteCallbackHandler(Object o) {
        return null;
    }

    public void sendVerificationEmail(VerificationEmailRequest verificationEmailRequest) {

    }

    public AccountStoreMapping addAccountStore(String s) {
        return null;
    }

    public AccountStoreMapping addAccountStore(DirectoryCriteria directoryCriteria) {
        return null;
    }

    public AccountStoreMapping addAccountStore(GroupCriteria groupCriteria) {
        return null;
    }

    public Application saveWithResponseOptions(ApplicationOptions applicationOptions) {
        return null;
    }

    public Date getCreatedAt() {
        return null;
    }

    public Date getModifiedAt() {
        return null;
    }

    public void delete() {

    }

    public CustomData getCustomData() {
        return null;
    }

    public String getHref() {
        return null;
    }

    public void save() {

    }
}
