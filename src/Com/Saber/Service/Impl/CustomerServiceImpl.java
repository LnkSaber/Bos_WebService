package Com.Saber.Service.Impl;

import Com.Saber.Bean.Customer;
import Com.Saber.Service.CustomerService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Transactional
public class CustomerServiceImpl implements CustomerService {
    private JdbcTemplate jdbcTemplate;
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> findAll() {
        String sql ="select * from t_customer";

        List<Customer> list = jdbcTemplate.query(sql,new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String station = rs.getString("station");
                String telephone = rs.getString("telephone");
                String address = rs.getString("address");
                String decidedzone_id = rs.getString("decidedzone_id");
                return new Customer(id, name, station, telephone, address, decidedzone_id);
            }
        });
        return list;
    }

    /**
     * 查询未关联到定区的客户
     * select * from t_customer where decidedzone_id is null
     */
    public List<Customer> findListNotAssociation() {
        String sql ="select * from t_customer where decidedzone_id is null";

        List<Customer> list = jdbcTemplate.query(sql,new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String station = rs.getString("station");
                String telephone = rs.getString("telephone");
                String address = rs.getString("address");
                String decidedzone_id = rs.getString("decidedzone_id");
                return new Customer(id, name, station, telephone, address, decidedzone_id);
            }
        });
        return list;

    }


    /**
     * select * from t_customer where decidedzone_id ='asdfasdffasdj';
     * 查询已经关联到定区的客户
     */
    public List<Customer> findListHasAssociation(String decidedzoneId) {

        String sql ="select * from t_customer where decidedzone_id = ?";

        List<Customer> list = jdbcTemplate.query(sql,new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String station = rs.getString("station");
                String telephone = rs.getString("telephone");
                String address = rs.getString("address");
                String decidedzone_id = rs.getString("decidedzone_id");
                return new Customer(id, name, station, telephone, address, decidedzone_id);
            }
        },decidedzoneId);
        return list;
    }

    /**
     * 定区关联客户
     */
    @Override
    public void assigncustomerstodecidedzone(String decidedzoneId, Integer[] customerIds) {
        //清空客户表中字段为定区id值
        String sql = "update t_customer set decidedzone_id = null where decidedzone_id = ? ";
        jdbcTemplate.update(sql,decidedzoneId);

        //为需要选中的客户管理定区
        sql = "update t_customer set decidedzone_id = ? where id = ? ";
        //需要判断customerIds 不为空
        for(Integer id : customerIds){
            jdbcTemplate.update(sql,decidedzoneId,id);
        }
    }

    /**
     * 根据客户手机号查询客户信息
     */
    @Override
    public Customer findCustomerByTelephone(String telephone) {
        String sql ="select * from t_customer where telephone = ?";

        List<Customer> list = jdbcTemplate.query(sql,new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String station = rs.getString("station");
                String telephone = rs.getString("telephone");
                String address = rs.getString("address");
                String decidedzone_id = rs.getString("decidedzone_id");
                return new Customer(id, name, station, telephone, address, decidedzone_id);
            }
        },telephone);
        if(list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 根据客户的地址查询定区id
     */
    @Override
    public String findDecidedzoneIdByAddress(String address) {
        String sql ="select decidedzone_id from t_customer where address = ?";

        String decidedzoneId = jdbcTemplate.queryForObject(sql, String.class, address);

        return decidedzoneId;
    }

}
