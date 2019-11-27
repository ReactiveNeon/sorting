import java.util.*;
import java.util.stream.*;
import java.io.*;
class BenjaminPotterA3 {

   /* These methods should each do the appropriate
   sort on the String array list,  which contains listSize
   Strings.  The method should return the time the sort takes
   in seconds, accurate to the nearest millisecond. 
   Convert all the letters to uppercase before sorting.*/
   public static double insertSort(String[] list, int listSize){ 
      long startTime = System.currentTimeMillis();
      
      for (int top = 1; top < listSize; top++){
         String item = list[top];
         int i;
         for (i = top; i > 0 && item.compareTo(list[i - 1]) < 0; i--)
            list[i] = list[i - 1];
         list[i] = item; 
      }
      
      return System.currentTimeMillis() - startTime; 
   }
   
   public static double selectSort(String[] list, int listSize){ 
      long startTime = System.currentTimeMillis();
   
      for (int top = listSize - 1; top > 0; top--)
      {
         int largeLoc = 0;
         for (int i = 1; i <= top; i++)
            if (list[i].compareTo(list[largeLoc]) > 0)
               largeLoc = i;
         String temp = list[top];
         list[top] = list[largeLoc];
         list[largeLoc] = temp;
      } 
      
      return System.currentTimeMillis() - startTime; 
   }
   
   public static double shellsort(String[] list, int listSize){      
      long startTime = System.currentTimeMillis();
      
      List<Integer> kValues = new ArrayList<Integer>();
      int k = 1;
      
      while (k < listSize) {
         kValues.add(k);
         
         k = (k * 3) + 1; 
      }
      
      for (int a = kValues.size() - 1; a >= 0; a--) 
      { 
         int gap = kValues.get(a);
         
         for (int i = gap; i < listSize; i++) {
            String key = list[i];
            int j = i;
            while (j >= gap && list[j - gap].compareTo(key) > 0) {
                list[j] = list[j - gap];
                j -= gap;
            }
            list[j] = key;
         }
      }
      
      return System.currentTimeMillis() - startTime;
   }
   
   /* This method prints up to max items from the list.
   Print each word separated by a space, with as many
   words on each line as possible (up to 80 characters per line)*/
   public static void printList(String[] list, int listSize, int max) {
      for(int i = 0; i < listSize; i++) {
         System.out.println(list[i]);
      }
   }
   /* Read all the words from a file and store them in
   list.  Return the number of words read.  Strip all
   punctuation from the words (other than the
   apostrophe).  Return 0 if the file doesn't exist*/
   public static int readList(String[] list, String fileName) {
      int wordCount = 0;
   
      try { 
         FileReader fr = new FileReader(fileName);
         BufferedReader br = new BufferedReader(fr);
         
         // TODO: fix end of line chars not meaning a space
         String file = br.lines().map(
            l -> l + " "
         ).collect(Collectors.joining());

         StringTokenizer st = new StringTokenizer(file, " 0123456789<>?:,./;'[]{}-=_+\\\"");
         
         while (st.hasMoreTokens()) {
            list[wordCount] = st.nextToken().toUpperCase();
            wordCount++;
         }
         
         br.close();
         
      } catch (IOException ioe) {
      
      }  
      
      return wordCount;
   }
   
   public static void main(String[] args)
   {
      Scanner input = new Scanner(System.in);
      String[] words = new String[1500000];
      String[] original = new String[1500000];
      System.out.println("Enter file name");
      String name = input.nextLine();
      String choice;
      int numWords = readList(words, name);
      double insertTime = 0, selectTime = 0, shellTime = 0;

      if (numWords !=0)
      {
         System.arraycopy(words,0, original, 0, words.length);
         System.out.println("Enter 1 for Insertion Sort");
         System.out.println("Enter 2 for Selection Sort");
         System.out.println("Enter 3 for Shellsort");
         System.out.println("Enter 4 to do all 3 sorts");
         choice = input.nextLine();
         if (choice.charAt(0) == '1' || choice.charAt(0) == '4')
         {  
            System.out.println("Here is the list (max 2000 words) after Insertion sort:");
            insertTime = insertSort(words, numWords);
            printList(words, numWords, 2000);
            System.arraycopy(original,0, words, 0, words.length);
            System.out.println("***************************");
         }
         if (choice.charAt(0) == '2' || choice.charAt(0) == '4')
         {
            System.out.println("Here is the list (max 3000 words) after Selection sort:");
            selectTime = selectSort(words, numWords);
            printList(words, numWords, 3000);
            System.arraycopy(original,0, words, 0, words.length);
            System.out.println("***************************");
         }
         if (choice.charAt(0) == '3' || choice.charAt(0) == '4')
         {
            System.out.println("Here is the list (max 4000 words) after Shellsort:");
            shellTime = shellsort(words, numWords);
            printList(words, numWords, 4000);
            System.out.println("***************************");
         }

         System.out.println("Read " + numWords + " words in " + name + ".");
         if (choice.charAt(0) == '1' || choice.charAt(0) == '4')
            System.out.println("It took " + insertTime + " s to sort the words using insertion sort");
         if (choice.charAt(0) == '2' || choice.charAt(0) == '4')
            System.out.println("It took " + selectTime + " s to sort the words using selection sort");
         if (choice.charAt(0) == '3' || choice.charAt(0) == '4')
            System.out.println("It took " + shellTime + " s to sort the words using Shellsort");
      }
      else
         System.out.println("No such file exists.");
   }
      
} 