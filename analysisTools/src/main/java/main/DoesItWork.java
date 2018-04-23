package main;

import intcreation.CreateInts;
import oracle.Oracle;
import bubblesort.*;
import heapsort.*;
import mergesort.*;
import quicksort.*;
import selectionsort.*;
import shellsort.*;
import insertionsort.*;


public class DoesItWork {

	public static void main(String[] args) {
		CreateInts ints = new CreateInts();
		Oracle oracle = new Oracle();
		int listLength = 10000;

		int[] listOriginal = ints.giveInts(listLength);
		int[] beforeSort = new int[listLength];

		for (int i = 0; i < listLength; i++) {
			beforeSort[i] = listOriginal[i];
		}

		ShellSortPone6.sort(listOriginal);

		//for (int i = 0; i < listLength; i++) {
		//	System.out.println(listOriginal[i]);  //Sätt listLength=10 och aktivera denna om du vill...
		//}										//...dubbelkolla att din algo inte skapat dubletter

		System.out.println(oracle.assertPerturbation(beforeSort, listOriginal));

	}
}