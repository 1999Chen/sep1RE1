package model;

import model.domain.BorrowerList;
import model.domain.BorrowingRecordList;
import model.domain.ItemList;
import model.domain.ReservationList;
import model.domain.borrower.BorrowerInterface;
import model.domain.borrowingRecord.BorrowingRecordInterface;
import model.domain.item.ItemInformation;
import model.domain.item.ItemInterface;
import model.domain.item.ItemType;
import model.domain.reservation.ReservationInterface;
import utility.NamedPropertyChangeSubject;

import java.time.LocalDate;
import java.util.ArrayList;

public interface LibraryModel extends NamedPropertyChangeSubject
{
  void updateError(String error);
  void addBorrower(String name, String roleName, String email);
  BorrowerInterface getBorrower(int index);
  BorrowerInterface getBorrowerById(String id);
  BorrowerInterface getBorrowerByEmail(String email);
  BorrowerList getBorrowers(String role);
  void removeBorrower(int index);
  void removeBorrower(BorrowerInterface borrower);

  void addBorrowingRecord(BorrowerInterface borrower, ItemInterface item);
  void addBorrowingRecord(BorrowerInterface borrower, ItemInterface item,
      LocalDate borrowDate);
  void addBorrowingRecord(BorrowerInterface borrower, ItemInterface item,
      LocalDate borrowDate, LocalDate returnDate);
  BorrowingRecordInterface getBorrowingRecord(int index);
  BorrowingRecordList getBorrowingRecords(ItemInterface item);
  BorrowingRecordList getBorrowingRecords(BorrowerInterface borrower);
  BorrowingRecordList getBorrowingRecordsByBorrowDate(LocalDate borrowDate);
  BorrowingRecordList getBorrowingRecordsByPlanReturnDate(
      LocalDate planReturnDate);
  BorrowingRecordList getBorrowingRecordsByReturnDate(LocalDate returnDate);
  void removeBorrowingRecord(int index);
  void removeBorrowingRecord(BorrowingRecordInterface borrowingRecord);

  void addItemInformation(ItemType itemType, ArrayList<Object> informationList);
  ItemInformation getItemInformation(int index);
  ArrayList<ItemInformation> getItemInformation(ItemType itemType, String name,
      Object information);
  ArrayList<ItemInformation> getAllItemInformation();
  void removeItemInformation(int index);
  void removeItemInformation(ItemInformation itemInformation);

  void addItem(ItemInformation itemInformation,
      ArrayList<Object> informationList);
  ItemInterface getItem(int index);
  ItemList getItem(String name, Object information);
  ItemList getItem(ItemInformation itemInformation);
  ItemList getItem(ItemType itemType);
  ItemList getItem(LocalDate addDate);
  ItemList getItem(boolean isBorrowed);
  void removeItem(int index);
  void removeItem(ItemInterface item);

  void addReservation(BorrowerInterface borrower,
      ItemInformation itemInformation);
  ReservationInterface getReservation(int index);
  ReservationList getReservations(BorrowerInterface borrower);
  ReservationList getReservations(ItemType itemType);
  ReservationList getReservations(ItemInformation itemInformation);
  ReservationList getReservations(LocalDate reserveDate);
  void removeReservation(int index);
  void removeReservation(ReservationInterface reservation);
}
