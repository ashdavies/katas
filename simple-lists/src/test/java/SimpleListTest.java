import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SimpleListTest {

  private SimpleList simpleList;

  @Before
  public void createList() {
    simpleList = new MySimpleList();
  }

  @Test
  public void shouldNotFindWhenEmpty() {
    assertEquals(null, simpleList.find("fred"));
  }

  @Test
  public void shouldHandleAddition() throws Exception {
    simpleList.add("fred");

    assertEquals("fred", simpleList.find("fred"));

    simpleList.add("wilma");

    assertEquals("fred", simpleList.find("fred"));
    assertEquals("wilma", simpleList.find("wilma"));
    String[] expecteds = arrayOf("fred", "wilma");
    assertArrayEquals(expecteds, simpleList.values());
  }

  @Test
  public void shouldHandleDeletion() throws Exception {
    simpleList.add("fred");
    simpleList.add("wilma");
    simpleList.add("betty");
    simpleList.add("barney");

    assertArrayEquals(arrayOf("fred", "wilma", "betty", "barney"), simpleList.values());

    simpleList.delete(simpleList.find("wilma"));

    assertArrayEquals(arrayOf("fred", "betty", "barney"), simpleList.values());

    simpleList.delete(simpleList.find("barney"));

    assertArrayEquals(arrayOf("fred", "betty"), simpleList.values());

    simpleList.delete(simpleList.find("fred"));

    assertArrayEquals(arrayOf("betty"), simpleList.values());

    simpleList.delete(simpleList.find("betty"));

    assertArrayEquals(arrayOf(), simpleList.values());
  }

  private static String[] arrayOf(String... s) {
    return s;
  }

}
