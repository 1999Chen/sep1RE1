package model.domain;

import model.domain.error.ErrorInterface;
import model.domain.error.HasError;
import model.domain.error.NoError;

public class LocalError
{
  private ErrorInterface error;

  public LocalError()
  {
    error = new NoError();
  }

  public String getError()
  {
    return error.getError();
  }

  public void updateError(String error)
  {
    if (error == null || error.equals(""))
    {
      this.error = new NoError();
    }
    else
    {
      this.error = new HasError(error);
    }
  }
}
