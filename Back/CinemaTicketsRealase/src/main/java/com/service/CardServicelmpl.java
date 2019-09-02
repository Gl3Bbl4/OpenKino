package com.service;
import com.dao.CardDao;
import com.dao.KinoUserDao;
import com.models.Card;
import com.models.KinoUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class CardServicelmpl implements CardService{
    private CardDao cardDao;
    private KinoUserDao kinoUserDao;

    public CardServicelmpl(CardDao cardDao, KinoUserDao kinoUserDao) {
        this.cardDao = cardDao;
        this.kinoUserDao = kinoUserDao;
    }

    @Override
    public Long saveCard(Card card, KinoUser kinoUser) {
        KinoUser kinoUser1 = kinoUserDao.findById(kinoUser.getId()).get();
        card.setKinoUser(kinoUser1);
        cardDao.save(card);
        return card.getId();
    }

    @Override
    public void deleteCardById(Long id) {
          cardDao.deleteById(id);
    }

    @Override
    public List<Card> findCardsByIdKinoUser(Long id) {
        return cardDao.findAllByKinoUserId(id);
    }

    @Override
    public Long addBalance(Long id, BigDecimal money) {
        Card card = cardDao.findById(id).get();
        card.setBalance(card.getBalance().add(money));
        return cardDao.save(card).getId();
    }
}
