package com.example.coviddatavisiualisation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.File;

@SpringBootTest
class CovidDataVisualisationApplicationTests {

    @Test
    void contextLoads() {
        // assert that the file is downloaded
        File file = new File("src/main/resources/data.csv");
        Assert.isTrue(file.exists(), "The file is not downloaded");
    }

}
