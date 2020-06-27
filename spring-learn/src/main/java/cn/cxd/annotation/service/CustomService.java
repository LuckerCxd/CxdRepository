package cn.cxd.annotation.service;

import cn.cxd.annotation.repository.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 16:17 2020/5/8
 * @Modified By:
 */
@Service("service")
public class CustomService implements MyService{
    @Autowired
    private CustomRepository customRepository;

}
