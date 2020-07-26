package model.domain.borrowingRecord;

import model.domain.borrower.BorrowerInterface;
import model.domain.item.ItemInterface;

import java.time.LocalDate;

public interface BorrowingRecordInterface
{
  void setReturnDate(LocalDate returnDate);
  LocalDate getBorrowDate();
  LocalDate getReturnDate();
  BorrowerInterface getBorrower();
  ItemInterface getItem();
  LocalDate getPlanReturnDate();
}
