package model.domain.item;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ItemInterface
{
  ItemInformation getItemInformation();
  void setItemInformation(ItemInformation itemInformation);
  ArrayList<Object> getInformationList();
  void setInformationList(ArrayList<Object> informationList);
  Object getObjectByName(String name);
  LocalDate getAddDate();
  boolean isBorrowed();
  void changeState();
  boolean equals(Item other);
  String toString();
}
