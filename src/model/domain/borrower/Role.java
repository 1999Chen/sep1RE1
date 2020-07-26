package model.domain.borrower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Role implements RoleInterface
{
  private static Map<String, Role> allInstance = new HashMap<>();
  private static ArrayList<String> keyList = new ArrayList<>();
  private String roleName;
  private int borrowDuration;

  public static Role getRole(String roleName)
  {
    Role role = allInstance.get(roleName.toLowerCase());
    return role;
  }

  public static Role getRole(String roleName,int borrowDuration)
  {
    Role instance = allInstance.get(roleName.toLowerCase());
    if (instance==null)
    {
      synchronized (allInstance)
      {
        instance = allInstance.get(roleName.toLowerCase());
        if (instance==null)
        {
          instance = new Role(roleName, borrowDuration);
          allInstance.put(roleName.toLowerCase(),instance);
          keyList.add(roleName.toLowerCase());
        }
      }
    }
    return instance;
  }

  public static ArrayList<Role> getAllRole()
  {
    ArrayList<Role> allRole = new ArrayList<>();
    for (int x=0;x<keyList.size();x++)
    {
      allRole.add(allInstance.get(keyList.get(x)));
    }
    return allRole;
  }

  public static void removeRole(String roleName)
  {
    allInstance.remove(roleName.toLowerCase());
  }

  private Role(String roleName,int borrowDuration)
  {
    this.roleName = roleName;
    this.borrowDuration = borrowDuration;
  }

  public String getRoleName()
  {
    return roleName;
  }

  @Override public int getBorrowDuration()
  {
    return borrowDuration;
  }

  @Override public void setBorrowDuration(int borrowDuration)
  {
    this.borrowDuration = borrowDuration;
  }

  @Override public String toString()
  {
    return "Role: " + roleName + "\nBorrow Duration: " + borrowDuration;
  }
}
