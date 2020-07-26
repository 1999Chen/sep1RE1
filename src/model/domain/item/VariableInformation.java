package model.domain.item;

public class VariableInformation
{
  private String type;
  private String name;
  private boolean forInformation;
  private boolean noRepeat;

  public VariableInformation(String type,String name,boolean forInformation,boolean noRepeat)
  {
    this.type = type;
    this.name = name;
    this.forInformation = forInformation;
    this.noRepeat = noRepeat;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getName()
  {
    return name;
  }

  public String getType()
  {
    return type;
  }

  public void setForInformation(boolean forInformation)
  {
    this.forInformation = forInformation;
  }

  public void setNoRepeat(boolean noRepeat)
  {
    this.noRepeat = noRepeat;
  }

  public boolean isForInformation()
  {
    return forInformation;
  }

  public boolean isNoRepeat()
  {
    return noRepeat;
  }

  public boolean equals(VariableInformation other)
  {
    return type.equals(other.getType())&&name.equals(other.getName())&&forInformation==other.isForInformation()&&noRepeat==other.isNoRepeat();
  }

  @Override public String toString()
  {
    return type + "," + name + "," + forInformation + "," + noRepeat;
  }
}

