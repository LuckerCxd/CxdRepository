package cn.cxd.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 23:54 2020/5/14
 * @Modified By:
 */

public class BookTranscationService {
    @Autowired
    private BookBaseService bookBaseService;

    public void someoneBuyBook(String uname,String ibsn){
        int price = bookBaseService.findPrice(ibsn);
        bookBaseService.updateStore(ibsn);
        bookBaseService.updateAccount(uname,price);
    }
}
