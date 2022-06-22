package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SingleRoomTest {
  SingleRoom roomtest;
  SingleRoom roomtest1;
  SingleRoom roomtest2;

  @BeforeEach
  void setUp() {
    roomtest= new SingleRoom(1,300,1);
    roomtest1= new SingleRoom(1,300,0);
  }

  @Test
  void getMaxOccupancy() {
    assertEquals(1,roomtest.getMaxOccupancy());
  }

  @Test
  void getPrice() {
    assertEquals(300,roomtest.getPrice());
  }

  @Test
  void setPrice() throws IllegalArgument {
    roomtest.setPrice(300);
    assertEquals(300,roomtest.getPrice());

    Assertions.assertThrows(IllegalArgument.class, () -> {
      roomtest.setPrice(-200);
    });
  }

  @Test
  void getNumberGuest() {
    assertEquals(0,roomtest.getNumberGuest());
  }

  @Test
  void setNumberGuest() throws IllegalArgument{
    roomtest.setNumberGuest(1);
    assertEquals(1,roomtest.getNumberGuest());

    Assertions.assertThrows(IllegalArgument.class, () -> {
      roomtest.setPrice(-2);
    });
  }
  @Test
  void isAvailable() throws IllegalArgument {
    assertEquals(true,roomtest.isAvailable());
    roomtest1.setNumberGuest(1);
    assertEquals(false,roomtest1.isAvailable());
  }

  @Test
  void bookRoom() throws IllegalBooking {
    assertEquals(true,roomtest1.isAvailable());
    roomtest1.bookRoom(1);
    assertEquals(1, roomtest1.getNumberGuest());

    Assertions.assertThrows(IllegalBooking.class, () -> {
      roomtest1.bookRoom(0);
    });

    Assertions.assertThrows(IllegalBooking.class, () -> {
      roomtest1.bookRoom(5);
    });

    assertEquals(false,roomtest1.isAvailable());

    Assertions.assertThrows(IllegalBooking.class, () -> {
      roomtest.bookRoom(5);
    });

    Assertions.assertThrows(IllegalBooking.class, () -> {
      roomtest.bookRoom(0);
    });


  }

  @Test
  void testEquals() throws IllegalArgument {
    assertTrue(roomtest.equals(roomtest));
    assertFalse(roomtest.equals(null));
    Room roomtest1=roomtest;
    assertEquals(roomtest1,roomtest);
    DoubleRoom roomtestD;
    roomtestD=new DoubleRoom (1,300,1);
    assertFalse(roomtestD.equals(roomtest));


    SingleRoom roomtest3, roomtest4;
    roomtest3 =new SingleRoom(1,300,0);
    assertTrue(roomtest3.equals(roomtest) && roomtest.equals(roomtest3));
    roomtest2=new SingleRoom(1,300,0);
    roomtest2.setMaxOccupancy(2);
    assertFalse(roomtest2.equals(roomtest) && roomtest.equals(roomtest2));
    roomtest4 =new SingleRoom(1,300,1);
    roomtest4.setNumberGuest(3);
    assertFalse(roomtest4.equals(roomtest) && roomtest.equals(roomtest4));
    roomtest3 =new SingleRoom(1,299,1);
    assertFalse(roomtest3.equals(roomtest) && roomtest.equals(roomtest3));
    roomtest3 =new SingleRoom(0,300,1);
    assertTrue(roomtest3.equals(roomtest) && roomtest.equals(roomtest3));
  }

  @Test
  void testHashCode() {
    roomtest2=new SingleRoom(1,300,1);
    assertEquals(roomtest2.hashCode(),roomtest.hashCode());
  }


}