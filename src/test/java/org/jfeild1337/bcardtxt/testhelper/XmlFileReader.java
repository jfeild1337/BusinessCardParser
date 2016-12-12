/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jfeild1337.bcardtxt.testhelper;

import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author jfeild
 */
public class XmlFileReader {
   private JAXBContext ctx;

   public XmlFileReader() throws JAXBException 
   {
      this.ctx = JAXBContext.newInstance(BCardDataEntityList.class, BCardDataEntity.class);
   }
   
   public List<BCardDataEntity> readTestDataFromXML(String xmlFile) throws JAXBException
   {
      Unmarshaller unmarshaller = ctx.createUnmarshaller();
      
      StreamSource xmlStream = new StreamSource(xmlFile);
      BCardDataEntityList entityList = (BCardDataEntityList)unmarshaller.unmarshal(xmlStream, BCardDataEntityList.class).getValue();
      return entityList.getDataEntityList();
   }
   
      
}
