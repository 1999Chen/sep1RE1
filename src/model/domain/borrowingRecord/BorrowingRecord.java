package model.domain.borrowingRecord;

import model.domain.borrower.BorrowerInterface;
import model.domain.item.ItemInterface;

import java.time.LocalDate;

public class BorrowingRecord implements BorrowingRecordInterface
{
  private LocalDate borrowDate;
  private LocalDate returnDate;
  private ItemInterface item;
  private BorrowerInterface borrower;

  public BorrowingRecord(BorrowerInterface borrower,ItemInterface item)
  {
    this.borrower = borrower;
    this.item = item;
    this.borrowDate = LocalDate.now();
  }

  public BorrowingRecord(BorrowerInterface borrower,ItemInterface item,LocalDate borrowDate)
  {
    this(borrower, item);
    this.borrowDate = borrowDate;
  }

  public BorrowingRecord(BorrowerInterface borrower,ItemInterface item,LocalDate borrowDate,LocalDate returnDate)
  {
    this(borrower, item);
    this.borrowDate = borrowDate;
    this.returnDate = returnDate;
  }

  public void setReturnDate(LocalDate returnDate)
  {
    this.returnDate = returnDate;
  }

  public LocalDate getBorrowDate()
  {
    return borrowDate;
  }

  public LocalDate getReturnDate()
  {
    return returnDate;
  }

  public BorrowerInterface getBorrower()
  {
    return borrower;
  }

  public ItemInterface getItem()
  {
    return item;
  }

  public LocalDate getPlanReturnDate()
  {
    return borrowDate.plusDays(borrower.getBorrowDuration());
  }
}
