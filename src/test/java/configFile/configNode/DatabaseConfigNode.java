package configFile.configNode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

//nu puteam pune si aici name="DriverConfig" ??
@XmlAccessorType(XmlAccessType.FIELD)
public class DatabaseConfigNode {

    @XmlElement(name="Port")
    public String port;

    @XmlElement(name="DatabaseName")
    public String databaseName;

    @XmlElement(name="Username")
    public String username;

    @XmlElement(name="Password")
    public String password;





}
