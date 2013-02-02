package org.collectivemedia.mapreduce.authorquotes;

import org.collectivemedia.mapreduce.authorquotes.AggregationByAuthor;

public class AuthorQuotes {
		/**
		 * @param args
		 */
		public static void main(String[] args) {
			if (args.length < 4) {
				System.out.println("Insufficient Number of Arguments");
				throw new IllegalArgumentException("Expect arguments: jobtype jobname input output");
			}

			String jobtype = args[0];
			String jobname = args[1];
			String input = args[2];
			String output = args[3];
			
			String szjobtype = null;
			
			if (Integer.parseInt(jobtype) == 1) {
				szjobtype = "AggregationByAuthor";
			} else if (Integer.parseInt(jobtype) == 2) {
				szjobtype = "AggregationByWord";
			} else {
				System.out.println("jobtype MUST be 1,2 or 3");
				throw new IllegalArgumentException("jobtype MUST be 1,2 or 3");
			}
			
			System.out.println("Starting "+szjobtype+" job "+ jobname+ " with input="+ input+
					" and output="+output);
			
			if (Integer.parseInt(jobtype) == 1) {
				AggregationByAuthor aggrAuthorJob = new AggregationByAuthor();
				aggrAuthorJob.RunJob(jobname, input, output);
			} else if (Integer.parseInt(jobtype) == 2) {
				AggregationByWord aggrAuthorWord = new AggregationByWord();
				aggrAuthorWord.RunJob(jobname, input, output);
			}
		}

	}
