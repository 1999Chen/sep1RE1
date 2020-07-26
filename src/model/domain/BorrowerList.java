package model.domain;

import model.domain.borrower.BorrowerInterface;

import java.util.ArrayList;

public class BorrowerList
{
  private ArrayList<BorrowerInterface> borrowerList;

  public BorrowerList()
  {
    borrowerList = new ArrayList<>();
  }

  public void addBorrower(BorrowerInterface newBorrower)
  {
    if (newBorrower!=null)
    {
      if (getBorrowerById(newBorrower.getId())==null)
      {
        borrowerList.add(newBorrower);
      }
    }
  }

  public int getSize()
  {
    return borrowerList.size();
  }

  public BorrowerInterface getBorrowerByIndex(int index)
  {
    if (index<borrowerList.size()&&index>=0)
    {
      return borrowerList.get(index);
    }
    return null;
  }

  public BorrowerInterface getBorrowerById(String id)
  {
    for (int x=0;x<borrowerList.size();x++)
    {
      if (borrowerList.get(x).getId().equals(id))
      {
        return borrowerList.get(x);
      }
    }
    return null;
  }

  public BorrowerInterface getBorrowerByEmail(String email)
  {
    for (int x=0;x<borrowerList.size();x++)
    {
      if (borrowerList.get(x).getEmail().equals(email))
      {
        return borrowerList.get(x);
      }
    }
    return null;
  }

  public BorrowerList getBorrowersByRole(String role)
  {
    BorrowerList getList = new BorrowerList();
    for (int x=0;x<borrowerList.size();x++)
    {
      if (borrowerList.get(x).getRoleName().toLowerCase().equals(role.toLowerCase()))
      {
        getList.addBorrower(borrowerList.get(x));
      }
    }
    return getList;
  }

  public void removeBorrowerByIndex(int index)
  {
    borrowerList.remove(index);
  }

  public void removeBorrowerByBorrower(BorrowerInterface borrower)
  {
    borrowerList.remove(borrower);
  }
}
