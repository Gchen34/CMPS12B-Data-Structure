import java.io.*;
import java.util.Scanner;

public class Simulation {
	public static void main(String[] args) throws IOException {
		
		// Declares the variables
		Scanner in = null;
		PrintWriter report = null;
		PrintWriter trace = null;
		Queue Storage = new Queue();
		int n, m, time;
		
		// Throws an exception
		if (args.length != 1) {
			System.err.println("Usage: Simulation fileIn & fileOut");
			System.exit(1);
		}

		// Initializes input and output files
		in = new Scanner(new File(args[0]));
		report = new PrintWriter(new FileWriter(args[0] + ".rpt"));
		trace = new PrintWriter(new FileWriter(args[0] + ".trc"));

		// Declares and initializes the m job and the Queue array
		m = numOfJobs(in);
		while (in.hasNext()) {
			Storage.enqueue((Job) getJob(in));
		}
		
		// Trace
		trace.println("Trace file: " + args[0] + ".trc");
		trace.println(m + " Jobs:");
		trace.println(Storage + "\n");

		for (n = 1; n < m; n++) {
			time = 0;
			Queue[] processorQueues = new Queue[n + 1];
			for (int i = 0; i < n + 1; i++) {
				processorQueues[i] = new Queue();
			}

			trace.println("************************************************");
			trace.println(n + " processor:");
			trace.println("************************************************");

			processorQueues[0] = Storage;
			while (((Job) processorQueues[0].peek()).getFinish() == -1 || processorQueues[0].length() != m) {

				if (time == 0) {
					printTrace(trace, processorQueues, n, time);
					time = ((Job) processorQueues[0].peek()).getArrival();
					processorQueues[1].enqueue(processorQueues[0].dequeue());
					Job temp = (Job) processorQueues[1].peek();
					temp.computeFinishTime(time);
				} else if (((Job) processorQueues[0].peek()).getFinish() != -1) {
					int small = getIndex(processorQueues);
					time = ((Job) processorQueues[small].peek()).getFinish();
					processorQueues[0].enqueue(processorQueues[small].dequeue());
					printTrace(trace, processorQueues, n, time);
				} else {
					printTrace(trace, processorQueues, n, time);
					int small = getIndex(processorQueues);
					if (processorQueues[small].length() == 0) {
						time = ((Job) processorQueues[0].peek()).getArrival();
						processorQueues[small].enqueue(processorQueues[0].dequeue());
						Job temp = (Job) processorQueues[small].peek();
						temp.computeFinishTime(time);

						printTrace(trace, processorQueues, n, time);

						small = getIndex(processorQueues);
						time = ((Job) processorQueues[small].peek()).getFinish();
						processorQueues[0].enqueue(processorQueues[small].dequeue());
						printTrace(trace, processorQueues, n, time);

						small = getIndex(processorQueues);
						time = ((Job) processorQueues[0].peek()).getArrival();
						processorQueues[small].enqueue(processorQueues[0].dequeue());
						temp = (Job) processorQueues[small].peek();
						temp.computeFinishTime(time);
						printTrace(trace, processorQueues, n, time);

						time = ((Job) processorQueues[small + 1].peek()).getFinish();
						processorQueues[0].enqueue(processorQueues[small + 1].dequeue());
						printTrace(trace, processorQueues, n, time);

						small = getIndex(processorQueues);
						time = ((Job) processorQueues[small - 1].peek()).getFinish();
						processorQueues[0].enqueue(processorQueues[small - 1].dequeue());
						printTrace(trace, processorQueues, n, time);

					}

					else if (((Job) processorQueues[0].peek()).getArrival() <= ((Job) processorQueues[small].peek()).getFinish()) {
						time = ((Job) processorQueues[0].peek()).getArrival();
						processorQueues[small].enqueue(processorQueues[0].dequeue());

						printTrace(trace, processorQueues, n, time);

						time = ((Job) processorQueues[small].peek()).getFinish();
						processorQueues[0].enqueue(processorQueues[small].dequeue());
						Job temp = (Job) processorQueues[small].peek();
						temp.computeFinishTime(time);

						printTrace(trace, processorQueues, n, time);
					} else {
						Job temp = (Job) processorQueues[small].peek();
						temp.computeFinishTime(time);

						printTrace(trace, processorQueues, n, time);

						time = ((Job) processorQueues[small].peek()).getFinish();
						processorQueues[0].enqueue(processorQueues[small].dequeue());

						printTrace(trace, processorQueues, n, time);

					}

				}

			}
			
			// Report
			if (n == 1) {
				report.println("Report file: " + args[0] + ".rpt");
				report.println(m + " Jobs:");
				report.println(processorQueues[0] + "\n");
				report.println("************************************************");
			}
			int totalWait = 0;
			float averageWait = 0;
			int max = 0;
			int maxWait = 0;
			Queue StorageQueue = new Queue();
			while (processorQueues[0].length() != 0) {
				max = ((Job) processorQueues[0].peek()).getWaitTime();
				if (maxWait < max) {
					maxWait = max;
				}
				totalWait += ((Job) processorQueues[0].peek()).getWaitTime();
				StorageQueue.enqueue((Job) processorQueues[0].dequeue());
			}
			averageWait = (float) totalWait / m;
			report.println(
					n + " processor: totalWait=" + totalWait + " maxWait=" + maxWait + " averageWait=" + averageWait);

			while (StorageQueue.length() != 0) {
				((Job) StorageQueue.peek()).resetFinishTime();
				Storage.enqueue((Job) StorageQueue.dequeue());
			}
			trace.println();
		}

	//Closes Scanner and PrintWriter 	
		in.close();
		report.close();
		trace.close();

	}

	//printTrace
	public static void printTrace(PrintWriter trace, Queue[] q, int n, int time) {
		trace.println("time = " + time);
		for (int i = 0; i < n + 1; i++) {
			trace.println(i + ": " + q[i]);
		}
	}

	//getIndex
	public static int getIndex(Queue[] q) {
		int index = 0;
		if (((Job) q[index].peek()).getFinish() == -1) {
			index = 1;
		}
		for (int i = 1; i < q.length; i++) {
			if (q[i].length() < q[index].length()) {
				if (q[i].length() == q[index].length()) {
					index = index;
				} else {
					index = i;
				}
			} else if (q[i].length() < q[index].length() && ((Job) q[index].peek()).getFinish() != -1) {
				index = index;
			}
		}
		return index;
	}

	
	//numOfJobs 
	public static int numOfJobs(Scanner in) {
		int x;
		String s = in.nextLine();
		x = Integer.parseInt(s);
		return x;
	}

	//getJob
	public static Job getJob(Scanner in) {
		String[] s = in.nextLine().split(" ");
		int a = Integer.parseInt(s[0]);
		int d = Integer.parseInt(s[1]);
		return new Job(a, d);
	}
}
