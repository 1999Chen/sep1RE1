package model;

import model.domain.*;
import model.domain.borrower.Borrower;
import model.domain.borrower.BorrowerInterface;
import model.domain.borrowingRecord.BorrowingRecord;
import model.domain.borrowingRecord.BorrowingRecordInterface;
import model.domain.item.*;
import model.domain.reservation.Reservation;
import model.domain.reservation.ReservationInterface;
import utility.InitialData;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.ArrayList;

public class LibraryModelManager implements LibraryModel
{
  private BorrowerList borrowerList;
  private BorrowingRecordList borrowingRecordList;
  private ItemList itemList;
  private ReservationList reservationList;
  private LocalError error;
  private PropertyChangeSupport property;

  public LibraryModelManager()
  {
    borrowerList = new BorrowerList();
    borrowingRecordList = new BorrowingRecordList();
    itemList = new ItemList();
    reservationList = new ReservationList();
    error = new LocalError();
    property = new PropertyChangeSupport(this);
    InitialData.initialData();
  }

  private String getHexadecimalRandomNumber()
  {
    String number = "";
    for (int x=0;x<8;x++)
    {
      int randomNumber = (int) (Math.random() * 16 + 0);
      switch (randomNumber)
      {
        case 10:
          number+="A";
          break;
        case 11:
          number+="B";
          break;
        case 12:
          number+="C";
          break;
        case 13:
          number+="D";
          break;
        case 14:
          number+="E";
          break;
        case 15:
          number+="F";
          break;
        default:
          number+=randomNumber;
          break;
      }
    }
    return number;
  }

  @Override public void updateError(String error)
  {
    this.error.updateError(error);
    property.firePropertyChange("error",null,this.error);
  }

  @Override public void addBorrower(String name, String roleName, String email)
  {
    if (borrowerList.getBorrowerByEmail(email)!=null)
    {
      updateError("Repetitive email");
    }
    else
    {
      String newId = getHexadecimalRandomNumber();
      while (borrowerList.getBorrowerById(newId)!=null)
      {
        newId = getHexadecimalRandomNumber();
      }
      borrowerList.addBorrower(new Borrower(newId,name,roleName,email));
      property.firePropertyChange("borrower",null,borrowerList);
    }
  }

  @Override public BorrowerInterface getBorrower(int index)
  {
    return borrowerList.getBorrowerByIndex(index);
  }

  @Override public BorrowerInterface getBorrowerById(String id)
  {
    return borrowerList.getBorrowerById(id);
  }

  @Override public BorrowerInterface getBorrowerByEmail(String email)
  {
    return borrowerList.getBorrowerByEmail(email);
  }

  @Override public BorrowerList getBorrowers(String role)
  {
    return borrowerList.getBorrowersByRole(role);
  }

  @Override public void removeBorrower(int index)
  {
    borrowerList.removeBorrowerByIndex(index);
    property.firePropertyChange("borrower",null,borrowerList);
  }

  @Override public void removeBorrower(BorrowerInterface borrower)
  {
    borrowerList.removeBorrowerByBorrower(borrower);
    property.firePropertyChange("borrower",null,borrowerList);
  }

  @Override public void addBorrowingRecord(BorrowerInterface borrower, ItemInterface item)
  {
    if (!item.isBorrowed())
    {
      if (borrowingRecordList.getBorrowingRecordsByBorrower(borrower).getBorrowingRecordsByItem(item).getSize()>0)
      {
        updateError("Borrow too much [" + item.getObjectByName("Title") + "]");
      }
      else
      {
        item.changeState();
        borrowingRecordList.addBorrowingRecord(new BorrowingRecord(borrower,item));
        property.firePropertyChange("borrowingRecord",null,borrowingRecordList);
      }
    }
    else
    {
      updateError("This " + item.getItemInformation().getItemType().getTypeName() + " is borrowed");
    }
  }

  @Override public void addBorrowingRecord(BorrowerInterface borrower, ItemInterface item, LocalDate borrowDate)
  {
    if (!item.isBorrowed())
    {
      item.changeState();
      borrowingRecordList.addBorrowingRecord(new BorrowingRecord(borrower,item,borrowDate));
      property.firePropertyChange("borrowingRecord",null,borrowingRecordList);
    }
    else
    {
      updateError("This " + item.getItemInformation().getItemType().getTypeName() + " is borrowed");
    }
  }

