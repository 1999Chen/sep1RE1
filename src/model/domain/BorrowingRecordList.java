package model.domain;

import model.domain.borrower.BorrowerInterface;
import model.domain.borrowingRecord.BorrowingRecordInterface;
import model.domain.item.ItemInterface;

import java.time.LocalDate;
import java.util.ArrayList;

public class BorrowingRecordList
{
  private ArrayList<BorrowingRecordInterface> borrowingRecordList;

  public BorrowingRecordList()
  {
    borrowingRecordList = new ArrayList<>();
  }

  public void addBorrowingRecord(BorrowingRecordInterface newBorrowingRecord)
  {
    if (newBorrowingRecord!=null)
    {
      borrowingRecordList.add(newBorrowingRecord);
    }
  }

  public int getSize()
  {
    return borrowingRecordList.size();
  }

  public BorrowingRecordInterface getBorrowingRecordByIndex(int index)
  {
    if (index>=0&&index<borrowingRecordList.size())
    {
      return borrowingRecordList.get(index);
    }
    return null;
  }

  public BorrowingRecordList getBorrowingRecordsByItem(ItemInterface item)
  {
    BorrowingRecordList searchList = new BorrowingRecordList();
    for (int x = 0;x<borrowingRecordList.size();x++)
    {
      if (borrowingRecordList.get(x).getItem().equals(item))
      {
        searchList.addBorrowingRecord(borrowingRecordList.get(x));
      }
    }
    return searchList;
  }

  public BorrowingRecordList getBorrowingRecordsByBorrower(
      BorrowerInterface borrower)
  {
    BorrowingRecordList searchList = new BorrowingRecordList();
    for (int x = 0;x<borrowingRecordList.size();x++)
    {
      if (borrowingRecordList.get(x).getBorrower().getId().equals(borrower.getId()))
      {
        searchList.addBorrowingRecord(borrowingRecordList.get(x));
      }
    }
    return searchList;
  }

  public BorrowingRecordList getBorrowingRecordsByBorrowDate(LocalDate borrowDate)
  {
    BorrowingRecordList searchList = new BorrowingRecordList();
    for (int x = 0;x<borrowingRecordList.size();x++)
    {
      if (borrowingRecordList.get(x).getBorrowDate()==borrowDate)
      {
        searchList.addBorrowingRecord(borrowingRecordList.get(x));
      }
    }
    return searchList;
  }

  public BorrowingRecordList getBorrowingRecordsByPlanReturnDate(LocalDate planReturnDate)
  {
    BorrowingRecordList searchList = new BorrowingRecordList();
    for (int x = 0;x<borrowingRecordList.size();x++)
    {
      if (borrowingRecordList.get(x).getPlanReturnDate()==planReturnDate)
      {
        searchList.addBorrowingRecord(borrowingRecordList.get(x));
      }
    }
    return searchList;
  }
  public BorrowingRecordList getBorrowingRecordsByReturnDate(LocalDate returnDate)
  {
    BorrowingRecordList searchList = new BorrowingRecordList();
    for (int x = 0;x<borrowingRecordList.size();x++)
    {
      if (borrowingRecordList.get(x).getReturnDate()==returnDate)
      {
        searchList.addBorrowingRecord(borrowingRecordList.get(x));
      }
    }
    return searchList;
  }

  public void removeBorrowingRecordByIndex(int index)
  {
    borrowingRecordList.remove(index);
  }

  public void removeBorrowingRecordByBorrowingRecord(
      BorrowingRecordInterface borrowingRecord)
  {
    borrowingRecordList.remove(borrowingRecord);
  }
}
