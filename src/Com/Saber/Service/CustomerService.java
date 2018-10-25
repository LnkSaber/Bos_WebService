package Com.Saber.Service;

import Com.Saber.Bean.Customer;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface CustomerService {
    List<Customer> findAll();

    //查询未关联到定区的客户
   List<Customer> findListNotAssociation();

    //查询已经关联到定区的客户
     List<Customer> findListHasAssociation(String decidedzoneId);

    //定区关联客户
    void assigncustomerstodecidedzone(String decidedzoneId,Integer[] customerIds);

    //根据客户手机号查询客户信息22
     Customer findCustomerByTelephone(String telephone);

    //根据客户的地址查询定区id
    String findDecidedzoneIdByAddress(String address);

}
