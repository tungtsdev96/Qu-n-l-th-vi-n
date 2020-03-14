/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borrowbook.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tungts
 */
public class BorrowerInforHelper {
//    public static String setInfomationCode(){
//    
//    
//    
//    }
    public static String getLentDate(String borrowedDate){
        String lentDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = Calendar.getInstance().getTime();
        String n = addDate(Calendar.getInstance() , -2);
        try {
            Date n1 = formatter.parse(n);
            Date borrowed = formatter.parse(borrowedDate);
            if (borrowed.before(n1)){
                return null;
            }
        } catch (ParseException ex) {
            Logger.getLogger(BorrowerInforHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        lentDate = formatter.format(date);
        return lentDate;
    }
    
    public static String addDate(Calendar date,int count){
        date.add(Calendar.DATE, count);
       return date.get(Calendar.DATE)
                + "/"
                + (date.get(Calendar.MONTH) + 1)
                + "/"
                + date.get(Calendar.YEAR);
    }
    
    public static String  getExpectReturnDate(String lentDate){
        String  expectReturnDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = formatter.parse(lentDate);
            Calendar lent = Calendar.getInstance();
            lent.setTime(date);
            expectReturnDate = addDate(lent, 14);
        } catch (ParseException ex) {
            Logger.getLogger(BorrowerInforHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return expectReturnDate;
    }
    
}
