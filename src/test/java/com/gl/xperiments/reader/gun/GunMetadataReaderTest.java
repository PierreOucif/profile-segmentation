package com.gl.xperiments.reader.gun;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * GunMetadataReaderTest
 * Created by p_rbondel on 28/10/2016.
 */
public class GunMetadataReaderTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(GunMetadataReaderTest.class);
    private GunMetadataReader gunMetadataReader;

    @Before
    public void setUp() throws Exception {
        gunMetadataReader = new GunMetadataReader();
    }

    @Test
    public void empty() throws Exception {
        final GunMetadata metadata = gunMetadataReader.getMetadata(null);
        Assert.assertNotNull(metadata);
    }

    @Test
    public void allImages() throws Exception {
        Files.newDirectoryStream(Paths.get(this.getClass().getClassLoader().getResource(".").toURI()), "*.{jpg}").forEach((Path dir) -> {
            try {
                final GunMetadata metadata = gunMetadataReader.getMetadata(new FileInputStream(dir.toFile()));
                Assert.assertNotNull(metadata);
            } catch (FileNotFoundException e) {
                LOGGER.error("Error", e);
            }
        });
    }
}