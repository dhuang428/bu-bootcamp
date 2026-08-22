import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; 
 
public class ContactTest {
  private Contact contact; 
 
  @BeforeEach
  void setUp() {
    contact = new Contact("Ada Lovelace", "+1 617 555 0101");
  } 
 
  @Test 
  void constructor_setsNameCorrectly() { 
    assertEquals("Ada Lovelace", contact.getName()); 
  } 
 
  @Test
  void constructor_setsPhoneCorrectly() { 
    assertEquals("+1 617 555 0101", contact.getPhone()); 
  } 
 
  @Test
  void getName_returnsExactString_notTransformed() { 
    assertEquals("Ada Lovelace", contact.getName());
  } 
 
  @Test
  void toString_containsName() { 
    assertTrue(contact.toString().contains("Ada"));
  } 
 
  @Test
  void toString_containsPhone() {
    assertTrue(contact.toString().contains("555 0101"));
  }

  // additional test
  @Test
  void getPhone_returnsExactString_notTransformed() { 
    assertEquals("+1 617 555 0101", contact.getPhone());
  }   
}