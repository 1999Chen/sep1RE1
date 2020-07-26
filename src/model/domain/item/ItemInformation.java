package model.domain.item;

import java.util.ArrayList;

public class ItemInformation
{
  private static ArrayList<ItemInformation> allInstance = new ArrayList<>();
  private ItemType itemType;
  private ArrayList<Object> informationList;

  private ItemInformation(ItemType itemType, ArrayList<Object> informationList)
  {
    this.itemType = itemType;
    this.informationList = informationList;
    checkInformationList();
  }

  public static ItemInformation getItemInformation(ItemType itemType,ArrayList<Object> informationList)
  {
    for (int i=0;i<itemType.getVariableInformationList().getSize();i++)
    {
      if (itemType.getVariableInformationList().getVariableInformationByIndex(i).isNoRepeat())
      {
        for (int x=0;x<allInstance.size();x++)
        {
          if (allInstance.get(x).getItemType().getTypeName().equals(itemType.getTypeName()))
          {
            if (allInstance.get(x).getInformationList().get(i).equals(informationList.get(i)))
            {
              return allInstance.get(x);
            }
          }
        }
      }
    }
    ItemInformation newItemInformation = new ItemInformation(itemType, informationList);
    allInstance.add(newItemInformation);
    return newItemInformation;
  }

  public static ArrayList<ItemInformation> getAllItemInformation()
  {
    return allInstance;
  }

  public static void removeItemInformationByIndex(int index)
  {
    allInstance.remove(index);
  }

  public static void removeItemInformationByItemInformation(
      ItemInformation itemInformation)
  {
    allInstance.remove(itemInformation);
  }

  private void checkInformationList()
  {
    if (!VariableInformationList.checkInformation(itemType.getVariableInformationList().getVariableInformationForInformation(),informationList))
    {
      for (int x=0;x<itemType.getVariableInformationList().getVariableInformationForInformation().getSize();x++)
      {
        if (!itemType.getVariableInformationList().getVariableInformationForInformation().getVariableInformationByIndex(x).getType().equals(informationList.get(x).getClass().getSimpleName()))
        {
          this.informationList.set(x,"error");
        }
      }
    }
  }

  public ItemType getItemType()
  {
    return itemType;
  }

  public Object getObjectByName(String name)
  {
    VariableInformation variableInformation = itemType.getVariableInformationList().getVariableInformationByName(name);
    if (variableInformation!=null)
    {
      if (variableInformation.isForInformation())
      {
        int index = itemType.getVariableInformationList().getVariableInformationForInformation().getIndexByVariableInformation(variableInformation);
        return informationList.get(index);
      }
    }
    return null;
  }

  public ArrayList<Object> getInformationList()
  {
    return informationList;
  }

  public boolean equals(ItemInformation other)
  {
    if (itemType.equals(other.getItemType()))
    {
      if (informationList.size()==other.getInformationList().size())
      {
        for (int x=0;x<informationList.size();x++)
        {
          if (!informationList.get(x).equals(other.getInformationList().get(x)))
          {
            return false;
          }
        }
        return true;
      }
    }
    return false;
  }

  @Override public String toString()
  {
    String toString;
    return itemType.toString() + "\n" + VariableInformationList
        .toString(itemType.getVariableInformationList().getVariableInformationForInformation(),informationList);
  }
}
