package fragrantia.fragrantiaadminserver.domain.store;

import fragrantia.fragrantiaadminserver.domain.notice.Notice;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import static fragrantia.fragrantiaadminserver.utils.Preconditions.require;

@Getter
public class Store {
    private Long id;
    private Double latitude;
    private Double longitude;
    private Integer zip;
    private String address;
    private String name;
    private String detail;
    private String telephone;
    private String file;

    public Store(Long id, Double latitude, Double longitude, Integer zip, String address, String name, String detail, String telephone, String file) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zip = zip;
        this.address = address;
        this.name = name;
        this.detail = detail;
        this.telephone = telephone;
        this.file = file;
    }

    private Store(Double latitude, Double longitude, Integer zip, String address, String name, String detail, String telephone, String file) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.zip = zip;
        this.address = address;
        this.name = name;
        this.detail = detail;
        this.telephone = telephone;
        this.file = file;
    }

    public static Store create(Double latitude, Double longitude, Integer zip, String address, String name, String detail, String telephone, String file) {
        require(latitude != null);
        require(longitude != null);
        require(zip != null);
        require(Strings.isNotBlank(address));
        require(Strings.isNotBlank(name));
        require(Strings.isNotBlank(detail));
        require(Strings.isNotBlank(telephone));

        return new Store(latitude, longitude, zip, address, name, detail, telephone, file);
    }


    public void update(Double latitude, Double longitude, Integer zip, String address, String name, String detail, String telephone, String file) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.zip = zip;
        this.address = address;
        this.name = name;
        this.detail = detail;
        this.telephone = telephone;
        this.file = file;
    }
}
