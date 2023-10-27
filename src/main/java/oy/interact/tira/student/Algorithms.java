package oy.interact.tira.student;

import java.util.Comparator;

public class Algorithms {

   private Algorithms() {
      // nada
   }

   ///////////////////////////////////////////
   // Swap indexes of two elements in an array
   ///////////////////////////////////////////

   public static <T> void swap(T[] array, int first, int second) {
      T tmp = array[first];
      array[first] = array[second];
      array[second] = tmp;
   }

   ///////////////////////////////////////////
   // Insertion Sort for the whole array
   ///////////////////////////////////////////

   public static <T extends Comparable<T>> void insertionSort(T[] array) {
      insertionSort(array, 0, array.length);
   }

   ///////////////////////////////////////////
   // Insertion Sort for a slice of the array
   ///////////////////////////////////////////

   public static <T extends Comparable<T>> void insertionSort(T[] array, int fromIndex, int toIndex) {
      for (int i=fromIndex; i<toIndex; i++) {
         T tmp = array[i];
         int j;
         for (j=i-1; j>=fromIndex; j--) {
            if (array[j].compareTo(tmp) > 0) {
               array[j+1] = array[j];
            }
            else {
               break;
            }
         }
         array[j+1] = tmp;
      }
   }

   //////////////////////////////////////////////////////////
   // Insertion Sort for the whole array using a Comparator
   //////////////////////////////////////////////////////////

   public static <T> void insertionSort(T[] array, Comparator<T> comparator) {
      insertionSort(array, 0, array.length, comparator);
   }

   ////////////////////////////////////////////////////////////
   // Insertion Sort for slice of the array using a Comparator
   ////////////////////////////////////////////////////////////

   public static <T> void insertionSort(T[] array, int fromIndex, int toIndex, Comparator<T> comparator) {
      for (int i=fromIndex; i<toIndex; i++) {
         T tmp = array[i];
         int j;
         for (j=i-1; j>=fromIndex; j--) {
            if (comparator.compare(array[j], tmp) > 0) {
               array[j+1] = array[j];
            }
            else {
               break;
            }
         }
         array[j+1] = tmp;
      }
   }

   ///////////////////////////////////////////
   // Reversing an array
   ///////////////////////////////////////////

   public static <T> void reverse(T[] array) {
      reverse(array, 0, array.length);
   }

   ///////////////////////////////////////////
   // Reversing a slice of an array
   ///////////////////////////////////////////

   public static <T> void reverse(T[] array, int fromIndex, int toIndex) {
      toIndex--;
      while (fromIndex < toIndex) {
         swap(array, fromIndex, toIndex);
         fromIndex++;
         toIndex--;
      }
   }

   ///////////////////////////////////////////
   // Binary search bw indices
   ///////////////////////////////////////////

   public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {
   // Iterative implementation
      /*
      toIndex--;
      int middleIndex = 0;
      while (fromIndex <= toIndex) {
         middleIndex = fromIndex + (toIndex - fromIndex) / 2;
         if (aValue.compareTo(fromArray[middleIndex]) == 0) { // Item found
            return middleIndex;
         }
         else if (aValue.compareTo(fromArray[middleIndex]) < 0) {
            toIndex = middleIndex - 1;
         }
         else {
            fromIndex = middleIndex + 1;
         }
      }
      return -1; // Item not found
   }
      */

   // Recursive implementation

      int middleIndex = 0;
      if (fromIndex >= toIndex - 1) {
         if (aValue.compareTo(fromArray[fromIndex]) == 0) {
            return fromIndex;
         }
         else {
            return -1; // Item not found
         }
      } else {
         middleIndex = fromIndex + (toIndex - fromIndex) / 2;
         if (aValue.compareTo(fromArray[middleIndex]) < 0) {
            return binarySearch(aValue, fromArray, fromIndex, middleIndex);
         }
         else return binarySearch(aValue, fromArray, middleIndex, toIndex);
      }
   }

   ///////////////////////////////////////////
   // Binary search using a Comparator
   ///////////////////////////////////////////

