package cn.cxd.jdbc;

import cn.cxd.jdbc.model.BookState;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 11:35 2020/5/13
 * @Modified By:
 */
public class DaoJdbcdaoSupport extends JdbcDaoSupport {
    public Object queryForSingleObject(String ibsn){
        String sql = " select ibsn,price,bookname,store from book natural join bookstore where ibsn = ?";
        RowMapper rowMapper = new BeanPropertyRowMapper(BookState.class);
        return getJdbcTemplate().queryForObject(sql,rowMapper,ibsn);
    }
}
