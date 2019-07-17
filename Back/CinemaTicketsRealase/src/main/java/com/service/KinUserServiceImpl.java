package com.service;

import com.dao.CardDao;
import com.dao.KinoUserDao;
import com.dao.PositionDao;
import com.models.Card;
import com.models.KinoUser;
import com.models.Position;
import javafx.geometry.Pos;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KinUserServiceImpl implements KinUserService {

    private KinoUserDao kinoUserDao;
    private CardDao cardDao;
    private PositionDao positionDao;

    public KinUserServiceImpl(KinoUserDao kinoUserDao, CardDao cardDao, PositionDao positionDao) {
        this.kinoUserDao = kinoUserDao;
        this.cardDao = cardDao;
        this.positionDao = positionDao;
    }

    @Override
    public Long saveKinoUser(KinoUser kinoUser) {
        if(kinoUser.getPosition()!= null) {
            Position position = kinoUser.getPosition();
            if(kinoUser.getPosition().getId() == null) {
                savePosition(position);
            }
        }
        kinoUserDao.save(kinoUser);
        return kinoUser.getId();
    }


    @Override
    public KinoUser findKinoUserById(Long id) {
        return kinoUserDao.findById(id).get();
    }

    @Override
    public Long updateKinoUser(KinoUser kinoUser) {
        KinoUser kinoUser1 = kinoUserDao.findById(kinoUser.getId()).get();
        kinoUser1.setName(kinoUser.getName());
        kinoUser1.setfName(kinoUser.getfName());
        kinoUser1.setAge(kinoUser.getAge());
        kinoUser1.setMail(kinoUser.getMail());
        kinoUser1.setPhone(kinoUser.getPhone());
        kinoUser1.setPosition(kinoUser.getPosition());
        kinoUser1.setCards(kinoUser.getCards());
        kinoUser1.setPassword(kinoUser.getPassword());

        return saveKinoUser(kinoUser1);
    }


    @Override
    public void deleteById(Long id) {
        kinoUserDao.deleteById(id);
    }


    @Override
    public Long savePosition(Position position) {
        return positionDao.save(position).getId();
    }


    @Override
    public Long setPositionKinoUserById(Long idPosition, Long id) {
        Position position = positionDao.findById(idPosition).get();
        KinoUser kinoUser = kinoUserDao.findById(id).get();
        kinoUser.setPosition(position);
        return kinoUserDao.save(kinoUser).getId();
    }

    @Override
    public List<KinoUser> findKinoUserAll() {
        return kinoUserDao.findAll();
    }

    @Override
    public Position getPositionKinoUserById(Long id) {
        return kinoUserDao.findById(id).get().getPosition();
    }



    @Override
    public Long action_true(KinoUser kinoUser) {
        KinoUser kinoUser1 = kinoUserDao.findById(kinoUser.getId()).get();
        kinoUser1.setAction(true);
        return kinoUserDao.save(kinoUser1).getId();
    }

    @Override
    public Long action_false(KinoUser kinoUser) {
        KinoUser kinoUser1 = kinoUserDao.findById(kinoUser.getId()).get();
        kinoUser1.setAction(false);
        return kinoUserDao.save(kinoUser1).getId();
    }

}
