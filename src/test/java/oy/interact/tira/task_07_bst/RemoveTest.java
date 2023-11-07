package oy.interact.tira.task_07_bst;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import oy.interact.tira.factories.BSTFactory;
import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedOrderedContainer;

@DisplayName("Testing removing from BST.")

public class RemoveTest {
    
    static TIRAKeyedOrderedContainer<String, Integer> bst = null;
    static final int MIN_TEST_COUNT = 100;
    static final int TEST_COUNT = 999;
    static Pair<String, Integer> [] testArray;
    static int testIndex = 0;
    static Integer testValue;
    static boolean testResult;
    static String removedValue;
    static Comparator<String> comparator = new Comparator<>() {

        
        @Override
        public int compare(String first, String second) {
            return first.compareTo(second);
        }
    };
    
    @BeforeAll
    static void allocateDataStructure() {
        try {
            bst = BSTFactory.createBST(comparator);
            if (null == bst) {
                fail("BSTFactory.createBST did not instantiate the TIRAKeyedOrderedContainer implementation (yet)");
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("BSTFactory.createBST could not instantiate the TIRAKeyedOrderedContainer implementation");
        }
    }

    @Test
    // @Timeout(value = 30, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Tests BST calling add, size, get and toArray")
    void removeTest() {
        try {
            if (bst == null) {
                System.out.println("======================================================");
                System.out.println("Not testing BST yet since it has not been implemented.");
                System.out.println("======================================================");
                fail("BSTFactory.createBST could not instantiate the TIRAKeyedOrderedContainer implementation (yet?)");
                return;
            }
            bst.clear();
            List<String> randomList = new ArrayList<>();
            final int CURRENT_TEST_COUNT = ThreadLocalRandom.current().nextInt(TEST_COUNT) + MIN_TEST_COUNT;
            for (int index = 0; index < CURRENT_TEST_COUNT; index++) {
                randomList.add(String.valueOf(index));
            }
            Collections.shuffle(randomList);
            for (int index = 0; index < CURRENT_TEST_COUNT; index++) {
                final int findValue = index;
                assertDoesNotThrow(() -> bst.add(randomList.get(findValue), Integer.parseInt(randomList.get(findValue))), "BST add must not throw");
            }
            System.out.println(">> Testing BST with " + bst.size() + " entries");
            assertEquals(CURRENT_TEST_COUNT, bst.size(), () -> "Inserted count must match");
            
            final int findValue = 90;
            assertDoesNotThrow( () -> bst.remove(randomList.get(findValue)), "Removing from BST must not throw");
            assertEquals(CURRENT_TEST_COUNT - 1, bst.size(), () -> "BST size must be 1 smaller than before removing");
            

            assertDoesNotThrow( () -> testArray = bst.toArray(), "BST toSortedArray must not throw");
            assertNotNull(testArray, () -> "Returned array from toSortedArray must not be null");
            assertEquals(randomList.size(), testArray.length, () -> "Test array and toSortedArray lengths do not match");
            Object [] originalArray = randomList.toArray();
            Arrays.sort(originalArray);
            for (int index = 0; index < CURRENT_TEST_COUNT; index++) {
                final int findIndex = index;
                assertNotNull(testArray[index], "Array from toSortedArray must not contain null elements");
                assertEquals(originalArray[index], testArray[index].getKey(), () -> "Array elements do not match");
                int randomListIndex = Arrays.binarySearch(originalArray, 0, originalArray.length, String.valueOf(index));
                assertDoesNotThrow(() -> testIndex = bst.findIndex(value -> value.equals(findIndex)), "Finding an index from BST must not throw");
                assertEquals(randomListIndex, testIndex, "Found testIndex for the value is not correct");
                System.out.format("Searching for %5s at index %4d ...", originalArray[findIndex], findIndex);
                assertDoesNotThrow( () -> testValue = bst.getIndex(findIndex).getValue(), "BST get(int) must not throw");
                System.out.format(" and found %d%n", testValue);
                assertEquals(Integer.valueOf((String)originalArray[findIndex]), testValue, "When in correct order, same object in same indices");
                assertDoesNotThrow(() -> testIndex = bst.indexOf(originalArray[findIndex].toString()), "bst.indexOf must not throw");
                assertEquals(findIndex, testIndex, "Index of item in bst must match the index of item in test data");
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("Something went wrong in the test." + e.getMessage());
        }
    }

}
