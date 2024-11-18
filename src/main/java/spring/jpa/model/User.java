package spring.jpa.model;
import java.io.Serializable;
import java.util.Collection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
@Entity
@Table(name ="users")
public class User implements Serializable{
@Id
private String username;
private String password;
private boolean actived;
public boolean isActived() {
return actived;
}
public void setActived(boolean actived) {
this.actived = actived;
}
@ManyToMany
@JoinTable(name="USERS_ROLES")
private Collection <Role> roles;
public User(String username, String password, boolean actived, Collection<Role>
roles) {

super();
this.username = username;
this.password = password;
this.actived = actived;
this.roles = roles;
}
@Override
public String toString() {
return "User [username=" + username + ", password=" + password + ",actived=" + actived + ", roles=" + roles

+ "]";

}
public User() {
super();
// TODO Auto-generated constructor stub
}
public String getUsername() {
return username;
}
public void setUsername(String username) {
this.username = username;
}
public String getPassword() {
return password;
}
public void setPassword(String password) {
this.password = password;
}
public Collection<Role> getRoles() {
return roles;
}
public void setRoles(Collection<Role> roles) {
this.roles = roles;
}
}