package com.footmark.core.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.footmark.core.dao.LetterDao;
import com.footmark.core.entity.Letter;
import com.footmark.core.enums.ReadStatusEnum;
import com.footmark.framework.dao.GenericDaoDefault;

@Repository
public class LetterDaoImpl extends GenericDaoDefault<Letter> implements LetterDao {

    @Override
    public void updateReadStatus(Long id) {
        Letter letter = new Letter();
        letter.setId(id);
        letter.setReadStatus(ReadStatusEnum.READED);
        this.update(letter);
    }

    @Override
    public void updateToDelete(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        this.update("deleteFromParent", params);
    }

}
