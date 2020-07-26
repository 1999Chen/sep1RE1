package model.domain.reservation;

import model.domain.borrower.BorrowerInterface;
import model.domain.item.ItemInformation;

import java.time.LocalDate;

public class Reservation implements ReservationInterface
{
  private LocalDate reserveDate;
  private BorrowerInterface borrower;
  private ItemInformation itemInformation;

  public Reservation(BorrowerInterface borrower, ItemInformation itemInformation)
  {
    this.reserveDate = LocalDate.now();
    this.borrower = borrower;
    this.itemInformation = itemInformation;
  }

  public LocalDate getReserveDate()
  {
    return reserveDate;
  }

  public BorrowerInterface getBorrower()
  {
    return borrower;
  }

  public ItemInformation getItemInformation()
  {
    return itemInformation;
  }
}
