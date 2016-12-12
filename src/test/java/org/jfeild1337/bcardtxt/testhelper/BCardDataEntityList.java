/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jfeild1337.bcardtxt.testhelper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jfeild
 */
@XmlRootElement(name="dataEntries")
public class BCardDataEntityList {
   
   private List<BCardDataEntity> dataEntityList;

   public BCardDataEntityList(List<BCardDataEntity> dataEntityList) 
   {
      this.dataEntityList = dataEntityList;
   }
   
   public BCardDataEntityList() 
   {
      this.dataEntityList = new ArrayList<>();
   }

   @XmlElement(type = BCardDataEntity.class, name="dataEntry")
   public List<BCardDataEntity> getDataEntityList() 
   {
      return dataEntityList;
   }
   
   public void setDataEntityList(List<BCardDataEntity> dataEntityList) 
   {
      this.dataEntityList = dataEntityList;
   }
   
   
}
