package com.footmark.core.dao;

public interface LetterDao {

    void updateReadStatus(Long id);

    void updateToDelete(Long id);
}