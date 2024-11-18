package spring.jpa.model;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Role implements Serializable {
@Id
private String role;
private String description;
@Override
public String toString() {
return "Role [role=" + role + ", description=" + description + "]";
}
public Role(String role, String description) {
super();
this.role = role;
this.description = description;
}
public Role() {
super();
// TODO Auto-generated constructor stub
}
public String getRole() {
return role;
}
public void setRole(String role) {
this.role = role;
}
public String getDescription() {
return description;
}
public void setDescription(String description) {
this.description = description;
}
}