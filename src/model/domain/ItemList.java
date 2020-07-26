package model.domain;

import model.domain.item.ItemInformation;
import model.domain.item.ItemInterface;
import model.domain.item.ItemType;
import model.domain.item.VariableInformation;

import java.time.LocalDate;
import java.util.ArrayList;

public class ItemList
{
  private ArrayList<ItemInterface> itemList;

  public ItemList()
  {
    itemList = new ArrayList<>();
  }

  public void addItem(ItemInterface newItem)
  {
    if (newItem!=null)
    {
      itemList.add(newItem);
    }
  }

  public int getSize()
  {
    return itemList.size();
  }

  public ItemInterface getItemByIndex(int index)
  {
    if (index>=0&&index<itemList.size())
    {
      return itemList.get(index);
    }
    return null;
  }

  public ItemList getItemsByVariable(VariableInformation variableInformation,Object information)
  {
    ItemList searchList = new ItemList();
    for (int x=0;x<itemList.size();x++)
    {
      int indexOfInformation;
      if (variableInformation.isForInformation())
      {
        indexOfInformation = itemList.get(x).getItemInformation().getItemType().getVariableInformationList().getVariableInformationForInformation().getIndexByVariableInformation(variableInformation);
        if (itemList.get(x).getItemInformation().getInformationList().get(indexOfInformation).equals(information))
        {
          searchList.addItem(itemList.get(x));
        }
      }
      else
      {
        indexOfInformation = itemList.get(x).getItemInformation().getItemType().getVariableInformationList().getVariableInformationNotForInformation().getIndexByVariableInformation(variableInformation);
        if (itemList.get(x).getInformationList().get(indexOfInformation).equals(information))
        {
          searchList.addItem(itemList.get(x));
        }
      }
    }
    return searchList;
  }

  public ItemList getItemsByItemInformation(ItemInformation itemInformation)
  {
    ItemList searchList = new ItemList();
    for (int x=0;x<itemList.size();x++)
    {
      if (itemList.get(x).getItemInformation().equals(itemInformation))
      {
        searchList.addItem(itemList.get(x));
      }
    }
    return searchList;
  }

  public ItemList getItemsByItemType(ItemType itemType)
  {
    ItemList searchList = new ItemList();
    for (int x=0;x<itemList.size();x++)
    {
      if (itemList.get(x).getItemInformation().getItemType().equals(itemType))
      {
        searchList.addItem(itemList.get(x));
      }
    }
    return searchList;
  }

  public ItemList getItemsByAddDate(LocalDate addDate)
  {
    ItemList searchList = new ItemList();
    for (int x=0;x<itemList.size();x++)
    {
      if (itemList.get(x).getAddDate().equals(addDate))
      {
        searchList.addItem(itemList.get(x));
      }
    }
    return searchList;
  }

  public ItemList getItemsByState(boolean isBorrowed)
  {
    ItemList searchList = new ItemList();
    for (int x=0;x<itemList.size();x++)
    {
      if (itemList.get(x).isBorrowed()==isBorrowed)
      {
        searchList.addItem(itemList.get(x));
      }
    }
    return searchList;
  }

  public void removeItemByIndex(int index)
  {
    itemList.remove(index);
  }

  public void removeItemByItem(ItemInterface item)
  {
    itemList.remove(item);
  }
}