   public static <T> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex, Comparator<T> comparator) {
      // Iterative implementation
      /*
      toIndex--;
      int middleIndex = 0;
      while (fromIndex <= toIndex) {
         middleIndex = fromIndex + (toIndex - fromIndex) / 2;
         if (comparator.compare(aValue, fromArray[middleIndex]) == 0) { // Item found
            return middleIndex;
         }
         else if (comparator.compare(aValue, fromArray[middleIndex]) < 0) {
            toIndex = middleIndex - 1;
         }
         else {
            fromIndex = middleIndex + 1;
         }
      }
      return -1; // Item not found
      */

      // Recursive implementation

      int middleIndex = 0;
      if (fromIndex >= toIndex - 1) {
         if (comparator.compare(aValue, fromArray[fromIndex]) == 0) {
            return fromIndex;
         }
         else {
            return -1; // Item not found
         }
      } else {
         middleIndex = fromIndex + (toIndex - fromIndex) / 2;
         if (comparator.compare(aValue, fromArray[middleIndex]) < 0) {
            return binarySearch(aValue, fromArray, fromIndex, middleIndex, comparator);
         }
         else return binarySearch(aValue, fromArray, middleIndex, toIndex, comparator);
      }

   }

   public static <E extends Comparable<E>> void fastSort(E [] array) {
      quickSort(array, 0, array.length, Comparator.naturalOrder());
      // mergeSort(array, 0, array.length - 1, Comparator.naturalOrder());
      // heapSort(array, 0, array.length, Comparator.naturalOrder());
   }

   public static <E> void fastSort(E [] array, Comparator<E> comparator) {
      fastSort(array, 0, array.length, comparator);
   }

   public static <E> void fastSort(E [] array, int fromIndex, int toIndex, Comparator<E> comparator) {
      quickSort(array, fromIndex, toIndex, comparator);
      // mergeSort(array, fromIndex, toIndex - 1, comparator);
      // heapSort(array, fromIndex, toIndex, comparator);
   }

   private static <E> void quickSort(E [] array, int fromIndex, int toIndex, Comparator<E> comparator) {
      if (fromIndex < toIndex - 1) {
         int partitionIndex = partition(array, fromIndex, toIndex, comparator);
         quickSort(array, fromIndex, partitionIndex, comparator);
         quickSort(array, partitionIndex + 1, toIndex, comparator);
      }
   }

   private static <E> int partition(E [] array, int low, int high, Comparator<E> comparator) {
      E pivot = array[high - 1];
      int leftIndex = low - 1;
      for (int rightIndex = low; rightIndex < high; rightIndex++) {
         if (comparator.compare(array[rightIndex], pivot) <= 0) {
            leftIndex++;
            swap(array, leftIndex, rightIndex);
         }
      }
      return leftIndex;
   }

   private static <E> void heapSort(E [] array, int fromIndex, int toIndex, Comparator<E> comparator) {
      toIndex--;
      heapify(array, fromIndex, toIndex, comparator);
      while (toIndex > fromIndex) {
         swap(array, toIndex, fromIndex);
         toIndex--;
         siftDown(array, fromIndex, fromIndex, toIndex, comparator);
      }
   }

   private static <E> void heapify(E [] array, int fromIndex, int toIndex, Comparator<E> comparator) {
      int startIndex = ((toIndex - fromIndex) - 1) / 2;
         while (startIndex >= 0) {
            siftDown(array, fromIndex, startIndex + fromIndex, toIndex, comparator);
            startIndex--;
         }
   }

   private static <E> void siftDown(E [] array, int fromIndex, int startIndex, int toIndex, Comparator<E> comparator) {
      int rootNode = startIndex;

      while (2 * (rootNode - fromIndex) + 1 <= toIndex - fromIndex) {
         int childNode = 2 * (rootNode - fromIndex) + 1;
         int swapNode = rootNode;
         if (comparator.compare(array[swapNode], array[fromIndex + childNode]) < 0) {
            swapNode = fromIndex + childNode;
         }
         if (fromIndex + childNode + 1 <= toIndex && comparator.compare(array[swapNode], array[fromIndex + childNode + 1]) < 0) {
            swapNode = fromIndex + childNode + 1;
         }
         if (swapNode == rootNode) {
            return;
         } else {
            swap(array, rootNode, swapNode);
            rootNode = swapNode;
         }
      }
   }


   // NOT YET WORKING!!

   @SuppressWarnings("unchecked")
   private static <E> void mergeSort(E [] array, int fromIndex, int toIndex, Comparator<E> comparator) {
      if (toIndex - fromIndex < 2) {
         return; // Array smaller than 2 is already sorted
      }
      int middleIndex = (toIndex + fromIndex) / 2;
      E [] leftArray = (E []) new Comparable[middleIndex - fromIndex];
      E [] rightArray = (E []) new Comparable[toIndex - middleIndex  - fromIndex];

      for (int index = 0; index < middleIndex - fromIndex; index++) {
         leftArray[index] = array[index + fromIndex];
      }
      for (int index = middleIndex; index < toIndex - fromIndex; index++) {
         rightArray[index] = array[index + fromIndex];
      }
      mergeSort(leftArray, 0, leftArray.length - 1, comparator);
      mergeSort(rightArray, 0, rightArray.length - 1, comparator);
      merge(array, leftArray, rightArray, fromIndex, comparator);
   }

   private static <E> void merge(E [] array, E [] leftArray, E [] rightArray, int fromIndex, Comparator<E> comparator) {
      int leftIndex = 0;
      int rightIndex = 0;
      int arrayIndex = fromIndex;

      while (leftIndex < leftArray.length && rightIndex < rightArray.length) {
         if (comparator.compare(leftArray[leftIndex], rightArray[rightIndex]) <= 0) {
            array[arrayIndex] = leftArray[leftIndex];
            arrayIndex++;
            leftIndex++;
         } else {
            array[arrayIndex] = rightArray[rightIndex];
            arrayIndex++;
            rightIndex++;
         }
      }
      while (leftIndex < leftArray.length) {
         array[arrayIndex] = leftArray[leftIndex];
         arrayIndex++;
         leftIndex++;
      }
      while (rightIndex < rightArray.length) {
         array[arrayIndex] = rightArray[rightIndex];
         arrayIndex++;
         rightIndex++;
      }
   }
}