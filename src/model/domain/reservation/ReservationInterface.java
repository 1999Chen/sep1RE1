package model.domain.reservation;

import model.domain.borrower.BorrowerInterface;
import model.domain.item.ItemInformation;

import java.time.LocalDate;

public interface ReservationInterface
{
  LocalDate getReserveDate();
  BorrowerInterface getBorrower();
  ItemInformation getItemInformation();
}
