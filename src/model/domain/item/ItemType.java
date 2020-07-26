package model.domain.item;

import model.domain.borrower.RoleInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ItemType
{
  private static Map<String, ItemType> allInstance = new HashMap<>();
  private static ArrayList<String> keyList = new ArrayList<>();
  private String typeName;
  private VariableInformationList variableInformationList;
  private Map<String,Integer> borrowDuration;

  private ItemType(String typeName)
  {
    this.typeName = typeName;
    variableInformationList = new VariableInformationList();
    borrowDuration = new HashMap<>();
  }

  private ItemType(String typeName, VariableInformationList variableInformationList)
  {
    this.typeName = typeName;
    this.variableInformationList = variableInformationList;
    borrowDuration = new  HashMap<>();
  }

  public static ItemType getItemType(String typeName)
  {
    ItemType itemType = allInstance.get(typeName.toLowerCase());
    return itemType;
  }

  public static ItemType getItemType(String typeName, VariableInformationList variableInformationList)
  {
    ItemType instance = allInstance.get(typeName.toLowerCase());
    if (instance==null)
    {
      synchronized (allInstance)
      {
        instance = allInstance.get(typeName.toLowerCase());
        if (instance==null)
        {
          if (variableInformationList==null)
          {
            instance = new ItemType(typeName);
          }
          else
          {
            instance = new ItemType(typeName, variableInformationList);
          }
          allInstance.put(typeName.toLowerCase(),instance);
          keyList.add(typeName.toLowerCase());
        }
      }
    }
    return instance;
  }

  public static ArrayList<ItemType> getAllItemType()
  {
    ArrayList<ItemType> allItemType = new ArrayList<>();
    for (int x=0;x<keyList.size();x++)
    {
      allItemType.add(allInstance.get(keyList.get(x)));
    }
    return allItemType;
  }

  public String getTypeName()
  {
    return typeName;
  }

  public void setTypeName(String typeName)
  {
    this.typeName = typeName;
  }

  public VariableInformationList getVariableInformationList()
  {
    return variableInformationList;
  }

  public void setVariableInformationList(
      VariableInformationList variableInformationList)
  {
    this.variableInformationList = variableInformationList;
  }

  public void addBorrowDuration(RoleInterface role,int time)
  {
    if (!borrowDuration.containsKey(role.getRoleName().toLowerCase()))
    {
      borrowDuration.put(role.getRoleName().toLowerCase(),time);
    }
  }

  public void setBorrowDuration(RoleInterface role,int time)
  {
    if (borrowDuration.get(role.getRoleName().toLowerCase())!=null)
    {
      borrowDuration.put(role.getRoleName().toLowerCase(),time);
    }
  }

  public int getBorrowDuration(RoleInterface role)
  {
    if (borrowDuration.containsKey(role.getRoleName().toLowerCase()))
    {
      return borrowDuration.get(role.getRoleName().toLowerCase());
    }
    return role.getBorrowDuration();
  }

  public void removeBorrowDuration(RoleInterface role)
  {
    borrowDuration.remove(role.getRoleName().toLowerCase());
  }

  public boolean equals(ItemType itemType)
  {
    return typeName.equals(itemType.getTypeName())&&variableInformationList.equals(itemType.getVariableInformationList());
  }

  @Override public String toString()
  {
    return "Type: " + typeName;
  }
}
