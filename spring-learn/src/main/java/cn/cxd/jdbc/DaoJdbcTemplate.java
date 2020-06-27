package cn.cxd.jdbc;

import cn.cxd.jdbc.model.BookState;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 11:34 2020/5/13
 * @Modified By:
 */
public class DaoJdbcTemplate {
    private JdbcTemplate jdbcTemplate = null;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // queryForObject
    public Object queryForSingleObject(String ibsn){
        String sql = " select ibsn,price,bookname,store from book natural join bookstore where ibsn = ?";
        RowMapper rowMapper = new BeanPropertyRowMapper(BookState.class);
        return jdbcTemplate.queryForObject(sql,rowMapper,ibsn);
    }

    // queryField
    public String queryField(String ibsn){
        String sql = " select bookname from book where ibsn = ?";
        return jdbcTemplate.queryForObject(sql,String.class,ibsn);
    }

    // queryAvg
    public Integer queryAvg(){
        String sql = " select sum(price) from book ";
        return  jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // query*
    public Map queryMap(String ibsn){
        String sql = " select * from book where ibsn = ?";
        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap(sql, ibsn);
        return  stringObjectMap;
    }



    // query*list
    public List queryMapList(String ibsn){
        String sql = " select * from book where ibsn >= ?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, ibsn);
        return  maps;
    }

    //queryFieldList
    public List queryFieldList(String ibsn){
        String sql = " select price from book where ibsn >= ?";
        List<Integer> integers = jdbcTemplate.queryForList(sql, int.class, ibsn);
        return  integers;
    }

    //queryObjectList
    public List queryObjectList(String ibsn){
        String sql = " select price,ibsn,bookname name,store from book natural join bookstore where ibsn >= ?";
        List list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(BookState.class), 1);
        return list;
    }

    //queryFieldMap
    public Map queryFieldMap(String ibsn){
        String sql = " select price from book where ibsn = ?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, 1);
        return map;
    }

    // batch insert
    public int[] batchUpdate(){
        String sql = "insert into account (NAME,money) values (?,?)";
        List<Object[]> objectList = new ArrayList<>();
        objectList.add(new Object[]{"aa",100});
        objectList.add(new Object[]{"bb","ddd"});
        objectList.add(new Object[]{"cc",300});
        return jdbcTemplate.batchUpdate(sql, objectList);
    }

    // update insert
    public int update(String ibsn,int store){
        String sql = " update bookstore set store = ? where ibsn = ?";
        return jdbcTemplate.update(sql,store,ibsn);
    }

}
