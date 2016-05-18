package com.flipkart.zjsonpatch;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


        List<JsonNode> patches = new ArrayList<>();

        patches.add(JsonDiff.asJson(mapper.readTree(json1), mapper.readTree(json2)));

        a = new A();
        a.setValue("a second value");
        bean.setA(a);

        String json3 = mapper.writeValueAsString(bean);

        patches.add(JsonDiff.asJson(mapper.readTree(json2), mapper.readTree(json3)));


        for(int i=0;i<10000;i++) {
            String prevJson = mapper.writeValueAsString(bean);
            bean.setIntValue(i);
            bean.setbValue("b" +i);
            bean.setStringValue("stringValue" + i);
            String json = mapper.writeValueAsString(bean);
            patches.add(JsonDiff.asJson(mapper.readTree(prevJson), mapper.readTree(json)));
        }

        JsonNode original = mapper.readTree(json1);
        JsonNode prevNode = original;
        for(JsonNode patch: patches) {
            JsonNode patched = JsonPatch.apply(patch, prevNode);
            prevNode = patched;
        }

        Bean bean2 = mapper.treeToValue(prevNode, Bean.class);

        assertEquals(bean, bean2);


    }
}
