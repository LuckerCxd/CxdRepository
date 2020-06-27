package cn.cxd.jdbc;

import cn.cxd.jdbc.model.BookState;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 18:17 2020/5/14
 * @Modified By:
 */
public class DaoJdbcNameProperty {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int butchUpdate(){
        String sql = " insert into book (ibsn,bookname,price) values (:ibsn,:name,:price)";
        BookState bookState = new BookState();
        bookState.setIbsn("3");
        bookState.setName("C++");
        bookState.setPrice(30);

        BookState bookState2 = new BookState();
        bookState.setIbsn("4");
        bookState.setName("Python");
        bookState.setPrice(40);


        BookState bookState3 = new BookState();
        bookState.setIbsn("5");
        bookState.setName("PHP");
        bookState.setPrice(50);

        SqlParameterSource ps1 = new BeanPropertySqlParameterSource(bookState);
        SqlParameterSource ps2 = new BeanPropertySqlParameterSource(bookState2);
        SqlParameterSource ps3 = new BeanPropertySqlParameterSource(bookState3);

        SqlParameterSource[] sqlParameters = new SqlParameterSource[]{ps1,ps2,ps3};
        return namedParameterJdbcTemplate.batchUpdate(sql,sqlParameters).length;
    }
}
