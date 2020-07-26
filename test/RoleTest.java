import borrower.Borrower;
import borrower.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

class RoleTest
{

  private String id = "A1";
  private String name = "Alan";
  private String roleName = "Student";
  private String email = "@Alan.com";
  private int borrowDuration = 10;
  private static Role role = Role.getRole("Student", 10);
  private Borrower borrower = new Borrower(this.id, this.name, this.roleName,
      this.email);

  @BeforeEach void add()
  {
    borrower.setRole("Student");
    System.out.println("this is before each");
  }

  @Test void testgetRole()
  {
    assertEquals("Student",role.getRoleName());
  }

  @Test void testgetRole2()
  {
    assertEquals(borrower.getRole(), role);
  }

  @Test void testgetAllRole()
  {

    ArrayList<Role> a = Role.getAllRole();
    assertNotNull(a);
  }

  @Test void testremoveRole()
  {
    boolean a = Role.getAllRole().contains(role);
    Role.removeRole("Student");
    boolean b = Role.getAllRole().contains(role);
    assertNotEquals(a, b);
  }

  @Test void testgetRoleName()
  {
    assertEquals("Student", role.getRoleName());
  }

  @Test void testgetBorrowDuration()
  {
    assertEquals(10, role.getBorrowDuration());
  }

  @Test void testsetBorrowDuration()
  {
    role.setBorrowDuration(6);
    assertEquals(6, role.getBorrowDuration());
  }
}