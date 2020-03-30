package com.cebbank.airm.tech;

import io.micrometer.core.instrument.config.InvalidConfigurationException;
import org.junit.Test;
import org.junit.*;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MybatisGen{
        @Test
        public void generator() throws IOException, SQLException,
                InterruptedException, XMLParserException, InvalidConfigurationException, org.mybatis.generator.exception.InvalidConfigurationException {
                List<String> warnings = new ArrayList<String>();
                boolean overwrite = true;
                URL path = this.getClass().getClassLoader().getResource("generatorConfig.xml");
                System.out.println(path);
                File configFile = new File(path.getPath());
                ConfigurationParser cp = new ConfigurationParser(warnings);
                Configuration config = cp.parseConfiguration(configFile);
                DefaultShellCallback callback = new DefaultShellCallback(overwrite);
                MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                        callback, warnings);
                myBatisGenerator.generate(null);
                for (String w : warnings)
                        System.out.println(w);
        }
}