  @Override public void addBorrowingRecord(BorrowerInterface borrower, ItemInterface item, LocalDate borrowDate, LocalDate returnDate)
  {
    borrowingRecordList.addBorrowingRecord(new BorrowingRecord(borrower,item,borrowDate,returnDate));
    property.firePropertyChange("borrowingRecord",null,borrowingRecordList);
  }

  @Override public BorrowingRecordInterface getBorrowingRecord(int index)
  {
    return borrowingRecordList.getBorrowingRecordByIndex(index);
  }

  @Override public BorrowingRecordList getBorrowingRecords(ItemInterface item)
  {
    return borrowingRecordList.getBorrowingRecordsByItem(item);
  }

  @Override public BorrowingRecordList getBorrowingRecords(BorrowerInterface borrower)
  {
    return borrowingRecordList.getBorrowingRecordsByBorrower(borrower);
  }

  @Override public BorrowingRecordList getBorrowingRecordsByBorrowDate(LocalDate borrowDate)
  {
    return borrowingRecordList.getBorrowingRecordsByBorrowDate(borrowDate);
  }

  @Override public BorrowingRecordList getBorrowingRecordsByPlanReturnDate(
      LocalDate planReturnDate)
  {
    return borrowingRecordList.getBorrowingRecordsByPlanReturnDate(planReturnDate);
  }

  @Override public BorrowingRecordList getBorrowingRecordsByReturnDate(
      LocalDate returnDate)
  {
    return borrowingRecordList.getBorrowingRecordsByReturnDate(returnDate);
  }

  @Override public void removeBorrowingRecord(int index)
  {
    borrowingRecordList.removeBorrowingRecordByIndex(index);
    property.firePropertyChange("borrowingRecord",null,borrowingRecordList);
  }

  @Override public void removeBorrowingRecord(
      BorrowingRecordInterface borrowingRecord)
  {
    borrowingRecordList.removeBorrowingRecordByBorrowingRecord(borrowingRecord);
    property.firePropertyChange("borrowingRecord",null,borrowingRecordList);
  }

  @Override public void addItemInformation(ItemType itemType,
      ArrayList<Object> informationList)
  {
    boolean legalInformation = true;
    for (int x=0;x<itemType.getVariableInformationList().getVariableInformationForInformation().getSize();x++)
    {
      if (itemType.getVariableInformationList().getVariableInformationForInformation().getVariableInformationByIndex(x).isNoRepeat())
      {
        for (int i=0;i<ItemInformation.getAllItemInformation().size();i++)
        {
          if (ItemInformation.getAllItemInformation().get(i).getInformationList().get(x).equals(informationList.get(x)))
          {
            updateError("The information of " + itemType.getVariableInformationList().getVariableInformationForInformation().getVariableInformationByIndex(x).getName() + " can't repeat");
            legalInformation = false;
          }
        }
      }
    }
    if (legalInformation)
    {
      ItemInformation.getItemInformation(itemType, informationList);
      property.firePropertyChange("itemInformation",null,ItemInformation.getAllItemInformation());
    }
  }

  @Override public ItemInformation getItemInformation(int index)
  {
    return ItemInformation.getAllItemInformation().get(index);
  }

  @Override public ArrayList<ItemInformation> getItemInformation(ItemType itemType, String name, Object information)
  {
    ArrayList<ItemInformation> searchList = new ArrayList<>();
    for (int x=0;x<ItemInformation.getAllItemInformation().size();x++)
    {
      if (ItemInformation.getAllItemInformation().get(x).getItemType().equals(itemType))
      {
        int index = itemType.getVariableInformationList().getVariableInformationForInformation().getIndexByName(name);
        if (ItemInformation.getAllItemInformation().get(x).getInformationList().get(index).equals(information))
        {
          searchList.add(ItemInformation.getAllItemInformation().get(x));
        }
      }
    }
    return searchList;
  }

  @Override public ArrayList<ItemInformation> getAllItemInformation()
  {
    return ItemInformation.getAllItemInformation();
  }

  @Override public void removeItemInformation(int index)
  {
    ItemInformation.removeItemInformationByIndex(index);
    property.firePropertyChange("itemInformation",null,ItemInformation.getAllItemInformation());
  }

