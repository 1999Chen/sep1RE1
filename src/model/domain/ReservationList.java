package model.domain;

import model.domain.borrower.BorrowerInterface;
import model.domain.item.ItemInformation;
import model.domain.item.ItemType;
import model.domain.reservation.ReservationInterface;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationList
{
  private ArrayList<ReservationInterface> reservationList;

  public ReservationList()
  {
    reservationList = new ArrayList<>();
  }

  public void addReservation(ReservationInterface newReservation)
  {
    if (newReservation!=null)
    {
      reservationList.add(newReservation);
    }
  }

  public int getSize()
  {
    return reservationList.size();
  }

  public ReservationInterface getReservationByIndex(int index)
  {
    if (index>=0&&index<reservationList.size())
    {
      return reservationList.get(index);
    }
    return null;
  }

  public ReservationList getReservationsByBorrower(BorrowerInterface borrower)
  {
    ReservationList searchList = new ReservationList();
    for (int x = 0;x<reservationList.size();x++)
    {
      if (reservationList.get(x).getBorrower().getId().equals(borrower.getId()))
      {
        searchList.addReservation(reservationList.get(x));
      }
    }
    return searchList;
  }

  public ReservationList getReservationsByItemType(ItemType itemType)
  {
    ReservationList searchList = new ReservationList();
    for (int x = 0;x<reservationList.size();x++)
    {
      if (reservationList.get(x).getItemInformation().getItemType().equals(itemType))
      {
        searchList.addReservation(reservationList.get(x));
      }
    }
    return searchList;
  }

  public ReservationList getReservationsByItemInformation(
      ItemInformation itemInformation)
  {
    ReservationList searchList = new ReservationList();
    for (int x = 0;x<reservationList.size();x++)
    {
      if (reservationList.get(x).getItemInformation().equals(itemInformation))
      {
        searchList.addReservation(reservationList.get(x));
      }
    }
    return searchList;
  }

  public ReservationList getReservationsByReserveDate(LocalDate reserveDate)
  {
    ReservationList searchList = new ReservationList();
    for (int x = 0;x<reservationList.size();x++)
    {
      if (reservationList.get(x).getReserveDate().equals(reserveDate))
      {
        searchList.addReservation(reservationList.get(x));
      }
    }
    return searchList;
  }

  public void removeReservationByIndex(int index)
  {
    reservationList.remove(index);
  }

  public void removeReservationByReservation(ReservationInterface reservation)
  {
    reservationList.remove(reservation);
  }
}
