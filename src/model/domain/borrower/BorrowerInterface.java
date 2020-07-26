package model.domain.borrower;

public interface BorrowerInterface
{
  public String getId();
  public String getName();
  public String getEmail();
  public String getRoleName();
  public int getBorrowDuration();
  public RoleInterface getRole();
  public void setName(String name);
  public void setEmail(String email);
  public void setRole(String roleName);
}
