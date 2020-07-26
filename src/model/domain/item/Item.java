package model.domain.item;

import java.time.LocalDate;
import java.util.ArrayList;

public class Item implements ItemInterface
{
  private ItemInformation itemInformation;
  private ArrayList<Object> informationList;
  private boolean isBorrowed;
  private LocalDate addDate;

  public Item(ItemInformation itemInformation,ArrayList<Object> informationList)
  {
    this(itemInformation,informationList,LocalDate.now());
  }

  public Item(ItemInformation itemInformation,ArrayList<Object> informationList,LocalDate addDate)
  {
    this.itemInformation = itemInformation;
    this.informationList = informationList;
    checkInformationList();
    isBorrowed = false;
    this.addDate = addDate;
  }

  private void checkInformationList()
  {
    if (!VariableInformationList.checkInformation(itemInformation.getItemType().getVariableInformationList().getVariableInformationNotForInformation(),informationList))
    {
      for (int x=0;x<itemInformation.getItemType().getVariableInformationList().getVariableInformationNotForInformation().getSize();x++)
      {
        if (!itemInformation.getItemType().getVariableInformationList().getVariableInformationNotForInformation().getVariableInformationByIndex(x).getType().equals(informationList.get(x).getClass().getSimpleName()))
        {
          this.informationList.set(x,"error");
        }
      }
    }
  }

  public ItemInformation getItemInformation()
  {
    return itemInformation;
  }

  public void setItemInformation(ItemInformation itemInformation)
  {
    this.itemInformation = itemInformation;
  }

  public ArrayList<Object> getInformationList()
  {
    return informationList;
  }

  public void setInformationList(ArrayList<Object> informationList)
  {
    this.informationList = informationList;
  }

  @Override public Object getObjectByName(String name)
  {
    VariableInformation variableInformation = itemInformation.getItemType().getVariableInformationList().getVariableInformationByName(name);
    if (variableInformation!=null)
    {
      int index;
      if (variableInformation.isForInformation())
      {
        index = itemInformation.getItemType().getVariableInformationList().getVariableInformationForInformation().getIndexByVariableInformation(variableInformation);
        return itemInformation.getInformationList().get(index);
      }
      else
      {
        index = itemInformation.getItemType().getVariableInformationList().getVariableInformationNotForInformation().getIndexByVariableInformation(variableInformation);
        return informationList.get(index);
      }
    }
    return null;
  }

  public LocalDate getAddDate()
  {
    return addDate;
  }

  public boolean isBorrowed()
  {
    return isBorrowed;
  }

  public void changeState()
  {
    if (isBorrowed)
    {
      isBorrowed = false;
    }
    else
    {
      isBorrowed = true;
    }
  }

  public boolean equals(Item other)
  {
    if (itemInformation.equals(other.getItemInformation()))
    {
      if (addDate.equals(other.getAddDate()))
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
    }
    return false;
  }

  @Override public String toString()
  {
    return itemInformation.toString() + "\n" + VariableInformationList.toString(itemInformation.getItemType().getVariableInformationList().getVariableInformationNotForInformation(),informationList);
  }
}
