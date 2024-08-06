package com.example.fullstack_study_boostcourse.guestbook.service;

import com.example.fullstack_study_boostcourse.guestbook.dao.GuestbookDao;
import com.example.fullstack_study_boostcourse.guestbook.dao.LogDao;
import com.example.fullstack_study_boostcourse.guestbook.dto.Guestbook;
import com.example.fullstack_study_boostcourse.guestbook.dto.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

//@Service
//@Transactional
public class GuestbookServiceImpl implements GuestbookService {

    @Autowired
    private GuestbookDao guestbookDao;
    @Autowired
    private LogDao logDao;

    @Override
    public List<Guestbook> getGuestBooks(Integer start) {
        return guestbookDao.selectAll(start, LIMIT);
    }

    @Override
    public int deleteGuestBook(Long id, String ip) {
        int deleteCount = guestbookDao.deleteById(id);
        Log log = new Log();
        log.setIp(ip);
        log.setMethod("delete");
        log.setRegdate(new Date());
        logDao.insert(log);
        return deleteCount;
    }

    @Override
    public Guestbook addGuestBook(Guestbook guestbook, String ip) {
        guestbook.setRegdate(new Date());
        Long id = guestbookDao.insert(guestbook);
        guestbook.setId(id);

        Log log = new Log();
        log.setIp(ip);
        log.setMethod("insert");
        log.setRegdate(new Date());
        logDao.insert(log);
        return guestbook;
    }
    
    @Override
    public int getCount() {
        return guestbookDao.selectCount();
    }
}
