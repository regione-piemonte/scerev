/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class FiltriDate   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [implicit-camel-case] 
  
  private Date eq = null;
  private Date ne = null;
  private Date lt = null;
  private Date lte = null;
  private Date gt = null;
  private Date gte = null;
  private List<Date> in = new ArrayList<Date>();
  private List<Date> nin = new ArrayList<Date>();

  /**
   **/
  


  // nome originario nello yaml: eq 
  public Date getEq() {
    return eq;
  }
  public void setEq(Date eq) {
    this.eq = eq;
  }

  /**
   **/
  


  // nome originario nello yaml: ne 
  public Date getNe() {
    return ne;
  }
  public void setNe(Date ne) {
    this.ne = ne;
  }

  /**
   **/
  


  // nome originario nello yaml: lt 
  public Date getLt() {
    return lt;
  }
  public void setLt(Date lt) {
    this.lt = lt;
  }

  /**
   **/
  


  // nome originario nello yaml: lte 
  public Date getLte() {
    return lte;
  }
  public void setLte(Date lte) {
    this.lte = lte;
  }

  /**
   **/
  


  // nome originario nello yaml: gt 
  public Date getGt() {
    return gt;
  }
  public void setGt(Date gt) {
    this.gt = gt;
  }

  /**
   **/
  


  // nome originario nello yaml: gte 
  public Date getGte() {
    return gte;
  }
  public void setGte(Date gte) {
    this.gte = gte;
  }

  /**
   **/
  


  // nome originario nello yaml: in 
  public List<Date> getIn() {
    return in;
  }
  public void setIn(List<Date> in) {
    this.in = in;
  }

  /**
   **/
  


  // nome originario nello yaml: nin 
  public List<Date> getNin() {
    return nin;
  }
  public void setNin(List<Date> nin) {
    this.nin = nin;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FiltriDate filtriDate = (FiltriDate) o;
    return Objects.equals(eq, filtriDate.eq) &&
        Objects.equals(ne, filtriDate.ne) &&
        Objects.equals(lt, filtriDate.lt) &&
        Objects.equals(lte, filtriDate.lte) &&
        Objects.equals(gt, filtriDate.gt) &&
        Objects.equals(gte, filtriDate.gte) &&
        Objects.equals(in, filtriDate.in) &&
        Objects.equals(nin, filtriDate.nin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(eq, ne, lt, lte, gt, gte, in, nin);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FiltriDate {\n");
    
    sb.append("    eq: ").append(toIndentedString(eq)).append("\n");
    sb.append("    ne: ").append(toIndentedString(ne)).append("\n");
    sb.append("    lt: ").append(toIndentedString(lt)).append("\n");
    sb.append("    lte: ").append(toIndentedString(lte)).append("\n");
    sb.append("    gt: ").append(toIndentedString(gt)).append("\n");
    sb.append("    gte: ").append(toIndentedString(gte)).append("\n");
    sb.append("    in: ").append(toIndentedString(in)).append("\n");
    sb.append("    nin: ").append(toIndentedString(nin)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

