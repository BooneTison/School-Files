/**
 * Write a succinct, meaningful description of the class here. You should avoid wordiness    
 * and redundancy. If necessary, additional paragraphs should be preceded by <p>,
 * the html tag for a new paragraph.
 *
 * <p>Bugs: (a list of bugs and / or other problems)
 *
 * @author <your name>
 * @date   <date of completion>
 */
 
package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import heap.MinHeap;
import heap.SortedListMinHeap;
import heap.UnsortedListMinHeap;
import utilities.Timer;

public class Main
{
	public static void main(String[] args)
	{
		final int[] ELEMENT_COUNT = new int[] {5000, 10000, 50000, 100000, 200000 }; //, 500000};

		//
		// All heaps we are testing
		List<MinHeap<Integer>> heaps = new ArrayList<MinHeap<Integer>>();
		heaps.add(new UnsortedListMinHeap<Integer>());
		heaps.add(new SortedListMinHeap<Integer>());

		//
		// Execute the build process over all the heaps
		for (MinHeap<Integer> heap : heaps)
		{
			System.out.println(heap.getClass() + " Build Heap");

            //
            // TODO: timing code
			// 
			// You are strongly advised to create support methods for building a shuffled list, etc.
			//

			System.out.println();

			heap.clear();
		}
	}
}