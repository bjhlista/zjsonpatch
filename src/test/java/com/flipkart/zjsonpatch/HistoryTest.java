package com.flipkart.zjsonpatch;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class HistoryTest {

    @Test
    public void testHistory() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        A a = new A();
        a.setValue("a value");

        Bean bean = new Bean();
        bean.setA(a);
        bean.setbValue("base value");
        bean.setIntValue(77);
        bean.setStringValue("howdy doody");
        bean.setLongs(Arrays.asList(55L, 33L, 101L));


        String json1 = mapper.writeValueAsString(bean);

        bean.setIntValue(78);

        String json2 = mapper.writeValueAsString(bean);


        JsonNode patch = JsonDiff.asJson(mapper.readTree(json1), mapper.readTree(json2));

        System.out.println("delta1 " + patch.asText());

        a = new A();
        a.setValue("a second value");
        bean.setA(a);

        String json3 = mapper.writeValueAsString(bean);

        JsonNode patch2 = JsonDiff.asJson(mapper.readTree(json2), mapper.readTree(json3));

        System.out.println("delta2 " + patch2.asText());



        JsonNode original = mapper.readTree(json1);

        JsonNode patched1 = JsonPatch.apply(patch, original);

        JsonNode patched2 = JsonPatch.apply(patch2, patched1);

        Bean bean2 = mapper.treeToValue(patched2, Bean.class);

        assertEquals(bean, bean2);


    }
}
