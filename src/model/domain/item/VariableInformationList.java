package model.domain.item;

import java.util.ArrayList;

public class VariableInformationList
{
  private ArrayList<VariableInformation> variableInformationList;

  public static boolean checkInformation(
      VariableInformationList variableInformationList,ArrayList<Object> informationList)
  {
    if (variableInformationList.getSize()==informationList.size())
    {
      for (int x=0;x<variableInformationList.getSize();x++)
      {
        if (!variableInformationList.getVariableInformationByIndex(x).getType().equals(informationList.get(x).getClass().getSimpleName()))
        {
          return false;
        }
      }
    }
    else
    {
      return false;
    }
    return true;
  }

  public static String toString(VariableInformationList variableInformationList,ArrayList<Object> informationList)
  {
    String toString;
    toString = variableInformationList.getVariableInformationByIndex(0).getName() + ": " + informationList.get(0);
    for (int x=1;x<variableInformationList.getSize();x++)
    {
      toString += "\n" + variableInformationList.getVariableInformationByIndex(x).getName() + ": " + informationList.get(x);
    }
    return toString;
  }

  public VariableInformationList()
  {
    variableInformationList = new ArrayList<>();
  }

  public void addVariable(VariableInformation variableInformation)
  {
    variableInformationList.add(variableInformation);
  }

  public VariableInformation getVariableInformationByIndex(int index)
  {
    if (index<variableInformationList.size()&&index>=0)
    {
      return variableInformationList.get(index);
    }
    return null;
  }

  public VariableInformation getVariableInformationByName(String name)
  {
    for(int x=0;x<variableInformationList.size();x++)
    {
      if (variableInformationList.get(x).getName().equals(name))
      {
        return variableInformationList.get(x);
      }
    }
    return null;
  }

  public VariableInformationList getVariableInformationForInformation()
  {
    VariableInformationList forInformation = new VariableInformationList();
    for(int x=0;x<variableInformationList.size();x++)
    {
      if (variableInformationList.get(x).isForInformation())
      {
        forInformation.addVariable(variableInformationList.get(x));
      }
    }
    return forInformation;
  }

  public VariableInformationList getVariableInformationNotForInformation()
  {
    VariableInformationList notForInformation = new VariableInformationList();
    for(int x=0;x<variableInformationList.size();x++)
    {
      if (!variableInformationList.get(x).isForInformation())
      {
        notForInformation.addVariable(variableInformationList.get(x));
      }
    }
    return notForInformation;
  }

  public int getIndexByVariableInformation(
      VariableInformation variableInformation)
  {
    for (int x=0;x<variableInformationList.size();x++)
    {
      if (variableInformationList.get(x).equals(variableInformation))
      {
        return x;
      }
    }
    return -1;
  }

  public int getIndexByName(String name)
  {
    for (int x=0;x<variableInformationList.size();x++)
    {
      if (variableInformationList.get(x).equals(getVariableInformationByName(name)))
      {
        return x;
      }
    }
    return -1;
  }

  public int getSize()
  {
    return variableInformationList.size();
  }

  public void removeVariableInformationByIndex(int index)
  {
    variableInformationList.remove(index);
  }

  public boolean equals(VariableInformationList other)
  {
    if (variableInformationList.size()!=other.getSize())
    {
      return false;
    }
    for (int x=0;x<variableInformationList.size();x++)
    {
      if (!variableInformationList.get(x).equals(other.getVariableInformationByIndex(x)))
      {
        return false;
      }
    }
    return true;
  }
}
