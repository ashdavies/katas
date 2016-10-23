public class MySimpleList implements SimpleList {

  private static final String NOT_FOUND = null;

  private Node head;
  private Node tail;
  private int count;

  @Override
  public String find(String element) {
    if (isEmpty()) {
      return NOT_FOUND;
    }

    Node node = head;
    while(node != null) {
      if (node.value.equals(element)) {
        return node.value;
      }

      node = node.next;
    }

    return NOT_FOUND;
  }

  @Override
  public void add(String element) {
    Node node = new Node(element);

    if (isEmpty()) {
      head = tail = node;
    } else {
      tail.next = node;
      tail = node;
    }

    count++;
  }

  @Override
  public String[] values() {
    String[] result = new String[count];

    int index = 0;
    Node node = head;
    while(node != null && index < count) {
      result[index] = node.value;
      node = node.next;
      index++;
    }

    return result;
  }

  @Override
  public void delete(String element) {
    if (isEmpty()) {
      return;
    }

    if (head.value.equals(element)) {
      head = head.next;
      count--;
      return;
    }


    Node node = head.next;
    Node prev = head;
    while(node != null) {
      if (node.value.equals(element)) {
        prev.next = node.next;
        count--;
        return;
      }

      prev = node;
      node = node.next;
    }
  }

  public boolean isEmpty() {
    return head == null;
  }

  private static class Node {
    String value;
    Node next;

    Node(String value) {
      this(value, null);
    }

    Node(String value, Node next) {
      this.value = value;
      this.next = next;
    }
  }
}
