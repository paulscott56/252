package za.co.paulscott.person.com.dstvdm.person.model;

/**
 * Created by pscot on 3/7/2016.
 */
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(value = {"handler"})
public class Role {

    @Id
    private String id;
    @Version
    @JsonIgnore
    private Long version;
    private String name;
    private String description;
    @ManyToMany
    private List<Permission> permissions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Permission> getPermissions() {
        if (permissions == null)
            this.permissions = new ArrayList<>();
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

}