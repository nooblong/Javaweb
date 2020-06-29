import nooblong.utils.JDBCUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestJDBC {
    @Test
    public void test1() throws IOException {
        Properties p = new Properties();
        p.load(this.getClass().getClassLoader().getResourceAsStream("/config/druid.properties"));
        System.out.println(p.getProperty("url"));
    }
}
