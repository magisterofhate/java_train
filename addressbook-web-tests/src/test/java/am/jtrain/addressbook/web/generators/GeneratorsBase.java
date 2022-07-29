package am.jtrain.addressbook.web.generators;

import org.apache.commons.lang3.RandomStringUtils;

public class GeneratorsBase {

    protected String generateRndString(int length) {
        return RandomStringUtils.randomAlphanumeric(1 + (int) (Math.random() * length));
    }

}