  @Override public void removeItemInformation(ItemInformation itemInformation)
  {
    ItemInformation.removeItemInformationByItemInformation(itemInformation);
    property.firePropertyChange("itemInformation",null,ItemInformation.getAllItemInformation());
  }

  @Override public void addItem(ItemInformation itemInformation, ArrayList<Object> informationList)
  {
    boolean legalInformation = true;
    for (int x=0;x<itemInformation.getItemType().getVariableInformationList().getVariableInformationForInformation().getSize();x++)
    {
      if (itemInformation.getItemType().getVariableInformationList().getVariableInformationNotForInformation().getVariableInformationByIndex(x).isNoRepeat())
      {
        for (int i=0;i<itemList.getSize();i++)
        {
          if (itemList.getItemByIndex(i).getInformationList().get(x).equals(informationList.get(x)))
          {
            updateError("The information of " + itemInformation.getItemType().getVariableInformationList().getVariableInformationForInformation().getVariableInformationByIndex(x).getName() + " can't repeat");
            legalInformation = false;
          }
        }
      }
    }
    if (legalInformation)
    {
      itemList.addItem(new Item(itemInformation,informationList));
      property.firePropertyChange("item",null,itemList);
    }
  }

  @Override public ItemInterface getItem(int index)
  {
    return itemList.getItemByIndex(index);
  }

  @Override public ItemList getItem(String name, Object information)
  {
    ItemList searchList = new ItemList();
    for (int x=0;x<ItemType.getAllItemType().size();x++)
    {
      VariableInformation variableInformation = ItemType.getAllItemType().get(x).getVariableInformationList().getVariableInformationByName(name);
      if (variableInformation!=null)
      {
        ItemList partOfSearch = itemList.getItemsByVariable(variableInformation,information);
        for (int i=0;i<partOfSearch.getSize();i++)
        {
          searchList.addItem(partOfSearch.getItemByIndex(i));
        }
      }
    }
    return searchList;
  }

  @Override public ItemList getItem(ItemInformation itemInformation)
  {
    return itemList.getItemsByItemInformation(itemInformation);
  }

  @Override public ItemList getItem(ItemType itemType)
  {
    return itemList.getItemsByItemType(itemType);
  }

  @Override public ItemList getItem(LocalDate addDate)
  {
    return itemList.getItemsByAddDate(addDate);
  }

  @Override public ItemList getItem(boolean isBorrowed)
  {
    return itemList.getItemsByState(isBorrowed);
  }

  @Override public void removeItem(int index)
  {
    itemList.removeItemByIndex(index);
    property.firePropertyChange("item",null,itemList);
  }

  @Override public void removeItem(ItemInterface item)
  {
    itemList.removeItemByItem(item);
    property.firePropertyChange("item",null,itemList);
  }

  @Override public void addReservation(BorrowerInterface borrower,
      ItemInformation itemInformation)
  {
    if (reservationList.getReservationsByBorrower(borrower).getReservationsByItemInformation(itemInformation).getSize()>0)
    {
      updateError("Reserve too much [" + itemInformation.getObjectByName("Title") + "]");
    }
    else
    {
      reservationList.addReservation(new Reservation(borrower, itemInformation));
      property.firePropertyChange("reservation",null,reservationList);
    }
  }

  @Override public ReservationInterface getReservation(int index)
  {
    return reservationList.getReservationByIndex(index);
  }

  @Override public ReservationList getReservations(BorrowerInterface borrower)
  {
    return reservationList.getReservationsByBorrower(borrower);
  }

  @Override public ReservationList getReservations(ItemType itemType)
  {
    return reservationList.getReservationsByItemType(itemType);
  }

  @Override public ReservationList getReservations(
      ItemInformation itemInformation)
  {
    return reservationList.getReservationsByItemInformation(itemInformation);
  }

  @Override public ReservationList getReservations(LocalDate reserveDate)
  {
    return reservationList.getReservationsByReserveDate(reserveDate);
  }

  @Override public void removeReservation(int index)
  {
    reservationList.removeReservationByIndex(index);
    property.firePropertyChange("reservation",null,reservationList);
  }

  @Override public void removeReservation(ReservationInterface reservation)
  {
    reservationList.removeReservationByReservation(reservation);
    property.firePropertyChange("reservation",null,reservationList);
  }

  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(propertyName, listener);
  }

  @Override public void removeListener(String propertyName,
      PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(propertyName, listener);
  }
}
