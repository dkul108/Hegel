package com.hegel.properties;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Vyacheslav_Lapin
 */
class SimplePropertyMapTest {

    private static final String RESOURCES_DIR_PATH = "src/test/resources/";
    private static final String CONFIG_FILE_PATH = RESOURCES_DIR_PATH + "test.properties";

    private Properties properties = new Properties() {
        @SneakyThrows
        public Properties load(String filePath) {
            try (FileInputStream inStream = new FileInputStream(filePath)) {
                load(inStream);
            }
            return this;
        }
    }.load(CONFIG_FILE_PATH);

    @Test
    void convertFromAndToLegacyClassProperties() throws Exception {
        PropertyMap propertyMap = PropertyMap.from(properties);
        assertThat(propertyMap.toProperties(), is(properties));
        assertThat(propertyMap.get("param2"), is(properties.getProperty("param2")));
    }

    @Test
    void workWithLoad() {
        PropertyMap propertyMap = PropertyMap.fromFile(CONFIG_FILE_PATH);
        assertEquals(propertyMap.toProperties(), properties);
        assertEquals(propertyMap.get("param2"), properties.getProperty("param2"));
//        System.out.println(propertyMap);
    }

    @Test
    @Disabled
    void getBean() {
//        SimpleTestPojo simpleTestPojo = PropertyMap.get(CONFIG_FILE_PATH, SimpleTestPojo.class);
//
//        PropertyMap props = PropertyMap.fromFile(CONFIG_FILE_PATH);
//        SimpleTestPojo expected = new SimpleTestPojo(props.get("param2"), Integer.parseInt(props.get("intParam")));
//
//        assertEquals(expected, simpleTestPojo);
    }
}
