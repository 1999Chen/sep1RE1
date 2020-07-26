package model.domain.borrower;

public class Borrower implements BorrowerInterface
{
  private String id;
  private String name;
  private Role role;
  private String email;

  public Borrower(String id,String name,String roleName,String email)
  {
    this.id = id;
    this.name = name;
    this.email = email;
    this.role = Role.getRole(roleName);
  }

  public String getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public String getEmail()
  {
    return email;
  }

  public String getRoleName()
  {
    return role.getRoleName();
  }

  public int getBorrowDuration()
  {
    return role.getBorrowDuration();
  }

  public RoleInterface getRole()
  {
    return role;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public void setRole(String roleName)
  {
    this.role = Role.getRole(roleName);
  }
}
