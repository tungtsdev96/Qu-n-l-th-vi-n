/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package card.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import model.BorrowingCard;

/**
 *
 * @author 2&G
 */
public class CardController {

    BorrowingCard card = new BorrowingCard();

    public CardController() {
        card = new BorrowingCard();
    }

    public ArrayList<BorrowingCard> getAllCard() throws ClassNotFoundException, SQLException {
        return card.getAllCard();
    }

    public ArrayList<BorrowingCard> getCardSearchingList(String info) throws ClassNotFoundException, SQLException {
        return card.getCardById(info);
    }
}
