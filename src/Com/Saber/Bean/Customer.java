package Com.Saber.Bean;

public class Customer {
    /**
     * `id` int(11) NOT NULL auto_increment,
     `name` varchar(255) default NULL,
     `station` varchar(255) default NULL,
     `telephone` varchar(255) default NULL,
     `address` varchar(255) default NULL,
     `decidedzone_id` varchar(255) default NULL,
     */
    private int id;
    private String name;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", station='" + station + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", decidedzone_id='" + decidedzone_id + '\'' +
                '}';
    }

    private String station;
    private String telephone;
    private String address;
    private String decidedzone_id;


    public Customer() {

    }
    public Customer(int id, String name, String station, String telephone, String address, String decidedzone_id) {
        super();
        this.id = id;
        this.name = name;
        this.station = station;
        this.telephone = telephone;
        this.address = address;
        this.decidedzone_id = decidedzone_id;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStation() {
        return station;
    }
    public void setStation(String station) {
        this.station = station;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getDecidedzone_id() {
        return decidedzone_id;
    }
    public void setDecidedzone_id(String decidedzone_id) {
        this.decidedzone_id = decidedzone_id;
    }
}
