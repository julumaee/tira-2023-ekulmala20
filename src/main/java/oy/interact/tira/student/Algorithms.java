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
      // TODO: Student, implement this.
   }

   ////////////////////////////////////////////////////////////
   // Insertion Sort for slice of the array using a Comparator
   ////////////////////////////////////////////////////////////

   public static <T> void insertionSort(T[] array, int fromIndex, int toIndex, Comparator<T> comparator) {
      // TODO: Student, implement this.
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
      return -1;
   }

   ///////////////////////////////////////////
   // Binary search using a Comparator
   ///////////////////////////////////////////

   public static <T> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex, Comparator<T> comparator) {
      return -1;
   }

   public static <E extends Comparable<E>> void fastSort(E [] array) {
      // TODO: Student, implement this.
   }

   public static <E> void fastSort(E [] array, Comparator<E> comparator) {
      // TODO: Student, implement this.
   }

   public static <E> void fastSort(E [] array, int fromIndex, int toIndex, Comparator<E> comparator) {
      // TODO: Student, implement this.
   }

}
