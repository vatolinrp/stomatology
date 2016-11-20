package by.bsu.vatolinrp.subd.model;

public class ReportLine
{
  private String monthName;

  private Integer total;

  public String getMonthName()
  {
    return monthName;
  }

  public void setMonthName( String monthName )
  {
    this.monthName = monthName;
  }

  public Integer getTotal()
  {
    return total;
  }

  public void setTotal( Integer total )
  {
    this.total = total;
  }

  public ReportLine( String monthName, Integer total )
  {
    this.monthName = monthName;
    this.total = total;
  }
}
