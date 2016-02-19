package com.footmark.core.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.footmark.common.enums.StatusEnum;
import com.footmark.core.dao.impl.CommentDaoImpl;
import com.footmark.core.entity.Comment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-footmark-*.xml",
        "classpath:applicationContext-footmark.xml" })
public class CommentTest {

    @Autowired
    private CommentDaoImpl commentDao;

    @Test
    public void testInsertComment() {
        Comment c = new Comment();
        c.setUserId(111L);
        c.setStatus(StatusEnum.NORMAL);
        commentDao.add(c);
    }
}
